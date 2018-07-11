package com.uxb2b.ecp.web.controller;




import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uxb2b.ecp.bean.UserProfileBean;

import com.uxb2b.ecp.business.IRateDetailsBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.exception.CertServerException;
import com.uxb2b.ecp.exception.RestfulException;
import com.uxb2b.ecp.exception.SwiftException;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.RejectReason;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.service.SystemInfo;


/**
 * 本票帳務明細作業
 * @author steve
 *
 */

@Controller
@RequestMapping(value = "/rateDetails")
public class RateDetailsController {

	private Logger log = LoggerFactory.getLogger(RateDetailsController.class);

	@Autowired
	SystemInfo systemInfo;

	@Autowired
	IRateDetailsBusiness iRateDetailsBusiness;
	
	@Autowired
	IUserLogBusiness iUserLogBusiness;
	
	/**
	 * 本票帳務明細登錄清冊
	 * 
	 * @param pageNumber 第幾頁
	 * @param pageSize 一頁顯示幾筆資料
	 * @return awardDataList 待本票帳務明細登錄清單
	 */

	@RequestMapping(value = "/rateDetailsRegisterInventory")
	public String quotionEntryInventory(Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String branchId = userDetails.getBranchId();
		int roleId = userDetails.getRoleId();
		try {
			
			Page<AwardData> awardDataList =iRateDetailsBusiness.getRateDetailsRegisterInventory(branchId, roleId, pageNumber, pageSize);
			model.addAttribute("awardDataList", awardDataList);
			return "rateDetails/rateDetailsRegisterInventory";
		} catch (Exception e) {

			log.error("本票帳務明細登錄清冊導入失敗:" + e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "本票帳務明細登錄");
			return "common/message";
		}


	}


	/**
	 * 本票帳務明細待登錄
	 * 
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @return awardData 本票帳務明細待登錄資料
	 */ 

	@RequestMapping(value = "/rateDetailsRegister")
	public String rateDetailsRegister(Model model, @RequestParam String quationNo, @RequestParam String itemNo) {
		try {
			
			AwardData awardData = iRateDetailsBusiness.getRateDetails(quationNo, Integer.valueOf(itemNo));
			model.addAttribute("awardData", awardData);
			return "rateDetails/rateDetailsRegister";
		} catch (Exception e) {
			log.error("本票帳務明細登錄導入失敗:" + e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "本票帳務明細登錄");
			return "common/message";
		}


	}
	
	/**
	 * 本票帳務明細登錄
	 * 
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @param deliveryRate 交割服務費率
	 * @param underwritingRate 承銷手續費率
	 * @param visaRate 簽證費率 
	 * @param guaranteeRate 保證費率
	 * @return
	 */
	
