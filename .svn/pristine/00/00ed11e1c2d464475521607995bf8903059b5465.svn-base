package com.uxb2b.ecp.business;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.model.User;

/**
 * 帳號管理
 * @author steve
 *
 */
public interface IUserBusiness {
	
	/**
	 * 人員資料修改
	 * @param userId 帳號
	 * @return
	 */
	public User personalUserSetting(String userId);
	
	/**
	 * SAVE 人員資料
	 * @param user
	 */
	public void personalUserSave(User user);
	
	/**
	 * 查詢人員資料
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<User> getAccountUserByManagement(User user,int pageNumber, int pageSize);
}
