package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.Function;

/**
 * 票券批號登錄(得標確認)
 * @author evayang
 *
 */
public interface IAwardDataRegisterBusiness {
	
	/**
	 * 票券批號登錄(得標確認)查詢
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<AwardData> findbyAwardDataList(int pageNumber, int pageSize) throws Exception;

	/**
	 * 票券批號登錄(得標確認)明細查詢
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @return
	 * @throws Exception
	 */
	public AwardData findByQuationNoAndItemNo(String quationNo, String itemNo) throws Exception;
	
	/**
	 * 票券批號登錄(得標確認)更新
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @return
	 * @throws Exception
	 */
	public AwardData updateAwardData(String quationNo, String itemNo) throws Exception;
	
	/**
	 * 票券批號登錄(得標確認)更新
	 * @param quationNo 報價單號碼 
	 * @param itemNo 項次序號
	 * @param batchNo 票券批號
	 * @return
	 * @throws Exception
	 */
	public AwardData updateAwardData(String quationNo, String itemNo, String batchNo) throws Exception;
	
	/**
	 * 票券批號登錄(得標確認)檢查票卷批號是否重複
	 * @param batchNo
	 * @return
	 * @throws Exception
	 */
	public AwardData findByBatchNo(String batchNo, String quationNo, int itemNo) throws Exception;
}
