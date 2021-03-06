package com.uxb2b.ecp.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IAwardDataRegisterBusiness;
import com.uxb2b.ecp.business.IFlowMaintainBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.exception.CertServerException;
import com.uxb2b.ecp.exception.RestfulException;
import com.uxb2b.ecp.exception.SwiftException;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.model.UserLog;
import com.uxb2b.ecp.repository.FlowRepository;
import com.uxb2b.ecp.repository.RoleRepository;
import com.uxb2b.ecp.service.SystemInfo;
import com.uxb2b.ecp.web.controller.query.QueryUserAccessRecordController;


/**
 * 票券批號登錄
 * @author evayang
 *
 */

@Controller
@RequestMapping(value = "/awardDataRegister")
public class AwardDataRegisterController {
	
	private Logger log = LoggerFactory.getLogger(AwardDataRegisterController.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IAwardDataRegisterBusiness iAwardDataRegisterBusiness;
	
	@Autowired
	IFlowMaintainBusiness iFlowMaintainBusiness;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	FlowRepository flowRepository;
	
	@Autowired
	IUserLogBusiness iUserLogBusiness;
	
	/**
	 * 票券批號登錄(得標確認) - 待報價清冊
	 * @param pageNumber
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
    public String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			  @RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize, Model model) throws Exception {
		Page<AwardData> resultList = iAwardDataRegisterBusiness.findbyAwardDataList(pageNumber, pageSize);
		model.addAttribute("resultList", resultList);
		return "award/queryAwardData";
	}

	
	/**
	 * 票券批號登錄(得標確認) - 明細
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doDetail", method = RequestMethod.POST)
    public String doDetail(String quationNo, String itemNo, Model model) {
    	try {
    		AwardData awardData = iAwardDataRegisterBusiness.findByQuationNoAndItemNo(quationNo, itemNo);
        	model.addAttribute("awardData", awardData);
        	return "award/awardDataDetail";
    	} catch (Exception e) {
    		String errorMsg = "查詢失敗！";
			model.addAttribute("errorMsg", errorMsg);
			return "common/message";
    	}
    }
	
	/**
	 * 票券批號登錄(得標確認) - 放棄
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doAbandon", method = RequestMethod.POST)
    public String doAbandon(String quationNo, String itemNo, Model model) throws Exception {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent="";
		String action="票券批號登錄(得標確認)";
    	try {
    		AwardData awardData = iAwardDataRegisterBusiness.updateAwardData(quationNo, itemNo);
    		iUserLogBusiness.SuccessLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-放棄", awardData.toString());
    		logContent = "報價單號碼["+quationNo+"]票券批號(得標確認)放棄成功";
		}catch (RestfulException|CertServerException e) {
			logContent="報價單號碼["+quationNo+"]放棄成功，電文發送失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-放棄", logContent);
		}catch (SwiftException e) {
			logContent="報價單號碼["+quationNo+"]票券批號(得標確認)放棄成功，電文回傳錯誤訊息";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-放棄", logContent);
		} catch (Exception e) {
			e.printStackTrace();
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-放棄", logContent);;
			logContent=SystemInfo.SYSTEM_ERROR;
		}
    	model.addAttribute("message", logContent);
    	model.addAttribute("functionName", action);
    	return "common/message";
    }
	
	/**
	 * 票券批號登錄(得標確認) - 登錄
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @param batchNo 票券批號
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doConfirm", method = RequestMethod.POST)
    public String doConfirm(String quationNo, String itemNo, String batchNo, Model model) throws Exception {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent="";
		String action="票券批號登錄(得標確認)";
    	try {
    		AwardData awardData = iAwardDataRegisterBusiness.updateAwardData(quationNo, itemNo, batchNo);
    		iUserLogBusiness.SuccessLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-已同意", awardData.toString());
    		logContent = "報價單號碼["+quationNo+"]票券批號(得標確認)登錄成功";
    	}catch (RestfulException|CertServerException e) {
			logContent="報價單號碼["+quationNo+"]核准成功，電文發送失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-已同意", logContent);
		}catch (SwiftException e) {
			logContent="報價單號碼["+quationNo+"]登錄成功，電文回傳錯誤訊息";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-已同意", logContent);
		} catch (Exception e) {
			e.printStackTrace();
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER, "票券批號登錄(得標確認)-已同意", logContent);;
			logContent=SystemInfo.SYSTEM_ERROR;
		}
    	model.addAttribute("message", logContent);
    	model.addAttribute("functionName", action);
    	return "common/message";
    }
	/**
	 * 票券批號登錄(得標確認) - 檢查票券批號是否重複
	 * @param purposeCode
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/checkAwardDataBatchNo", method = RequestMethod.POST)
	@JsonIgnore
    public @ResponseBody String checkAwardDataBatchNo(@RequestParam("batchNo") String batchNo, @RequestParam("quationNo") String quationNo, @RequestParam("itemNo") int itemNo) {
		String result;
		try{
			AwardData awardData = iAwardDataRegisterBusiness.findByBatchNo(batchNo, quationNo, itemNo);
			if(awardData != null){
				result = batchNo;
			}else{
				result = "";
			}
		}catch(Exception e){
			log.error("AJAX檢查錯誤 "+e);
			e.printStackTrace();
			result = null;
		}
		return result;
    }
	
}
