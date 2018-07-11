package com.uxb2b.ecp.persist.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.persist.IFlowStepRepositoryPersist;

import com.uxb2b.ecp.repository.FlowStepRepository;


@Repository
@Transactional
public class FlowStepRepositoryPersistImpl implements IFlowStepRepositoryPersist{

	@Autowired
	FlowStepRepository flowStepRepository;

	@Override
	public int getMaxStepNoByFlowId(int flowId) {
	
		return flowStepRepository.getMaxStepNoByFlowId(flowId);
	}

	@Override
	public int findRoleIdByFlowIdAndStepNo(int flowId,int stepNo) {
		
		return flowStepRepository.findByIdFlowIdAndIdStepNo(flowId, stepNo).getRoleId();
	}

	@Override
	public List<FlowStep> findFlowStepByFlowId(int flowId) {
		
		return flowStepRepository.findbyFlowId(flowId);
	}

	@Override
	public String findFinalStepByFlowIdAndStepNo(int flowId, int stepNo) {
		return flowStepRepository.findByIdFlowIdAndIdStepNo(flowId, stepNo).getFinalStep();
	} 
	
	
	
}
