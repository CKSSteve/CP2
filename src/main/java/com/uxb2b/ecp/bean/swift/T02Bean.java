package com.uxb2b.ecp.bean.swift;

import java.math.BigDecimal;
import java.util.List;


/*
 * 上傳報價單(T02)上行電文
 * */
public class T02Bean {
	
	private String TX_ID;
	private String TX_TIME;
	private String TENDER_NO;
	private String BRANCH_ID;
	private String QUATION_NO;
	private String QUATION_TIME;
	private BigDecimal MAX_AMOUNT;
	private BigDecimal EXTRA_AMOUNT;
	private String EXTRA_TYPE;
	private BigDecimal EXTRA_RATE;
	private String QUATION_METHOD;
	private String STATUS;
	private String CREATE_USER;
	private String CREATE_DATE;
	private String CREATE_TIME;
	private List<T02ItemBean> Items;
	public String getTX_ID() {
		return TX_ID;
	}
	public void setTX_ID(String tX_ID) {
		TX_ID = tX_ID;
	}
	public String getTX_TIME() {
		return TX_TIME;
	}
	public void setTX_TIME(String tX_TIME) {
		TX_TIME = tX_TIME;
	}
	public String getTENDER_NO() {
		return TENDER_NO;
	}
	public void setTENDER_NO(String tENDER_NO) {
		TENDER_NO = tENDER_NO;
	}
	public String getBRANCH_ID() {
		return BRANCH_ID;
	}
	public void setBRANCH_ID(String bRANCH_ID) {
		BRANCH_ID = bRANCH_ID;
	}
	public String getQUATION_NO() {
		return QUATION_NO;
	}
	public void setQUATION_NO(String qUATION_NO) {
		QUATION_NO = qUATION_NO;
	}
	public String getQUATION_TIME() {
		return QUATION_TIME;
	}
	public void setQUATION_TIME(String qUATION_TIME) {
		QUATION_TIME = qUATION_TIME;
	}	
	public BigDecimal getMAX_AMOUNT() {
		return MAX_AMOUNT;
	}
	public void setMAX_AMOUNT(BigDecimal mAX_AMOUNT) {
		MAX_AMOUNT = mAX_AMOUNT;
	}
	
	public BigDecimal getEXTRA_AMOUNT() {
		return EXTRA_AMOUNT;
	}
	public void setEXTRA_AMOUNT(BigDecimal eXTRA_AMOUNT) {
		EXTRA_AMOUNT = eXTRA_AMOUNT;
	}
	public String getEXTRA_TYPE() {
		return EXTRA_TYPE;
	}
	public void setEXTRA_TYPE(String eXTRA_TYPE) {
		EXTRA_TYPE = eXTRA_TYPE;
	}
	public BigDecimal getEXTRA_RATE() {
		return EXTRA_RATE;
	}
	public void setEXTRA_RATE(BigDecimal eXTRA_RATE) {
		EXTRA_RATE = eXTRA_RATE;
	}
	public String getQUATION_METHOD() {
		return QUATION_METHOD;
	}
	public void setQUATION_METHOD(String qUATION_METHOD) {
		QUATION_METHOD = qUATION_METHOD;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getCREATE_USER() {
		return CREATE_USER;
	}
	public void setCREATE_USER(String cREATE_USER) {
		CREATE_USER = cREATE_USER;
	}
	public String getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public List<T02ItemBean> getItems() {
		return Items;
	}
	public void setItems(List<T02ItemBean> items) {
		Items = items;
	}
	
}
