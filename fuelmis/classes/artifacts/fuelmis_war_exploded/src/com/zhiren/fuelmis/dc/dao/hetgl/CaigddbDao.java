package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.hetgl.Caigddb;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddbsub;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddbyfsub;
import com.zhiren.fuelmis.dc.entity.hetgl.Hetkhzb;
import com.zhiren.fuelmis.dc.entity.xitgl.Gongys;

@Repository("caigddbDao")
public interface CaigddbDao {

	public int addCaigddb(Caigddb caigddb);

	public List<Map<String, Object>> getCaigddbList(Map<String, Object> map);
	public List<Map<String, Object>> getjiagtype();
	public List<Map<String, Object>> getCaigddbinfoList(Map<String, Object> map);

	public int delCaigddb(Map<String, Object> map);

	public List<Caigddb> getCaigddbById(Map<String, Object> map);

	public int updateCaigddb(Caigddb caigddb);

	public int addCaigddbsub(Caigddbsub caigddbsub);

	public int addCaigddbyfsub(Caigddbyfsub caigddbyfsub);

	public List<Caigddbsub> getCaigddbsubById(Map<String, Object> map);

	public List<Caigddbyfsub> getCaigddbyfsubById(Map<String, Object> map);

	public void updateCaigddbsub(Caigddbsub caigddbsub);

	public void updateCaigddbyfsub(Caigddbyfsub caigddbyfsub);

	public int delKaohzbbyid(Map<String, Object> map);
	
	public int delKaohzbbyPriceschemeId(Map<String, Object> map);

	public int delCaigddbsubbyid(Map<String, Object> map);

	public int delCaigddbyfsubbyid(Map<String, Object> map);

	public int delCaigddbsub(Map<String, Object> map);

	public int delCaigddbyfsub(Map<String, Object> map);

	public List<Map<String, Object>> getJihkjById(@SuppressWarnings("rawtypes") Map map);

	public List<Gongys> getGongysById(@SuppressWarnings("rawtypes") Map map);

	public String getHuowMingcById(@SuppressWarnings("rawtypes") Map map);

	public String getYunsfsMingcById(@SuppressWarnings("rawtypes") Map map);

	public void addKaohzb(Hetkhzb hetkhzb);

    /**
     *
     * @param hetkhzb
     */
	public void updateKaohzb(Map<String, Object> hetkhzb);

	public List<Caigddb> getCaigddByBh(Map<String, Object> map);

	public String beforedelCaigddb(Map<String, Object> map);

    /**
     *
     * @param map
     */
    void insertKaohzb(Map<String, Object> map);
}
