package com.uxb2b.ecp.persist;


import com.uxb2b.ecp.model.Ctbflbt2;

/**
 * 
 * CTBFLBT2 TALBE
 * @author steve
 *
 */
public interface ICtbflbt2RepositoryPersist {

	/**
	 * 取得EXPIRED_DATE
	 * 條件:TENDER_NO、DAYS 
	 * 
	 * @param tenderNo 標單號碼
	 * @param days 天期
	 * @return
	 */
	public String findExpiredDateByTenderNoAndDays(String tenderNo,int days);
	
	/**
	 * 查詢
	 * 條件:TENDER_NO、DAYS 
	 * 
	 * @param tenderNo 標單號碼
	 * @param days 天期
	 * @return
	 */
	public Ctbflbt2 findByIdTenderNoAndDays(String tenderNo, int days);
	
	/**
	 * SAVE CTBFLBT2
	 * 
	 * @param ctbflbt2
	 */
	public void saveCtbflbt2(Ctbflbt2 ctbflbt2);
	
}

