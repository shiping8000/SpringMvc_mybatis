package com.boc.crm.base.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.boc.crm.base.exception.PoJoReflectException;

/**
 * 反射工具类
 * @author Neo
 *
 */
public class ReflectionUtil {

	private static final Logger logger = LogManager.getLogger(ReflectionUtil.class);
	
	/** 基本类型的ClassMap */
    private static final Map<String, Class<?>> PRIMITIVE_CLASS_MAP = new HashMap<>();
    static {
        PRIMITIVE_CLASS_MAP.put(boolean.class.getName(), boolean.class);
        PRIMITIVE_CLASS_MAP.put(char.class.getName(), char.class);
        PRIMITIVE_CLASS_MAP.put(byte.class.getName(), byte.class);
        PRIMITIVE_CLASS_MAP.put(short.class.getName(), short.class);
        PRIMITIVE_CLASS_MAP.put(int.class.getName(), int.class);
        PRIMITIVE_CLASS_MAP.put(long.class.getName(), long.class);
        PRIMITIVE_CLASS_MAP.put(float.class.getName(), float.class);
        PRIMITIVE_CLASS_MAP.put(double.class.getName(), double.class);
        PRIMITIVE_CLASS_MAP.put(void.class.getName(), void.class);
    }
	
	/**
	 * 获取父类的泛型Class
	 * @param clz 当前子类的Class
	 * @param index 泛型参数索引
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperGenericClass(Class clz, int index) {
		Type type = clz.getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			logger.error(clz.getSimpleName() + "'s superclass is not ParameterizedType");
			return Object.class;
		}
		Type[] params = ((ParameterizedType) type).getActualTypeArguments();
		
		if (index >= params.length || index < 0) {
			logger.error("params.length: " + params.length + ", index is illegal");
			return Object.class;
		}
		
		if (!(params[index] instanceof Class)) {
			logger.error(clz.getSimpleName() + "has not set the actual class to superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * 获取类的类名简称
	 * @param clz
	 * @return
	 */
	public static String getClassSimpleName(Class<?> clz) {
		return clz.getSimpleName();
	}
	
	/**
	 * 通过类名获取Class
	 * @param className 类名
	 * @return
	 */
	public static Class<?> getClassByClassName(String className) {
        Class<?> clz = PRIMITIVE_CLASS_MAP.get(className);
        if (clz != null) {
            return clz;
        }

        try {
            clz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clz;
    }
	
	/**
	 * 获取class的定义属性
	 * @param filedName
	 * @param clazz
	 * @return
	 */
	public static Field findField(String filedName,Class clazz){
		Field[] fields = clazz.getDeclaredFields();
		Field findField = findField(filedName, fields);
		if(findField==null && clazz.getSuperclass()!=null){
			findField = findField(filedName, clazz.getSuperclass().getDeclaredFields());
		}
		return findField;
	}
	
	/**
	 * 获取clas的定义属性
	 * @param filedName
	 * @param fields
	 * @return
	 */
	public static Field findField(String filedName,Field[] fields){
		if(fields==null) return null;
		for(Field f : fields){
			if(CommonUtil.strEqual(f.getName(),filedName)){
				return f;
			}
		}
		return null;
	}
	
	/**
	 * 通过骆驼命名法查找属性
	 * @param camal_field_name
	 * @param clazz
	 * @return
	 */
	public static Field findFieldByCamel(String camal_field_name,Class clazz){
		String javaPropName = camelCaseColumn2JavaProp(camal_field_name);
		return findField(javaPropName, clazz);
	}
	
	/**
	 * 通过骆驼命名法查找属性值
	 * @param camal_field_name
	 * @param clazz
	 * @return
	 * @throws PoJoReflectException 
	 */
	public static Object getValueByCamel(Object invokeObj,String camel_field_name) throws PoJoReflectException{
		Class clazz = invokeObj.getClass();
		Field field = findFieldByCamel(camel_field_name, clazz);
		if(field==null){
			throw new PoJoReflectException(PoJoReflectException.CODE_1_NOREADMETHOD,"["+clazz.getName()+"."+field.getName()+"]");
		}
		return getValue(invokeObj, field);
	}
	
	
	public static void setValue(Object obj,Field field,Object value) throws PoJoReflectException{
		Class clazz = obj.getClass();
		try {
			PropertyDescriptor fieldDescriptor = new PropertyDescriptor(field.getName(), clazz);
			Method writeMethod = fieldDescriptor.getWriteMethod();
			Object realValue = dataTypeConvert(field, value);
			writeMethod.invoke(obj, realValue);
		} catch (IntrospectionException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_2_CREATEPRODESCRIPTOR,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		} catch (IllegalAccessException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_3_INVOKEGETSETMETHOD,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_3_INVOKEGETSETMETHOD,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		} catch (InvocationTargetException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_3_INVOKEGETSETMETHOD,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		}		
	}
	
	public static Object getValue(Object invokeObj,Field field) throws PoJoReflectException{
		Class clazz = invokeObj.getClass();
		try {
			PropertyDescriptor fieldDescriptor = new PropertyDescriptor(field.getName(), clazz);
			Method readMethod = fieldDescriptor.getReadMethod();
			return readMethod.invoke(invokeObj);
		} catch (IntrospectionException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_2_CREATEPRODESCRIPTOR,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		} catch (IllegalAccessException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_3_INVOKEGETSETMETHOD,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_3_INVOKEGETSETMETHOD,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		} catch (InvocationTargetException e) {
			logger.catching(e);
			throw new PoJoReflectException(PoJoReflectException.CODE_3_INVOKEGETSETMETHOD,"["+clazz.getName()+"."+field.getName()+"]"+e.getMessage());
		}		
	}
	
	public static Object dataTypeConvert(Field field,Object value){
		return CommonUtil.parse(value, field.getType());
	}
	
	/**
	 * 
	 * @Title: camelCaseTable2Class
	 * @Description: (将骆驼命名表名转换为Java类名的方法)
	 * @param @param table
	 * @param @return 转换后的Java类名称
	 * @return String 返回类型
	 * @throws
	 */
	public static String camelCaseTable2Class(String table) {
		table = table.toLowerCase();
		if (table.contains("_")) {
			StringBuffer sb = new StringBuffer();
			String[] array = table.split("_");
			for (int i = 0; i < array.length; i++) {
				sb.append(toUpperCaseFirstOne(array[i]));
			}
			return sb.toString();
		}

		return toUpperCaseFirstOne(table);
	}

	/**
	 * 
	 * @Title: camelCaseColumn2JavaProp
	 * @Description: (将骆驼命名转换为Java类中的属性名)
	 * @param @param column
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String camelCaseColumn2JavaProp(String column) {
		column = column.toLowerCase();
		if (column.contains("_")) {
			String[] array = column.split("_");
			StringBuffer sb = new StringBuffer();
			sb.append(array[0]);
			for (int i = 1; i < array.length; i++) {
				sb.append(toUpperCaseFirstOne(array[i]));
			}
			return sb.toString();
		}
		return column;
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}
	
	/**
	 * 获取方法对象
	 * @param joinPoint
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        final Signature sig = joinPoint.getSignature();
        if (!(sig instanceof MethodSignature)) {
            throw new NoSuchMethodException("This annotation is only valid on a method.");
        }
        final MethodSignature msig = (MethodSignature) sig;
        final Object target = joinPoint.getTarget();
        return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }
}
