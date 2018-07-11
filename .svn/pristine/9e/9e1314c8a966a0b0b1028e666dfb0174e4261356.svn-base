package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.persist.IPassRecordRepositoryPersist;
import com.uxb2b.ecp.repository.PassRecordRepository;



@Repository
@Transactional
public class PassRecordRepositoryPersistImpl implements IPassRecordRepositoryPersist{

	@Autowired
	PassRecordRepository passRecordRepository;
	
	@Override
	public int getCountByStatus(String status) {
		
		return passRecordRepository.countByStatus(status);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void savePassRecord(PassRecord passRecord) {
		passRecordRepository.save(passRecord);
	}

	@Override
	public List<PassRecord> findPassRecordByStatus(String status) {
		
		return passRecordRepository.findByStatus(status);
		
	}

	@Override
	public Page<PassRecord> findPassRecordByStatus(List<String> status,int pageNumber, int pageSize) {
		
		Pageable pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.ASC,"recordId"));
		
		return passRecordRepository.findByStatusIn(status,pageRequest);
	}

	@Override
	public List<PassRecord> findPassRecordByRecordId(List<Long> passRecordList) {
		return passRecordRepository.findByRecordIdIn(passRecordList);
	}

	@Override
	public void updateStatusByRecordId(String status, List<Long> recordIdlist) {
		
		passRecordRepository.updateStatusByRecordIdIn(status, recordIdlist);
		
	}

	
}
