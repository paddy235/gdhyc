package com.zhiren.fuelmis.dc.service.impl.kucgl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.kucgl.KucsscbDao;
import com.zhiren.fuelmis.dc.entity.kucgl.ChurkBean;
import com.zhiren.fuelmis.dc.entity.kucgl.Shiscbhs;
import com.zhiren.fuelmis.dc.service.kucgl.KucsscbService;

@Service
public class KucsscbServiceImpl implements KucsscbService{

	@Resource
	private KucsscbDao kucsscbDao;
	
	@Override
	public List<Map<String, Object>> getKucsscbAll(Map<String, Object> map) {
		 List<Map<String , Object>> list = kucsscbDao.getKucsscbAll(map);
			JSONArray result=JSONArray.fromObject(list);
			return result;
	}

	@Override
	public void shicbhs(String rukdbh) {
		// 计算成本
		// 1.获取基准bean
		ChurkBean baseBean = kucsscbDao.getBaseChurkBean();
		// 2.获取待求bean
		List<ChurkBean> newBeans = kucsscbDao.getNewChurkBeans(rukdbh);
		Shiscbhs shiscbhs = new Shiscbhs();
		shiscbhs.iterate(baseBean, newBeans);
		for (ChurkBean churkBean : newBeans) {
			kucsscbDao.saveChurkBean(churkBean);
		}
	}
	
}
