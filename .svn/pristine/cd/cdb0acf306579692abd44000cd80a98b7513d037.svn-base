package com.uxb2b.ecp.persist.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.Ctbflbt2;
import com.uxb2b.ecp.persist.ICtbflbt2RepositoryPersist;
import com.uxb2b.ecp.repository.Ctbflbt2Repository;


@Repository
@Transactional
public class Ctbflbt2RepositoryPersistImpl implements ICtbflbt2RepositoryPersist{

	@Autowired
	Ctbflbt2Repository ctbflbt2Repository;

	@Override
	public String findExpiredDateByTenderNoAndDays(String tenderNo, int days) {
		return ctbflbt2Repository.findByIdTenderNoAndDays(tenderNo, days).getExpiredDate();
	}

	@Override
	public Ctbflbt2 findByIdTenderNoAndDays(String tenderNo, int days) {
		return ctbflbt2Repository.findByIdTenderNoAndDays(tenderNo, days);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveCtbflbt2(Ctbflbt2 ctbflbt2) {
		ctbflbt2Repository.save(ctbflbt2);
	}
	
}
