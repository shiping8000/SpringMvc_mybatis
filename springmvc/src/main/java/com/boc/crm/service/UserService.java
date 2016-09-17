package com.boc.crm.service;

import com.boc.crm.base.service.BaseService;
import com.boc.crm.dao.criteria.UserCriteria;
import com.boc.crm.domain.User;

/**
 * 用户类
 * @author csp
 *
 */
public interface UserService extends  BaseService<User, UserCriteria> {

	/**
	 * 通过用户Id获取用户
	 * @param 
	 */
	public User getUserNameByUserId(String userName,String password);
	
	
}
