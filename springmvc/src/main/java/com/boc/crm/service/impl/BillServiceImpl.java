package com.boc.crm.service.impl;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boc.crm.base.mybatis.BaseMapper;
import com.boc.crm.base.mybatis.Example;
import com.boc.crm.base.service.BaseServiceImpl;
import com.boc.crm.dao.BillMapper;
import com.boc.crm.dao.criteria.BillCriteria;
import com.boc.crm.domain.Bill;
import com.boc.crm.service.BillService;

/**
 * 
 * <br>
 * <b>功能：</b>BillService<br>
 */
@Service("billService")
public class BillServiceImpl extends BaseServiceImpl<Bill, BillCriteria> implements BillService    {

	
	@Resource
	private BillMapper billMapper;
	
	/**绑定微信回调线程池*/
	private static ThreadPoolExecutor downloadImgThreadPool;
	
	@Override
	public BaseMapper<Bill, BillCriteria> getBaseMapper() {
		// TODO Auto-generated method stub
		return billMapper;
	}
	
	@Override
	public void add(Bill bill){
		billMapper.insert(bill);
	}
	
	@Override
	public List<Bill> queryByList() {
		Example<BillCriteria> example = new Example<BillCriteria>(BillCriteria.class);
		example.setOrderByClause(new String [][]{{"createTime",example.ORDER_DESC}});
		List<Bill> certList= billMapper.getListByExample(example);
		return certList;
	}

	@Override
	public Bill queryById(Integer id) {
		return billMapper.getById(id);
	}

	

	
	public void createBillList(){
		Runnable callBackThread = new Runnable() {
			@Override
			public void run() {
				try {
					
					
					
					
					
				} catch (Exception e) {
					logger.catching(e);
				}
			}
		};
		if (downloadImgThreadPool == null) {
			synchronized(this) {
				if (downloadImgThreadPool == null) {
					downloadImgThreadPool = new ThreadPoolExecutor(5, 10000, 120, 
							TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
				}
			}
		}
		downloadImgThreadPool.execute(callBackThread);
	}
	
	

	

}
