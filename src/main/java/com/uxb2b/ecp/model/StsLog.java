package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the STS_LOG database table.
 * 
 */
@Entity
@Table(name="STS_LOG")
@NamedQuery(name="StsLog.findAll", query="SELECT s FROM StsLog s")
public class StsLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LOG_ID")
	private long logId;

	@Lob
	@Column(name="CONTENT_RQ")
	private String contentRq;
	
	@Lob
	@Column(name="CONTENT_RS")
	private String contentRs;

	@Column(name="LOG_TIME")
	private Timestamp logTime;

	@Column(name="STATUS")
	private String status;

	@Column(name="TX_ID")
	private String txId;

	public StsLog() {
	}

	public StsLog(String txId,String contentRq,Timestamp logTime){
		
		this.txId=txId;
		this.contentRq=contentRq;
		this.logTime=logTime;
	}
	
	public long getLogId() {
		return this.logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public String getContentRq() {
		return this.contentRq;
	}

	public void setContentRq(String contentRq) {
		this.contentRq = contentRq;
	}
	
	public String getContentRs() {
		return contentRs;
	}

	public void setContentRs(String contentRs) {
		this.contentRs = contentRs;
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

	public String getTxId() {
		return this.txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

}