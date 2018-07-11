package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the USER_LOG database table.
 * 
 */
@Entity
@Table(name="USER_LOG")
@NamedQuery(name="UserLog.findAll", query="SELECT u FROM UserLog u")
public class UserLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LOG_ID")
	private String logId;

	@Column(name="FUNCTION_ACTION")
	private String action;

	@Lob
	@Column(name="CONTENT")
	private String content;

	@Column(name="FUNCTION_ID")
	private String functionId;

	@Column(name="LOG_TIME")
	private Timestamp logTime;

	@Column(name="STATUS")
	private String status;

	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@ManyToOne
    @JoinColumn(name="FUNCTION_ID", insertable = false, updatable = false)  
	private Function function;
	
	public UserLog() {
	}

	public UserLog(String functionId,String action, Timestamp logTime, String content,
			String userId, String userName,String status) {
		this.action=action;
		this.content=content;
		this.functionId=functionId;
		this.logTime=logTime;
		this.status=status;
		this.userId=userId;
		this.userName=userName;
	}
	
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public Timestamp getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	
}