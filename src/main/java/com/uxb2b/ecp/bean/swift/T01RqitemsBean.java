package com.uxb2b.ecp.bean.swift;


/*
 * 上傳標單狀態(T01)上行電文子欄位
 * */
public class T01RqitemsBean {
	
	private String TENDER_NO;
	private String QUATION_NO;
	private String STATUS;
	
	public T01RqitemsBean(){}
	
	public T01RqitemsBean(String TENDER_NO,String QUATION_NO,String STATUS){
		
		this.TENDER_NO=TENDER_NO;
		this.QUATION_NO=QUATION_NO;
		this.STATUS=STATUS;
	}
	
	public String getTENDER_NO() {
		return TENDER_NO;
	}
	public void setTENDER_NO(String tENDER_NO) {
		TENDER_NO = tENDER_NO;
	}
	public String getQUATION_NO() {
		return QUATION_NO;
	}
	public void setQUATION_NO(String qUATION_NO) {
		QUATION_NO = qUATION_NO;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	
	
}
