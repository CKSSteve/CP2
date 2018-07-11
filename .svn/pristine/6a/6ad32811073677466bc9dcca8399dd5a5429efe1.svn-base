package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.bean.RejectReasonMaintainBean;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.RejectReason;

/**
 * 退回原因維護
 * @author evayang
 *
 */
public interface IRejectReasonMaintainBusiness {
	
	/**
	 * 退回原因維護頁面查詢
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<RejectReasonMaintainBean> queryRejectReasonList(int pageNumber, int pageSize) throws Exception;
	
	/**
	 * 退回原因維護- 修改查詢
	 * @param codeType 代碼類別 
	 * @param codeKey 代碼key 
	 * @return
	 */
	public RejectReasonMaintainBean findByIdCodeTypeNoAndIdCodeKey(String codeType, String codeKey);
	
	/**
	 * 退回原因維護- 修改/更新
	 * @param searchBean
	 */
	public void save(RejectReasonMaintainBean searchBean);
	
}
