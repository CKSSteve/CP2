package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.ServiceCenterMapping;
import com.uxb2b.ecp.persist.IServiceCenterMappingRepositoryPersist;
import com.uxb2b.ecp.repository.ServiceCenterMappingRepository;

@Repository
@Transactional
public class ServiceCenterMappingRepositoryPersistImpl implements IServiceCenterMappingRepositoryPersist{

	@Autowired
	ServiceCenterMappingRepository serviceCenterMappingRepository;

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveServiceCenterMapping(ServiceCenterMapping serviceCenterMappingList) {
		
		serviceCenterMappingRepository.save(serviceCenterMappingList);

	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void removeByCenterIdBranchId(String centerId,String branchId){
		
		serviceCenterMappingRepository.deleteByCenterIdBranchId(centerId,branchId);

	}

	@Override
	public List<ServiceCenterMapping> findByIdCenterId(String centerId) {
		
		return serviceCenterMappingRepository.findByIdCenterId(centerId);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void removeByCenterId(String centerId) {
		
		serviceCenterMappingRepository.deleteByCenterIdB(centerId);
		
	}

}
