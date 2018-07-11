package com.uxb2b.ecp.persist;

import java.util.List;

import com.uxb2b.ecp.model.ServiceCenterMapping;

/**
 * SERVICE_CENTER_MAPPING TABLE
 * @author steve
 *
 */
public interface IServiceCenterMappingRepositoryPersist {

	/**
	 * SAVE SERVICE_CENTER_MAPPING
	 * @param serviceCenterMapping
	 */
	public void saveServiceCenterMapping(ServiceCenterMapping serviceCenterMapping);
	
	/**
	 * 刪除 SERVICE_CENTER_MAPPING TABLE的資料
	 * 條件:CENTER_ID、BRANCH_ID
	 * 
	 * @param centerId 區域中心代碼
	 * @param branchId 銀行分行金資代碼
	 */ 
	public void removeByCenterIdBranchId(String centerId,String branchId);
	
	/**
	 * 刪除SERVICE_CENTER_MAPPING TABLE的資料
	 * 條件:CENTER_ID
	 * 
	 * @param centerId 區域中心代碼
	 */
	public void removeByCenterId(String centerId);
	
	/**
	 * 查詢SERVICE_CENTER_MAPPING TABLE的資料
	 * 條件:CENTER_ID
	 * 
	 * @param centerId 區域中心代碼
	 * @return
	 */
	public List<ServiceCenterMapping> findByIdCenterId(String centerId);
	
}
