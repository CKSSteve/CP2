package com.uxb2b.ecp.business.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.model.UserLog;
import com.uxb2b.ecp.persist.IUserLogRepositoryPersist;
import com.uxb2b.ecp.service.SystemInfo;


@Component
public class UserLogBusinessImpl implements IUserLogBusiness{

	@Autowired
	private IUserLogRepositoryPersist iUserLogRepositoryPersist;
	
	@Override
	public void LoginErrorOverLog(String userId, String userName) {
		
		UserLog userLog = new UserLog();
		
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_LOGIN, SystemInfo.USER_LOG_ACTION_LOGIN,
				new Timestamp(new Date().getTime()), SystemInfo.USER_LOG_CONTENT_LOGINERROR_THREE,
				userId, userName, SystemInfo.USER_LOG_STATUS_ERROR);		
		
		iUserLogRepositoryPersist.saveUserLog(userLog);

	}

	@Override
	public void LoginSuccessLog(String userId, String userName) {

		UserLog userLog = new UserLog();
		
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_LOGIN, SystemInfo.USER_LOG_ACTION_LOGIN,
				new Timestamp(new Date().getTime()), SystemInfo.USER_LOG_CONTENT_LOGIN_NORMAL,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
	}

	@Override
	public void LoginErrorLog(String userId, String userName) {
		
		UserLog userLog = new UserLog();
		
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_LOGIN, SystemInfo.USER_LOG_ACTION_LOGIN,
				new Timestamp(new Date().getTime()), SystemInfo.USER_LOG_CONTENT_LOGIN_NORMAL,
				userId, userName, SystemInfo.USER_LOG_STATUS_ERROR);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void LoginUserStop(String userId, String userName) {

		UserLog userLog = new UserLog();
		
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_LOGIN, SystemInfo.USER_LOG_ACTION_LOGIN,
				new Timestamp(new Date().getTime()), SystemInfo.USER_LOG_CONTENT_USER_STOP,
				userId, userName, SystemInfo.USER_LOG_STATUS_ERROR);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}
	
	@Override
	public void LogoutLog(String userId,String userName,String logContent){
		
		UserLog userLog = new UserLog();
		
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_LOGOUT, SystemInfo.USER_LOG_ACTION_LOGIN,
				new Timestamp(new Date().getTime()), SystemInfo.USER_LOG_CONTENT_LOGOUT,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
//		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}
	
	@Override
	public void updateChangePwd(String userId,String userName) {

		UserLog userLog = new UserLog();
		
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_LOGIN, SystemInfo.USER_LOG_ACTION_LOGIN,
				new Timestamp(new Date().getTime()), SystemInfo.USER_LOG_CONTENT_PWDCHANGE,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void personalUserSave(String userId, String userName,String logContent) {

		UserLog userLog = new UserLog();	
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_PERSONIUSERSETTING, SystemInfo.USER_LOG_ACTION_MODIFY,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
	}

	@Override
	public void maintainBranch(String userId,String userName,String action,String logContent) {

		UserLog userLog = new UserLog();	
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_BRANCH_SETTING, action,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void ErrorLog(String userId,String userName,String functionId, String actionId, String logContent) {

		UserLog userLog = new UserLog();	
		userLog = new UserLog(functionId, actionId,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_ERROR);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void roleSetting(String userId, String userName, String action, String logContent) {
		
		UserLog userLog = new UserLog();	
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_ROLE_SETTING, action,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void accountSetting(String userId, String userName, String action, String logContent) {
 
		UserLog userLog = new UserLog();	
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_ACCOUNT_SETTING, action,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void messageInboxDelete(String userId, String userName, String logContent) {

		UserLog userLog = new UserLog();	
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_MESSAGEINBOX, SystemInfo.USER_LOG_ACTION_DELETE,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void quotionEntry(String userId, String userName, String action, String logContent) {
		
		UserLog userLog = new UserLog();	
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_QUOTION_ENTRY, action,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void quotionReview(String userId, String userName, String action, String logContent) {
		
		UserLog userLog = new UserLog();	
		userLog = new UserLog(SystemInfo.USER_LOG_FUNCTION_ID_QUOTION_REVIEW, action,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

	@Override
	public void SuccessLog(String userId, String userName, String functionId, String actionId, String logContent) {
		UserLog userLog = new UserLog();	
		userLog = new UserLog(functionId, actionId,
				new Timestamp(new Date().getTime()),logContent,
				userId, userName, SystemInfo.USER_LOG_STATUS_SUCCESS);
		
		iUserLogRepositoryPersist.saveUserLog(userLog);
		
	}

}
