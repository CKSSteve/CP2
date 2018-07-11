package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uxb2b.ecp.model.Function;


@Repository
public  interface FunctionRepository extends JpaRepository<Function, Integer>{

	@Query("SELECT a FROM Function a WHERE a.functionId like %:functionId% AND NOT a.functionId='MessageInBox'" )
	public List<Function> getFunctionByFunctionId(@Param("functionId") String functionId);
	
	public Function findByFunctionId(String functionId);
	
	@Query("SELECT p FROM Function p where p.saveLog = '1' ORDER BY p.orderIndex ASC")
	public List<Function> findAll();
	
	@Query("SELECT p FROM Function p where p.startFlow = '1' ORDER BY p.orderIndex ASC")
	public List<Function> findFlowFunctinList();
	
}
