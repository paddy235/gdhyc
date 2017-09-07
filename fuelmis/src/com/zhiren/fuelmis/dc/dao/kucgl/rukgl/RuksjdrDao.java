package com.zhiren.fuelmis.dc.dao.kucgl.rukgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author Rain
 */
@Repository
public interface RuksjdrDao {
	/**
	 * 获取shifdr=0的所有入库单编号
	 * 
	 * @return
	 */
	List<String> getRukdbhs();

	/**
	 * 根据入库单编号得到入库单id
	 * 
	 * @param rukdbh
	 * @return
	 */
	String getRukdID(String rukdbh);

	/**
	 * 更新入库单主表
	 * 
	 * @param rukdID
	 */
	void updateRukd(String rukdID);

	/**
	 * 删除入库单子表根据主表的id
	 * 
	 * @param rukdID
	 */
	void deleteRukdSub(String rukdID);

	/**
	 * 插入入库单主表数据
	 * 
	 * @param rukdID
	 * @param rukdbh
	 */
	void insertRukd(String rukdID, String rukdbh);

	/**
	 * 插入入库单子表数据
	 * 
	 * @param rukdbh
	 */
	void insertRukdSub(String rukdbh);

	/**
	 * 更新出入库单是否导入的状态0代表导入1代表已经导入 全部更新为1
	 */
	void updateChurkd();

	void deteteKucmxhzb(String rukdbh);

	void insertKucmxhzb(String rukdbh);

	void updateKucmxhzb(Map<String, Object> kucmx);

	void updateChurkd(String rukdbh);

	List<String> getKucmxhzbID(String rukdbh);

	List<Map<String, Object>> getKucmxList(String rukdbh);
//------------------------------------------------------------------------------------------------------------------------
	List<String> getYunzfRukdbhs();

	String getYunzfRukdID(String rukdbh);

	void updateYunzfRukd(String rukdID);

	void deleteYunzfRukdSub(String rukdID);

	void insertYunzfRukdSub(String rukdbh);

	List<Map<String, Object>> getYunzfKucmxList(String rukdbh);

	void insertYunzfKucmxhzb(String rukdbh);

	void updateYunzfKucmxhzb(Map<String, Object> kucmx);

	void insertYunzfRukd(String rukdID, String rukdbh);

	void deleteMeik();

	void deleteMeikSub();

	void deleteKucmxhzb();

	void deleteYunzfRukd();

	void deleteYunzfRukdSubByNothing();

	void insertMonthTotal(Map<String, Object> kuaijrqMap);

	List<Map<String, Object>> getOpenKuaijrq();

	void updateChurkdbDelete();

	void updateYunzfDeleteb();

}
