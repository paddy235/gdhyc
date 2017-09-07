package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.RucslDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRucslService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.TreeUtil;

/**
 * @author 陈宝露
 */
@Service
public class RucslServiceImpl implements IRucslService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RucslDao rucslDao;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public JSONObject getAll(Map<String, Object> map) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = rucslDao.getAll(map);
		jsonMap.put("data", list);

		JSONObject result = JSONObject.fromObject(jsonMap);
		return result;
	}

	@Override
	@Transactional
	public JSONArray createData(Map<String, Object> map) {
		String riq = map.get("riq").toString();
		String yuebksrq = "";
		String yuebjzrq = "";
		
		String sql_ybq = "select  y.yuebjq,y.yuebksrq,y.yuebjzrq  from yuebsjpzb  y where y.yuebjq = '" + map.get("riq") + "' ";
		
		List<Map<String, Object>> list_ybq = jdbcTemplate.queryForList(sql_ybq);
		if (list_ybq != null && list_ybq.size() > 0) {
			for (int i = 0; i < list_ybq.size(); i++) {
				yuebksrq = list_ybq.get(i).get("YUEBKSRQ").toString();
			    yuebjzrq = list_ybq.get(i).get("YUEBJZRQ").toString();	    
			}
		}		
		
		int intnianf = Integer.parseInt(riq.substring(0,4));
		int intYuef=Integer.parseInt(riq.substring(5,7));
		int shangyyf = intYuef==1?12: intYuef- 1 ;
		JSONArray jsonArray = new JSONArray();
		try {
			// 删除已经存在的数据
			jdbcTemplate.update("delete from yueslb where yuetjkjb_id in (select id from yuetjkjb where substr(riq,0,7) = '" + map.get("riq") + "' and diancxxb_id = " + map.get("dianc") + ")");
			jdbcTemplate.update("delete from yuetjkjb where riq = '" + map.get("riq") + "' and diancxxb_id =" + map.get("dianc"));
			// 查询入厂验收车信息
			String sql =  "select c.meikxxb_id,\n" +
					"       c.gongysb_id,\n" + 
					"       c.jihkjb_id,\n" + 
					"       c.pinzb_id,\n" + 
					"       c.yunsfsb_id,\n" + 
					"       round(sum(c.maoz - c.piz-c.zongkd),2) jingz,\n" + 
					"       round(sum(c.piaoz),2) piaoz\n" + 
					"  from rl_ys_chepb c, rl_ys_chepb_qc qc\n" + 
					" where C.ID = QC.CHEPB_ID\n" + 
					"   and substr(QC.qingcsj, 0, 10) between '" + yuebksrq + "' and  '" + yuebjzrq + "' \n" + 
					" group by c.gongysb_id,\n" + 
					"          c.jihkjb_id,\n" + 
					"          c.pinzb_id,\n" + 
					"          c.yunsfsb_id,\n" + 
					"          c.meikxxb_id\n" ;
				if (intYuef==1) {
					sql += "union\n" + 
							"select y.gongysb_id as meikxxb_id , y.gongysb_id,y.jihkjb_id, y.pinzb_id,  y.yunsfsb_id, 0 as jingz, 0 as piaoz\n" +
							"  from yuetjkjb y, yuehcb h\n" + 
							" where y.id = h.yuetjkjb_id\n" + 
							"   and to_date(riq,'yyyy-mm') = date'"+(intYuef==1?intnianf-1: intnianf)+"-"+shangyyf+"-1'\n" + 
							"   AND h.kuc <> 0\n" + 
							"   AND H.FENX = '本月'\n" + 
							"   and y.diancxxb_id = 515 \n" +
							"   and (diancxxb_id, gongysb_id, jihkjb_id, pinzb_id, yunsfsb_id) not in\n" + 
							"	(select distinct c.diancxxb_id, c.meikxxb_id, c.jihkjb_id,c.pinzb_id, c.yunsfsb_id\n" +
							"    	from rl_ys_chepb c, rl_ys_chepb_qc qc\n" + 
							"   where C.ID = QC.CHEPB_ID\n" + 
							"     	and substr(QC.qingcsj, 0, 10)  between '" + yuebksrq + "' and  '" + yuebjzrq + "' )";

				}else{
					
					sql +="union\n" + 
							"select y.gongysb_id,0 as gongysb_id ,y.jihkjb_id,y.pinzb_id,y.yunsfsb_id,0 as jingz,0 as piaoz\n" + 
							"  from yuetjkjb y\n" + 
							" where to_date(riq,'yyyy-mm') = date'"+intnianf+"-"+shangyyf+"-1' \n" + 
							"   and (diancxxb_id, gongysb_id, jihkjb_id, pinzb_id, yunsfsb_id) not in\n" + 
							"(select distinct c.diancxxb_id, c.meikxxb_id, c.jihkjb_id,c.pinzb_id, c.yunsfsb_id\n" +
							"    from rl_ys_chepb c, rl_ys_chepb_qc qc\n" + 
							"   where C.ID = QC.CHEPB_ID\n" + 
							"     and substr(QC.qingcsj, 0, 10)  between '" + yuebksrq + "' and  '" + yuebjzrq + "' )";
				}
					
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					/******* 生成Yuetjkjb Begin *************/
					String yuetjkjb_id = Sequence.nextId();
					String insertSql = "insert into yuetjkjb(ID,RIQ,DIANCXXB_ID,XUH,GONGYSB_ID,JIHKJB_ID,PINZB_ID,YUNSFSB_ID)"
							+ " values (" + yuetjkjb_id + ",'" + map.get("riq") + "'," + map.get("dianc") + ","
							+ "(select nvl(max(xuh)+1,1) from yuetjkjb),"
							+ list.get(i).get("MEIKXXB_ID") + ","
							+ list.get(i).get("JIHKJB_ID") + ","
							+ list.get(i).get("PINZB_ID") + ","
							+ list.get(i).get("YUNSFSB_ID") + ")";
					jdbcTemplate.update(insertSql);
					/******* 生成Yuetjkjb End *************/
					
					/******* 生成Yueslb Begin *************/
					//本月数据
					jdbcTemplate.update("insert into yueslb(ID,YUETJKJB_ID,FENX,JINGZ,BIAOZ,JIANJL,ZHUANGT) values "
							+ "(" + Sequence.nextId() + "," + yuetjkjb_id + ",'本月',"
							+ list.get(i).get("JINGZ") + ","
							+ list.get(i).get("PIAOZ") + ","
							+ list.get(i).get("JINGZ") + ",0)");
					
					//累计数据  (1月累计=1月本月，其他月份累计=上月累计+本月)
					Map<String, Object> maps = new HashMap<String, Object>();
					try {
						maps = jdbcTemplate.queryForMap("select * from yueslb where yuetjkjb_id in (select id from yuetjkjb where gongysb_id=" + list.get(i).get("MEIKXXB_ID")
										+ " and jihkjb_id=" + list.get(i).get("JIHKJB_ID")
										+ " and pinzb_id=" + list.get(i).get("PINZB_ID")
										+ " and yunsfsb_id=" + list.get(i).get("YUNSFSB_ID")
										+ " and substr(riq,0,7) = '" + DateUtil.getLastMonthString(riq) + "' \n"
										+ "and diancxxb_id = " + map.get("dianc")
										+ ") and fenx = '累计'");
					} catch (Exception e) {
						
					}	
					

					if (maps.size() == 0) {
						jdbcTemplate.update("insert into yueslb(ID,YUETJKJB_ID,FENX,JINGZ,BIAOZ,JIANJL,ZHUANGT) values "
										+ "(" + Sequence.nextId() + ","
										+ yuetjkjb_id + ",'累计',"
										+ list.get(i).get("JINGZ") + ","
										+ list.get(i).get("PIAOZ") + ","
										+ list.get(i).get("JINGZ") + ",0)");
					} else {
						if (intYuef==1) {
							jdbcTemplate.update("insert into yueslb(ID,YUETJKJB_ID,FENX,JINGZ,BIAOZ,YINGD,KUID,YUNS,ZONGKD,"
									+ "JIANJL,RUCTZL,YINGDZJE,KUIDZJE,SUOPSL,SUOPJE,ZHUANGT) values "
									+ "(" + Sequence.nextId() + ","
									+ yuetjkjb_id + ",'累计',"
									+ Double.parseDouble(list.get(i).get("JINGZ").toString()) + ","
									+ Double.parseDouble(list.get(i).get("PIAOZ").toString()) + ",0,0,0,0,"
									+ Double.parseDouble(list.get(i).get("JINGZ").toString()) + ",0,0,0,0,0," + "0)");
						}else{
							jdbcTemplate.update("insert into yueslb(ID,YUETJKJB_ID,FENX,JINGZ,BIAOZ,YINGD,KUID,YUNS,ZONGKD,"
									+ "JIANJL,RUCTZL,YINGDZJE,KUIDZJE,SUOPSL,SUOPJE,ZHUANGT) values "
									+ "("
									+ Sequence.nextId() + ","
									+ yuetjkjb_id + ",'累计',"
									+ (Double.parseDouble(maps.get("JINGZ").toString()) + Double.parseDouble(list.get(i).get("JINGZ").toString())) + ","
									+ (Double.parseDouble(maps.get("BIAOZ").toString()) + Double.parseDouble(list.get(i).get("PIAOZ").toString())) + ","
									+ maps.get("YINGD") + ","
									+ maps.get("KUID") + ","
									+ maps.get("YUNS") + ","
									+ maps.get("ZONGKD") + ","
									+ (Double.parseDouble(maps.get("JIANJL").toString()) + Double.parseDouble(list.get(i).get("JINGZ").toString())) + ","
									+ maps.get("RUCTZL") + ","
									+ maps.get("YINGDZJE") + ","
									+ maps.get("KUIDZJE") + ","
									+ maps.get("SUOPSL") + ","
									+ maps.get("SUOPJE") + "," + "0)");
						}
					}
					
					/******* 生成Yueslb End *************/					
				}
			}

			jsonArray.add(1);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			jsonArray.add(0);
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	@Transactional
	public JSONArray saveData(Map<String, Object> map) {
		String riq = map.get("riq").toString();
		int intnianf = Integer.parseInt(riq.substring(0,4));
		int intYuef=Integer.parseInt(riq.substring(5,7));
		int shangyyf = intYuef==1?12: intYuef- 1 ;
		JSONArray array = new JSONArray();
		JSONArray jsonArray = JSONArray.fromObject(map.get("rucslList"));
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Map<String, Object>> list = JSONArray.toList(jsonArray, Map.class);
		if (list != null) {
			try {
				for (int i = 0; i < list.size(); i++) {
					
					Map<String, Object> maps = list.get(i);
					TreeUtil.checkMapNull(maps, new String[]{"ID","YUETJKJB_ID","GONGYSB_ID","JIHKJB_ID","PINZB_ID","YUNSFSB_ID","FENX","JINGZ","BIAOZ","YINGD","ZHUANGT","KUID","YUNS","ZONGKD","JIANJL","RUCTZL","YINGDZJE","KUIDZJE","SUOPSL","SUOPJE"});
					if (Long.parseLong(maps.get("ID").toString()) > 0
							&& maps.get("FENX").equals("本月")) {
						jdbcTemplate.update("update yueslb set JINGZ="
								+ maps.get("JINGZ") + ",BIAOZ="
								+ maps.get("BIAOZ") + ",YINGD="
								+ maps.get("YINGD") + ",KUID="
								+ maps.get("KUID") + ",YUNS="
								+ maps.get("YUNS") + ",ZONGKD="
								+ maps.get("ZONGKD") + ",JIANJL="
								+ maps.get("JIANJL") + ",RUCTZL="
								+ maps.get("RUCTZL") + ",YINGDZJE="
								+ maps.get("YINGDZJE") + ",KUIDZJE="
								+ maps.get("KUIDZJE") + ",SUOPSL="
								+ maps.get("SUOPSL") + ",SUOPJE="
								+ maps.get("SUOPJE") + " where id = "
								+ maps.get("ID"));

						// 重新计算累计
						Map<String, Object> rucsl_last = new HashMap<String, Object>();
						try {
							rucsl_last = jdbcTemplate
									.queryForMap("select " +
											"nvl(id,0) id,\n" +
											"nvl(yuetjkjb_id,0) yuetjkjb_id,\n" +
											"nvl(fenx,0) fenx,\n" +
											"nvl(jingz,0) jingz,\n" +
											"nvl(biaoz,0) biaoz,\n" +
											"nvl(yingd,0) yingd,\n" +
											"nvl(kuid,0) kuid,\n" +
											"nvl(yuns,0) yuns,\n" +
											"nvl(koud,0) koud,\n" +
											"nvl(kous,0) kous,\n" +
											"nvl(kouz,0) kouz,\n" +
											"nvl(koum,0) koum,\n" +
											"nvl(zongkd,0) zongkd,\n" +
											"nvl(sanfsl,0) sanfsl,\n" +
											"nvl(jianjl,0) jianjl,\n" +
											"nvl(ructzl,0) ructzl, \n" +
											"nvl(zhuangt,0) zhuangt,\n" +
											"nvl(laimsl,0) laimsl,\n" +
											"nvl(yingdzje,0) yingdzje,\n" +
											"nvl(kuidzje,0) kuidzje,\n" +
											"nvl(suopsl,0) suopsl,\n" +
											"nvl(suopje,0) suopje\n" +
											"from yueslb where yuetjkjb_id in (select id from yuetjkjb where gongysb_id=" + maps.get("GID")
											+ " and jihkjb_id=" + maps.get("JID")
											+ " and pinzb_id=" + maps.get("PID")
											+ " and yunsfsb_id=" + maps.get("YID")
											+ " and substr(riq,0,7) = '" + DateUtil.getLastMonthFirstDay(sdf .parse(map.get("riq") .toString())).substring(0, 7)
											+ "' and diancxxb_id = " + map.get("dianc") + ") and fenx = '累计'");
						} catch (Exception e) {
						}

						Map<String, Object> rucsl_now = jdbcTemplate.queryForMap("select " +
										"nvl(id,0) id,\n" +
										"nvl(yuetjkjb_id,0) yuetjkjb_id,\n" +
										"nvl(fenx,0) fenx,\n" +
										"nvl(jingz,0) jingz,\n" +
										"nvl(biaoz,0) biaoz,\n" +
										"nvl(yingd,0) yingd,\n" +
										"nvl(kuid,0) kuid,\n" +
										"nvl(yuns,0) yuns,\n" +
										"nvl(koud,0) koud,\n" +
										"nvl(kous,0) kous,\n" +
										"nvl(kouz,0) kouz,\n" +
										"nvl(koum,0) koum,\n" +
										"nvl(zongkd,0) zongkd,\n" +
										"nvl(sanfsl,0) sanfsl,\n" +
										"nvl(jianjl,0) jianjl,\n" +
										"nvl(ructzl,0) ructzl, \n" +
										"nvl(zhuangt,0) zhuangt,\n" +
										"nvl(laimsl,0) laimsl,\n" +
										"nvl(yingdzje,0) yingdzje,\n" +
										"nvl(kuidzje,0) kuidzje,\n" +
										"nvl(suopsl,0) suopsl,\n" +
										"nvl(suopje,0) suopje\n" +
										"from yueslb where yuetjkjb_id in (select id from yuetjkjb where gongysb_id=" + maps.get("GID") + " \n"
										+ " and jihkjb_id=" + maps.get("JID") + " \n"
										+ " and pinzb_id=" + maps.get("PID") + " \n"
										+ " and yunsfsb_id=" + maps.get("YID") + " \n"
										+ " and substr(riq,0,7) = '" + map.get("riq").toString().substring(0,7) + "' \n"
										+ "and diancxxb_id = " + map.get("dianc") + ") and fenx='累计'");

						if (rucsl_last.size() == 0) {
							jdbcTemplate.update("update yueslb set JINGZ="
									+ maps.get("JINGZ") + ",BIAOZ="
									+ maps.get("BIAOZ") + ",YINGD="
									+ maps.get("YINGD") + ",KUID="
									+ maps.get("KUID") + ",YUNS="
									+ maps.get("YUNS") + ",ZONGKD="
									+ maps.get("ZONGKD") + ",JIANJL="
									+ maps.get("JIANJL") + ",RUCTZL="
									+ maps.get("RUCTZL") + ",YINGDZJE="
									+ maps.get("YINGDZJE") + ",KUIDZJE="
									+ maps.get("KUIDZJE") + ",SUOPSL="
									+ maps.get("SUOPSL") + ",SUOPJE="
									+ maps.get("SUOPJE") + " where id = "
									+ rucsl_now.get("ID"));
						} else {
							if (intYuef==1) {
								jdbcTemplate.update("update yueslb set JINGZ=" + Double.parseDouble(maps.get("JINGZ").toString()) + ",\n"
										+ " BIAOZ=" + Double.parseDouble(maps.get("BIAOZ").toString()) + ",\n"
										+ " YINGD=" + Double.parseDouble(maps.get("YINGD").toString()) + ",\n"
										+ " KUID=" + Double.parseDouble(maps.get("KUID").toString()) + ",\n"
										+ " YUNS=" + Double.parseDouble(maps.get("YUNS").toString()) + ",\n"
										+ " ZONGKD=" + Double.parseDouble(maps.get("ZONGKD").toString()) + ",\n"
										+ " JIANJL=" + Double.parseDouble(maps.get("JIANJL").toString()) + ",\n"
										+ " RUCTZL=" + Double.parseDouble(maps.get("RUCTZL").toString()) + ",\n"
										+ " YINGDZJE=" + Double.parseDouble(maps.get("YINGDZJE").toString()) + ",\n"
										+ " KUIDZJE=" + Double.parseDouble(maps.get("KUIDZJE").toString()) + ",\n"
										+ " SUOPSL=" + Double.parseDouble(maps.get("SUOPSL").toString()) + ",\n"
										+ " SUOPJE=" + Double.parseDouble(maps.get("SUOPJE").toString())
										+ " where id = " + rucsl_now.get("ID"));
							}else{
								jdbcTemplate.update("update yueslb set JINGZ=" + (Double.parseDouble(rucsl_last.get("JINGZ").toString()) + Double.parseDouble(maps.get("JINGZ").toString())) + ",\n"
										+ " BIAOZ=" + (Double.parseDouble(rucsl_last.get("BIAOZ").toString()) + Double.parseDouble(maps.get("BIAOZ").toString())) + ",\n"
										+ " YINGD=" + (Double.parseDouble(rucsl_last.get("YINGD").toString()) + Double.parseDouble(maps.get("YINGD").toString())) + ",\n"
										+ " KUID=" + (Double.parseDouble(rucsl_last.get("KUID").toString()) + Double.parseDouble(maps.get("KUID").toString())) + ",\n"
										+ " YUNS=" + (Double.parseDouble(rucsl_last.get("YUNS").toString()) + Double.parseDouble(maps.get("YUNS").toString())) + ",\n"
										+ " ZONGKD=" + (Double.parseDouble(rucsl_last.get("ZONGKD").toString()) + Double.parseDouble(maps.get("ZONGKD").toString())) + ",\n"
										+ " JIANJL=" + (Double.parseDouble(rucsl_last.get("JIANJL").toString()) + Double.parseDouble(maps.get("JIANJL").toString())) + ",\n"
										+ " RUCTZL=" + (Double.parseDouble(rucsl_last.get("RUCTZL").toString()) + Double.parseDouble(maps.get("RUCTZL").toString())) + ",\n"
										+ " YINGDZJE=" + (Double.parseDouble(rucsl_last.get("YINGDZJE").toString()) + Double.parseDouble(maps.get("YINGDZJE").toString())) + ",\n"
										+ " KUIDZJE=" + (Double.parseDouble(rucsl_last.get("KUIDZJE").toString()) + Double.parseDouble(maps.get("KUIDZJE").toString())) + ",\n"
										+ " SUOPSL=" + (Double.parseDouble(rucsl_last.get("SUOPSL").toString()) + Double.parseDouble(maps.get("SUOPSL").toString())) + ",\n"
										+ " SUOPJE=" + (Double.parseDouble(rucsl_last.get("SUOPJE").toString()) + Double.parseDouble(maps.get("SUOPJE").toString()))
										+ " where id = " + rucsl_now.get("ID"));
							}
							
						}
					}
				}
				array.add(1);
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				array.add(0);
				e.printStackTrace();
			}
		}
		return array;
	}

	@Override
	@Transactional
	public JSONArray delData(Map<String, Object> map) {
		
		JSONArray jsonArray = new JSONArray();
		try {
			jdbcTemplate
					.update("delete from yueslb where yuetjkjb_id in (select id from yuetjkjb where substr(riq,0,7) = '" + map.get("riq") + "' and diancxxb_id = " + map.get("dianc") + ")");
			jsonArray.add(1);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			jsonArray.add(0);
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONArray check(Map<String, Object> map) {
		
		JSONArray jsonArray = new JSONArray();
		try {
			int count = jdbcTemplate.queryForInt("select count(1) from yueslb where yuetjkjb_id in (select id from yuetjkjb where riq='"
							+ map.get("riq")
							+ "' and diancxxb_id = "
							+ map.get("dianc") + ")");
			if (count == 0) {
				jsonArray.add(1);
			} else {
				count = jdbcTemplate.queryForInt("select count(1) from yueslb where yuetjkjb_id in (select id from yuetjkjb where riq='"
								+ map.get("riq")
								+ "' and diancxxb_id = "
								+ map.get("dianc") + ") and zhuangt = 0");
				jsonArray.add(count);
			}
		} catch (Exception e) {
			jsonArray.add(1);
		}
		return jsonArray;
	}
}
