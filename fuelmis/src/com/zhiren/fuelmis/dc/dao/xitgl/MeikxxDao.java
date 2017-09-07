package com.zhiren.fuelmis.dc.dao.xitgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.entity.xitgl.Gongysmkglb;
import com.zhiren.fuelmis.dc.entity.xitgl.Kuangzglb;
import com.zhiren.fuelmis.dc.entity.xitgl.Meikxx;

/** 
 * @author 陈宝露
 */
@Repository
public interface MeikxxDao {
	List<Meikxx> getAll(Map<String,Object> map);
	
	int insertMeikxx(Map<String,Object> map);
	
	List<Meikxx> getOne(Map<String,Object> map);
	
	int updateMeikxx(Map<String,Object> map);
	
	int insertKuangzglb(List<Kuangzglb> list);
	
	int insertGongysmkglb(List<Gongysmkglb> list);
	
	List<String> getKuangzglb(Map<String,Object> map);
	
	List<String> getGongysmkglb(Map<String,Object> map);
	
	int deleteKuangzglb(String meikxxb_id);
	
	int deleteGongysmkglb(String meikxxb_id);
}
