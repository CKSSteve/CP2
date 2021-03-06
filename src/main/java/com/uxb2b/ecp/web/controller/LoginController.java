package com.uxb2b.ecp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
<<<<<<< .mine
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
||||||| .r108
=======
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
>>>>>>> .r212
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uxb2b.ecp.bean.TodoBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.ILoginBusiness;
import com.uxb2b.ecp.business.ITodoBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 登入
 * @author steve
 *
 */

@Controller
@RequestMapping(value = "/login", method = RequestMethod.POST)
public class LoginController {

	private Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private ILoginBusiness iLoginBusiness;
	
	@Autowired
	private IUserLogBusiness iUserLogBusiness;
	
	@Autowired
	private IMessageInboxRepositoryPersist iMessageInboxRepositoryPersist; 
	
	@Autowired
	ITodoBusiness iTodoBusiness;
	
	@Autowired
	private SystemInfo systemInfo;
<<<<<<< .mine
	
	@Autowired
	private SessionRegistry sessionRegistry;
||||||| .r108
=======
	
	boolean isLogining=false;
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;
>>>>>>> .r212

	boolean isLogining=false;
	
	public String main(Model model) {
		return "login";
	}

	/**登入成功前往待辦事項頁面
	 * 
	 * @return todoBeanList 待辦清單
	 * @return messageCount 訊息數量
	 * @return bankName 分行中文名稱
	 */
	
	@RequestMapping(value = "/loginAuth", method = RequestMethod.GET)
	public String loginSuccess(Model model) {

	UserProfileBean userDetails = systemInfo.getUserProfileBean();
	int roleId = userDetails.getRoleId();
	String branchId=userDetails.getBranchId();
	int messageCount=iMessageInboxRepositoryPersist.getMessageInboxCountByReceiverRole(roleId,branchId);
	List<TodoBean> todoBeanList=iTodoBusiness.getTodoList(roleId,branchId);
	String sessionId = sessionRegistry.getAllSessions(userDetails, false).get(0).getSessionId();
	
<<<<<<< .mine
	sessionRegistry.getAllPrincipals().forEach(principals->{
		if(userDetails.getUsername().equals(((UserProfileBean)principals).getUsername())){
			List<SessionInformation> sessionList = sessionRegistry.getAllSessions(principals, false);
			sessionList.forEach(session->{
				if(!sessionId.equals(session.getSessionId())){
					isLogining=true;
				}			
			});
		}
	});
	if(isLogining){
		model.addAttribute("duplicate", true);
		isLogining=false;
		return "login";
	}else{	
		model.addAttribute("todoBeanList", todoBeanList);	
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("bankName", systemInfo.getStaticParameter().get(SystemInfo.BANK_NAME));
		return "common/todo";
		}
||||||| .r108
	model.addAttribute("todoBeanList", todoBeanList);	
	model.addAttribute("messageCount", messageCount);
	model.addAttribute("bankName", systemInfo.getStaticParameter().get(SystemInfo.BANK_NAME));
	return "common/todo";
=======
	sessionRegistry.getAllPrincipals().forEach(principals->{
		if(userDetails.getUsername().equals(((UserProfileBean)principals).getUsername())){
			List<SessionInformation> sessionList = sessionRegistry.getAllSessions(principals, false);
			sessionList.forEach(session->{
				if(!sessionId.equals(session.getSessionId())){
					isLogining=true;
				}			
			});
		}
	});
	if(isLogining){
		model.addAttribute("duplicate", userDetails.getUsername());
		isLogining=false;
		return "login";
	}else{	
		model.addAttribute("todoBeanList", todoBeanList);	
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("bankName", systemInfo.getStaticParameter().get(SystemInfo.BANK_NAME));
		return "common/todo";
		}
>>>>>>> .r212
	}
	
	/**登入後-變更密碼導入頁
	 * 
	 * @param authentication
	 * @return changePasswordMsg 需要修改密碼的原因
	 */
	
	
	@RequestMapping(value = "/changPassword", method = RequestMethod.GET)
	public String changPassword(Model model ,Authentication authentication) {
		UserProfileBean userDetails = (UserProfileBean) authentication.getPrincipal();
		String message=userDetails.getMessage();
		if(SystemInfo.LOGIN_MESSAGE_OVERTHIRTYDAY.equals(message)){
		model.addAttribute("changePasswordMsg", "離上次修改密碼已超過90日");
		}else if(SystemInfo.LOGIN_MESSAGE_FIRSYLOGIN.equals(message)){
			model.addAttribute("changePasswordMsg", "第一次登入需修改密碼");
		}
		return "changPassword";
	}

