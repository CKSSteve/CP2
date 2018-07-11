package com.uxb2b.ecp.web.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IBranchBusiness;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.BranchType;
import com.uxb2b.ecp.repository.BranchTypeRepository;
import com.uxb2b.ecp.repository.ServiceCenterMappingRepository;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 分行資料維護
 * @author steve
 *
 */

@Controller
@RequestMapping(value = "/branchMaintain")
public class BranchMaintainController {

	@Autowired
	SystemInfo systemInfo;

	@Autowired
	IBranchBusiness iBranchBusiness;

	@Autowired
	IUserLogBusiness iUserLogBusiness;

	@Autowired
	BranchTypeRepository branchTypeRepository;
	
	@Autowired
	ServiceCenterMappingRepository serviceCenterMappingRepository;

	private Logger log = LoggerFactory.getLogger(BranchMaintainController.class);

	/**
	 * 分行資料維護進入頁面
	 *
	 * @param pageNumber 第幾頁
	 * @param pageSize 一頁顯示幾筆資料
	 * @return branchMaintainList 分行列表
	 */
	@RequestMapping
	public String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize, Model model) {
		
		try {
			Page<Branch> branchMaintainList = iBranchBusiness.getAllBranch(pageNumber, pageSize);
			model.addAttribute("branchMaintainList", branchMaintainList);
			return "branch/branchMaintain";
		} catch (Exception e) {
			log.error("分行資料維護顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "分行資料維護");
			return "common/message";
		}

	}

	/**
	 * 修改進入頁面
	 * 
	 * @param branchId 欲修改的分行的BRANCHID
	 * @return branch 分行資料
	 * @return branchTypeList 分行類型清單
	 * @return noCenterbranch 非區域中心的分行清單
	 */
	
	@RequestMapping(value = "/modifyBranch", method = RequestMethod.POST)
	public String modifyBranch(Model model, @RequestParam String branchId) {

		try {
			Branch branch = iBranchBusiness.findBranchByBranchId(branchId);
			List<BranchType> branchTypeList = branchTypeRepository.findAll();
			List<Branch> noCenterbranch = iBranchBusiness.findBranchNotInServiceCenter(SystemInfo.BRANCHTYPEID);
			model.addAttribute("branch", branch);
			model.addAttribute("branchTypeList", branchTypeList);
			model.addAttribute("noCenterbranch", noCenterbranch);

			return "branch/modifyBranch";
		} catch (Exception e) {
			log.error("分行資料維護-修改進入頁面顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("functionName", "分行資料維護");
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			return "common/message";
		}
		

	}

	/**
	 * 修改
	 * 
	 * @param branchId 被修改的分行的BRANCHID
	 * @param boss 分行負責人
	 * @param chineseName 分行中文名稱
	 * @param englishName 分行英文名稱
	 * @param chineseAddr 分行中文地址
	 * @param englishAddr 分行英文地址
	 * @param tel 分行電話
	 * @param fax 分行傳真 
	 * @param email 分行人員電子郵件
	 * @param typeId 分行類型
	 * @param branchs 區域中心轄下的分行
	 * @return
	 */
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
	public String updateBranch(Model model, @RequestParam String branchId, @RequestParam String boss,
			@RequestParam String chineseName, @RequestParam String englishName, @RequestParam String chineseAddr,
			@RequestParam String englishAddr, @RequestParam String tel, @RequestParam String fax,
			@RequestParam String email, @RequestParam String typeId, @RequestParam String branchs) {
		
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		
		try {
			Branch branch = new Branch(branchId, boss, chineseName, englishName, chineseAddr, englishAddr, tel, fax,
					email, typeId);
			iBranchBusiness.saveBranch(branch);
			if (SystemInfo.SERVICECENTERTYPEID.equals(typeId)) {
				if(StringUtils.isNotBlank(branchs)){
					iBranchBusiness.setServiceCenterMapping(branchId,branchs);
				}else{
					iBranchBusiness.removeByCenterId(branchId);
				}
			}
			String logContent = "分行資料維護[" + branchId + "]更新成功";
			iUserLogBusiness.maintainBranch(userId, userName,SystemInfo.USER_LOG_ACTION_MODIFY, logContent);
			model.addAttribute("functionMenuName", "分行資料維護");
			model.addAttribute("functionUrl", "branchMaintain");
			model.addAttribute("functionName", "分行資料維護");
			model.addAttribute("message", logContent);
			return "common/message";

		} catch (Exception e) {
			log.error("分行資料維護失敗 :"+e);
			e.printStackTrace();
			String logContent="分行資料維護[" + branchId + "]更新失敗";
			iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_BRANCH_SETTING, SystemInfo.USER_LOG_ACTION_MODIFY, logContent);
			model.addAttribute("functionUrl", "branchMaintain");
			model.addAttribute("functionMenuName", "分行資料維護");
			model.addAttribute("functionName", "分行資料維護");
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			return "common/message";
		}
	}

	/**
	 * 新增進入頁面
	 * 
	 * @return 分行類型清單
	 * @return 非區域中心的分行清單
	 */
	

	@RequestMapping(value = "/newBranch")
	public String newBranch(Model model) {

		try {
			List<BranchType> branchTypeList = branchTypeRepository.findAll();
			List<Branch> noCenterbranch = iBranchBusiness.findBranchNotInServiceCenter(SystemInfo.BRANCHTYPEID);
			model.addAttribute("branchTypeList", branchTypeList);
			model.addAttribute("noCenterbranch", noCenterbranch);
			
			return "branch/addNewBranch";
		} catch (Exception e) {
			log.error("分行資料維護-新增進入頁面顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "分行資料維護");
			return "common/message";
		}


	}

	/**
	 * 新增
	 * 
	 * @param branchId 新增分行的BRANCHID
	 * @param boss 分行負責人
	 * @param chineseName 分行中文名稱
	 * @param englishName 分行英文名稱
	 * @param chineseAddr 分行中文地址
	 * @param englishAddr 分行英文地址
	 * @param tel 分行電話
	 * @param fax 分行傳真
	 * @param email 分行人員電子郵件
	 * @param typeId 分行類型
	 * @param branchs 區域中心轄下的分行
	 * @return
	 */
	
	
	@RequestMapping(value = "/addNewBranch", method = RequestMethod.POST)
	public String addNewBranch(Model model, @RequestParam String branchId, @RequestParam String boss,
			@RequestParam String chineseName, @RequestParam String englishName, @RequestParam String chineseAddr,
			@RequestParam String englishAddr, @RequestParam String tel, @RequestParam String fax,
			@RequestParam String email, @RequestParam String typeId, @RequestParam String branchs) {
		
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		String bankCode = systemInfo.getStaticParameter().get(SystemInfo.BANK_CODE);
			try {
				Branch branch = new Branch(bankCode+branchId, boss, chineseName, englishName, chineseAddr, englishAddr, tel, fax,
						email, typeId);
				iBranchBusiness.saveBranch(branch);
				if (SystemInfo.SERVICECENTERTYPEID.equals(typeId)) {
					if(StringUtils.isNotBlank(branchs)){
						String branchsArray[] = branchs.split(",");
						IntStream.range(0, branchsArray.length)
								.forEach(i -> iBranchBusiness.saveServiceCenterMapping(bankCode+branchId,bankCode+branchsArray[i].substring(0, branchsArray[i].indexOf(" "))));
					}
				}
				String logContent = "分行資料維護[" + bankCode+branchId + "]新增成功";
				iUserLogBusiness.maintainBranch(userId, userName,SystemInfo.USER_LOG_ACTION_ADD, logContent);
				model.addAttribute("functionUrl", "branchMaintain");
				model.addAttribute("functionMenuName", "分行資料維護");
				model.addAttribute("functionName", "分行資料維護");
				model.addAttribute("message", logContent);
				
				return "common/message";
			} catch (Exception e) {
				
				log.error("分行資料新增失敗 :"+e);
				e.printStackTrace();
				String logContent="分行資料維護[" + bankCode+branchId + "]新增失敗";
				iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_BRANCH_SETTING, SystemInfo.USER_LOG_ACTION_ADD, logContent);
				logContent=SystemInfo.SYSTEM_ERROR;
				model.addAttribute("functionUrl", "branchMaintain");
				model.addAttribute("functionMenuName", "分行資料維護");
				model.addAttribute("functionName", "分行資料維護");
				model.addAttribute("message", logContent);
				return "common/message";
			}	
	}
	
	@RequestMapping(value = "/branchIdCheck", method = RequestMethod.POST)
	@JsonIgnore
    public @ResponseBody String branchIdCheck(@RequestParam("branchId") String branchId) {

		String errorMsg;
		try {
			Branch branch=iBranchBusiness.findBranchByBranchId(systemInfo.getStaticParameter().get(SystemInfo.BANK_CODE)+branchId);
			if(branch==null){
				errorMsg="";
			}else{
				errorMsg=branchId;
			}
			
		} catch (Exception e) {
			log.error("AJAX檢查錯誤 "+e);
			e.printStackTrace();
			errorMsg=null;
		}
		return errorMsg;
	}
	

}
