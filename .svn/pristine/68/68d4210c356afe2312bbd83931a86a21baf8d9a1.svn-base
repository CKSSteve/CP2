package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.CpData;



@Repository
public  interface CpDataRepository extends JpaRepository<CpData, Integer>,PagingAndSortingRepository<CpData, Integer>, JpaSpecificationExecutor<CpData> {

	public int countByRoleIdAndApproveStatus(int roleId,String approveStatus);
	
	public List<CpData> findByFlowIdAndApproveStatusNot(int flowId, String approveStatus);
	
	public CpData findByBatchNo(String batchNo);
	
	public List<CpData> findByStatusNotIn(List<Integer> status);
}
