package com.uxb2b.ecp.bean;

/**
 * 使用者存取紀錄查詢
 * @author evayang
 *
 */
public class QueryUserAccessRecordFormBean {
	
	private String searchLogTimeStartDate; 
	private String searchLogTimeEndDate;   
	private String searchFunctionId;  
	private String searchUserId;
	
	private String logId;
	
	public String getSearchLogTimeStartDate() {
		return searchLogTimeStartDate;
	}
	public void setSearchLogTimeStartDate(String searchLogTimeStartDate) {
		this.searchLogTimeStartDate = searchLogTimeStartDate;
	}
	public String getSearchLogTimeEndDate() {
		return searchLogTimeEndDate;
	}
	public void setSearchLogTimeEndDate(String searchLogTimeEndDate) {
		this.searchLogTimeEndDate = searchLogTimeEndDate;
	}
	public String getSearchFunctionId() {
		return searchFunctionId;
	}
	public void setSearchFunctionId(String searchFunctionId) {
		this.searchFunctionId = searchFunctionId;
	}
	public String getSearchUserId() {
		return searchUserId;
	}
	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}  

}
