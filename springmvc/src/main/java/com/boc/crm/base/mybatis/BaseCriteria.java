package com.boc.crm.base.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Criteria基类
 * @author Neo
 *
 */
public abstract class BaseCriteria {

	@SuppressWarnings("unused")
	private static final Logger baseLogger = LogManager.getLogger(BaseCriteria.class);
	
	/**
	 * and查询条件list
	 */
	protected List<Criterion> criteria;
	
	protected BaseCriteria() {
		criteria = new ArrayList<Criterion>();
	}
	
	/**
	 * criteria是否为非空
	 * @return true=非空, false=空
	 */
	public boolean isNotEmpty() {
		return criteria.size() > 0;
	}

	/**
	 * 获取and查询条件list
	 * @return
	 */
	public List<Criterion> getCriteria() {
		return criteria;
	}
	
	/**
	 * 添加无值条件
	 * @param condition 条件
	 */
	protected void addCriterion(String condition) {
		if (condition == null) {
			throw new RuntimeException("condition is null");
		}
		criteria.add(new Criterion(condition));
	}
	
	/**
	 * 添加单值或list条件
	 * @param condition 条件
	 * @param value 条件值
	 * @param property 属性名称
	 */
	protected void addCriterion(String condition, Object value, String property) {
		if (value == null) {
			throw new RuntimeException("condition:" + condition + ", property:" + property + ", value is null");
		}
		criteria.add(new Criterion(condition, value));
	}
	
	/**
	 * 添加双值条件
	 * @param condition 条件
	 * @param value1 条件值1
	 * @param value2 条件值2
	 * @param property 属性名称
	 */
	protected void addCriterion(String condition, Object value1, Object value2, String property) {
		if (value1 == null) {
			throw new RuntimeException("condition:" + condition + ", property:" + property + ", value1 is null");
		}
		
		if (value2 == null) {
			throw new RuntimeException("condition:" + condition + ", property:" + property + ", value2 is null");
		}
		
		criteria.add(new Criterion(condition, value1, value2));
	}
	
}
