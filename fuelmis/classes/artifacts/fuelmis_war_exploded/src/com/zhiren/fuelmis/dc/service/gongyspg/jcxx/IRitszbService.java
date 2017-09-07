package com.zhiren.fuelmis.dc.service.gongyspg.jcxx;

import java.util.List;
import java.util.Map;

public interface IRitszbService {

	List<Map<String, Object>> getRitszb(Map<String, Object> map);

	void saveRitszb(List<Map<String, Object>> list);

}
