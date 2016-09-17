package com.boc.crm.service;

import java.util.List;

import com.boc.crm.base.service.BaseService;
import com.boc.crm.dao.criteria.BillCriteria;
import com.boc.crm.domain.Bill;

/**
 * 
 * <br>
 * <b>功能：</b>BillService<br>
 */
public interface BillService extends  BaseService<Bill, BillCriteria> {
	
	public void add(Bill bill);
	
	public List<Bill> queryByList();
	
	public Bill queryById(Integer id);

}
