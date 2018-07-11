//============================================================
//  
// copyright © 2017 uxb2b corporation. all rights reserved
//  
//============================================================

package com.uxb2b.ecp.types;

/**
 * 取得登入相關訊息
 * 
 * @author takashi
 *
 */
public enum LoginMessageType {
	LOGIN_DESC("正常登入"),  //Login中文說明
	LOGOUT_DESC("登出"),   //Logout中文說明
	OVERNINETYDAYS("OverNinetyDays"), //超過九十天
	FIRST_LOGIN("FirstLogin"), //第一次登入
	LOGINERROR_THREE("LoginErrorThree"), //登入錯誤超過三次
	LOGINERROR_THREE_DESC("登入失敗超過三次"), //LOGINERROR_THREE中文說明
	ACCOUNT_DISABLED("AccountDisabled"), //帳號停用
	ACCOUNT_DISABLED_DESC("此帳號已停用"), //ACCOUNT_DISABLED中文說明
	ACCOUNT_ERROR("AccountError"), //帳號錯誤
	PWDCHANGE("密碼變更"), //PWD_CHANGE中文說明
	PASSWORD_ERROR("PasswordError"); //密碼錯誤
	
	private String value;
	
	private LoginMessageType(String var) {
		this.value = var;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
