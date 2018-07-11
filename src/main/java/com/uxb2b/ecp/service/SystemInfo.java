package com.uxb2b.ecp.service;


import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.uxb2b.ecp.bean.UserProfileBean;




@Service
public class SystemInfo {
	
	/*spring-service*/
	public static final String BANK_CODE="bankCode";
	public static final String BANK_NAME="bankName";
	public static final String CERTSIGNURL="cert.sign.url";
	public static final String SHOW_LOGIN_ERROR="show.login.error.message";
	
	/*電文TXID*/
	public static final String TXID_DOWNLOAD_TENDER="Q01";
	public static final String TXID_DOWNLOAD_AWARDDATA="Q02";
	public static final String TXID_DOWNLOAD_BATCH_REGISTER_STATUS="Q03";
	public static final String TXID_DOWNLOAD_BATCH_REGISTER="Q04";
	public static final String TXID_DOWNLOAD_ENTRUSTDOC_STATEMENTDOC="Q05";
	public static final String TXIDUPLOADTENDERSTATUS="T01";
	public static final String TXIDQUATION="T02";
	public static final String TXID_AWARDDATA="T03";
	public static final String TXIDRATEDETAIL="T05";
	
	/*電文回應代碼*/
	public static final String RETURN_CODE_SUCCESSFUL="0000";
	
	
	public static final String PAGE_SIZE = "10";
	
	public static final String NOT_MARK_STATUS="190";
	
	public static final String BRANCHTYPEID="Branch";
	public static final String SERVICECENTERTYPEID="ServiceCenter";
	public static final String HEADOFFICETYPEID="HeadOffice";
	/*USER*/
	public static final int ERROR_COUNT_CLEAR = 0;
    /**
     * 啟用帳號
     */
    public static final Short USER_OPEN = 1;
    /**
     * 關閉帳號
     */
    public static final Short USER_CLOSE = 0;
	
	/*USER_LOG*/
	public static final String USER_LOG_ACTION_LOGIN = "登入";
	public static final String USER_LOG_ACTION_MODIFY = "修改";
	public static final String USER_LOG_ACTION_ADD = "新增";
	public static final String USER_LOG_ACTION_DELETE = "刪除";
	public static final String USER_LOG_ACTION_OPEN = "開啟";
	public static final String USER_LOG_ACTION_CLOSE = "關閉";
	public static final String USER_LOG_ACTION_DONOTQUOTION="報價登錄-不報價";
	public static final String USER_LOG_ACTION_DOQUOTION = "報價-已報價";
	
	public static final String USER_LOG_CONTENT_LOGINERROR_THREE = "登入失敗超過三次";
	public static final String USER_LOG_CONTENT_USER_STOP = "此帳號已停用";
	public static final String USER_LOG_CONTENT_LOGIN_NORMAL = "正常登入";
	public static final String USER_LOG_CONTENT_PWDCHANGE = "密碼變更";
	public static final String USER_LOG_CONTENT_LOGOUT = "登出";
	
	public static final String USER_LOG_STATUS_SUCCESS = "Success";
	public static final String USER_LOG_STATUS_ERROR = "Failure";
	
	public static final String USER_LOG_FUNCTION_ID_LOGIN = "Login";		   		//登入
	public static final String USER_LOG_FUNCTION_ID_LOGOUT = "Logout";		   		//登出
	public static final String USER_LOG_FUNCTION_ID_QUOTION_ENTRY = "A1";       	//報價登陸
	public static final String USER_LOG_FUNCTION_ID_QUOTION_REVIEW = "A2";       	//報價審核
	public static final String USER_LOG_FUNCTION_ID_ROLE_SETTING = "D1";       		//角色權限維護
    public static final String USER_LOG_FUNCTION_ID_ACCOUNT_SETTING = "D2";	   		//帳號管理
	public static final String USER_LOG_FUNCTION_ID_PERSONIUSERSETTING = "D3"; 		//個人資料維護
	public static final String USER_LOG_FUNCTION_ID_BRANCH_SETTING = "D5";	   		//分行資料維護
	public static final String USER_LOG_FUNCTION_ID_MESSAGEINBOX="MessageInBox";	//訊息
	public static final String USER_LOG_FUNCTION_ID_FLOW_MAINTAIN = "D8";	   //簽核流程維護
	public static final String USER_LOG_FUNCTION_ID_REJECT_REASON_MAINTAIN = "D9";	   //退回原因維護
	public static final String USER_LOG_FUNCTION_ID_AWARDDATA_REGISTER= "B1";	   //票券批號登錄
	public static final String USER_LOG_FUNCTION_ID_AWARDDATA_VERIFY= "B2";	   //票券批號審核
	public static final String USER_LOG_FUNCTION_ID_RATEDETAILS_REGISTER= "B5";
	public static final String USER_LOG_FUNCTION_ID_RATEDETAILS_VERIFY= "B6";
	
