package com.uxb2b.ecp.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uxb2b.ecp.bean.TodoBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.ITodoBusiness;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 待辦事項
 * @author steve
 *
 */

@Controller
@RequestMapping(value = "/todoMain")
public class TodoController {
	
	private Logger log = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	ITodoBusiness iTodoBusiness;
	
	
	/**
	 * 待辦事項清單 
	 * @return todoBeanList 清單資料 
	 * @return bankName 分行中文名稱
	 */
	
	@RequestMapping
	public String main(Model model) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		int roleId = userDetails.getRoleId();
		String branchId = userDetails.getBranchId();
		
		try {
			List<TodoBean> todoBeanList=iTodoBusiness.getTodoList(roleId,branchId);
			
			model.addAttribute("todoBeanList", todoBeanList);
			model.addAttribute("bankName", systemInfo.getStaticParameter().get(SystemInfo.BANK_NAME));
			return "common/todo";
		} catch (Exception e) {
			log.error("待辦事項清單 顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("functionName", "待辦事項");
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			return "common/message";
		}

	}
}
