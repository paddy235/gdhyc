package com.zhiren.fuelmis.dc.service.impl.kucgl.rukgl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.ChurkdlrDao;
import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IChukdlrService;
import com.zhiren.fuelmis.dc.utils.Sequence;

public class ChurkdlrImpl implements IChukdlrService {
	@Autowired
	private ChurkdlrDao churkdlrDao;
	@Override
	public List<Map<String, Object>> getChurkd(Map<String, Object> map) {
		return churkdlrDao.getChurkd(map);
	}

	@Override
	public void saveChurkd(List<Map<String, Object>> list) {
		for (Map<String, Object> m : list) {
			if (m.get("ID") != null) {
				churkdlrDao.updateChurkd(m);
			} else {
				m.put("ID", Sequence.nextId());
				churkdlrDao.insertChurkd(m);
			}
		}
	}

	@Override
	public void delChurkd(List<Object> ids) {
		for (Object id : ids) {
			churkdlrDao.delChurkd(id);
		}

	}

	@Override
	public String getChukdbh(Map<String, Object> map) {
		String chukdbh = churkdlrDao.getChukdbh(map);
		return chukdbh;
	}

}
