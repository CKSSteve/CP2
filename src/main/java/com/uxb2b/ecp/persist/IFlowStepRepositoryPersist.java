package com.uxb2b.ecp.persist;

import java.util.List;

import com.uxb2b.ecp.model.FlowStep;

/**
 * FlowFunction TABLE
 * @author evayang
 *
 */
public interface IFlowStepRepositoryPersist {

	public int getMaxStepNoByFlowId(int flowId);
	
	public int findRoleIdByFlowIdAndStepNo(int flowId,int stepNo);
	
	public String findFinalStepByFlowIdAndStepNo(int flowId,int stepNo);

	public List<FlowStep> findFlowStepByFlowId(int flowId);
}
