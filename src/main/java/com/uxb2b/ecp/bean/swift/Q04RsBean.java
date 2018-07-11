package com.uxb2b.ecp.bean.swift;

import java.util.List;

/*
 * 查詢CP登錄資料(Q04)下行電文
 * */
public class Q04RsBean {

	private String TX_ID;
	private String TX_TIME;
	private String RETURN_CODE;
	private String RETURN_DESC;
	private List<Q04RsItemBean> Items;
	
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
	public List<Q04RsItemBean> getItems() {
		return Items;
	}
	public void setItems(List<Q04RsItemBean> items) {
		Items = items;
	}
	
}
