package com.uxb2b.ecp.bean.swift;


/*
 * 查詢CP登錄狀態(Q03)下行電文
 * */
public class Q03RsBean {
	
	private String TX_ID;
	private String TX_TIME;
	private String RETURN_CODE;
	private String RETURN_DESC;
	private String BATCH_NO;
	private int STATUS;
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
	public String getRETURN_CODE() {
		return RETURN_CODE;
	}
	public void setRETURN_CODE(String rETURN_CODE) {
		RETURN_CODE = rETURN_CODE;
	}
	public String getRETURN_DESC() {
		return RETURN_DESC;
	}
	public void setRETURN_DESC(String rETURN_DESC) {
		RETURN_DESC = rETURN_DESC;
	}
	public String getBATCH_NO() {
		return BATCH_NO;
	}
	public void setBATCH_NO(String bATCH_NO) {
		BATCH_NO = bATCH_NO;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

}
