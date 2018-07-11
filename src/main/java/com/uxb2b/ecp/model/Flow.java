package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the FLOW database table.
 * 
 */
@Entity
@Table(name="FLOW")
@NamedQuery(name="Flow.findAll", query="SELECT f FROM Flow f")
public class Flow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FLOW_ID")
	private int flowId;

	@Column(name="CREATE_TIME")
	private Timestamp createTime;

	@Column(name="CREATE_USER")
	private String createUser;

	@Column(name="FLOW_MEMO")
	private String flowMemo;

	@Column(name="FLOW_NAME")
	private String flowName;

	@Column(name="MODIFY_TIME")
	private Timestamp modifyTime;

	@Column(name="MODIFY_USER")
	private String modifyUser;
	
	public Flow() {
	}

	//bi-directional many-to-many association to CbcDec
	@ManyToMany
	@JoinTable(
		name="FLOW_FUNCTION"
		, joinColumns={
			@JoinColumn(name="FLOW_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FUNCTION_ID")
			}
		)
	private List<Function> functions;
		
	public int getFlowId() {
		return this.flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getFlowMemo() {
		return this.flowMemo;
	}

	public void setFlowMemo(String flowMemo) {
		this.flowMemo = flowMemo;
	}

	public String getFlowName() {
		return this.flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

}