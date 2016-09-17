package com.boc.crm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boc.crm.domain.Bill;
import com.boc.crm.domain.SystemNotice;
import com.boc.crm.domain.User;
import com.boc.crm.service.BillService;
import com.boc.crm.service.SystemNoticeService;
import com.boc.crm.service.UserService;
import com.boc.crm.util.SystemGlobal;


@Controller
@RequestMapping("/admin")
public class LoginController {

	Logger logger= Logger.getLogger(LoginController.class);
	
	@Resource
	private UserService userServiceImpl;
	
	@Resource
	private BillService billServiceImpl;
	
	@Resource
	private SystemNoticeService systemNoticeService;
	
	@RequestMapping("/login")  
	public String login(HttpServletRequest request,ModelMap model) throws Exception{
		String userName ="1000";
		String password ="123456";
		String serviceId = "98";
		System.out.println();
		User user =null;
		Bill bill =null;
		SystemNotice systemNotice =null;
		SystemNotice systemNotice2 =null;
		try{
			user =  userServiceImpl.getUserNameByUserId(userName,password);
			bill = billServiceImpl.queryById(1);
			systemNotice2 = systemNoticeService.getById("1");
			systemNotice =systemNoticeService.getSystemNoticeList(serviceId);
			systemNotice.setId(null);
			systemNoticeService.insert(systemNotice);
		}catch(Exception e){
			e.printStackTrace();
		}
		model.put("systemNotice2", systemNotice2);
		model.put("user", user);
		model.put("bill", bill);
		model.put("systemNotice", systemNotice);
		//System.out.println("systemNotice="+systemNotice.getServerName());
		Integer onclik =(Integer) SystemGlobal.get("onclik");
		onclik =onclik+1;
		SystemGlobal.put("onclik", onclik);
		logger.error(" 登录信息压力测试 第  "+ onclik +"次————————————————————");
		return "admin/login";
	}
	
	
}
