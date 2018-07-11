package com.uxb2b.ecp.persist.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.uxb2b.ecp.bean.QueryQuotesBean;
import com.uxb2b.ecp.bean.QueryTenderBean;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbq2RepositoryPersist;
import com.uxb2b.ecp.persist.impl.CustomizeSearch.Operator;
import com.uxb2b.ecp.repository.Ctbflbq1Repository;
import com.uxb2b.ecp.repository.Ctbflbq2Repository;

@Repository
@Transactional
public class Ctbflbq2RepositoryPersistImpl implements ICtbflbq2RepositoryPersist{

	@Autowired
	Ctbflbq2Repository ctbflbq2Repository;
	
	@Override
	public Page<Ctbflbq2> findCtbflbq2ByQuationNo( int pageNumber, int pageSize ,String quationNo){
		
		
		return ctbflbq2Repository.findByIdQuationNo(buildPageRequest(pageNumber,pageSize),quationNo);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize);
	}

	@Override
	public Ctbflbq2 findByIdQuationNoAndIdItemNo(String quationNo, int itemNo) {
		
		return ctbflbq2Repository.findByIdQuationNoAndIdItemNo(quationNo, itemNo);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveCtbflbq2(List<Ctbflbq2> ctbflbq2) {
		
		ctbflbq2Repository.save(ctbflbq2);
		
	}

	@Override
	public List<Ctbflbq2> findByIdQuationNoOrderByIdItemNo(String quationNo) {
		
		return ctbflbq2Repository.findByIdQuationNoOrderByIdItemNo(quationNo);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void removeCtbflbq2ByQuationNo(String quationNo) {
	
		ctbflbq2Repository.deleteByIdQuationNo(quationNo);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveCtbflbq2(Ctbflbq2 ctbflbq2) {
		ctbflbq2Repository.save(ctbflbq2);
	}

}
