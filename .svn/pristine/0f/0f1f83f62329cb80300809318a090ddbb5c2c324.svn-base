package com.uxb2b.ecp.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uxb2b.ecp.bean.TodoBean;
import com.uxb2b.ecp.business.ITodoBusiness;
import com.uxb2b.ecp.persist.ICpDataRepositoryPersist;
import com.uxb2b.ecp.persist.ICtbflbq1RepositoryPersist;
import com.uxb2b.ecp.persist.IAwardDataRepositoryPersist;
import com.uxb2b.ecp.persist.IPassRecordRepositoryPersist;

@Component
public class TodoBusinessImpl implements ITodoBusiness{

	@Autowired
	ICtbflbq1RepositoryPersist iCtbflbq1RepositoryPersist;
	
	@Autowired
	IAwardDataRepositoryPersist iAwardDataRepositoryPersist;
	
	@Autowired
	ICpDataRepositoryPersist iCpDataRepositoryPersist;
	
	@Autowired
	IPassRecordRepositoryPersist iPassRecordRepositoryPersist;
	
	@Override
	public List<TodoBean> getTodoList(int roleId,String branchId) {
		
		List<TodoBean> todoBeanList = new ArrayList<>();
		int count;
		
		//報價登錄
		List<String> ctbflbq1ApproveStatus = Arrays.asList("0","1");
		count=iCtbflbq1RepositoryPersist.getCountByRoleIdAndbranchIdAndApproveStatus(roleId,branchId, ctbflbq1ApproveStatus);
		if(count>0)
			todoBeanList.add(new TodoBean("quotion/quotionEntryInventory", "報價登錄", count));
	
		//票券批號登錄
		List<String> awardDataApproveStatus = Arrays.asList("0","1");
		count=iAwardDataRepositoryPersist.getCountByRoleId1AndApprove1Status(branchId, roleId, awardDataApproveStatus);
		if(count>0)
			todoBeanList.add(new TodoBean("awardDataRegister", "票券批號登錄", count));
		
		//本票發行確認
//		count=iCpDataRepositoryPersist.getCountByRoleIdAndApproveStatus(roleId, "0");
//		if(count>0)
//		todoBeanList.add(new TodoBean("#", "本票發行確認", count));
		
		//本票帳務明細登錄
		List<String> awardDataApproveStatus2 = Arrays.asList("0","1");
		count=iAwardDataRepositoryPersist.getCountByRoleId2AndBranchIdAndApproveStatus2(roleId,branchId, awardDataApproveStatus2);
		if(count>0)
			todoBeanList.add(new TodoBean("rateDetails/rateDetailsRegisterInventory", "本票帳務明細登錄", count));
		
		//報價審核
		ctbflbq1ApproveStatus = Arrays.asList("2","3");
		count=iCtbflbq1RepositoryPersist.getCountByRoleIdAndbranchIdAndApproveStatus(roleId, branchId ,ctbflbq1ApproveStatus);
		if(count>0)
			todoBeanList.add(new TodoBean("quotion/quotionReviewInventory", "報價審核", count));

		//票券批號登錄審核
		awardDataApproveStatus = Arrays.asList("2","3");
		count=iAwardDataRepositoryPersist.getCountByRoleId1AndApprove1Status(branchId, roleId, awardDataApproveStatus);
		if(count>0)
			todoBeanList.add(new TodoBean("awardDataVerify", "票券批號登錄審核", count));
		
		//審核票券批號登錄
//		count=iCpDataRepositoryPersist.getCountByRoleIdAndApproveStatus(roleId, "3");
//		if(count>0)
//		todoBeanList.add(new TodoBean("#", "審核本票發行確認", count));
		
		//審核本票帳務明細登錄
		awardDataApproveStatus2= Arrays.asList("2","3");
		count=iAwardDataRepositoryPersist.getCountByRoleId2AndBranchIdAndApproveStatus2(roleId,branchId ,awardDataApproveStatus2);
		if(count>0)
			todoBeanList.add(new TodoBean("rateDetails/rateDetailsReviewInventory", "本票帳務明細審核", count));
			
		//重新票券批號登錄

//		count=iAwardDataRepositoryPersist.getCountByRoleId1AndApprove1Status(roleId, "1");
//		if(count>0)
//			todoBeanList.add(new TodoBean("#", "重新票券批號登錄", count));

				
		//重新本票發行確認
//		count=iCpDataRepositoryPersist.getCountByRoleIdAndApproveStatus(roleId, "1");
//		if(count>0)
//		todoBeanList.add(new TodoBean("#", "重新本票發行確認", count));
				
		//重新本票帳務明細登錄
//		count=iAwardDataRepositoryPersist.getCountByRoleId2AndApproveStatus2(roleId, "1");
//		if(count>0)
//			todoBeanList.add(new TodoBean("#", "重新本票帳務明細登錄", count));		
		
		//重新傳送資料
//		count=iPassRecordRepositoryPersist.getCountByStatus(2);
//		if(count>0)
//			todoBeanList.add(new TodoBean("#", "重新傳送資料", count));
		
		return todoBeanList;
	}

	
}
