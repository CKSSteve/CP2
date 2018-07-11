package com.uxb2b.ecp.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import com.uxb2b.ecp.business.IRoleBusiness;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.Role;
import com.uxb2b.ecp.model.RoleFunction;
import com.uxb2b.ecp.model.RoleFunctionPK;
import com.uxb2b.ecp.model.User;
import com.uxb2b.ecp.persist.IRoleRepositoryPersist;
import com.uxb2b.ecp.persist.IUserRepositoryPersist;
import com.uxb2b.ecp.repository.FunctionRepository;
import com.uxb2b.ecp.repository.RoleFunctionRepository;
import com.uxb2b.ecp.persist.IRoleFunctionRepositoryPersist;

@Component
public class RoleBusinessImpl implements IRoleBusiness {

	@Autowired
	IRoleRepositoryPersist iRoleRepositoryPersist;

	@Autowired
	IUserRepositoryPersist iUserRepositoryPersist;

	@Autowired
	FunctionRepository functionRepository;

	@Autowired
	RoleFunctionRepository roleFunctionRepository;
	
	@Autowired
	IRoleFunctionRepositoryPersist iRoleFunctionRepositoryPersist;

	@Override
	public Page<Role> getAllRole(int pageNumber, int pageSize) {

		return iRoleRepositoryPersist.getAllRole(pageNumber, pageSize);
	}

	@Override
	public List<User> getAllUser() {

		return iUserRepositoryPersist.getAllUser();
	}

	@Override
	public void deleteRole(int roleId) {

		iRoleRepositoryPersist.deleteRole(roleId);

	}

	@Override
	public List<Function> getFunctionByFunctionId(String fuctionId) {

		return functionRepository.getFunctionByFunctionId(fuctionId);
	}

	@Override
	public List<RoleFunction> findRoleFunctionByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return iRoleFunctionRepositoryPersist.findByIdRoleId(Integer.valueOf(roleId));
	}

	@Override
	public void deleteRoleFunctionByRoleId(int roleId) {
		iRoleFunctionRepositoryPersist.deleteByRoleId(roleId);
	}

	@Override
	public void settingRoleFunction(int roleId, String A, String B,
			String C, String D) {

		List<RoleFunction> roleFunctionList = new ArrayList<RoleFunction>();
		Boolean aIsNotBlank=StringUtils.isNotBlank(A);
		Boolean bIsNotBlank=StringUtils.isNotBlank(B);
		Boolean cIsNotBlank=StringUtils.isNotBlank(C);
		Boolean dIsNotBlank=StringUtils.isNotBlank(D);
		String tenderNoFunction[]=aIsNotBlank?A.split(","):null;
		String promissoryNoteFunction[]=bIsNotBlank?B.split(","):null;
		String guaranteeFunction[]=cIsNotBlank?C.split(","):null;
		String manageFunctions[]=dIsNotBlank?D.split(","):null;
				
		if (aIsNotBlank) {
			for(int i=0;i<tenderNoFunction.length ;i++){
				if(tenderNoFunction[i].length()>1){
					RoleFunction RoleFunction = new RoleFunction();
					RoleFunctionPK RoleFunctionPK = new RoleFunctionPK(roleId,tenderNoFunction[i].trim());
					RoleFunction.setId(RoleFunctionPK);
					roleFunctionList.add(RoleFunction);
				}
			}
		}  if (bIsNotBlank) {
			for(int i=0;i<promissoryNoteFunction.length ;i++){
				if(promissoryNoteFunction[i].length()>1){
					RoleFunction RoleFunction = new RoleFunction();
					RoleFunctionPK RoleFunctionPK = new RoleFunctionPK(roleId,promissoryNoteFunction[i].trim());
					RoleFunction.setId(RoleFunctionPK);
					roleFunctionList.add(RoleFunction);
				}
			}
		} if (cIsNotBlank) {
			for(int i=0;i<guaranteeFunction.length ;i++){
				if(guaranteeFunction[i].length()>1){
				RoleFunction RoleFunction = new RoleFunction();
				RoleFunctionPK RoleFunctionPK = new RoleFunctionPK(roleId,guaranteeFunction[i].trim());
				RoleFunction.setId(RoleFunctionPK);
				roleFunctionList.add(RoleFunction);
				}
			}
		}  if (dIsNotBlank) {
			for(int i=0;i<manageFunctions.length ;i++){
				if(manageFunctions[i].length()>1){
					RoleFunction RoleFunction = new RoleFunction();
					RoleFunctionPK RoleFunctionPK = new RoleFunctionPK(roleId,manageFunctions[i].trim());
					RoleFunction.setId(RoleFunctionPK);
					roleFunctionList.add(RoleFunction);
				}
			}
		}
		iRoleFunctionRepositoryPersist.saveByRoleFunctionList(roleFunctionList);
	
	}

	@Override
	public void saveRole(Role role) {
		
		iRoleRepositoryPersist.saveRole(role);
		
	}

	@Override
	public List<Role> getNewRole() {
		
		return iRoleRepositoryPersist.getNewRole();
		
	}

	@Override
	public List<Role> findAllRole() {
		
		return iRoleRepositoryPersist.findAllRole();
		
	}

	@Override
	public List<Role> findRoleNot(List<Integer> roleId) {
		
		return iRoleRepositoryPersist.findRoleNot(roleId);
	}

	@Override
	public Role findByRoleId(int roleId) {
		
		return iRoleRepositoryPersist.findByRoleId(roleId);
	}


}
