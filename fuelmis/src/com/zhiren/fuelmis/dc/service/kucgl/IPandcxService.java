package com.zhiren.fuelmis.dc.service.kucgl;

import java.util.Map;

import net.sf.json.JSONArray;

/** 
 * @author 陈宝露
 */
public interface IPandcxService {
	JSONArray getReport(Map<String,Object> map);

	void shangb(Map<String, Object> map);
}
