package com.uxb2b.ecp.business;

import java.sql.Timestamp;

import com.uxb2b.ecp.model.User;

/**
 * 登錄
 * @author steve
 *
 */
public interface ILoginBusiness {

	/**
	 * 查詢USER
	 * @param userId 帳號
	 * @param pwd 密碼
	 * @return
	 */
	public User findByUserIdAndpassword(String userId,String pwd);
	
	/**
	 * 查詢USER
	 * @param userId 帳號
	 * @return
	 */
	public User findByUserId(String userId);
	
	/**
	 * 查詢USER
	 * @param userId 帳號
	 * @return
	 */
	public User findByUserIdAndStatus(String userId,String userStatus);
	
	/**
	 * 登錄成功
	 * @param user
	 */
	public void loginSusses(User user);
	
	/**
	 * 登錄失敗
	 * @param user
	 */
	public void loginError(User user);
	
	/**
	 * 計算密碼有效天數
	 * @param changePasswordTime 變更密碼時間
	 * @return
	 */
	public long pwdChangeday(Timestamp changePasswordTime);
	
	/**
	 * 
	 * @param userId 帳號
	 * @param pwd 密碼
	 */
	public void changePwd(String userId, String pwd);
		
}
