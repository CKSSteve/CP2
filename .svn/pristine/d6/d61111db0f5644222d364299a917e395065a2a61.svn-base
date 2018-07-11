package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.Function;

/**
 * 票券批號(得標確認)審核
 * @author evayang
 *
 */
public interface IAwardDataVerifyBusiness {
	
	/**
	 * 票券批號(得標確認)審核查詢
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<AwardData> findbyAwardDataList(int pageNumber, int pageSize) throws Exception;
	
	/**
	 * 票券批號(得標確認)審核明細查詢
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @return
	 * @throws Exception
	 */
	public AwardData findByQuationNoAndItemNo(String quationNo, String itemNo) throws Exception;
	
	/**
	 * 票券批號(得標確認)審核更新
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @return
	 * @throws Exception
	 */
	public AwardData updateAwardData(String quationNo, String itemNo) throws Exception;
	
	/**
	 * 票券批號(得標確認)審核更新
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @param rejectReason1 退回理由
	 * @return
	 * @throws Exception
	 */
	public AwardData updateAwardData(String quationNo, String itemNo, String rejectReason1) throws Exception;
	
}
