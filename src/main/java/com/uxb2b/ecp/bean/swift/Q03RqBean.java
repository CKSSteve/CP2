package com.uxb2b.ecp.bean.swift;


/*
 * 查詢CP登錄狀態(Q03)上行電文
 * */
public class Q03RqBean {

	private String TX_ID;
	private String TX_TIME;
	private String BANK_ID;
	private String BATCH_NO;
	
	public Q03RqBean(){}
	
	public Q03RqBean(String TX_ID,String TX_TIME,String BANK_ID,String BATCH_NO){
		
		this.TX_ID=TX_ID;
		this.TX_TIME=TX_TIME;
		this.BANK_ID=BANK_ID;
		this.BATCH_NO=BATCH_NO;
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
	public String getBATCH_NO() {
		return BATCH_NO;
	}
	public void setBATCH_NO(String bATCH_NO) {
		BATCH_NO = bATCH_NO;
	}
	
}
