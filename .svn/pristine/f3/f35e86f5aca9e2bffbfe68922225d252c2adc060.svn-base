package com.uxb2b.ecp.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IAwardDataVerifyBusiness;
import com.uxb2b.ecp.business.IFlowMaintainBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.exception.CertServerException;
import com.uxb2b.ecp.exception.RestfulException;
import com.uxb2b.ecp.exception.SwiftException;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.RejectReason;
import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.model.UserLog;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.FlowRepository;
import com.uxb2b.ecp.repository.RejectReasonRepository;
import com.uxb2b.ecp.repository.RoleRepository;
import com.uxb2b.ecp.service.SystemInfo;
import com.uxb2b.ecp.web.controller.query.QueryUserAccessRecordController;


/**
 * 票券批號審核
 * @author evayang
 *
 */
@Controller
@RequestMapping(value = "/awardDataVerify")
public class AwardDataVerifyController {
	
	private Logger log = LoggerFactory.getLogger(AwardDataVerifyController.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IAwardDataVerifyBusiness iAwardDataVerifyBusiness;
	
	@Autowired
	ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	@Autowired
	IFlowMaintainBusiness iFlowMaintainBusiness;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	FlowRepository flowRepository;
	
	@Autowired
	IUserLogBusiness iUserLogBusiness;
	
	@Autowired
	RejectReasonRepository rejectReasonRepository;
	
	/**
	 * 票券批號(得標確認)審核 - 待審核清冊
	 * @param pageNumber
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
    public String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			  @RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize, Model model) throws Exception {
		Page<AwardData> resultList = iAwardDataVerifyBusiness.findbyAwardDataList(pageNumber, pageSize);
		model.addAttribute("resultList", resultList);
		return "award/verifyAwardData";
	}

	
	/**
	 * 票券批號(得標確認)審核 - 明細
	 * @param quationNo
	 * @param itemNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doDetail", method = RequestMethod.POST)
    public String doDetail(String quationNo, String itemNo, Model model) {
    	try {
    		List<RejectReason> RejectReasonList=rejectReasonRepository.findByIdCodeTypeAndStatusOrderByIdCodeKeyAsc(SystemInfo.REJECT_REASON_CODE_TYPE_AWARD_DATA, SystemInfo.REJECT_REASON_STATUS_ON);
    		AwardData awardData = iAwardDataVerifyBusiness.findByQuationNoAndItemNo(quationNo, itemNo);
        	model.addAttribute("awardData", awardData);
        	model.addAttribute("rejectReasonList", RejectReasonList);
        	return "award/verifyAwardDataDetail";
    	} catch (Exception e) {
    		String errorMsg = "查詢失敗！";
			model.addAttribute("errorMsg", errorMsg);
			return "common/message";
    	}
    }
	
	/**
	 * 票券批號(得標確認)審核 - 退回
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @param rejectReason1 退回理由
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doReturn", method = RequestMethod.POST)
    public String doReturn(String quationNo, String itemNo, String rejectReason1, Model model) throws Exception {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
    	try {
    		AwardData awardData = iAwardDataVerifyBusiness.updateAwardData(quationNo, itemNo, rejectReason1);
    		iUserLogBusiness.SuccessLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_VERIFY, "票券批號(得標確認)審核退回", awardData.toString());
			model.addAttribute("functionName", "票券批號(得標確認)審核");
    		model.addAttribute("message", "報價單號碼["+quationNo+"]票券批號(得標確認)退回成功");
    	} catch (Exception e) {
    		AwardData awardData = iAwardDataVerifyBusiness.findByQuationNoAndItemNo(quationNo, itemNo);
    		log.error("票券批號(得標確認)審核退回失敗 :"+e);
			e.printStackTrace();			
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_VERIFY, "票券批號(得標確認)審核退回", awardData.toString());
			model.addAttribute("functionName", "票券批號(得標確認)審核");
    		model.addAttribute("message", "系統發生錯誤，請通知系統管理員");
    	}
    	return "common/message";
    }
	
	/**
	 * 票券批號(得標確認)審核 - 核准
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doConfirm", method = RequestMethod.POST)
    public String doConfirm(String quationNo, String itemNo, Model model) throws Exception {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent="";
		String action="票券批號(得標確認)審核";
    	try {
    		AwardData awardData = iAwardDataVerifyBusiness.updateAwardData(quationNo, itemNo);
    		iUserLogBusiness.SuccessLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_VERIFY, "票券批號(得標確認)審核核准", awardData.toString());
    		logContent = "報價單號碼["+quationNo+"]票券批號(得標確認)核准成功"; 
    	}catch (RestfulException|CertServerException e) {
			logContent="報價單號碼["+quationNo+"]票券批號(得標確認)核准成功，電文發送失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_VERIFY, "票券批號(得標確認)審核核准", logContent);
		}catch (SwiftException e) {
			logContent="報價單號碼["+quationNo+"]票券批號(得標確認)核准成功，電文回傳錯誤訊息";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_VERIFY, "票券批號(得標確認)審核核准", logContent);
		} catch (Exception e) {
			e.printStackTrace();
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_AWARDDATA_VERIFY, "票券批號(得標確認)審核核准", logContent);;
			logContent=SystemInfo.SYSTEM_ERROR;
		}
    	model.addAttribute("message", logContent);
    	model.addAttribute("functionName", action);
    	return "common/message";
    }
	
}
