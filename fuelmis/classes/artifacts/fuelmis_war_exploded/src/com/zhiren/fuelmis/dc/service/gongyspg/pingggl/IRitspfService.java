package com.zhiren.fuelmis.dc.service.gongyspg.pingggl;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

public interface IRitspfService {

	List<Map<String, Object>> getRitspf(Map<String, Object> map);

	void saveRitspf(List<Map<String, Object>> list);



}
