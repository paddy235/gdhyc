package com.zhiren.fuelmis.dc.service.kucgl;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

/**
 * @author 刘志宇
 */
public interface IKucglService {

	/**
	 * 查询库存组织
	 * 
	 * @return
	 */
	List<Map<String, Object>> getKuczz();

	/**
	 * 更新库存组织
	 * 
	 * @param map
	 * @return
	 */
	int update(Map<String, Object> map);

	/**
	 * 插入库存组织
	 * 
	 * @param map
	 * @return
	 */
	int insert(Map<String, Object> map);

	/**
	 * 查询库存物种
	 * 
	 * @return
	 */
	List<Map<String, Object>> getKucwz();

	/**
	 * 更新库存物种
	 * 
	 * @param map
	 * @return
	 */
	int updateKucwz(Map<String, Object> map);

	/**
	 * 插入库存物种
	 * 
	 * @param map
	 * @return
	 */
	int insertKucwz(Map<String, Object> map);

	/**
	 * 查询会计期
	 * 
	 * @return
	 */
	List<Map<String, Object>> getKuaijqdy();




	void saveKuaijqdy(List<Map<String, Object>> list);

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * 查询库存组织会计期关联
	 * 
	 * @return
	 */
	List<Map<String, Object>> getGuanl();

	/**
	 * 查询所有会计期列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getKuaijqList();

	/**
	 * 查询所有库存组织列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> getKuczzList();

	/**
	 * 查询库存位置
	 * 
	 * @return
	 */
	List<Map<String, Object>> getWeiz();

	/**
	 * 查询上一级库存组织列表
	 * 
	 * @param fuid
	 * @return
	 */
	List<Map<String, Object>> getFuKuczzList(String fuid);

	/**
	 * 更新库存位置
	 * 
	 * @param map
	 * @return
	 */
	int updateWeiz(Map<String, Object> map);

	/**
	 * 插入库存位置
	 * 
	 * @param map
	 *            表中一行数据，键表示列名
	 * @return
	 */
	int insertWeiz(Map<String, Object> map);

	List<Map<String, Object>> getKucftList();

	/**
	 * 保存库存分摊
	 * 
	 * @param list
	 */
	void saveKucftList(List<Map<String, Object>> list);

	List<Map<String, Object>> getChurkd(Map<String, Object> riq);

	void saveChurkd(List<Map<String, Object>> list, Renyxx user);

	void delChurkd(List<Object> ids);

	void yuemgz(Map<String, Object> map);

	String getChukdbh(Map<String, Object> map);

	void saveGuanl(List<Map<String, Object>> list, Renyxx user);


}
