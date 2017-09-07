package com.zhiren.fuelmis.dc.service.kucgl.baobcx;

import java.util.Map;

import net.sf.json.JSONArray;

public interface IRanmzgService {
	/**
	 * 燃煤暂估明细表
	 */
	JSONArray getRanmzg(Map<String,Object> map);
}
