package com.zhiren.fuelmis.dc.service.impl.caiygl.heby;
import java.util.List;
import java.util.Map;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhiren.fuelmis.dc.dao.caiygl.heby.HebyDao;
import com.zhiren.fuelmis.dc.service.caiygl.heby.IHebyService;

@Repository
public class HebyServiceImpl implements IHebyService {
	@Autowired 
	private HebyDao hebyDao;

	@Override
	public List<Map<String, Object>> getSamcodeList(String riq) {
		// TODO Auto-generated method stub
		return hebyDao.getSamcodeList(riq);
	}

	@Override
	public List<Map<String, Object>> getList(String riq) {
		// TODO Auto-generated method stub
		return hebyDao.getList(riq);
	}

	@Override
	public void updateList(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			hebyDao.updateList(list.get(i));
		}
		
	}

	@Override
	public void updateCancelList(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> map=list.get(i);
			hebyDao.updateCancelList(map);	
		}
	}

	
}
