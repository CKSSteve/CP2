package com.uxb2b.ecp.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q03RqBean;
import com.uxb2b.ecp.business.IDownloadBatchRegisterStatusBusiness;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 下載本票登錄狀態排程
 * @author steve
 *
 */
public class DownloadBatchRegisterStatus {

	private Logger log = LoggerFactory.getLogger(DownloadBatchRegisterStatus.class);
	
	@Autowired
	ICpDataRepositoryPersist iCpDataRepositoryPersist;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	IDownloadBatchRegisterStatusBusiness iDownloadBatchRegisterStatusBusiness;
	
	public void process() {
		log.info("下載本票登錄狀態開始");
		downloadBatchRegister();
		log.info("下載本票登錄狀態結束");
	}
	public void downloadBatchRegister(){
		
		try {
			List<Integer> status =Arrays.asList(1,5,7,9,11,12,18,20);
			List<CpData> cpDataList = iCpDataRepositoryPersist.findCpDataByStatusNot(status);
			cpDataList.forEach(cpData->{
				String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
				Q03RqBean q03RqBean = new Q03RqBean(SystemInfo.TXID_DOWNLOAD_BATCH_REGISTER_STATUS, currentPoint,cpData.getBankId(), cpData.getBatchNo()); 
				Gson gson = new Gson();
				String q03Json=gson.toJson(q03RqBean);
				String wsUrl = enterpriseRepository.findByUni(cpData.getIssuerId()).getWsUrl();
				String responseString =iSendMessagePersist.daemonMessage(wsUrl+SystemInfo.TXID_DOWNLOAD_BATCH_REGISTER_STATUS, q03Json);
				iDownloadBatchRegisterStatusBusiness.processQ03RS(q03Json, responseString);
			});
			
		} catch (Exception e) {
			log.error("下載本票登錄狀態失敗 :"+e);
			e.printStackTrace();
		}
		
	}
}
