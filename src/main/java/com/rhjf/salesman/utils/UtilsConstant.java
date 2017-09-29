package com.rhjf.salesman.utils;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UtilsConstant {
	
	
	
	public static String RandCode( ) { 
	 
		char[] arrChar = new char[]{ '0','1','2','3','4','5','6','7','8','9' }; 
		StringBuilder num = new StringBuilder(); 
	 
		ThreadLocalRandom ran = ThreadLocalRandom.current();
		
		for (int i = 0; i < 15; i++) {
			int x = ran.nextInt(0,10);
			num.append(arrChar[x]); 
		} 
		return num.toString(); 
	}
	
	
	/**
	 *    获取UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
	 *   判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean strIsEmpty(String str){
		if(str==null||str.trim().isEmpty()){
			return true;
		}
		return false;
	}
	
	
	/**
	 *  判断obj对象是否为空 如果不为null 将返回对应的字符
	 * @param obj
	 * @return
	 */
	public static String ObjToStr(Object obj){
		if(obj==null){
			return "";
		}
		return obj.toString();
	}
	
	/**
	 *    将JSON对象转换成 map对象
	 * @param json
	 * @return
	 */
	public static Map<String , Object> jsonToMap(JSONObject json){
		Map<String,Object> map = new TreeMap<String,Object>();
		Set<String> keys = json.keySet();
		for (String key : keys) {
			map.put(key, json.get(key));
		}
		return map;
	}
	
	
	 /**  
     * 将Map对象通过反射机制转换成Bean对象  
	 * @param <T>
     *   
     * @param map 存放数据的map对象  
     * @param clazz 待转换的class  
     * @return 转换后的Bean对象  
     * @throws Exception 异常  
     */    
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws Exception {    
        T obj = clazz.newInstance();    
        if(map != null && map.size() > 0) {    
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();       //属性名  
                Object value = entry.getValue();
                
                String setMethodName = "set"    
                        + propertyName.substring(0, 1).toUpperCase()  
                        + propertyName.substring(1);    
                
                
                Field field = getClassField(clazz , propertyName);    
                if(field==null){
                	continue;  
                }
                Class<?> fieldTypeClass = field.getType();
                if(value==null){
                	 continue;
                }
                value = convertValType(value, fieldTypeClass); 
                clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);   
            }    
        }    
        return obj;    
    } 
    
    
    /**  
     * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)  
     *   
     * @param clazz 指定的class  
     * @param fieldName 字段名称  
     * @return Field对象  
     */    
    private static Field getClassField(Class<?> clazz, String fieldName) {    
        if( Object.class.getName().equals(clazz.getName())) {    
            return null;    
        }    
        Field []declaredFields = clazz.getDeclaredFields();    
        for (Field field : declaredFields) { 
			if(field.getName().equalsIgnoreCase(fieldName)){
				return field;
            }
        }    
        Class<?> superClass = clazz.getSuperclass();    
        if(superClass != null) {// 简单的递归一下    
            return getClassField(superClass, fieldName);    
        }    
        return null;    
    } 
    
    
    /**  
     * 将Object类型的值，转换成bean对象属性里对应的类型值  
     *   
     * @param value Object对象值  
     * @param fieldTypeClass 属性的类型  
     * @return 转换后的值  
     */    
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {    
        Object retVal = null;    
        if(Long.class.getName().equals(fieldTypeClass.getName())    
                || long.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Long.parseLong(value.toString());    
        } else if(Integer.class.getName().equals(fieldTypeClass.getName())    
                || int.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Integer.parseInt(value.toString());    
        } else if(Float.class.getName().equals(fieldTypeClass.getName())    
                || float.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Float.parseFloat(value.toString());    
        } else if(Double.class.getName().equals(fieldTypeClass.getName())    
                || double.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Double.parseDouble(value.toString());    
        } else {    
            retVal = value;    
        }    
        return retVal;    
    }



	public static String  GetSmsCode() {
		Random random = new Random();
		StringBuffer randBuffer = new StringBuffer();
		char[] codeSequence = { '0', '1', '2', '3', '4', '5','6', '7', '8', '9'};

		for (int i = 0; i < 4; i++) {
			randBuffer.append(String.valueOf(codeSequence[random.nextInt(10)]));
		}
		return randBuffer.toString();
	}


	public static String getRequestIP(HttpServletRequest request) {
		String ipaddr = request.getHeader("X-Real-IP");
		if ((ipaddr == null) || (ipaddr.length() == 0) || ("unknown".equalsIgnoreCase(ipaddr))) {
			ipaddr = request.getHeader("Proxy-Client-IP");
		}
		if ((ipaddr == null) || (ipaddr.length() == 0) || ("unknown".equalsIgnoreCase(ipaddr))) {
			ipaddr = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ipaddr == null) || (ipaddr.length() == 0) || ("unknown".equalsIgnoreCase(ipaddr))) {
			ipaddr = request.getHeader("X-FORWARDED-FOR");
			if ((ipaddr != null) && (ipaddr.length() > 0) && (!"unknown".equalsIgnoreCase(ipaddr))) {
				ipaddr = ipaddr.split(",")[0];
			}
		}
		if ((ipaddr == null) || (ipaddr.length() == 0) || ("unknown".equalsIgnoreCase(ipaddr))) {
			ipaddr = request.getRemoteAddr();
		}
		return ipaddr.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ipaddr;
	}



	public static  boolean checkMerchantName(String merchantName){

		if(merchantName.length() < 5 || merchantName.length() > 12){
			return true;
		}

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(merchantName);
		if (isNum.matches()) {
			return true;
		}

		if(merchantName.matches("[a-zA-Z]*")){
			return true;
		}

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher m = p.matcher(merchantName);
		if (!m.find()){
			return true;
		}
		return false;
	}

}
