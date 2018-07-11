package com.uxb2b.ecp.web.controller.query;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uxb2b.ecp.bean.QueryUserAccessRecordFormBean;
import com.uxb2b.ecp.business.IQueryUserAccessRecordBusiness;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.UserLog;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 使用者存取紀錄
 * @author evayang
 *
 */

@Controller
@RequestMapping(value = "/queryUserAccessRecord")
public class QueryUserAccessRecordController {
	
	private Logger log = LoggerFactory.getLogger(QueryUserAccessRecordController.class);
	
	@Autowired
	IQueryUserAccessRecordBusiness iQueryUserAccessRecordBusiness;
	
	/**
	 * 使用者存取紀錄
	 * @param searchBean
	 * @param model
	 * @return
	 */
	@RequestMapping
    public String main(QueryUserAccessRecordFormBean searchBean, Model model) {
		List<Function>  functionList = iQueryUserAccessRecordBusiness.findFunctionList();
		model.addAttribute("functionList", functionList);
		model.addAttribute("searchBean", searchBean);
        return "query/queryUserAccessRecord";
    } 
	
	/**
	 * 使用者存取紀錄 - 查詢列表
	 * @param searchBean
	 * @param pageNumber
	 * @param pageSize
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/doQuery", method = {RequestMethod.GET, RequestMethod.POST})
    public String doQuery(QueryUserAccessRecordFormBean searchBean,
    					  @RequestParam(value = "page", defaultValue = "1") int pageNumber,
    					  @RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize,
    					  Model model) {
		try {
			List<Function>  functionList = iQueryUserAccessRecordBusiness.findFunctionList();
			Page<UserLog> resultList= iQueryUserAccessRecordBusiness.findUserLogByCondition(searchBean, pageNumber, pageSize);
			model.addAttribute("resultList", resultList);
			model.addAttribute("functionList", functionList);
			model.addAttribute("searchBean", searchBean);
			model.addAttribute("showForm", true);
			return "query/queryUserAccessRecord";
		} catch (Exception e) {
			String errorMsg = String.format("使用者存取記錄作業失敗，請重新操作");
			log.error(errorMsg, e);
			model.addAttribute("errorMsg", errorMsg);
			return "common/message";
		}
    }
	
	/**
	 * 使用者存取紀錄 - 明細列表
	 * @param searchBean
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/showDetail", method = {RequestMethod.GET, RequestMethod.POST})
    public String showDetail(QueryUserAccessRecordFormBean searchBean,
    					  	 Model model) {
		try {
			List<UserLog> result = iQueryUserAccessRecordBusiness.findUserLogDetail(searchBean);
			model.addAttribute("result", result);
			model.addAttribute("searchBean", searchBean);
			return "query/queryUserAccessRecordDetail";			
		} catch (Exception e) {
			String errorMsg = String.format("使用者存取記錄明細作業失敗，請重新操作");
			log.error(errorMsg, e);
			model.addAttribute("errorMsg", errorMsg);
			return "common/message";
		}
    }

}
