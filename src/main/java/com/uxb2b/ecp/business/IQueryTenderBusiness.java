package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryTenderBean;
import com.uxb2b.ecp.model.Ctbflbt1;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.SysCode;

/**
 * 標單查詢
 * @author steve
 *
 */
public interface IQueryTenderBusiness {

	/**
	 * 查詢所有公司資料
	 * @return
	 */
	public List<Enterprise> findAllEnterprise();
	
	/**
	 * 查詢SYSCODE
	 * @param codeType 代碼類別
	 * @return
	 */
	public List<SysCode> findSysCodeByCodeType(String codeType);
 	
	/**
	 * 查詢標單資料
	 * @param tenderForm 查詢條件
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<Ctbflbt1> findTender(QueryTenderBean tenderForm,int pageNumber,int pageSize)throws Exception;
	
	/**
	 * 查詢標單資料
	 * @param tenderNo 標單號碼
	 * @return
	 */
	public Ctbflbt1 findByTenderNo(String tenderNo);
}
