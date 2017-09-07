package com.zhiren.fuelmis.dc.service.gongyspg.pingggl;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

public interface IYuezbpfService {

	List<Map<String, Object>> getYuezbpf(Map<String, Object> map);

	String computeScore(List<Map<String, Object>> list, Renyxx lurry);

	void fab(List<String> list);

	void fabCancel(List<Object> list);

}
