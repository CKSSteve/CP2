package com.uxb2b.ecp.persist;

import java.util.List;

import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.model.RejectReason;

/**
 * REJECT_REASON TABLE
 * @author evayang
 *
 */
public interface IRejectReasonRepositoryPersist {
	
	/**
	 * 
	 * @param codeType
	 * @param codeKey
	 * @return
	 */
	public RejectReason findByIdCodeTypeAndIdCodeKey(String codeType, String codeKey);
	
	/**
	 * 取得目前codeType的最大codeKey值
	 * @param codeType
	 * @return
	 */
	public String getMaxCodeKeyByCodeType(String codeType);
	
	/**
	 * 更新 REJECT_REASON TABLE
	 * @param rejectReason
	 */
	public void saveRejectReason(RejectReason rejectReason);

}

