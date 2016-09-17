package com.boc.crm.base.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boc.crm.base.date.DateUtil;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.LongSerializationPolicy;

/**
 * Json工具类
 * @author Neo
 *
 */
public class JsonUtil {

    /**
     * gson对象
     */
    private final static Gson gson = new GsonBuilder().serializeNulls()
            .setDateFormat(DateUtil.DATE_FORMAT_WITH_TIME)
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .disableHtmlEscaping().create();

 
    /**
     * 把对象转换成json字符串
     * @param object 待转换的对象
     * @return json格式的字符串
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 把对象转换成json字符串，可以配置需要忽略的属性
     * @param object 待转换的对象
     * @param ignoreProperties 需要忽略的属性list
     * @return json格式的字符串
     */
    public static String toJsonIgnore(Object object, final List<String> ignoreProperties) {
        if (ignoreProperties == null || ignoreProperties.size() == 0) {
            return toJson(object);
        }

        return new GsonBuilder().serializeNulls()
                .setDateFormat(DateUtil.DATE_FORMAT_WITH_TIME)
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .disableHtmlEscaping()
                .setExclusionStrategies(new ExclusionStrategy() {

                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        String propName = null;
                        for (int i = 0, len = ignoreProperties.size(); i < len; i++) {
                            propName = ignoreProperties.get(i);
                            if (f.getName().equals(propName)) {
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).create().toJson(object);
    }

    /**
     * 把对象转换成json字符串，可以配置需要输出的属性
     * @param object 待转换的对象
     * @param includeProperties 需要输出的属性list
     * @return json格式的字符串
     */
    public static String toJsonInclude(Object object, final List<String> includeProperties) {
        if (includeProperties == null || includeProperties.size() == 0) {
            return toJson(object);
        }

        return new GsonBuilder().serializeNulls()
                .setDateFormat(DateUtil.DATE_FORMAT_WITH_TIME)
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .disableHtmlEscaping()
                .setExclusionStrategies(new ExclusionStrategy() {

                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        String propName = null;
                        for (int i = 0, len = includeProperties.size(); i < len; i++) {
                            propName = includeProperties.get(i);
                            if (f.getName().equals(propName)) {
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).create().toJson(object);
    }

    /**
     * json字符串转换成对象
     * @param jsonStr json格式的字符串
     * @param clz 目标对象的Class对象
     * @return 目标对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> clz) {
        return gson.fromJson(jsonStr, clz);
    }
    
    public static <T> List<T> fromJson(String jsonStr, Type type) {
        return gson.fromJson(jsonStr, type);
    }
    
    public static <T> T fromJsonO(String jsonStr, Type type) {
        return gson.fromJson(jsonStr, type);
    }
    
    /**
     * 源对象转换成目标对象
     * @param srcObj
     * @param destClz
     * @return
     */
    public static <T> T exchange(Object srcObj, Class<T> destClz) {
    	if(srcObj==null) return null;
        return gson.fromJson(JsonUtil.toJson(srcObj), destClz);
    }
    
    public static <T> List<T> exchangeList(Object srcObj, Type type) {
    	if(srcObj==null) return null;
        return gson.fromJson(JsonUtil.toJson(srcObj), type);
    }

	public static <T> T mapToObject(Map map,Class<T> tclass){
		String jsonStr = JsonUtil.toJson(map);
		T instance = JsonUtil.fromJson(jsonStr,tclass);
		return instance;
	}
	
	public static Map<String,Object> objectToMap(Object obj){
		String jsonStr = JsonUtil.toJson(obj);
		return JsonUtil.fromJson(jsonStr,Map.class);
	}
	
	
	/**
	 * 特殊处理 会员平台查询 json 格式公用方法
	 * @param map   ice 传入的参数
	 * @param jsonStr  ice 返回json字符串
	 * @return
	 */
	public static JsonObject getJsonObject(Map<String,String> map,String jsonStr){
		Map<String,Object> retMap = new HashMap<String, Object>();
		JsonObject strjsonObj = JsonUtil.fromJson(jsonStr, JsonObject.class); 
		String menu [] = map.get("select").split(",");
		JsonArray newjsonArray  =new JsonArray();
		Gson gson =new Gson();
		if(strjsonObj.get("data").isJsonObject()){
			JsonObject dateJsonObjcet = strjsonObj.getAsJsonObject("data");
			JsonObject obj=new JsonObject();
			JsonArray jsonEmt = dateJsonObjcet.getAsJsonArray();
			for(int i =0;i<jsonEmt.size();i++ ){
				String key []=menu[i].substring(2).split("\\.");
				if(key.length==1){
					obj.add(key[0], jsonEmt.get(i) );
				}else{
					JsonElement StrjsonElement = gson.fromJson(JsonUtil.toJson(getNodeString(key,jsonEmt.get(i))), JsonElement.class); 
					obj.add(key[0], StrjsonElement );
				}
			}
			newjsonArray.add(obj);
		}else if(strjsonObj.get("data").isJsonArray()){
			JsonArray jsonArray = strjsonObj.getAsJsonArray("data");
			
			for (JsonElement jsonElement :jsonArray) {
				JsonObject obj=new JsonObject();
				JsonArray jsonEmt = jsonElement.getAsJsonArray();
				for(int i =0;i<jsonEmt.size();i++ ){
					String key []=menu[i].substring(2).split("\\.");
					if(key.length==1){
						obj.add(key[0], jsonEmt.get(i) );
					}else{
						JsonElement StrjsonElement = gson.fromJson(JsonUtil.toJson(getNodeString(key,jsonEmt.get(i))), JsonElement.class); 
						obj.add(key[0], StrjsonElement );
					}
				}
				newjsonArray.add(obj);
			}
		}
		retMap.put("data", newjsonArray);
		return JsonUtil.fromJson(retMap.toString(), JsonObject.class);
		
	}
	
	/**
	 *  上下级  json 赋值 装换工具类
	 * @param key
	 * @param value
	 * @return
	 */
	public static JsonObject getNodeString(String [] key, Object value){
		// {"crtUser":{"id":"123456"}}
		JsonObject jsonObject = new JsonObject();
		JsonElement StrjsonElement = gson.fromJson(JsonUtil.toJson(value), JsonElement.class); 
		for(int i=0;i<key.length-1;i++){
			JsonObject nodejson = new JsonObject();
			nodejson.add(key[key.length-i-1], StrjsonElement);
			jsonObject = nodejson;
		}
		return  jsonObject;
	}
	
	
	
}
