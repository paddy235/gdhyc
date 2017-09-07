package com.zhiren.fuelmis.dc.service.impl.jih;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.jih.YuedjhzhibycDao;
import com.zhiren.fuelmis.dc.service.jih.IYuedjhzhibycService;
@Service
public class YuedjhzhibycService implements IYuedjhzhibycService {
	@Autowired
	private YuedjhzhibycDao yuedjhzhibycDao;
	@SuppressWarnings("rawtypes")
	@Override
	public List getJihzbdyb() {
		return yuedjhzhibycDao.getJihzbdyb();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getZhiFromYuedjhzhib(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.getZhiFromYuedjhzhib(map);
	}
	@Override
	public int getIdByRiqAndDiancid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.getIdByRiqAndDiancid(map);
	}
	@Override
	public int updateYuedjhzhib(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.updateYuedjhzhib(map);
	}
	@Override
	public int addYuedjhzhib(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.addYuedjhzhib(map);
	}
	@Override
	public int delByRiqAndDiancid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.delByRiqAndDiancid(map);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List getLastMonthData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.getLastMonthData(map);
	}
	@Override
	public int DelThisMonthData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.DelThisMonthData(map);
	}
	@Override
	public int CopyLastMonthData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return yuedjhzhibycDao.CopyLastMonthData(map);
	}
	@Override
	public int updateThreeDate(Map<String, Object> map) {
		int  result;
		try{
			result = yuedjhzhibycDao.updateThreeDate(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}
}
