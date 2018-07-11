package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYSTEM_PARAMETER database table.
 * 
 */
@Entity
@Table(name="SYSTEM_PARAMETER")
@NamedQuery(name="SystemParameter.findAll", query="SELECT s FROM SystemParameter s")
public class SystemParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARAMETER_ID")
	private String parameterId;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="[VALUE]")
	private String value;

	public SystemParameter() {
	}

	public String getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}