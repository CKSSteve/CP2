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

import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.model.Ctbflbt1;



@Repository
public  interface Ctbflbq2Repository extends JpaRepository<Ctbflbq2, Integer>,PagingAndSortingRepository<Ctbflbq2, Integer>, JpaSpecificationExecutor<Ctbflbq2>{

	public Ctbflbq2 findByIdQuationNoAndIdItemNo(String quationNo,int itemNo);
	
	public List<Ctbflbq2> findByIdQuationNoOrderByIdItemNo(String quationNo);
	
	public void deleteByIdQuationNo(String quationNo);
	
	public Page<Ctbflbq2> findByIdQuationNo(Pageable pageRequest,String quationNo);
}
