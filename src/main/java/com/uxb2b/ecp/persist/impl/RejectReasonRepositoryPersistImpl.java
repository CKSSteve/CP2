package com.uxb2b.ecp.persist.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.model.RejectReason;
import com.uxb2b.ecp.persist.IFlowStepRepositoryPersist;
import com.uxb2b.ecp.persist.IRejectReasonRepositoryPersist;
import com.uxb2b.ecp.repository.FlowStepRepository;
import com.uxb2b.ecp.repository.RejectReasonRepository;


@Repository
@Transactional
public class RejectReasonRepositoryPersistImpl implements IRejectReasonRepositoryPersist{

	@Autowired
	RejectReasonRepository rejectReasonRepository;

	@Override
	public RejectReason findByIdCodeTypeAndIdCodeKey(String codeType, String codeKey) {
		return rejectReasonRepository.findByIdCodeTypeAndIdCodeKey(codeType, codeKey);
	}

	@Override
	public String getMaxCodeKeyByCodeType(String codeType) {
		return rejectReasonRepository.getMaxCodeKeyByCodeType(codeType);
	}
	
	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveRejectReason(RejectReason rejectReason) {
		rejectReasonRepository.save(rejectReason);
	}
	
	
	
	
}
