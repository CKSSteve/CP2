package com.uxb2b.ecp.persist;

import java.util.List;

import com.uxb2b.ecp.model.SysCode;

/**
 * SYS_CODE TABLE
 * @author steve
 *
 */
public interface ISysCodeRepositoryPersist {

	/**
	 * 查詢SYS_CODE TABLE 
	 * 條件:CODE_TYPE
	 * 
	 * @param codeType 代碼類別
	 * @return
	 */
	public List<SysCode> findByIdCodeType(String codeType);
	
	public String findCodeValueByCodeTypeCodeKey(String codeType,String codeKey);
}
