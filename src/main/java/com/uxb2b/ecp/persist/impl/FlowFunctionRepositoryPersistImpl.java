package com.uxb2b.ecp.persist.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.uxb2b.ecp.persist.IFlowFunctionRepositoryPersist;
import com.uxb2b.ecp.repository.FlowFunctionRepository;



@Repository
@Transactional
public class FlowFunctionRepositoryPersistImpl implements IFlowFunctionRepositoryPersist{

	@Autowired
	FlowFunctionRepository flowFunctionRepository;
	
	@Override
	public int findFlowId(String functionId) {
		
		return flowFunctionRepository.findByIdFunctionId(functionId).getId().getFlowId();
	}
	
	
	
	
}
