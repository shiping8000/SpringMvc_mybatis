package com.boc.crm.base.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.boc.crm.base.page.PageQuery;
import com.boc.crm.base.util.CommonUtil;

public class BaseController {

	protected final Logger logger = LogManager.getLogger(getClass());
	
	protected PageQuery initPageQuery(String pageNum, String numPerPage) {
		int pageIdex = 1;
		if (!CommonUtil.isNull(pageNum)) {
			pageIdex = Integer.parseInt(pageNum);
		}
		int pageSize = PageQuery.DEFAULT_PAGE_SIZE;
		if (!CommonUtil.isNull(numPerPage)) {
			pageSize = Integer.parseInt(numPerPage);
		}
		return new PageQuery(pageIdex, pageSize);
	}
}
