package com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;

public interface DuibbmdjDao {

	public List<Map<String, Object>> getBanzList();

	public List<Map<String, Object>> getJizList();

	public int updateMeihy(Map<String, Object> map);

	public int insertMeihy(Map<String, Object> map);

	public int insertHuayd(Map<String, Object> map);

	public Integer getHaoyCount(String riq);

	public List<Map<String, Object>> getHaoy(String riq);

	public int delMeiHy(Object id);

	public List<Map<String, Object>> getMeihy(Map<String, Object> map);

	public String getHuaybm(String diancxxbID, String huaybm);

	public String getBanzXuh(String banzid);


	public List<Map<String, Object>> getHuaybmList(String startrq, String endrq);
	/**
	 * 向化验单里插入一条空数据
	 * @param huaydid 化验单id
	 */
	public void insertHuayID(String huaydid);

	// public List<Map<String, Object>> getHaoy(String bianm, String riq);
	
	List<Map<String ,Object>> getMeihyAll(Map<String, Object> map);//煤耗用SIS查询
	
	void MeihyAdd(Map<String, Object> map); //
	
	void DelMeihy(int id);

	List<Map<String,Object>> getMeihysis(String riq);
}
