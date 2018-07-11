package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;
import com.uxb2b.ecp.bean.QueryQuotesBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.model.Branch;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.RejectReason;
import com.uxb2b.ecp.model.SysCode;

/**
 * 報價
 * @author steve
 *
 */
public interface IQuotionBusiness {
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
	 * 查詢報價退回原因
	 * @return
	 */
	public List<RejectReason> findQuotionRejectReason();
 	
	/**
	 * 查詢報價單資料
	 * @param quotesForm 查詢條件
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<Ctbflbq1> findQuotes(QueryQuotesBean quotesForm,int pageNumber,int pageSize)throws Exception;
	
	/**
	 * 查詢報價單明細資料
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
	 * 查詢分行
	 * @param branchId 分行代號
	 * @return
	 */
	public Branch findBranchByBranchId(String branchId);
	
	/**
	 * 查詢待報價登錄資料
	 * 
	 * @param branchId 分行代號
	 * @param roleId 簽核角色代碼
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Ctbflbq1> findQuotionEntry(String branchId,int roleId,int pageNumber, int pageSize);
	
	/**
	 * 查詢報價資料
	 * @param tenderNo 標單號碼
	 * @return
	 */
	public Ctbflbq1 findCtbflbq1ByTenderNo(String tenderNo);
	
	/**
	 * 報價登錄檢查籌資總金額
	 * @param inputquotionMoneyArrays 籌資金額
	 * @param inputDaysArrays 天期
	 * @param ctbflbq1 報價資料
	 * @return
	 */
	public String quotionEntryCheckLoanAmount(String[] inputquotionMoneyArrays,String[] inputDaysArrays, Ctbflbq1 ctbflbq1);
	
	/**
	 * 不報價
	 * @param tenderNo 標單號碼
	 * @param userDetails 使用者資訊
	 * @throws Exception
	 */
	public void doNotQuotion(String tenderNo,UserProfileBean userDetails)throws Exception;
	
	/**
	 * 報價
	 * @param inputquotionMoneyArrays 籌資金額
	 * @param inputAllInArrays 報價利率
	 * @param inputLoanTypeArrays 類別
	 * @param inputDaysArrays 天期
	 * @param tenderNo 標單號碼
	 * @param maxAmount 可承作總金額
	 * @param extraAmount 尚可承作總金額
	 * @param allInRate 額外30天期利率 
	 * @param extraType 額外類別
	 * @param userDetails 使用者資訊
	 * @throws Exception
	 */
	public void doQuotion(String[] inputquotionMoneyArrays,String[] inputAllInArrays, String[] inputLoanTypeArrays, String[] inputDaysArrays,
						  String tenderNo,String maxAmount, String extraAmount,String allInRate,String extraType,UserProfileBean userDetails)throws Exception;
	
	/**
	 * 查詢待報價審核
	 * @param branchId 分行代號
	 * @param roleId 角色代碼
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Ctbflbq1> findQuotionReview(String branchId,int roleId,int pageNumber, int pageSize);
	
	/**
	 * 查詢報價明細資料
	 * ORDERBY 項次序號
	 * 
	 * @param quationNo 報價單號碼
	 * @return
	 */
	public List<Ctbflbq2> findCtbflbq2OrderByIdItemNo(String quationNo);
	
	/**
	 * 退回報價
	 * @param tenderNo 標單號碼
	 * @param reasonForReturnString 退回原因
	 * @param userDetails 使用者資訊
	 */
	public void doReturn(String tenderNo,String reasonForReturnString,UserProfileBean userDetails);
	
	/**
	 * 報價審核
	 * @param tenderNo 標單號碼
	 * @param userDetails 使用者資訊
	 * @throws Exception
	 */
	public void doReview(String tenderNo,UserProfileBean userDetails) throws Exception;
}
