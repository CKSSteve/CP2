package com.uxb2b.ecp.repository;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbt1;



@Repository
public  interface Ctbflbq1Repository extends JpaRepository<Ctbflbq1, Integer>,PagingAndSortingRepository<Ctbflbq1, Integer>, JpaSpecificationExecutor<Ctbflbq1>{

	public int countByRoleIdAndBranchIdAndApproveStatusIn(int roleId,String branchId,List<String> AppproveStatus);
	
	public Ctbflbq1 findByQuationNo(String quationNo);
	
	public Page<Ctbflbq1> findByBranchIdAndRoleIdAndApproveStatusIn(String branchId,int roleId,List<String> status,Pageable pageRequest);
	
	public Ctbflbq1 findCtbflbq1ByTenderNo(String tenderNo);

	public List<Ctbflbq1> findByFlowIdAndApproveStatusNot(int flowId, String approveStatus);
	
	public List<Ctbflbq1> findByIssuerIdAndStatusIn(String issuer,List<String> status);
	
}
