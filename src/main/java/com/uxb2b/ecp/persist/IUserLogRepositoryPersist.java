package com.uxb2b.ecp.persist;

import com.uxb2b.ecp.model.UserLog;

/**
 * USER_LOG TABLE
 * 
 * @author steve
 *
 */
public interface IUserLogRepositoryPersist {
	
	/**
	 * save USER_LOG
	 * 
	 * @param userLog
	 */
	public void saveUserLog(UserLog userLog);

}
