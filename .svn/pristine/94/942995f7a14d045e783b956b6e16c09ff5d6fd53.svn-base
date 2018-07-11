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
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.FlowFunction;
import com.uxb2b.ecp.model.FlowFunctionPK;
import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.persist.IBranchRepositoryPersist;
import com.uxb2b.ecp.persist.IEnterpriseRepositoryPersist;
import com.uxb2b.ecp.persist.IFlowMaintainPersist;
import com.uxb2b.ecp.repository.Ctbflbt2Repository;
import com.uxb2b.ecp.repository.EnterpriseRepository;
import com.uxb2b.ecp.repository.FlowFunctionRepository;
import com.uxb2b.ecp.repository.FlowRepository;
import com.uxb2b.ecp.repository.FlowStepRepository;


@Repository
@Transactional
public class EnterpriseRepositoryPersistImpl implements IEnterpriseRepositoryPersist{
	
	private Logger log = LoggerFactory.getLogger(EnterpriseRepositoryPersistImpl.class);
	
	@Autowired
	EnterpriseRepository enterpriseRepository;

	@Override
	public Enterprise findByUni(String uni) {
		return enterpriseRepository.findByUni(uni);
	}
	
}
