package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CTBFLBT2 database table.
 * 
 */
@Embeddable
public class Ctbflbt2PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TENDER_NO")
	private String tenderNo;

	@Column(name="ITEM_NO")
	private int itemNo;

	public Ctbflbt2PK() {
	}
	public String getTenderNo() {
		return this.tenderNo;
	}
	public void setTenderNo(String tenderNo) {
		this.tenderNo = tenderNo;
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
		if (!(other instanceof Ctbflbt2PK)) {
			return false;
		}
		Ctbflbt2PK castOther = (Ctbflbt2PK)other;
		return 
			this.tenderNo.equals(castOther.tenderNo)
			&& (this.itemNo == castOther.itemNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tenderNo.hashCode();
		hash = hash * prime + this.itemNo;
		
		return hash;
	}
}