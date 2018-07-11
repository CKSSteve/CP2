package com.uxb2b.ecp.bean.swift;


/*
 * 查詢CP登錄資料(Q04)上行電文
 * */
public class Q04RqBean {

	private String TX_ID;
	private String TX_TIME;
	private String BANK_ID;
	private String ISSUER_ID;
	
	public Q04RqBean(){}
	
	public Q04RqBean(String TX_ID,String TX_TIME,String BANK_ID,String ISSUER_ID){
		
		this.TX_ID=TX_ID;
		this.TX_TIME=TX_TIME;
		this.BANK_ID=BANK_ID;
		this.ISSUER_ID=ISSUER_ID;
	}
	
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
	public String getBANK_ID() {
		return BANK_ID;
	}
	public void setBANK_ID(String bANK_ID) {
		BANK_ID = bANK_ID;
	}
	public String getISSUER_ID() {
		return ISSUER_ID;
	}
	public void setISSUER_ID(String iSSUER_ID) {
		ISSUER_ID = iSSUER_ID;
	}
}
