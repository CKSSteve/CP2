package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryUserAccessRecordFormBean;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.model.UserLog;

public interface IQueryUserAccessRecordBusiness {
	
	public List<Function> findFunctionList();

	public Page<UserLog> findUserLogByCondition(QueryUserAccessRecordFormBean formBean, int pageNumber, int pageSize) throws Exception;
	
	public List<UserLog> findUserLogDetail(QueryUserAccessRecordFormBean formBean) throws Exception;
	
}
