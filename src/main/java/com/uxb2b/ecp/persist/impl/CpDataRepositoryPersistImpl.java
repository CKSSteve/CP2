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
import com.uxb2b.ecp.bean.QueryBatchBean;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.persist.impl.CustomizeSearch.Operator;
import com.uxb2b.ecp.repository.CpDataRepository;



@Repository
@Transactional
public class CpDataRepositoryPersistImpl implements ICpDataRepositoryPersist{

	@Autowired
	CpDataRepository cpDataRepository;
	
	@Override
	public int getCountByRoleIdAndApproveStatus(int roleId, String approveStatus) {
		
		return cpDataRepository.countByRoleIdAndApproveStatus(roleId, approveStatus);
	}

	@Override
	public Page<CpData> findBatch(QueryBatchBean batchForm, int pageNumber, int pageSize) throws Exception {
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.ASC,"issuerId","effectDate"));

		Specification<CpData> spec = buildSpecification(batchForm);
	
		return cpDataRepository.findAll(spec, pageRequest);
		
	}
	
	private Specification<CpData> buildSpecification(QueryBatchBean formBean) throws Exception {
		Map<String, CustomizeSearch> filters = Maps.newIdentityHashMap();
		
		
		if (!"0".equals(formBean.getIssuerId())) {
			filters.put("issuerId", new CustomizeSearch("issuerId", Operator.EQ, formBean.getIssuerId()));
		}
		
		if (!"0".equals(formBean.getCurrencyId())) {
			filters.put("currencyId", new CustomizeSearch("currencyId", Operator.EQ, formBean.getCurrencyId()));
		}
		
		if (StringUtils.isNotBlank(formBean.getBatchNo())) {
			filters.put("batchNo", new CustomizeSearch("batchNo", Operator.EQ, formBean.getBatchNo()));
		}
		
		if (StringUtils.isNotBlank(formBean.getEffectDateStartDate()) && StringUtils.isNotBlank(formBean.getEffectDateEndDate())) {
			List<String> effectDateList = new ArrayList<String>();
			effectDateList.add(formBean.getEffectDateStartDate());
			effectDateList.add(formBean.getEffectDateEndDate());
			filters.put("effectDate", new CustomizeSearch("effectDate", Operator.BETWEEN, effectDateList));	
		} else if (StringUtils.isNotBlank(formBean.getEffectDateStartDate()) && StringUtils.isBlank(formBean.getEffectDateEndDate())) {
			filters.put("effectDate", new CustomizeSearch("effectDate", Operator.GTE, formBean.getEffectDateStartDate()));
		} else if (StringUtils.isBlank(formBean.getEffectDateStartDate()) && StringUtils.isNotBlank(formBean.getEffectDateEndDate())) {
			filters.put("effectDate", new CustomizeSearch("effectDate", Operator.LTE, formBean.getEffectDateEndDate()));
		}
		
		if (StringUtils.isNotBlank(formBean.getExpiredDateStartDate()) && StringUtils.isNotBlank(formBean.getExpiredDateEndDate())) {
			List<String> expiredDateList = new ArrayList<String>();
			expiredDateList.add(formBean.getExpiredDateStartDate());
			expiredDateList.add(formBean.getExpiredDateEndDate());
			filters.put("expiredDate", new CustomizeSearch("expiredDate", Operator.BETWEEN, expiredDateList));	
		} else if (StringUtils.isNotBlank(formBean.getExpiredDateStartDate()) && StringUtils.isBlank(formBean.getExpiredDateEndDate())) {
			filters.put("expiredDate", new CustomizeSearch("expiredDate", Operator.GTE, formBean.getExpiredDateStartDate()));
		} else if (StringUtils.isBlank(formBean.getExpiredDateStartDate()) && StringUtils.isNotBlank(formBean.getExpiredDateEndDate())) {
			filters.put("expiredDate", new CustomizeSearch("expiredDate", Operator.LTE, formBean.getExpiredDateEndDate()));
		}
		
		Specification<CpData> spec = CustomizeDynamicSpecifications.generateSpecification(filters.values(), CpData.class);
		return spec;
	}

	@Override
	public CpData findCpDataByBatchNo(String batchNo) {
		
		return cpDataRepository.findByBatchNo(batchNo);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void deleteCpData(CpData cpData) {

		cpDataRepository.delete(cpData);
			
	}

	@Override
	public void savaCpData(CpData cpData) {
		
		cpDataRepository.save(cpData);
	}

	@Override
	public List<CpData> findAllCpData() {

		return cpDataRepository.findAll();
	}

	@Override
	public List<CpData> findCpDataByStatusNot(List<Integer> status) {
		
		return cpDataRepository.findByStatusNotIn(status);
	}
	
}
