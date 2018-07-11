package com.uxb2b.ecp.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.service.SystemInfo;

public class AuthSuccessInterceptor extends SimpleUrlAuthenticationSuccessHandler{
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
		UserProfileBean userDetails = (UserProfileBean) authentication.getPrincipal();
		String message=userDetails.getMessage();
		if(StringUtils.isBlank(message)){
			setDefaultTargetUrl("/login/loginAuth");		
		}else {
			setDefaultTargetUrl("/login/changPassword");
		}		
            
        super.onAuthenticationSuccess(request, response, authentication);
    }
	
}
