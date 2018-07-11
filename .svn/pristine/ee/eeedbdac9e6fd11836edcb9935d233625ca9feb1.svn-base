package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.T01RsBean;
import com.uxb2b.ecp.business.IResendSwiftBusiness;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.persist.IEnterpriseRepositoryPersist;
import com.uxb2b.ecp.persist.IPassRecordRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class ResendSwiftBusinessImpl implements IResendSwiftBusiness , Runnable{

	private Logger log = LoggerFactory.getLogger(RateDetailsBusinessImpl.class);
	
	private List<PassRecord> resendList = new ArrayList();
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IPassRecordRepositoryPersist iPassRecordRepositoryPersist;
	
	@Autowired
	IEnterpriseRepositoryPersist iEnterpriseRepositoryPersist;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Override
	public Page<PassRecord> getNeedResendSwift(int pageNumber,int pageSize) {
		
		List<String> statuslist = Arrays.asList(SystemInfo.PASS_RECORD_STATUS_FAIL,SystemInfo.PASS_RECORD_STATUS_RESEND);
		
		return iPassRecordRepositoryPersist.findPassRecordByStatus(statuslist, pageNumber, pageSize);
	}

	@Override
	public void resendSwiftThread(List<Long> passRecordIdSelect) {
		
		iPassRecordRepositoryPersist.updateStatusByRecordId(SystemInfo.PASS_RECORD_STATUS_SENDING, passRecordIdSelect);
		resendList=iPassRecordRepositoryPersist.findPassRecordByRecordId(passRecordIdSelect);
		new Thread(this).start();
	}
	
	public void resendSwiftMain(List<PassRecord> passRecordList){
		passRecordList.forEach(passRecord->{
			int resendTime=Integer.valueOf(passRecord.getReSendTimes())+1;
			try {
				passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_SENDING);
				passRecord.setPassTime(new Timestamp(new Date().getTime()));
				iPassRecordRepositoryPersist.savePassRecord(passRecord);
				String wsUrl=iEnterpriseRepositoryPersist.findByUni(passRecord.getIssuerId()).getWsUrl();
				String downSwiftJson=iSendMessagePersist.daemonMessage(wsUrl+passRecord.getTxId(), passRecord.getContent());
				Gson gson = new Gson();
				/*T01、T02、T03、T05下行電文規格一樣*/
				T01RsBean downSwift=gson.fromJson(downSwiftJson,T01RsBean.class);
				passRecord.setReSendTimes(String.valueOf(resendTime));
				if(!SystemInfo.RETURN_CODE_SUCCESSFUL.equals(downSwift.getRETURN_CODE())){
//					passRecord.setReSendReason(downSwift.getRETURN_DESC());
					passRecord.setReSendReason(systemInfo.swiftErrorMessage(downSwift.getRETURN_CODE()));
					if(resendTime<3){
						passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
					}else{
						passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_FAIL);
					}
				}else{
					passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_SUCCESS);	
					passRecord.setReSendReason("");
				}
			} catch (Exception e) {
				log.error("重送失敗 :"+e);
				passRecord.setReSendReason(e.getMessage());
				passRecord.setReSendTimes(String.valueOf(resendTime));
				if(resendTime<3){
					passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);
				}else{
					passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_FAIL);
				}
			}finally {
				try {
					iPassRecordRepositoryPersist.savePassRecord(passRecord);
				} catch (Exception e2) {
					log.error("PASS_RECORD UPDATE失敗");
				}
				
			}
		});
		
	}
	
	@Override
	public void run() {	
		resendSwiftMain(resendList);

	}

}
