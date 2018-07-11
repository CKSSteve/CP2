package com.uxb2b.ecp.daemon;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.T01RsBean;
import com.uxb2b.ecp.bean.swift.T02RsBean;
import com.uxb2b.ecp.bean.swift.T05RsBean;
import com.uxb2b.ecp.business.IResendSwiftBusiness;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.persist.IEnterpriseRepositoryPersist;
import com.uxb2b.ecp.persist.IPassRecordRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.service.SystemInfo;

public class SwiftResendDaemon {

	private Logger log = LoggerFactory.getLogger(SwiftResendDaemon.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IPassRecordRepositoryPersist iPassRecordRepositoryPersist;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	IEnterpriseRepositoryPersist iEnterpriseRepositoryPersist;
	
	@Autowired
	IResendSwiftBusiness iResendSwiftBusiness;
	public void process() {
		log.info("重送電文開始");
		SwiftResend();
		log.info("重送電文結束");
	}
	
	public void SwiftResend(){
		
		List<PassRecord> passRecordList = iPassRecordRepositoryPersist.findPassRecordByStatus(SystemInfo.PASS_RECORD_STATUS_RESEND);

		passRecordList.forEach(passRecord->{		
			passRecord.setStatus(SystemInfo.PASS_RECORD_STATUS_SENDING);
			iPassRecordRepositoryPersist.savePassRecord(passRecord);
		});
		
		iResendSwiftBusiness.resendSwiftMain(passRecordList);
	}
	
}
