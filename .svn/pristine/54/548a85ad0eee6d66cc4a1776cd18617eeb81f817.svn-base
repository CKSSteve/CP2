package com.uxb2b.ecp.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.uxb2b.ecp.model.PassRecord;

public interface IResendSwiftBusiness {

	public Page<PassRecord> getNeedResendSwift(int pageNumber,int pageSize);
	
	public void resendSwiftThread(List<Long> passRecordIdList);
	
	public void resendSwiftMain(List<PassRecord> passRecordList);

}