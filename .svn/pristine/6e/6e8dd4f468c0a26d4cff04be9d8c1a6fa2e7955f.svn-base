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

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IUserLogBusiness;
import com.uxb2b.ecp.model.MessageInbox;
import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.service.SystemInfo;

/**
 * 訊息匣
 * @author steve
 *
 */
@Controller
@RequestMapping(value = "/MessageInbox")
public class MessageController {

	private Logger log = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IMessageInboxRepositoryPersist iMessageInboxRepositoryPersist;
	
	@Autowired
	IUserLogBusiness iUserLogBusiness;
	
	/**
	 * 訊息匣清單
	 * 
	 * @param pageNumber 第幾頁
	 * @param pageSize 一頁顯示幾筆資料
	 * @return messageInBoxList 訊息清單
	 */
	
	@RequestMapping
	public String main(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = SystemInfo.PAGE_SIZE) int pageSize, Model model) {

		UserProfileBean userDetails = systemInfo.getUserProfileBean();	
		int roleId=userDetails.getRoleId();
		String branchId=userDetails.getBranchId();
		try {

			Page<MessageInbox> messageInBoxList =iMessageInboxRepositoryPersist.findMessageInboxByReceiverRole(roleId,branchId,pageNumber, pageSize);
			
			model.addAttribute("messageInBoxList", messageInBoxList);
			return "common/messageBox";
		} catch (Exception e) {
			log.error("訊息匣清單顯示失敗:"+e);
			e.printStackTrace();
			model.addAttribute("message", SystemInfo.SYSTEM_ERROR);
			model.addAttribute("functionName", "訊息匣");
			return "common/message";
		}

		
	}
	
	/**
	 * 刪除訊息
	 * 
	 * @param messageIdSelect 選擇刪除的訊息ID
	 * @return 回訊息匣清單
	 */
	
	@RequestMapping(value = "/delMessage", method = RequestMethod.POST)
	public String delMessage(Model model, @RequestParam List<String> messageIdSelect) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		String userId = userDetails.getUsername();
		String userName = userDetails.getViewUserName();
		
		messageIdSelect.forEach(messageId->{
			MessageInbox messageInbox=iMessageInboxRepositoryPersist.findMessageInboxByMessageId(Integer.valueOf(messageId));
			try {
					if(messageInbox!=null){
						iMessageInboxRepositoryPersist.deleteMessageInbox(messageInbox);
//						String logContent="使用者：" + userId + "\\n刪除訊息匣的資料內容：\\n&nbsp;&nbsp;訊息編號(" + messageId +
//		                    ")\\n&nbsp;&nbsp;訊息內容(" + messageInbox.getContent() + ")";
						String logContent="使用者：" + userId + "刪除訊息匣的資料內容：訊息編號(" + messageId +
			                    ")，訊息內容(" + messageInbox.getContent() + ")";
						iUserLogBusiness.messageInboxDelete(userId, userName, logContent);
					}
				} catch (Exception e) {
					log.error("訊息刪除失敗 :"+e);
					e.printStackTrace();
//					String logContent="使用者：" + userId + "\\n刪除訊息匣的資料內容：\\n&nbsp;&nbsp;訊息編號(" + messageId +
//		                    ")\\n&nbsp;&nbsp;訊息內容(" + messageInbox.getContent() + ")";
					String logContent="使用者：" + userId + "刪除訊息匣的資料內容：訊息編號(" + messageId +
		                    ")，訊息內容(" + messageInbox.getContent() + ")";
					iUserLogBusiness.ErrorLog(userId, userName, SystemInfo.USER_LOG_FUNCTION_ID_MESSAGEINBOX, SystemInfo.USER_LOG_ACTION_DELETE, logContent);
				}
		});

		return "redirect:/MessageInbox";
	}	
	
}
