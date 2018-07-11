package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the [USER] database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Column(name="BRANCH_ID")
	private String branchId;

	@Column(name="BUSINESS_TYPE_C")
	private String businessTypeC;

	@Column(name="BUSINESS_TYPE_G")
	private String businessTypeG;

	@Column(name="BUSINESS_TYPE_Q")
	private String businessTypeQ;

	@Column(name="CHANGE_PASSWORD_TIME")
	private Timestamp changePasswordTime;

	@Column(name="DEPUTIED")
	private short deputied;

	@Column(name="DEPUTY_ID")
	private String deputyId;

	@Column(name="EMAIL")
	private String email;

	@Column(name="ERROR_COUNT")
	private int errorCount;

	@Column(name="LAST_ERROR_TIME")
	private Timestamp lastErrorTime;

	@Column(name="LAST_LOGIN_TIME")
	private Timestamp lastLoginTime;

	@Column(name="LOGIN_COUNT")
	private int loginCount;

	@Column(name="MOBILE_PHONE")
	private String mobilePhone;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="ROLE_ID")
	private int roleId;

	@Column(name="STATUS")
	private short status;

	@Column(name="SUPERVISOR")
	private String supervisor;

	@Column(name="TEL_NO")
	private String telNo;

	@Column(name="USER_NAME")
	private String userName;
	
	@ManyToOne
    @JoinColumn(name="ROLE_ID", insertable = false, updatable = false)  
	private Role role;

	@ManyToOne
    @JoinColumn(name="BRANCH_ID", insertable = false, updatable = false)  
	private Branch branch;
	
	public User() {
	}
	
	public User(String userId,String userName,String branchId, int roleId,String password,String telNo,String email){
		this.userId=userId;
		this.userName=userName;
		this.branchId=branchId;
		this.roleId=roleId;
		this.password=password;
		this.telNo=telNo;
		this.email=email;
	}
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBusinessTypeC() {
		return this.businessTypeC;
	}

	public void setBusinessTypeC(String businessTypeC) {
		this.businessTypeC = businessTypeC;
	}

	public String getBusinessTypeG() {
		return this.businessTypeG;
	}

	public void setBusinessTypeG(String businessTypeG) {
		this.businessTypeG = businessTypeG;
	}

	public String getBusinessTypeQ() {
		return this.businessTypeQ;
	}

	public void setBusinessTypeQ(String businessTypeQ) {
		this.businessTypeQ = businessTypeQ;
	}

	public Timestamp getChangePasswordTime() {
		return this.changePasswordTime;
	}

	public void setChangePasswordTime(Timestamp changePasswordTime) {
		this.changePasswordTime = changePasswordTime;
	}

	public short getDeputied() {
		return this.deputied;
	}

	public void setDeputied(short deputied) {
		this.deputied = deputied;
	}

	public String getDeputyId() {
		return this.deputyId;
	}

	public void setDeputyId(String deputyId) {
		this.deputyId = deputyId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public Timestamp getLastErrorTime() {
		return this.lastErrorTime;
	}

	public void setLastErrorTime(Timestamp lastErrorTime) {
		this.lastErrorTime = lastErrorTime;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public short getStatus() {
		return this.status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getSupervisor() {
		return this.supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getTelNo() {
		return this.telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}