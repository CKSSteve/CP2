package com.uxb2b.ecp.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.uxb2b.ecp.bean.EmailValueBean;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.persist.IUserRepositoryPersist;


public class MailService {

	private Logger log = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private SystemInfo systemInfo;
	
	@Autowired
	private FreemarkerService freemarkerService;
	
	@Autowired
	private IUserRepositoryPersist iUserRepositoryPersist;
	
    private JavaMailSender mailSender;
    
    private SimpleMailMessage simpleMailMessage;
    
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }
	
	public void sendMail(String mailType,EmailValueBean emailBean,int roleId ,String branchId){
		
    	String subject = null;
    	String[] emailAddrs = null;
    	List<String> emailList = new ArrayList();
    	String body = null;
    	subject=systemInfo.getSendMailInfo().get(mailType+"_Subject");
    	
    	try {
			body = freemarkerService.generateByEmailTemplate(mailType, emailBean);
		} catch (Exception e) {
			log.error("SendMail Error : InitMailInfo generateByEmailTemplate Error：{}", e.getMessage());
		}
    	
    	List<User> userList =iUserRepositoryPersist.getUserByRoleId(roleId,branchId);
    	userList.forEach(user->{
    		if(StringUtils.isNotBlank(user.getEmail())){
    			emailList.add(user.getEmail());
    		}
    	});
    	if(emailList.size()>0){
        	emailAddrs=emailList.toArray(new String[emailList.size()]);
        	MimeMessage message = mailSender.createMimeMessage();  	
        	try {
    			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
    			helper.setFrom(simpleMailMessage.getFrom());		
    			helper.setSubject(subject);
    	    	helper.setText(body, true);	
    	    	helper.setTo(emailAddrs);
    	    	mailSender.send(message);
    		} catch (Exception e) {
    			e.printStackTrace();
    			log.error("SendMail Error : Send Error：{}", e.getMessage());
    		}
    	}else{
    		log.info("無收件人 不發送MAIL");
    	}

	}
	
}
