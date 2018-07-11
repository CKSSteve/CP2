package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q01RsBean;
import com.uxb2b.ecp.bean.swift.Q05RsBean;
import com.uxb2b.ecp.business.IDownloadEntrustStatementDocBusiness;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.StsLog;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.repository.StsLogRepository;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class DownloadEntrustStatementDocBusinessImpl implements IDownloadEntrustStatementDocBusiness{

	private Logger log = LoggerFactory.getLogger(DownloadEntrustStatementDocBusinessImpl.class);

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	ICpDataRepositoryPersist iCpDataRepositoryPersist;
	
	@Autowired
	StsLogRepository stsLogRepository;

	@Override
	public void processQ05RS(String request, String response) {
		
		try {
			
			StsLog stsLog =new StsLog(SystemInfo.TXID_DOWNLOAD_ENTRUSTDOC_STATEMENTDOC, request, new Timestamp(new Date().getTime()));
			stsLog.setContentRs(response);
			stsLog.setStatus("Successful");
			stsLogRepository.save(stsLog);
			
			Gson gson = new Gson();
			Q05RsBean q05rs = gson.fromJson(response, Q05RsBean.class);
			
			if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(q05rs.getRETURN_CODE())){
				CpData cpData = iCpDataRepositoryPersist.findCpDataByBatchNo(q05rs.getBATCH_NO());
				if(cpData!=null){
					cpData.setEntrustDoc(q05rs.getENTRUST_DOC());
					cpData.setStatementDoc(q05rs.getENTRUST_DOC());
					iCpDataRepositoryPersist.savaCpData(cpData);
				}else{
					log.info("票券批號:"+q05rs.getBATCH_NO()+"不存在，不處理!");
				}				
			}else{
				log.error("下載本票委請書與聲明書下行電文回傳錯誤 :"+q05rs.getRETURN_CODE()+" - "+q05rs.getRETURN_DESC());
				if("0001".equals(q05rs.getRETURN_CODE())){
					log.error("查無資料");
				}
		
			}
		} catch (Exception e) {
			log.error("Q05下行電文處理失敗 :"+e);
			e.printStackTrace();
		}
		
	}
	
		
}
