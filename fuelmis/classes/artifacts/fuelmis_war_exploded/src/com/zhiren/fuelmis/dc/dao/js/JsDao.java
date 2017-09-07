package com.zhiren.fuelmis.dc.dao.js;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface JsDao {
	
	@SuppressWarnings("rawtypes")
	List getFapb(Map<String, Object> map);
	List<Map<String,Object>> getFapbById(String id);

	int addFapData(Map<String, Object> map);

	/**
	 * 查询发票编码的最大值
	 * @return 编码的最大值
	 */
	Integer getMaxBianm();
	int delFapById(String id);
	int updateFapById(Map<String, Object> map);
//	List<Map<String,Object>> getAllJiesxx();
	List<Map<String,Object>> getFapByCondition(Map<String, Object> map);
	String getMeikjcById(BigDecimal id);
	String getJeisdhById(BigDecimal id);
	List<Map<String, Object>> getAllJiesxx(String id);
	List<Map<String, Object>> getJiesxx();
	/**
	 * 根据条件查询发票信息
	 * @param conditionMap
	 * @return 发票信息
	 */
	List<Map<String,Object>> getFap(Map<String, Object> conditionMap);
	/**
	 * 查询结算单的编号和结算单id
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getJiesdhList(Integer id);
	/**
	 * 更新审批状态
	 * @param huitlc_id
	 * @param zhuangt
	 */
	void updateHuitZhuangt(String huitlc_id, String zhuangt);

	List<Map<String,Object>> getDanjAndJine(Map<String, Object> jiesdbh);



	void updateOtherJine(Map<String,Object> map);




	String getOtherZongje(Map<String,Object> map);


	String getMaxRukdid(String jiesdbh);

	List<String> getRukdbh(String jiesdbh);

	void updateMaxRukdje(String rukdidmax, Double leftJine, String balance_date);

	List<Map<String,Object>> getFapById(String id);

	void setJiesdLiucIDWithNull(String jiesbh);

	void updateHuaybg(Map<String, Object> r);

	String getSamcode(String rukdbh);

	Map<String,Object> getHuayd(String samcode);

	void updateChurkd(String s, String rukdbh);

	List<Map<String,Object>> getJiesdByYuejsdbh(String jiesdbh);

	void updateMeikSubById(Map<String, Object> jiesd);

	void updateMeikByRukdbh(Map<String, Object> map);
}
