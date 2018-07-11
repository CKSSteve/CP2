package com.uxb2b.ecp.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IUserLogBusiness;


public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	@Autowired
	IUserLogBusiness iUserLogBusiness;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	private Logger log = LoggerFactory.getLogger(LogoutSuccessHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		UserProfileBean userDetails = (UserProfileBean) authentication.getPrincipal();

		log.info("使用者登出：{}", userDetails != null ? userDetails.getUsername() : "NULL");
		
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent="正常登出";
		log.info("使用者("+userId+")["+userName+"]登出");
		iUserLogBusiness.LogoutLog(userId, userName, logContent);

		setDefaultTargetUrl("/logoutPage");
		
		super.onLogoutSuccess(request, response, authentication);
	}
}
