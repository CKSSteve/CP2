package com.uxb2b.ecp.bean.swift;

import java.util.List;

/*
 * 上傳標單狀態(T01)上行電文
 * */
public class T01RqBean {

	private String TX_ID;
	private String TX_TIME;
	private List<T01RqitemsBean> Items;
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
	public List<T01RqitemsBean> getItems() {
		return Items;
	}
	public void setItems(List<T01RqitemsBean> items) {
		Items = items;
	}
	
}
