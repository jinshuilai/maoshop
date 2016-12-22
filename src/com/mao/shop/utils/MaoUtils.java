package com.mao.shop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

public class MaoUtils {
	
	public static String readProp(String key){
		Properties prop = new Properties();
		InputStream resourceAsStream = MaoUtils.class.getClassLoader().getResourceAsStream("mao.properties");
		String value = null;
		try {
			prop.load(resourceAsStream);
			value = prop.getProperty(key);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return value;
	}
	
	public static void printJSON(String result , HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("html/text;charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized String getOrderNo(Integer userId) {  
        String str = new SimpleDateFormat("ddMMyyHHmmss").format(new Date());     
        String u = String.valueOf(userId.intValue() + 10000);
        u = u.substring(u.length() - 4, u.length());
        str = "1" + str + u;  
        return str;  
    }  
	
	public static Integer[] getRandomIntegerArray(int maxNum,int returnSize) {
		List<Integer> arr = new ArrayList<Integer>();       
	    for(int i=0;i<maxNum;i++){
	        arr.add(i);
	    }
	    Integer[] array = new Integer[returnSize];
	    for(int i=0;i<returnSize;i++){
	        array[i] = arr.remove(new Random().nextInt(arr.size()));
	    }
		return array;
	}
	
	public static void main(String[] args) {
		String str = "majkljljlsfjo";
		StringBuffer sb = new StringBuffer(str);
		for (int i = 1; i < str.length()-1; i++) {
			sb.setCharAt(i, '*');
		}
		System.out.println(sb.toString());
	}
}
