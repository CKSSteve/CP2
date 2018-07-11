package com.uxb2b.ecp.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.business.IUserBusiness;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.persist.IUserRepositoryPersist;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class UserBusinessImpl implements IUserBusiness{

	@Autowired
	private IUserRepositoryPersist iUserRepositoryPersist;
	
	@Override
	public User personalUserSetting(String userId) {
		
		return iUserRepositoryPersist.findByUserId(userId);		
	}

	@Override
	public void personalUserSave(User user) {
		
		iUserRepositoryPersist.personalUserSave(user);
		
	}

	@Override
	public Page<User> getAccountUserByManagement(User user, int pageNumber, int pageSize) {

		Page<User> userPage;
		
		if(SystemInfo.HEADOFFICETYPEID.equals(user.getBranch().getTypeId())){
			userPage=iUserRepositoryPersist.getAllUserPage(pageNumber, pageSize);
		}else{
			userPage=iUserRepositoryPersist.getUserByBranchId(user.getBranchId(), pageNumber, pageSize);
		}
	
		return userPage;
	}
	

}
