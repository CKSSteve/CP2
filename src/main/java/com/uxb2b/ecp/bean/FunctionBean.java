package com.uxb2b.ecp.bean;

public class FunctionBean {

	private int roleId;
	private String functionId;
	private String uri;
	private String functionName;
	private String ParentId;
	
	public FunctionBean(int roleId,String functionId,String functionName,String ParentId,String uri){
		
		this.roleId=roleId;
		this.functionId=functionId;
		this.uri=uri;
		this.functionName=functionName;
		this.ParentId=ParentId;
		
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getParentId() {
		return ParentId;
	}
	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	
	
	
}
