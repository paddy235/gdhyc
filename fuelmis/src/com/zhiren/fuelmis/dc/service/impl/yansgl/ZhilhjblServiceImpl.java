package com.zhiren.fuelmis.dc.service.impl.yansgl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import com.zhiren.fuelmis.dc.dao.yansgl.ZhilhjblDao;
import com.zhiren.fuelmis.dc.service.yansgl.IZhilhjblService;

public class ZhilhjblServiceImpl implements IZhilhjblService{

	@Resource
	private ZhilhjblDao zhilhjblDao;

	@Override
	public List<Map<String, Object>> getZhilhjbl(Map<String, Object> map) {
		return zhilhjblDao.getZhilhjbl(map);
	}
	
	
	

}
