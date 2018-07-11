package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTERPRISE_BRANCH database table.
 * 
 */
@Entity
@Table(name="ENTERPRISE_BRANCH")
@NamedQuery(name="EnterpriseBranch.findAll", query="SELECT e FROM EnterpriseBranch e")
public class EnterpriseBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnterpriseBranchPK id;

	public EnterpriseBranch() {
	}

	public EnterpriseBranchPK getId() {
		return this.id;
	}

	public void setId(EnterpriseBranchPK id) {
		this.id = id;
	}

}