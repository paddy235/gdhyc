package com.zhiren.fuelmis.dc.dao.caiygl.bianmcx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("bianmcxDao")
public interface BianmcxDao { //
	public List<Map<String, Object>> getTabelData(Map<String, Object> map);
}
