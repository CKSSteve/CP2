package com.uxb2b.ecp.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.bean.QueryUserAccessRecordFormBean;
import com.uxb2b.ecp.business.IQueryUserAccessRecordBusiness;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.UserLog;
import com.uxb2b.ecp.persist.IQueryUserAccessRecordPersist;
import com.uxb2b.ecp.repository.FunctionRepository;


@Component
public class QueryUserAccessRecordBusinessImpl implements IQueryUserAccessRecordBusiness {
	
	@Autowired
	private FunctionRepository functionRepository;
	
	@Autowired
	private IQueryUserAccessRecordPersist iQueryUserAccessRecordPersist;

	@Override
	public List<Function> findFunctionList() {
		return functionRepository.findAll();
	}

	@Override
	public Page<UserLog> findUserLogByCondition(QueryUserAccessRecordFormBean formBean, int pageNumber, int pageSize)
			throws Exception {

		return iQueryUserAccessRecordPersist.findUserLogByCondition(formBean, pageNumber, pageSize);

	}

	@Override
	public List<UserLog> findUserLogDetail(QueryUserAccessRecordFormBean formBean) throws Exception {
		return iQueryUserAccessRecordPersist.findUserLogDetail(formBean);
	}

}
