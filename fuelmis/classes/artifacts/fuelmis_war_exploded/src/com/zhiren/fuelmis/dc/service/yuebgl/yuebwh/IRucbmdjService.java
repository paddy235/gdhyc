package com.zhiren.fuelmis.dc.service.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import net.sf.json.JSONArray;

/**
 * @author 陈宝露
 */
public interface IRucbmdjService {
//	JSONObject getAll(Map<String,Object> map);

    void createData(Map<String,Object> map);

    void saveData(List<ConcurrentMap> list) throws Exception;

    void delData(Map<String,Object> map);

    JSONArray check(Map<String,Object> map);
}
