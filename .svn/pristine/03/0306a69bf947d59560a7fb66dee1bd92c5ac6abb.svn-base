package com.uxb2b.ecp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the [ROLE] database table.
 * 
 */
@Entity
@Table(name="ROLES")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private int roleId;

	@Column(name="MEMO")
	private String memo;

	@Column(name="ROLE_NAME")
	private String roleName;
	
	public Role() {
	}
	
	public Role(String roleName,String memo){
		this.roleName=roleName;
		this.memo=memo;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}