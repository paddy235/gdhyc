package com.zhiren.fuelmis.dc.dao.ruchy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author 刘志宇
 */
@Repository
public interface ShenhDao {

	/**
	 * 审核
	 * 
	 * @param id
	 * @param zhuangt
	 */
	void shenh(String id, String zhuangt);

	/**
	 * 回退
	 * 
	 * @param id
	 * @param zhuangt
	 */
	void huit(String id, String zhuangt);

	/**
	 * 审核历史
	 * 
	 * @param map
	 */
	void shenhLs(Map<String, Object> map);

	/**
	 * 删除化验单
	 * 
	 * @param id
	 */
	void deleteHuayd(String id);

	/**
	 * 查询化验单
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getHuayd(Map<String, Object> map);

	/**
	 * 获取化验单行数
	 * 
	 * @param conditionMap
	 * @return
	 */
	Integer getCount(Map<String, Object> conditionMap);

	/**
	 * 删除关系
	 * 
	 * @param id
	 */
	void deleteGX(String id);
	/**
	 * 根据id获取一条化验单数据
	 * @param huaydbid
	 * @return
	 */
	List<Map<String, Object>> getHuaysjById(String huaydbid);
	/**
	 * 根据id获取一条化验单数据
	 * @param huaydbid
	 * @return
	 */
	List<Map<String, Object>> getHuaydz(Map<String, Object> map);
	/**
	 * 向化验单(rl_hy_huaydb)里插入一条数据
	 * @param map
	 */
	void insert(Map<String, Object> map);
	/**
	 * 向化验单(rl_hy_huaydb)里更新一条数据
	 * @param map
	 */
	void update(Map<String, Object> map);

	/**
	 * 记录历史
	 * @param map
	 */
	void huaydLog(Map<String, Object> map);

	List<Map<String,Object>> getJiesxxList(String huaybm);

	void deleteRijiesd(Object rijiesdb_id);

	void deleteGX_JIESDB_CHEPB(Object rijiesdb_id);
}
