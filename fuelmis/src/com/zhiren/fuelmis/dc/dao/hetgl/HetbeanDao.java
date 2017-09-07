package com.zhiren.fuelmis.dc.dao.hetgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.common.Fujxx;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddbsub;
import com.zhiren.fuelmis.dc.entity.hetgl.Hetbean;
import com.zhiren.fuelmis.dc.entity.hetgl.Hetbxianggfzb;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Gongys;

@Repository("hetbeanDao")
public interface HetbeanDao {

	 List<Map<String, Object>> getHetbeanList(Map<String, Object> map);

	 int addHetbean(Hetbean hetbean);

	 int addCaigddbgx(Map<String, Object> map);



	 int delHetbean(Map<String, Object> map);

	 List<Hetbean> getHetbeanById(Map<String, Object> map);

	 List<Map<String, Object>> getCaigddbById(Map<String, Object> map);

	@SuppressWarnings("rawtypes")
	 List<Gongys> getGongysById(Map map);

	 List<Diancxx> DiancxxById(Map<String,Object> map);

	 int addHetbxianggfzb(Hetbxianggfzb hetbxianggfzb);

	@SuppressWarnings("rawtypes")
	 List<Diancxx> getDiancxxById(Map map);

	 int updateCaigddb(Map<String, Object> map);

	 int delCaigddbid(Map<String, Object> map);

	 void updateHetbean(Hetbean hetbean);

	 List<Caigddbsub> getCaigddbsub(Map<String, Object> map);

	 void addHetbeantmp(Hetbean hetbean);

	 void updateFujxx(Map<String, Object> fujmap);

	 List<Fujxx> getFujxxById(Map<String, Object> map);

	 int delHetbxianggfzb(Map<String, Object> map);

	 int delCaigddgl(Map<String, Object> map);

	 void updateHetbeanSanjzt(Map<String, Object> map);

	 List<Map<String, Object>> getCaigddbList(Map<String, Object> map);

	 List<Map<String, Object>> getKaohzbbList(Map<String, Object> map);

	 List<Map<String, Object>> getCaigddb(Map<String, Object> map);

	 List<Map<String, Object>> getRlhtmbList(Map<String, Object> map);


	 List<Map<String, Object>> getHetbh(@Param("hetbh") String hetbh);
}
