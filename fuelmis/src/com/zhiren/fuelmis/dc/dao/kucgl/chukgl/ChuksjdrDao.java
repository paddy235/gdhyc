package com.zhiren.fuelmis.dc.dao.kucgl.chukgl;

import org.springframework.stereotype.Repository;

import java.util.Map;

import java.util.List;

/**
 * @author rain
 */
@Repository
public interface ChuksjdrDao {
	/**
	 * 获取shifdr=0的所有入库单编号 yewlx=6
	 * 
	 * @return
	 */
	List<String> getChukdbhs();

	/**
	 * 根据出库单编号获得出库单子表id
	 * 
	 * @param chukdbh
	 * @return
	 */
	String getChukdID(String chukdbh);

	/**
	 * 更新出库单主表
	 * 
	 * @param chukdID
	 */
	void updateChukd(String chukdID);

	/**
	 * 删除出库单子表
	 * 
	 * @param chukdID
	 */
	void deleteChukdSub(String chukdID);

	/**
	 * 插入出库单主表
	 * 
	 * @param chukdID
	 * @param chukdbh
	 */
	void insertChukd(String chukdID, String chukdbh);

	/**
	 * 插入出库单子表
	 * 
	 * @param chukdbh
	 */
	void insertChukdSub(String chukdbh);

	/**
	 * 删除库存明细汇总表根据出库单编号
	 * 
	 * @param chukdbh
	 */
	void deteteKucmxhzb(String chukdbh);

	/**
	 * 插入库存明细汇总表根据出库单编号
	 * 
	 * @param chukdbh
	 */
	void insertKucmxhzb(String chukdbh);

	/**
	 * 更新库存明细汇总表根据出库单编号
	 * 
	 * @param chukdbh
	 */
	void updateKucmxhzb(String chukdbh);

	/**
	 * 更新出入库单是否导入的状态
	 * 
	 * @param chukdbh
	 */
	void updateChurkd(String chukdbh);

	/**
	 * 插入出库单价表
	 */
	void insertChukdj(Map<String, Object> map);

	List<Map<String, Object>> getChukdjCondition(String kuczzID);

	List<String> getKuczzIDs();
	/**
	 * 删除出库单价表
	 */
	void deleteChukdj(Map<String, Object> map);

	List<String> getKucmxhzbID(String chukdbh);

	List<Map<String, Object>> getKucmxList(String chukdbh);

	void updateKucmxhzb(Map<String, Object> kucmx);

	void deleteChukd();

	void deleteChukdSubByNothing();

	void deleteKucmxhzb();

	void updateChurkdbDelete();


}
