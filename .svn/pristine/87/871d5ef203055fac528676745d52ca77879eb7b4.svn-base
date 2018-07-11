package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q03RsBean;
import com.uxb2b.ecp.bean.swift.T01RqBean;
import com.uxb2b.ecp.bean.swift.T01RsBean;
import com.uxb2b.ecp.business.IUploadTenderStatusBusiness;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.StsLog;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.repository.StsLogRepository;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class UploadTenderStatusBusinessImpl implements IUploadTenderStatusBusiness{

	private Logger log = LoggerFactory.getLogger(UploadTenderStatusBusinessImpl.class);

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	ICtbflbq1RepositoryPersist iCtbflbq1RepositoryPersist;
	
	@Autowired
	StsLogRepository stsLogRepository;
	
	@Override
	public void processT01RS(String request, String response) {
		
		try {
			
			StsLog stsLog =new StsLog(SystemInfo.TXIDUPLOADTENDERSTATUS, request, new Timestamp(new Date().getTime()));
			stsLog.setContentRs(response);
			stsLog.setStatus("Successful");
			stsLogRepository.save(stsLog);
			
			Gson gson = new Gson();
			T01RsBean t01Rs = gson.fromJson(response,T01RsBean.class);
			T01RqBean t01Rq = gson.fromJson(response,T01RqBean.class);
			if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(t01Rs.getRETURN_CODE())){

				t01Rq.getItems().forEach(items->{
					List<String> statusList=Arrays.asList("181","191","201");
					Ctbflbq1 ctbflbq1 = iCtbflbq1RepositoryPersist.findCtbflbq1ByTenderNo(items.getTENDER_NO());
					if("010".equals(ctbflbq1.getStatus())){
						ctbflbq1.setUploadFlag1("Y");
					}else if(statusList.contains(ctbflbq1.getStatus())){
						ctbflbq1.setUploadFlag2("Y");
					}
					iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
				});
	
			}else{
				log.error("上傳標單狀態下行電文回傳錯誤 :"+t01Rs.getRETURN_CODE()+" - "+t01Rs.getRETURN_DESC());
				if("0001".equals(t01Rs.getRETURN_CODE())){
					log.error("查無資料");
				}
			}
		} catch (Exception e) {
			log.error("T01下行電文處理失敗 :"+e);
			e.printStackTrace();
		}
			
	}
}
