package com.uxb2b.ecp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.uxb2b.ecp.model.UserLog;

@Repository
public interface UserLogRepository extends PagingAndSortingRepository<UserLog, Integer>, JpaSpecificationExecutor<UserLog> {
	
	public List<UserLog> findByLogId(String logId);
	
}
