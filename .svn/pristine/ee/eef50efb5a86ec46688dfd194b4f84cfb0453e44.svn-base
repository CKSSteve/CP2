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
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uxb2b.ecp.bean.FlowMaintainBean;
import com.uxb2b.ecp.bean.UserProfileBean;
import com.uxb2b.ecp.business.IFlowMaintainBusiness;
import com.uxb2b.ecp.model.AwardData;
import com.uxb2b.ecp.model.CpData;
import com.uxb2b.ecp.model.Ctbflbq1;
import com.uxb2b.ecp.model.Flow;
import com.uxb2b.ecp.model.FlowFunction;
import com.uxb2b.ecp.model.FlowFunctionPK;
import com.uxb2b.ecp.model.FlowStep;
import com.uxb2b.ecp.model.FlowStepPK;
import com.uxb2b.ecp.model.Function;
import com.uxb2b.ecp.persist.IFlowMaintainPersist;
import com.uxb2b.ecp.repository.AwardDataRepository;
import com.uxb2b.ecp.repository.CpDataRepository;
import com.uxb2b.ecp.repository.Ctbflbq1Repository;
import com.uxb2b.ecp.repository.FlowFunctionRepository;
import com.uxb2b.ecp.repository.FlowRepository;
import com.uxb2b.ecp.repository.FlowStepRepository;
import com.uxb2b.ecp.repository.FunctionRepository;
import com.uxb2b.ecp.service.DateConvertService;
import com.uxb2b.ecp.service.SystemInfo;
/**
 * 簽核流程維護
 * @author evayang
 *
 */
@Component
public class FlowMaintainBusinessImpl implements IFlowMaintainBusiness{
	
	private Logger log = LoggerFactory.getLogger(FlowMaintainBusinessImpl.class);
	
	@Autowired
	FunctionRepository functionRepository;
	
	@Autowired
	FlowFunctionRepository flowFunctionRepository;
	
	@Autowired
	IFlowMaintainPersist iFlowMaintainPersist;
	
	@Autowired
	private SystemInfo systemInfo;
	
	@Autowired
	private DateConvertService dateConvertService;
	
	@Autowired
	private FlowStepRepository flowStepRepository;
	
	@Autowired
	FlowRepository flowRepository;
	
	@Autowired
	Ctbflbq1Repository ctbflbq1Repository;
	
	@Autowired
	AwardDataRepository awardDataRepository;
	
	@Autowired
	CpDataRepository cpDataRepository;
	
	
	@Override
	public List<Function> findFunctionList() {
		
		List<Function> functionList = functionRepository.findFlowFunctinList();
		List<FlowFunction> flowFuctionList = flowFunctionRepository.findAll();
		
		List<Function> flowFunctionList = new ArrayList<Function>();
		//已套用功能不重複套用
		for(Function f : functionList){
			if(!flowFuctionList.stream().anyMatch(str -> str.getId().getFunctionId().equals(f.getFunctionId()))) {
				flowFunctionList.add(f);
			}
		}
		return flowFunctionList;
	}
	
	@Override
	public List<Function> findFunctionListForModify(String flowId) {
		List<Function> functionList = functionRepository.findFlowFunctinList();
		List<FlowFunction> flowFuctionList = flowFunctionRepository.findAll();
		
		List<Function> flowFunctionList = new ArrayList<Function>();
		
		//已套用功能不重複套用,但此修改之flowId需顯示
		for(Function f : functionList){
			if(!flowFuctionList.stream().anyMatch(str -> str.getId().getFunctionId().equals(f.getFunctionId()))){
				flowFunctionList.add(f);
			}
		}
		
		for(FlowFunction flowFunction : flowFuctionList){
			if(flowFunction.getId().getFlowId() == Integer.valueOf(flowId) ){
				Function fuction = functionRepository.findByFunctionId(flowFunction.getId().getFunctionId());
				flowFunctionList.add(fuction);
			}
		}
		
		return flowFunctionList;
	}

