package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryQuotesBean;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.SysCode;

/**
 * 報價單查詢
 * @author steve
 *
 */
public interface IQueryQuotesBusiness {

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
	 * @param quotesForm 查詢條件
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<Ctbflbq1> findQuotes(QueryQuotesBean quotesForm,int pageNumber,int pageSize)throws Exception;
	
	/**
	 * 查詢標單明細
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Ctbflbq2> findQuotesDetails(int pageNumber,int pageSize,String quationNo);
	
	/**
	 * 查詢報價資料
	 * @param quationNo 報價單號碼
	 * @return
	 */
	public Ctbflbq1 findCtbflbq1ByQuationNo(String quationNo);
	
	/**
	 * 查詢報價明細資料
	 * @param quationNo 報價單號碼
	 * @param itemNo 項次序號
	 * @return
	 */
	public Ctbflbq2 findCtbflbq2ByQuationNoAndItemNo(String quationNo,int itemNo);
	
	/**
	 * 查詢分行資料
	 * @param branchId 分行資料
	 * @return
	 */
	public Branch findBranchByBranchId(String branchId);
}
