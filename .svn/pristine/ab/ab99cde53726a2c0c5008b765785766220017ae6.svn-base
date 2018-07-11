package com.uxb2b.ecp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.service.SystemInfo;

@Controller
@RequestMapping(value = "/logoutPage")
public class LogoutController {
	
	private Logger log = LoggerFactory.getLogger(LogoutController.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IUserLogBusiness iUserLogBusiness;
	
	@RequestMapping
    public String main(Model model) {
        return "login";
    }
	
}
