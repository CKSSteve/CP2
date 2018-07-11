package com.uxb2b.ecp.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserProfileBean implements UserDetails {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username; //實際是userId
    private String password;
    private Collection<GrantedAuthority> authorities;

    //以下自訂所需屬性
    private String viewUserName;
    private int roleId;
    private String roleName;
    private String message;
    private long changePwdtime;
    private String bankChineseName;
    private String branchId;
 


    public UserProfileBean (String username,String password,String viewUserName, int roleId, String roleName,long changePwdtime,Collection<GrantedAuthority> authorities,String message,String bankChineseName,String branchId) {

    	this.username = username;
    	this.password = password;
        this.viewUserName = viewUserName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.changePwdtime = changePwdtime;
        this.authorities = authorities;
        this.message = message;
        this.bankChineseName = bankChineseName;
        this.branchId=branchId;
    }    
    
    public UserProfileBean(String message){
    	
    	this.message = message;
    	
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
    
	/**
	 *  實際是UserId
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getViewUserName() {
		return viewUserName;
	}

	public void setViewUserName(String viewUserName) {
		this.viewUserName = viewUserName;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getChangePwdtime() {
		return changePwdtime;
	}

	public void setChangePwdtime(long changePwdtime) {
		this.changePwdtime = changePwdtime;
	}

	public String getBankChineseName() {
		return bankChineseName;
	}

	public void setBankChineseName(String bankChineseName) {
		this.bankChineseName = bankChineseName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
}
