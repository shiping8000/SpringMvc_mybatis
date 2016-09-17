package com.boc.crm.base.mybatis;

/**
 * Mybatis Example工厂
 * @author Neo
 *
 */
public class ExampleFactory {

	/**
	 * 创建Mybatis Example
	 * @param criteriaClz Criteria类Class
	 * @return
	 */
	public static <CriteriaType extends BaseCriteria> Example<CriteriaType> createExample(Class<CriteriaType> criteriaClz) {
		return new Example<CriteriaType>(criteriaClz);
	}
	
}
