package com.boc.crm.base.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.boc.crm.base.util.JsonUtil;

/**
 * 对象基类
 * @author Neo
 *
 */
public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = 4583864030301867006L;

	@Override
	public String toString() {
		try {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		} catch (Exception e) {
			return super.toString();
		}
	}
	
	/**
	 * 输出Json字符串
	 * @return
	 */
	public String toJsonString() {
		try {
			return JsonUtil.toJson(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
	
}
