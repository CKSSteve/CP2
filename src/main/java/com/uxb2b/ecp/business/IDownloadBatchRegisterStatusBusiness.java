package com.uxb2b.ecp.business;


/**
 * 下載本票登錄狀態
 * @author steve
 *
 */
public interface IDownloadBatchRegisterStatusBusiness {

	/**
	 * 處理Q03下行電文
	 * @param request
	 * @param response
	 */
	public void processQ03RS(String request,String response);
	
}
