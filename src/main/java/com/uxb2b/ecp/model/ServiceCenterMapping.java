package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SERVICE_CENTER_MAPPING database table.
 * 
 */

@Entity
@Table(name="SERVICE_CENTER_MAPPING")
@NamedQuery(name="ServiceCenterMapping.findAll", query="SELECT s FROM ServiceCenterMapping s")
public class ServiceCenterMapping implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ServiceCenterMappingPK id;
	
	@ManyToOne
    @JoinColumn(name="CENTER_ID", insertable = false, updatable = false)  
	private Branch branch;
	
	@OneToOne
    @JoinColumn(name="BRANCH_ID", insertable = false, updatable = false)  
	private Branch oneBranch;


	public ServiceCenterMappingPK getId() {
		return this.id;
	}

	public void setId(ServiceCenterMappingPK id) {
		this.id = id;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Branch getOneBranch() {
		return oneBranch;
	}

	public void setOneBranch(Branch oneBranch) {
		this.oneBranch = oneBranch;
	}
	
}