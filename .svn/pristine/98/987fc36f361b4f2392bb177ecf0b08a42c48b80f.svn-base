package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the REJECT_REASON database table.
 * 
 */
@Entity
@Table(name="REJECT_REASON")
@NamedQuery(name="RejectReason.findAll", query="SELECT r FROM RejectReason r")
public class RejectReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RejectReasonPK id;

	@Column(name="CODE_VALUE")
	private String codeValue;

	@Column(name="ERROR_CODE")
	private String errorCode;

	@Column(name="MEMO")
	private String memo;

	@Column(name="STATUS")
	private short status;

	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name="UPDATE_USER")
	private String updateUser;
	
//	@ManyToOne
//	@JoinColumns({
//		  @JoinColumn(name = "CODE_TYPE", insertable = false, updatable = false),
//		  @JoinColumn(name = "CODE_KEY", insertable = false, updatable = false)
//	})
////	@JoinColumn(name = "CODE_TYPE", insertable = false, updatable = false)
//	private SysCode sysCode;
		

	public RejectReason() {
	}

	public RejectReasonPK getId() {
		return this.id;
	}

	public void setId(RejectReasonPK id) {
		this.id = id;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}