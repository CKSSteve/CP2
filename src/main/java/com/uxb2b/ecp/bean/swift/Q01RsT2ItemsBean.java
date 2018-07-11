package com.uxb2b.ecp.bean.swift;

import java.math.BigDecimal;

public class Q01RsT2ItemsBean {
	private int ITEM_NO;
	private String EXPIRED_DATE;
	private int DAYS;
	private BigDecimal LOAN_AMOUNT;
	public int getITEM_NO() {
		return ITEM_NO;
	}
	public void setITEM_NO(int iTEM_NO) {
		ITEM_NO = iTEM_NO;
	}
	public String getEXPIRED_DATE() {
		return EXPIRED_DATE;
	}
	public void setEXPIRED_DATE(String eXPIRED_DATE) {
		EXPIRED_DATE = eXPIRED_DATE;
	}
	public int getDAYS() {
		return DAYS;
	}
	public void setDAYS(int dAYS) {
		DAYS = dAYS;
	}
	public BigDecimal getLOAN_AMOUNT() {
		return LOAN_AMOUNT;
	}
	public void setLOAN_AMOUNT(BigDecimal lOAN_AMOUNT) {
		LOAN_AMOUNT = lOAN_AMOUNT;
	} 
}
