package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.Function;

/**
 * 簽核流程維護
 * @author evayang
 *
 */
public interface IFlowMaintainBusiness {
	
	/**
	 * 查詢需指派簽核流程之功能列表
	 * @return
	 */
	public List<Function> findFunctionList();
	
	/**
	 * 修改簽核流程檢查可編修之功能列表
	 * @param flowId 流程流水編號
	 * @return
	 */
	public List<Function> findFunctionListForModify(String flowId);	

	/**
	 * 維護簽核流程
	 * @param flowMaintainBean
	 * @return
	 */
	public FlowMaintainBean save(FlowMaintainBean flowMaintainBean);

	/**
	 * 刪除簽核流程
	 * @param flowMaintainBean
	 * @return
	 * @throws Exception
	 */
	public FlowMaintainBean del(FlowMaintainBean flowMaintainBean) throws Exception;
	
	/**
	 * 簽核流程頁面查詢
	 * @param searchBean
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<Flow> queryFlowList(FlowMaintainBean searchBean, int pageNumber, int pageSize) throws Exception;
	
	/**
	 * 簽核流程頁面查詢
	 * @param searchBean
	 * @return
	 * @throws Exception
	 */
	public List<FlowMaintainBean> queryFlowList(FlowMaintainBean searchBean) throws Exception;
	
	/**
	 * 簽核流程頁面查詢By流程流水編號
	 * @param searchBean
	 * @return
	 * @throws Exception
	 */
	public FlowMaintainBean findbyFlowId(FlowMaintainBean searchBean) throws Exception;
	
}
