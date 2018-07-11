package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AWARD_DATA database table.
 * 
 */
@Embeddable
public class AwardDataPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="QUATION_NO")
	private String quationNo;

	@Column(name="ITEM_NO")
	private int itemNo;

	public AwardDataPK() {
	}
	public String getQuationNo() {
		return this.quationNo;
	}
	public void setQuationNo(String quationNo) {
		this.quationNo = quationNo;
	}
	public int getItemNo() {
		return this.itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AwardDataPK)) {
			return false;
		}
		AwardDataPK castOther = (AwardDataPK)other;
		return 
			this.quationNo.equals(castOther.quationNo)
			&& (this.itemNo == castOther.itemNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.quationNo.hashCode();
		hash = hash * prime + this.itemNo;
		
		return hash;
	}
}