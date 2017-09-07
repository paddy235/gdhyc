package com.zhiren.fuelmis.dc.service.kucgl.chukgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * @author 陈宝露
 */
public interface IHaoyckService {
	/**
	 * 查询出库单明细
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getHaoyckmx(Map<String, Object> map);

	/**
	 * 出库
	 * 
	 * @param o
	 */
	void chuk(JSONObject o);


	/**
	 * 保存出库单
	 * 
	 * @param o
	 * @return
	 */
	String saveChukd(JSONObject o);

	/**
	 * 删除出入库单
	 * 
	 * @param chukdbh
	 */
	void deleteChurkd(String chukdbh);

	/**
	 * 根据条件查询出库单列表
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getChukdList(Map<String, Object> map);

	List<Map<String, Object>> getDangrjc();
}
