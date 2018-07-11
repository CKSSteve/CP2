package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SYS_CODE database table.
 * 
 */
@Embeddable
public class SysCodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CODE_TYPE")
	private String codeType;

	@Column(name="CODE_KEY")
	private String codeKey;

	public SysCodePK() {
	}
	public String getCodeType() {
		return this.codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeKey() {
		return this.codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SysCodePK)) {
			return false;
		}
		SysCodePK castOther = (SysCodePK)other;
		return 
			this.codeType.equals(castOther.codeType)
			&& this.codeKey.equals(castOther.codeKey);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codeType.hashCode();
		hash = hash * prime + this.codeKey.hashCode();
		
		return hash;
	}
}