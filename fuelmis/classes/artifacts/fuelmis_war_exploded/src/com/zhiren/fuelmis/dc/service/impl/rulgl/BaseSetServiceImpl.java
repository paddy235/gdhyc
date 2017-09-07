package com.zhiren.fuelmis.dc.service.impl.rulgl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.rulgl.BaseSetDao;
import com.zhiren.fuelmis.dc.service.rulgl.IbaseSetService;
import com.zhiren.fuelmis.dc.utils.Sequence;

@Service
public class BaseSetServiceImpl implements IbaseSetService {
	@Autowired
	private BaseSetDao baseSetDao;

	@Override
	public List<Map<String, Object>> getBanz() {
		// TODO Auto-generated method stub
		return baseSetDao.getBanz();
	}

	@Override
	public int insertBanz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int ret = -1;
		try {
			String id = Sequence.nextId();
			map.put("id", id);
			ret = baseSetDao.insertBanz(map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public int updateBanz(Map<String, Object> map) {
		// TODO Auto-generated method stub

		int ret = -1;
		try {
			ret = baseSetDao.updateBanz(map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public List<Map<String, Object>> getJiz() {
		// TODO Auto-generated method stub
		return baseSetDao.getJiz();
	}

	@Override
	public int insertJiz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int ret = -1;
		try {
			String id = Sequence.nextId();
			map.put("id", id);
			ret = baseSetDao.insertJiz(map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public int updateJiz(Map<String, Object> map) {
		// TODO Auto-generated method stub

		int ret = -1;
		try {
			ret = baseSetDao.updateJiz(map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}
}
