package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.persist.IBranchRepositoryPersist;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.BranchRepository;
import com.uxb2b.ecp.repository.SysCodeRepository;

@Repository
@Transactional
public class SysCodeRepositoryPersistImpl implements ISysCodeRepositoryPersist{
	
	@Autowired
	SysCodeRepository sysCodeRepository;

	@Override
	public List<SysCode> findByIdCodeType(String codeType) {
		return sysCodeRepository.findByIdCodeTypeOrderByIdCodeKeyAsc(codeType);
	}

	@Override
	public String findCodeValueByCodeTypeCodeKey(String codeType, String codeKey) {
		
		return sysCodeRepository.findByIdCodeTypeAndIdCodeKey(codeType, codeKey).getCodeValue();
	}

	
}
