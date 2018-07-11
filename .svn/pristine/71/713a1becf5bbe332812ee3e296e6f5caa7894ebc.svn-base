package com.uxb2b.ecp.persist;

import java.util.List;

import com.uxb2b.ecp.bean.FunctionBean;
import com.uxb2b.ecp.model.RoleFunction;

/**
 * ROLE_FUNCTION TABLE
 * @author steve
 *
 */
public interface IRoleFunctionRepositoryPersist {

	/**
	 * 查詢ROLE_FUNCTION TABLE 所有資料
	 * 
	 * @return
	 */
	public List<RoleFunction> getAllRoleFunction();
	
	/**
	 * 查詢ROLE_FUNCTION TABLE
	 * 條件:ROLE_ID
	 * 
	 * @param roleId 銀行角色代碼
	 * @return
	 */
	public List<RoleFunction> findByIdRoleId(int roleId);
	
	/**
	 * 刪除ROLE_FUNCTION TABLE的資料
	 * 條件:ROLE_ID
	 * 
	 * @param roleId 銀行角色代碼
	 */
	public void deleteByRoleId(int roleId);
	
	/**
	 * SAVE ROLE_FUNCTION
	 * 
	 * @param roleFunctionList
	 */
	public void saveByRoleFunctionList(List<RoleFunction> roleFunctionList);
	
	/**
	 * 查詢ROLE_FUNCTION TABLE
	 * ROLE_FUNCTION JOIN FUNCTION
	 * 
	 * @return
	 */
	public List<FunctionBean> getJoinRoleFunction();
	
}
