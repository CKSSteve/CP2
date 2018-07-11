package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REFUSE_REASON database table.
 * 
 */
@Entity
@Table(name="REFUSE_REASON")
@NamedQuery(name="RefuseReason.findAll", query="SELECT r FROM RefuseReason r")
public class RefuseReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RefuseReasonPK id;

	@Column(name="DESCRIPTION")
	private String description;

	public RefuseReason() {
	}

	public RefuseReasonPK getId() {
		return this.id;
	}

	public void setId(RefuseReasonPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}