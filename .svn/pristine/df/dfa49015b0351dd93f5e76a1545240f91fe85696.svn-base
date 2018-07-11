package com.uxb2b.ecp.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.bean.QueryTenderBean;
import com.uxb2b.ecp.business.IQueryTenderBusiness;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.persist.ICtbflbt1RepositoryPersist;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;

@Component
public class QueryTenderBusinessImpl implements IQueryTenderBusiness{
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	@Autowired
	ICtbflbt1RepositoryPersist iCtbflbt1RepositoryPersist;

	@Override
	public List<Enterprise> findAllEnterprise() {
		
		return enterpriseRepository.findAll();
	}

	@Override
	public List<SysCode> findSysCodeByCodeType(String codeType) {
		
		return iSysCodeRepositoryPersist.findByIdCodeType(codeType);
	}

	@Override
	public Page<Ctbflbt1> findTender(QueryTenderBean tenderForm,int pageNumber,int pageSize) throws Exception {
		
		return iCtbflbt1RepositoryPersist.findTender(tenderForm, pageNumber, pageSize);
	}

	@Override
	public Ctbflbt1 findByTenderNo(String tenderNo) {
		
		return iCtbflbt1RepositoryPersist.findByTenderNo(tenderNo);
	}
	

}
