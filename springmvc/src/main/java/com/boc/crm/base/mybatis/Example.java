package com.boc.crm.base.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.boc.crm.base.util.StringUtil;

/**
 * Mybatis Example类
 * @author Neo
 * 
 * @param <C> Criteria类型
 */
public class Example<C> {

	private static final Logger baseLogger = LogManager.getLogger(Example.class);
	
	/**排序，升序*/
	public static final String ORDER_ASC = "ASC";
	
	/**排序，降序*/
	public static final String ORDER_DESC = "DESC";
	
	/**
	 * Criteria Class
	 */
	private Class<C> criteriaClass;
	/**
	 * 排序，单个示例： 列名1 DESC；多个示例：列名1 ASC, 列名2 DESC  
	 */
	private String orderByClause;
	/**
	 * 是否distinct
	 */
	private Boolean distinct = false;
	/**
	 * or的查询条件list
	 */
	private List<C> orCriteria;
	
	public Example(Class<C> clz) {
		criteriaClass = clz;
		orCriteria = new ArrayList<C>();
	}
	
	/**
	 * 把一个criteria添加到or查询条件list中
	 * @param criteria
	 */
	public void or(C criteria) {
		orCriteria.add(criteria);
	}
	
	/**
	 * 创建Criteria
	 * @return
	 */
	public C createCriteria() {
		C criteria = null;
		try {
			criteria = criteriaClass.newInstance();
		} catch (Exception e) {
			baseLogger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
		orCriteria.add(criteria);
		return criteria;
	}
	
	/**
	 * 清空Example
	 */
	public void clear() {
		orderByClause = null;
		distinct = false;
		orCriteria.clear();
	}
	
	//---------------------------------------------------
	
	//static method
	/**
	 * 实体属性->列名
	 * @param prop
	 * @return
	 */
	public static String prop2Column(String prop) {
		validateProp(prop);
		return StringUtil.camelNaming2Underline(prop);
	}
	
	/**
	 * 验证实体属性，属性不合法会抛异常
	 * @param prop
	 */
	public static void validateProp(final String prop) {
		StringBuffer sb = new StringBuffer();
		Matcher matcher = StringUtil.PATTERN_LETTER_NUM.matcher(prop);
		while (matcher.find()) {
			sb.append(matcher.group(0));
		}
		if (sb.length() != prop.length()) {
			throw new IllegalArgumentException("实体属性只能由字母、数字组成");
		}
	}
	
	/**
	 * 验证排序方式
	 * @param orderType
	 * @return 合法则返回true
	 */
	public static boolean validateOrderType(String orderType) {
		if (ORDER_ASC.equals(orderType)) {
			return true;
		}
		if (ORDER_DESC.equals(orderType)) {
			return true;
		}
		return false;
	}
	
	//---------------------------------------------------
	
	//getter setter

	public String getOrderByClause() {
		return orderByClause;
	}
	
	/**
	 * 设置排序
	 * @param order 排序，格式如下: new String[][]{{"id", Example.ORDER_DESC}, {"loginName"}, {"userName", Example.ORDER_DESC}}
	 * 				在每个元素中，第一个子元素是实体属性名称，第二个子元素是排序方式（升序或降序）
	 * 				如果升序或降序没填，则默认为升序：Example.ORDER_ASC
	 */
	public void setOrderByClause(String[][] order) {
		if (ArrayUtils.isEmpty(order)) {
			this.orderByClause = null;
			return;
		}
		StringBuffer orderBuffer = new StringBuffer();  //排序串
		String[] singleOrder = null;  //单个属性排序
		String prop = null;  //实体属性名称
		String column = null;  //列名
		String orderType = null;  //排序方式
		for (int i = 0, lenOutter = order.length; i < lenOutter; i++) {
			singleOrder = order[i];
			if (ArrayUtils.isEmpty(singleOrder)) {
				continue;
			}
			
			prop = singleOrder[0];
			validateProp(prop);
			column = StringUtil.camelNaming2Underline(prop);
			
			//没有传入排序参数，{"loginName"}
			if (singleOrder.length == 1) {
				orderBuffer.append(String.format("t.%s %s", column, ORDER_ASC));
			}
			
			//传入了排序参数，{"id", Example.ORDER_DESC}
			if (singleOrder.length == 2) {
				orderType = singleOrder[1];
				if (!validateOrderType(orderType)) {
					orderType = ORDER_ASC;
				}
				orderBuffer.append(String.format("t.%s %s", column, orderType));
			}
			
			if (i != lenOutter - 1) {
				orderBuffer.append(",");
			}
		}
		
		if (orderBuffer.length() == 0) {
			this.orderByClause = null;
			return;
		}
		
		this.orderByClause = orderBuffer.toString();
	}

	public Boolean getDistinct() {
		return distinct;
	}

	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
	}

	public List<C> getOrCriteria() {
		return orCriteria;
	}

	public Class<C> getCriteriaClass() {
		return criteriaClass;
	}
	
	public static void main(String[] args) {
		Example<Object> example = new Example<Object>(Object.class);
		example.setOrderByClause(new String[][]{{"id", "DESC"}, {}, null, {"loginName"}, {"userName", "DESC"}});
		System.out.println("OrderByClause:" + example.getOrderByClause());
	}
	
}
