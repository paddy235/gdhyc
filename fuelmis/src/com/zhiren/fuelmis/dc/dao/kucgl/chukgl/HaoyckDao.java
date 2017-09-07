package com.zhiren.fuelmis.dc.dao.kucgl.chukgl;

import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 陈宝露
 */
@Repository
public interface HaoyckDao {
	/**
	 * 添加出库单
	 * 
	 * @param map
	 */
	void insertChukd(Map<String, Object> map);

	/**
	 * 添加出入库单
	 * 
	 * @param map
	 */
	void insertChurkd(Map<String, Object> map);

	/**
	 * 根据库存组织获得会计期
	 * 
	 * @param kuczz
	 * @return 会计期
	 */
	String getKuaijrq(Object kuczz);

	/**
	 * 删除出入库单
	 * 
	 * @param id
	 */
	void deleteChurkd(Object id);

	/**
	 * 更新出入库单
	 * 
	 * @param churkd
	 */
	void updateChurkd(Map<String, Object> churkd);

	/**
	 * 查询出库单行
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getChukdhList(Map<String, Object> map);

	/**
	 * 查询出库单头
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getChukd(Map<String, Object> map);

	/**
	 * 添加出库子表
	 * 
	 * @param chukd
	 */
	void insertChukdSub(Map<String, Object> chukd);

	/**
	 * 更新出入库单的出库状态
	 * 
	 * @param zhuangt
	 */
	void updateChurkdZhuant(Map<String, Object> zhuangt);

	/**
	 * 删除出入库单根据出库单编号
	 * 
	 * @param chukdbh
	 */
	void deleteChurkdByChukdbh(String chukdbh);

	/**
	 * 查询出库单根据条件
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getChukdList(Map<String, Object> map);

	ChurkBean getQicChurkBean();

	ChurkBean getChukBean();

	ChurkBean getRukBean();

	void saveChurkBean(ChurkBean c);

	void updateChurkdShifdrZhuant(Map<String, Object> map);

	List<Map<String, Object>> getDangrjc();
}
