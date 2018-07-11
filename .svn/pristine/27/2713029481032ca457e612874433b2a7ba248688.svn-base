package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q01RsBean;
import com.uxb2b.ecp.bean.swift.Q01RsItemsBean;
import com.uxb2b.ecp.business.IDownloadTenderDataBusiness;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.model.Ctbflbt2;
import com.uxb2b.ecp.model.Ctbflbt2PK;
import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.model.MessageInbox;
import com.uxb2b.ecp.model.StsLog;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbt1RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbt2RepositoryPersist;
import com.uxb2b.ecp.persist.IFlowFunctionRepositoryPersist;
import com.uxb2b.ecp.persist.IFlowStepRepositoryPersist;
import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.repository.StsLogRepository;
import com.uxb2b.ecp.service.SystemInfo;





@Component
public class DownloadTenderDataBusinessImpl implements IDownloadTenderDataBusiness{

	private Logger log = LoggerFactory.getLogger(DownloadTenderDataBusinessImpl.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	ICtbflbq1RepositoryPersist iCtbflbq1RepositoryPersist; 

	@Autowired
	ICtbflbt1RepositoryPersist iCtbflbt1RepositoryPersist;
	
	@Autowired
	ICtbflbt2RepositoryPersist iCtbflbt2RepositoryPersist;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	IFlowFunctionRepositoryPersist iFlowFunctionRepositoryPersist;
	
	@Autowired
	IFlowStepRepositoryPersist iFlowStepRepositoryPersist;
	
	@Autowired
	StsLogRepository stsLogRepository;
	
	@Autowired
	IMessageInboxRepositoryPersist iMessageInboxRepositoryPersist;
	
	@Autowired
	ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	@Override
	public void processQ01RS(String request,String response) {
		
		try{
			StsLog stsLog =new StsLog(SystemInfo.TXID_DOWNLOAD_TENDER, request, new Timestamp(new Date().getTime()));
			stsLog.setContentRs(response);
			stsLog.setStatus("Successful");
			stsLogRepository.save(stsLog);
			
			Gson gson = new Gson();
			Q01RsBean q01RsDown = gson.fromJson(response, Q01RsBean.class);
	
			if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(q01RsDown.getRETURN_CODE())){
				
				q01RsDown.getItems().forEach(q01Rs->{
				
					Ctbflbq1 checkCtbflbq1 = iCtbflbq1RepositoryPersist.findByQuationNo(q01Rs.getQUATION_NO());
					Ctbflbt1 checkCtbflbt1 = iCtbflbt1RepositoryPersist.findByTenderNo(q01Rs.getTENDER_NO());
					
					if(checkCtbflbt1==null){
						if(checkCtbflbq1==null){
							insertDB(q01Rs);
						}else{
							log.info("報價單號碼 :"+q01Rs.getQUATION_NO()+"重複，不處理!");
						}
						
					}else{			
						log.info("標單號碼 :"+q01Rs.getTENDER_NO()+"重複，不處理!");			
					}
				});
			}else{
				log.error("下載標單資料下行電文回傳錯誤 :"+q01RsDown.getRETURN_CODE()+" - "+q01RsDown.getRETURN_DESC());
				if("0001".equals(q01RsDown.getRETURN_CODE())){
					log.error("查無資料");
				}
			}	
		}catch (Exception e) {
			log.error("Q01下行電文處理失敗 :"+e);
			e.printStackTrace();
		}
	}
	
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void insertDB(Q01RsItemsBean q01RsBean){
		
		String issuerName=enterpriseRepository.findByUni(q01RsBean.getISSUER_ID()).getChineseName();
		
		String  currencyName= iSysCodeRepositoryPersist.findCodeValueByCodeTypeCodeKey(SystemInfo.SYSCODE_CODETYPE_CURRENCY, q01RsBean.getCURRENCY_ID());
		
		//INSERT CTBFLBT1
		Ctbflbt1 ctbflbt1 = new Ctbflbt1();
		ctbflbt1.setTenderNo(q01RsBean.getTENDER_NO());
		ctbflbt1.setIssuerId(q01RsBean.getISSUER_ID());
		ctbflbt1.setIssuerName(issuerName);
		ctbflbt1.setInviteDate(q01RsBean.getINVITE_DATE());
		ctbflbt1.setCloseDate(q01RsBean.getCLOSE_DATE());
		ctbflbt1.setCloseTime(q01RsBean.getCLOSE_TIME());
		ctbflbt1.setEffectDate(q01RsBean.getEFFECT_DATE());
		ctbflbt1.setLoanAmount(q01RsBean.getLOAN_AMOUNT());
		ctbflbt1.setCurrencyId(q01RsBean.getCURRENCY_ID());
		ctbflbt1.setCurrencyName(currencyName);
		ctbflbt1.setAmountUnit(q01RsBean.getAMOUNT_UNIT());
		ctbflbt1.setStatus(q01RsBean.getSTATUS());
		ctbflbt1.setContactUser(q01RsBean.getCONTACT_USER());
		ctbflbt1.setContactPhone(q01RsBean.getCONTACT_PHONE());
		ctbflbt1.setContactFax(q01RsBean.getCONTACT_FAX());
		ctbflbt1.setBankId(q01RsBean.getBANK_ID());
		ctbflbt1.setBranchId(q01RsBean.getBRANCH_ID());
		
		iCtbflbt1RepositoryPersist.saveCtbflbt1(ctbflbt1);
		//INSERT CTBFLBT2
		q01RsBean.getItems().forEach(items->{
			Ctbflbt2 ctbflbt2 = new Ctbflbt2();
			Ctbflbt2PK ctbflbt2PK =new Ctbflbt2PK();
			ctbflbt2PK.setTenderNo(q01RsBean.getTENDER_NO());
			ctbflbt2PK.setItemNo(items.getITEM_NO());
			ctbflbt2.setId(ctbflbt2PK);
			ctbflbt2.setExpiredDate(items.getEXPIRED_DATE());
			ctbflbt2.setDays(items.getDAYS());
			ctbflbt2.setLoanAmount(items.getLOAN_AMOUNT());
			iCtbflbt2RepositoryPersist.saveCtbflbt2(ctbflbt2);
		});
		
		
		
		//INSERT CTBFLBQ1
		int flowId = iFlowFunctionRepositoryPersist.findFlowId(SystemInfo.USER_LOG_FUNCTION_ID_QUOTION_ENTRY);
		List<FlowStep> flowStepList = iFlowStepRepositoryPersist.findFlowStepByFlowId(flowId);
		int roleId = 0;
		
		for(FlowStep flowStep:flowStepList){		
			if(flowStep.getId().getStepNo()==1)
				roleId=flowStep.getRoleId();
		}
		
		Ctbflbq1 ctbflbq1 = new Ctbflbq1();
		ctbflbq1.setQuationNo(q01RsBean.getQUATION_NO());
		ctbflbq1.setTenderNo(q01RsBean.getTENDER_NO());
		ctbflbq1.setIssuerId(q01RsBean.getISSUER_ID());
		ctbflbq1.setIssuerName(issuerName);
		ctbflbq1.setEffectDate(q01RsBean.getEFFECT_DATE());
		ctbflbq1.setLoanAmount(q01RsBean.getLOAN_AMOUNT());
		ctbflbq1.setCurrencyId(q01RsBean.getCURRENCY_ID());
		ctbflbq1.setCurrencyName(currencyName);
		ctbflbq1.setAmountUnit(q01RsBean.getAMOUNT_UNIT());
		ctbflbq1.setStatus("010");
		ctbflbq1.setContactUser(q01RsBean.getCONTACT_USER());
		ctbflbq1.setContactPhone(q01RsBean.getCONTACT_PHONE());
		ctbflbq1.setContactFax(q01RsBean.getCONTACT_FAX());
		ctbflbq1.setBankId(q01RsBean.getBANK_ID());
		ctbflbq1.setBranchId(q01RsBean.getBRANCH_ID());
		ctbflbq1.setApproveStatus("0");
		ctbflbq1.setFlowId(flowId);
		ctbflbq1.setStepNo(1);
		ctbflbq1.setRoleId(roleId);
		if(flowStepList.size()==1){
			ctbflbq1.setFinalStep("Y");
		}else{
			ctbflbq1.setFinalStep("N");
		}
		
		
		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
		
		//INSERT MESSAGE_INBOX
		String content = "標單號碼:"+ctbflbt1.getTenderNo()+",發行人:"+issuerName+",邀標日期:"+ctbflbt1.getInviteDate(); 
		String subject = "已接收標單，待報價";
		MessageInbox messageInbox = new MessageInbox(ctbflbq1.getBranchId(), content, new Timestamp(new Date().getTime()), ctbflbq1.getRoleId(), "SYSTEM", subject);
		iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
		
	}
	
}
