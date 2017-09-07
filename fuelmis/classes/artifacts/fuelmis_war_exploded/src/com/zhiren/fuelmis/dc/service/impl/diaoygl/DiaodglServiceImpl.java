package com.zhiren.fuelmis.dc.service.impl.diaoygl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.diaoygl.RiddshDao;
import com.zhiren.fuelmis.dc.service.diaoygl.DiaodglService;


@Service
public class DiaodglServiceImpl implements DiaodglService {

	@Autowired
	private RiddshDao riddshDao;


	@Override
	public List<Map<String, Object>> getRiddsh(Map<String, Object> map) {
		return riddshDao.getRiddsh(map);
	}


	@Override
	public void shenh(String id, Long lurrid) {
		riddshDao.shenh(id,lurrid);
	}	
	@Override
	public void huit(String id, Long lurrid) {
		riddshDao.huit(id,lurrid);
	}	
}
