package com.uxb2b.ecp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.springframework.web.bind.annotation.RequestParam;


/**
 * The persistent class for the BRANCH database table.
 * 
 */
@Entity
@Table(name="BRANCH")
@NamedQuery(name="Branch.findAll", query="SELECT b FROM Branch b")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BRANCH_ID")
	private String branchId;

	@Column(name="BOSS")
	private String boss;

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

	@Column(name="TYPE_ID")
	private String typeId;
	
	@ManyToOne
    @JoinColumn(name="TYPE_ID", insertable = false, updatable = false)  
	private BranchType branchType;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="branch")
	private List<ServiceCenterMapping> serviceCenterMapping;
	

	public Branch() {
	}

	public Branch(String branchId, String boss,String chineseName,String englishName,String chineseAddr, String englishAddr,String tel,String fax,String email,String typeId){
	
		this.branchId=branchId;
		this.boss=boss;
		this.chineseName=chineseName;
		this.englishName=englishName;
		this.chineseAddr=chineseAddr;
		this.englishAddr=englishAddr;
		this.tel=tel;
		this.fax=fax;
		this.email=email;
		this.typeId=typeId;
		
		
	}
	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBoss() {
		return this.boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
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

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public BranchType getBranchType() {
		return branchType;
	}

	public void setBranchType(BranchType branchType) {
		this.branchType = branchType;
	}

	public List<ServiceCenterMapping> getServiceCenterMapping() {
		return serviceCenterMapping;
	}

	public void setServiceCenterMapping(List<ServiceCenterMapping> serviceCenterMapping) {
		this.serviceCenterMapping = serviceCenterMapping;
	}
	
}