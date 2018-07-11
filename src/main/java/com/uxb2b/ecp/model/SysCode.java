package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYS_CODE database table.
 * 
 */
@Entity
@Table(name="SYS_CODE")
@NamedQuery(name="SysCode.findAll", query="SELECT s FROM SysCode s")
public class SysCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysCodePK id;

	@Column(name="CODE_VALUE")
	private String codeValue;

	@Column(name="MEMO1")
	private String memo1;

	@Column(name="MEMO2")
	private String memo2;

	public SysCode() {
	}

	public SysCodePK getId() {
		return this.id;
	}

	public void setId(SysCodePK id) {
		this.id = id;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getMemo1() {
		return this.memo1;
	}

	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}

	public String getMemo2() {
		return this.memo2;
	}

	public void setMemo2(String memo2) {
		this.memo2 = memo2;
	}

}