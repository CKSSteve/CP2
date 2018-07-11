package com.uxb2b.ecp.bean.swift;

import java.math.BigDecimal;


/*
 * 上傳帳務明細(T05)上行電文子欄位
 * */
public class T05RqItemsBean {

	private int ITEM_NO;
	private int DAYS;
	private BigDecimal DELIVERY_RATE;
	private BigDecimal UNDERWRITING_RATE;
	private BigDecimal VISA_RATE;
	private BigDecimal GUARANTEE_RATE;
	public int getITEM_NO() {
		return ITEM_NO;
	}
	public void setITEM_NO(int iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}
	public int getDAYS() {
		return DAYS;
	}
	public void setDAYS(int dAYS) {
		DAYS = dAYS;
	}
	public BigDecimal getDELIVERY_RATE() {
		return DELIVERY_RATE;
	}
	public void setDELIVERY_RATE(BigDecimal dELIVERY_RATE) {
		DELIVERY_RATE = dELIVERY_RATE;
	}
	public BigDecimal getUNDERWRITING_RATE() {
		return UNDERWRITING_RATE;
	}
	public void setUNDERWRITING_RATE(BigDecimal uNDERWRITING_RATE) {
		UNDERWRITING_RATE = uNDERWRITING_RATE;
	}
	public BigDecimal getVISA_RATE() {
		return VISA_RATE;
	}
	public void setVISA_RATE(BigDecimal vISA_RATE) {
		VISA_RATE = vISA_RATE;
	}
	public BigDecimal getGUARANTEE_RATE() {
		return GUARANTEE_RATE;
	}
	public void setGUARANTEE_RATE(BigDecimal gUARANTEE_RATE) {
		GUARANTEE_RATE = gUARANTEE_RATE;
	}
	
}
