package com.uxb2b.ecp.persist;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.bean.QueryQuotesBean;
import com.uxb2b.ecp.model.Ctbflbq1;

/**
 * 
 * CTBFLBQ1 TABLE
 * @author steve
 *
 */
public interface ICtbflbq1RepositoryPersist {

/**
 * 取的資料筆數
 * 條件:ROLE_ID、BRANCH_ID、APPROVE_STATUS
 * 
 * @param roleId 簽核角色代碼
 * @param branchId 分行代號
 * @param AppproveStatus 報價審核狀態
 * @return
 */
 public int getCountByRoleIdAndbranchIdAndApproveStatus(int roleId,String branchId,List<String> AppproveStatus);
 
 /**
  * 查詢報價單資料
  * 
  * @param quotesForm 查詢條件
  * @param pageNumber
  * @param pageSize
  * @return
  * @throws Exception
  */
 public Page<Ctbflbq1> findQuotes(QueryQuotesBean quotesForm,int pageNumber,int pageSize) throws Exception;

 /**
  * 查詢
  * 條件:QUATION_NO
  * 
  * @param quationNo 報價單號碼
  * @return
  */
 public Ctbflbq1 findByQuationNo(String quationNo);
 
 /**
  * 查詢
  * 條件:BRANCH_ID、ROLE_ID、APPROVE_STATUS
  * 
  * @param branchId 分行代號
  * @param roleId 簽核角色代碼
  * @param approveStatus 報價審核狀態
  * @param pageNumber
  * @param pageSize
  * @return
  */
 public Page<Ctbflbq1> findByBranchIdAndRoleIdAndApproveStatus(String branchId,int roleId,List<String> approveStatus,int pageNumber, int pageSize);
 
 /**
  * 查詢 
  * 條件:TENDER_NO
  * 
  * @param tenderNo 標單號碼
  * @return
  */
 public Ctbflbq1 findCtbflbq1ByTenderNo(String tenderNo);
 
 /**
  * SAVE CTBFLBQ1
  * 
  * @param ctbflbq1
  */
 public void saveCtbflbq1(Ctbflbq1 ctbflbq1);
 
 /**
  * 查詢
  * 條件:ISSUER_ID、STATUS
  * 
  * @param issuer 發票人統編
  * @param status 報價單狀態
  * @return
  */
 public List<Ctbflbq1> findCpDataByIssuerAndStatus(String issuer,List<String> status);
}

