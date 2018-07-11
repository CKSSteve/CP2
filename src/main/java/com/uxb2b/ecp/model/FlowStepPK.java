package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FLOW_STEP database table.
 * 
 */
@Embeddable
public class FlowStepPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FLOW_ID")
	private int flowId;

	@Column(name="STEP_NO")
	private int stepNo;

	public FlowStepPK() {
	}
	public int getFlowId() {
		return this.flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public int getStepNo() {
		return this.stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FlowStepPK)) {
			return false;
		}
		FlowStepPK castOther = (FlowStepPK)other;
		return 
			(this.flowId == castOther.flowId)
			&& (this.stepNo == castOther.stepNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.flowId;
		hash = hash * prime + this.stepNo;
		
		return hash;
	}
}