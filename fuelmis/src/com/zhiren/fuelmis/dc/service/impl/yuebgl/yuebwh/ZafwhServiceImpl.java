package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.ZafwhDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IZafwhService;
import com.zhiren.fuelmis.dc.utils.Sequence;

/** 
 * @author 陈宝露
 */
@Service
public class ZafwhServiceImpl implements IZafwhService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ZafwhDao zafwhDao;

	@Override
	public JSONArray getAll(Map<String, Object> map) {
		List<Map<String, Object>> list = zafwhDao.getAll(map);
		JSONArray result = JSONArray.fromObject(list);
		return result;
	}
	
	@Override
	public JSONArray getAll_new(Map<String, Object> map) {
		List<Map<String, Object>> list = zafwhDao.getAll_new(map);
		JSONArray result = JSONArray.fromObject(list);
		return result;
	}
	
	/**
	 * 获取杂费名称
	 */
	@Override
	public JSONArray getZfmingc() {
		List<Map<String, Object>> list = zafwhDao.getZfmingc();
		JSONArray result = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox("请选择",-1);
			result.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("MINGC"));
				result.add(combobox);
			}
		}
		return result;
	}

	@Override
	public void deletezf(List<Map<String, Object>> l) {
		for (int i = 0; i < l.size(); i++) {
			zafwhDao.deleteZf(l.get(i).get("ZFID"));
		}
	}

	@Override
	public void saveZf(List<Map<String, Object>> l) {
		
		for (int i = 0; i < l.size(); i++) {
			Map<String, Object> m = l.get(i);
			String id=Sequence.nextId();
//			String riq=DateUtil.getCurrentTime().substring(0,7);
			if(m.get("ZFID")!=null){
				zafwhDao.updateZf(m);
			}else{
				m.put("ID", id);
				m.put("dianc", 515);
				//m.put("riq",riq);
				m.put("BEIZ", (m.get("BEIZ")!=null?m.get("BEIZ").toString():"' '"));
				zafwhDao.insertZf(m);
			}
		}
	}

	@Override
	public List<Map<String,Object>> getZafeiByDiancidAndRiq(Map<String,Object> map) {
		return zafwhDao.getZafeiByDiancidAndRiq(map);
	}

	@Override
	public int DelZafeiByDiancidAndRiq(Map<String, Object> map) {
		return zafwhDao.DelZafeiByDiancidAndRiq(map);
	}

	@Override
	public int CopyZafeiData(Map<String, Object>map) {
		int  result;
		try{
			result = zafwhDao.CopyZafeiData(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return result;
	}

}
