package com.zhiren.fuelmis.dc.dao.caiygl.caizhbmwh;

import java.util.List;
import java.util.Map;

public interface CaizhbmwhDao {

	List<Map<String, Object>> getBianm(String diancid, String riq);


	void insertBianm(String yuanbm, String mubbm, String diancid,String zhuanmlbid, String nowTime, String user);

	void updateZhiybm(String caiybm, String zhiybm, String diancid);


	List<Map<String, Object>> getBianmByCaiybm(String caiybm, String diancid);


	void updateBianm(String yuanbm, String mubbm, String yuanbm_old,String diancid);


	void updateHuaybmOfHuayd(String huaybm, String huaybm_old, String diancid);


	

	

}
