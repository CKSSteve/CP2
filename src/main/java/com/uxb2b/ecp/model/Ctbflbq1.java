package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CTBFLBQ1 database table.
 * 
 */
@Entity
@Table(name="CTBFLBQ1")
@NamedQuery(name="Ctbflbq1.findAll", query="SELECT c FROM Ctbflbq1 c")
public class Ctbflbq1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TENDER_NO")
	private String tenderNo;

	@Column(name="APPROVE_STATUS")
	private String approveStatus;

	@Column(name="AWARD_AMOUNT")
	private BigDecimal awardAmount;

	@Column(name="AWARD_RATE")
	private BigDecimal awardRate;

	@Column(name="AWARD_RATE_AVG")
	private BigDecimal awardRateAvg;

	@Column(name="AMOUNT_UNIT")
	private String amountUnit;
	
	@Column(name="AWARD_DATE")
	private String awardDate;
	
	@Column(name="AWARD_TIME")
	private String awardTime;
	
	@Column(name="BANK_ID")
	private String bankId;

	@Column(name="BRANCH_ID")
	private String branchId;

	@Column(name="CONTACT_FAX")
	private String contactFax;

	@Column(name="CONTACT_PHONE")
	private String contactPhone;

	@Column(name="CONTACT_USER")
	private String contactUser;

	@Column(name="CURRENCY_ID")
	private String currencyId;

	@Column(name="CURRENCY_NAME")
	private String currencyName;

	@Column(name="EFFECT_DATE")
	private String effectDate;

	@Column(name="EXTRA_AMOUNT")
	private BigDecimal extraAmount;

	@Column(name="EXTRA_RATE")
	private BigDecimal extraRate;

	@Column(name="EXTRA_TYPE")
	private String extraType;

	@Column(name="FINAL_STEP")
	private String finalStep;

	@Column(name="FLOW_ID")
	private int flowId;

	@Column(name="ISSUER_ID")
	private String issuerId;

	@Column(name="ISSUER_NAME")
	private String issuerName;

	@Column(name="LAST_MODIFY_TIME")
	private Timestamp lastModifyTime;

	@Column(name="LAST_MODIFY_USER")
	private String lastModifyUser;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="MAX_AMOUNT")
	private BigDecimal maxAmount;

	@Column(name="QUATION_METHOD")
	private String quationMethod;

	@Column(name="QUATION_NO")
	private String quationNo;

	@Column(name="QUATION_TIME")
	private Timestamp quationTime;

	@Column(name="REJECT_REASON")
	private String rejectReason;

	@Column(name="ROLE_ID")
	private int roleId;

	@Column(name="STATUS")
	private String status;

	@Column(name="STEP_NO")
	private int stepNo;
	
	@Column(name="UPLOAD_FLAG1")
	private String uploadFlag1;
	
	@Column(name="UPLOAD_FLAG2")
	private String uploadFlag2;
	
	@OneToOne
    @JoinColumn(name="TENDER_NO", insertable = false, updatable = false)  
	private Ctbflbt1 ctbflbt1;


	public Ctbflbq1() {
	}

	public String getTenderNo() {
		return this.tenderNo;
	}

	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}

	public String getApproveStatus() {
		return this.approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public BigDecimal getAwardAmount() {
		return this.awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

	public BigDecimal getAwardRate() {
		return this.awardRate;
	}

	public void setAwardRate(BigDecimal awardRate) {
		this.awardRate = awardRate;
	}

	public BigDecimal getAwardRateAvg() {
		return this.awardRateAvg;
	}

	public void setAwardRateAvg(BigDecimal awardRateAvg) {
		this.awardRateAvg = awardRateAvg;
	}
	
	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}

	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getContactFax() {
		return this.contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactUser() {
		return this.contactUser;
	}

	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public BigDecimal getExtraAmount() {
		return this.extraAmount;
	}

	public void setExtraAmount(BigDecimal extraAmount) {
		this.extraAmount = extraAmount;
	}

	public BigDecimal getExtraRate() {
		return this.extraRate;
	}

	public void setExtraRate(BigDecimal extraRate) {
		this.extraRate = extraRate;
	}

	public String getExtraType() {
		return this.extraType;
	}

	public void setExtraType(String extraType) {
		this.extraType = extraType;
	}

	public String getFinalStep() {
		return this.finalStep;
	}

	public void setFinalStep(String finalStep) {
		this.finalStep = finalStep;
	}

	public int getFlowId() {
		return this.flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public String getIssuerId() {
		return this.issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getIssuerName() {
		return this.issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public Timestamp getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastModifyUser() {
		return this.lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public BigDecimal getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getQuationMethod() {
		return this.quationMethod;
	}

	public void setQuationMethod(String quationMethod) {
		this.quationMethod = quationMethod;
	}

	public String getQuationNo() {
		return this.quationNo;
	}

	public void setQuationNo(String quationNo) {
		this.quationNo = quationNo;
	}

	public Timestamp getQuationTime() {
		return this.quationTime;
	}

	public void setQuationTime(Timestamp quationTime) {
		this.quationTime = quationTime;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStepNo() {
		return this.stepNo;
	}

	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}

	public Ctbflbt1 getCtbflbt1() {
		return ctbflbt1;
	}

	public void setCtbflbt1(Ctbflbt1 ctbflbt1) {
		this.ctbflbt1 = ctbflbt1;
	}

	public String getUploadFlag1() {
		return uploadFlag1;
	}

	public void setUploadFlag1(String uploadFlag1) {
		this.uploadFlag1 = uploadFlag1;
	}

	public String getUploadFlag2() {
		return uploadFlag2;
	}

	public void setUploadFlag2(String uploadFlag2) {
		this.uploadFlag2 = uploadFlag2;
	}

	public String getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}
	
}