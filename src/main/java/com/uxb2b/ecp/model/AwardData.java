package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the AWARD_DATA database table.
 * 
 */
@Entity
@Table(name="AWARD_DATA")
@NamedQuery(name="AwardData.findAll", query="SELECT a FROM AwardData a")
public class AwardData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AwardDataPK id;

	@Column(name="AMOUNT_UNIT")
	private String amountUnit;

	@Column(name="APPROVE_STATUS1")
	private String approveStatus1;

	@Column(nullable = true,name="APPROVE_STATUS2")
	private String approveStatus2;

	@Column(name="AWARD_AMOUNT")
	private BigDecimal awardAmount;

	@Column(name="AWARD_DATE")
	private String awardDate;

	@Column(name="AWARD_TIME")
	private String awardTime;

	@Column(name="BANK_ID")
	private String bankId;

	@Column(name="BATCH_NO")
	private String batchNo;

	@Column(name="BRANCH_ID")
	private String branchId;

	@Column(name="CURRENCY_ID")
	private String currencyId;

	@Column(name="CURRENCY_NAME")
	private String currencyName;

	@Column(name="[DAYS]")
	private int days;

	@Column(name="DELIVERY_RATE")
	private BigDecimal deliveryRate;

	@Column(name="EFFECT_DATE")
	private String effectDate;

	@Column(name="EXPIRED_DATE")
	private String expiredDate;

	@Column(name="FINAL_STEP1")
	private String finalStep1;

	@Column(nullable = true,name="FINAL_STEP2")
	private String finalStep2;

	@Column(name="FLOW_ID1")
	private int flowId1;

	@Column(nullable = true,name="FLOW_ID2")
	private Integer flowId2;

	@Column(name="GUARANTEE_RATE")
	private BigDecimal guaranteeRate;

	@Column(name="ISSUER_ID")
	private String issuerId;

	@Column(name="ISSUER_NAME")
	private String issuerName;

	@Column(name="LAST_MODIFY_TIME1")
	private Timestamp lastModifyTime1;

	@Column(name="LAST_MODIFY_TIME2")
	private Timestamp lastModifyTime2;

	@Column(name="LAST_MODIFY_USER1")
	private String lastModifyUser1;

	@Column(name="LAST_MODIFY_USER2")
	private String lastModifyUser2;

	@Column(name="LOAN_AMOUNT")
	private BigDecimal loanAmount;

	@Column(name="LOAN_TYPE")
	private String loanType;

	@Column(name="QUATION_AMOUNT")
	private BigDecimal quationAmount;

	@Column(name="QUATION_RATE")
	private BigDecimal quationRate;

	@Column(name="REJECT_REASON1")
	private String rejectReason1;

	@Column(name="REJECT_REASON2")
	private String rejectReason2;

	@Column(name="REPLY_TIME1")
	private Timestamp replyTime1;

	@Column(name="REPLY_TIME2")
	private Timestamp replyTime2;

	@Column(name="ROLE_ID1")
	private int roleId1;

	@Column(nullable = true,name="ROLE_ID2")
	private Integer roleId2;

	@Column(name="STATUS")
	private String status;

	@Column(name="STEP_NO1")
	private int stepNo1;

	@Column(nullable = true,name="STEP_NO2")
	private Integer stepNo2;

	@Column(name="TENDER_NO")
	private String tenderNo;

	@Column(name="UNDERWRITING_RATE")
	private BigDecimal underwritingRate;

	@Column(name="VISA_RATE")
	private BigDecimal visaRate;

	public AwardData() {
	}

	public AwardDataPK getId() {
		return this.id;
	}

	public void setId(AwardDataPK id) {
		this.id = id;
	}

	public String getAmountUnit() {
		return this.amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public String getApproveStatus1() {
		return this.approveStatus1;
	}

	public void setApproveStatus1(String approveStatus1) {
		this.approveStatus1 = approveStatus1;
	}

	public String getApproveStatus2() {
		return this.approveStatus2;
	}

	public void setApproveStatus2(String approveStatus2) {
		this.approveStatus2 = approveStatus2;
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

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
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

	public int getDays() {
		return this.days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public BigDecimal getDeliveryRate() {
		return this.deliveryRate;
	}

	public void setDeliveryRate(BigDecimal deliveryRate) {
		this.deliveryRate = deliveryRate;
	}

	public String getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getExpiredDate() {
		return this.expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getFinalStep1() {
		return this.finalStep1;
	}

	public void setFinalStep1(String finalStep1) {
		this.finalStep1 = finalStep1;
	}

	public String getFinalStep2() {
		return this.finalStep2;
	}

	public void setFinalStep2(String finalStep2) {
		this.finalStep2 = finalStep2;
	}

	public int getFlowId1() {
		return this.flowId1;
	}

	public void setFlowId1(int flowId1) {
		this.flowId1 = flowId1;
	}

	public int getFlowId2() {
		return this.flowId2;
	}

	public void setFlowId2(int flowId2) {
		this.flowId2 = flowId2;
	}

	public BigDecimal getGuaranteeRate() {
		return this.guaranteeRate;
	}

	public void setGuaranteeRate(BigDecimal guaranteeRate) {
		this.guaranteeRate = guaranteeRate;
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

	public Timestamp getLastModifyTime1() {
		return this.lastModifyTime1;
	}

	public void setLastModifyTime1(Timestamp lastModifyTime1) {
		this.lastModifyTime1 = lastModifyTime1;
	}

	public Timestamp getLastModifyTime2() {
		return this.lastModifyTime2;
	}

	public void setLastModifyTime2(Timestamp lastModifyTime2) {
		this.lastModifyTime2 = lastModifyTime2;
	}

	public String getLastModifyUser1() {
		return this.lastModifyUser1;
	}

	public void setLastModifyUser1(String lastModifyUser1) {
		this.lastModifyUser1 = lastModifyUser1;
	}

	public String getLastModifyUser2() {
		return this.lastModifyUser2;
	}

	public void setLastModifyUser2(String lastModifyUser2) {
		this.lastModifyUser2 = lastModifyUser2;
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

	public String getRejectReason1() {
		return this.rejectReason1;
	}

	public void setRejectReason1(String rejectReason1) {
		this.rejectReason1 = rejectReason1;
	}

	public String getRejectReason2() {
		return this.rejectReason2;
	}

	public void setRejectReason2(String rejectReason2) {
		this.rejectReason2 = rejectReason2;
	}

	public Timestamp getReplyTime1() {
		return this.replyTime1;
	}

	public void setReplyTime1(Timestamp replyTime1) {
		this.replyTime1 = replyTime1;
	}

	public Timestamp getReplyTime2() {
		return this.replyTime2;
	}

	public void setReplyTime2(Timestamp replyTime2) {
		this.replyTime2 = replyTime2;
	}

	public int getRoleId1() {
		return this.roleId1;
	}

	public void setRoleId1(int roleId1) {
		this.roleId1 = roleId1;
	}

	public int getRoleId2() {
		return this.roleId2;
	}

	public void setRoleId2(int roleId2) {
		this.roleId2 = roleId2;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStepNo1() {
		return this.stepNo1;
	}

	public void setStepNo1(int stepNo1) {
		this.stepNo1 = stepNo1;
	}

	public int getStepNo2() {
		return this.stepNo2;
	}

	public void setStepNo2(int stepNo2) {
		this.stepNo2 = stepNo2;
	}

	public String getTenderNo() {
		return this.tenderNo;
	}

	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
	}

	public BigDecimal getUnderwritingRate() {
		return this.underwritingRate;
	}

	public void setUnderwritingRate(BigDecimal underwritingRate) {
		this.underwritingRate = underwritingRate;
	}

	public BigDecimal getVisaRate() {
		return this.visaRate;
	}

	public void setVisaRate(BigDecimal visaRate) {
		this.visaRate = visaRate;
	}
	
	@Override
	public String toString() {
		return "AWARD_DATA [AWARD_DATA [quationNo=" + id.getQuationNo() + ", itemNo="
				+ id.getItemNo() + ", amountUnit=" + amountUnit + ", approveStatus1="
				+ approveStatus1 + ", approveStatus2=" + approveStatus2 + ", awardAmount="
				+ awardAmount + ", awardDate=" + awardDate + ", awardTime="
				+ awardTime + ", bankId=" + bankId + ", batchNo=" + batchNo
				+ ", branchId=" + branchId + ", currencyId="
				+ currencyId + ", currencyName=" + currencyName
				+ ", days=" + days + ", deliveryRate="
				+ deliveryRate + ", effectDate=" + effectDate
				+ ", expiredDate=" + expiredDate
				+ ", finalStep1=" + finalStep1
				+ ", finalStep2=" + finalStep2 + ", flowId1="
				+ flowId1 + ", flowId2=" + flowId2
				+ ", guaranteeRate=" + guaranteeRate + ", issuerId="
				+ issuerId + ", issuerName=" + issuerName
				+ ", lastModifyTime1=" + lastModifyTime1 + ", lastModifyTime2="
				+ lastModifyTime2 + ", lastModifyUser1=" + lastModifyUser1
				+ ", lastModifyUser2=" + lastModifyUser2
				+ ", loanAmount=" + loanAmount + ", loanType=" + loanType + ", quationAmount="
				+ quationAmount + ", quationRate="
				+ quationRate + ", rejectReason1="
				+ rejectReason1 + ", rejectReason2=" + rejectReason2 + ", replyTime1="
				+ replyTime1 + ", replyTime2=" + replyTime2 + ", roleId1="
				+ roleId1 + ", roleId2=" + roleId2
				+ ", status=" + status + ", stepNo1="
				+ stepNo1 + ", stepNo2=" + stepNo2 + ", tenderNo="
				+ tenderNo + ", underwritingRate="
				+ underwritingRate + ", visaRate="
				+ visaRate + "]";
	}

}