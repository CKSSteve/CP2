package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.bean.FunctionBean;
import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.model.RoleFunction;
import com.uxb2b.ecp.persist.IRoleFunctionRepositoryPersist;
import com.uxb2b.ecp.repository.FunctionRepository;
import com.uxb2b.ecp.repository.RoleFunctionRepository;
import com.uxb2b.ecp.repository.RoleRepository;

@Repository
@Transactional
public class RoleFunctionRepositoryPersistImpl implements IRoleFunctionRepositoryPersist{

	@Autowired
	RoleFunctionRepository roleFunctionRepository;
	
	@Override
	public List<RoleFunction> getAllRoleFunction() {
		return roleFunctionRepository.findAll();
	}

	@Override
	public List<RoleFunction> findByIdRoleId(int roleId) {
		
		return roleFunctionRepository.findByIdRoleId(roleId);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveByRoleFunctionList(List<RoleFunction> roleFunctionList) {
		
//		roleFunctionList.forEach(action->roleFunctionRepository.save(action));
		roleFunctionRepository.save(roleFunctionList);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteByRoleId(int roleId) {

		roleFunctionRepository.deleteByRoleId(roleId);
		
	}

	@Override
	public List<FunctionBean> getJoinRoleFunction() {
		// TODO Auto-generated method stub
		return roleFunctionRepository.getJoinRoleFunction();
	}
}
