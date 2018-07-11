package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the CTBFLBT1 database table.
 * 
 */
@Entity
@Table(name="CTBFLBT1")
@NamedQuery(name="Ctbflbt1.findAll", query="SELECT c FROM Ctbflbt1 c")
public class Ctbflbt1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TENDER_NO")
	private String tenderNo;

	@Column(name="AMOUNT_UNIT")
	private String amountUnit;

	@Column(name="AWARD_AMOUNT")
	private BigDecimal awardAmount;

	@Column(name="AWARD_DATE")
	private String awardDate;

	@Column(name="AWARD_RATE")
	private BigDecimal awardRate;

	@Column(name="AWARD_RATE_AVG")
	private BigDecimal awardRateAvg;

	@Column(name="AWARD_TIME")
	private String awardTime;

	@Column(name="BANK_ID")
	private String bankId;

	@Column(name="BRANCH_ID")
	private String branchId;

	@Column(name="CLOSE_DATE")
	private String closeDate;

	@Column(name="CLOSE_TIME")
	private String closeTime;

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

	@Column(name="INVITE_DATE")
	private String inviteDate;

	@Column(name="ISSUER_ID")
	private String issuerId;

	@Column(name="ISSUER_NAME")
	private String issuerName;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="QUATION_RATE_AVG")
	private BigDecimal quationRateAvg;

	@Column(name="STATUS")
	private String status;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="ctbflbt1")
	private List<Ctbflbt2> ctbflbt2;

	public Ctbflbt1() {
	}

	public String getTenderNo() {
		return this.tenderNo;
	}

	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}

	public String getAmountUnit() {
		return this.amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public BigDecimal getAwardAmount() {
		return this.awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

	public String getAwardDate() {
		return this.awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
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

	public String getAwardTime() {
		return this.awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
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

	public String getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
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

	public String getInviteDate() {
		return this.inviteDate;
	}

	public void setInviteDate(String inviteDate) {
		this.inviteDate = inviteDate;
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

	public BigDecimal getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getQuationRateAvg() {
		return this.quationRateAvg;
	}

	public void setQuationRateAvg(BigDecimal quationRateAvg) {
		this.quationRateAvg = quationRateAvg;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Ctbflbt2> getCtbflbt2() {
		return ctbflbt2;
	}

	public void setCtbflbt2(List<Ctbflbt2> ctbflbt2) {
		this.ctbflbt2 = ctbflbt2;
	}
	
}