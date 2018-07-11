package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CTBFLBT2 database table.
 * 
 */
@Entity
@Table(name="CTBFLBT2")
@NamedQuery(name="Ctbflbt2.findAll", query="SELECT c FROM Ctbflbt2 c")
public class Ctbflbt2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Ctbflbt2PK id;

	@Column(name="AWARD_AMOUNT")
	private BigDecimal awardAmount;

	@Column(name="AWARD_RATE")
	private BigDecimal awardRate;

	@Column(name="AWARD_RATE_AVG")
	private BigDecimal awardRateAvg;

	@Column(name="[DAYS]")
	private int days;

	@Column(name="EXPIRED_DATE")
	private String expiredDate;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;
	
	@ManyToOne
    @JoinColumn(name="TENDER_NO", insertable = false, updatable = false)  
	private Ctbflbt1 ctbflbt1;

	public Ctbflbt2() {
	}

	public Ctbflbt2PK getId() {
		return this.id;
	}

	public void setId(Ctbflbt2PK id) {
		this.id = id;
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

	public Ctbflbt1 getCtbflbt1() {
		return ctbflbt1;
	}

	public void setCtbflbt1(Ctbflbt1 ctbflbt1) {
		this.ctbflbt1 = ctbflbt1;
	}

}