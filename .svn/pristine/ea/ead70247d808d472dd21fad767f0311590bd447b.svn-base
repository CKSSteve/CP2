package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FLOW_STEP database table.
 * 
 */
@Entity
@Table(name="FLOW_STEP")
@NamedQuery(name="FlowStep.findAll", query="SELECT f FROM FlowStep f")
public class FlowStep implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FlowStepPK id;

	@Column(name="FINAL_STEP")
	private String finalStep;

	@Column(name="ROLE_ID")
	private int roleId;

	public FlowStep() {
	}

	public FlowStepPK getId() {
		return this.id;
	}

	public void setId(FlowStepPK id) {
		this.id = id;
	}

	public String getFinalStep() {
		return this.finalStep;
	}

	public void setFinalStep(String finalStep) {
		this.finalStep = finalStep;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}