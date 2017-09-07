package com.zhiren.fuelmis.dc.service.impl.jih;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.NiandjhzhibycDao;
import com.zhiren.fuelmis.dc.service.jih.INiandjhzhibycService;
@Service
public class NiandjhzhibycService implements INiandjhzhibycService {
	@Autowired
	private NiandjhzhibycDao niandjhzhibycDao;
	@SuppressWarnings("rawtypes")
	@Override
	public List getJihzbdyb() {
		return niandjhzhibycDao.getJihzbdyb();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getZhiFromniandjhzhib(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.getZhiFromniandjhzhib(map);
	}
	@Override
	public int getIdByRiqAndDiancid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.getIdByRiqAndDiancid(map);
	}
	@Override
	public int updateniandjhzhib(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.updateniandjhzhib(map);
	}
	@Override
	public int addniandjhzhib(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.addniandjhzhib(map);
	}
	@Override
	public int delByRiqAndDiancid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.delByRiqAndDiancid(map);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getLastYearData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.getLastYearData(map);
	}
	@Override
	public int DelThisYearData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.DelThisYearData(map);
	}
	@Override
	public int CopyLastMonthData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return niandjhzhibycDao.CopyLastMonthData(map);
	}
	@Override
	public int updateThreeDate(Map<String, Object> map) {
		int  result;
		try{
			result = niandjhzhibycDao.updateThreeDate(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
}
