package com.uxb2b.ecp.persist.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.exception.CertServerException;
import com.uxb2b.ecp.exception.RestfulException;
import com.uxb2b.ecp.model.StsLog;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.StsLogRepository;
import com.uxb2b.ecp.service.HttpService;
import com.uxb2b.ecp.service.RestfulClientService;
import com.uxb2b.ecp.service.SystemInfo;


@Repository
@Transactional
public class SendMessagePersistImpl implements ISendMessagePersist{

	private Logger log = LoggerFactory.getLogger(SendMessagePersistImpl.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	HttpService httpService;
	
	@Autowired
	RestfulClientService restfulClientService;
	
	@Autowired
	StsLogRepository stsLogRepository;
	
	public String sendMessage(String url,String jsonMessage,String txId)throws Exception{
		
		String responseString="";
		log.info("上行電文 :"+jsonMessage);
		StsLog stsLog= new StsLog(txId,jsonMessage,new Timestamp(new Date().getTime()));
		try{
			//呼叫CertServer加簽
			String pkcsJson =httpService.httpCert(systemInfo.getStaticParameter().get(SystemInfo.CERTSIGNURL), jsonMessage);
			//傳送電文
			responseString = restfulClientService.resfulClient(pkcsJson, url);
			log.info("下行電文 :"+responseString);
			stsLog.setContentRs(responseString);
			stsLog.setStatus("Successful");
		}catch (CertServerException e) {
			stsLog.setStatus("Fail");
			throw new CertServerException(e.getMessage());
		}catch (RestfulException e) {
			stsLog.setStatus("Fail");
			throw new RestfulException(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			stsLog.setStatus("Fail");
			log.error("傳送電文發生未知錯誤"+e.getMessage());
			throw new Exception(e.getMessage());
		}finally {
			stsLogRepository.save(stsLog);
			stsLogRepository.flush();
		}
		return responseString;
	}

	@Override
	public String daemonMessage(String url, String jsonMessage) {
		
		log.info("上行電文 :"+jsonMessage);
		String responseString="";
		//呼叫CertServer加簽
		try {
			

		String pkcsJson =httpService.httpCert(systemInfo.getStaticParameter().get(SystemInfo.CERTSIGNURL), jsonMessage);
		//傳送電文
		responseString = restfulClientService.resfulClient(pkcsJson, url);
		log.info("下行電文 :"+responseString);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("傳送電文失敗"+e.getMessage());
		}	
		return responseString;
		
	}
	
}
