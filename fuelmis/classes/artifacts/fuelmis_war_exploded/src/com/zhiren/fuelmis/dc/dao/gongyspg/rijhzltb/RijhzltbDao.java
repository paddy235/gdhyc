package com.zhiren.fuelmis.dc.dao.gongyspg.rijhzltb;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Repository;

@Repository("RijhzltbDao")
public interface RijhzltbDao {
	public List<Map<String, Object>> getRijhzltbAll(Map<String, Object> map);
	
	public void updateRijhzltb(Map<String,Object>map);
}
