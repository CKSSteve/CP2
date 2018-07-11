package com.uxb2b.ecp.persist;

import java.util.List;

import org.springframework.data.domain.Page;
import com.uxb2b.ecp.model.AwardData;

/**
 * 
 * AWARD_DATA TABLE
 * @author steve
 *
 */
public interface IAwardDataRepositoryPersist {


/**
 * 取得資料筆數
 * 條件:BRANCH_ID、ROLE_ID1、APPROVE_STATUS1
 *  
 * @param branchId 分行代號
 * @param roleId1 本票批號簽核角色代碼
 * @param approveStatusList 本票批號審核狀態
 * @return
 */
public int getCountByRoleId1AndApprove1Status(String branchId, int roleId1, List<String> approveStatusList);

/**
 * 取得資料筆數
 * 
 * @param roleId2
 * @param branchId
 * @param approveStatus2
 * @return
 */
 public int getCountByRoleId2AndBranchIdAndApproveStatus2(int roleId2,String branchId,List<String> approveStatus2);
 
 /**
  * 查詢
  * 條件:BRANCH_ID、ROLE_ID2
  * 
  * @param BranchId 分行代號
  * @param roleId 帳務明細簽核角色代碼
  * @param approveStatus2 帳務明細審核狀態
  * @param pageNumber
  * @param pageSize
  * @return
  */
 public Page<AwardData> findAwardDataByBranchIdAndRoleId2AndApproveStatus2In(String BranchId,int roleId,List<String> approveStatus2,int pageNumber,int pageSize);
 
 /**
  * 查詢
  * 條件:QUATION_NO、ITEM_NO
  * 
  * @param quationNo 報價單號碼
  * @param itemNo 項次序號
  * @return
  */
 public AwardData findAwardDataByQuationNoAndItemNo(String quationNo,int itemNo);
 
 /**
  * SAVE AWARD_DATA TABLE
  * 
  * @param awardData
  */
 public void saveAwardData(AwardData awardData);
 
 /**
  * 查詢
  * 條件:BATCH_NO, not quationNo, not itemNo
  * 
  * @param batchNo 票券批號
  * @return
  */
 public AwardData findAwardDataByBatchNo(String batchNo, String quationNo, int itemNo );
 
 /**
  * 查詢
  * 條件:BATCH_NO
  * 
  * @param batchNo 票券批號
  * @return
  */
 public AwardData findAwardDataByBatchNo(String batchNo);
 
}

