package com.zhiren.fuelmis.dc.utils.formular;

import java.util.*;
import java.util.Map.Entry;

public class Result {
    public static Map<String,Object> key2UpperCase(Map map){
        Map<String,Object> rm=new HashMap<String,Object>();
        Set<Entry<String, Object>> entrySet = map.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            Object value = entry.getValue();
            String key = entry.getKey();
            rm.put(key.toUpperCase(),value);
        }
        return rm;
    }
	public static Double round_new(Double Value0, int Bit0) {
		Double Value1 = 0.0;
		Double Value2 = 0.0;

		if (Value0 == 0) {
			return 0.0;
		} else {

			Value1 = Math.floor(Value0 * Math.pow(10, Bit0)) - Math.floor(Value0 * Math.pow(10, Bit0 - 1)) * 10;
			Value2 = Value0 * Math.pow(10, Bit0);

			if (((Value2 - Math.floor(Value0 * Math.pow(10, Bit0))) >= 0.5)
					&& ((Value2 - Math.floor(Value0 * Math.pow(10, Bit0))) < 0.6)) {
				if ((Value2 - Math.floor(Value0 * Math.pow(10, Bit0))) == 0.5) {
					if (Value1 == 0 || Value1 == 2 || Value1 == 4 || Value1 == 6 || Value1 == 8) {
						return Math.floor(Value0 * Math.pow(10, Bit0)) / Math.pow(10, Bit0);
					} else {
						return (Math.floor(Value0 * Math.pow(10, Bit0)) + 1) / Math.pow(10, Bit0);
					}
				} else {
					return Math.round(Value0 * Math.pow(10, Bit0)) / Math.pow(10, Bit0);
				}
			} else {
				return Math.round(Value0 * Math.pow(10, Bit0)) / Math.pow(10, Bit0);
			}
		}
	}

	public static String[][] list2array(List<Map<String, Object>> list, int x) {
		String[][] arrData = new String[list.size()][x];
		for (int i = 0; i < list.size(); i++) {
			int j = 0;
			Map<String, Object> map = list.get(i);
			Set<Entry<String, Object>> entrySet = map.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				Object value = entry.getValue();
				if (value == null) {
					arrData[i][j++] = "";
				} else {
					arrData[i][j++] = value.toString();
				}
			}
		}
		return arrData;
	}

	/**
	 * 
	 * @param list<map<String,Object>>
	 * @param columNames
	 *            keys
	 * @return 对象数组
	 */
	public static String[][] list2array(List<Map<String, Object>> list, String[] columNames) {
		String[][] arrData = new String[list.size()][columNames.length];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			for (int j = 0; j < columNames.length; j++) {
				String key = columNames[j];
				Object value = map.get(key.toUpperCase());
				if (value != null) {
					arrData[i][j] = value.toString();
				} else {
					arrData[i][j] = "";
				}
			}
		}
		return arrData;
	}

	public static String[][] list2array(List<Map<String, Object>> list) {
		if (list.size() != 0) {
			int x = list.get(0).size();
			String[][] arrData = new String[list.size()][x];
			for (int i = 0; i < list.size(); i++) {
				int j = 0;
				Map<String, Object> map = list.get(i);
				Set<Entry<String, Object>> entrySet = map.entrySet();
				for (Entry<String, Object> entry : entrySet) {
					Object value = entry.getValue();
					if (value == null) {
						arrData[i][j++] = "";
					} else {
						arrData[i][j++] = value.toString();
					}
				}
			}
			return arrData;
		} else {
			return null;
		}
	}

	public static String list2XmlByte(List<Map<String, Object>> list) {	
		if(list!=null&&list.size()!=0){
			String xmlString="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<bills>\n";
			for (Map<String, Object> map : list) {
				Set<Entry<String, Object>> set = map.entrySet();
				xmlString+="<bill>\n";
				for (Entry<String, Object> entry : set) {
					String key=entry.getKey().toLowerCase();
					Object value = entry.getValue();
					if(!key.equals("item")){
						xmlString+="<"+key+">"+value+"</"+key+">"+"\n";
					}else{
						for (Map<String, Object> itemMap : (List<Map<String,Object>>)value) {
							xmlString+="<item>\n";
							for(Entry<String,Object> itemEntry : ((Map<String,Object>)itemMap).entrySet()){
								String itemkey=itemEntry.getKey().toLowerCase();
								Object itemvalue=itemEntry.getValue();
								xmlString+="<"+itemkey+">"+itemvalue+"</"+itemkey+">"+"\n";
							}
							xmlString+="</item>"+"\n";
							}
					}
				}
				xmlString+="</bill>\n";
			}
			xmlString+="</bills>";
			return xmlString;
		}else{
			return null;
		}
	}
}
