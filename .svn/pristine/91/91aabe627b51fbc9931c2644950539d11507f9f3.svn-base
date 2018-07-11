package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.model.RoleFunction;
import com.uxb2b.ecp.model.User;

/**
 * 角色權限管理
 * @author steve
 *
 */
public interface IRoleBusiness {

	/**
	 * 查詢所有角色資料
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Role> getAllRole(int pageNumber,int pageSize);
 	
	/**
	 * 查詢所有銀行人員資料
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * 刪除角色資料
	 * @param roleId
	 */
	public void deleteRole(int roleId);
	
	/**
	 * 查詢系統功能表
	 * @param fuctionId 功能表流水編號 
	 * @return
	 */
	public List<Function> getFunctionByFunctionId(String fuctionId);
	
	/**
	 * 查詢角色所屬功能
	 * @param roleId 角色代碼
	 * @return
	 */
	public List<RoleFunction> findRoleFunctionByRoleId(String roleId);
	
	/**
	 * 刪除角色所屬功能
	 * @param roleId 角色代碼
	 */
	public void deleteRoleFunctionByRoleId(int roleId);
	
	/**
	 * 修改角色所屬功能
	 * @param roleId 角色代碼
	 * @param A 標單作業
	 * @param B 本票作業
	 * @param D 管理作業
	 */
	public void settingRoleFunction(int roleId,String A,String B,String C,String D);
	
	/**
	 * SAVE ROLE
	 * @param role
	 */
	public void saveRole(Role role);
	
	/**
	 * 取得最新角色資料
	 * @return
	 */
	public List<Role> getNewRole();
	
	/**
	 * 查詢所有角色資料
	 * @return
	 */
	public List<Role> findAllRole();
	
	/**
	 * 查詢角色資料
	 * @param roleId 角色代碼
	 * @return
	 */
	public List<Role> findRoleNot(List<Integer> roleId);
	
	/**
	 * 查詢角色資料
	 * @param roleId 角色代碼
	 * @return
	 */
	public Role findByRoleId(int roleId);
}
