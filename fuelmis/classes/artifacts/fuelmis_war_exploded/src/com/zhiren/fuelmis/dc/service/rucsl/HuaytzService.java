package com.zhiren.fuelmis.dc.service.rucsl;

import net.sf.json.JSONArray;

import java.util.Map;

public interface HuaytzService {

	public JSONArray getTabelData(String strdate,String enddate,String diancid,String yunsfs,String pinzid);

	public JSONArray getTabelDataByRiq(Map<String, Object> map);
}
