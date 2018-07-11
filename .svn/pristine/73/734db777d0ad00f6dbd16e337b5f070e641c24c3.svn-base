package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uxb2b.ecp.model.SysCode;



@Repository
public  interface SysCodeRepository extends JpaRepository<SysCode, Integer>{

	public List<SysCode> findByIdCodeTypeOrderByIdCodeKeyAsc(String codeType);
	
	public SysCode findByIdCodeTypeAndIdCodeKey(String codeType,String codeKey);
}
