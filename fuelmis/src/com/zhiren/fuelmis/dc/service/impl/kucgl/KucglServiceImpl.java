package com.zhiren.fuelmis.dc.service.impl.kucgl;


import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiren.fuelmis.dc.dao.rulgl.KucglDao;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.IKucglService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author 刘志宇
 */
@Service
public class KucglServiceImpl implements IKucglService {

	@Autowired
	private KucglDao kucglDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Map<String, Object>> getKuczz() {

		return kucglDao.getKuczz();
	}

	/**
	 * 库存组织
	 */
	@Override
	public int insert(Map<String, Object> map) {
		int ret = -1;
		try {

			String id = Sequence.nextId();
			map.put("id", id);
			ret = kucglDao.insert(map);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public int update(Map<String, Object> map) {
		int ret = -1;
		try {

			ret = kucglDao.update(map);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public List<Map<String, Object>> getKucwz() {
		// TODO Auto-generated method stub
		return kucglDao.getKucwz();
	}

	@Override
	public int insertKucwz(Map<String, Object> map) {
		int ret = -1;
		try {

			String id = Sequence.nextId();
			map.put("id", id);
			ret = kucglDao.insertKucwz(map);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public int updateKucwz(Map<String, Object> map) {
		int ret = -1;
		try {

			ret = kucglDao.updateKucwz(map);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public List<Map<String, Object>> getKuaijqdy() {
		// TODO Auto-generated method stub
		return kucglDao.getKuaijqdy();
	}

	@Override
	public void saveKuaijqdy(List<Map<String, Object>> list) {
		for (Map<String,Object> map:list) {
			if(map.get("ID")==null){
				String id = Sequence.nextId();
				map.put("ID", id);
				kucglDao.insertKuaijqdy(map);
			}else {
				kucglDao.updateKuaijqdy(map);
			}
		}
	}


	/**
	 * 库存组织关系
	 */
	@Override
	public List<Map<String, Object>> getGuanl() {
		List<Map<String, Object>> list=kucglDao.getGuanl();
//		for (Map<String, Object> map : list) {
//			List<Map<String, Object>> kuaijqList=kucglDao.getKuaijqListByKuczzId(map.get("KUCZZ_ID"));
//			map.put("kuaijqList",kuaijqList);
//		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getKuaijqList() {
		// TODO Auto-generated method stub
		return kucglDao.getKuaijqList();
	}

	@Override
	public List<Map<String, Object>> getKuczzList() {
		// TODO Auto-generated method stub
		return kucglDao.getKuczzList();
	}

	@Override
	public void saveGuanl(List<Map<String, Object>> list,Renyxx user) {
		for (Map<String, Object> map : list) {
			if (map.get("ID") != null) {
				kucglDao.updateGuanl(map);
			} else {
				map.put("ID", Sequence.nextId());
				kucglDao.insertGuanl(map);
				map.put("CREATED_BY_USERID", user.getId());
				map.put("CREATED_BY_USERNAME", user.getMingc());
				String kuaijrq=kucglDao.getkuaijrqById(map.get("KUAIJQ_ID"));
				map.put("lastMonth", DateUtil.getLastMonthString(kuaijrq));
				kucglDao.insertMonthTotal(map);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getWeiz() {
		return kucglDao.getWeiz();
	}

	@Override
	public List<Map<String, Object>> getFuKuczzList(String fuid) {
		// TODO Auto-generated method stub
		return kucglDao.getFuKuczzList(fuid);
	}

	@Override
	public int updateWeiz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return kucglDao.updateWeiz(map);
	}

	@Override
	public int insertWeiz(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int ret = -1;
		try {
			String id = Sequence.nextId();
			map.put("id", id);
			ret = kucglDao.insertWeiz(map);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return ret;
	}

	@Override
	public List<Map<String, Object>> getKucftList() {
		List<Map<String, Object>> list = kucglDao.getKucftList();
		if (list.size() == 0) {
			list = kucglDao.getKucftListFromKucwl();
		}
		return list;
	}

	@Override
	public void saveKucftList(List<Map<String, Object>> list) {
		for (Map<String, Object> m : list) {
			if (m.get("ID") != null) {
				kucglDao.updateKucftList(m);
			} else {
				m.put("ID", Sequence.nextId());
				kucglDao.insertKucftList(m);
			}
		}
	}

	@Override
	public List<Map<String, Object>> getChurkd(Map<String, Object> map) {
		return kucglDao.getChurkd(map);
	}

	@Override
	public void saveChurkd(List<Map<String, Object>> list, Renyxx user) {


		for (Map<String, Object> m : list) {
			m.put("CAOZR",user.getQuanc());
			m.put("CAOZRID",user.getId());
			Object yewlx=m.get("YEWLX");
			Object yewrq=m.get("YEWRQ");
			if (m.get("ID") != null) {
				kucglDao.updateChurkd(m);
			} else {
				m.put("ID", Sequence.nextId());
				Map<String,Object> bianhMap=jdbcTemplate.queryForMap("SELECT\n" +
						"  decode((select leixdm from GY_DM_CHURKYWLXDMB where id="+yewlx+"), '1', 'RKD-', 'CKD-') || RUKDBH RUKDBH,\n" +
						"  HANGH\n" +
						"FROM (\n" +
						"  SELECT\n" +
						"    nvl(substr(max(rukdbh), length(max(rukdbh)) - 10, 11), replace('"+yewrq+"', '-', '') || '000') + 1 RUKDBH,\n" +
						"    nvl(max(hangh), 0) + 1                                                                              HANGH\n" +
						"  FROM rl_kc_churkdb\n" +
						"  WHERE substr(rukdbh, 5, 8) = replace('"+yewrq+"', '-', '')\n" +
						")");
				m.put("RUKDBH",bianhMap.get("RUKDBH"));
				m.put("HANGH",bianhMap.get("HANGH"));
				kucglDao.insertChurkd(m);
			}
		}
	}

	@Override
	public void delChurkd(List<Object> ids) {
		for (Object id : ids) {
			kucglDao.delChurkd(id);
		}

	}

	@Override@Transactional
	public void yuemgz(Map<String, Object> map) {
		kucglDao.updateMonthTotal(map);
		kucglDao.updateChukdSub(map);
		kucglDao.updateChurkmxhzb(map);
	}

	@Override
	public String getChukdbh(Map<String, Object> map) {
		String chukdbh = kucglDao.getChukdbh(map);
		return chukdbh;
	}

}
