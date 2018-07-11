package com.uxb2b.ecp.repository;

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
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.bean.RejectReasonMaintainBean;
import com.uxb2b.ecp.model.RejectReason;




@Repository
@Transactional
public  interface RejectReasonRepository extends JpaRepository<RejectReason, Integer>,PagingAndSortingRepository<RejectReason, Integer>, JpaSpecificationExecutor<RejectReason> {
	
	public RejectReason findByIdCodeTypeAndIdCodeKey(String codeType, String codeKey);
	
	@Query("SELECT MAX(d.id.codeKey)+1 FROM RejectReason d WHERE d.id.codeType = :codeType")
	public String getMaxCodeKeyByCodeType(@Param("codeType") String codeType);
	
	
	@Query("SELECT new com.uxb2b.ecp.bean.RejectReasonMaintainBean(a.id.codeType,a.id.codeKey,b.codeValue,a.codeValue,a.errorCode,a.status,a.memo) from RejectReason a JOIN SysCode b on a.id.codeType=b.id.codeKey where b.id.codeType = :codeType")
	public Page<RejectReasonMaintainBean> getAllJoinSysCode(@Param("codeType") String codeType,Pageable pageRequest);
	
	public List<RejectReason> findByIdCodeTypeAndStatusOrderByIdCodeKeyAsc(String codeType,short stataus);
}
