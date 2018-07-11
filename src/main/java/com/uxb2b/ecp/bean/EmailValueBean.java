package com.uxb2b.ecp.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class EmailValueBean {

	/*標單*/
	private String quationNo;
	private String tenderNo;
	private String inviteDate;
	private String closeDate;
	private String closeTime;

	/*票券批號*/
	private String awardDate;
	private String quationAmount;
	private String awardAmount;
	
	/*本票帳務明細*/
	private String batchNo;
	private String effectDate;
	private String expiredDate;
	private String loanType;
	private Integer days;
	private BigDecimal quationRate;
	
	/*共用*/
	private Timestamp returnTime;
	private String returnPeople;
	private String rejectReason;
	private String issuerId;
	private String issuerName;
	private String currencyName;
	private BigDecimal loanAmount;
	private String amountUnit;
	
	public String getQuationNo() {
		return quationNo;
	}
	public void setQuationNo(String quationNo) {
		this.quationNo = quationNo;
	}
	public String getTenderNo() {
		return tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}
	public String getInviteDate() {
		return inviteDate;
	}
	public void setInviteDate(String inviteDate) {
		this.inviteDate = inviteDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getAmountUnit() {
		return amountUnit;
	}
	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}
	public Timestamp getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}
	public String getReturnPeople() {
		return returnPeople;
	}
	public void setReturnPeople(String returnPeople) {
		this.returnPeople = returnPeople;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public BigDecimal getQuationRate() {
		return quationRate;
	}
	public void setQuationRate(BigDecimal quationRate) {
		this.quationRate = quationRate;
	}
	public String getAwardDate() {
		return awardDate;
	}
	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}
	public String getQuationAmount() {
		return quationAmount;
	}
	public void setQuationAmount(String quationAmount) {
		this.quationAmount = quationAmount;
	}
	public String getAwardAmount() {
		return awardAmount;
	}
	public void setAwardAmount(String awardAmount) {
		this.awardAmount = awardAmount;
	}
	
} 
