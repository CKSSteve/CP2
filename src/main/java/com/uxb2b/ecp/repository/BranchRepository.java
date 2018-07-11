package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.User;



@Repository
public  interface BranchRepository extends JpaRepository<Branch, Integer>,PagingAndSortingRepository<Branch, Integer>, JpaSpecificationExecutor<Branch> {

	public Branch findBranchByBranchId(String branchId);
	
	@Query("SELECT a FROM Branch a where typeId = :typeId AND a.branchId not in (SELECT s.id.branchId FROM ServiceCenterMapping s)")
	public List<Branch> findBranchNotInServiceCenter(@Param("typeId") String typeId );
	
}
