package com.zhiren.fuelmis.dc.utils;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Util {
	public static String cssTable=" style='table-layout:fixed;empty-cells:show;border-collapse:collapse;margin:0 auto;bolder:1px solid #cad9ea;color:#666;font-size:10pt;text-align:center;'";
	public static String cssTitlle=" style='font-size:10pt;font-weight:bold;background: #FFF;'";
    public static String cssTh=" style='font-weight:bold;background-repeat:repeat-x;background-color:#f5fafe;'";
    public static String cssTr=" style='background-color:#f5fafe;' ";
    public static String cssTd=" style='border:1px solid #cad9ea;' ";
    
    /** 
     * 功能：将字符串装换为double，如果失败返回默认值。 
     * @author chh
     * @param str 
     *            字符串 
     * @param defaultValue 
     *            失败时返回的默认值 
     * @ret 
     * 	double数值
     */
    public static double toDouble(String str, double defaultValue) {  
        if (str == null) {  
            return defaultValue;  
        }  
        try {  
            return Double.parseDouble(str);  
        } catch (NumberFormatException nfe) {  
            return defaultValue;  
        }  
    }  
    
    public static String toNotNull(String str) {  
        if (str == null) {  
            return "";  
        } 
        
        if ("NULL".equalsIgnoreCase(str)){
        	return "";  
        }
        return str;  
    } 
    
    public static String formatValue(double dblValue,String strFormat){
    	DecimalFormat df=new DecimalFormat();
    	df.applyPattern(strFormat);  
        return df.format(dblValue);
    }
    
    public static String formatValue(String value,String strFormat){
    	if ("".equals(value)){
    		return "";
    	}
    	DecimalFormat df=new DecimalFormat();
    	df.applyPattern(strFormat);  
        return df.format(toDouble(value,0));
    }
    
	@SuppressWarnings("rawtypes")
	public static double getMapValue(HashMap aMap,String name){
		Double value=(Double)aMap.get(name);
		if (value==null){
			return 0d;
		} else{
			return value.doubleValue();
		}
	}
	
}
