package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FLOW_FUNCTION database table.
 * 
 */
@Entity
@Table(name="FLOW_FUNCTION")
@NamedQuery(name="FlowFunction.findAll", query="SELECT f FROM FlowFunction f")
public class FlowFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FlowFunctionPK id;

	public FlowFunction() {
	}

	public FlowFunctionPK getId() {
		return this.id;
	}

	public void setId(FlowFunctionPK id) {
		this.id = id;
	}

}