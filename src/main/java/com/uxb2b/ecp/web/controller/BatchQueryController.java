package com.uxb2b.ecp.web.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uxb2b.ecp.bean.QueryBatchBean;
import com.uxb2b.ecp.business.IQueryBatchBusiness;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.SysCode;

import com.uxb2b.ecp.service.SystemInfo;


/**
 * 本票查詢
 * @author steve
 *
 */
@Controller
@RequestMapping(value = "/batchQuery")
public class BatchQueryController {

	private Logger log = LoggerFactory.getLogger(BatchQueryController.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IQueryBatchBusiness iQueryBatchBusiness;
	
	
	/**
	 * 本票查詢條件頁
	 * 
	 * @return issuerIdList 公司清單
	 * @return currencyList 幣別清單
	 */
	
	@RequestMapping
	public String main(Model model) {
		try {
			List<Enterprise> enterpriseList=iQueryBatchBusiness.findAllEnterprise();
			List<SysCode> sysCodeList=iQueryBatchBusiness.findSysCodeByCodeType(SystemInfo.SYSCODE_CODETYPE_CURRENCY);		
			model.addAttribute("issuerIdList", enterpriseList);
			model.addAttribute("currencyList", sysCodeList);
			return "batch/batchQuery";
		} catch (Exception e) {
			log.error("本票查詢條件頁顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("functionName", "本票查詢");
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
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
	 * @return queryBatchList 查詢結果
	 * @return searchBean 查詢條件
	 * @return showForm 顯示查詢結果
	 */
	
	
	@RequestMapping(value = "/doQuery", method = RequestMethod.POST)
    public String doQuery(QueryBatchBean searchBean,
    					  @RequestParam(value = "page", defaultValue = "1") int pageNumber,
    					  @RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize,
    					  Model model) {
		
		try {
		
			List<Enterprise> enterpriseList=iQueryBatchBusiness.findAllEnterprise();
			List<SysCode> sysCodeList=iQueryBatchBusiness.findSysCodeByCodeType(SystemInfo.SYSCODE_CODETYPE_CURRENCY);
			Page<CpData> queryBatchList = iQueryBatchBusiness.findBatch(searchBean, pageNumber, pageSize);
			model.addAttribute("issuerIdList", enterpriseList);
			model.addAttribute("currencyList", sysCodeList);
			model.addAttribute("queryBatchList", queryBatchList);
			model.addAttribute("searchBean", searchBean);
			model.addAttribute("showForm", true);
			return "batch/batchQuery";
		} catch (Exception e) {
			
			log.error("本票查詢失敗:"+e);
			e.printStackTrace();
			model.addAttribute("functionName", "本票查詢");
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			return "common/message";
		}
		
		
	}
	
	
	/**
	 * 顯示明細
	 * 
	 * @param searchBean 查詢條件
	 * @param detailBatchNo 票券批號
	 * @param method 上一頁的路徑
	 * @return batchDetail 明細資料
	 * @return searchBean 查詢條件
	 * @return method 上一頁的路徑
	 */
	
	
	@RequestMapping(value = "/showDetail", method = RequestMethod.POST)
    public String showDetail(Model model,QueryBatchBean searchBean,@RequestParam String detailBatchNo,@RequestParam String method){
    	try {
    		CpData cpData=iQueryBatchBusiness.findByBatchNo(detailBatchNo);	
    		model.addAttribute("batchDetail", cpData);
    		model.addAttribute("searchBean", searchBean);
    		model.addAttribute("method", method);
    		
    		return "batch/batchQueryShowDetail";
		} catch (Exception e) {
			log.error("本票明細顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("functionName", "本票查詢");
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			return "common/message";
		}

    }
	
	/**
	 * 書件預覽
	 * 
	 * @param batchNo 票券批號
	 * @param docType 書件種類 entrustDoc:委請書、statementDoc:聲明書
	 * @param response IO輸出PDF
	 */
	
	@RequestMapping(value = "/showPdfDoc", method = {RequestMethod.GET, RequestMethod.POST})
    public void showPdfDoc(@RequestParam String detailBatchNo,@RequestParam String docType ,HttpServletResponse response){
    	
		ServletOutputStream  outStream = null ;
        try {
        	CpData cpData=iQueryBatchBusiness.findByBatchNo(detailBatchNo);
        	byte[] bytes;
        	if("entrustDoc".equals(docType)){			
        		bytes=cpData.getEntrustDoc(); 
        	}else{
        		bytes=cpData.getStatementDoc(); 		
        	}
        response.setContentType("application/pdf");  
        outStream = response.getOutputStream();
        response.setContentLength(bytes.length);  
		outStream.write(bytes,0,bytes.length);
	    outStream.flush();  
	
        } catch (Exception e) {
        	log.error("PDF顯示失敗"+e);
        	e.printStackTrace();
        }  finally {
        	try {
        		outStream.close();
        	} catch (IOException e) {
        		log.error("outStream關閉失敗"+e);
        		e.printStackTrace();
        	}  
        }
		
    }
	
}
