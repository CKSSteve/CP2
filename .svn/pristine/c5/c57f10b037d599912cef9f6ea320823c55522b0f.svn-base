package com.uxb2b.ecp.persist;


import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryTenderBean;
import com.uxb2b.ecp.model.Ctbflbt1;


/**
 * CTBFLBT1 TABLE
 * @author steve
 *
 */
public interface ICtbflbt1RepositoryPersist {

	/**
	 * 查詢標單資料
	 * 
	 * @param searchBean 查詢條件
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<Ctbflbt1> findTender(QueryTenderBean searchBean, int pageNumber,int pageSize)throws Exception;
	
	/**
	 * 查詢
	 * 條件:TENDER_NO
	 * 
	 * @param tenderNo
	 * @return
	 */
	public Ctbflbt1 findByTenderNo(String tenderNo); 
	
	/**
	 * SAVE CTBFLBT1
	 * 
	 * @param ctbflbt1
	 */
	public void saveCtbflbt1(Ctbflbt1 ctbflbt1);
	
}

