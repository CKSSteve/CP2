package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import javax.persistence.Query;

import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.FlowFunction;
import com.uxb2b.ecp.model.FlowFunctionPK;
import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.persist.IBranchRepositoryPersist;
import com.uxb2b.ecp.persist.IFlowMaintainPersist;
import com.uxb2b.ecp.repository.FlowFunctionRepository;
import com.uxb2b.ecp.repository.FlowRepository;
import com.uxb2b.ecp.repository.FlowStepRepository;


@Repository
@Transactional
public class FlowMaintainPersistImpl implements IFlowMaintainPersist{
	
	private Logger log = LoggerFactory.getLogger(FlowMaintainPersistImpl.class);
	
	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private FlowRepository flowRepository;
	
	@Autowired
	private FlowFunctionRepository flowFunctionRepository;
	
	@Autowired
	private FlowStepRepository flowStepRepository;
	
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int saveFlow(Flow flow) {
		return flowRepository.save(flow).getFlowId();
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveFlowFunction(FlowFunction flowFunction) {
		flowFunctionRepository.save(flowFunction);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveFlowStep(FlowStep flowStep) {
		flowStepRepository.save(flowStep);
	}
	
}
