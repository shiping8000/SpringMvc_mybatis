package com.boc.crm.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.boc.crm.base.mybatis.BaseMapper;
import com.boc.crm.base.service.BaseServiceImpl;
import com.boc.crm.dao.UserMapper;
import com.boc.crm.dao.criteria.UserCriteria;
import com.boc.crm.domain.User;
import com.boc.crm.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, UserCriteria> implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public BaseMapper<User, UserCriteria> getBaseMapper() {
		return  userMapper;
	}

	
	@Override
	public User getUserNameByUserId(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);


		return user;
	}




	

}
