package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.ServiceCenterMapping;



@Repository
@Transactional
public  interface ServiceCenterMappingRepository extends JpaRepository<ServiceCenterMapping, Integer>{

	@Modifying
	@Query("Delete FROM ServiceCenterMapping d WHERE d.id.centerId = :centerId AND d.id.branchId = :branchId")
	public void deleteByCenterIdBranchId(@Param("centerId") String centerId,@Param("branchId") String branchId);
	
	public List<ServiceCenterMapping> findByIdCenterId(String centerId);
	
	@Modifying
	@Query("Delete FROM ServiceCenterMapping d WHERE d.id.centerId = :centerId")
	public void deleteByCenterIdB(@Param("centerId") String centerId);
}
