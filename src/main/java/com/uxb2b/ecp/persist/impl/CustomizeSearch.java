package com.uxb2b.ecp.persist.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
public class CustomizeSearch {
	/*******************************************************************************
	 * Copyright (c) 2005, 2014 springside.github.io
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 *******************************************************************************/



		public enum Operator {
			EQ, LIKE, GT, LT, GTE, LTE, IN, DATE_BETWEEN, BETWEEN_IN_HOURS_AND_MINUTE, NOT_NULL, NE,BETWEEN
		}

		public String fieldName;
		public Object value;
		public Operator operator;

		public CustomizeSearch(String fieldName, Operator operator, Object value) {
			this.fieldName = fieldName;
			this.value = value;
			this.operator = operator;
		}
		
		public static Map<String, CustomizeSearch> getSearchFilter() {
			Map<String, CustomizeSearch> filters = Maps.newIdentityHashMap();
			return filters;
		}

		/**
		 * searchParams中key的格式為OPERATOR_FIELDNAME
		 */
		public static Map<String, CustomizeSearch> parse(Map<String, Object> searchParams) {
			Map<String, CustomizeSearch> filters = Maps.newIdentityHashMap();

			for (Entry<String, Object> entry : searchParams.entrySet()) {
				// 過濾空值
				String key = entry.getKey();
				Object value = entry.getValue();
				
				if (value instanceof String) {
					if (StringUtils.isBlank((String) value)) {
						continue;
					}
				}
				
				if (value instanceof Object[]) {
					
					String[] valueArray = (String[]) value;
					boolean isAllEmpty = true;
					
					for (String tempValue : valueArray) {
						if (StringUtils.isNotBlank(tempValue)) {
							isAllEmpty = false;
						}
					}
					
					if (isAllEmpty) {
						continue;
					} else {
						value = Arrays.asList((String[]) value);  						
					}
					
				}
				
				if (value instanceof List) {
					List<String> valueList = (List<String>) value;
					boolean isAllEmpty = true;
					
					for (String tempValue : valueList) {
						if (StringUtils.isNotBlank(tempValue)) {
							isAllEmpty = false;
						}
					}
					
					if (isAllEmpty) {
						continue;
					}
				}
				
				// 拆operator與filedAttribute
				String[] names = StringUtils.split(key, "_");
				if (names.length != 2) {
					throw new IllegalArgumentException(key + " is not a valid search filter name");
				}
				String filedName = names[1];
				Operator operator = Operator.valueOf(names[0]);

				// 創建searchFilter
				CustomizeSearch filter = new CustomizeSearch(filedName, operator, value);
				filters.put(key, filter);
			}

			return filters;
		}

}
