package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.persist.IRoleRepositoryPersist;
import com.uxb2b.ecp.repository.RoleRepository;

@Repository
@Transactional
public class RoleRepositoryPersistImpl implements IRoleRepositoryPersist{

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Page<Role> getAllRole(int pageNumber,int pageSize) {
		
		PageRequest pageRequest = buildPageRequest(pageNumber,pageSize);
		return roleRepository.findAll(pageRequest);
		
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize, new Sort(Direction.ASC,"roleId"));
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteRole(int roleId) {

		roleRepository.delete(roleId);
		
	}

	@Override
	public List<Integer> getAllRoleId() {
		// TODO Auto-generated method stub
		return roleRepository.getAllRoleId();
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveRole(Role role) {
		
		roleRepository.save(role);
		
	}

	@Override
	public List<Role> getNewRole() {
		
		return roleRepository.getNewRole();
	}

	@Override
	public List<Role> findAllRole() {
		
		return roleRepository.findAll();
	}

	@Override
	public List<Role> findRoleNot(List<Integer> roleId) {
		// TODO Auto-generated method stub
		return roleRepository.findRoleNot(roleId);
	}

	@Override
	public Role findByRoleId(int roleId) {
		
		return roleRepository.findByRoleId(roleId);
	}
	
}
