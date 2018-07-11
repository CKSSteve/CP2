package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.bean.swift.Q02RsBean;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.Function;

/**
 * 下載決標資料
 * @author evayang
 *
 */
public interface IDownloadAwardDataBusiness {
	
	/**
	 * 傳送Q02電文
	 * @return
	 */
	public List<Q02RsBean> sendQ02Rq();
	
	/**
	 * 檢查Q02電文回傳欄位
	 * @param response
	 * @return
	 */
	public void vaildQ02RsData(String request,String response);
	
	/**
	 * 接收Q02電文資料處理
	 * @param response
	 * @return
	 */
	
	public boolean downloadAwardDataRsLogic(Q02RsBean response) throws Exception;
}
