package com.zhiren.fuelmis.dc.service.jiesgl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import org.springframework.transaction.annotation.Transactional;

public interface IRijsscService {
	List<Map<String , Object>> getRijscx(Map<String, Object> map);


    void del(List<Object> list);

    void delRijsd(List<Map<String, Object>> jiesxxList);
}
