package com.boc.crm.base.mybatis;

import org.springframework.beans.factory.FactoryBean;

/**
 * 数据库url包装器
 * @author huangweiqi
 * 2015-7-22
 */
public class DbUrlWrapper implements FactoryBean<String> {

	/**
	 * 最终url
	 */
	private String url;
	/**
	 * url前缀
	 */
	private String urlPrefix;
	/**
	 * url参数
	 */
	private String urlParam;
	
	@Override
	public String getObject() throws Exception {
		url = urlPrefix + urlParam;
		return url;
	}

	@Override
	public Class<?> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getUrl() {
		url = urlPrefix + urlParam;
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
	
	
	
}
