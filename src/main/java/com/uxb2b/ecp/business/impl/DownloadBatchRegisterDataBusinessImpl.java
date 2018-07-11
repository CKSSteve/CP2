package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q04RsBean;
import com.uxb2b.ecp.bean.swift.Q04RsItemBean;
import com.uxb2b.ecp.business.IDownloadBatchRegisterDataBusiness;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.StsLog;
import com.uxb2b.ecp.persist.IAwardDataRepositoryPersist;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.StsLogRepository;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class DownloadBatchRegisterDataBusinessImpl implements IDownloadBatchRegisterDataBusiness{

	private Logger log = LoggerFactory.getLogger(DownloadBatchRegisterDataBusinessImpl.class);

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IAwardDataRepositoryPersist iAwardDataRepositoryPersist;
	
	@Autowired
	ICpDataRepositoryPersist iCpDataRepositoryPersist;
	
	@Autowired
	StsLogRepository stsLogRepository;
	
	@Autowired
	ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	@Override
	public void processQ04RS(String request, String response) {
		
		try {
			
			StsLog stsLog =new StsLog(SystemInfo.TXID_DOWNLOAD_BATCH_REGISTER, request, new Timestamp(new Date().getTime()));
			stsLog.setContentRs(response);				
			stsLog.setStatus("Successful");
			stsLogRepository.save(stsLog);
			
			Gson gson = new Gson();
			Q04RsBean q04Rs = gson.fromJson(response,Q04RsBean.class);
			
			if(SystemInfo.RETURN_CODE_SUCCESSFUL.equals(q04Rs.getRETURN_CODE())){
				q04Rs.getItems().forEach(cp2Item->{
					AwardData awardData =iAwardDataRepositoryPersist.findAwardDataByBatchNo(cp2Item.getBATCH_NO());
					if(awardData!=null){
						
						CpData cpData = iCpDataRepositoryPersist.findCpDataByBatchNo(cp2Item.getBATCH_NO());
						if(cpData!=null){
							log.info("票券批號:"+cp2Item.getBATCH_NO()+"已存在本票資料檔，刪除舊資料");
							iCpDataRepositoryPersist.deleteCpData(cpData);
						}
						String currencyName=iSysCodeRepositoryPersist.findCodeValueByCodeTypeCodeKey(SystemInfo.SYSCODE_CODETYPE_CURRENCY, cp2Item.getCURRENCY_ID());
						insertDB(cp2Item,currencyName);
						
					}else{
						log.info("票券批號:"+cp2Item.getBATCH_NO()+"不存在，不處理!!");
					}
				});
				
			}else{
				log.error("下載本票登錄資料下行電文回傳錯誤 :"+q04Rs.getRETURN_CODE()+" - "+q04Rs.getRETURN_DESC());
				if("0001".equals(q04Rs.getRETURN_CODE())){
					log.error("查無資料");
				}
				
			}
		} catch (Exception e) {
			log.error("Q04下行電文處理失敗 :"+e);
			e.printStackTrace();
		}
			
	}
	
	public void insertDB(Q04RsItemBean q04Rs,String currencyName){
		
		//INSERT CP_DATA
		CpData cpData =new CpData();
		cpData.setBatchNo(q04Rs.getBATCH_NO());
		cpData.setIssueNo(q04Rs.getISSUE_NO());
		cpData.setIssuerId(q04Rs.getISSUER_ID());
		cpData.setIssuerName(q04Rs.getISSUER_NAME());
		cpData.setLoanType(q04Rs.getLOAN_TYPE());
		cpData.setCurrencyId(q04Rs.getCURRENCY_ID());
		cpData.setCurrencyName(currencyName);
		cpData.setEffectDate(q04Rs.getEFFECT_DATE());
		cpData.setExpiredDate(q04Rs.getEXPIRED_DATE());
		cpData.setDays(q04Rs.getDAYS());
		cpData.setBankId(q04Rs.getBANK_ID());
		cpData.setBranchId(q04Rs.getBRANCH_ID());
		cpData.setPayer(q04Rs.getPAYER());
		cpData.setGuaranteeType(q04Rs.getGUARANTEE_TYPE());
		cpData.setGuarantorId(q04Rs.getGUARANTOR_ID());
		cpData.setGuarantorName(q04Rs.getGUARANTOR_NAME());
		cpData.setBrokerId(q04Rs.getBROKER_ID());
		cpData.setBrokerName(q04Rs.getBROKER_NAME());
		cpData.setEntrustDocId(q04Rs.getENTRUST_DOC_ID());;
		cpData.setStatementDocId(q04Rs.getSTATEMENT_DOC_ID());
		cpData.setStatus(q04Rs.getSTATUS());
		cpData.setPayDate(q04Rs.getPAY_DATE());
		cpData.setPatAmount(q04Rs.getPAT_AMOUNT());
		
		iCpDataRepositoryPersist.savaCpData(cpData);
	}
	
}
