package com.uxb2b.ecp.daemon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q01RqBean;
import com.uxb2b.ecp.business.IDownloadTenderDataBusiness;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 下載標單資料排程
 * @author steve
 *
 */
public class DownloadTenderData {
	
	private Logger log = LoggerFactory.getLogger(DownloadTenderData.class);
	 
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	IDownloadTenderDataBusiness iDownloadTenderDataBusiness;
	
	public void process() {
		log.info("下載標單資料開始");
		downloadTender();
		log.info("下載標單資料結束");
	}
	
	public void downloadTender(){
		
		try {
			
			List<Enterprise> enterpriseList =enterpriseRepository.findAll();
			
			enterpriseList.forEach(enterprise->{
				String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
				Q01RqBean q01RqBean = new Q01RqBean(SystemInfo.TXID_DOWNLOAD_TENDER, currentPoint, systemInfo.getStaticParameter().get(SystemInfo.BANK_CODE), enterprise.getUni());
				Gson gson = new Gson();
				String q01Json=gson.toJson(q01RqBean);
				String responseString =iSendMessagePersist.daemonMessage(enterprise.getWsUrl()+SystemInfo.TXID_DOWNLOAD_TENDER, q01Json);
				iDownloadTenderDataBusiness.processQ01RS(q01Json,responseString);
			});

		} catch (Exception e) {
			log.error("下載標單資料失敗 :"+e);
			e.printStackTrace();
		}
	}

}
	