package com.zhiren.fuelmis.dc.service.impl.kucgl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.dao.kucgl.KucglCommonDao;
import com.zhiren.fuelmis.dc.service.kucgl.IKucglCommonService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author 陈宝露
 */
@Service
public class KucglCommonServiceImpl implements IKucglCommonService {

	@Autowired
	private KucglCommonDao kucglDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int rukjz(List<Long> idLst) {
		// TODO Auto-generated method stub
		int result = 0;
		if (idLst != null && idLst.size() > 0) {
			for (int i = 0; i < idLst.size(); i++) {
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", idLst.get(i));
					List<Map<String, Object>> list = kucglDao.getChurkd(map);
					if (list != null && list.size() > 0) {
						map = list.get(0);
						result += kucglDao.rukjz(map);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public int kucye(List<Long> idLst) {
		// TODO Auto-generated method stub
		int result = 0;
		if (idLst != null && idLst.size() > 0) {
			for (int i = 0; i < idLst.size(); i++) {
				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", idLst.get(i));
					List<Map<String, Object>> mxLst = kucglDao.getKucmx(map);
					if (mxLst != null && mxLst.size() > 0) {
						map.clear();
						map.put("kuczz", mxLst.get(0).get("KUCZZ"));
						map.put("kucwz", mxLst.get(0).get("KUCWZ"));
						map.put("kucwl", mxLst.get(0).get("KUCWL"));
						map.put("huoz", mxLst.get(0).get("HUOZ"));
						map.put("zuz", mxLst.get(0).get("ZUZ"));

						List<Map<String, Object>> hzLst = kucglDao
								.getKuchz(map);
						if (hzLst != null) {
							if (hzLst.size() == 0) {
								result += kucglDao.insertKuchz(mxLst.get(0));
							} else if (hzLst.size() == 1) {
								result += kucglDao.updateKuchz(mxLst.get(0));
							} else {
								result = -1;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public int kucjz(String kuaijrq) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			if (kuaijrq != null) {
				// 查询库存明细
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("kuaijrq", kuaijrq);
				List<Map<String, Object>> mxLst = kucglDao.getKucmxByParams(map);
				
				// 插入库存汇总
				if (mxLst != null && mxLst.size() > 0) {
					for (int i = 0; i < mxLst.size(); i++) {
						Map<String, Object> mxMap = mxLst.get(i);
						result += jdbcTemplate
								.update("insert into RL_KC_KUCHZB(ID,KUCZZ,KUCWZ,KUCWL,HUOZ,ZUZ,LEIX,SHUL,JINE,KUAIJRQ,CAOZSJ) values ("
										+ Sequence.nextId()
										+ ","
										+ mxMap.get("KUCZZ")
										+ ","
										+ mxMap.get("KUCWZ")
										+ ","
										+ mxMap.get("KUCWL")
										+ ","
										+ mxMap.get("HUOZ")
										+ ","
										+ map.get("ZUZ")
										+ ",'期末',"
										+ mxMap.get("SHUL")
										+ ","
										+ mxMap.get("JINE")
										+ ",'"
										+ kuaijrq
										+ "','" + DateUtil.format(new Date()) + "'");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int openKuaijq(String kuaijrq) {
		// TODO Auto-generated method stub
		int result = 0;
		try{
			if (kuaijrq != null) {
				// 查询库存明细
				// 查询上一会计期
				String lastKuaijrq = jdbcTemplate
						.queryForObject(
								"select kuaijrq from RL_KC_KUAIJRQB where xuh = ((select xuh from RL_KC_KUAIJRQB where kuaijrq = '"
										+ kuaijrq + "')-1)", String.class);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("kuaijrq", lastKuaijrq);
				List<Map<String, Object>> mxLst = kucglDao.getKucmxByParams(map);
				
				// 插入库存汇总
				if (mxLst != null && mxLst.size() > 0) {
					for (int i = 0; i < mxLst.size(); i++) {
						Map<String, Object> mxMap = mxLst.get(i);
						
						result += jdbcTemplate
								.update("insert into RL_KC_KUCHZB(ID,KUCZZ,KUCWZ,KUCWL,HUOZ,ZUZ,LEIX,SHUL,JINE,KUAIJRQ,CAOZSJ) values ("
										+ Sequence.nextId()
										+ ","
										+ mxMap.get("KUCZZ")
										+ ","
										+ mxMap.get("KUCWZ")
										+ ","
										+ mxMap.get("KUCWL")
										+ ","
										+ mxMap.get("HUOZ")
										+ ","
										+ map.get("ZUZ")
										+ ",'期初',"
										+ mxMap.get("SHUL")
										+ ","
										+ mxMap.get("JINE")
										+ ",'"
										+ kuaijrq
										+ "','" + DateUtil.format(new Date()) + "'");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
