package com.uxb2b.ecp.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;


/**
 * The persistent class for the ROLE_FUNCTION database table.
 * 
 */
@Entity
@Table(name="ROLE_FUNCTION")
@NamedQuery(name="RoleFunction.findAll", query="SELECT r FROM RoleFunction r")
public class RoleFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleFunctionPK id;
	
	@ManyToOne()
    @JoinColumn(name="FUNCTION_ID", referencedColumnName="FUNCTION_ID",updatable=false,insertable = false)  
	private Function function;
	
	public RoleFunction() {
	}

	public RoleFunctionPK getId() {
		return this.id;
	}

	public void setId(RoleFunctionPK id) {
		this.id = id;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

}