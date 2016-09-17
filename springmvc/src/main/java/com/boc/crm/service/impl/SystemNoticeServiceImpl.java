package com.boc.crm.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.boc.crm.base.mybatis.BaseMapper;
import com.boc.crm.base.mybatis.Example;
import com.boc.crm.base.service.BaseServiceImpl;
import com.boc.crm.dao.SystemNoticeMapper;
import com.boc.crm.dao.criteria.SystemNoticeCriteria;
import com.boc.crm.domain.SystemNotice;
import com.boc.crm.service.SystemNoticeService;

/**
 * 用户信息认证表 Service 实现类
 * @author CodeGen
 * 2015-01-18 15:39:14
 */
@Service
public class SystemNoticeServiceImpl extends BaseServiceImpl<SystemNotice, SystemNoticeCriteria> implements SystemNoticeService {

	@Resource
	private SystemNoticeMapper systemNoticeMapper;
	
	@Override
	public BaseMapper<SystemNotice, SystemNoticeCriteria> getBaseMapper() {
		return systemNoticeMapper;
	}

	@Override
	public SystemNotice getSystemNoticeList(String serviceId) {
		Example<SystemNoticeCriteria> example = new Example<SystemNoticeCriteria>(SystemNoticeCriteria.class);
		//example.setOrderByClause(new String [][]{{"createTime",example.ORDER_DESC}});
		SystemNoticeCriteria criteria = example.createCriteria();
		criteria.andServerIdEqualTo(serviceId);
		List<SystemNotice> certList= systemNoticeMapper.getListByExample(example);
		return (null!=certList &&certList.size()>0)?certList.get(0):null;
	}
	
}
