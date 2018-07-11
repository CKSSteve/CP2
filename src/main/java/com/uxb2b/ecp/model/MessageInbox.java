package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MESSAGE_INBOX database table.
 * 
 */
@Entity
@Table(name="MESSAGE_INBOX")
@NamedQuery(name="MessageInbox.findAll", query="SELECT m FROM MessageInbox m")
public class MessageInbox implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MESSAGE_ID")
	private long messageId;

	@Column(name="BRANCH_ID")
	private String branchId;

	@Column(name="CONTENT")
	private String content;

	@Column(name="MESSAGE_TIME")
	private Timestamp messageTime;

	@Column(name="RECEIVER_ROLE")
	private int receiverRole;

	@Column(name="SENDER")
	private String sender;

	@Column(name="SUBJECT")
	private String subject;

	public MessageInbox() {
	}
	
	public MessageInbox(String branchId,String content,Timestamp messageTime,int receiverRole,String sender, String subject) {
		this.branchId=branchId;
		this.content=content;
		this.messageTime=messageTime;
		this.receiverRole=receiverRole;
		this.sender=sender;
		this.subject=subject;
	}

	public long getMessageId() {
		return this.messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getMessageTime() {
		return this.messageTime;
	}

	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}

	public int getReceiverRole() {
		return this.receiverRole;
	}

	public void setReceiverRole(int receiverRole) {
		this.receiverRole = receiverRole;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}