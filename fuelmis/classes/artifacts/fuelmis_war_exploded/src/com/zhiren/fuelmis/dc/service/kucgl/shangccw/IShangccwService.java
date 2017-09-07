package com.zhiren.fuelmis.dc.service.kucgl.shangccw;

import java.util.List;
import java.util.Map;

public interface IShangccwService {

	String[][] getList(Map<String, Object> map);


	void updateJiesdShangccwzt(List<String> idList);

	List<Map<String, Object>> getList4Xml(List<String> idList, Map<String, Object> map);


	void updateMonthTotalShangccwzt(List<String> idList);

}
