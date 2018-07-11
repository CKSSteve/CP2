package com.uxb2b.ecp.daemon;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q02RqBean;
import com.uxb2b.ecp.business.IDownloadAwardDataBusiness;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 下載決標資料排程
 * @author evayang
 *
 */
public class DownloadAwardData {
	
	private Logger log = LoggerFactory.getLogger(DownloadAwardData.class);
	
	@Autowired
	private SystemInfo systemInfo; 
	
	@Autowired
	private IDownloadAwardDataBusiness iDownloadAwardDataBusiness;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	public void process() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("[downloadAwardDataDaemon] Daemon Start：{}", sdf.format(new Date()));
		startDownloadAwardData();
		log.info("[downloadAwardDataDaemon] Daemon End：{}", sdf.format(new Date()));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void startDownloadAwardData() {
		
		try {	
			List<Enterprise> enterpriseList =enterpriseRepository.findAll();
			enterpriseList.forEach(enterprise->{
				String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
				//1.	由系統設定檔取得銀行代號
				String bankId = systemInfo.getStaticParameter().get(systemInfo.BANK_CODE);
				Gson gson = new Gson();
				Q02RqBean q02RqBean = new Q02RqBean();
				q02RqBean.setTX_ID(systemInfo.TXID_DOWNLOAD_AWARDDATA);
				q02RqBean.setTX_TIME(currentPoint);
				q02RqBean.setBANK_ID(bankId);
				q02RqBean.setISSUER_ID(enterprise.getUni());
				String responseString =iSendMessagePersist.daemonMessage(enterprise.getWsUrl()+systemInfo.TXID_DOWNLOAD_AWARDDATA, gson.toJson(q02RqBean));
				iDownloadAwardDataBusiness.vaildQ02RsData(gson.toJson(q02RqBean),responseString);
			});
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[downloadAwardDataDaemon] Exception：", e);
			
		}

    } 

}
