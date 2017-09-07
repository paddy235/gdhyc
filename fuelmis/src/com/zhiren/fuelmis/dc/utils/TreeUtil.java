package com.zhiren.fuelmis.dc.utils;

import java.util.List;
import java.util.Map;

public class TreeUtil {
	/**
	 * 根据节点数据集合，生成XML
	 * 
	 * @param treeNodes
	 *            权节点集合
	 * @return
	 */

	public static String parseNodeToXML(List<Map<String, Object>> treeNodes) {
		StringBuffer xmlnodes = new StringBuffer();
		if (treeNodes != null && treeNodes.size() > 0) {
			// xmlnodes.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
			xmlnodes.append("<data>");
			for (int i = 0; i < treeNodes.size(); i++) {
				for (Map.Entry<String, Object> entry : treeNodes.get(i).entrySet()) {// 遍历map
					xmlnodes.append("<" + entry.getKey() + ">"+ entry.getValue() + "</" + entry.getKey() + ">");
				}
			}
		}
		xmlnodes.append("</data>");
		return xmlnodes.toString();
	}
	/**
	 * 将map里面的null值换成""
	 * @param 
	 */
	public static void checkMapNull(Map<String, Object> map,List<String> keys){
		for (int i = 0; i < keys.size(); i++) {
			String key=keys.get(i);
			if(!map.containsKey(key)){
				map.put(key, "");
			}
		}
	}
	/**
	 * 将map里面的null值换成""
	 * @param 
	 */
	public static void checkMapNull(Map<String, Object> map,String[] keys){
		for (int i = 0; i < keys.length; i++) {
			String key=keys[i];
			if(!map.containsKey(key)){
				map.put(key, "0");
			}
		}
	}
}