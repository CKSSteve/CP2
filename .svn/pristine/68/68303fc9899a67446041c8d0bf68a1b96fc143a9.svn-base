package com.uxb2b.ecp.business.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.swift.Q01RsBean;
import com.uxb2b.ecp.bean.swift.Q02RqBean;
import com.uxb2b.ecp.bean.swift.Q02RsBean;
import com.uxb2b.ecp.bean.swift.Q02RsItemBean;
import com.uxb2b.ecp.business.IDownloadAwardDataBusiness;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.AwardDataPK;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.model.Ctbflbt2;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.MessageInbox;
import com.uxb2b.ecp.model.StsLog;
import com.uxb2b.ecp.persist.IAwardDataRepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbq2RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbt1RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbt2RepositoryPersist;
import com.uxb2b.ecp.persist.IFlowFunctionRepositoryPersist;
import com.uxb2b.ecp.persist.IFlowStepRepositoryPersist;
import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.repository.StsLogRepository;
import com.uxb2b.ecp.service.DateConvertService;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class DownloadAwardDataBusinessImpl implements IDownloadAwardDataBusiness{
	
	private Logger log = LoggerFactory.getLogger(DownloadAwardDataBusinessImpl.class);
	
	@Autowired
	private SystemInfo systemInfo;
	
	@Autowired
	private DateConvertService dateConvertService;

	@Autowired
	private ICtbflbq1RepositoryPersist iCtbflbq1RepositoryPersist;
	
	@Autowired
	private StsLogRepository stsLogRepository;
	
	@Autowired
	private ICtbflbq2RepositoryPersist iCtbflbq2RepositoryPersist;
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ICtbflbt1RepositoryPersist iCtbflbt1RepositoryPersist;
	
	@Autowired
	ICtbflbt2RepositoryPersist iCtbflbt2RepositoryPersist;
	
	@Autowired
	IFlowFunctionRepositoryPersist iFlowFunctionRepositoryPersist;
	
	@Autowired
	IFlowStepRepositoryPersist iFlowStepRepositoryPersist;
	
	@Autowired
	IAwardDataRepositoryPersist iAwardDataRepositoryPersist;
	
	@Autowired
	IMessageInboxRepositoryPersist iMessageInboxRepositoryPersist;
	
	@Autowired
	ISendMessagePersist iSendMessagePersist;
	
	@Override
	public List<Q02RsBean> sendQ02Rq() {
		String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
		//1.	由系統設定檔取得銀行代號
		String bankId = systemInfo.getStaticParameter().get(systemInfo.BANK_CODE);
		//2.	逐一讀取ENTERPRISE.WS_URL
		List<Enterprise> enterpriselist = enterpriseRepository.findAll();
		List<Q02RsBean> q02RsBeanList = new ArrayList<Q02RsBean>();
		Gson gson = new Gson();	
		try {
			for(Enterprise enterprise : enterpriselist){
				Q02RqBean q02RqBean = new Q02RqBean();
				q02RqBean.setTX_ID(systemInfo.TXID_DOWNLOAD_AWARDDATA);
				q02RqBean.setTX_TIME(currentPoint);
				q02RqBean.setBANK_ID(bankId);
				q02RqBean.setISSUER_ID(enterprise.getUni());
				String responseString =iSendMessagePersist.daemonMessage(enterprise.getWsUrl()+systemInfo.TXID_DOWNLOAD_AWARDDATA, gson.toJson(q02RqBean));
				Q02RsBean q02Rs = gson.fromJson(responseString,Q02RsBean.class);
				q02RsBeanList.add(q02Rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return q02RsBeanList;
	}
	
	@Override
	public boolean downloadAwardDataRsLogic(Q02RsBean response) throws Exception {
		//5.	UPDATE CTBFLBT1 (by TENDER_NO)
		Ctbflbt1 ctbflbt1 = iCtbflbt1RepositoryPersist.findByTenderNo(response.getTENDER_NO());
		ctbflbt1.setAwardDate(response.getAWARD_DATE());
		ctbflbt1.setAwardTime(response.getAWARD_TIME());
		ctbflbt1.setAwardAmount(new BigDecimal(response.getAWARD_AMOUNT1()));
		ctbflbt1.setAwardRate(new BigDecimal(response.getAWARD_RATE1()));
		ctbflbt1.setAwardRateAvg(new BigDecimal(response.getAWARD_RATE_AVG1()));
		ctbflbt1.setQuationRateAvg(new BigDecimal(response.getQUATION_RATE_AVG()));
		ctbflbt1.setStatus(response.getSTATUS1());
		iCtbflbt1RepositoryPersist.saveCtbflbt1(ctbflbt1);
		
		//6.	UPDATE CTBFLBT2(可能有多筆) (by TENDER_NO+DAYS)
		for(Q02RsItemBean q02RsItemBean : response.getItem()){
			Ctbflbt2 ctbflbt2 = iCtbflbt2RepositoryPersist.findByIdTenderNoAndDays(response.getTENDER_NO(), Integer.parseInt(q02RsItemBean.getDAYS()));
			ctbflbt2.setAwardAmount(new BigDecimal(q02RsItemBean.getAWARD_AMOUNT3()));
			ctbflbt2.setAwardRate(new BigDecimal(q02RsItemBean.getAWARD_RATE3()));
			ctbflbt2.setAwardRateAvg(new BigDecimal(q02RsItemBean.getAWARD_RATE_AVG3()));
			iCtbflbt2RepositoryPersist.saveCtbflbt2(ctbflbt2);
		}
		
		//7.	UPDATE CTBFLBQ1(by QUATION_NO)
		Ctbflbq1 ctbflbq1 = iCtbflbq1RepositoryPersist.findByQuationNo(response.getQUATION_NO());
		ctbflbq1.setAwardDate(response.getAWARD_DATE());
		ctbflbq1.setAwardTime(response.getAWARD_TIME());
		ctbflbq1.setAwardAmount(new BigDecimal(response.getAWARD_AMOUNT2()));
		ctbflbq1.setAwardRate(new BigDecimal(response.getAWARD_RATE2()));
		ctbflbq1.setAwardRateAvg(new BigDecimal(response.getAWARD_RATE_AVG2()));
		if(response.getSTATUS2().equals("200")){
			ctbflbq1.setStatus("201");
		}else if(response.getSTATUS2().equals("190")){
			ctbflbq1.setStatus("191");
		}else{
			ctbflbq1.setStatus("181");
		}
		iCtbflbq1RepositoryPersist.saveCtbflbq1(ctbflbq1);
		
		//8.	CTBFLBQ2(可能有多筆) (by QUATION_NO+ITEM_NO)
		for(Q02RsItemBean q02RsItemBean : response.getItem()){
			Ctbflbq2 ctbflbq2 = iCtbflbq2RepositoryPersist.findByIdQuationNoAndIdItemNo(response.getQUATION_NO(), Integer.parseInt(q02RsItemBean.getITEM_NO()));
			ctbflbq2.setAwardAmount(new BigDecimal(q02RsItemBean.getAWARD_AMOUNT4()));
			ctbflbq2.setAwardRate(new BigDecimal(q02RsItemBean.getAWARD_RATE3()));
			ctbflbq2.setAwardRateAvg(new BigDecimal(q02RsItemBean.getAWARD_RATE_AVG3()));
			ctbflbq2.setAwardAmountTotal(new BigDecimal(q02RsItemBean.getAWARD_AMOUNT3()));
			if(q02RsItemBean.getSTATUS3().equals("200")){
				ctbflbq2.setStatus("201");
				//9.	若response電文STATUS3=’200’，以FUNCTION_ID=’B1’查詢FLOW_FUNCTION取得FLOW_ID，再以FLOW_ID讀取FLOW_STEP，然後INSERT AWARD_DATA
				AwardData awardData = new AwardData();
				AwardDataPK AwardDataPK = new AwardDataPK();
				AwardDataPK.setQuationNo(response.getQUATION_NO());
				AwardDataPK.setItemNo(Integer.parseInt(q02RsItemBean.getITEM_NO()));
				awardData.setId(AwardDataPK);
				awardData.setTenderNo(response.getTENDER_NO());
				awardData.setIssuerId(response.getISSUER_ID());
				awardData.setIssuerName(ctbflbq1.getIssuerName());
				awardData.setLoanType(q02RsItemBean.getLOAN_TYPE());
				awardData.setLoanAmount(ctbflbq2.getLoanAmount());
				awardData.setCurrencyId(ctbflbq1.getCurrencyId());
				awardData.setCurrencyName(ctbflbq1.getCurrencyName());
				awardData.setEffectDate(ctbflbq1.getEffectDate());
				awardData.setExpiredDate(ctbflbq2.getExpiredDate());
				awardData.setAmountUnit(ctbflbq1.getAmountUnit());
				awardData.setStatus(q02RsItemBean.getSTATUS3());
				awardData.setDays(Integer.parseInt(q02RsItemBean.getDAYS()));
				awardData.setAwardDate(response.getAWARD_DATE());
				awardData.setAwardTime(response.getAWARD_TIME());
				awardData.setBankId(response.getBANK_ID());
				awardData.setBranchId(response.getBRANCH_ID());
				awardData.setAmountUnit(ctbflbq1.getAmountUnit());
				awardData.setQuationAmount(new BigDecimal(q02RsItemBean.getQUATION_AMOUNT()));
				awardData.setQuationRate(new BigDecimal(q02RsItemBean.getQUATION_RATE()));
				awardData.setAwardAmount(new BigDecimal(q02RsItemBean.getAWARD_AMOUNT4()));
				awardData.setApproveStatus1("0");
				awardData.setFlowId1(iFlowFunctionRepositoryPersist.findFlowId("B1"));
				awardData.setStepNo1(1);
				awardData.setRoleId1(iFlowStepRepositoryPersist.findRoleIdByFlowIdAndStepNo(iFlowFunctionRepositoryPersist.findFlowId("B1"), 1));
				awardData.setFinalStep1(iFlowStepRepositoryPersist.findFlowStepByFlowId(iFlowFunctionRepositoryPersist.findFlowId("B1")).size() > 1 ? "N" : "Y");
				iAwardDataRepositoryPersist.saveAwardData(awardData);
				
				//10.	INSERT MESSAGE_INBOX
				MessageInbox messageInbox = new MessageInbox();
				messageInbox.setBranchId(ctbflbq1.getBranchId());
				messageInbox.setSender("SYSTEM");
				messageInbox.setReceiverRole(awardData.getRoleId1());
				messageInbox.setSubject("已接收決標單，待確認(登錄票券批號)");
				messageInbox.setContent("報價單號碼:"+ ctbflbq1.getQuationNo() +",發行人:"+ ctbflbq1.getIssuerName() +",決標日期:"+ ctbflbq1.getAwardDate());
				messageInbox.setMessageTime(dateConvertService.DateToTimestamp(new Date()));
				iMessageInboxRepositoryPersist.saveMessageInbox(messageInbox);
				
			}else if(q02RsItemBean.getSTATUS3().equals("190")){
				ctbflbq2.setStatus("191");
			}else{
				ctbflbq2.setStatus("181");
			}
			iCtbflbq2RepositoryPersist.saveCtbflbq2(ctbflbq2);
		}
		return false;
	}



	@Override
	public void vaildQ02RsData(String request,String response) {
		try{
			//INSERT StsLog
			StsLog stsLog = new StsLog();
			stsLog.setTxId(systemInfo.TXID_DOWNLOAD_AWARDDATA);
			stsLog.setContentRq(request);
			stsLog.setContentRs(response);
			stsLog.setLogTime(dateConvertService.DateToTimestamp(new Date()));
			stsLog.setStatus("Successful");
			stsLogRepository.save(stsLog);
			
			Gson gson = new Gson();
			Q02RsBean q02RsDown = gson.fromJson(response, Q02RsBean.class);
			
			//(1)	若response電文無資料或有錯誤
			if("0001".equals(q02RsDown.getRETURN_CODE())){
				log.info("查無資料");
			}else if("0100".equals(q02RsDown.getRETURN_CODE())){
				log.info("電文格式錯誤");
			}else if("0198".equals(q02RsDown.getRETURN_CODE())){
				log.info("資料庫處理異常");
			}else if("0199".equals(q02RsDown.getRETURN_CODE())){
				log.info("其他錯誤");
			}else{
				//(2)	若response電文QUATION_NO查詢CTBFLBQ1，若無資料log印出 “無此報價單號碼:xxxx不處理!
				if(iCtbflbq1RepositoryPersist.findByQuationNo(q02RsDown.getQUATION_NO()) == null){
					log.info("無此報價單號碼: "+ q02RsDown.getQUATION_NO() +" 不處理!");
				}
				//(3)	以response電文QUATION_NO+ITEM_NO查詢CTBFLBQ2，若無資料log印出 “標單號碼＋項次:QUATION_NO+ITEM_NO，不處理!”
				for(Q02RsItemBean q02RsItemBean : q02RsDown.getItem()){
					if(iCtbflbq2RepositoryPersist.findByIdQuationNoAndIdItemNo(q02RsDown.getQUATION_NO(), Integer.valueOf(q02RsItemBean.getITEM_NO())) == null ){
						log.info("標單號碼＋項次: "+q02RsDown.getQUATION_NO() + q02RsItemBean.getITEM_NO() + " 不處理!");
					}else{
						downloadAwardDataRsLogic(q02RsDown);
					}
				}
			}
		}catch(Exception e){
			log.error("Q02下行電文處理失敗 :"+e);
			e.printStackTrace();
		}
	}
}
