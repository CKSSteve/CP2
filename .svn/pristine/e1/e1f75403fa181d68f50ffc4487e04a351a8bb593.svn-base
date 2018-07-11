package com.uxb2b.ecp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the [FUNCTION] database table.
 * 
 */
@Entity
@Table(name="CP2_FUNCTION")
@NamedQuery(name="Function.findAll", query="SELECT f FROM Function f")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FUNCTION_ID")
	private String functionId;

	@Column(name="[ALIAS]")
	private String alias;

	@Column(name="FUNCTION_NAME")
	private String functionName;

	@Column(name="MEMO")
	private String memo;

	@Column(name="ORDER_INDEX")
	private Integer orderIndex;

	@Column(name="PARENT_ID")
	private String parentId;

	@Column(name="SHOW_LINK")
	private Integer showLink;

	@Column(name="URI")
	private String uri;
	
	@Column(name="SAVE_LOG")
	private String saveLog;
	
	@Column(name="START_FLOW")
	private String startFlow;
	
	@OneToMany(mappedBy="function")
	private List<RoleFunction> roleFunction;
	
	public Function() {
	}

	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getShowLink() {
		return this.showLink;
	}

	public void setShowLink(Integer showLink) {
		this.showLink = showLink;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getSaveLog() {
		return saveLog;
	}

	public void setSaveLog(String saveLog) {
		this.saveLog = saveLog;
	}

	public String getStartFlow() {
		return startFlow;
	}

	public void setStartFlow(String startFlow) {
		this.startFlow = startFlow;
	}

	public List<RoleFunction> getRoleFunction() {
		return roleFunction;
	}

	public void setRoleFunction(List<RoleFunction> roleFunction) {
		this.roleFunction = roleFunction;
	}
	
}