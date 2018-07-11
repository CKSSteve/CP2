package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.persist.IBranchRepositoryPersist;
import com.uxb2b.ecp.repository.BranchRepository;

@Repository
@Transactional
public class BranchRepositoryPersistImpl implements IBranchRepositoryPersist{

	@Autowired
	BranchRepository branchRepository;
	
	public Page<Branch> getAllBranch(int pageNumber,int pageSize){
		
		PageRequest pageRequest = buildPageRequest(pageNumber,pageSize);
		
		return branchRepository.findAll(pageRequest);
		
	}
	
	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize, new Sort(Direction.DESC,"branchId"));
	}

	@Override
	public Branch findBranchByBranchId(String branchId) {
		
		return branchRepository.findBranchByBranchId(branchId);
	}

	@Override
	public List<Branch> findBranchNotInServiceCenter(String typeId) {
		// TODO Auto-generated method stub
		return branchRepository.findBranchNotInServiceCenter(typeId);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveBranch(Branch branch) {
	
		branchRepository.save(branch);		
	}

	@Override
	public List<Branch> findAllBranch() {
		// TODO Auto-generated method stub
		return branchRepository.findAll();
	}
	
}
