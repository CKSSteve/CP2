package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.EmailValueBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.bean.swift.T01RsBean;
import com.uxb2b.ecp.bean.swift.T03RsBean;
import com.uxb2b.ecp.business.IAwardDataRegisterBusiness;
import com.uxb2b.ecp.business.IAwardDataVerifyBusiness;
import com.uxb2b.ecp.exception.CertServerException;
import com.uxb2b.ecp.exception.RestfulException;
import com.uxb2b.ecp.exception.SwiftException;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.MessageInbox;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.persist.IAwardDataRepositoryPersist;
import com.uxb2b.ecp.persist.IEnterpriseRepositoryPersist;
import com.uxb2b.ecp.persist.IFlowFunctionRepositoryPersist;
import com.uxb2b.ecp.persist.IFlowStepRepositoryPersist;
import com.uxb2b.ecp.persist.IJsonMessagePersist;
import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.persist.IPassRecordRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.AwardDataRepository;
import com.uxb2b.ecp.service.DateConvertService;
import com.uxb2b.ecp.service.MailService;
import com.uxb2b.ecp.service.SystemInfo;
import com.uxb2b.ecp.types.FunctionType;

@Component
public class AwardDataVerifyBusinessImpl implements IAwardDataVerifyBusiness{
	
	private Logger log = LoggerFactory.getLogger(AwardDataVerifyBusinessImpl.class);
	
	@Autowired
	private SystemInfo systemInfo;
	
	@Autowired
	private DateConvertService dateConvertService;
	
	@Autowired
	AwardDataRepository awardDataRepository;
	
	@Autowired
	IFlowStepRepositoryPersist iFlowStepRepositoryPersist;
	
	@Autowired
	IFlowFunctionRepositoryPersist iFlowFunctionRepositoryPersist;
	
	@Autowired
	IMessageInboxRepositoryPersist iMessageInboxRepositoryPersist;

	@Autowired
	IAwardDataRepositoryPersist iAwardDataRepositoryPersist;
	
	@Autowired
	IPassRecordRepositoryPersist iPassRecordRepositoryPersist;
	
	@Autowired
	IJsonMessagePersist iJsonMessagePersist;
	
	@Autowired
	IEnterpriseRepositoryPersist iEnterpriseRepositoryPersist;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	MailService mailService;
	
	@Override
	public Page<AwardData> findbyAwardDataList(int pageNumber, int pageSize) throws Exception {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		
		List<String> approveStatusList = Arrays.asList("2","3");
		Pageable pageable = new PageRequest(pageNumber-1,pageSize, new Sort(Sort.Direction.ASC, "id.quationNo","id.itemNo"));
		Page<AwardData> awardDataList  = awardDataRepository.findByBranchIdAndRoleId1AndApproveStatus1In(userDetails.getBranchId(), userDetails.getRoleId(), approveStatusList, pageable);	
		
		return awardDataList;
	}

	@Override
	public AwardData findByQuationNoAndItemNo(String quationNo, String itemNo) throws Exception {
		return awardDataRepository.findByIdQuationNoAndIdItemNo(quationNo, Integer.parseInt(itemNo));
	}

