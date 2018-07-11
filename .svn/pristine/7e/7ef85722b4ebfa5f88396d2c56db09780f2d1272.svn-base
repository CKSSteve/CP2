package com.uxb2b.ecp.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.uxb2b.ecp.model.PassRecord;



@Repository
public  interface PassRecordRepository extends JpaRepository<PassRecord, Integer>,PagingAndSortingRepository<PassRecord, Integer>, JpaSpecificationExecutor<PassRecord> {

	public int countByStatus(String status);
	
	public List<PassRecord> findByStatus(String status);

	public Page<PassRecord> findByStatusIn(List<String> status,Pageable pageRequest);
	
	public List<PassRecord> findByRecordIdIn(List<Long> recordIdList);
	
	@Modifying
	@Query("UPDATE  PassRecord d SET d.status = :status WHERE d.recordId IN :recordId")
	public void updateStatusByRecordIdIn(@Param("status") String status , @Param("recordId") List<Long> recordId );
}
