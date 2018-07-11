package com.uxb2b.ecp.persist.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.persist.IAwardDataRepositoryPersist;
import com.uxb2b.ecp.repository.AwardDataRepository;


@Repository
@Transactional
public class AwardDataRepositoryPersistImpl implements IAwardDataRepositoryPersist{

	@Autowired
	AwardDataRepository awardDataRepository;
	
	@Override

	public int getCountByRoleId2AndBranchIdAndApproveStatus2(int roleId2,String branchId ,List<String> approveStatus2) {
		return awardDataRepository.countByRoleId2AndBranchIdAndApproveStatus2In(roleId2,branchId ,approveStatus2);
	}
	
	@Override
	public int getCountByRoleId1AndApprove1Status(String branchId, int roleId1, List<String> approveStatusList) {
		return awardDataRepository.countByBranchIdAndRoleId1AndApproveStatus1In(branchId, roleId1, approveStatusList);
	}

	@Override
	public Page<AwardData> findAwardDataByBranchIdAndRoleId2AndApproveStatus2In(String BranchId, int roleId,
			List<String> approveStatus2,int pageNumber,int pageSize) {
		
		Pageable pageRequest = new PageRequest(pageNumber - 1, pageSize);
					
		return awardDataRepository.findByBranchIdAndRoleId2AndApproveStatus2In(BranchId, roleId, approveStatus2,pageRequest);
	}

	@Override
	public AwardData findAwardDataByQuationNoAndItemNo(String quationNo, int itemNo) {
		
		return awardDataRepository.findByIdQuationNoAndIdItemNo(quationNo, itemNo);
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public void saveAwardData(AwardData awardData) {
		
		awardDataRepository.save(awardData);
		
	}

	@Override
	public AwardData findAwardDataByBatchNo(String batchNo, String quationNo, int itemNo ) {
		
		AwardData awardData = awardDataRepository.findByBatchNo(batchNo);
		if(awardData != null){
			if(awardData.getId().getQuationNo().equals(quationNo) 
						&& awardData.getId().getItemNo() == itemNo){
				return null;
			}else{
				return awardData;
			}
		}else{
			return null;
		}
		
	}

	@Override
	public AwardData findAwardDataByBatchNo(String batchNo) {
		
		return awardDataRepository.findByBatchNo(batchNo);
		
	}
	
}
