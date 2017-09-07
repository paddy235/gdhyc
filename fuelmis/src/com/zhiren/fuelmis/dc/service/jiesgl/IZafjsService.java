package com.zhiren.fuelmis.dc.service.jiesgl;

import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

public interface IZafjsService {

	List<Map<String, Object>> getZafjs();

	void saveZafjs(List<Map<String, Object>> list, Renyxx user,Diancxx diancxx);

	void delZafjs(String id);

}
