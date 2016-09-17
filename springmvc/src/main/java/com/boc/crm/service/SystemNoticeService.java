package com.boc.crm.service;

import com.boc.crm.base.service.BaseService;
import com.boc.crm.dao.criteria.SystemNoticeCriteria;
import com.boc.crm.domain.SystemNotice;



/**
 * 用户信息认证 Service
 * @author CodeGen
 * 2015-01-18 15:39:14
 */
public interface SystemNoticeService extends  BaseService<SystemNotice, SystemNoticeCriteria> {

	/**
	 * 获得最新的认证信息
	 * @param serviceId  商户小U ID
	 * @return
	 */
	public SystemNotice getSystemNoticeList(String serviceId);
	
	
}