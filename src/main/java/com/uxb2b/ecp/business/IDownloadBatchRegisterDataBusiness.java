package com.uxb2b.ecp.business;


/**
 * 下載本票登錄資料
 * @author steve
 *
 */
public interface IDownloadBatchRegisterDataBusiness {

	/**
	 * 處理Q04下行電文
	 * @param request
	 * @param response
	 */
	public void processQ04RS(String request,String response);
	
}
