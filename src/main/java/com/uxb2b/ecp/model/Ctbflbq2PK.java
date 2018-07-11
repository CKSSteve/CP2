package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CTBFLBQ2 database table.
 * 
 */
@Embeddable
public class Ctbflbq2PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="QUATION_NO")
	private String quationNo;

	@Column(name="ITEM_NO")
	private int itemNo;

	public Ctbflbq2PK() {
	}
	
	public Ctbflbq2PK(String quationNo,int itemNo) {
		this.quationNo=quationNo;
		this.itemNo=itemNo;
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
		if (!(other instanceof Ctbflbq2PK)) {
			return false;
		}
		Ctbflbq2PK castOther = (Ctbflbq2PK)other;
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