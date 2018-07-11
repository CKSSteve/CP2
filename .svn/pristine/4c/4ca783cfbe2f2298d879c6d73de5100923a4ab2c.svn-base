package com.uxb2b.ecp.daemon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.T01RqBean;
import com.uxb2b.ecp.bean.swift.T01RqitemsBean;
import com.uxb2b.ecp.business.IUploadTenderStatusBusiness;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.service.SystemInfo;


/**
 * 上傳報價單狀態排程
 * @author steve
 *
 */
public class UploadTenderStatus {

	private Logger log = LoggerFactory.getLogger(UploadTenderStatus.class);

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ICtbflbq1RepositoryPersist iCtbflbq1RepositoryPersist;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Autowired
	IUploadTenderStatusBusiness iUploadTenderStatusBusiness;
	
	public void process() {
		log.info("上傳報價單狀態開始");
		uploadTenderStatus();
		log.info("上傳報價單狀態結束");
	}
	
	public void uploadTenderStatus(){
		
		List<String> status =Arrays.asList("010","181","191","201");
		List<Enterprise> enterpriseList =enterpriseRepository.findAll();
		enterpriseList.forEach(enterprise->{
				List<Ctbflbq1> ctbflbq1List = iCtbflbq1RepositoryPersist.findCpDataByIssuerAndStatus(enterprise.getUni(), status);
			    T01RqBean t01RqBean = new T01RqBean();
			    t01RqBean.setTX_ID(SystemInfo.TXIDUPLOADTENDERSTATUS);
			    t01RqBean.setTX_TIME(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00");
			    List<T01RqitemsBean> itemsList = new ArrayList();
				Boolean sendMessage=false;
				for(Ctbflbq1 ctbflbq1 : ctbflbq1List){
				    List<String> statusList=Arrays.asList("181","191","201");
					if("010".equals(ctbflbq1.getStatus())&&"Y".equals(ctbflbq1.getUploadFlag1())){
						//不處理
					}else if(statusList.contains(ctbflbq1.getStatus())&&"Y".equals(ctbflbq1.getUploadFlag2())){
						//不處理
					}else{
						sendMessage=true;
						T01RqitemsBean t01RqitemsBean = new T01RqitemsBean(ctbflbq1.getTenderNo(), ctbflbq1.getQuationNo(), ctbflbq1.getStatus());
						itemsList.add(t01RqitemsBean);
					}			
				}
				if(sendMessage){
					t01RqBean.setItems(itemsList);
					Gson gson = new Gson();
					String t01Json = gson.toJson(t01RqBean);
					String responseString =iSendMessagePersist.daemonMessage(enterprise.getWsUrl()+SystemInfo.TXIDUPLOADTENDERSTATUS, t01Json);
					iUploadTenderStatusBusiness.processT01RS(t01Json,responseString);
				}
		});
	}
		
}
