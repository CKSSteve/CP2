package com.uxb2b.ecp.daemon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q04RqBean;
import com.uxb2b.ecp.business.IDownloadBatchRegisterDataBusiness;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.service.SystemInfo;


/**
 * 下載本票登錄資料排程
 * @author steve
 *
 */
public class DownloadBatchRegisterData {

	private Logger log = LoggerFactory.getLogger(DownloadBatchRegisterData.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	IDownloadBatchRegisterDataBusiness iDownloadBatchRegisterDataBusiness;
	
	public void process() {
		log.info("下載本票登錄資料開始");
		downloadBatchRegister();
		log.info("下載本票登錄資料結束");
	}
	
	public void downloadBatchRegister(){
		
		try {
			
			List<Enterprise> enterpriseList =enterpriseRepository.findAll();
			enterpriseList.forEach(enterprise->{
				String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
				Q04RqBean q04RqBean = new Q04RqBean(SystemInfo.TXID_DOWNLOAD_BATCH_REGISTER, currentPoint, systemInfo.getStaticParameter().get(SystemInfo.BANK_CODE), enterprise.getUni()); 
				Gson gson = new Gson();
				String q04Json=gson.toJson(q04RqBean);
				String responseString =iSendMessagePersist.daemonMessage(enterprise.getWsUrl()+SystemInfo.TXID_DOWNLOAD_BATCH_REGISTER, q04Json);
				iDownloadBatchRegisterDataBusiness.processQ04RS(q04Json, responseString);				
			});								
		} catch (Exception e) {	
			log.error("下載本票登錄資料失敗 :"+e);
			e.printStackTrace();			
		}				
	}
}
