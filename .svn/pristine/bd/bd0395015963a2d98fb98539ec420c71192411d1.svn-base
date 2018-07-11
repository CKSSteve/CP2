package com.uxb2b.ecp.web.controller;


import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IRoleBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.model.RoleFunction;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.service.FunctionService;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 角色權限維護
 * @author steve
 *
 */
@Controller
@RequestMapping(value = "/roleSetting")
public class RoleController {

	private Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IRoleBusiness iRoleBusiness;
	
	@Autowired
	IUserLogBusiness IUserLogBusiness;
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	ServletContext context; 
	
	/**
	 * 角色權限維護進入頁面
	 * 
	 * @param pageNumber 第幾頁 
	 * @param pageSize 一頁顯示幾筆資料
	 * @return usersList USER清單
	 * @return rolesList 角色清單
	 */
	
	@RequestMapping
	public String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize, Model model) {
		
		try {
			Page<Role> rolesList=iRoleBusiness.getAllRole(pageNumber, pageSize);
			List<User> usersList=iRoleBusiness.getAllUser();
			model.addAttribute("usersList", usersList);
			model.addAttribute("rolesList", rolesList);

			return "role/roleAuthoritySetting";
		} catch (Exception e) {
			log.error("角色權限維護進入頁面顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "角色權限維護");
			return "common/message";
		}

	}
	
	/**
	 *刪除role
	 * 
	 * @param roleId 角色代碼
	 * @param roleName 角色名稱
	 * @return
	 */
	
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
	public String deleteRole(Model model, @RequestParam String roleId,@RequestParam String roleName) {
		
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent;
			
		try {
			
			iRoleBusiness.deleteRole(Integer.valueOf(roleId));
			logContent="角色權限維護 [" + roleId + " " + roleName + "]刪除成功";
			IUserLogBusiness.roleSetting(userId, userName, SystemInfo.USER_LOG_ACTION_DELETE, logContent);
			model.addAttribute("message", logContent);
			
		} catch (Exception e) {
			
			log.error("角色權限維護刪除失敗 :"+e);
			e.printStackTrace();
			logContent="角色權限維護[" + roleId + " " + roleName + "]刪除失敗 :" + e.getMessage();
			IUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_ROLE_SETTING, SystemInfo.USER_LOG_ACTION_DELETE, logContent);
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			
		}
		model.addAttribute("functionUrl", "roleSetting");
		model.addAttribute("functionMenuName", "角色權限維護");
		model.addAttribute("functionName", "角色權限維護");
		return "common/message";
	}	
	
	/**
	 * 角色權限修改進入頁面
	 * 
	 * @param roleId 角色代碼
	 * @param roleName 角色名稱
	 * @return functionListA 標單作業
	 * @return functionListB 本票作業
	 * @return functionListD 管理作業
	 * @return roleFunction 角色所屬功能
	 * @return roleId 角色代碼
	 * @return roleName 角色名稱
	 */
	
	@RequestMapping(value = "/settingFunction", method = RequestMethod.POST)
	public String settingFunction(Model model, @RequestParam String roleId,@RequestParam String roleName) {
		
		try {
			List<Function> functionListA=iRoleBusiness.getFunctionByFunctionId("A");
			List<Function> functionListB=iRoleBusiness.getFunctionByFunctionId("B");
//			List<Function> functionListC=iRoleBusiness.getFunctionByFunctionId("C");
			List<Function> functionListD=iRoleBusiness.getFunctionByFunctionId("D");
			List<RoleFunction> roleFunction=iRoleBusiness.findRoleFunctionByRoleId(roleId);
			model.addAttribute("functionListA", functionListA);
			model.addAttribute("functionListB", functionListB);
//			model.addAttribute("functionListC", functionListC);
			model.addAttribute("functionListD", functionListD);
			model.addAttribute("roleFunction", roleFunction);
			model.addAttribute("roleId", roleId);
			model.addAttribute("roleName", roleName);
			
			return "role/roleAuthoritySettingFunction";
		} catch (Exception e) {
			log.error("角色權限修改進入頁面-顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionUrl", "roleSetting");
			model.addAttribute("functionMenuName", "角色權限維護");
			model.addAttribute("functionName", "角色權限維護");
			return "common/message";
		}

	}
	
	/**
	 * 角色權限修改
	 * 
	 * @param roleId 角色代碼
	 * @param roleName 角色名稱
	 * @param A 標單作業
	 * @param B 本票作業
	 * @param D 管理作業
	 * @return
	 */
	@RequestMapping(value = "/updateFunction", method = RequestMethod.POST)
	public String updateFunction(Model model,@RequestParam String roleId,@RequestParam String roleName,@RequestParam(value="A", required = false) String A,@RequestParam(value="B", required = false) String B,@RequestParam(value="C", required = false) String C,@RequestParam(value="D", required = false) String D) {
		
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent;

		try {
			iRoleBusiness.deleteRoleFunctionByRoleId(Integer.valueOf(roleId));
			iRoleBusiness.settingRoleFunction(Integer.valueOf(roleId),A, B, C, D);
			logContent="角色權限維護 [" + roleId + " " + roleName + "]修改成功";
			IUserLogBusiness.roleSetting(userId, userName, SystemInfo.USER_LOG_ACTION_MODIFY, logContent);
			functionService.putAllFunctionToContext(context);
			model.addAttribute("message", logContent);

		} catch (Exception e) {
			log.error("角色權限維護修改失敗");
			e.printStackTrace();
			logContent="角色權限維護 [" + roleId + " " + roleName + "]修改失敗";
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
		}
		model.addAttribute("functionUrl", "roleSetting");
		model.addAttribute("functionMenuName", "角色權限維護");
		model.addAttribute("functionName", "角色權限維護");
		return "common/message";
	}
	
	/**
	 * 角色權限新增進入頁面	
	 * 
	 * @param model
	 * @return
	 */
	
	
	@RequestMapping(value = "/newRole")
	public String newBranch(Model model) {	
		return "role/addRoleAuthorityFunction";
	}
	
	/**
	 * 角色權限新增
	 * 
	 * @param roleName 角色名稱
	 * @param memo 角色說明
	 * @return
	 */
	
	@RequestMapping(value = "/addNewRole")
	public String addNewRole(Model model,@RequestParam String roleName,@RequestParam String memo) {	
		
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent;
		
		try {
			Role roleModel=new Role(roleName, memo);
			iRoleBusiness.saveRole(roleModel);
			Role role=iRoleBusiness.getNewRole().get(0);
			logContent="角色權限維護 [" + role.getRoleId() + " " + role.getRoleName() + "]新增成功";
			IUserLogBusiness.roleSetting(userId, userName, SystemInfo.USER_LOG_ACTION_ADD, logContent);
			model.addAttribute("role", role);
			return "role/roleAuthoritySettingFunctionPreview";
		} catch (Exception e) {
			log.error("角色權限新增失敗"+e);
			e.printStackTrace();
			logContent="角色權限維護 [" + roleName + "]新增失敗";
			model.addAttribute("functionUrl", "roleSetting");
			model.addAttribute("functionMenuName", "角色權限維護");
			model.addAttribute("functionName", "角色權限維護");
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			return "common/message";
		}		
		
	}
	
}
