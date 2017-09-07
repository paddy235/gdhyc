package com.zhiren.fuelmis.dc.service.kucgl.rukgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;

/** 
 * @author Rain
 */
public interface IQitrkService {
	/**
	 * 查询其他杂费入库列表
	 * @param map
	 * @return
     */
	List<Map<String, Object>> getQitrkList(Map<String, Object> map);


	JSONObject getRanlcgrk_WRK_MX2(Map<String, Object> map);


	/**
	 * 入库更改入库单编号状态1是否导入为0
	 * @param rukdbh
	 * @return
     */
	JSONArray ruk(String rukdbh);
	
	JSONArray chex(String rukdbh);
	
	JSONObject getQitrk();
	
	JSONArray check(String rukdbh);



	/**
	 * 删除运杂费入库
	 * @param id
     */
	void deleteYunzf(String id);


	void saveQitrk(JSONObject d);

}
