package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.bean.FunctionBean;
import com.uxb2b.ecp.model.RoleFunction;


@Repository
@Transactional
public  interface RoleFunctionRepository extends JpaRepository<RoleFunction, Integer>{

	public List<RoleFunction> findByIdRoleId(int roleId);
	
	@Modifying
	@Query("Delete FROM RoleFunction d WHERE d.id.roleId = :roleId")
	public void deleteByRoleId(@Param("roleId") int roleId);
	
	@Query("SELECT new com.uxb2b.ecp.bean.FunctionBean(a.id.roleId,b.functionId,b.functionName,b.parentId,b.uri) from RoleFunction a JOIN Function b on a.id.functionId=b.functionId")
	public List<FunctionBean> getJoinRoleFunction();
}
