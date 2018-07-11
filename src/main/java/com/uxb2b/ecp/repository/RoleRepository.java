package com.uxb2b.ecp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.model.User;



@Repository
@Transactional
public  interface RoleRepository extends JpaRepository<Role, Integer>,PagingAndSortingRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

	@Query("SELECT a.roleId FROM Role a")
	public List<Integer> getAllRoleId();
	
	@Query("SELECT a FROM Role a ORDER BY a.roleId DESC")
	public List<Role> getNewRole();
	
	@Query("SELECT a FROM Role a where a.roleId not in:roleId")
	public List<Role> findRoleNot(@Param("roleId") List<Integer> roleId);
	
	public Role findByRoleId(int roleId);
}

