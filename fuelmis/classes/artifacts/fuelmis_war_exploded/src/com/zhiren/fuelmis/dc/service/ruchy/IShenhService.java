package com.zhiren.fuelmis.dc.service.ruchy;

import java.util.List;
import java.util.Map;

/**
 * @author 刘志宇
 */
public interface IShenhService {
	/**
	 * 审核
	 * 
	 * @param id
	 * @param zhuangt
	 * @param xiugry
	 */
	void shenh(String id, String zhuangt, String xiugry);

	/**
	 * 回退
	 * 
	 * @param id
	 * @param zhuangt
	 */
	void huit(String id, String zhuangt);

	/**
	 * 获取化验单数据
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

	List<Map<String, Object>> getHuaydz(Map<String, Object> map);

	void update(List<Map<String, Object>> a, String LURY);

	void huaydLog(Map<String, Object> map);

	List<Map<String,Object>> getJiesxxList(String huaybm);

}
