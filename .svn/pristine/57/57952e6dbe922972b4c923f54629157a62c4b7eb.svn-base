package com.uxb2b.ecp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.uxb2b.ecp.model.Ctbflbt1;



@Repository
public  interface Ctbflbt1Repository extends JpaRepository<Ctbflbt1, Integer>,PagingAndSortingRepository<Ctbflbt1, Integer>, JpaSpecificationExecutor<Ctbflbt1>{

	public Ctbflbt1 findByTenderNo(String tenderNo);
	
}
