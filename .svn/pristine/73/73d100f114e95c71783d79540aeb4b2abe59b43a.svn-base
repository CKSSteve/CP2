package com.uxb2b.ecp.business.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.business.IBranchBusiness;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.ServiceCenterMapping;
import com.uxb2b.ecp.model.ServiceCenterMappingPK;
import com.uxb2b.ecp.persist.IBranchRepositoryPersist;
import com.uxb2b.ecp.persist.IServiceCenterMappingRepositoryPersist;
import com.uxb2b.ecp.service.SystemInfo;



@Component
public class BranchBusinessImpl implements IBranchBusiness{

	@Autowired
	SystemInfo systemInfo;
	
	@Autowired
	IBranchRepositoryPersist iBranchRepositoryPersist;
	
	@Autowired
	IServiceCenterMappingRepositoryPersist iServiceCenterMappingRepositoryPersist;
	
	@Override
	public Page<Branch> getAllBranch(int pageNumber, int pageSize) {
	
		return iBranchRepositoryPersist.getAllBranch(pageNumber, pageSize);
	}

	@Override
	public Branch findBranchByBranchId(String branchId) {
		// TODO Auto-generated method stub
		return iBranchRepositoryPersist.findBranchByBranchId(branchId);
	}

	@Override
	public List<Branch> findBranchNotInServiceCenter(String typeId) {
		// TODO Auto-generated method stub
		return iBranchRepositoryPersist.findBranchNotInServiceCenter(typeId);
	}

	@Override
	public void saveBranch(Branch branch) {
		
		iBranchRepositoryPersist.saveBranch(branch);
		
	}

	@Override
	public void setServiceCenterMapping(String centerId,String branchs) {
		String branchsArray[] = branchs.split(",");
		String bankCode = systemInfo.getStaticParameter().get(SystemInfo.BANK_CODE);
		List<ServiceCenterMapping> oldServiceCenterMapping=iServiceCenterMappingRepositoryPersist.findByIdCenterId(centerId);
		List<String> oldBranchId=new ArrayList<>();
		List<String> newBranchId=new ArrayList<>();
		IntStream.range(0, branchsArray.length).forEach(i ->newBranchId.add(bankCode+branchsArray[i].substring(0, branchsArray[i].indexOf(" "))));
		oldServiceCenterMapping.forEach(action->oldBranchId.add(action.getId().getBranchId()));
		List<String> needDelete=oldBranchId;
		List<String> needSave=newBranchId;
		newBranchId.forEach(branchId->needDelete.remove(branchId));
		oldBranchId.forEach(branchId->needSave.remove(branchId));
		needSave.forEach(branchId->saveServiceCenterMapping(centerId,branchId));
		needDelete.forEach(branchId->removeByCenterIdBranchId(centerId,branchId));
	}

	@Override
	public void removeByCenterIdBranchId(String centerId,String branchId) {
		iServiceCenterMappingRepositoryPersist.removeByCenterIdBranchId(centerId,branchId);
	}

	@Override
	public List<Branch> findAllBranch() {
	
		return iBranchRepositoryPersist.findAllBranch();
	}

	@Override
	public void saveServiceCenterMapping(String centerId, String branchId) {
		
		ServiceCenterMapping serviceCenterMapping = new ServiceCenterMapping();
//		Branch branchManytoOne=iBranchRepositoryPersist.findBranchByBranchId(centerId);
//		Branch branchOnetoOne=iBranchRepositoryPersist.findBranchByBranchId(branchId);
		ServiceCenterMappingPK serviceCenterMappingPK =new ServiceCenterMappingPK(centerId, branchId);
		serviceCenterMapping.setId(serviceCenterMappingPK);
//		serviceCenterMapping.setBranch(branchManytoOne);
//		serviceCenterMapping.setOneBranch(branchOnetoOne);
		iServiceCenterMappingRepositoryPersist.saveServiceCenterMapping(serviceCenterMapping);
	}

	@Override
	public void removeByCenterId(String centerId) {
		
		iServiceCenterMappingRepositoryPersist.removeByCenterId(centerId);
		
	}

}
