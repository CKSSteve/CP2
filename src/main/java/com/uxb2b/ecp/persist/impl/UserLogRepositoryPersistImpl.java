package com.uxb2b.ecp.persist.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.UserLog;
import com.uxb2b.ecp.persist.IUserLogRepositoryPersist;
import com.uxb2b.ecp.repository.UserLogRepository;

@Repository
@Transactional
public class UserLogRepositoryPersistImpl implements IUserLogRepositoryPersist {
	
	@Autowired
	private UserLogRepository userLogRepository;

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveUserLog(UserLog userLog) {
		
		userLogRepository.save(userLog);
		
	}
}