	@Override
	public AwardData updateAwardData(String quationNo, String itemNo) throws Exception {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		// (1)	UPDATE AWARD_DATA
		AwardData awardData = awardDataRepository.findByIdQuationNoAndIdItemNo(quationNo, Integer.parseInt(itemNo));
		int maxStepNo = iFlowStepRepositoryPersist.getMaxStepNoByFlowId(awardData.getFlowId1());
		Boolean sendSwift = false;
		
		if("Y".equalsIgnoreCase(awardData.getFinalStep1())){
			sendSwift = true;
			awardData.setApproveStatus1("4");
			awardData.setReplyTime1(dateConvertService.DateToTimestamp(new Date()));
		}else{
//			(1)SELECT MAX(STEP_NO) FROM FLOW_STEP WHERE FLOW_ID=AWARD_DATA.FLOW_ID1
//			若AWARD_DATA.STEP_NO1+1=(1)查詢結果 THEN APPROVE_STATUS1=3
//			ELSE APPROVE_STATUS1=2
			if(maxStepNo == (awardData.getStepNo1()+1)){
				awardData.setApproveStatus1("3");
			}else{
				awardData.setApproveStatus1("2");
			}
		}
		if("N".equalsIgnoreCase(awardData.getFinalStep1())){
			awardData.setStepNo1(awardData.getStepNo1()+1);
//			(2)SELECT ROLE_ID FROM FLOW_STEP WHERE FLOW_ID=AWARD_DATA.FLOW_ID1 AND STEP_NO=AWARD_DATA.STEP_NO1+1
			int roleId = iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(awardData.getFlowId1(), awardData.getStepNo1());
			awardData.setRoleId1(roleId);
		}
		if("N".equalsIgnoreCase(awardData.getFinalStep1()) && (maxStepNo == awardData.getStepNo1())){
			awardData.setFinalStep1("Y");
		}	
		
		if(sendSwift && "300".equals(awardData.getStatus())){
			int flowId2 = iFlowFunctionRepositoryPersist.findFlowId("B5");
			awardData.setFlowId2(flowId2);
			awardData.setStepNo2(1);
			int roleId2 = iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(flowId2, 1);
			awardData.setRoleId2(roleId2);
			String finalStep2 = iFlowStepRepositoryPersist.findFinalStepByFlowIdAndStepNo(flowId2, 1);
			awardData.setFinalStep2(finalStep2.equals("0") ? "N" : "Y");
			awardData.setApproveStatus2("0");
		}
		awardData.setLastModifyTime1(dateConvertService.DateToTimestamp(new Date()));
		awardData.setLastModifyUser1(userDetails.getUsername()+userDetails.getViewUserName());
		iAwardDataRepositoryPersist.saveAwardData(awardData);
		
		// (3)	INSERT MESSAGE_INBOX
		String timeFormat="yyyy-MM-dd HH:mm:ss";
		MessageInbox messageInbox = new MessageInbox();
		messageInbox.setBranchId(userDetails.getBranchId());
		messageInbox.setSender(userDetails.getUsername());
		if(sendSwift){
			messageInbox.setReceiverRole(Integer.parseInt("0"));
			if("290".equals(awardData.getStatus())){
				messageInbox.setSubject("得標放棄審核完成");
			}else{
				messageInbox.setSubject("得標確認審核完成");
			}
		}else{
			messageInbox.setReceiverRole(awardData.getRoleId1());
			if("290".equals(awardData.getStatus())){
				messageInbox.setSubject("得標放棄待審核");
			}else{
				messageInbox.setSubject("得標確認待審核");
			}
		}
		messageInbox.setContent("票券批號:"+(awardData.getBatchNo() == null ? "無":awardData.getBatchNo())+",審核日期:"+dateConvertService.TimestampToString(awardData.getLastModifyTime1(),timeFormat));
		messageInbox.setMessageTime(dateConvertService.DateToTimestamp(new Date()));
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		// (4)	若AWARD_DATA.FINAL_STEP1=Y:
		if(sendSwift){
			//i.	產生報價 request電文內容(參考CP2平台與票券商S2S介面規格)
			String json = iJsonMessagePersist.makeT03Message(awardData, userDetails, "verify");
			String wsUrl = iEnterpriseRepositoryPersist.findByUni(awardData.getIssuerId()).getWsUrl();
			//ii.	INSERT PASS_RECORD
			PassRecord passRecord = new PassRecord();
			passRecord.setTransactionNo(awardData.getId().getQuationNo());
			passRecord.setTxId(SystemInfo.TXID_AWARDDATA);
			passRecord.setTransactionDate(awardData.getReplyTime1());
			passRecord.setReSendTimes("0");
			passRecord.setContent(json);//電文內容
			passRecord.setBranchId(awardData.getBranchId());
			passRecord.setIssuerId(awardData.getIssuerId());
			passRecord.setPassTime(dateConvertService.DateToTimestamp(new Date()));
			try{
				String responseString =iSendMessagePersist.sendMessage(wsUrl+SystemInfo.TXID_AWARDDATA, json, SystemInfo.TXID_AWARDDATA);
				log.info("T03下行電文 :"+responseString);
				Gson gson = new Gson();
				T03RsBean t03Rs = gson.fromJson(responseString,T03RsBean.class);
				if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(t03Rs.getRETURN_CODE())){
					log.info("下行電文回傳成功");
				}else{
					log.error("T03下行電文回傳錯誤 : "+t03Rs.getRETURN_CODE()+" - "+t03Rs.getRETURN_DESC());
					passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
					passRecord.setReSendReason(systemInfo.swiftErrorMessage(t03Rs.getRETURN_CODE()));
					iPassRecordRepositoryPersist.savePassRecord(passRecord);
					throw new SwiftException("T03下行電文回傳錯誤 : "+t03Rs.getRETURN_CODE()+" - "+t03Rs.getRETURN_DESC());
				}
			}catch (CertServerException e) {
				passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
				passRecord.setReSendReason(e.getMessage());
				iPassRecordRepositoryPersist.savePassRecord(passRecord);
				throw new CertServerException(e.getMessage());
			}catch (RestfulException e) {
				passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
				passRecord.setReSendReason(e.getMessage());
				iPassRecordRepositoryPersist.savePassRecord(passRecord);
				throw new RestfulException(e.getMessage());
			}catch (SwiftException e) {
				passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
				passRecord.setReSendReason(e.getMessage());
				iPassRecordRepositoryPersist.savePassRecord(passRecord);
				throw new SwiftException(e.getMessage());
			}catch (Exception e) {
				log.error("傳送電文發生未知錯誤 :"+e);
				e.printStackTrace();
				passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
				passRecord.setReSendReason(e.getMessage());
				iPassRecordRepositoryPersist.savePassRecord(passRecord);
				throw new Exception(e.getMessage());
			}
		}
		return awardData;
	}

	@Override
	public AwardData updateAwardData(String quationNo, String itemNo, String rejectReason1) throws Exception {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		// (1)	UPDATE AWARD_DATA
		AwardData awardData = awardDataRepository.findByIdQuationNoAndIdItemNo(quationNo, Integer.parseInt(itemNo));
		awardData.setApproveStatus1("1");
		awardData.setStepNo1(1);
		//SELECT ROLE_ID FROM FLOW_STEP WHERE FLOW_ID=AWARD_DATA.FLOW_ID1 AND STEP_NO1=1
		int roleId1 = iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(awardData.getFlowId1(), awardData.getStepNo1());
		awardData.setRoleId1(roleId1);
		awardData.setFinalStep1("N");
		awardData.setRejectReason1(rejectReason1);		
		awardData.setLastModifyTime1(dateConvertService.DateToTimestamp(new Date()));
		awardData.setLastModifyUser1(userDetails.getUsername()+userDetails.getViewUserName());
		iAwardDataRepositoryPersist.saveAwardData(awardData);
		
		// (3)	INSERT MESSAGE_INBOX
		MessageInbox messageInbox = new MessageInbox();
		messageInbox.setBranchId(userDetails.getBranchId());
		messageInbox.setSender(userDetails.getUsername());
		messageInbox.setReceiverRole(awardData.getRoleId1());
		messageInbox.setSubject("票券批號(得標確認)審核退回");
		messageInbox.setContent("票券批號:"+(awardData.getBatchNo() == null ?"無":awardData.getBatchNo())+",退回原因:"+awardData.getRejectReason1());
		messageInbox.setMessageTime(dateConvertService.DateToTimestamp(new Date()));
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		//(4)寄送mail
		Timestamp nowTime= new Timestamp(new Date().getTime());
		EmailValueBean emailBean =new EmailValueBean();
		emailBean.setQuationNo(awardData.getId().getQuationNo());
		emailBean.setTenderNo(awardData.getTenderNo()); 
		emailBean.setAwardDate(awardData.getAwardDate());
		emailBean.setIssuerId(awardData.getIssuerId());
		emailBean.setIssuerName(awardData.getIssuerName());
		emailBean.setEffectDate(awardData.getEffectDate());
		emailBean.setExpiredDate(awardData.getExpiredDate());
		emailBean.setLoanType(awardData.getLoanType());
		emailBean.setLoanAmount(awardData.getLoanAmount());
		emailBean.setAmountUnit(awardData.getAmountUnit());
		emailBean.setCurrencyName(awardData.getCurrencyName());
		emailBean.setQuationAmount(awardData.getQuationAmount().toString());
		emailBean.setAwardAmount(awardData.getAwardAmount().toString());
		emailBean.setDays(awardData.getDays());
		emailBean.setQuationRate(awardData.getQuationRate());
		emailBean.setBatchNo(awardData.getBatchNo());
		emailBean.setRejectReason(rejectReason1);
		emailBean.setReturnTime(nowTime);
		emailBean.setReturnPeople(userDetails.getUsername()+":"+userDetails.getViewUserName());
		mailService.sendMail(SystemInfo.MAIL_TYPE_AWARD_DATA_RETURN, emailBean, roleId1,userDetails.getBranchId());
		
		return awardData;
	}

}
