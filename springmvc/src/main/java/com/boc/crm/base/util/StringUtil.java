package com.boc.crm.base.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 * @author Neo
 *
 */
public class StringUtil {
	
	/**大写字母模式*/
	public static final Pattern PATTERN_UPPER_LETTER = Pattern.compile("[A-Z]");
	/**数字、字母模式*/
	public static final Pattern PATTERN_LETTER_NUM = Pattern.compile("[A-Za-z0-9]");
	
	/**
	 * 通过报名获取路径,com.cg.model -> com/cg/model 或 com\cg\model
	 * @param packageName
	 * @return
	 */
	public static String getPathByPackage(String packageName) {
		String[] pkgFragment = packageName.split("\\.");
		return StringUtils.join(pkgFragment, File.separator);
	}
	
	/**
	 * 把单词首字母转成大写
	 * @param word
	 * @return
	 */
	public static String convertFirstLetter2Upper(String word) {
		return (word.charAt(0) + "").toUpperCase() + word.substring(1);
	}
	
	/**
	 * 下划线命名->Camel命名，如：last_update_time -> lastUpdateTime
	 * @return
	 */
	public static String underline2CamelNaming(String source) {
		String[] words = source.split("_");
		words[0] = words[0].toLowerCase();
		if (words.length == 1) {
			return words[0];
		}
		
		String word = null;
		for (int i = 1, len = words.length; i < len; i++) {
			word = convertFirstLetter2Upper(words[i]);
			words[i] = word;
		}
		return StringUtils.join(words);
	}
	
	/**
	 * 下划线命名->Pascal命名，如：last_update_time -> LastUpdateTime
	 * @param source
	 * @return
	 */
	public static String underline2PascalNaming(String source) {
		String[] words = source.split("_");
		String word = null;
		for (int i = 0, len = words.length; i < len; i++) {
			word = convertFirstLetter2Upper(words[i]);
			words[i] = word;
		}
		return StringUtils.join(words);
	}
	
	/**
	 * Camel命名->下划线命名，如：lastUpdateTime -> last_update_time 
	 * @param source
	 * @return
	 */
	public static String camelNaming2Underline(String source) {
		StringBuffer buffer = new StringBuffer();
		
		Matcher matcher = PATTERN_UPPER_LETTER.matcher(source);
		String upperLetter = null;
		while (matcher.find()) {
			upperLetter = matcher.group(0);
			matcher.appendReplacement(buffer, "_" + upperLetter.toLowerCase());
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}
	
	/**
	 * Pascal命名->Camel命名
	 * @param source
	 * @return
	 */
	public static String pascalNaming2Camel(String source) {
		return (source.charAt(0) + "").toLowerCase() + source.substring(1);
	}
	
	/**
	 * 判断字符串是否为
	 * 
	 * @param str 目标字符
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	/**
	 * 判断字符串是否为
	 * 
	 * @param str 目标字符
	 */
	public static boolean isStrEmpty(Object value) {
		return (value==null||"null".equals(value.toString()) || value.toString().isEmpty());
	}
	
	/**
	 * 判断字符是否非空
	 * @param str 目标字符
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * 判断字符串是否属于数据的子元素
	* @Title: isBelongArray 
	* @Description: 
	* @param array
	* @param str
	* @return    
	* boolean
	* @throws
	 */
	public static boolean isBelongArray(String[] array,String str){
		if(null==array || array.length==0 || isEmpty(str)){
			return false;
		}
		for(int i=0;i<array.length;i++){
			//循环查找字符串数组中的每个字符串中是否包含所有查找的内容
		   if(equalsIgnoreCase(array[i], str)){
			 //查找到了就返回真，不在继续查询
			   return true;
		   }
		}
		//没找到返回false
		return false;
	}
	
	/**
	 * 判断字符串是否为数字可包含小数点
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
		   return false; 
		} 
		return true; 
	}
	
	/**
	 * 转换成字符串,空字符串返回""
	 * @param value
	 * @return
	 */
	public static String toString(Object value){
		return toString(value, false);
	}
	
	/**
	 * 转换成字符串
	 * @param value
	 * @param ifEmptyRetrunNull 是否为空字符串时返回null
	 * @return
	 */
	public static String toString(Object value, boolean ifEmptyRetrunNull){
		return isStrEmpty(value)?"":value.toString().trim(); 
	}
	
	/**
	 * 去除空字符串
	 * @param value
	 * @return
	 */
	public static String trimEmptyToNull(String value){
		return isStrEmpty(value)?null:value;
	}
	
	public static float toFloat(Object value){
		return isStrEmpty(value)?0:Float.parseFloat(value.toString().trim()); 
	}
	public static Double toDouble(Object value){
		return isStrEmpty(value)?0:Double.parseDouble(value.toString().trim()); 
	}
	public static long toLong(Object value){
		return isStrEmpty(value)?0:Long.parseLong(value.toString().trim()); 
	}
	public static long toShort(Object value){
		return isStrEmpty(value)?0:Short.parseShort(value.toString().trim()); 
	}
	public static long toByte(Object value){
		return isStrEmpty(value)?0:Byte.parseByte(value.toString().trim()); 
	}
	public static int toInt(Object value){
		return isStrEmpty(value)?0:Integer.parseInt(value.toString().trim()); 
	}
	public static boolean equalsIgnoreCase(String val1,String val2){
		if(isStrEmpty(val1)) return false;
		if(isStrEmpty(val2)) return false;
		return val1.equalsIgnoreCase(val2);
	}
	public static boolean equals(String val1,String val2){
		if(isStrEmpty(val1)) return false;
		if(isStrEmpty(val2)) return false;
		return val1.equals(val2);
	}
	
	
	/**
	 * 格式化金额
	 * @param amount 金额
	 * @return
	 */
	public static String formatAmount(Double amount) {
		if (amount == null) {
			return "";
		}
		return new DecimalFormat("0.00").format(amount);
	}
	
	/**
	 * 格式化百分数小数
	 * @param percent
	 * @return
	 */
	public static String formatPercent(Double percent) {
		if (percent == null) {
			return "";
		}
		return new DecimalFormat("0.00##").format(percent);
	}
	
	public static void main(String[] args) {
		System.out.println(equalsIgnoreCase("", ""));
//		System.out.println(underline2PascalNaming("last_update_time"));
		
//		System.out.println(underline2CamelNaming("last_update_time"));
//		System.out.println(underline2CamelNaming("User"));
		
//		System.out.println(convertFirstLetter2Upper("commodity"));
		
//		System.out.println(getPathByPackage("com.cg.model"));
		
//		System.out.println(camelNaming2Underline("lastUpdateTime"));
//		System.out.println(camelNaming2Underline("loginName"));
//		System.out.println(camelNaming2Underline("userName"));
		
	}
	
}
