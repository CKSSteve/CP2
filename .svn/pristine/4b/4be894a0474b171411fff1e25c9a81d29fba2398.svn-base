package com.uxb2b.ecp.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxb2b.ecp.bean.RejectReasonMaintainBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IAwardDataRegisterBusiness;
import com.uxb2b.ecp.business.IAwardDataVerifyBusiness;
import com.uxb2b.ecp.business.IRejectReasonMaintainBusiness;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.Enterprise;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.FlowFunction;
import com.uxb2b.ecp.model.FlowFunctionPK;
import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.model.FlowStepPK;
import com.uxb2b.ecp.model.MessageInbox;
import com.uxb2b.ecp.model.PassRecord;
import com.uxb2b.ecp.model.RejectReason;
import com.uxb2b.ecp.model.RejectReasonPK;
import com.uxb2b.ecp.persist.IAwardDataRepositoryPersist;
import com.uxb2b.ecp.persist.IEnterpriseRepositoryPersist;
import com.uxb2b.ecp.persist.IFlowStepRepositoryPersist;
import com.uxb2b.ecp.persist.IJsonMessagePersist;
import com.uxb2b.ecp.persist.IMessageInboxRepositoryPersist;
import com.uxb2b.ecp.persist.IPassRecordRepositoryPersist;
import com.uxb2b.ecp.persist.IRejectReasonRepositoryPersist;
import com.uxb2b.ecp.persist.ISendMessagePersist;
import com.uxb2b.ecp.persist.ISysCodeRepositoryPersist;
import com.uxb2b.ecp.repository.AwardDataRepository;
import com.uxb2b.ecp.repository.RejectReasonRepository;
import com.uxb2b.ecp.service.DateConvertService;
import com.uxb2b.ecp.service.SystemInfo;

@Component
public class RejectReasonMaintainBusinessImpl implements IRejectReasonMaintainBusiness{
	
	private Logger log = LoggerFactory.getLogger(RejectReasonMaintainBusinessImpl.class);
	
	@Autowired
	private SystemInfo systemInfo;
	
	@Autowired
	private DateConvertService dateConvertService;
	
	@Autowired
	private IRejectReasonRepositoryPersist iRejectReasonRepositoryPersist;
	
	@Autowired
	private RejectReasonRepository rejectReasonRepository;
	
	@Autowired
	private ISysCodeRepositoryPersist iSysCodeRepositoryPersist;
	
	
	@Override
	public Page<RejectReasonMaintainBean> queryRejectReasonList(int pageNumber, int pageSize) throws Exception {
		Pageable pageable = new PageRequest(pageNumber-1,pageSize, new Sort(Sort.Direction.ASC, "id.codeType","id.codeKey"));
		Page<RejectReasonMaintainBean> rejectReasonList  = rejectReasonRepository.getAllJoinSysCode("REJECT",pageable);
		return rejectReasonList;
	}

	@Override
	public RejectReasonMaintainBean findByIdCodeTypeNoAndIdCodeKey(String codeType, String codeKey) {
		RejectReason rejectReason = iRejectReasonRepositoryPersist.findByIdCodeTypeAndIdCodeKey(codeType, codeKey);
		RejectReasonMaintainBean resultBean = new RejectReasonMaintainBean();
		resultBean.setCodeKey(rejectReason.getId().getCodeKey());
		resultBean.setCodeType(rejectReason.getId().getCodeType());
		resultBean.setCodeValue(rejectReason.getCodeValue());
		resultBean.setMemo(rejectReason.getMemo());
		resultBean.setStatus(rejectReason.getStatus());
		
		String codeValue = iSysCodeRepositoryPersist.findCodeValueByCodeTypeCodeKey("REJECT", rejectReason.getId().getCodeType());
		resultBean.setCodeKeyView(codeValue);
		
		return resultBean;
	}

	@Override
	public void save(RejectReasonMaintainBean searchBean) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		try{
			if("add".equals(searchBean.getAction())){
				RejectReason rejectReason = new RejectReason();
				RejectReasonPK rejectReasonPK = new RejectReasonPK();
				String codeKey = iRejectReasonRepositoryPersist.getMaxCodeKeyByCodeType(searchBean.getCodeType());
				
				rejectReasonPK.setCodeType(searchBean.getCodeType());
				rejectReasonPK.setCodeKey(codeKey);
				rejectReason.setId(rejectReasonPK);
				rejectReason.setCodeValue(searchBean.getCodeValue());
				rejectReason.setStatus(searchBean.getStatus());
				rejectReason.setMemo(searchBean.getMemo());
				rejectReason.setUpdateUser(userDetails.getUsername());
				rejectReason.setUpdateTime(dateConvertService.DateToTimestamp(new Date()));
				
				iRejectReasonRepositoryPersist.saveRejectReason(rejectReason);
				
			}else{
				RejectReason rejectReason = iRejectReasonRepositoryPersist.findByIdCodeTypeAndIdCodeKey(searchBean.getCodeTypeEdit(), searchBean.getCodeKey());
				
				rejectReason.setCodeValue(searchBean.getCodeValue());
				rejectReason.setStatus(searchBean.getStatus());
				rejectReason.setMemo(searchBean.getMemo());
				rejectReason.setUpdateUser(userDetails.getUsername());
				rejectReason.setUpdateTime(dateConvertService.DateToTimestamp(new Date()));
				
				iRejectReasonRepositoryPersist.saveRejectReason(rejectReason);
			}
				
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
