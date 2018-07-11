package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CTBFLBQ2 database table.
 * 
 */
@Entity
@Table(name="CTBFLBQ2")
@NamedQuery(name="Ctbflbq2.findAll", query="SELECT c FROM Ctbflbq2 c")
public class Ctbflbq2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Ctbflbq2PK id;

	@Column(name="AWARD_AMOUNT")
	private BigDecimal awardAmount;

	@Column(name="AWARD_RATE")
	private BigDecimal awardRate;
	
	@Column(name="AWARD_RATE_AVG")
	private BigDecimal awardRateAvg;
	
	@Column(name="AWARD_AMOUNT_TOTAL")
	private BigDecimal awardAmountTotal;
	
	@Column(name="[DAYS]")
	private int days;

	@Column(name="EXPIRED_DATE")
	private String expiredDate;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="LOAN_TYPE")
	private String loanType;

	@Column(name="QUATION_AMOUNT")
	private BigDecimal quationAmount;

	@Column(name="QUATION_RATE")
	private BigDecimal quationRate;

	@Column(name="STATUS")
	private String status;

	public Ctbflbq2() {
	}

	public Ctbflbq2PK getId() {
		return this.id;
	}

	public void setId(Ctbflbq2PK id) {
		this.id = id;
	}

	public BigDecimal getAwardAmount() {
		return this.awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

	public BigDecimal getAwardRate() {
		return awardRate;
	}

	public void setAwardRate(BigDecimal awardRate) {
		this.awardRate = awardRate;
	}

	public BigDecimal getAwardRateAvg() {
		return awardRateAvg;
	}

	public void setAwardRateAvg(BigDecimal awardRateAvg) {
		this.awardRateAvg = awardRateAvg;
	}
	
	public BigDecimal getAwardAmountTotal() {
		return awardAmountTotal;
	}

	public void setAwardAmountTotal(BigDecimal awardAmountTotal) {
		this.awardAmountTotal = awardAmountTotal;
	}

	public int getDays() {
		return this.days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getExpiredDate() {
		return this.expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public BigDecimal getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanType() {
		return this.loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public BigDecimal getQuationAmount() {
		return this.quationAmount;
	}

	public void setQuationAmount(BigDecimal quationAmount) {
		this.quationAmount = quationAmount;
	}

	public BigDecimal getQuationRate() {
		return this.quationRate;
	}

	public void setQuationRate(BigDecimal quationRate) {
		this.quationRate = quationRate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}