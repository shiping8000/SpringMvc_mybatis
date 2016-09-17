package com.boc.crm.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.boc.crm.domain.Bill;
import com.boc.crm.service.BillService;

@Controller
@RequestMapping("/admin")
public class BillController {

	
	Logger logger= Logger.getLogger(BillController.class);
	
	
	
	@Resource
	private BillService billService;
	
	@RequestMapping("/getbilllist")  
	public String billList(HttpServletRequest request,ModelMap model) {
		List<Bill> billList = billService.queryByList();
		logger.error("账单列表压力测试  账单数:"+billList.size());
		model.put("billList",billList);
		return "bill/bill_list";
	}

	@RequestMapping("/toAddBill")  
	public String toAddBill(HttpServletRequest request,Model model) {
		
		return "bill/bill_add";
	}
	
	@RequestMapping("/doAddBill")  
	public String doAddBill(HttpServletRequest request,ModelMap model) {
		Bill bill= new Bill();
		bill.setCreateTime(new Date());
		billService.add(bill);
		List<Bill> billList = billService.queryByList();
		//model.
		model.put("billList", billList);
		return "bill/bill_list";
	}
	
	
}
