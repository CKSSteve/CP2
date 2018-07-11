package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q03RsBean;
import com.uxb2b.ecp.business.IDownloadBatchRegisterStatusBusiness;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.StsLog;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.repository.StsLogRepository;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class DownloadBatchRegisterStatusBusinessImpl implements IDownloadBatchRegisterStatusBusiness{

	private Logger log = LoggerFactory.getLogger(DownloadBatchRegisterStatusBusinessImpl.class);

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	ICpDataRepositoryPersist iCpDataRepositoryPersist;
	
	@Autowired
	StsLogRepository stsLogRepository;
	
	@Override
	public void processQ03RS(String request, String response) {
		
		try {
			
			StsLog stsLog =new StsLog(SystemInfo.TXID_DOWNLOAD_BATCH_REGISTER_STATUS, request, new Timestamp(new Date().getTime()));
			stsLog.setContentRs(response);
			stsLog.setStatus("Successful");
			stsLogRepository.save(stsLog);
			
			Gson gson = new Gson();
			Q03RsBean q03Rs = gson.fromJson(response,Q03RsBean.class);
			
			if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(q03Rs.getRETURN_CODE())){
				CpData cpData = iCpDataRepositoryPersist.findCpDataByBatchNo(q03Rs.getBATCH_NO());
				if(cpData!=null){
					cpData.setStatus(q03Rs.getSTATUS());
					iCpDataRepositoryPersist.savaCpData(cpData);
				}else{
					log.info("票券批號:"+q03Rs.getBATCH_NO()+"不存在，不處理!");
				}			
		
			}else{
				log.error("下載本票登錄狀態下行電文回傳錯誤 :"+q03Rs.getRETURN_CODE()+" - "+q03Rs.getRETURN_DESC());
				if("0001".equals(q03Rs.getRETURN_CODE())){
					log.error("查無資料");
				}
			}
		} catch (Exception e) {
			log.error("Q03下行電文處理失敗 :"+e);
			e.printStackTrace();
		}
			
	}
}
