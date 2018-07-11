package com.uxb2b.ecp.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.bean.QueryBatchBean;
import com.uxb2b.ecp.bean.QueryTenderBean;
import com.uxb2b.ecp.business.IQueryBatchBusiness;
import com.uxb2b.ecp.business.IQueryTenderBusiness;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.SysCode;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbt1RepositoryPersist;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.EnterpriseRepository;

@Component
public class QueryBatchBusinessImpl implements IQueryBatchBusiness{
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	@Autowired
	ICpDataRepositoryPersist iCpDataRepositoryPersist;

	@Override
	public List<Enterprise> findAllEnterprise() {
		
		return enterpriseRepository.findAll();
	}

	@Override
	public List<SysCode> findSysCodeByCodeType(String codeType) {
		
		return iSysCodeRepositoryPersist.findByIdCodeType(codeType);
	}

	@Override
	public Page<CpData> findBatch(QueryBatchBean batchForm, int pageNumber, int pageSize) throws Exception {
		
		return iCpDataRepositoryPersist.findBatch(batchForm, pageNumber, pageSize);
	}

	@Override
	public CpData findByBatchNo(String batchNo) {
		
		return iCpDataRepositoryPersist.findCpDataByBatchNo(batchNo);
	}

}
