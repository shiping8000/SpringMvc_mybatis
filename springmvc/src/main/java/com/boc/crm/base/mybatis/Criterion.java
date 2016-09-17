package com.boc.crm.base.mybatis;

import java.util.Collection;

/**
 * 单个and的条件
 * @author Neo
 *
 */
public class Criterion {

	/**
	 * 条件
	 */
	private String condition;
	/**
	 * 条件值
	 */
	private Object value;
	/**
	 * 第二个条件值
	 */
	private Object secondValue;
	/**
	 * 是否为无值条件
	 */
	private Boolean isNoValue = false;
	/**
	 * 是否为单值条件
	 */
	private Boolean isSingleValue = false;
	/**
	 * 是否为双值条件
	 */
	private Boolean isBetweenValue = false;
	/**
	 * 是否为list条件
	 */
	private Boolean isListValue = false;
	
	/**
	 * 无值条件构造函数
	 * @param condition 条件
	 */
	public Criterion(String condition) {
		super();
		this.condition = condition;
		this.isNoValue = true;
	}
	
	/**
	 * 单值或list条件构造函数
	 * @param condition 条件
	 * @param value 条件值
	 */
	public Criterion(String condition, Object value) {
		super();
		this.condition = condition;
		this.value = value;
		if (value instanceof Collection<?>) {
			this.isListValue = true;
		} else {
			this.isSingleValue = true;
		}
	}
	
	/**
	 * 双值条件构造函数
	 * @param condition 条件
	 * @param value 第一个条件值
	 * @param secondValue 第二个条件值
	 */
	public Criterion(String condition, Object value, Object secondValue) {
		super();
		this.condition = condition;
		this.value = value;
		this.secondValue = secondValue;
		this.isBetweenValue = true;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(Object secondValue) {
		this.secondValue = secondValue;
	}

	public Boolean getIsNoValue() {
		return isNoValue;
	}

	public void setIsNoValue(Boolean isNoValue) {
		this.isNoValue = isNoValue;
	}

	public Boolean getIsSingleValue() {
		return isSingleValue;
	}

	public void setIsSingleValue(Boolean isSingleValue) {
		this.isSingleValue = isSingleValue;
	}

	public Boolean getIsBetweenValue() {
		return isBetweenValue;
	}

	public void setIsBetweenValue(Boolean isBetweenValue) {
		this.isBetweenValue = isBetweenValue;
	}

	public Boolean getIsListValue() {
		return isListValue;
	}

	public void setIsListValue(Boolean isListValue) {
		this.isListValue = isListValue;
	}
	
}
