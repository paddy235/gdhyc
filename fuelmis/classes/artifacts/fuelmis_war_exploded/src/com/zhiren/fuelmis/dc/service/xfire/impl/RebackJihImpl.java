package com.zhiren.fuelmis.dc.service.xfire.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.reback.RebackHetDao;
import com.zhiren.fuelmis.dc.dao.jih.reback.RebackNiandJhDao;
import com.zhiren.fuelmis.dc.dao.jih.reback.RebackYuedJhDao;
import com.zhiren.fuelmis.dc.dao.jih.upload.UploadDao;
import com.zhiren.fuelmis.dc.service.xfire.dao.RebackJihDao;

import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.TreeUtil;
@Service
public class RebackJihImpl implements RebackJihDao {
	@Autowired
	private RebackNiandJhDao rebackNianJhDao;
	@Autowired
	private RebackYuedJhDao rebackYuedJhDao;

	@Autowired
	private RebackHetDao rebackHetDao;


	@Autowired
	private UploadDao uploadDao;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void HuitYear(String zhuangt, String huitlc_id, String year,String month, String diancxxb_id, String advice, String rebacker) {
		Map map = new HashMap();
		map.put("yewlx", "jihsp");
		map.put("huitlc_id", huitlc_id);
		if (year != null) {
			map.put("year", year);
		} else {
			map.put("year", "-");
		}
		if (month != null) {
			map.put("month", month);
		} else {
			map.put("month", "-");
		}
		if (rebacker != null) {
			map.put("rebacker", rebacker);
		} else {
			map.put("rebacker", "-");
		}
		String lisID = Sequence.nextId();
		map.put("ID", lisID);
		map.put("diancxxb_id", diancxxb_id);
		map.put("advice", advice);
		rebackNianJhDao.addAdviceHistory(map);
		map.put("riq", year + "-01-01");
		map.put("diancxxb_id", diancxxb_id);
		map.put("zhuangt", zhuangt);
		rebackNianJhDao.updateNiandjhZhib(map);
		rebackNianJhDao.updateNiandjhZaf(map);
		rebackNianJhDao.updateNiandjhCaig(map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void HuitMonth(String zhuangt, String huitlc_id, String year,
			String month, String diancxxb_id, String advice, String rebacker) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("yewlx", "jihsp");
		map.put("huitlc_id", huitlc_id);
		map.put("year", year);
		map.put("month", month);
		map.put("rebacker", rebacker);
		map.put("diancxxb_id", diancxxb_id);
		map.put("advice", advice);
		String lisID = Sequence.nextId();
		map.put("ID", lisID);
		rebackYuedJhDao.addAdviceHistory(map);
		map.put("riq", year + "-01-01");
		map.put("diancxxb_id", diancxxb_id);
		map.put("diancid", diancxxb_id);
		map.put("zhuangt", zhuangt);//是否通过
		rebackYuedJhDao.updateYuedjhZhib(map);
		rebackYuedJhDao.updateYuedjhZaf(map);
		rebackYuedJhDao.updateYuedjhCaig(map);

	}




	
	@Override
	public void HuitHet(String zhuangt,String huitlc_id, String hetb_id, String rebacker,String advice) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String nowTime = df.format(new Date());// new Date()为获取当前系统时间
		map.put("rebackdate", nowTime);
		map.put("id", Sequence.nextId());
		map.put("hetb_id", hetb_id);
		map.put("huitlc_id", huitlc_id);
		map.put("rebacker", rebacker);
		map.put("advice", advice);
		rebackYuedJhDao.addHetAdviceHistory(map);
		rebackHetDao.rebackHet(map);
	}







}
