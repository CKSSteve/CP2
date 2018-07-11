package com.uxb2b.ecp.bean.swift;

import java.util.List;
/**
 * 上傳票券批號(得標確認) 
 * */

public class T03RqBean {
	
	private String TX_ID;
	private String TX_TIME;
	private String TENDER_NO;
	private String BRANCH_ID;
	private String QUATION_NO;
	private List<T03RqItemBean> Item;
	
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
	
	public List<T03RqItemBean> getItem() {
		return Item;
	}
	public void setItem(List<T03RqItemBean> item) {
		Item = item;
	}
	
	
	
}
