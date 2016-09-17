package com.boc.crm.util;

import org.apache.log4j.Logger;

public class SystemGlobal {
	private static Logger logger = Logger.getLogger(SystemGlobal.class);

	private static Cache cache ;
	
	/** 系统消息记录集合 */
	private static String onclik ="onclik";

	/**
	 * 初始化系统缓存
	 */
	public static void init() {
		
		logger.error("系统 init...");

		cache = new Cache();
		
		cache.put(onclik, 0);

		
	}

	/**
	 * 获得缓存中的对象
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T get(String key, Class<T> c) {
		Object o = cache.get(key);
		if (o == null)
			return null;
		return c.cast(o);
	}

	/**
	 * 获得缓存中的对象
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return cache.get(key);
	}

	
	public static void put(String key,Object object) {
		cache.put(key, object);
	}
	
	
}
