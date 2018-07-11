package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.uxb2b.ecp.model.AwardData;



@Repository
public  interface AwardDataRepository extends JpaRepository<AwardData, Integer>,PagingAndSortingRepository<AwardData, Integer>, JpaSpecificationExecutor<AwardData> {

	public int countByBranchIdAndRoleId1AndApproveStatus1In(String branchId, int roleId1, List<String> approveStatusList);
	
	public int countByRoleId2AndBranchIdAndApproveStatus2In(int roleId2,String branchId,List<String> approveStatus2);
	
	public List<AwardData> findByFlowId1AndApproveStatus1Not(int flowId1, String approveStatus1);

	public List<AwardData> findByFlowId2AndApproveStatus2Not(int flowId2, String approveStatus2);
	
	public Page<AwardData> findByBranchIdAndRoleId2AndApproveStatus2In(String BranchId,int roleId,List<String> approveStatus2,Pageable pageRequest);
	
	public Page<AwardData> findByBranchIdAndRoleId1AndApproveStatus1In(String branchId, int roleId1, List<String> approveStatusList, Pageable pageable);
	
	public AwardData findByIdQuationNoAndIdItemNo(String quationNo, int itemNo);
	
	public AwardData findByBatchNo(String batchNo);
	
	public AwardData findByBatchNoAndIdQuationNoNotOrIdItemNoNot(String batchNo, String quationNo, int itemNo);
	
}
