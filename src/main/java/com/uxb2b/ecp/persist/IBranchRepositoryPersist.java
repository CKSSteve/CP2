package com.uxb2b.ecp.persist;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.model.Branch;

/**
 * BRANCH TABLE
 * 
 * @author steve
 *
 */
public interface IBranchRepositoryPersist {

	/**
	 * 查詢 BRANCH TABLE 所有資料
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Branch> getAllBranch(int pageNumber,int pageSize);
	
	/**
	 * 查詢
	 * 
	 * @param branchId 銀行分行金資代碼
	 * @return
	 */
	public Branch findBranchByBranchId(String branchId);
	
	/**
	 * 查詢
	 * 條件:TYPE_ID、NOT IN SERVICE_CENTER_MAPPING
	 * 
	 * @param typeId 分行類型
	 * @return
	 */
	public List<Branch> findBranchNotInServiceCenter(String typeId);
	
	/**
	 * SAVE BRANCH TABLE
	 * 
	 * @param branch
	 */
	public void saveBranch(Branch branch);
	
	/**
	 * 查詢 BRANCH TABLE 所有資料
	 *
	 * @return
	 */
	public List<Branch> findAllBranch();
}

