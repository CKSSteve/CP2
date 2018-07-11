package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SERIAL_NO database table.
 * 
 */
@Embeddable
public class SerialNoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="[TYPE]")
	private String type;

	@Column(name="[YEAR]")
	private String year;

	public SerialNoPK() {
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getYear() {
		return this.year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SerialNoPK)) {
			return false;
		}
		SerialNoPK castOther = (SerialNoPK)other;
		return 
			this.type.equals(castOther.type)
			&& this.year.equals(castOther.year);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.type.hashCode();
		hash = hash * prime + this.year.hashCode();
		
		return hash;
	}
}