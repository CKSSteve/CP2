package com.uxb2b.ecp.web.controller;

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

import com.uxb2b.ecp.bean.QueryQuotesBean;
import com.uxb2b.ecp.business.IQuotionBusiness;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.service.SystemInfo;


/**
 * 報價單查詢
 * @author steve
 *
 */

@Controller
@RequestMapping(value = "/quotionQuery")
public class QuotionQueryController {

	private Logger log = LoggerFactory.getLogger(QuotionQueryController.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IQuotionBusiness iQuotionBusiness;
	
	/**
	 *查詢頁導入 
	 * 
	 * @param searchBean 查詢條件(空的、避免JSP的SpringTag出錯)
	 * @return issuerIdList 公司清單
	 * @return currencyList 幣別清單
	 * @return searchBean 查詢條件
	 */
	
	@RequestMapping
	public String main(Model model,QueryQuotesBean searchBean) {
		
		try {
			List<Enterprise> enterpriseList=iQuotionBusiness.findAllEnterprise();
			List<SysCode> sysCodeList=iQuotionBusiness.findSysCodeByCodeType(SystemInfo.SYSCODE_CODETYPE_CURRENCY);
			
			model.addAttribute("issuerIdList", enterpriseList);
			model.addAttribute("currencyList", sysCodeList);
			model.addAttribute("searchBean", searchBean) ;
			
			
			return "quotion/quotesQuery";
		} catch (Exception e) {
			log.error("報價單查詢-查詢頁導入顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "報價單查詢");
			return "common/message";
		}

	}
	
	/**
	 * 查詢結果
	 * 
	 * @param searchBean 查詢條件
	 * @param pageNumber 第幾頁
	 * @param pageSize 一頁顯示幾筆資料
	 * @return issuerIdList 公司清單
	 * @return currencyList 幣別清單
	 * @return queryQuotesList 查詢結果
	 * @return searchBean 查詢條件
	 * @return showForm 顯示查詢結果
	 */
	
	@RequestMapping(value = "/doQuery", method = RequestMethod.POST)
    public String doQuery(QueryQuotesBean searchBean,
    					  @RequestParam(value = "page", defaultValue = "1") int pageNumber,
    					  @RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize,
    					  Model model) {
		
		try {
			
			List<Enterprise> enterpriseList=iQuotionBusiness.findAllEnterprise();
			List<SysCode> sysCodeList=iQuotionBusiness.findSysCodeByCodeType(SystemInfo.SYSCODE_CODETYPE_CURRENCY);
			Page<Ctbflbq1> queryQuotesList = iQuotionBusiness.findQuotes(searchBean, pageNumber, pageSize);
			model.addAttribute("issuerIdList", enterpriseList);
			model.addAttribute("currencyList", sysCodeList);
			model.addAttribute("queryQuotesList", queryQuotesList);
			model.addAttribute("searchBean", searchBean);
			model.addAttribute("showForm", true);
			return "quotion/quotesQuery";
		} catch (Exception e) {
			
			log.error("報價單查詢失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "報價單查詢");
			return "common/message";
		}
		

	}
	
	/**
	 * 顯示明細
	 * 
	 * @param searchBean 查詢條件
	 * @param pageNumber 第幾頁
	 * @param pageSize 一頁顯示幾筆資料
	 * @param method 上一頁路徑
	 * @return ctbflbq2List 明細資料
	 * @return searchBean 查詢條件
	 * @return method 上一頁路徑
	 */
	
	@RequestMapping(value = "/showDetail", method = RequestMethod.POST)
    public String showDetail(QueryQuotesBean searchBean,
			  				 @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			  				 @RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize,
			  				 Model model,@RequestParam String method,@RequestParam String detailQuationNo){
    	
		try {
			Page<Ctbflbq2> ctbflbq2List=iQuotionBusiness.findQuotesDetails(pageNumber, pageSize,detailQuationNo);
			model.addAttribute("ctbflbq2List", ctbflbq2List);
			model.addAttribute("searchBean", searchBean);
			model.addAttribute("method", method);
			return "quotion/quotesDetail";
		} catch (Exception e) {
			log.error("報價單查詢-明細顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "報價單查詢");
			return "common/message";
		}
		
    }

	/**
	 * 競價結果
	 * 
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @param status 報價單狀態
	 * @return ctbflbq1 報價資料
	 * @return ctbflbq2 報價明細資料
	 * @return winningTheMark 是否得標
	 * @return branchName 分行中文名稱
	 */
	
	
	@RequestMapping(value = "/showBidResults")
	public String showBidResults(QueryQuotesBean searchBean,Model model,@RequestParam String quationNo,@RequestParam String itemNo,@RequestParam String status) {
		
		try {
			Ctbflbq1 ctbflbq1=iQuotionBusiness.findCtbflbq1ByQuationNo(quationNo);
			Ctbflbq2 ctbflbq2=iQuotionBusiness.findCtbflbq2ByQuationNoAndItemNo(quationNo, Integer.valueOf(itemNo));
			model.addAttribute("ctbflbq1", ctbflbq1);
			model.addAttribute("ctbflbq2", ctbflbq2);
			if(SystemInfo.NOT_MARK_STATUS.equals(status)){
				model.addAttribute("winningTheMark", false);
			}else{
				Branch branch=iQuotionBusiness.findBranchByBranchId(ctbflbq1.getBranchId());
				model.addAttribute("branchName", branch.getChineseName());
				model.addAttribute("winningTheMark", true);
			}
			
			model.addAttribute("searchBean", searchBean);
			return "quotion/bidResultsPreview";
		} catch (Exception e) {
			log.error("報價單查詢-競價結果顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "報價單查詢");
			return "common/message";
		}

	}
	
}
