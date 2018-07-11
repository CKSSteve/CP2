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

import com.uxb2b.ecp.business.IResendSwiftBusiness;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.service.SystemInfo;


/**
 * 
 * @author steve
 *
 */
@Controller
@RequestMapping(value = "/rendSendSwift")
public class ResendSwiftController {

	private Logger log = LoggerFactory.getLogger(ResendSwiftController.class);
	
	@Autowired
	IResendSwiftBusiness iResendSwiftBusiness;
	
	
	/**重送電文清冊
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize, Model model) {
		
		try {
			Page<PassRecord> passRecordPage = iResendSwiftBusiness.getNeedResendSwift(pageNumber, pageSize);
			model.addAttribute("passRecordList", passRecordPage);
			return "resendSwift/resendSwift";
		} catch (Exception e) {
			log.error("重送電文清冊導入失敗:" + e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "資料重新傳送");
			return "common/message";
		}

	}
	
	@RequestMapping(value = "/reSendThread", method = RequestMethod.POST)
	public String reSendThread(Model model, @RequestParam List<Long> passRecordIdSelect){
		
		iResendSwiftBusiness.resendSwiftThread(passRecordIdSelect);
		
		return "redirect:/rendSendSwift";
		
		
	}
	
}
