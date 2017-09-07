package com.zhiren.fuelmis.dc.service.gongyspg.pingggl;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

public interface IRizbpfService {

	List<Map<String, Object>> getRizbpf(Map<String, Object> map);

	String jis(List<Map<String, Object>> list, Renyxx renyxx);

	void fab(List<Map<String, Object>> list);

	void fabCancel(List<String> list);

}
