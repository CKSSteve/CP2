package com.uxb2b.ecp.persist;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryUserAccessRecordFormBean;
import com.uxb2b.ecp.model.UserLog;

public interface IQueryUserAccessRecordPersist {
	
	public Page<UserLog> findUserLogByCondition(QueryUserAccessRecordFormBean formBean, int pageNumber, int pageSize) throws Exception;
	
	public List<UserLog> findUserLogDetail(QueryUserAccessRecordFormBean formBean) throws Exception;

}
