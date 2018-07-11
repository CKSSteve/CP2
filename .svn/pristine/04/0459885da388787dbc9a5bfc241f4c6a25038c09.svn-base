//============================================================
//  
// copyright © 2017 uxb2b corporation. all rights reserved
//  
//============================================================

package com.uxb2b.ecp.types;

/**
 * 取得Table:SYS_CODE-->code_type(大類)及code_key(分類) 
 * 
 * @author takashi
 *
 */
public enum SysCodeType {
	CURRENCY("CURRENCY"), //幣別
	CURRENCY_TWD("TWD"),  //幣別-新台幣
	CURRENCY_USD("USD"),  //幣別-美金
	REJECTA1("REJECT-A1"),//類別-報價
	REJECTA1_1("1"),//報價金額錯誤
	REJECTA1_2("2"),//報價利率錯誤
	REJECTA1_3("3"),//尚可承作總金額錯誤
	REJECTA1_4("4"),//尚可承作利率錯誤
	REJECTA1_5("5"),//應改為不報價
	REJECTA1_6("6"),//應改為報價
	
	REJECTB1("REJECT-B1"),//類別-本票
	REJECTB1_1("1"),//票券批號錯誤
	REJECTB1_2("2"),//應改為放棄得標
	REJECTB1_3("3"),//應改為同意得標
	
	REJECTB5("REJECT-B5"),//類別-費率
	REJECTB5_1("1"),//交割服務費率錯誤
	REJECTB5_2("2"),//承銷手續費率錯誤
	REJECTB5_3("3"),//簽證費率錯誤
	REJECTB5_4("4");//保證費率錯誤
	
	private String value;
	
	private SysCodeType(String var) {
		this.value = var;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
