package com.uxb2b.ecp.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.business.ILoginBusiness;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.persist.IUserRepositoryPersist;
import com.uxb2b.ecp.service.AlgorithmService;
import com.uxb2b.ecp.service.SystemInfo;
import java.sql.Timestamp;
import java.util.Date;


@Component
public class LoginBusinessImpl implements ILoginBusiness {

	@Autowired
	private AlgorithmService algorithmService;
	
	@Autowired
	private IUserRepositoryPersist iUserRepositoryPersist;
	
	@Autowired
	private SystemInfo systemInfo;

	private Logger log = LoggerFactory.getLogger(LoginBusinessImpl.class);

	@Override
	public User findByUserIdAndpassword(String userId, String pwd) {
		
		return iUserRepositoryPersist.findByUserIdAndpassword(userId, pwd);
	}

	@Override
	public User findByUserId(String userId) {
		
		return iUserRepositoryPersist.findByUserId(userId);
	}

	@Override
	public User findByUserIdAndStatus(String userId, String userStatus) {
		
		return null;
	}

	@Override
	public void loginSusses(User user) {
		iUserRepositoryPersist.updateLoginSuccess(user.getUserId(), user.getLoginCount()+1, new Timestamp(new Date().getTime()));
		
	}

	@Override
	public void loginError(User user) {
		
		iUserRepositoryPersist.updateLoginError(user.getUserId(), user.getErrorCount()+1, new Timestamp(new Date().getTime()));
		
	}

	@Override
	public long pwdChangeday(Timestamp changePasswordTime) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		long changeTime =currentTime.getTime()-changePasswordTime.getTime();
		long day=changeTime/(24*60*60*1000);
		return 90-day;
	}

	@Override
	public void changePwd(String userId, String pwd) {

		String md5Pwd=algorithmService.toEncryptMD5(pwd);
		int loginCount=iUserRepositoryPersist.findByUserId(userId).getLoginCount();
		iUserRepositoryPersist.changePwd(userId, md5Pwd,new Timestamp(new Date().getTime()));
		
	}

}
