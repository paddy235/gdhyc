package com.zhiren.fuelmis.dc.service.gongyspg.rigmjh;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public interface RigmjhService {
	//日供煤查看
	JSONObject searchRigmjhList(Map<String, Object> map);

	int updateRigmjh(Map<String, Object> map);

	int insertRigmjh(Map<String, Object> map);

	int delRigmjh(Long[] arr);

	int publishRigmjh(Long[] arr);

}
