package com.uxb2b.ecp.persist.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.uxb2b.ecp.bean.QueryQuotesBean;
import com.uxb2b.ecp.bean.QueryTenderBean;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.persist.impl.CustomizeSearch.Operator;
import com.uxb2b.ecp.repository.Ctbflbq1Repository;

@Repository
@Transactional
public class Ctbflbq1RepositoryPersistImpl implements ICtbflbq1RepositoryPersist{

	@Autowired
	Ctbflbq1Repository ctbflbq1Repository;
	
	
	@Override
	public int getCountByRoleIdAndbranchIdAndApproveStatus(int roleId,String branchId,List<String> AppproveStatus) {
		
		return ctbflbq1Repository.countByRoleIdAndBranchIdAndApproveStatusIn(roleId,branchId ,AppproveStatus);
	}
	
	@Override
	public Page<Ctbflbq1> findQuotes(QueryQuotesBean quotesForm, int pageNumber, int pageSize) throws Exception {

		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

		Specification<Ctbflbq1> spec = buildSpecification(quotesForm);
	
		return ctbflbq1Repository.findAll(spec, pageRequest);
		
	}

	
	
	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize, new Sort(Direction.ASC,"tenderNo","quationTime"));
	}
	
	private Specification<Ctbflbq1> buildSpecification(QueryQuotesBean formBean) throws Exception {
		Map<String, CustomizeSearch> filters = Maps.newIdentityHashMap();
		
		
		if (!"0".equals(formBean.getIssuerId())) {
			filters.put("issuerId", new CustomizeSearch("issuerId", Operator.EQ, formBean.getIssuerId()));
		}
		
		if (!"0".equals(formBean.getCurrencyId())) {
			filters.put("currencyId", new CustomizeSearch("currencyId", Operator.EQ, formBean.getCurrencyId()));
		}
		
		if(formBean.getApproveStatusTypeArray()!=null){
			if(formBean.getApproveStatusTypeArray().size()>0){
				filters.put("approveStatus", new CustomizeSearch("approveStatus", Operator.IN, formBean.getApproveStatusTypeArray()));
			}
		}
		
		if(formBean.getStatusTypeArray()!=null){
			if(formBean.getStatusTypeArray().size()>0){
				filters.put("status", new CustomizeSearch("status", Operator.IN, formBean.getStatusTypeArray()));
			}
		}
	
		if (StringUtils.isNotBlank(formBean.getQuotesDateStartDate()) && StringUtils.isNotBlank(formBean.getQuotesDateEndDate())) {
			List<String> quotesDateList = new ArrayList<String>();
			quotesDateList.add(formBean.getQuotesDateStartDate());
			quotesDateList.add(formBean.getQuotesDateEndDate());
			filters.put("quationTime", new CustomizeSearch("quationTime", Operator.DATE_BETWEEN, quotesDateList));	
		} else if (StringUtils.isNotBlank(formBean.getQuotesDateStartDate()) && StringUtils.isBlank(formBean.getQuotesDateEndDate())) {
			filters.put("quationTime", new CustomizeSearch("quationTime", Operator.GTE, formBean.getQuotesDateStartDate()));
		} else if (StringUtils.isBlank(formBean.getQuotesDateStartDate()) && StringUtils.isNotBlank(formBean.getQuotesDateEndDate())) {
			filters.put("quationTime", new CustomizeSearch("quationTime", Operator.LTE, formBean.getQuotesDateEndDate()));
		}
		
		Specification<Ctbflbq1> spec = CustomizeDynamicSpecifications.generateSpecification(filters.values(), Ctbflbq1.class);
		return spec;
	}

	@Override
	public Ctbflbq1 findByQuationNo(String quationNo) {
		
		return ctbflbq1Repository.findByQuationNo(quationNo);
	}

	@Override
	public Page<Ctbflbq1> findByBranchIdAndRoleIdAndApproveStatus(String branchId, int roleId,List<String> approveStatus,int pageNumber, int pageSize) {
		
		Pageable pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.ASC,"tenderNo"));
		
		return ctbflbq1Repository.findByBranchIdAndRoleIdAndApproveStatusIn(branchId, roleId, approveStatus,pageRequest);
	}

	@Override
	public Ctbflbq1 findCtbflbq1ByTenderNo(String tenderNo) {
		
		return ctbflbq1Repository.findCtbflbq1ByTenderNo(tenderNo);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveCtbflbq1(Ctbflbq1 ctbflbq1) {
		
		ctbflbq1Repository.save(ctbflbq1);
	}

	@Override
	public List<Ctbflbq1> findCpDataByIssuerAndStatus(String issuer,List<String> status) {
		
		return ctbflbq1Repository.findByIssuerIdAndStatusIn(issuer,status);
	}


	
}
