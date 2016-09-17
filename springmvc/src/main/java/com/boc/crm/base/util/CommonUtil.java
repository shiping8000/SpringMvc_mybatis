package com.boc.crm.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boc.crm.base.date.DateUtil;

public class CommonUtil {
	
	static {
		System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
	}
	
	public static boolean isNull(Object o) {
		if (o == null)
			return true;
		if (o instanceof String)
			return isNull((String) o);
		else {
			return false;
		}
	}

	public static boolean isNull(String str) {
		if (str == null)
			return true;
		if (str.isEmpty())
			return true;
		if (str.trim().length() == 0)
			return true;
		return false;
	}
	
	public static boolean isNull(String...strArr) {
		if(strArr==null) return true;
		for(String str : strArr){
			if(isNull(str)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	public static boolean isNotEmpty(Set set) {
		return !isEmpty(set);
	}

	public static boolean isNotEmpty(Object[] objArr) {
		return !isEmpty(objArr);
	}
	
	public static boolean isNotEmpty(Collection list) {
		return !isEmpty(list);
	}

	public static boolean isEmpty(List list) {
		if (list == null || list.isEmpty())
			return true;
		return false;
	}

	public static boolean isEmpty(Map map) {
		if (map == null || map.isEmpty())
			return true;
		return false;
	}

	public static boolean isEmpty(Set set) {
		if (set == null || set.isEmpty())
			return true;
		return false;
	}

	public static boolean isEmpty(Collection list) {
		if (list == null || list.isEmpty())
			return true;
		return false;
	}

	public static boolean isEmpty(Object[] objArr) {
		if (objArr == null || objArr.length == 0)
			return true;
		return false;
	}
	
	public static boolean strEqual(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			return false;
		if ((o1 + "").equals(o2 + "")) {
			return true;
		}
		return false;
	}
	
	public static boolean strNotEqual(Object o1, Object o2) {
		return !strEqual(o1, o2);
	}

	public static <T> boolean ifin(T obj, T[] inArr) {
		if (inArr == null || obj == null)
			return false;
		for (T ia : inArr) {
			if (strEqual(ia, obj)) {
				return true;
			}
		}
		return false;
	}

	public static <T> boolean ifin(T obj, List<T> list) {
		if (list == null || obj == null)
			return false;
		return ifin(obj, list.toArray());
	}

	public static boolean ifIndex(String str, String indexStr) {
		if (str == null || indexStr == null)
			return false;
		int index = str.indexOf(indexStr);
		if (index < 0) {
			return false;
		}
		return true;
	}

	/**
	 * str是否在indexStrs中存在
	 * 
	 * @param str
	 * @param indexStrs
	 * @return
	 */
	public static boolean ifIndex(String str, String... indexStrs) {
		if (str == null || indexStrs == null)
			return false;
		boolean isindex = true;
		for (String is : indexStrs) {
			if (!ifIndex(str, is)) {
				isindex = false;
				return isindex;
			}
		}
		return isindex;
	}
	
	public static String replaceAll(String src,String key,String value){
		if(value==null) return src;
		if(src.indexOf(key)>=0){
			src = src.replace(key, value);
		}
		
		if(src.indexOf(key)>=0){
			return replaceAll(src, key, value);
		}
		return src;
	}
	
	public static <T> T parse(Object o, Class<T> ct,
			boolean formatErrorReturnNull) {
		if (formatErrorReturnNull) {
			try {
				return parse(o, ct);
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		} else {
			return parse(o, ct);
		}
	}

	/**
	 * List类型转换
	 * 
	 * @param list
	 * @param ct
	 * @return
	 */
	public static <T> List parse(List list, Class<T> ct) {
		if (isEmpty(list))
			return null;
		List<T> returnList = new ArrayList<T>();
		for (Object o : list) {
			returnList.add((T) o);
		}
		return returnList;
	}

	/**
	 * Set类型转换
	 * 
	 * @param set
	 * @param ct
	 * @return
	 */
	public static <T> Set parse(Set set, Class<T> ct) {
		if (isEmpty(set))
			return null;
		Set<T> returnList = new HashSet<T>();
		for (Object o : set) {
			returnList.add((T) o);
		}
		return returnList;
	}

	public static <T> List parse(Collection collection, Class<T> ct) {
		if (isEmpty(collection))
			return null;
		List<T> returnList = new ArrayList<T>();
		for (Object o : collection) {
			returnList.add((T) o);
		}
		return returnList;
	}

	public static <T> T parse(Object obj, Class<T> ct) {
		if (isNull(obj))
			return null;
		Object returnO = obj;
		if (obj.getClass() == ct) {
			return (T) returnO;
		}
		if (ct == Long.class) {
			returnO = Long.parseLong(obj + "");
		} else if (ct == Integer.class) {
			returnO = Integer.parseInt(obj + "");
		} else if (ct == Float.class) {
			returnO = Float.parseFloat(obj + "");
		} else if (ct == Double.class) {
			returnO = Double.parseDouble(obj + "");
		} else if (ct == Byte.class) {
			returnO = Byte.parseByte(obj + "");
		} else if (ct == String.class){
			returnO = obj + "";
		} else if (ct == Date.class) {
			String strObj = obj + "";
			if(strObj.indexOf(".")>0){
				strObj = strObj.replace(".", "#").split("#")[0];
			}
			returnO = DateUtil.parseDate(strObj,DateUtil.DATE_FORMAT_WITH_TIME);
		}

		return (T) returnO;
	}
	
	public static Map<String,List<Object>> putListMap(Map<String,List<Object>> listmap,String key,Object value){
		if(listmap==null){
			listmap = new HashMap<String, List<Object>>();
		}
		List<Object> priceList = listmap.get(key);
		if(priceList==null){
			priceList = new ArrayList<Object>();
		}
		priceList.add(value);
		listmap.put(key, priceList);
		return listmap;
	}
}
