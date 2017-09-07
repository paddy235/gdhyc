package com.zhiren.fuelmis.dc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawTableUtil {
	
	public static String[][] Creatarr(List<Map<String, Object>> list,String[] column){
		int rowsize = list.size();
		int colsize = column.length;
		Object middlevalue;
		String[][] tablevalue = new String[rowsize][colsize];
		for(int m = 0 ; m<list.size(); m++){
			Map<String, Object> HashMap = (HashMap<String, Object>) list.get(m);
			for(int n = 0; n<colsize; n++){
				middlevalue = HashMap.get(column[n]);
				if(null == middlevalue){
					tablevalue[m][n] = "";
				}else{
					tablevalue[m][n] = String.valueOf(HashMap.get(column[n]));
				}
			}
		}
		return tablevalue;
	}
}
