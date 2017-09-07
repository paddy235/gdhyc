package com.zhiren.fuelmis.dc.dao.gongyspg.gongmjhzl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("gongmjhzlDao")
public interface GongmjhzlDao {

	public List<Map<String, Object>> getRigmjhList(Map<String, Object> map);

}
