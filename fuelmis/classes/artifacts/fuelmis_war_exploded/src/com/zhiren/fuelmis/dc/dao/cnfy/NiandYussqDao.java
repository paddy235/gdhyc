package com.zhiren.fuelmis.dc.dao.cnfy;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/** 
 * @author 摧枯拉朽cococa
 */
@Repository
public interface NiandYussqDao {
	@SuppressWarnings("rawtypes")
	List getNiandYussqData(Map<String, Object> map);
	
	@SuppressWarnings("rawtypes")
	List getsubmitdatas(Map<String, Object> map);
	
	int addNiandysData(Map<String, Object> map);
	
	int delNiandyusById(String id);
	
	@SuppressWarnings("rawtypes")
	List getNinadyusById(String id);
	
	int updateNiandysyId(Map<String, Object> map);
	
	int updateNiandys(Map<String, Object> map);
	
	String getZhuangt(Map<String, Object> map);
	
	int updateState(Map<String, Object> map);
}