	/**登入失敗
	 *
	 * @param model
	 * @param authentication
	 * @return
	 */
	
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(Model model) {
		
		Boolean showErrorMessage=Boolean.valueOf(systemInfo.getStaticParameter().get(SystemInfo.SHOW_LOGIN_ERROR).trim());
//		if("true".equals(systemInfo.getStaticParameter().get(SystemInfo.SHOW_LOGIN_ERROR).trim())){
			if(showErrorMessage){
			model.addAttribute("showMessage", true);
		}else{
			model.addAttribute("errorMsg", "登入失敗");
		}

		log.info("登入失敗");
		return "login";
	}
	
	
	/**登入後-修改密碼
	 * 
	 * @param userId 帳號
	 * @param userName 姓名
	 * @param newPassword 新密碼
	 * @return 前往登入成功
	 */
	
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	public String changePwd(Model model ,@RequestParam String userId,@RequestParam String userName,@RequestParam String newPassword) {
		
		iLoginBusiness.changePwd(userId, newPassword);
		iUserLogBusiness.updateChangePwd(userId,userName);
		User user=iLoginBusiness.findByUserId(userId);
		iLoginBusiness.loginSusses(user);
		iUserLogBusiness.LoginSuccessLog(userId, userName);
		UserProfileBean userProfileBean =systemInfo.getUserProfileBean();
		userProfileBean.setChangePwdtime(90);
		log.info("修改密碼成功");
		return "redirect:/login/loginAuth";
	}
<<<<<<< .mine
	
	
	@RequestMapping(value = "/loginDuplicate", method = RequestMethod.POST)
	public String loginDuplicate(Model model ,@RequestParam String lockOn) {
	
		boolean goLogin= Boolean.valueOf(lockOn);
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String sessionId = sessionRegistry.getAllSessions(userDetails, false).get(0).getSessionId();
		if(goLogin){
			sessionRegistry.getAllPrincipals().forEach(principals->{
				if(userDetails.getUsername().equals(((UserProfileBean)principals).getUsername())){
					List<SessionInformation> sessionList = sessionRegistry.getAllSessions(principals, false);
					sessionList.forEach(session->{
						if(!sessionId.equals(session.getSessionId())){
//							sessionRegistry.removeSessionInformation(session.getSessionId());
							session.expireNow();
						}			
					});
				}
			});
			
			int roleId = userDetails.getRoleId();
			String branchId=userDetails.getBranchId();
			int messageCount=iMessageInboxRepositoryPersist.getMessageInboxCountByReceiverRole(roleId,branchId);
			List<TodoBean> todoBeanList=iTodoBusiness.getTodoList(roleId,branchId);
			model.addAttribute("todoBeanList", todoBeanList);	
			model.addAttribute("messageCount", messageCount);
			model.addAttribute("bankName", systemInfo.getStaticParameter().get(SystemInfo.BANK_NAME));
			return "common/todo";
		}else{
			List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(userDetails, false);
			sessionInformationList.forEach(sessionInformation->{
//				sessionRegistry.removeSessionInformation(sessionId);
				sessionInformation.expireNow();
			});
			return "redirect:/login";
		}
		

	}
	
||||||| .r108
=======
	
	/**重複登入
	 * 
	 * @param model
	 * @param mandatoryLogin 是否要強登
	 * @return
	 */
	
	@RequestMapping(value = "/loginDuplicate", method = RequestMethod.POST)
	public String loginDuplicate(Model model ,@RequestParam String mandatoryLogin) {
	
		boolean goLogin= Boolean.valueOf(mandatoryLogin);
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String sessionId = sessionRegistry.getAllSessions(userDetails, false).get(0).getSessionId();
		if(goLogin){
			sessionRegistry.getAllPrincipals().forEach(principals->{
				if(userDetails.getUsername().equals(((UserProfileBean)principals).getUsername())){
					List<SessionInformation> sessionList = sessionRegistry.getAllSessions(principals, false);
					sessionList.forEach(session->{
						if(!sessionId.equals(session.getSessionId())){
//							sessionRegistry.removeSessionInformation(session.getSessionId());
							session.expireNow();
						}			
					});
				}
			});
			
			int roleId = userDetails.getRoleId();
			String branchId=userDetails.getBranchId();
			int messageCount=iMessageInboxRepositoryPersist.getMessageInboxCountByReceiverRole(roleId,branchId);
			List<TodoBean> todoBeanList=iTodoBusiness.getTodoList(roleId,branchId);
			model.addAttribute("todoBeanList", todoBeanList);	
			model.addAttribute("messageCount", messageCount);
			model.addAttribute("bankName", systemInfo.getStaticParameter().get(SystemInfo.BANK_NAME));
			return "common/todo";
		}else{
			List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(userDetails, false);
			sessionInformationList.forEach(sessionInformation->{
//				sessionRegistry.removeSessionInformation(sessionId);
				sessionInformation.expireNow();
			});
			return "redirect:/";
		}
		

	}
	
>>>>>>> .r212
}
