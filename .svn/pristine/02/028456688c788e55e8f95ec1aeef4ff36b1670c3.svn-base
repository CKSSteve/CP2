package com.uxb2b.ecp.daemon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q05RqBean;
import com.uxb2b.ecp.business.IDownloadEntrustStatementDocBusiness;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.service.SystemInfo;


/**
 * 
 * 下載本票委請書及聲明書排程
 * @author steve
 *
 */
public class DownloadEntrustStatementDoc {

	private Logger log = LoggerFactory.getLogger(DownloadEntrustStatementDoc.class);
	
	@Autowired
	ICpDataRepositoryPersist iCpDataRepositoryPersist;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	IDownloadEntrustStatementDocBusiness iDownloadEntrustStatementDocBusiness;
	
	public void process() {
		log.info("下載本票委請書及聲明書開始");
		downloadEntrustStatement();
		log.info("下載本票委請書及聲明書結束");
	}
	
	public void downloadEntrustStatement(){
		
		try {			
			List<CpData> cpDataList=iCpDataRepositoryPersist.findAllCpData();
			cpDataList.forEach(cpData->{
				if(cpData.getEntrustDoc()==null&&cpData.getStatementDoc()==null){
					String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
					Q05RqBean q05RqBean = new Q05RqBean(SystemInfo.TXID_DOWNLOAD_ENTRUSTDOC_STATEMENTDOC, currentPoint, cpData.getBatchNo());
					Gson gson = new Gson();
					String q05Json=gson.toJson(q05RqBean);
					String wsUrl = enterpriseRepository.findByUni(cpData.getIssuerId()).getWsUrl();
					String responseString =iSendMessagePersist.daemonMessage(wsUrl+SystemInfo.TXID_DOWNLOAD_ENTRUSTDOC_STATEMENTDOC, q05Json);
					iDownloadEntrustStatementDocBusiness.processQ05RS(q05Json, responseString);
				}				
			});	
		} catch (Exception e) {
			log.error("下載本票委請書及聲明書失敗 :"+e);
			e.printStackTrace();	
		}
	}
}
