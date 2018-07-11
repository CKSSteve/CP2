package com.uxb2b.ecp.service;

import javax.servlet.ServletContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uxb2b.ecp.bean.FunctionBean;
import com.uxb2b.ecp.persist.IRoleFunctionRepositoryPersist;
import com.uxb2b.ecp.persist.IRoleRepositoryPersist;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 載入FUNCTION 到 ServletContext
 * 
 * @author steve 
 *
 */
@Service
public class FunctionService {

	@Autowired
	private IRoleFunctionRepositoryPersist iRoleFunctionRepositoryPersist;
	
	@Autowired
	IRoleRepositoryPersist IRoleRepositoryPersist;
	
	Map<Integer, List<FunctionBean>> aMap = new HashMap();
	Map<Integer, List<FunctionBean>> bMap = new HashMap();
	Map<Integer, List<FunctionBean>> dMap = new HashMap();
	
	/**
	 * 
	 * @param context
	 */
	public void putAllFunctionToContext(ServletContext context) {


		List<FunctionBean> FunctionBean=iRoleFunctionRepositoryPersist.getJoinRoleFunction();
		List<Integer> roleIdList=IRoleRepositoryPersist.getAllRoleId();
		

		roleIdList.forEach(action->toMap(action,FunctionBean));
		context.removeAttribute("functionA");
		context.removeAttribute("functionB");
		context.removeAttribute("functionD");
		context.setAttribute("functionA", aMap);
		context.setAttribute("functionB", bMap);
		context.setAttribute("functionD", dMap);

	}
	
	/**
	 * 
	 * @param i 第幾迴圈
	 * @param roleFunction List
	 */
	public void toMap(int i,List<FunctionBean> roleFunction){
		List<FunctionBean> list = new ArrayList<>();

		list= roleFunction.stream().filter(predicate->predicate.getRoleId()==i&&"A".equals(predicate.getParentId())).collect(Collectors.toList());
		aMap.put(i, list);
		list= roleFunction.stream().filter(predicate->predicate.getRoleId()==i&&"B".equals(predicate.getParentId())).collect(Collectors.toList());
		bMap.put(i, list);
		list= roleFunction.stream().filter(predicate->predicate.getRoleId()==i&&"D".equals(predicate.getParentId())).collect(Collectors.toList());
		dMap.put(i, list);
	}
}
