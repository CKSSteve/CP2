package com.uxb2b.ecp.persist;

import java.util.List;

import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Ctbflbq2;

/**
 * BEAN 轉 JSON
 * 
 * @author steve
 *
 */
public interface IJsonMessagePersist {
	
	/**
	 * 生成T02電文
	 * 
	 * @param ctbflbq1
	 * @param ctbflbq2
	 * @param userDetails 登錄者資料
	 * @return
	 */
	public String makeT02Message(Ctbflbq1 ctbflbq1,List<Ctbflbq2> ctbflbq2,UserProfileBean userDetails);
	
	/**
	 * 生成T03電文
	 * 
	 * @param awardData
	 * @param userDetails 登錄者資料
	 * @param action 操作功能的方法
	 * @return
	 */
	public String makeT03Message(AwardData awardData, UserProfileBean userDetails, String action);

	
	/**
	 * 生成T05電文
	 * 
	 * @param awardData
	 * @param userDetails 登錄者資料
	 * @return
	 */
	public String makeT05Message(AwardData awardData,UserProfileBean userDetails);
}

