package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryBatchBean;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.SysCode;

/**
 * 本票查詢
 * @author steve
 *
 */
public interface IQueryBatchBusiness {

	/**
	 * 查詢所有公司資料
	 * @return
	 */
	public List<Enterprise> findAllEnterprise();
	
	/**
	 * 查詢 SYS_CODE
	 * @param codeType 代碼類別
	 * @return
	 */
	public List<SysCode> findSysCodeByCodeType(String codeType);
 	
	/**
	 * 查詢本票資料
	 * @param batchForm 查詢條件
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<CpData> findBatch(QueryBatchBean batchForm,int pageNumber,int pageSize)throws Exception;
	
	/**
	 * 查詢CP_DATA
	 * @param batchNo 本票批號
	 * @return
	 */
	public CpData findByBatchNo(String batchNo);
}
