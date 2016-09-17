package com.boc.crm.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;



public class InitSystemListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(InitSystemListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
		logger.info("停止web服务...");
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("启动web服务...");
		
		SystemGlobal.init();
	}
	
	

}
