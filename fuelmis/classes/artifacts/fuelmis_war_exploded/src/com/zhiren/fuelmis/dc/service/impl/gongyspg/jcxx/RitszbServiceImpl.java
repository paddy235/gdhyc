package com.zhiren.fuelmis.dc.service.impl.gongyspg.jcxx;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiren.fuelmis.dc.dao.gongyspg.jcxx.RitszbDao;
import com.zhiren.fuelmis.dc.service.gongyspg.jcxx.IRitszbService;
@Service
public class RitszbServiceImpl implements IRitszbService {
	
	@Autowired
	private RitszbDao ritszbDao;
	@Override
	public List<Map<String, Object>> getRitszb(Map<String, Object> map) {
		return ritszbDao.getRitszb(map);
	}
	@Override@Transactional
	public void saveRitszb(List<Map<String, Object>> list) {
		for (Map<String, Object> map : list) {
			ritszbDao.updateRitszb(map);
		}
	}

}
