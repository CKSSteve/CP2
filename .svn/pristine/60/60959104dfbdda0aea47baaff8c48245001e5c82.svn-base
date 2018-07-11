package com.uxb2b.ecp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.uxb2b.ecp.model.Ctbflbt2;



@Repository
public  interface Ctbflbt2Repository extends JpaRepository<Ctbflbt2, Integer>,PagingAndSortingRepository<Ctbflbt2, Integer>, JpaSpecificationExecutor<Ctbflbt2>{

	public Ctbflbt2 findByIdTenderNoAndDays(String tenderNo,int days);
	
}
