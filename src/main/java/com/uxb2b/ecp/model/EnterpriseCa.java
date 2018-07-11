package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ENTERPRISE_CA database table.
 * 
 */
@Entity
@Table(name="ENTERPRISE_CA")
@NamedQuery(name="EnterpriseCa.findAll", query="SELECT e FROM EnterpriseCa e")
public class EnterpriseCa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private long id;

	@Column(name="BRANCH_ID")
	private String branchId;

	@Column(name="CA_NO")
	private String caNo;

	@Column(name="ENTERPRISE_UNI")
	private String enterpriseUni;

	@Column(name="IS_USED")
	private short isUsed;

	@Column(name="NEW_BRANCH_ID")
	private String newBranchId;

	@Column(name="STATUS")
	private String status;

	@Column(name="[TYPE]")
	private String type;

	@Column(name="VERIFY_TIME")
	private Timestamp verifyTime;

	@Column(name="VERIFY_USER_ID")
	private String verifyUserId;

	@Lob
	@Column(name="XML")
	private String xml;

	public EnterpriseCa() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCaNo() {
		return this.caNo;
	}

	public void setCaNo(String caNo) {
		this.caNo = caNo;
	}

	public String getEnterpriseUni() {
		return this.enterpriseUni;
	}

	public void setEnterpriseUni(String enterpriseUni) {
		this.enterpriseUni = enterpriseUni;
	}

	public short getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(short isUsed) {
		this.isUsed = isUsed;
	}

	public String getNewBranchId() {
		return this.newBranchId;
	}

	public void setNewBranchId(String newBranchId) {
		this.newBranchId = newBranchId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getVerifyTime() {
		return this.verifyTime;
	}

	public void setVerifyTime(Timestamp verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerifyUserId() {
		return this.verifyUserId;
	}

	public void setVerifyUserId(String verifyUserId) {
		this.verifyUserId = verifyUserId;
	}

	public String getXml() {
		return this.xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

}