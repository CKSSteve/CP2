package com.uxb2b.ecp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENTERPRISE database table.
 * 
 */
@Entity
@Table(name="ENTERPRISE")
@NamedQuery(name="Enterprise.findAll", query="SELECT e FROM Enterprise e")
public class Enterprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UNI")
	private String uni;

	@Column(name="CHINESE_ADDR")
	private String chineseAddr;

	@Column(name="CHINESE_NAME")
	private String chineseName;

	@Column(name="EMAIL")
	private String email;

	@Column(name="ENGLISH_ADDR")
	private String englishAddr;

	@Column(name="ENGLISH_NAME")
	private String englishName;

	@Column(name="FAX")
	private String fax;

	@Column(name="TEL")
	private String tel;
	
	@Column(name="WS_URL")
	private String wsUrl;

	public Enterprise() {
	}

	public String getUni() {
		return this.uni;
	}

	public void setUni(String uni) {
		this.uni = uni;
	}

	public String getChineseAddr() {
		return this.chineseAddr;
	}

	public void setChineseAddr(String chineseAddr) {
		this.chineseAddr = chineseAddr;
	}

	public String getChineseName() {
		return this.chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnglishAddr() {
		return this.englishAddr;
	}

	public void setEnglishAddr(String englishAddr) {
		this.englishAddr = englishAddr;
	}

	public String getEnglishName() {
		return this.englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}	

}