	@RequestMapping(value = "/doRegister")
	public String doRegister(Model model, @RequestParam String quationNo, @RequestParam String itemNo, @RequestParam String deliveryRate, @RequestParam String underwritingRate,@RequestParam String visaRate,@RequestParam String guaranteeRate, @RequestParam String batchNo) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		StringBuilder logContent= new StringBuilder();
		String action="本票帳務明細登錄";
		String message;
		try {
			
			iRateDetailsBusiness.doRegister(quationNo, itemNo, deliveryRate, underwritingRate, visaRate, guaranteeRate, userDetails);
			logContent.append("{QUATION_NO : "+quationNo);
			logContent.append(",ITEM_NO : "+itemNo);
			logContent.append(",DELIVERY_RATE : "+deliveryRate);
			logContent.append(",UNDERWRITING_RATE : "+underwritingRate);
			logContent.append(",VISA_RATE : "+visaRate);
			logContent.append(",GUARANTEE_RATE : "+guaranteeRate+"}");
			iUserLogBusiness.SuccessLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_REGISTER, action, logContent.toString());
			message="票券批號["+batchNo+"]本票帳務明細登錄成功";
		}catch (RestfulException|CertServerException e) {
			log.error("票券批號["+batchNo+"]本票帳務明細登錄發送電文失敗");
			message="票券批號["+batchNo+"]本票帳務明細登錄成功，電文發送失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_REGISTER, action, message);
		}catch (SwiftException e) {
			log.error("票券批號["+batchNo+"]本票帳務明細登錄下行電文回傳錯誤");
			message="票券批號["+batchNo+"]本票帳務明細登錄成功，電文回傳錯誤訊息";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_REGISTER, action, message);
		}catch (Exception e) {
			log.error("本票帳務明細登錄失敗 :"+e);
			e.printStackTrace();
			message="票券批號["+batchNo+"]本票帳務明細登錄失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_REGISTER, action, message);
			message=SystemInfo.SYSTEM_ERROR;
			model.addAttribute("errorMessage", e.getMessage());
		}
		model.addAttribute("functionName", "本票帳務明細登錄");
		model.addAttribute("message", message);
		return "common/message";
	}
	
	
	/**
	 *待審核清冊 
	 * 
	 * @param pageNumber 第幾頁
	 * @param pageSize 一頁顯示幾筆資料
	 * @return awardDataList 待審核清單
	 */ 
	
	@RequestMapping(value = "/rateDetailsReviewInventory")
	public String rateDetailsReviewInventory(Model model,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String branchId = userDetails.getBranchId();
		int roleId = userDetails.getRoleId();
		try {
			
			Page<AwardData> awardDataList =iRateDetailsBusiness.getRateDetailsReviewInventory(branchId, roleId, pageNumber, pageSize);	
			model.addAttribute("awardDataList",awardDataList);
			return "rateDetails/rateDetailsReviewInventory";
		} catch (Exception e) {

			log.error("本票帳務明細審核清冊導入失敗:" + e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "本票帳務明細審核");
			return "common/message";
		}


	}
	
	
	/**
	 * 本票帳務明細審核
	 * 
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @return awardData 審核資料
	 * @return reasonForReturnList 退回原因清單
	 */
	
	@RequestMapping(value = "/rateDetailsReview")
	public String quotionReview(Model model,@RequestParam String quationNo, @RequestParam String itemNo) {
	
			try {		
				AwardData awardData = iRateDetailsBusiness.getRateDetails(quationNo, Integer.valueOf(itemNo));
				List<RejectReason> reasonForReturnList= iRateDetailsBusiness.findRateDetailsRejectReason();
				model.addAttribute("awardData", awardData);
				model.addAttribute("reasonForReturnList", reasonForReturnList);
				return "rateDetails/rateDetailsReview";
			} catch (Exception e) {
				log.error("本票帳務明細登錄導入失敗:" + e);
				e.printStackTrace();
				model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
				model.addAttribute("functionName", "本票帳務明細審核");
				return "common/message";
			}

	}
	
	
	/**
	 * 審核退回
	 * 
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @param reasonForReturnString 退回原因
	 * @return
	 */
		
	@RequestMapping(value = "/doReturn")
	public String doReturn(Model model, @RequestParam String quationNo, @RequestParam String itemNo,@RequestParam String reasonForReturnString, @RequestParam String batchNo) {
		
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent;
		String action="本票帳務明細退回";
		String message;
		try {
			iRateDetailsBusiness.doReturn(quationNo,itemNo,reasonForReturnString, userDetails);
			logContent="票券批號["+batchNo+"]本票帳務明細退回成功";
			iUserLogBusiness.SuccessLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_VERIFY, action, logContent.toString());
			message="票券批號["+batchNo+"]本票帳務明細退回成功";
		} catch (Exception e) {
			log.error("本票帳務明細退回失敗"+e);
			e.printStackTrace();
			logContent="票券批號["+batchNo+"]本票帳務明細退回失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_VERIFY, action, logContent);
			message=SystemInfo.SYSTEM_ERROR;
		}
		model.addAttribute("functionName", "本票帳務明細審核");
		model.addAttribute("message", message);
		return "common/message";
	}
	
	/**
	 * 審核通過 
	 * 
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @return
	 */
	
	@RequestMapping(value = "/doReview")
	public String doReview(Model model, @RequestParam String quationNo, @RequestParam String itemNo, @RequestParam String batchNo) {
		
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String logContent;
		String action="本票帳務明細核准";
		String message;
		try {
			iRateDetailsBusiness.doReview(quationNo,itemNo, userDetails);
			logContent="票券批號["+batchNo+"]本票帳務明細核准成功";
			iUserLogBusiness.SuccessLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_VERIFY, action, logContent);
			message="票券批號["+batchNo+"]本票帳務明細核准成功";
		}catch (RestfulException|CertServerException e) {
			log.error("票券批號["+batchNo+"]本票帳務明細審核發送電文失敗");
			message="票券批號["+batchNo+"]本票帳務明細核准成功，電文發送失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_VERIFY, action, message);
		}catch (SwiftException e) {
			log.error("票券批號["+batchNo+"]本票帳務明細審核下行電文回傳錯誤");
			message="票券批號["+batchNo+"]本票帳務明細核准成功，電文回傳錯誤訊息";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_VERIFY, action, message);
		}catch (Exception e) {
			log.error("本票帳務明細核准失敗"+e);
			e.printStackTrace();
			logContent="票券批號["+batchNo+"]本票帳務明細核准失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_RATEDETAILS_VERIFY, action, logContent);
			message=SystemInfo.SYSTEM_ERROR;
			model.addAttribute("errorMessage", e.getMessage());
		}
		model.addAttribute("functionName", "本票帳務明細審核");
		model.addAttribute("message", message);
		return "common/message";
	}
	
}
