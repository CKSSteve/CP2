package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the APPLICANT_STATUS database table.
 * 
 */
@Entity
@Table(name="APPLICANT_STATUS")
@NamedQuery(name="ApplicantStatus.findAll", query="SELECT a FROM ApplicantStatus a")
public class ApplicantStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATUS_CODE")
	private int statusCode;

	@Column(name="NAME")
	private String name;

	public ApplicantStatus() {
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}