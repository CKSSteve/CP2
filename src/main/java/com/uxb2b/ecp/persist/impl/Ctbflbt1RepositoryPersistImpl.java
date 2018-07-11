package com.uxb2b.ecp.persist.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.uxb2b.ecp.bean.QueryTenderBean;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.persist.ICtbflbt1RepositoryPersist;
import com.uxb2b.ecp.persist.impl.CustomizeSearch.Operator;
import com.uxb2b.ecp.repository.Ctbflbt1Repository;


@Repository
@Transactional
public class Ctbflbt1RepositoryPersistImpl implements ICtbflbt1RepositoryPersist{

	@Autowired
	Ctbflbt1Repository ctbflbt1Repository; 
	
	
	@Override
	public Page<Ctbflbt1> findTender(QueryTenderBean searchBean, int pageNumber, int pageSize) throws Exception {
	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

		Specification<Ctbflbt1> spec = buildSpecification(searchBean);
	
		return ctbflbt1Repository.findAll(spec, pageRequest);
	}
	
	
	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		return new PageRequest(pageNumber - 1, pagzSize);
	}
	
	private Specification<Ctbflbt1> buildSpecification(QueryTenderBean formBean) throws Exception {
		Map<String, CustomizeSearch> filters = Maps.newIdentityHashMap();
		
		
		if (!"0".equals(formBean.getIssuerId())) {
			filters.put("issuerId", new CustomizeSearch("issuerId", Operator.EQ, formBean.getIssuerId()));
		}
		
		if (!"0".equals(formBean.getCurrencyId())) {
			filters.put("currencyId", new CustomizeSearch("currencyId", Operator.EQ, formBean.getCurrencyId()));
		}
		
	
		if (StringUtils.isNotBlank(formBean.getInviteDateStartDate()) && StringUtils.isNotBlank(formBean.getInviteDateEndDate())) {
			List<String> inviteTimeList = new ArrayList<String>();
			inviteTimeList.add(formBean.getInviteDateStartDate());
			inviteTimeList.add(formBean.getInviteDateEndDate());
			filters.put("inviteDate", new CustomizeSearch("inviteDate", Operator.BETWEEN, inviteTimeList));	
		} else if (StringUtils.isNotBlank(formBean.getInviteDateStartDate()) && StringUtils.isBlank(formBean.getInviteDateEndDate())) {
			filters.put("inviteDate", new CustomizeSearch("inviteDate", Operator.GTE, formBean.getInviteDateStartDate()));
		} else if (StringUtils.isBlank(formBean.getInviteDateStartDate()) && StringUtils.isNotBlank(formBean.getInviteDateEndDate())) {
			filters.put("inviteDate", new CustomizeSearch("inviteDate", Operator.LTE, formBean.getInviteDateEndDate()));
		}
		
		if (StringUtils.isNotBlank(formBean.getCloseDateTimeStartDate()) && StringUtils.isNotBlank(formBean.getCloseDateTimeEndDate())) {
			List<String> closeTimeList = new ArrayList<String>();
			closeTimeList.add(formBean.getCloseDateTimeStartDate());
			closeTimeList.add(formBean.getCloseDateTimeEndDate());
			filters.put("closeDate", new CustomizeSearch("closeDate", Operator.BETWEEN, closeTimeList));	
		} else if (StringUtils.isNotBlank(formBean.getCloseDateTimeStartDate()) && StringUtils.isBlank(formBean.getCloseDateTimeEndDate())) {
			filters.put("closeDate", new CustomizeSearch("closeDate", Operator.GTE, formBean.getCloseDateTimeStartDate()));
		} else if (StringUtils.isBlank(formBean.getCloseDateTimeStartDate()) && StringUtils.isNotBlank(formBean.getCloseDateTimeEndDate())) {
			filters.put("closeDate", new CustomizeSearch("closeDate", Operator.LTE, formBean.getCloseDateTimeEndDate()));
		}
		
		Specification<Ctbflbt1> spec = CustomizeDynamicSpecifications.generateSpecification(filters.values(), Ctbflbt1.class);
		return spec;
	}


	@Override
	public Ctbflbt1 findByTenderNo(String tenderNo) {
		
		return ctbflbt1Repository.findByTenderNo(tenderNo);
	}


	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveCtbflbt1(Ctbflbt1 ctbflbt1) {
		
		ctbflbt1Repository.save(ctbflbt1);
	}
	
}
