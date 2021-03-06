package com.uxb2b.ecp.web.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IUserBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.service.AlgorithmService;
import com.uxb2b.ecp.service.SystemInfo;


/**
 * 個人資料維護
 * @author steve
 *
 */
@Controller
@RequestMapping(value = "/personalUserSetting")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	SystemInfo systemInfo;

	@Autowired
	IUserBusiness iUserBusiness;

	@Autowired
	IUserLogBusiness iUserLogBusiness;

	@Autowired
	AlgorithmService algorithmService;
	
	
	/**
	 * 個人資料維護進入頁面
	 * 
	 * @return modifyUser 個人資料
	 */
	
	@RequestMapping
	public String main(Model model) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		
		try {
			User user = iUserBusiness.personalUserSetting(userId);
			model.addAttribute("modifyUser", user);

			return "user/personalUserSetting";
		} catch (Exception e) {
			log.error("個人資料維護進入頁面顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "個人資料維護");
			return "common/message";
		}

	}
	
	
	/**
	 * 個人資料修改
	 * 
	 * @param userName 姓名 
	 * @param newPwd 新密碼
	 * @param telNo 電話
	 * @param email 電子郵件
	 * @return
	 */
	
	@RequestMapping(value = "/personalUserSave", method = RequestMethod.POST)
	public String personalUserSave(Model model, @RequestParam String userName, @RequestParam String newPwd,
			@RequestParam String telNo, @RequestParam String email) {

		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		try {
			User modifyUser = iUserBusiness.personalUserSetting(userId);
			modifyUser.setUserName(userName);
			if (StringUtils.isNotBlank(newPwd)) {
				modifyUser.setPassword(algorithmService.toEncryptMD5(newPwd));
				modifyUser.setChangePasswordTime(new Timestamp(new Date().getTime()));
				userDetails.setChangePwdtime(90);
			}
			modifyUser.setTelNo(telNo);
			modifyUser.setEmail(email);
			String logContent = "個人資料維護(" + userId + ")[" + userName + "]修改成功";
			iUserBusiness.personalUserSave(modifyUser);
			iUserLogBusiness.personalUserSave(userId, userName, logContent);
			model.addAttribute("message", logContent);

		} catch (Exception e) {
			log.error("個人資料維護失敗 :"+e);
			e.printStackTrace();
			String logContent = "個人資料維護(" + userId + ")[" + userName + "]修改失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_PERSONIUSERSETTING, SystemInfo.USER_LOG_ACTION_MODIFY, logContent);
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
		}
		model.addAttribute("functionUrl", "personalUserSetting");
		model.addAttribute("functionMenuName", "個人資料維護");
		model.addAttribute("functionName", "個人資料維護");
		return "common/message";
	}

}
