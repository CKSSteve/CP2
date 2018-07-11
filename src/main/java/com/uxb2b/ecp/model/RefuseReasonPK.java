package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the REFUSE_REASON database table.
 * 
 */
@Embeddable
public class RefuseReasonPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="[TYPE]")
	private String type;

	@Column(name="REFUSE_CODE")
	private int refuseCode;

	public RefuseReasonPK() {
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRefuseCode() {
		return this.refuseCode;
	}
	public void setRefuseCode(int refuseCode) {
		this.refuseCode = refuseCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RefuseReasonPK)) {
			return false;
		}
		RefuseReasonPK castOther = (RefuseReasonPK)other;
		return 
			this.type.equals(castOther.type)
			&& (this.refuseCode == castOther.refuseCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.type.hashCode();
		hash = hash * prime + this.refuseCode;
		
		return hash;
	}
}