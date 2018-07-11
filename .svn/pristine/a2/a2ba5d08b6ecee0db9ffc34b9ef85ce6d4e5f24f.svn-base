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
import com.uxb2b.ecp.model.FlowFunction;
import com.uxb2b.ecp.model.FlowFunctionPK;
import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.model.User;



@Repository
@Transactional
public  interface FlowStepRepository extends JpaRepository<FlowStep, Integer>,PagingAndSortingRepository<FlowStep, Integer>, JpaSpecificationExecutor<FlowStep> {
											
	@Query("SELECT MAX(d.id.stepNo) FROM FlowStep d WHERE d.id.flowId = :flowId")
	public int getMaxStepNoByFlowId(@Param("flowId") int flowId);
	
	@Modifying
	@Query("delete from FlowStep t  WHERE t.id.flowId = :flowId ")
	public void del(@Param("flowId") int flowId);
	
	@Query("SELECT f FROM FlowStep f WHERE f.id.flowId = :flowId order by f.id.stepNo desc")
	public List<FlowStep> findbyFlowId(@Param("flowId") int flowId);
	
	public FlowStep findByIdFlowIdAndIdStepNo(int flowId,int stepNo);
}
