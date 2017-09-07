package com.zhiren.fuelmis.dc.service.impl.diaoygl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.diaoygl.DiaodjhwhDao;
import com.zhiren.fuelmis.dc.dao.diaoygl.RiddshDao;
import com.zhiren.fuelmis.dc.service.diaoygl.DiaodglService;
import com.zhiren.fuelmis.dc.service.diaoygl.IDiaodjhwhService;
import com.zhiren.fuelmis.dc.utils.RandomCode;
import com.zhiren.fuelmis.dc.utils.Sequence;


@Service
public class DiaodjhwhServiceImpl implements IDiaodjhwhService {
	@Autowired
	DiaodjhwhDao diaodjhwhDao;
	@Override
	public void insertDiaodjh(Map<String, Object> map) {
		List<Map<String,Object>> l = (List<Map<String, Object>>) map.get("diaodjhSubList");
		String diaodjhid=null;
		if(map.get("ID")==null){
			diaodjhid=Sequence.nextId();
			map.put("ID", diaodjhid);
			//1.向主表插入数据
			diaodjhwhDao.insertDiaodjh(map);
		}else{
			//1.向主表更新数据
			diaodjhid=map.get("ID").toString();
			diaodjhwhDao.updateDiaodjh(map);
		}
		//2.向子表插入数据
		for (int i = 0; i < l.size(); i++) {
			Map<String, Object> m = l.get(i);
			if(m.get("ID")==null){
				m.put("ID", Sequence.nextId());
				m.put("MAIN_ID",diaodjhid);
				diaodjhwhDao.insetDiaodjhSub(m);
			}else{
				diaodjhwhDao.updateDiaodjhSub(m);
			}
		}
	}
	@Override
	public List<Map<String, Object>> getDiaodjhList(Map<String, Object> map) {
		return diaodjhwhDao.getDiaodjhList(map);
	}
	@Override
	public Map<String, Object> getDiaodjh(String id) {
		//1.查询主表数据
		Map<String,Object> m=diaodjhwhDao.getDiaodjhMain(id);
		//2.查询子表数据
		List<Map<String,Object>> l=diaodjhwhDao.getDiaodjhSub(id);
		m.put("diaodjhSubList", l);
		return m;
	}
	@Override
	public void deleteDiaodjhSub(String id) {
		diaodjhwhDao.deleteDiaodjhSub(id);		
	}
	@Override
	public void deleteDiaodjh(String id) {
		diaodjhwhDao.deleteDiaodjhMain(id);
		List<String> idSubs = diaodjhwhDao.selectSubId(id);
		for (int i = 0; i < idSubs.size(); i++) {
			diaodjhwhDao.deleteDiaodjhSub(idSubs.get(i));
		}
	}
	@Override
	public String generateJihdh(String riq) {
		String jihdh=null;
		//1.查询计划单号
		String [] array=diaodjhwhDao.getJihdh(riq);
		while (true) {
			// 1.1产生一个两位随机数并生成一个新的单号
			String newJhdh = riq.replace("-", "") + RandomCode.getHexRadomCode(2);
			// 1.2判断该编码是否已经存在
			if(array!=null&&array.length!=0){
				boolean isExist = this.isExist(array, newJhdh);
				if (isExist == false) {
					jihdh=newJhdh;
					break;
				}
			}else{
				jihdh=newJhdh;
				break;
			}
		}
		return jihdh;
	}
	/**
	 * 判断数组中是否含有计划单号
	 * 
	 * @param array
	 *            数组
	 * @param newBianm
	 *            值
	 * @return true：存在， false：不存在。
	 */
	private boolean isExist(String[] array, String newJihdh) {
		boolean isExist = false;
		for (int i = 0; i < array.length; i++) {
			String jihdh = array[i];
				if (jihdh.equals(newJihdh)) {
					isExist = true;
					break;
				}
		}
		return isExist;
	}
}
