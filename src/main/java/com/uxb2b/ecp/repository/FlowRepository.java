package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.FlowFunction;
import com.uxb2b.ecp.model.FlowFunctionPK;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.User;



@Repository
@Transactional
public  interface FlowRepository extends JpaRepository<Flow, Integer>,PagingAndSortingRepository<Flow, Integer>, JpaSpecificationExecutor<Flow> {
	
	public Flow findByFlowId(int flowId);		
	
}
