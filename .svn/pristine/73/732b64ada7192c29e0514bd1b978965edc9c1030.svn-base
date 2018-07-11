package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SERVICE_CENTER_MAPPING database table.
 * 
 */
@Embeddable
public class ServiceCenterMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CENTER_ID")
	private String centerId;

	@Column(name="BRANCH_ID")
	private String branchId;

	public ServiceCenterMappingPK() {
	}
	
	public ServiceCenterMappingPK(String centerId,String branchId){
		this.centerId=centerId;
		this.branchId=branchId;
	}
	public String getCenterId() {
		return this.centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getBranchId() {
		return this.branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ServiceCenterMappingPK)) {
			return false;
		}
		ServiceCenterMappingPK castOther = (ServiceCenterMappingPK)other;
		return 
			this.centerId.equals(castOther.centerId)
			&& this.branchId.equals(castOther.branchId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.centerId.hashCode();
		hash = hash * prime + this.branchId.hashCode();
		
		return hash;
	}
}