package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SERIAL_NO database table.
 * 
 */
@Entity
@Table(name="SERIAL_NO")
@NamedQuery(name="SerialNo.findAll", query="SELECT s FROM SerialNo s")
public class SerialNo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="[TYPE]")
	private String type;

	@Column(name="SERIAL_NO")
	private short serialNo;

	public SerialNo() {
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public short getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(short serialNo) {
		this.serialNo = serialNo;
	}

}