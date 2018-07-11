package com.uxb2b.ecp.persist;

import com.uxb2b.ecp.model.Enterprise;

/**
 * ENTERPRISE TABLE
 * @author steve
 *
 */
public interface IEnterpriseRepositoryPersist {
	
	/**
	 * 查詢 ENTERPRISE TABLE
	 * 
	 * @param uni 統編
	 * @return
	 */
	public Enterprise findByUni(String uni);
	
}

