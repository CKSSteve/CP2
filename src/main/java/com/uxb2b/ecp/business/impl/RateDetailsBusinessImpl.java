package com.uxb2b.ecp.business.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.EmailValueBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.bean.swift.T01RsBean;
import com.uxb2b.ecp.bean.swift.T05RsBean;
import com.uxb2b.ecp.business.IRateDetailsBusiness;
import com.uxb2b.ecp.exception.CertServerException;
import com.uxb2b.ecp.exception.RestfulException;
import com.uxb2b.ecp.exception.SwiftException;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.MessageInbox;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.model.RejectReason;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.persist.IAwardDataRepositoryPersist;
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
public class RateDetailsBusinessImpl implements IRateDetailsBusiness{

	private Logger log = LoggerFactory.getLogger(RateDetailsBusinessImpl.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IAwardDataRepositoryPersist iAwardDataRepositoryPersist;
	
	@Autowired
	IFlowStepRepositoryPersist iFlowStepRepositoryPersist;
	
	@Autowired
	IMessageInboxRepositoryPersist iMessageInboxRepositoryPersist;
	
	@Autowired
	ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	@Autowired
	IJsonMessagePersist iJsonMessagePersist;
	
	@Autowired
	IPassRecordRepositoryPersist iPassRecordRepositoryPersist;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	RejectReasonRepository rejectReasonRepository;
	
	@Autowired
	DateConvertService dateConvertService;
	
	@Autowired
	MailService mailService;
	
	@Override
	public Page<AwardData> getRateDetailsRegisterInventory(String branchId, int roleId,int pageNumber,int pageSize) {
		
		List<String> approveStatus2=Arrays.asList("0","1");
		
		return iAwardDataRepositoryPersist.findAwardDataByBranchIdAndRoleId2AndApproveStatus2In(branchId, roleId, approveStatus2, pageNumber, pageSize);
	}

	@Override
	public AwardData getRateDetails(String quationNo, int itemNo) {
		
		return iAwardDataRepositoryPersist.findAwardDataByQuationNoAndItemNo(quationNo, itemNo);
	}

	@Override
//	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void doRegister(String quationNo, String itemNo, String deliveryRate, String underwritingRate,
			String visaRate, String guaranteeRate,UserProfileBean userDetails) throws Exception {
		
		AwardData awardData = iAwardDataRepositoryPersist.findAwardDataByQuationNoAndItemNo(quationNo, Integer.valueOf(itemNo));
		
		Timestamp nowTime= new Timestamp(new Date().getTime());
		String finalStep=awardData.getFinalStep2();
		int nextRoleId = 0;
		//UPDATE AWARD_DATA
		if("Y".equals(finalStep)){
			awardData.setApproveStatus2("4");
			awardData.setReplyTime2(nowTime);
		}else if("N".equals(finalStep)){
			int maxStepNo =iFlowStepRepositoryPersist.getMaxStepNoByFlowId(awardData.getFlowId2());
			 nextRoleId =iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(awardData.getFlowId2(), awardData.getStepNo2()+1);
			
			
			if((awardData.getStepNo2()+1)==maxStepNo){	
				awardData.setApproveStatus2("3");
				awardData.setFinalStep2("Y");
			}else{
				awardData.setApproveStatus2("2");
			}		
			awardData.setStepNo2(awardData.getStepNo2()+1);	
			awardData.setRoleId2(nextRoleId);
		}	
		awardData.setLastModifyTime2(nowTime);
		awardData.setLastModifyUser2(userDetails.getUsername()+":"+userDetails.getViewUserName());
		awardData.setDeliveryRate(new BigDecimal(deliveryRate));
		awardData.setUnderwritingRate(new BigDecimal(underwritingRate));
		awardData.setVisaRate(new BigDecimal(visaRate));
		awardData.setGuaranteeRate(new BigDecimal(guaranteeRate));

		
		iAwardDataRepositoryPersist.saveAwardData(awardData);
		
		//INSERT MESSAGE_INBOX
		String timeFormat="yyyy-MM-dd HH:mm:ss";
		String content="票券批號 : "+awardData.getBatchNo()+",登錄日期 : "+dateConvertService.TimestampToString(nowTime,timeFormat);
		int receiverRole = 0;
		String subject = null;
		if("Y".equals(finalStep)){
			receiverRole=0;
			subject="本票帳務明細已同意";
		}else if("N".equals(finalStep)){
			receiverRole=nextRoleId;
			subject="本票帳務明細確認待審核";
		}
		
		MessageInbox messageInbox =new MessageInbox(userDetails.getBranchId(), content, nowTime, receiverRole, userDetails.getUsername(), subject);
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		if("Y".equals(finalStep)){
			
			//產生帳務明細登錄request電文內容
			log.info("帳務明細登錄發送電文");
			String t05Json=iJsonMessagePersist.makeT05Message(awardData, userDetails);
			log.info("T05上行電文 :"+t05Json);
			
			PassRecord passRecord = new PassRecord();
			passRecord.setTransactionNo(awardData.getBatchNo());
			passRecord.setTxId(SystemInfo.TXIDRATEDETAIL);
			passRecord.setTransactionDate(awardData.getReplyTime2());
			passRecord.setPassTime(new Timestamp(new Date().getTime()));
			passRecord.setContent(t05Json);
			passRecord.setBranchId(awardData.getBranchId());
			passRecord.setIssuerId(awardData.getIssuerId());
			passRecord.setReSendTimes("0");
			String url =enterpriseRepository.findByUni(awardData.getIssuerId()).getWsUrl();
			
			try{
							
				String responseString =iSendMessagePersist.sendMessage(url+SystemInfo.TXIDRATEDETAIL, t05Json, SystemInfo.TXIDRATEDETAIL);
				log.info("T05下行電文 :"+responseString);
				Gson gson = new Gson();
				T05RsBean t05Rs = gson.fromJson(responseString,T05RsBean.class);
				if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(t05Rs.getRETURN_CODE())){
					log.info("下行電文回傳成功");
				}else{
					
					log.error("T05下行電文回傳錯誤 : "+t05Rs.getRETURN_CODE()+" - "+t05Rs.getRETURN_DESC());
					passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
//					passRecord.setReSendReason(t05Rs.getRETURN_CODE()+" - "+t05Rs.getRETURN_DESC());
					passRecord.setReSendReason(systemInfo.swiftErrorMessage(t05Rs.getRETURN_CODE()));
					iPassRecordRepositoryPersist.savePassRecord(passRecord);
					throw new SwiftException("T05下行電文回傳錯誤 : "+t05Rs.getRETURN_CODE()+" - "+t05Rs.getRETURN_DESC());
				}					
			} catch (CertServerException e) {
				passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
				passRecord.setReSendReason(e.getMessage());
				iPassRecordRepositoryPersist.savePassRecord(passRecord);
				throw new CertServerException(e.getMessage());
			} catch (RestfulException e) {
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
				e.printStackTrace();
				passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
				passRecord.setReSendReason(e.getMessage());
				iPassRecordRepositoryPersist.savePassRecord(passRecord);
				throw new Exception(e.getMessage());
			}		
					
		}
		
	}

	@Override
	public Page<AwardData> getRateDetailsReviewInventory(String branchId, int roleId, int pageNumber, int pageSize) {
	
		 List<String> approveStatus2=Arrays.asList("2","3");
		
		return iAwardDataRepositoryPersist.findAwardDataByBranchIdAndRoleId2AndApproveStatus2In(branchId, roleId, approveStatus2, pageNumber, pageSize);
	}

	@Override
	public List<SysCode> findSysCodeByCodeType(String codeType) {
		
		return iSysCodeRepositoryPersist.findByIdCodeType(codeType);
	}

	@Override
	public List<RejectReason> findRateDetailsRejectReason() {
		
		return rejectReasonRepository.findByIdCodeTypeAndStatusOrderByIdCodeKeyAsc(SystemInfo.REJECT_REASON_CODE_TYPE_RAREDETAILS, SystemInfo.REJECT_REASON_STATUS_ON);
	}
	
	
	@Override
	public void doReturn(String quationNo,String itemNo,String reasonForReturnString, UserProfileBean userDetails) {
		
		AwardData awardData = iAwardDataRepositoryPersist.findAwardDataByQuationNoAndItemNo(quationNo, Integer.valueOf(itemNo));
		Timestamp nowTime= new Timestamp(new Date().getTime());
		//UPDATE AWARD_DATA
		awardData.setApproveStatus2("1");
		awardData.setStepNo2(1);
		int nexRoleId =iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(awardData.getFlowId1(), 1);
		awardData.setRoleId2(nexRoleId);
		awardData.setFinalStep2("N");
		awardData.setRejectReason2(reasonForReturnString);
		awardData.setLastModifyTime2(nowTime);
		awardData.setLastModifyUser2(userDetails.getUsername()+":"+userDetails.getViewUserName());
		
		
		iAwardDataRepositoryPersist.saveAwardData(awardData);
		
		String content="票券批號:"+awardData.getBatchNo()+",退回原因:"+reasonForReturnString;
		MessageInbox messageInbox =new MessageInbox(userDetails.getBranchId(), content, nowTime, awardData.getRoleId2(), userDetails.getUsername(), "本票帳務明細退回");
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
		//SEND MAIL
		EmailValueBean emailBean =new EmailValueBean();
		emailBean.setBatchNo(awardData.getBatchNo());
		emailBean.setIssuerId(awardData.getIssuerId());
		emailBean.setIssuerName(awardData.getIssuerName());
		emailBean.setEffectDate(awardData.getEffectDate());
		emailBean.setExpiredDate(awardData.getExpiredDate());
		emailBean.setLoanType(awardData.getLoanType().trim());
		emailBean.setDays(awardData.getDays());
		emailBean.setCurrencyName(awardData.getCurrencyName());
		emailBean.setLoanAmount(awardData.getLoanAmount());
		emailBean.setAmountUnit(awardData.getAmountUnit());
		emailBean.setQuationRate(awardData.getQuationRate());
		emailBean.setReturnTime(nowTime);
		emailBean.setReturnPeople(userDetails.getUsername()+":"+userDetails.getViewUserName());
		emailBean.setRejectReason(awardData.getRejectReason2());
		mailService.sendMail(SystemInfo.MAIL_TYPE_RATE_DETAIL_RETURN, emailBean, nexRoleId,userDetails.getBranchId());
	}

	@Override
