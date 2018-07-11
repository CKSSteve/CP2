package com.uxb2b.ecp.business;


/**
 * 銀行人員操作記錄檔
 * @author steve
 *
 */
public interface IUserLogBusiness {

	/**
	 * 登入失敗超過3次
	 * @param userId 帳號
	 * @param userName 姓名
	 */
	public void LoginErrorOverLog(String userId,String userName);
	
	/**
	 * 登入成功
	 * @param userId 帳號
	 * @param userName 姓名
	 */
	public void LoginSuccessLog(String userId,String userName);
	
	/**
	 * 登入失敗
	 * @param userId 帳號
	 * @param userName 姓名
	 */
	public void LoginErrorLog(String userId,String userName);
	
	/**
	 * 帳號已停用
	 * @param userId 帳號
	 * @param userName 姓名
	 */
	public void LoginUserStop(String userId,String userName);
	
	/**
	 * 登出
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param logContent 操作內容說明
	 */
	public void LogoutLog(String userId,String userName,String logContent);
	
	/**
	 * 修改密碼
	 * @param userId 帳號
	 * @param userName 姓名
	 */
	public void updateChangePwd(String userId,String userName);
	
	/**
	 * 個人資料修改
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param logContent 操作內容說明
	 */
	public void personalUserSave(String userId,String userName,String logContent);
	
	/**
	 * 分行管理
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param action 操作功能的方法 
	 * @param logContent 操作內容說明
	 */
	public void maintainBranch(String userId,String userName,String action,String logContent);
	
	/**
	 * 角色權限管理
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param action 操作功能的方法 
	 * @param logContent 操作內容說明
	 */
	public void roleSetting(String userId,String userName,String action,String logContent);
	
	/**
	 * 操作失敗
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param functionId 操作功能
	 * @param actionId 操作功能的方法 
	 * @param logContent 操作內容說明
	 */
	public void ErrorLog(String userId,String userName,String functionId,String actionId,String logContent);
	
	/**
	 * 操作成功
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param functionId 操作功能
	 * @param actionId 操作功能的方法 
	 * @param logContent 操作內容說明
	 */
	public void SuccessLog(String userId,String userName,String functionId,String actionId,String logContent);
	
	/**
	 * 帳號管理
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param action 操作功能的方法 
	 * @param logContent 操作內容說明
	 */
	public void accountSetting(String userId,String userName,String action,String logContent);
	
	/**
	 * 刪除訊息
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param logContent 操作內容說明
	 */
	public void messageInboxDelete(String userId,String userName,String logContent);
	
	/**
	 * 報價登錄
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param action 操作功能的方法
	 * @param logContent 操作內容說明
	 */
	public void quotionEntry(String userId,String userName,String action,String logContent);

	/**
	 * 報價審核
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param action 操作功能的方法
	 * @param logContent 操作內容說明
	 */
	public void quotionReview(String userId,String userName,String action,String logContent);
}
