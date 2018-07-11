package com.uxb2b.ecp.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.service.SystemInfo;


@Controller
@RequestMapping(value = "/sessionTimeout")
public class SessionTimeoutController {
	
	private Logger log = LoggerFactory.getLogger(SessionTimeoutController.class);
	
	@RequestMapping
    public String main(HttpServletRequest request, Model model) {
		
		log.info("使用者操作逾時");
	    
		SecurityContextHolder.clearContext();
	    HttpSession session = request.getSession(false);
	    if(session != null) {
	    	log.info("使用者操作逾時，sessionid：" + session.getId());
	        session.invalidate();
	    }
	    if(request.getCookies()!=null){
		    for(Cookie cookie : request.getCookies()) {
		        cookie.setMaxAge(0);
		    }
	    }
		model.addAttribute("errorMsg", "畫面停滯時間過久或是被其他人登入，請重新登入");
		return "login";
    }
	
}