//	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void doReview(String quationNo, String itemNo,UserProfileBean userDetails) throws Exception {
		
		AwardData awardData = iAwardDataRepositoryPersist.findAwardDataByQuationNoAndItemNo(quationNo, Integer.valueOf(itemNo));
		
		Timestamp nowTime= new Timestamp(new Date().getTime());
		String finalStep=awardData.getFinalStep2();
		int nextRoleId = 0;
		
		//UPDATE AWARD_DATA
				if("Y".equals(finalStep)){
					awardData.setApproveStatus2("4");
					awardData.setReplyTime2(nowTime);
				}else if("N".equals(finalStep)){
					int maxStepNo =iFlowStepRepositoryPersist.getMaxStepNoByFlowId(awardData.getFlowId2());
					nextRoleId =iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(awardData.getFlowId2(), awardData.getStepNo2()+1);
					 
					if((awardData.getStepNo2()+1)==maxStepNo){	
						awardData.setApproveStatus2("3");
						awardData.setFinalStep2("Y");
					}else{
						awardData.setApproveStatus2("2");
					}
					
					awardData.setStepNo2(awardData.getStepNo2()+1);
					awardData.setRoleId2(nextRoleId);				 
				}
				
				awardData.setLastModifyTime2(nowTime);
				awardData.setLastModifyUser2(userDetails.getUsername()+":"+userDetails.getViewUserName());
				
				//INSERT MESSAGE_INBOX
				String timeFormat="yyyy-MM-dd HH:mm:ss";
				String content="票券批號 : "+awardData.getBatchNo()+",審核日期 : "+dateConvertService.TimestampToString(nowTime,timeFormat);
				int receiverRole = 0;
				String subject = null;
				if("Y".equals(finalStep)){
					receiverRole=0;
					subject="本票帳務明細已同意";
				}else if("N".equals(finalStep)){
					receiverRole=nextRoleId;
					subject="本票帳務明細確認待審核";
				}
				
				MessageInbox messageInbox =new MessageInbox(userDetails.getBranchId(), content, nowTime, receiverRole, userDetails.getUsername(), subject);
				iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
				
				if("Y".equals(finalStep)){
					
					//產生帳務明細審核request電文內容
					log.info("帳務明細審核發送電文");
					String t05Json=iJsonMessagePersist.makeT05Message(awardData, userDetails);
					log.info("T05上行電文 :"+t05Json);
					
					PassRecord passRecord = new PassRecord();
					passRecord.setTransactionNo(awardData.getBatchNo());
					passRecord.setTxId(SystemInfo.TXIDRATEDETAIL);
					passRecord.setTransactionDate(awardData.getReplyTime2());
					passRecord.setPassTime(new Timestamp(new Date().getTime()));
					passRecord.setContent(t05Json);
					passRecord.setBranchId(awardData.getBranchId());
					passRecord.setIssuerId(awardData.getIssuerId());
					passRecord.setReSendTimes("0");
					String url =enterpriseRepository.findByUni(awardData.getIssuerId()).getWsUrl();
					
					try{
									
						String responseString =iSendMessagePersist.sendMessage(url+SystemInfo.TXIDRATEDETAIL, t05Json, SystemInfo.TXIDRATEDETAIL);
						log.info("T05下行電文 :"+responseString);
						Gson gson = new Gson();
						T05RsBean t05Rs = gson.fromJson(responseString,T05RsBean.class);
						if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(t05Rs.getRETURN_CODE())){
							log.info("下行電文回傳成功"); 
						}else{
							
							log.error("T05下行電文回傳錯誤 : "+t05Rs.getRETURN_CODE()+" - "+t05Rs.getRETURN_DESC());
							passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
//							passRecord.setReSendReason(t05Rs.getRETURN_CODE()+" - "+t05Rs.getRETURN_DESC());
							passRecord.setReSendReason(systemInfo.swiftErrorMessage(t05Rs.getRETURN_CODE()));
							iPassRecordRepositoryPersist.savePassRecord(passRecord);
							throw new SwiftException("T05下行電文回傳錯誤 : "+t05Rs.getRETURN_CODE()+" - "+t05Rs.getRETURN_DESC());
						}
					
					}catch (CertServerException e) {
						passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
						passRecord.setReSendReason(e.getMessage());
						iPassRecordRepositoryPersist.savePassRecord(passRecord);
						throw new CertServerException(e.getMessage());
					} catch (RestfulException e) {
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
						e.printStackTrace();
						passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
						passRecord.setReSendReason(e.getMessage());
						throw new Exception(e.getMessage());
					}		
					iPassRecordRepositoryPersist.savePassRecord(passRecord);
				}	
	}
	
		
}
