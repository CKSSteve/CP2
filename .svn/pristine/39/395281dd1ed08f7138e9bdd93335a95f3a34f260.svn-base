//============================================================
// 
// copyright © 2017 uxb2b corporation. all rights reserved
//  
//============================================================

package com.uxb2b.ecp.types;

/**
 * 取得Table:FUNCTION-->function_id類型 
 * 
 * @author takashi
 *
 */
public enum FunctionType {
	LOGIN("Login"),   //登入
	LOGOUT("Logout"), //登出
	/** A-標單作業  */
	QUOTATION_REGISTER("A1"), //報價登錄
	QUOTATION_AUDIT("A2"), //報價審核
	TENDER_DOCUMENTS_INQUIRE("A3"), //標單查詢
	QUOTATION_INQUIRE("A4"), //報價單查詢
	/** B-本票作業  */
	AWARD_DATA_REGISTER("B1"), //票券批號登錄(得標確認)
	AWARD_DATA_AUDIT("B2"), //票券批號(得標確認)審核
	PROMISSORY_CONFIRM("B3"), //本票發行確認
	PROMISSORY_AUDIT("B4"), //本票發行審核
	PROMISSORY_ACCOUNTING_REGISTER("B5"), //本票利率明細登錄
	PROMISSORY_ACCOUNTING_VERIFY("B6"), //本票利率明細審核
	PROMISSORY_INQUIRE("B7"), //本票查詢
	/** D-管理作業*/
	ROLE_SETTING("D1"), //角色權限維護
	ACCOUNT_SETTING("D2"), //帳號管理
	PERSONAI_SETTING("D3"), //個人資料維護
	ENTERPRISE_SETTING("D4"), //企業資料維護
	BRANCH_SETTING("D5"), //分行資料維護
	DATA_RESEND("D6"), //資料重新傳送
	REJECTB1("D7"), //使用者存取記錄查詢
	APPROVALFLOW_SETTING("D8"), //簽核流程維護
	/** MessageInBox */
	MESSAGE_IN_BOX("MessageInBox"); //訊息匣
	
	private String value;
	
	private FunctionType(String var) {
		this.value = var;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
