package com.uxb2b.ecp.persist.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import com.uxb2b.ecp.bean.QueryUserAccessRecordFormBean;
import com.uxb2b.ecp.model.UserLog;
import com.uxb2b.ecp.persist.IQueryUserAccessRecordPersist;
import com.uxb2b.ecp.repository.UserLogRepository;
import com.uxb2b.ecp.persist.impl.CustomizeSearch.Operator;

@Repository
@Transactional
public class QueryUserAccessRecordPersistImpl implements IQueryUserAccessRecordPersist{
	
	@Autowired
	UserLogRepository userLogRepository;

	@Override
	public Page<UserLog> findUserLogByCondition(QueryUserAccessRecordFormBean formBean,
			int pageNumber, int pageSize) throws Exception {
		
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
		Specification<UserLog> spec = buildSpecification(formBean);
		return userLogRepository.findAll(spec, pageRequest);
		
	}
	
	private Specification<UserLog> buildSpecification(QueryUserAccessRecordFormBean formBean) throws Exception {
		Map<String, CustomizeSearch> filters = Maps.newIdentityHashMap();
		
		//以下為動態條件增加where查詢
		if (StringUtils.isNotBlank(formBean.getSearchUserId())) {
			filters.put("userId", new CustomizeSearch("userId", Operator.LIKE, formBean.getSearchUserId()));
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		if (StringUtils.isNotBlank(formBean.getSearchLogTimeStartDate()) && StringUtils.isNotBlank(formBean.getSearchLogTimeEndDate())) {
			List<String> logTimeList = new ArrayList<String>();
			logTimeList.add(formBean.getSearchLogTimeStartDate());
			logTimeList.add(formBean.getSearchLogTimeEndDate());
			filters.put("logTime", new CustomizeSearch("logTime", Operator.DATE_BETWEEN, logTimeList));	
		} else if (StringUtils.isNotBlank(formBean.getSearchLogTimeStartDate()) && StringUtils.isBlank(formBean.getSearchLogTimeEndDate())) {
			Date startTime = sdf.parse(formBean.getSearchLogTimeStartDate());
			filters.put("logTime", new CustomizeSearch("logTime", Operator.GTE, startTime));
		} else if (StringUtils.isBlank(formBean.getSearchLogTimeStartDate()) && StringUtils.isNotBlank(formBean.getSearchLogTimeEndDate())) {
			Date endTime = sdf.parse(formBean.getSearchLogTimeEndDate());
			filters.put("logTime", new CustomizeSearch("logTime", Operator.LTE, endTime));
		}
		
		/**
		 * 0為全查
		 * */
		if (StringUtils.isNotBlank(formBean.getSearchFunctionId()) && !StringUtils.equals(formBean.getSearchFunctionId(), "0")) {
			filters.put("functionId", new CustomizeSearch("functionId", Operator.EQ, formBean.getSearchFunctionId()));
		}
		
		Specification<UserLog> spec = CustomizeDynamicSpecifications.generateSpecification(filters.values(), UserLog.class);
		return spec;
	}
	
	private <T> Specification<T> getSpecification(final Predicate predicate) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return predicate;
			}
		};
	}
	
	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		String logTime = "logTime";
		return new PageRequest(pageNumber - 1, pagzSize, new Sort(Direction.DESC, logTime));
	}

	@Override
	public List<UserLog> findUserLogDetail(QueryUserAccessRecordFormBean formBean) throws Exception {
		return userLogRepository.findByLogId(formBean.getLogId());
	}
	

}
