package com.uxb2b.ecp.persist.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.bean.swift.T02Bean;
import com.uxb2b.ecp.bean.swift.T02ItemBean;
import com.uxb2b.ecp.bean.swift.T03RqBean;
import com.uxb2b.ecp.bean.swift.T03RqItemBean;
import com.uxb2b.ecp.bean.swift.T05RqBean;
import com.uxb2b.ecp.bean.swift.T05RqItemsBean;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.persist.IJsonMessagePersist;
import com.uxb2b.ecp.service.DateConvertService;
import com.uxb2b.ecp.service.SystemInfo;




@Repository
@Transactional
public class JsonMessagePersistImpl implements IJsonMessagePersist{
	
	String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
	
	@Autowired
	private DateConvertService dateConvertService;

	@Override
	public String makeT02Message(Ctbflbq1 ctbflbq1, List<Ctbflbq2> ctbflbq2,UserProfileBean userDetails) {
		T02Bean t02 =new T02Bean();
		
		t02.setTX_ID(SystemInfo.TXIDQUATION);
		t02.setTX_TIME(currentPoint);
		t02.setTENDER_NO(ctbflbq1.getTenderNo());
		t02.setBRANCH_ID(ctbflbq1.getBranchId());
		t02.setQUATION_NO(ctbflbq1.getQuationNo());
		t02.setQUATION_TIME(String.valueOf(ctbflbq1.getQuationTime()));
		t02.setMAX_AMOUNT(ctbflbq1.getMaxAmount());
		t02.setEXTRA_AMOUNT(ctbflbq1.getExtraAmount());
		t02.setEXTRA_TYPE(ctbflbq1.getExtraType());
		t02.setEXTRA_RATE(ctbflbq1.getExtraRate());
		t02.setQUATION_METHOD(ctbflbq1.getQuationMethod());
		t02.setSTATUS(ctbflbq1.getStatus());
		t02.setCREATE_USER(ctbflbq1.getLastModifyUser());
//		t02.setCREATE_DATE(StringUtils.replace(dateConvertService.TimestampToString(ctbflbq1.getLastModifyTime(), "YYYY-MM-dd"),"-",""));
		t02.setCREATE_DATE(dateConvertService.TimestampToString(ctbflbq1.getLastModifyTime(), "yyyyMMdd"));
		t02.setCREATE_TIME(dateConvertService.TimestampToString(ctbflbq1.getLastModifyTime(), "HH:MM:SS"));
		
		if(ctbflbq2!=null){
			List<T02ItemBean> list = new ArrayList<>();
			ctbflbq2.forEach(action->{
				T02ItemBean item = new T02ItemBean();
				item.setITEM_NO(String.valueOf(action.getId().getItemNo()));
				item.setEXPIRED_DATE(action.getExpiredDate());
				item.setDAYS(String.valueOf(action.getDays()));
				item.setLOAN_TYPE(action.getLoanType().trim());
				item.setQUATION_AMOUNT(action.getQuationAmount());
				item.setQUATION_RATE(action.getQuationRate());
				/*2017-12-26 規格變更:LOAN_AMOUNT 刪除*/
//				item.setLOAN_AMOUNT(action.getLoanAmount());
				list.add(item);
			});
			t02.setItems(list);
		}
		Gson gson = new Gson();
		//將物件轉成JSON
		return gson.toJson(t02);
	}


	@Override
	public String makeT03Message(AwardData awardData, UserProfileBean userDetails, String action) {
		
		T03RqBean t03 =new T03RqBean();
		
		t03.setTX_ID("T03");
		t03.setTX_TIME(currentPoint);
		t03.setTENDER_NO(awardData.getTenderNo());
		t03.setBRANCH_ID(awardData.getBankId());
		t03.setQUATION_NO(awardData.getId().getQuationNo());
		
		List<T03RqItemBean> t03Itemlist = new ArrayList<>();
		T03RqItemBean t03Item = new T03RqItemBean();
		t03Item.setITEM_NO(String.valueOf(awardData.getId().getItemNo()));
		t03Item.setEXPIRED_DATE(awardData.getExpiredDate());
		t03Item.setDAYS(String.valueOf(awardData.getDays()));
		t03Item.setSTATUS(awardData.getStatus());
		if("register".equals(action)){
			if(StringUtils.isNotEmpty(awardData.getBatchNo())){
				t03Item.setCONFIRM_CODE("Y");
			}else{
				t03Item.setCONFIRM_CODE("N");
			}
		}else{
			if("290".equals(awardData.getStatus())){
				t03Item.setCONFIRM_CODE("N");
			}else{
				t03Item.setCONFIRM_CODE("Y");
			}
		}
			
		t03Item.setCONFIRM_USER(userDetails.getUsername());					
		t03Item.setCONFIRM_DATE(dateConvertService.getDateOfYuanYearWithHyphenFormat(new Date()));
		t03Item.setCONFIRM_TIME(dateConvertService.getDateOfTimeFormat(new Date()));
		t03Item.setBATCH_NO(awardData.getBatchNo() == null ? "" : awardData.getBatchNo());
		t03Itemlist.add(t03Item);
		t03.setItem(t03Itemlist);
		
		Gson gson = new Gson();
		//將物件轉成JSON
		return gson.toJson(t03);
	}

	@Override
	public String makeT05Message(AwardData awardData, UserProfileBean userDetails) {
		
		String currentPoint = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))+"+08:00";
		
		T05RqBean t05 = new T05RqBean();
		t05.setTX_ID(SystemInfo.TXIDRATEDETAIL);
		t05.setTX_TIME(currentPoint);
		t05.setBRANCH_ID(awardData.getBranchId());
		t05.setTENDER_NO(awardData.getTenderNo());
		t05.setQUATION_NO(awardData.getId().getQuationNo());
		

		t05.setITEM_NO(awardData.getId().getItemNo());
		t05.setDAYS(awardData.getDays());
		t05.setDELIVERY_RATE(awardData.getDeliveryRate());
		t05.setUNDERWRITING_RATE(awardData.getUnderwritingRate());
		t05.setVISA_RATE(awardData.getVisaRate());
		t05.setGUARANTEE_RATE(awardData.getGuaranteeRate());

		
		Gson gson = new Gson();
		//將物件轉成JSON
		return gson.toJson(t05);
	}
	

	
}
