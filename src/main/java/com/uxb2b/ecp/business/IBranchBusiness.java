package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;
import com.uxb2b.ecp.model.Branch;


/**
 * 分行管理
 * @author steve
 *
 */
public interface IBranchBusiness {

	/**
	 * 取得所有分行資料
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Branch> getAllBranch(int pageNumber,int pageSize);
	
	/**
	 * 查詢分行資料 
	 * @param branchId 銀行分行金資代碼
	 * @return
	 */
	public Branch findBranchByBranchId(String branchId);
	
	/**
	 * 查詢沒有所屬區域中心的分行
	 * 
	 * @param typeId 分行類型
	 * @return
	 */
	public List<Branch> findBranchNotInServiceCenter(String typeId);
	
	/**
	 * SAVE分行資料
	 * 
	 * @param branch
	 */
	public void saveBranch(Branch branch);
	
	/**
	 * 查詢所有分行資料
	 * 
	 * @return
	 */
	public List<Branch> findAllBranch();
	
	/**
	 * 修改區域中心所屬分行
	 * 
	 * @param centerId 區域中心代碼
	 * @param branchs 銀行分行金資代碼's
	 */
	public void setServiceCenterMapping(String centerId,String branchs);
	
	/**
	 * SAVE SERVICE_CENTER_MAPPING
	 * 
	 * @param centerId 區域中心代碼
	 * @param branchId 銀行分行金資代碼
	 */
	public void saveServiceCenterMapping(String centerId, String branchId);
	
	/**
	 * 刪除區域中心所屬分行資料
	 * 
	 * @param centerId 區域中心代碼
	 * @param branchId 銀行分行金資代碼
	 */
	public void removeByCenterIdBranchId(String centerId,String branchId);
	
	/**
	 * 刪除區域中心所屬分行資料
	 * @param centerId 區域中心代碼
	 */
	public void removeByCenterId(String centerId);
 	
}
