package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FLOW_FUNCTION database table.
 * 
 */
@Embeddable
public class FlowFunctionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FLOW_ID")
	private int flowId;

	@Column(name="FUNCTION_ID")
	private String functionId;

	public FlowFunctionPK() {
	}
	public int getFlowId() {
		return this.flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public String getFunctionId() {
		return this.functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FlowFunctionPK)) {
			return false;
		}
		FlowFunctionPK castOther = (FlowFunctionPK)other;
		return 
			(this.flowId == castOther.flowId)
			&& this.functionId.equals(castOther.functionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.flowId;
		hash = hash * prime + this.functionId.hashCode();
		
		return hash;
	}
}