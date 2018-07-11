package com.uxb2b.ecp.bean.swift;

import java.math.BigDecimal;
import java.util.List;


/*
 * 查詢標單(Q01)上行電文子子欄位
 * */
public class Q01RsItemsBean {

	private String TENDER_NO;
	private String ISSUER_ID;
	private String INVITE_DATE;
	private String CLOSE_DATE;
	private String CLOSE_TIME;
	private String EFFECT_DATE;
	private BigDecimal LOAN_AMOUNT;
	private String CURRENCY_ID;
	private String AMOUNT_UNIT;
	private String STATUS;
	private String CONTACT_USER;
	private String CONTACT_PHONE;
	private String CONTACT_FAX;
	private String BANK_ID;
	private String BRANCH_ID;
	private String QUATION_NO;
	private List<Q01RsT2ItemsBean> Items;
	
	public String getTENDER_NO() {
		return TENDER_NO;
	}
	public void setTENDER_NO(String tENDER_NO) {
		TENDER_NO = tENDER_NO;
	}
	public String getISSUER_ID() {
		return ISSUER_ID;
	}
	public void setISSUER_ID(String iSSUER_ID) {
		ISSUER_ID = iSSUER_ID;
	}
	public String getINVITE_DATE() {
		return INVITE_DATE;
	}
	public void setINVITE_DATE(String iNVITE_DATE) {
		INVITE_DATE = iNVITE_DATE;
	}
	public String getCLOSE_DATE() {
		return CLOSE_DATE;
	}
	public void setCLOSE_DATE(String cLOSE_DATE) {
		CLOSE_DATE = cLOSE_DATE;
	}
	public String getCLOSE_TIME() {
		return CLOSE_TIME;
	}
	public void setCLOSE_TIME(String cLOSE_TIME) {
		CLOSE_TIME = cLOSE_TIME;
	}
	public String getEFFECT_DATE() {
		return EFFECT_DATE;
	}
	public void setEFFECT_DATE(String eFFECT_DATE) {
		EFFECT_DATE = eFFECT_DATE;
	}
	public BigDecimal getLOAN_AMOUNT() {
		return LOAN_AMOUNT;
	}
	public void setLOAN_AMOUNT(BigDecimal lOAN_AMOUNT) {
		LOAN_AMOUNT = lOAN_AMOUNT;
	}
	public String getCURRENCY_ID() {
		return CURRENCY_ID;
	}
	public void setCURRENCY_ID(String cURRENCY_ID) {
		CURRENCY_ID = cURRENCY_ID;
	}
	public String getAMOUNT_UNIT() {
		return AMOUNT_UNIT;
	}
	public void setAMOUNT_UNIT(String aMOUNT_UNIT) {
		AMOUNT_UNIT = aMOUNT_UNIT;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getCONTACT_USER() {
		return CONTACT_USER;
	}
	public void setCONTACT_USER(String cONTACT_USER) {
		CONTACT_USER = cONTACT_USER;
	}
	public String getCONTACT_PHONE() {
		return CONTACT_PHONE;
	}
	public void setCONTACT_PHONE(String cONTACT_PHONE) {
		CONTACT_PHONE = cONTACT_PHONE;
	}
	public String getCONTACT_FAX() {
		return CONTACT_FAX;
	}
	public void setCONTACT_FAX(String cONTACT_FAX) {
		CONTACT_FAX = cONTACT_FAX;
	}
	public String getBANK_ID() {
		return BANK_ID;
	}
	public void setBANK_ID(String bANK_ID) {
		BANK_ID = bANK_ID;
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
	public List<Q01RsT2ItemsBean> getItems() {
		return Items;
	}
	public void setItems(List<Q01RsT2ItemsBean> items) {
		Items = items;
	}	
	
}
