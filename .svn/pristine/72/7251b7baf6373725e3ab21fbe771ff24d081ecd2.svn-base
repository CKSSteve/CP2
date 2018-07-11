package com.uxb2b.ecp.persist;


import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.model.Role;

/**
 * ROLE TABLE
 * 
 * @author steve
 *
 */
public interface IRoleRepositoryPersist {

	/**
	 * 查詢 ROLE TABLE 所有資料
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Role> getAllRole(int pageNumber,int pageSize);
	
	/**
	 * 刪除ROLE TABLE 的資料
	 * 條件:ROLE_ID
	 * 
	 * @param roleId 角色代碼
	 */
	public void deleteRole(int roleId);
	
	/**
	 * 查詢 ROLE TABLE 所有的ROLE_ID
	 * 
	 * @return
	 */
	public List<Integer> getAllRoleId();
	
	/**
	 * SAVE ROLE
	 * 
	 * @param role
	 */
	public void saveRole(Role role);
	
	/**
	 * 取得ROLE TABLE最新的一筆資料
	 * 
	 * @return
	 */
	public List<Role> getNewRole();
	
	/**
	 * 查詢 ROLE TABLE 所有資料
	 * 
	 * @return
	 */
	public List<Role> findAllRole();
	
	/**
	 * 查詢ROLE TABLE
	 * 條件:NOT ROLE_ID
	 * 
	 * @param roleId 角色代碼
	 * @return
	 */
	public List<Role> findRoleNot(List<Integer> roleId);
	
	/**
	 * 查詢ROLE TABLE
	 * 條件:ROLE_ID
	 * 
	 * @param roleId 角色代碼
	 * @return
	 */
	public Role findByRoleId(int roleId);
}
