package com.uxb2b.ecp.persist.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

public class CustomizeDynamicSpecifications {
	
	public enum CustomOperator {
		EQ, LIKE, GT, LT, GTE, LTE, IN, DATE_BETWEEN, BETWEEN_IN_HOURS_AND_MINUTE, NOT_NULL, NE
	}
	
	public static <T> Specification<T> generateSpecification(final Collection<CustomizeSearch> filters, final Class<T> entityClazz) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Logger log = LoggerFactory.getLogger(CustomizeDynamicSpecifications.class);
				if (filters != null && !filters.isEmpty()) {
					List<Predicate> predicates = Lists.newArrayList();
					for (CustomizeSearch filter : filters) {
						// nested path translate, 如Task的名為"user.name"的filedName, 轉換為Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						
						// logic operator
						switch (filter.operator) {
							case EQ:
								predicates.add(builder.equal(expression, filter.value));
								break;
							case NE:
								predicates.add(builder.notEqual(expression, filter.value));
								break;	
							case LIKE:
								predicates.add(builder.like(expression, "%" + filter.value + "%"));
								break;
							case GT:
								predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
								break;
							case LT:
								predicates.add(builder.lessThan(expression, (Comparable) filter.value));
								break;
							case GTE:
								predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
								break;
							case LTE:
								predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
								break;
							case IN:
								predicates.add(expression.in(filter.value));
								break;
							case DATE_BETWEEN:
								DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
								try {
									List<String> dateList = (List<String>) filter.value;
									Date start =  df.parse(dateList.get(0));
									Date end = null;
									if (dateList.size() == 2) {
										end = new DateTime(df.parse(dateList.get(1))).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(59).toDate();
										if (start.compareTo(end) == 0) {
											end = new DateTime(start).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(59).toDate();
										} 
									} else {
										end = new DateTime(start).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(59).toDate();
									}
									predicates.add(builder.between(expression, start, end));
								} catch(Exception e) {
									log.error("客製共用動態查詢錯誤，DATE_BETWEEN：", e);
								}
								break;
							case BETWEEN_IN_HOURS_AND_MINUTE:
								DateFormat dft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								try {
									List<String> dateList = (List<String>) filter.value;
									Date start =  dft.parse(dateList.get(0));
									Date end = dft.parse(dateList.get(1));;
									predicates.add(builder.between(expression, start, end));
								} catch(Exception e) {
									log.error("客製共用動態查詢錯誤，BETWEEN_IN_HOURS_AND_MINUTE：", e);
								}
								break;
							case BETWEEN:
									List<String> dateList = (List<String>) filter.value;
									predicates.add(builder.between(expression, dateList.get(0), dateList.get(1)));
								break;
							case NOT_NULL:
								predicates.add(builder.isNotNull(expression));
								break;
							
						}
					}

					// 將所有條件用 and 聯合
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
	
	public static Predicate buildTimePredicate(CriteriaBuilder criteriaBuilder, Path expressionDate,
											   String startDateStr, String endDateStr) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Predicate predicate = null;
		Date startDate = null;
		Date endDate = null;

		if (StringUtils.isNotBlank(startDateStr) && StringUtils.isBlank(endDateStr)) {

			startDate = df.parse(startDateStr);
			predicate = criteriaBuilder.greaterThanOrEqualTo(expressionDate, (Comparable) startDate);

		} else if (StringUtils.isBlank(startDateStr) && StringUtils.isNotBlank(endDateStr)) {

			endDate = df.parse(endDateStr);
			predicate = criteriaBuilder.lessThanOrEqualTo(expressionDate, (Comparable) endDate);

		} else {

			startDate = new DateTime(df.parse(startDateStr)).withHourOfDay(00)
															.withMinuteOfHour(00)
															.withSecondOfMinute(00)
															.withMillisOfSecond(00).toDate();
			endDate = new DateTime(df.parse(endDateStr)).withHourOfDay(23)
														.withMinuteOfHour(59)
														.withSecondOfMinute(59)
														.withMillisOfSecond(59).toDate();
			predicate = criteriaBuilder.between(expressionDate, startDate, endDate);

		}
		return predicate;
	}
}
