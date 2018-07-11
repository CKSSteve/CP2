package com.uxb2b.ecp.persist;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.model.PassRecord;

/**
 * PASS_RECORD TABLE
 * @author steve
 *
 */
public interface IPassRecordRepositoryPersist {

	/**
	 * 取得PASS_RECORD 資料筆數
	 * 條件:STATUS
	 * 
	 * @param status 傳送狀態
	 * @return
	 */
	public int getCountByStatus(String status);

	/**
	 * SAVE PASS_RECORD
	 * 
	 * @param passRecord
	 */
	public void savePassRecord(PassRecord passRecord);
	
	/**
	 * 取得PASS_RECORD
	 * @param status 傳送狀態
	 * @return
	 */
	public List<PassRecord> findPassRecordByStatus(String status);

	/**
	 * 取得PASS_RECORD
	 * @param status
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	
	public Page<PassRecord> findPassRecordByStatus(List<String> status,int pageNumber, int pageSize);
	
	/**
	 * 取得PASS_RECORD
	 * @param passRecordList
	 * @return
	 */
	public List<PassRecord> findPassRecordByRecordId(List<Long> passRecordList);
	
	/**
	 * UPDATE PASS_RECORD STATUS
	 * @param status
	 * @param recordIdlist
	 */
	public void updateStatusByRecordId(String status,List<Long> recordIdlist);
} 