	@Override
	public FlowMaintainBean save(FlowMaintainBean flowMaintainBean) {
		UserProfileBean userDetails = systemInfo.getUserProfileBean();
		
		try{
				if("add".equals(flowMaintainBean.getAction())){
					Flow flow = new Flow();
					//flow.setFlowId(Integer.parseInt(flowMaintainBean.getFlowId()));			
					flow.setFlowName(flowMaintainBean.getFlowName());
					flow.setFlowMemo(flowMaintainBean.getFlowMemo());
					flow.setCreateUser(userDetails.getUsername());
					flow.setCreateTime(dateConvertService.DateToTimestamp(new Date()));
					int flowId = iFlowMaintainPersist.saveFlow(flow);
					
					List<String> functionIdList = Arrays.asList(flowMaintainBean.getFlowFunctionId());
					for(String functionId :functionIdList){
						FlowFunction flowFunction = new FlowFunction();
						FlowFunctionPK flowFunctionPK = new FlowFunctionPK();
						
						flowFunctionPK.setFlowId(flowId);
						flowFunctionPK.setFunctionId(functionId);
						flowFunction.setId(flowFunctionPK);
						iFlowMaintainPersist.saveFlowFunction(flowFunction);
					}
					
					List<String> roleIdList = Arrays.asList(flowMaintainBean.getRoleId());
					for(String roleId : roleIdList){
						FlowStep flowStep = new FlowStep();
						FlowStepPK flowStepPK = new FlowStepPK();
						flowStepPK.setFlowId(flowId);
						String[] stepNo= roleId.split("_");
						if(!stepNo[0].equals("0")){
							flowStep.setRoleId(Integer.parseInt(stepNo[0]));
							flowStepPK.setStepNo(Integer.parseInt(stepNo[1]));
							flowStep.setFinalStep("0");
							flowStep.setId(flowStepPK);
							iFlowMaintainPersist.saveFlowStep(flowStep);
						}
					}
					
					//修改最後步驟
					FlowStep flowStep =flowStepRepository.findbyFlowId(flowId).get(0);
					flowStep.setFinalStep("1");
					iFlowMaintainPersist.saveFlowStep(flowStep);
					
				}else{
					Flow flow = flowRepository.findByFlowId(Integer.parseInt(flowMaintainBean.getFlowId()));
					flow.setFlowName(flowMaintainBean.getFlowName());
					flow.setFlowMemo(flowMaintainBean.getFlowMemo());
					flow.setModifyUser(userDetails.getUsername());
					flow.setModifyTime(dateConvertService.DateToTimestamp(new Date()));
					iFlowMaintainPersist.saveFlow(flow);
					
					flowFunctionRepository.del(Integer.parseInt(flowMaintainBean.getFlowId()));
					
					List<String> functionIdList = Arrays.asList(flowMaintainBean.getFlowFunctionId());
					for(String functionId :functionIdList){
						log.info("functionId:"+functionId);
						FlowFunction flowFunction = new FlowFunction();
						FlowFunctionPK flowFunctionPK = new FlowFunctionPK();
						
						flowFunctionPK.setFlowId(Integer.parseInt(flowMaintainBean.getFlowId()));
						flowFunctionPK.setFunctionId(functionId);
						flowFunction.setId(flowFunctionPK);
						iFlowMaintainPersist.saveFlowFunction(flowFunction);
					}
					
					flowStepRepository.del(Integer.parseInt(flowMaintainBean.getFlowId()));
					
					List<String> roleIdList = Arrays.asList(flowMaintainBean.getRoleId());
					for(String roleId : roleIdList){
						FlowStep flowStep = new FlowStep();
						FlowStepPK flowStepPK = new FlowStepPK();
						log.info("roleId:"+roleId);
						flowStepPK.setFlowId(Integer.parseInt(flowMaintainBean.getFlowId()));
						String[] stepNo= roleId.split("_");
						if(!stepNo[0].equals("0")){
							flowStep.setRoleId(Integer.parseInt(stepNo[0]));
							flowStepPK.setStepNo(Integer.parseInt(stepNo[1]));
							flowStep.setFinalStep("0");
							flowStep.setId(flowStepPK);
							iFlowMaintainPersist.saveFlowStep(flowStep);
						}
					}
					
					//修改最後步驟
					FlowStep flowStep =flowStepRepository.findbyFlowId(Integer.parseInt(flowMaintainBean.getFlowId())).get(0);
					flowStep.setFinalStep("1");
					iFlowMaintainPersist.saveFlowStep(flowStep);
				
					
				}
					
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

	@Override
	public Page<Flow> queryFlowList(FlowMaintainBean searchBean, int pageNumber, int pageSize) throws Exception {
		
		Pageable pageable = new PageRequest(pageNumber,pageSize, new Sort(Sort.Direction.ASC, "flowId"));
		Page<Flow> flowList  = flowRepository.findAll(pageable);		
		return flowList;
	}

	@Override
	public List<FlowMaintainBean> queryFlowList(FlowMaintainBean searchBean) throws Exception {
		List<Flow> flowList  = flowRepository.findAll(new Sort(Sort.Direction.ASC, "flowId"));
		List<FlowMaintainBean> resultList = new ArrayList<FlowMaintainBean>();
		for(Flow flow : flowList){
			FlowMaintainBean bean = new FlowMaintainBean();
			List<Ctbflbq1> ctbflbq1List = ctbflbq1Repository.findByFlowIdAndApproveStatusNot(flow.getFlowId(),"4");
			List<AwardData> awardData1List = awardDataRepository.findByFlowId1AndApproveStatus1Not(flow.getFlowId(), "4");
			List<AwardData> awardData2List = awardDataRepository.findByFlowId2AndApproveStatus2Not(flow.getFlowId(), "4");
			List<CpData> cpDataList= cpDataRepository.findByFlowIdAndApproveStatusNot(flow.getFlowId(), "4");
			bean.setFlowId(String.valueOf(flow.getFlowId()));
			bean.setFlowName(flow.getFlowName());
			if(ctbflbq1List.size() > 0 || awardData1List.size() > 0 || awardData2List.size() > 0 || cpDataList.size() > 0){
				bean.setAlreadyDel(false);					
			}else{
				bean.setAlreadyDel(true);					
			}
			
			bean.setFunctions(flow.getFunctions());
			
			resultList.add(bean);
		}
		
		return resultList;
	}

	@Override
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public FlowMaintainBean del(FlowMaintainBean flowMaintainBean) throws Exception{
		
		flowRepository.delete(Integer.parseInt(flowMaintainBean.getFlowId()));
		flowStepRepository.del(Integer.parseInt(flowMaintainBean.getFlowId()));
		
		return flowMaintainBean;
	}

	@Override
	public FlowMaintainBean findbyFlowId(FlowMaintainBean searchBean) throws Exception {
		
		String step = systemInfo.getApprove().get("maxlevel");
		Flow flow = flowRepository.findByFlowId(Integer.parseInt(searchBean.getFlowId()));
		List<FlowStep> flowStepList = flowStepRepository.findbyFlowId(Integer.parseInt(searchBean.getFlowId()));
		searchBean.setFlowId(searchBean.getFlowId());
		searchBean.setFlowName(flow.getFlowName());
		searchBean.setFlowMemo(flow.getFlowMemo());
		
		List<String> flowFunctionIdList = new ArrayList<String>();
		List<String> roleIdList = new ArrayList<String>();
		
		for(Function function : flow.getFunctions()){
			flowFunctionIdList.add(function.getFunctionId());
		}
		for(FlowStep flowStep : flowStepList){
			roleIdList.add(flowStep.getRoleId()+"_"+flowStep.getId().getStepNo());
		}	
		
		if( roleIdList.size() < Integer.parseInt(step) ){
			for(int i = roleIdList.size()+1; i<Integer.parseInt(step) +1; i++){
				roleIdList.add("0_"+i);
			}
		}
		
		searchBean.setFlowFunctionId(flowFunctionIdList.toArray(new String[flowFunctionIdList.size()]));
		searchBean.setRoleId(roleIdList.toArray(new String[Integer.parseInt(step)]));
		
		return searchBean;
	}

	
	
}