	public static final String LOGIN_MESSAGE_OVERTHIRTYDAY="OverThirtydat";
	public static final String LOGIN_MESSAGE_FIRSYLOGIN="FirstLogin";
	public static final String LOGIN_MESSAGE_LOGINERROR_THREE="LoginErrorThree";
	public static final String LOGIN_MESSAGE_USER_STOP="UserStop";
	public static final String LOGIN_MESSAGE_PASSWORDERROR="PasswordError";
	public static final String LOGIN_MESSAGE_IDDERROR="IdError";
	
	/*SYS_CODE*/
	public static final String SYSCODE_CODETYPE_CURRENCY="CURRENCY";
	public static final String SYSCODE_CODETYPE_QUOTION_REASON_FOR_RETURN="REJECT-A1";
	public static final String SYSCODE_CODETYPE_RATEDETAILS_REASON_FOR_RETURN="REJECT-B5";
	public static final String SYSCODE_CODETYPE_REASON_FOR_REJECT="REJECT-B1";
	
	
	/*REJECT_REASON*/
	public static final String REJECT_REASON_CODE_TYPE_QUOTION = "A1";
	public static final String REJECT_REASON_CODE_TYPE_AWARD_DATA = "B1";
	public static final String REJECT_REASON_CODE_TYPE_RAREDETAILS = "B5";
	public static final short REJECT_REASON_STATUS_ON = 1;
	
	/*PASS_RECORD*/
	public static final String PASS_RECORD_STATUS_SUCCESS="A";
	public static final String PASS_RECORD_STATUS_FAIL="B";
	public static final String PASS_RECORD_STATUS_SENDING="C";
	public static final String PASS_RECORD_STATUS_RESEND="D";
	
	/*CTBFLBQ1*/
	public static final String CTBFLBQ1_STATUS_NOT_QUOTED="080";       //未報價
	public static final String CTBFLBQ1_STATUS_DO_NOT_QUOTE="090";
	public static final String CTBFLBQ1_STATUS_HAS_QUOTED="100";      //已報價
	
	public static final String CTBFLBQ1_APPROVE_STATUS_TO_BE_REGISTER="0";	   //待登錄
	public static final String CTBFLBQ1_APPROVE_STATUS_MANAGER_RETURNED="1";   //主管退回

	/*電文錯誤訊息*/
	public static final String RETURN_CODE_0001="查無資料";
	public static final String RETURN_CODE_0100="電文格式錯誤";
	public static final String RETURN_CODE_0198="WebService發生錯誤[0198]";
	public static final String RETURN_CODE_0199="WebService發生錯誤[0199]";
	public static final String RETURN_CODE_9999="未知錯誤";
	
	/*MAIL TYPE*/
	public static final String MAIL_TYPE_TENDER_RETURN="TENDER_RETURN";
	public static final String MAIL_TYPE_RATE_DETAIL_RETURN="RATE_DETAIL_RETURN";
	public static final String MAIL_TYPE_AWARD_DATA_RETURN="AWARD_DATA_RETURN";
	
	public static final Integer PRINT_PDF = 1;
	public static final String SYSTEM_ERROR="系統發生錯誤，請通知系統管理員";
	
	private Map<String, String> approve;
	private Map<String, String> staticParameter;
	private Map<String,String> downloadAwardDataDaemonEnabl;
	private Map<String,String> sendMailInfo;
	
	public UserProfileBean getUserProfileBean() {
		
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || !authentication.isAuthenticated()) {
				return null;
			}
			return (UserProfileBean) authentication.getPrincipal();
	
	}

	public String swiftErrorMessage(String errorCode){
		
		String message="";
		switch (errorCode) {
		case "0001":
			message=RETURN_CODE_0001;
			break;
		case "0100":
			message=RETURN_CODE_0100;
			break;
		case "0198":
			message=RETURN_CODE_0198;
			break;
		case "0199":
			message=RETURN_CODE_0199;
			break;
		default:
			message=RETURN_CODE_9999;
			break;
		}
		
		return message;
		
	}

	public Map<String, String> getApprove() {
		return approve;
	}

	public  void setApprove(Map<String, String> approve) {
		this.approve = approve;
	}

	public Map<String, String> getStaticParameter() {
		return staticParameter;
	}

	public void setStaticParameter(Map<String, String> staticParameter) {
		this.staticParameter = staticParameter;
	}

	public Map<String, String> getDownloadAwardDataDaemonEnabl() {
		return downloadAwardDataDaemonEnabl;
	}

	public void setDownloadAwardDataDaemonEnabl(Map<String, String> downloadAwardDataDaemonEnabl) {
		this.downloadAwardDataDaemonEnabl = downloadAwardDataDaemonEnabl;
	}
	
	public Map<String, String> getSendMailInfo() {
		return sendMailInfo;
	}

	public void setSendMailInfo(Map<String, String> sendMailInfo) {
		this.sendMailInfo = sendMailInfo;
	}
}
