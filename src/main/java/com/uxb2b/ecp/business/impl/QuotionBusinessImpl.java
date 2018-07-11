package com.uxb2b.ecp.business.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.EmailValueBean;
import com.uxb2b.ecp.bean.QueryQuotesBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.bean.swift.T01RsBean;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.business.IQuotionBusiness;
import com.uxb2b.ecp.exception.CertServerException;
import com.uxb2b.ecp.exception.RestfulException;
import com.uxb2b.ecp.exception.SwiftException;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.model.Ctbflbq2PK;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.MessageInbox;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.model.RejectReason;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.persist.IBranchRepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbq2RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbt1RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbt2RepositoryPersist;
import com.uxb2b.ecp.persist.IFlowStepRepositoryPersist;
import com.uxb2b.ecp.persist.IJsonMessagePersist;
import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.persist.IPassRecordRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.repository.RejectReasonRepository;
import com.uxb2b.ecp.service.DateConvertService;
import com.uxb2b.ecp.service.MailService;
import com.uxb2b.ecp.service.SystemInfo;



@Component
public class QuotionBusinessImpl implements IQuotionBusiness{
	
	private Logger log = LoggerFactory.getLogger(QuotionBusinessImpl.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	@Autowired
	ICtbflbq1RepositoryPersist iCtbflbq1RepositoryPersist;
	
	@Autowired
	ICtbflbq2RepositoryPersist iCtbflbq2RepositoryPersist;
	
	@Autowired
	IBranchRepositoryPersist iBranchRepositoryPersist;
	
	@Autowired
	ICtbflbt1RepositoryPersist iCtbflbt1RepositoryPersist;
	
	@Autowired
	ICtbflbt2RepositoryPersist iCtbflbt2RepositoryPersist;

	@Autowired
	IMessageInboxRepositoryPersist iMessageInboxRepositoryPersist;
	
	@Autowired
	IFlowStepRepositoryPersist iFlowStepRepositoryPersist;
	
	@Autowired
	IJsonMessagePersist iJsonMessagePersist;
	
	@Autowired
	IPassRecordRepositoryPersist iPassRecordRepositoryPersist;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	RejectReasonRepository rejectReasonRepository;
	
	@Autowired
	DateConvertService dateConvertService;
	
	@Autowired
	MailService mailService;
	
	@Override
	public List<Enterprise> findAllEnterprise() {
		
		return enterpriseRepository.findAll();
	}

	@Override
	public List<SysCode> findSysCodeByCodeType(String codeType) {
		
		return iSysCodeRepositoryPersist.findByIdCodeType(codeType);
	}
	
	@Override
	public List<RejectReason> findQuotionRejectReason() {
		
		return rejectReasonRepository.findByIdCodeTypeAndStatusOrderByIdCodeKeyAsc(SystemInfo.REJECT_REASON_CODE_TYPE_QUOTION, SystemInfo.REJECT_REASON_STATUS_ON);
	}

	@Override
	public Page<Ctbflbq1> findQuotes(QueryQuotesBean quotesForm,int pageNumber,int pageSize) throws Exception {
		
		return iCtbflbq1RepositoryPersist.findQuotes(quotesForm, pageNumber, pageSize);
	}

	@Override
	public Page<Ctbflbq2> findQuotesDetails(int pageNumber, int pageSize,String quationNo) {
		
		return iCtbflbq2RepositoryPersist.findCtbflbq2ByQuationNo(pageNumber, pageSize,quationNo);
	}

	@Override
	public Ctbflbq1 findCtbflbq1ByQuationNo(String quationNo) {
		
		return iCtbflbq1RepositoryPersist.findByQuationNo(quationNo);
	}

	@Override
	public Ctbflbq2 findCtbflbq2ByQuationNoAndItemNo(String quationNo, int itemNo) {
		
		return iCtbflbq2RepositoryPersist.findByIdQuationNoAndIdItemNo(quationNo, itemNo);
	}

	@Override
	public Branch findBranchByBranchId(String branchId) {
		
		return iBranchRepositoryPersist.findBranchByBranchId(branchId);
	}

	@Override
	public Page<Ctbflbq1> findQuotionEntry(String branchId, int roleId,int pageNumber, int pageSize) {

		List<String> ApproveStatusList=new ArrayList<>();
		ApproveStatusList.add(SystemInfo.CTBFLBQ1_APPROVE_STATUS_TO_BE_REGISTER);
		ApproveStatusList.add(SystemInfo.CTBFLBQ1_APPROVE_STATUS_MANAGER_RETURNED);
		return iCtbflbq1RepositoryPersist.findByBranchIdAndRoleIdAndApproveStatus(branchId, roleId, ApproveStatusList, pageNumber, pageSize);
	}

	@Override
	public Ctbflbq1 findCtbflbq1ByTenderNo(String tenderNo) {
		
		return iCtbflbq1RepositoryPersist.findCtbflbq1ByTenderNo(tenderNo);
	}

	@Override
	public String quotionEntryCheckLoanAmount(String[] inputquotionMoneyArrays, String[] inputDaysArrays,
			Ctbflbq1 ctbflbq1) {
		
		Map<Integer,BigDecimal> checkMap =new HashMap();
		List<String> errorMsgList= new ArrayList();
		IntStream.range(0, inputquotionMoneyArrays.length).forEach(i->{
			if(checkMap.get(Integer.valueOf(inputDaysArrays[i]))!=null){
				checkMap.put(Integer.valueOf(inputDaysArrays[i]), checkMap.get(Integer.valueOf(inputDaysArrays[i])).add(new BigDecimal(inputquotionMoneyArrays[i])));
			}else{
				checkMap.put(Integer.valueOf(inputDaysArrays[i]), new BigDecimal(inputquotionMoneyArrays[i]));				  
			}
		});
			
		ctbflbq1.getCtbflbt1().getCtbflbt2().forEach(action->{
			if(checkMap.get(action.getDays())!=null){
				if(action.getLoanAmount().compareTo(checkMap.get(action.getDays()))==-1){ 	
					errorMsgList.add("["+action.getDays()+"]");
				}
			}
		 });
		
			return errorMsgList.stream().collect(Collectors.joining("、"));		
	}

	@Override
//	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void doNotQuotion(String tenderNo,UserProfileBean userDetails) throws Exception {
		

//		Ctbflbt1 ctbflbt1= iCtbflbt1RepositoryPersist.findByTenderNo(tenderNo);
		Ctbflbq1 ctbflbq1= iCtbflbq1RepositoryPersist.findCtbflbq1ByTenderNo(tenderNo);
		String finalStep=ctbflbq1.getFinalStep();
		
		//UPDATE CTBFLBT1
		/*2017-12-05 規格書變更*/
//		ctbflbt1.setStatus("090");
//		iCtbflbt1RepositoryPersist.saveCtbflbt1(ctbflbt1);
		
		//UPDATE CTBFLBQ1
		ctbflbq1.setStatus(SystemInfo.CTBFLBQ1_STATUS_DO_NOT_QUOTE);
		Timestamp nowTime= new Timestamp(new Date().getTime());
		int nextRoleId = 0;
		if("Y".equals(finalStep)){
			ctbflbq1.setApproveStatus("4");
		}else if("N".equals(finalStep)){
			int maxStepNo=iFlowStepRepositoryPersist.getMaxStepNoByFlowId(ctbflbq1.getFlowId());
			nextRoleId=iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(ctbflbq1.getFlowId(), ctbflbq1.getStepNo()+1);
			if((ctbflbq1.getStepNo()+1)==maxStepNo){
				ctbflbq1.setApproveStatus("3");
				ctbflbq1.setFinalStep("Y");
			}else{
				ctbflbq1.setApproveStatus("2");
			}
			
			ctbflbq1.setStepNo(ctbflbq1.getStepNo()+1);
			ctbflbq1.setRoleId(nextRoleId);	
		}
			ctbflbq1.setLastModifyTime(nowTime);
			ctbflbq1.setLastModifyUser(userDetails.getUsername()+":"+userDetails.getViewUserName());
			ctbflbq1.setQuationTime(nowTime);
			ctbflbq1.setQuationMethod("A");	
			iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
		
//			saveOrUpdateDatabase(ctbflbq1, ctbflbt1);
			
		//SAVE MESSAGE_INBOX
		String timeFormat="yyyy-MM-dd HH:mm:ss";
		String content="報價單號碼:"+ctbflbq1.getQuationNo()+",報價日期:"+dateConvertService.TimestampToString(nowTime,timeFormat);
		int receiverRole = 0;
		String subject = null;
		if("Y".equals(finalStep)){
			receiverRole=0;
			subject="報價單已送出";
		}else if("N".equals(finalStep)){
			receiverRole=nextRoleId;
			subject="報價單待審核";
		}
		MessageInbox messageInbox =new MessageInbox(userDetails.getBranchId(), content, new Timestamp(new Date().getTime()), receiverRole, userDetails.getUsername(), subject);
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		
		if("Y".equals(finalStep)){
			//產生報價 request電文內容
			log.info("不報價-發送電文");
			//組成JSON
			String t02Json=iJsonMessagePersist.makeT02Message(ctbflbq1, null, userDetails);
			log.info("T02上行電文 :"+t02Json);
			

			
			String url =enterpriseRepository.findByUni(ctbflbq1.getIssuerId()).getWsUrl();
			
			PassRecord passRecord = new PassRecord();
			passRecord.setTransactionNo(ctbflbq1.getQuationNo());
			passRecord.setTxId(SystemInfo.TXIDQUATION);
			passRecord.setTransactionDate(ctbflbq1.getQuationTime());
			passRecord.setPassTime(new Timestamp(new Date().getTime()));
			passRecord.setContent(t02Json);
			passRecord.setBranchId(ctbflbq1.getBranchId());
			passRecord.setIssuerId(ctbflbq1.getIssuerId());
			passRecord.setReSendTimes("0");
			
			try{
							
				String responseString =iSendMessagePersist.sendMessage(url+SystemInfo.TXIDQUATION, t02Json, SystemInfo.TXIDQUATION);
				log.info("T02下行電文 :"+responseString);
				Gson gson = new Gson();
				T01RsBean t01Rs = gson.fromJson(responseString,T01RsBean.class);
				if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(t01Rs.getRETURN_CODE())){
					log.info("下行電文回傳成功");
				}else{
					log.error("T02下行電文回傳錯誤 : "+t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
					passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
//					passRecord.setReSendReason(t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
					passRecord.setReSendReason(systemInfo.swiftErrorMessage(t01Rs.getRETURN_CODE()));
					iPassRecordRepositoryPersist.savePassRecord(passRecord);
					throw new SwiftException("T02下行電文回傳錯誤 : "+t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
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
		
	}

	@Override
//	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void doQuotion(String[] inputquotionMoneyArrays, String[] inputAllInArrays, String[] inputLoanTypeArrays,
			String[] inputDaysArrays, String tenderNo, String maxAmount, String extraAmount, String allInRate,
			String extraType,UserProfileBean userDetails) throws Exception {
		
//		Ctbflbt1 ctbflbt1= iCtbflbt1RepositoryPersist.findByTenderNo(tenderNo);
		Ctbflbq1 ctbflbq1= iCtbflbq1RepositoryPersist.findCtbflbq1ByTenderNo(tenderNo);
		int nextRoleId = 0;
		Timestamp nowTime= new Timestamp(new Date().getTime());
		String finalStep=ctbflbq1.getFinalStep();
		
		//UPDATE CTBFLBT1
		/*2017-12-05 規格書變更*/
//		ctbflbt1.setStatus("100");
//		iCtbflbt1RepositoryPersist.saveCtbflbt1(ctbflbt1);
		
		//UPDATE CTBFLBQ1
		ctbflbq1.setStatus(SystemInfo.CTBFLBQ1_STATUS_HAS_QUOTED);
		
		if("Y".equals(finalStep)){
			ctbflbq1.setApproveStatus("4");
		}else if("N".equals(finalStep)){
			int maxStepNo=iFlowStepRepositoryPersist.getMaxStepNoByFlowId(ctbflbq1.getFlowId());
			nextRoleId=iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(ctbflbq1.getFlowId(), ctbflbq1.getStepNo()+1);
			if((ctbflbq1.getStepNo()+1)==maxStepNo){
				ctbflbq1.setApproveStatus("3");
				ctbflbq1.setFinalStep("Y");
			}else{
				ctbflbq1.setApproveStatus("2");
			}		
			
			ctbflbq1.setStepNo(ctbflbq1.getStepNo()+1);
			ctbflbq1.setRoleId(nextRoleId);
		}
		ctbflbq1.setLastModifyTime(nowTime);
		ctbflbq1.setLastModifyUser(userDetails.getUsername()+":"+userDetails.getViewUserName());
		ctbflbq1.setMaxAmount(new BigDecimal(maxAmount));
		ctbflbq1.setExtraAmount(new BigDecimal(extraAmount));
		ctbflbq1.setExtraType(extraType);
		ctbflbq1.setExtraRate(new BigDecimal(allInRate));
//		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
		
		
//		iCtbflbq2RepositoryPersist.removeCtbflbq2ByQuationNo(ctbflbq1.getQuationNo());
		//SAVE CTBFLBQ2
		List<Ctbflbq2> ctbflbq2List = new ArrayList<>();
		IntStream.range(0, inputquotionMoneyArrays.length).forEach(i->{
			Ctbflbq2 ctbflbq2 = new Ctbflbq2();
			Ctbflbq2PK ctbflbq2PK =new Ctbflbq2PK(ctbflbq1.getQuationNo(),i+1);
			ctbflbq2.setId(ctbflbq2PK);
			ctbflbq2.setExpiredDate(iCtbflbt2RepositoryPersist.findExpiredDateByTenderNoAndDays(tenderNo, Integer.valueOf(inputDaysArrays[i])));
			ctbflbq2.setDays(Integer.valueOf(inputDaysArrays[i]));
			ctbflbq2.setLoanType(inputLoanTypeArrays[i]);
			/*2017-12-26 規格變更 : insert CTBFLBQ2時不須處理LOAN_AMOUNT */
//			ctbflbq2.setLoanAmount(ctbflbq1.getLoanAmount());
			ctbflbq2.setQuationAmount(new BigDecimal(inputquotionMoneyArrays[i]));
			ctbflbq2.setQuationRate(new BigDecimal(inputAllInArrays[i]));
				
			ctbflbq2List.add(ctbflbq2);
		});
//		iCtbflbq2RepositoryPersist.saveCtbflbq2(ctbflbq2List);
		
		saveOrUpdateDatabase(ctbflbq1, ctbflbq2List);
		
		//SAVE MESSAGE_INBOX
		String timeFormat="yyyy-MM-dd HH:mm:ss";
		String content="報價單號碼:"+ctbflbq1.getQuationNo()+",報價日期 :"+dateConvertService.TimestampToString(nowTime,timeFormat);
		int receiverRole = 0;
		String subject = null;
		if("Y".equals(finalStep)){
			receiverRole=0;
			subject="報價單已送出";
		}else if("N".equals(finalStep)){
			receiverRole=nextRoleId;
			subject="報價單待審核";
		}
		MessageInbox messageInbox =new MessageInbox(userDetails.getBranchId(), content, new Timestamp(new Date().getTime()), receiverRole, userDetails.getUsername(), subject);
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		
		if("Y".equals(finalStep)){
			
			log.info("報價-發送電文");
			//組成JSON
			String t02Json=iJsonMessagePersist.makeT02Message(ctbflbq1, ctbflbq2List, userDetails);
			log.info("T02上行電文 :"+t02Json);
			

			
			String url =enterpriseRepository.findByUni(ctbflbq1.getIssuerId()).getWsUrl();
			
			PassRecord passRecord = new PassRecord();
			passRecord.setTransactionNo(ctbflbq1.getQuationNo());
			passRecord.setTxId(SystemInfo.TXIDQUATION);
			passRecord.setTransactionDate(ctbflbq1.getQuationTime());
			passRecord.setPassTime(new Timestamp(new Date().getTime()));
			passRecord.setContent(t02Json);
			passRecord.setBranchId(ctbflbq1.getBranchId());
			passRecord.setIssuerId(ctbflbq1.getIssuerId());
			passRecord.setReSendTimes("0");
			
			try{
		
				String responseString =iSendMessagePersist.sendMessage(url+SystemInfo.TXIDQUATION, t02Json, SystemInfo.TXIDQUATION);
				log.info("T02下行電文 :"+responseString);
				Gson gson = new Gson();
				T01RsBean t01Rs = gson.fromJson(responseString,T01RsBean.class);
				if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(t01Rs.getRETURN_CODE())){
					log.info("下行電文回傳成功");
				}else{

					log.error("T02下行電文回傳錯誤 : "+t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
					passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
//					passRecord.setReSendReason(t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
					passRecord.setReSendReason(systemInfo.swiftErrorMessage(t01Rs.getRETURN_CODE()));
					iPassRecordRepositoryPersist.savePassRecord(passRecord);
					throw new SwiftException("T02下行電文回傳錯誤 : "+t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
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
		
	}

	@Override
	public Page<Ctbflbq1> findQuotionReview(String branchId, int roleId, int pageNumber, int pageSize) {
		
		List<String> ApproveStatusList=new ArrayList<>();
		ApproveStatusList.add("2");
		ApproveStatusList.add("3");
		return iCtbflbq1RepositoryPersist.findByBranchIdAndRoleIdAndApproveStatus(branchId, roleId, ApproveStatusList, pageNumber, pageSize);

	}

	@Override
	public List<Ctbflbq2> findCtbflbq2OrderByIdItemNo(String quationNo) {
		
		return iCtbflbq2RepositoryPersist.findByIdQuationNoOrderByIdItemNo(quationNo);
	}

	@Override
	public void doReturn(String tenderNo, String reasonForReturnString,UserProfileBean userDetails) {
		
		
		Ctbflbq1 ctbflbq1= iCtbflbq1RepositoryPersist.findCtbflbq1ByTenderNo(tenderNo);
		//UPDATE CTBFLBQ1
		
		/*2017-12-05 規格書新增*/
		ctbflbq1.setStatus(SystemInfo.CTBFLBQ1_STATUS_NOT_QUOTED);
		
		Timestamp nowTime= new Timestamp(new Date().getTime());
		ctbflbq1.setApproveStatus(SystemInfo.CTBFLBQ1_APPROVE_STATUS_MANAGER_RETURNED);
		ctbflbq1.setStepNo(1);
		ctbflbq1.setFinalStep("N");
		ctbflbq1.setRejectReason(reasonForReturnString);
		ctbflbq1.setLastModifyTime(nowTime);
		ctbflbq1.setLastModifyUser(userDetails.getUsername()+":"+userDetails.getViewUserName());
		
		int nexRoleId =iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(ctbflbq1.getFlowId(), 1);
		ctbflbq1.setRoleId(nexRoleId);
		
		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
		
		//SAVE MESSAGE_INBOX
		String content="報價單號碼:"+ctbflbq1.getQuationNo()+",退回原因:"+ctbflbq1.getRejectReason();
		MessageInbox messageInbox =new MessageInbox(userDetails.getBranchId(), content, new Timestamp(new Date().getTime()), ctbflbq1.getRoleId(), userDetails.getUsername(), "報價單退回");
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		//SEND MAIL
		EmailValueBean emailBean =new EmailValueBean();
		emailBean.setQuationNo(ctbflbq1.getQuationNo());
		emailBean.setTenderNo(tenderNo);
		emailBean.setInviteDate(ctbflbq1.getCtbflbt1().getInviteDate());
		emailBean.setCloseDate(ctbflbq1.getCtbflbt1().getCloseDate());
		emailBean.setCloseTime(StringUtils.substring(ctbflbq1.getCtbflbt1().getCloseTime(), 0, 8));
		emailBean.setCurrencyName(ctbflbq1.getCurrencyName());
		emailBean.setLoanAmount(ctbflbq1.getLoanAmount());
		emailBean.setAmountUnit(ctbflbq1.getAmountUnit());
		emailBean.setReturnTime(nowTime);
		emailBean.setReturnPeople(userDetails.getUsername()+":"+userDetails.getViewUserName());
		emailBean.setIssuerId(ctbflbq1.getIssuerId());
		emailBean.setIssuerName(ctbflbq1.getIssuerName());
		emailBean.setRejectReason(ctbflbq1.getRejectReason());
		mailService.sendMail(SystemInfo.MAIL_TYPE_TENDER_RETURN, emailBean, nexRoleId,userDetails.getBranchId());
	}

	@Override
//	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void doReview(String tenderNo,UserProfileBean userDetails) throws Exception {
		
		Ctbflbq1 ctbflbq1= iCtbflbq1RepositoryPersist.findCtbflbq1ByTenderNo(tenderNo);
		int nextRoleId = 0;
		Timestamp nowTime= new Timestamp(new Date().getTime());
		String finalStep=ctbflbq1.getFinalStep();
		//UPDATE CTBFLBQ1
				
		
		
		if("Y".equals(finalStep)){
			ctbflbq1.setApproveStatus("4");
		}else if("N".equals(finalStep)){
			int maxStepNo =iFlowStepRepositoryPersist.getMaxStepNoByFlowId(ctbflbq1.getFlowId());
			nextRoleId=iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(ctbflbq1.getFlowId(), ctbflbq1.getStepNo()+1);
			if((ctbflbq1.getStepNo()+1)==maxStepNo){
				ctbflbq1.setApproveStatus("3");
				ctbflbq1.setFinalStep("Y");
			}else{
				ctbflbq1.setApproveStatus("2");
			}
			
			ctbflbq1.setStepNo(ctbflbq1.getStepNo()+1);
			ctbflbq1.setRoleId(nextRoleId);
		}
		ctbflbq1.setQuationTime(nowTime);
		ctbflbq1.setLastModifyTime(nowTime);
		ctbflbq1.setLastModifyUser(userDetails.getUsername()+":"+userDetails.getViewUserName());
//		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
			
		saveOrUpdateDatabase(ctbflbq1);
		
		//SAVE MESSAGE_INBOX
		String timeFormat="yyyy-MM-dd HH:mm:ss";
		String content="報價單號碼:"+ctbflbq1.getQuationNo()+",審核日期:"+dateConvertService.TimestampToString(nowTime,timeFormat);
		int receiverRole = 0;
		String subject = null;
		if("Y".equals(finalStep)){
			receiverRole=0;
			subject="報價單已送出";
		}else if("N".equals(finalStep)){
			receiverRole=nextRoleId;
			subject="報價單待審核";
		}
		MessageInbox messageInbox =new MessageInbox(userDetails.getBranchId(), content, new Timestamp(new Date().getTime()), receiverRole, userDetails.getUsername(), subject);
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		if("Y".equals(finalStep)){
			
			log.info("報價-發送電文");
			//組成JSON
			List<Ctbflbq2> ctbflbq2List=iCtbflbq2RepositoryPersist.findByIdQuationNoOrderByIdItemNo(ctbflbq1.getQuationNo());
			String t02Json=iJsonMessagePersist.makeT02Message(ctbflbq1, ctbflbq2List, userDetails);
			log.info("T02上行電文 :"+t02Json);
			
			PassRecord passRecord = new PassRecord();
			passRecord.setTransactionNo(ctbflbq1.getQuationNo());
			passRecord.setTxId(SystemInfo.TXIDQUATION);
			passRecord.setTransactionDate(ctbflbq1.getQuationTime());
			passRecord.setPassTime(new Timestamp(new Date().getTime()));
			passRecord.setContent(t02Json);
			passRecord.setBranchId(ctbflbq1.getBranchId());
			passRecord.setIssuerId(ctbflbq1.getIssuerId());
			passRecord.setReSendTimes("0");
			
			String url =enterpriseRepository.findByUni(ctbflbq1.getIssuerId()).getWsUrl();
			
			try{
				
				String responseString =iSendMessagePersist.sendMessage(url+SystemInfo.TXIDQUATION, t02Json, SystemInfo.TXIDQUATION);
				log.info("T02下行電文 :"+responseString);
				Gson gson = new Gson();
				T01RsBean t01Rs = gson.fromJson(responseString,T01RsBean.class);
				if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(t01Rs.getRETURN_CODE())){
					log.info("下行電文回傳成功");
				}else{					
					log.error("T02下行電文回傳錯誤 : "+t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
					passRecord.setStatus("2");
					passRecord.setReSendReason(t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
					iPassRecordRepositoryPersist.savePassRecord(passRecord);
					throw new SwiftException("T02下行電文回傳錯誤 : "+t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
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
	}
	
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveOrUpdateDatabase(Ctbflbq1 ctbflbq1){
		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
	}
	
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveOrUpdateDatabase(Ctbflbq1 ctbflbq1,Ctbflbt1 ctbflbt1){
		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
		iCtbflbt1RepositoryPersist.saveCtbflbt1(ctbflbt1);
	}
	
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveOrUpdateDatabase(Ctbflbq1 ctbflbq1,List<Ctbflbq2> ctbflbq2List){
		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
		iCtbflbq2RepositoryPersist.removeCtbflbq2ByQuationNo(ctbflbq1.getQuationNo());
		iCtbflbq2RepositoryPersist.saveCtbflbq2(ctbflbq2List);
	}

}
