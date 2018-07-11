package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BRANCH_TYPE database table.
 * 
 */
@Entity
@Table(name="BRANCH_TYPE")
@NamedQuery(name="BranchType.findAll", query="SELECT b FROM BranchType b")
public class BranchType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TYPE_ID")
	private String typeId;

	@Column(name="MEMO")
	private String memo;

	@Column(name="TYPE_NAME")
	private String typeName;

	public BranchType() {
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}