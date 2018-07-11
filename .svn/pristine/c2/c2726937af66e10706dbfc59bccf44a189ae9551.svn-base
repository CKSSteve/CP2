package com.uxb2b.ecp.persist.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.persist.IUserRepositoryPersist;
import com.uxb2b.ecp.repository.UserRepository;
import com.uxb2b.ecp.service.SystemInfo;

@Repository
@Transactional
public class UserRepositoryPersistImpl implements IUserRepositoryPersist{

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public void updateLoginSuccess(String userId, int loginCount, Timestamp lastLoginTime) {
		
		userRepository.updateLoginSuccess(userId, loginCount, lastLoginTime,SystemInfo.ERROR_COUNT_CLEAR);

	}

	@Override
	public User findByUserIdAndpassword(String userId, String pwd) {
		
		return userRepository.findByUserIdAndPassword(userId, pwd);
	}

	@Override
	public User findByUserId(String userId) {
		
		return userRepository.findByUserId(userId);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void updateLoginError(String userId, int errorCount, Timestamp lastErrorTime) {
		
		userRepository.updateLoginError(userId, errorCount, lastErrorTime);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void changePwd(String userId, String pwd,Timestamp changePasswordTime) {

		userRepository.changePwd(userId, pwd,changePasswordTime);
		
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void personalUserSave(User user) {

		userRepository.save(user);
		
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Page<User> getAllUserPage(int pageNumber, int pageSize) {

		PageRequest pageRequest = buildPageRequest(pageNumber,pageSize);
		return userRepository.findAll(pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize);
	}

	@Override
	public Page<User> getUserByBranchId(String branchId, int pageNumber, int pageSize) {

		Pageable pageRequest = buildPageRequest(pageNumber,pageSize);
		
		return userRepository.findByBranchId(branchId, pageRequest);
	}
	
	@Override
	public List<User> getUserByRoleId(int roleId ,String branchId){
		
		return userRepository.findByRoleIdAndBranchId(roleId ,branchId);
	}
}
