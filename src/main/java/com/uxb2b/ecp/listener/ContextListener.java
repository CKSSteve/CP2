package com.uxb2b.ecp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.uxb2b.ecp.repository.FunctionRepository;
import com.uxb2b.ecp.service.FunctionService;



public class ContextListener implements ServletContextListener {
	
	@Autowired
	private FunctionService functionService;
	
	@Override 
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext())
		.getAutowireCapableBeanFactory().autowireBean(this);
		functionService.putAllFunctionToContext(event.getServletContext());		
 

	}
}
