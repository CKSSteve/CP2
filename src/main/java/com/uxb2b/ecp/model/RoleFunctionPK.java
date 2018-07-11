package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ROLE_FUNCTION database table.
 * 
 */
@Embeddable
public class RoleFunctionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ID")
	private int roleId;

	@Column(name="FUNCTION_ID")
	private String functionId;

	public RoleFunctionPK() {
	}
	
	public RoleFunctionPK(int roleId,String functionId) {
		this.roleId=roleId;
		this.functionId=functionId;
	}
	
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
		if (!(other instanceof RoleFunctionPK)) {
			return false;
		}
		RoleFunctionPK castOther = (RoleFunctionPK)other;
		return 
			(this.roleId == castOther.roleId)
			&& this.functionId.equals(castOther.functionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.functionId.hashCode();
		
		return hash;
	}
}