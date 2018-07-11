package com.uxb2b.ecp.persist;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryBatchBean;
import com.uxb2b.ecp.model.CpData;

/**
 * CP_DATA TABLE
 * 
 * @author steve
 *
 */
public interface ICpDataRepositoryPersist {

	/**
	 * 取得資料筆數
	 * 條件:ROLE_ID、APPROVE_STATUS
	 * 
	 * @param roleId 簽核角色代碼
	 * @param approveStatus 審核狀態
	 * @return
	 */
 public int getCountByRoleIdAndApproveStatus(int roleId,String approveStatus);

 /**
  * 本票資料查詢
  * 
  * @param batchForm 查詢條件
  * @param pageNumber
  * @param pageSize
  * @return
  * @throws Exception
  */
 public Page<CpData> findBatch(QueryBatchBean batchForm, int pageNumber, int pageSize) throws Exception;
 
 /**
  * 查詢
  * 條件:BATCH_NO
  * 
  * @param batchNo 本票批號
  * @return
  */
 public CpData findCpDataByBatchNo(String batchNo);
 
 /**
  * DELETE CP_DATA
  * 
  * @param cpData
  */
 public void deleteCpData(CpData cpData);
 
 /**
  * SAVE CP_DATA
  * 
  * @param cpData
  */
 public void savaCpData(CpData cpData);
 
 /**
  * 查詢 CP_DATA 所有資料
  * 
  * @return
  */
 public List<CpData> findAllCpData();
 
 /**
  * 查詢
  * 條件:NOT STATUS
  * 
  * @param status
  * @return 狀態
  */
 public List<CpData> findCpDataByStatusNot(List<Integer> status);
}

