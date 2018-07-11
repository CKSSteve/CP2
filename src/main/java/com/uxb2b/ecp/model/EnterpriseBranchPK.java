package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ENTERPRISE_BRANCH database table.
 * 
 */
@Embeddable
public class EnterpriseBranchPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COMPANY_ID")
	private String companyId;

	@Column(name="UNI")
	private String uni;

	public EnterpriseBranchPK() {
	}
	public String getCompanyId() {
		return this.companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getUni() {
		return this.uni;
	}
	public void setUni(String uni) {
		this.uni = uni;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EnterpriseBranchPK)) {
			return false;
		}
		EnterpriseBranchPK castOther = (EnterpriseBranchPK)other;
		return 
			this.companyId.equals(castOther.companyId)
			&& this.uni.equals(castOther.uni);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.companyId.hashCode();
		hash = hash * prime + this.uni.hashCode();
		
		return hash;
	}
}