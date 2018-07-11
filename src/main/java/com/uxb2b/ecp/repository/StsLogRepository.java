package com.uxb2b.ecp.repository;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.uxb2b.ecp.model.StsLog;



@Repository
public  interface StsLogRepository extends JpaRepository<StsLog, Integer>,PagingAndSortingRepository<StsLog, Integer>, JpaSpecificationExecutor<StsLog>{

	
}
