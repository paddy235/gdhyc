package com.zhiren.fuelmis.dc.service.impl.zonghzs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zhiren.fuelmis.dc.service.zonghzs.ZonghzsDao;


public class ZonghzsServiceImpl implements ZonghzsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//当日供煤情况实时情况
	@Override
	public List<Map<String, Object>> getRanlxxgdts() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String  dangrgmqk_sql  = 
				"select wm_concat(jilmx) as laimxx\n" +
						"  from (select p.meikmc || ',' || sum(p.ches) || '车,' || sum(p.净重) || '吨;' as jilmx\n" + 
						"          from VM_PICXX p\n" + 
						"         where p.到货日期 = trunc(sysdate)\n" + 
						"         group by meikmc)";

//				"select wm_concat(jilmx) as laimxx \n" +
//						"from\n" + 
//						"(select p.供应商名称 || ',' || p.品种 || ',' || sum(p.ches) || '车,' || sum(p.净重) || '吨;' as jilmx\n" + 
//						"   from VM_PICXX p where p.到货日期 = trunc(sysdate)   group by 供应商名称,品种 )\n" + 
//						" ";
		Map<String, Object> dangrgmqk_list = jdbcTemplate.queryForMap(dangrgmqk_sql);		
		//map.putAll(dangrgmqk_list);
		if(dangrgmqk_list.get("LAIMXX")!=null){
			map.put("LAIMMX", dangrgmqk_list.get("LAIMXX").toString());
		}

		list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> getFenklmxxData() {
		// TODO Auto-generated method stub
		String dangjm_sql = "select sum(jingz) as dangrjm, sum(ches) dangrcs   --当日进煤量，共多少车\n" +
							"        from VM_FAHB  \n" +
							"    where daohrq = TRUNC(SYSDATE,'dd') --sysdate";
		String fenk_sql = "select m.mingc as fenkmc, p.mingc as meizmc,\n" +
							"       sum(f.jingz) jingz,sum(f.ches) ches, sum(f.yingk) yingk\n" + 
							"   from VM_FAHB f ,meikxxb m,pinzb p \n" + 
							"   where f.meikxxb_id=m.id\n" + 
							"         and f.pinzb_id=p.id\n" + 
							"         and f.daohrq = to_char(TRUNC(SYSDATE,'dd'),'yyyy-mm-dd') \n" + 
							"   group by m.mingc,p.mingc ";
		Map<String, Object> dangjm_result = jdbcTemplate.queryForMap(dangjm_sql);
		List<Map<String, Object>> fenk_result=jdbcTemplate.queryForList(fenk_sql);
		Map<String, Object> map = new HashMap<String, Object>();
		String str = "";
		for(int i=0; i<fenk_result.size(); i++){
			Map<String, Object> fenkmap=fenk_result.get(i);
			str += "分矿名称:" + fenkmap.get("FENKMC") + ", 煤种名称:" +fenkmap.get("MEIZMC") + ", 净重:" + fenkmap.get("JINGZ") + "(吨), \n " +
					" 车数:" + fenkmap.get("CHES") +  ", 盈亏:" + fenkmap.get("YINGK") + ";\n";
		}
		map.putAll(dangjm_result);
		map.put("FENKXX", str);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		result.add(map);
		return result;
	}

	@Override
	public List<Map<String, Object>> getFenklmcolumnData() {
		// TODO Auto-generated method stub
		String sql = "select sum(fh.jingz) as fenklm, mk.mingc as fenkmc\n" +
					"       from VM_FAHB fh, meikxxb mk\n" +
					"       where fh.meikxxb_id = mk.id and fh.daohrq = to_char(TRUNC(SYSDATE,'dd'),'yyyy-mm-dd')\n" +
					"       group by mk.mingc ";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Map<String, Object>> getKucjg(String yearmonth) {
		// TODO Auto-generated method stub
		String strDate[] = yearmonth.split("-");
		String year=strDate[0];
		String month=strDate[1];
		year="".equals(year)?String.valueOf((new Date()).getYear()):year;
		month="".equals(month)?String.valueOf((new Date()).getMonth()):month;
		String sql =
			"select * from (SELECT sum(yhc.kuc) as kuc, pz.mingc as pinz\n" +
			"  FROM yuehcb yhc, yuetjkjb kj, pinzb pz\n" + 
			" WHERE yhc.yuetjkjb_id = kj.id\n" + 
			"   and kj.pinzb_id = pz.id\n" + 
			"   and kj.diancxxb_id = 515\n" + 
			"   and yhc.fenx = '本月'\n" + 
			"   and kj.riq = to_char(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),'yyyy-mm-dd')\n" + 
			" group by pz.mingc) a where a.kuc>0 ";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> getKucqkqxData(String qsriq, String jzriq) {
		// TODO Auto-generated method stub
		qsriq="".equals(qsriq)?(new Date().toString()):qsriq;
		jzriq="".equals(jzriq)?(new Date().toString()):jzriq;
		String sql = "select  round(sum(nvl(shc.kuc,0))/10000, 2) as kucqk, round(sum(nvl(dc.jingjcml, 0))/10000, 2) as jingjkc, \n "+
				"     round(sum(nvl(dc.zhengccb, 0))/10000, 2) as zhengccb, to_char(shc.riq, 'mm\".\"dd') as riq\n" +
				"    from shouhcrbb shc, diancxxb dc\n" +
				"    where shc.diancxxb_id = dc.id and shc.riq between to_date('"+qsriq+"', 'yyyy/mm/dd') \n "+
				"          and (to_date('"+jzriq+"', 'yyyy/mm/dd')) \n" +
				"    group by shc.riq \n" +
				"    order by shc.riq";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> getLaihmqkqxData(String qsriq, String jzriq) {
		// TODO Auto-generated method stub
		qsriq="".equals(qsriq)?(new Date().toString()):qsriq;
		jzriq="".equals(jzriq)?(new Date().toString()):jzriq;
		String sql = "select round(sum(nvl(shc.dangrgm, 0))/10000, 2) as laim, \n " +
				" 	round(sum(nvl(fady, 0) + nvl(gongry, 0) + nvl(qity, 0) + nvl(feiscy, 0))/10000, 2) as haom, \n " +
				"	to_char(shc.riq, 'mm\".\"dd') as riq \n" +
				"    from shouhcrbb shc\n" + 
				"    where shc.riq between to_date('"+qsriq+"', 'yyyy/mm/dd') and to_date('"+jzriq+"', 'yyyy/mm/dd')\n" + 
				"    group by shc.riq \n" + 
				"    order by shc.riq";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> getKucmqx(String riq) {
		// TODO Auto-generated method stub
		riq="".equals(riq)?(new Date().toString()):riq;
		String blsql = "SELECT nvl(MAX(zhi),'*1') zhi FROM xitxxb WHERE mingc='库存煤热值曲线热值参数'";
		String bl_rlt=jdbcTemplate.queryForObject(blsql.toString(),String.class);
		String rezcs = bl_rlt;
		String sql = "SELECT M.MINGC AS MEIC,\n" +
			"          (case when K.JINML<1000 then k.jinml*5 else k.jinml end) AS JINM,\n" + 
			"          (case when K.HAOYL<1000 then k.haoyl*5 else k.haoyl end) AS HAOM,\n" + 
			"          (case when k.kuc>100000 then k.kuc/5 else k.kuc end) as CUNM,\n" + 
			"          K.KUCRZ"+rezcs+" AS KUCRZ,\n" + 
			"          K.KUCBMDJ AS KUCBMDJ,\n" + 
			"          (case when k.jinml<1000 then '/5' else '/1' end) as jmxs,\n" + 
			"          (case when k.haoyl<1000 then '/5' else '/1' end) as hmxs,\n" + 
			"         (case when k.kuc>100000 then '*5' else '*1' end) as cmxs\n" + 
			"  FROM KUCMJGB K, MEICB M\n" + 
			" WHERE K.MEICB_ID = M.ID\n" + 
			"  AND RIQ = to_char(to_date('"+riq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> getLaihcrbGridData(String riq) {
		//riq = riq.substring(0, 10);
		riq="".equals(riq)?(new Date().toString()):riq;
		String[] shouhcrbb_zid ={"dangrgm", "(fady + gongry + qity + feiscy)"}; //"fadl","gongrl"
		String str = "";
		String sql = "";
		for(int i=0; i<shouhcrbb_zid.length; i++){
			str = shouhcrbb_zid[i];
			String tiaojsql = "select decode('"+str+"', 'dangrgm', '来煤量', '(fady + gongry + qity + feiscy)', '耗煤量', 'kuc', '存煤量', 'fadl', '发电量', 'gongrl', '供热量' ) as laihc,\n" +
					"       "+str+" as dangrbq, --当日本期\n" +
					"       decode('"+str+"', 'dangrgm', 1, '(fady + gongry + qity + feiscy)', 2, 'kuc', 3, 'fadl', 4, 'gongrl', 5 ) as xuh, \n " + 
					"       decode((select "+str+" from shouhcrbb where riq = Add_months(date'"+riq+"', -12)), 0, 0,\n" +
					"         round(("+str+" - (select "+str+" from shouhcrbb where riq = Add_months(date'"+riq+"', -12)))/ \n" +
					"        (select "+str+" from shouhcrbb where riq = Add_months(date'"+riq+"', -12)),2))  as dangrtb,--当日同比\n" +
					"       decode((select "+str+" from shouhcrbb where riq = date'"+riq+"'-1 ), 0, 0,\n" +
					"         round(("+str+" - (select "+str+" from shouhcrbb where riq = date'"+riq+"'-1 ))/ \n" +
					"         (select "+str+" from shouhcrbb where riq = date'"+riq+"'-1),2)) as dangrhb, --当日环比\n" +
					"       (select sum("+str+") from shouhcrbb where riq between (next_day(date'"+riq+"', 1) - 6)\n" +
					"              and date'"+riq+"') as benzbq, --本周本期\n" +
					"       decode((select sum("+str+") from shouhcrbb where riq between (next_day(ADD_MONTHS(date'"+riq+"',-12), 1) - 7)\n" +
					"         and Add_months(date'"+riq+"', -12)), 0, 0,\n" +
					"         round(((select sum("+str+") from shouhcrbb where riq between (next_day(date'"+riq+"', 1) - 7) and date'"+riq+"') -\n" +
					"         (select sum("+str+") from shouhcrbb where riq between (next_day(ADD_MONTHS(date'"+riq+"',-12), 1) - 7)\n" +
					"           and Add_months(date'"+riq+"', -12))) /\n" +
					"         (select sum("+str+") from shouhcrbb where riq between (next_day(ADD_MONTHS(date'"+riq+"',-12), 1) - 6)\n" +
					"           and Add_months(date'"+riq+"', -12)),2))  as benztb, --本周同比\n" +
					"      decode((select sum("+str+") from shouhcrbb where riq between (next_day(date'"+riq+"', 1) - 13) and (date'"+riq+"' - 7)), 0, 0,\n" +
					"          round(((select sum("+str+") from shouhcrbb where riq between (next_day(date'"+riq+"', 1) - 6) and date'"+riq+"') -\n" +
					"            (select sum("+str+") from shouhcrbb where riq between (next_day(date'"+riq+"', 1) - 13) and (date'"+riq+"' - 7)))/\n" +
					"            (select sum("+str+") from shouhcrbb where riq between (next_day(date'"+riq+"', 1) - 13) and (date'"+riq+"' - 7)),2))\n" +
					"            as benzhb,--本周环比((本周数据-上周数据)/上周数据)\n" +
					"      (select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(date'"+riq+"',-1))+1)\n" +
					"               and (date'"+riq+"')) as benybq, --本月本期\n" +
					"      decode((select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(Add_months(date'"+riq+"', -12),-1))+1) and (Add_months(date'"+riq+"', -12))), 0, 0,\n" +
					"        round(((select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(date'"+riq+"',-1))+1) and (date'"+riq+"')) -\n" +
					"         (select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(Add_months(date'"+riq+"', -12),-1))+1) and (Add_months(date'"+riq+"', -12))))/\n" +
					"         (select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(Add_months(date'"+riq+"', -12),-1))+1) and (Add_months(date'"+riq+"', -12))),2))\n" +
					"        as benytb, --本月同比\n" +
					"      decode((select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(date'"+riq+"',-2))+1) and (Add_months(date'"+riq+"',-1))), 0, 0,\n" +
					"        round(((select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(date'"+riq+"',-1))+1) and (date'"+riq+"'))-\n" +
					"          (select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(date'"+riq+"',-2))+1) and (Add_months(date'"+riq+"',-1))))/\n" +
					"          (select sum("+str+") from shouhcrbb where riq between (last_day(Add_months(date'"+riq+"',-2))+1) and (Add_months(date'"+riq+"',-1))),2) )\n" +
					"          as benyhb --本月环比\n" +
					" from shouhcrbb\n" +
					" where riq = date'"+riq+"' \n";
				sql += " union \n" + tiaojsql ;
			}
			String zhixsql = sql.trim().substring(5) + " union \n" +
						" select decode('kuc', 'dangrgm', '来煤量', '(fady + gongry + qity + feiscy)', '耗煤量', 'kuc', '存煤量', 'fadl', '发电量', 'gongrl', '供热量' ) as laihc,\n" +
						"       kuc as dangrbq, --当日本期\n" +
						"       decode('kuc', 'dangrgm', 1, '(fady + gongry + qity + feiscy)', 2, 'kuc', 3, 'fadl', 4, 'gongrl', 5 ) as xuh,\n" +
						"        decode((select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)), 0, 0,\n" +
						"         round((kuc - (select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)))/\n" +
						"        (select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)),2))  as dangrtb,--当日同比\n" +
						"       decode((select kuc from shouhcrbb where riq = date'"+riq+"'-1 ), 0, 0,\n" +
						"         round((kuc - (select kuc from shouhcrbb where riq = date'"+riq+"'-1 ))/\n" +
						"         (select kuc from shouhcrbb where riq = date'"+riq+"'-1),2)) as dangrhb, --当日环比\n" +
						"       kuc as benzbq, --本周本期\n" +
						"         decode((select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)), 0, 0,\n" +
						"         round((kuc - (select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)))/\n" +
						"        (select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)),2)) as benztb, --本周同比\n" +
						"          decode((select kuc from shouhcrbb where riq = date'"+riq+"'-1 ), 0, 0,\n" +
						"         round((kuc - (select kuc from shouhcrbb where riq = date'"+riq+"'-1 ))/\n" +
						"         (select kuc from shouhcrbb where riq = date'"+riq+"'-1),2))   as benzhb,--本周环比((本周数据-上周数据)/上周数据)\n" +
						"         kuc as benybq, --本月本期\n" +
						"            decode((select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)), 0, 0,\n" +
						"         round((kuc - (select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)))/\n" +
						"        (select kuc from shouhcrbb where riq = Add_months(date'"+riq+"', -12)),2)) as benytb, --本月同比\n" +
						"          decode((select kuc from shouhcrbb where riq = date'"+riq+"'-1 ), 0, 0,\n" +
						"         round((kuc - (select kuc from shouhcrbb where riq = date'"+riq+"'-1 ))/\n" +
						"         (select kuc from shouhcrbb where riq = date'"+riq+"'-1),2)) as benyhb --本月环比\n" +
						" from shouhcrbb\n" +
						"where riq = date'"+riq+"'\n" +
						"order by xuh";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		System.out.println(zhixsql.toString());
		list = jdbcTemplate.queryForList(zhixsql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> getLaimxx(String riq) {
		// TODO Auto-generated method stub
		riq="".equals(riq)?(new Date().toString()):riq;
		String sql = 
				"select rownum as id, gongys,\n" +
						"                   pinz,\n" + 
						"             nvl(rez,0) as rez,\n" + 
						"             nvl(meij,0) as meij,\n" + 
						"             nvl(round(decode(rez, 0, 0, (meij / 1.17 + yunj * 0.93) / rez * 29.271),2),0) as biaomdj,\n" + 
						"             nvl(dangrlm,0) as dangrlm,\n" + 
						"             nvl(leijlm,0) as leijlm,\n" + 
						"             nvl(yuejhl,0) as yuejhl,\n" + 
						"             nvl(round(jind, 2),0) as jind\n" + 
						"              from (select decode(grouping(gys.mingc), 1, '合计', gys.mingc) as gongys,\n" + 
						"                           pz.mingc as pinz,\n" + 
						"                            round(decode(sum(shc.laimsl),0,0,sum(shc.rez * shc.laimsl) / sum(shc.laimsl)),2) as rez,\n" + 
						"                            round(decode(sum(shc.laimsl),0,0,sum(shc.meij * shc.laimsl) / sum(shc.laimsl)),2) as meij,\n" + 
						"                            round(decode(sum(shc.laimsl),0,0,sum(shc.yunj * shc.laimsl) / sum(shc.laimsl)),2) as yunj,\n" + 
						"                           sum(shc.laimsl) as dangrlm,\n" + 
						"                           sum(leij.leijlm) as leijlm,\n" + 
						"                           sum(leij.shul) as yuejhl,\n" + 
						"                           round(decode(sum(nvl(leij.shul, 0)),0,0,sum(leij.leijlm) / sum(nvl(leij.shul, 0))*100 ),2) as jind\n" + 
						"                      from (select *\n" + 
						"                              from shouhcfkb shc\n" + 
						"                             where shc.riq = to_date('"+riq+"', 'yyyy-mm-dd')\n" + 
						"                               /*and shc.diancxxb_id = 515*/) shc,\n" + 
						"                           (select decode(jih.gys,null,leij.gys,jih.gys) gys,\n" + 
						"                                   decode(jih.pz,null,leij.pz,jih.pz) pz,\n" + 
						"                                   leij.leijlm,\n" + 
						"                                   jih.shul\n" + 
						"                              from (select shc.gongysb_id as gys,\n" + 
						"                                           shc.pinzb_id as pz,\n" + 
						"                                           sum(laimsl) as leijlm\n" + 
						"                                      from shouhcfkb shc\n" + 
						"                                     where shc.riq >= trunc(to_date('"+riq+"', 'yyyy-mm-dd'), 'mm')\n" + 
						"                                       and shc.riq <= to_date('"+riq+"', 'yyyy-mm-dd')\n" + 
						"                                       /*and shc.diancxxb_id = 515*/\n" + 
						"                                     group by gongysb_id, meikxxb_id, pinzb_id) leij full join\n" + 
						"                                   (select gongysb_id gys,\n" + 
						"                                           meikxxb_id mk,\n" + 
						"                                           pinzb_id pz,\n" + 
						"                                           sum(jih_sl) shul\n" + 
						"                                      from yuedjh_caig\n" + 
						"                                     where riq = trunc(to_date('"+riq+"', 'yyyy-mm-dd'), 'mm')\n" + 
						"                                     group by gongysb_id, meikxxb_id, pinzb_id) jih on leij.gys=jih.gys and leij.pz = jih.pz\n" + 
						"                             /*where jih.gys=leij.gys(+)\n" + 
						"                               and leij.pz = jih.pz(+)*/) leij,\n" + 
						"                           gongysb gys,\n" + 
						"                           pinzb pz\n" + 
						"                     where leij.gys = gys.id\n" + 
						"                       and leij.pz = pz.id\n" + 
						"                       and shc.gongysb_id(+) = leij.gys\n" + 
						"                       and shc.pinzb_id(+) = leij.pz\n" + 
						"                     group by rollup((gys.mingc, pz.mingc))\n" + 
						"                     order by grouping(gys.mingc) desc,yuejhl,jind,gys.mingc,pinz) sr";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	
	@Override
	public List<Map<String, Object>> getRucbmdj(String riq, String jzriq) {
		// TODO Auto-generated method stub
		riq="".equals(riq)?(new Date().toString()):riq;
		jzriq="".equals(jzriq)?(new Date().toString()):jzriq;
		String sql = "select a.meik meik,\n" +
			"       a.price price,\n" + 
			"       round(decode(a.rez, 0, 0, a.price_bhs * 29.271 / a.rez), 2) priceb,             --这句话有变更\n" + 
			"       a.laimsl laimsl\n" + 
			"  from (select mk.mingc as meik,\n" + 
			"               nvl(round(decode(sum(shc.laimsl),\n" + 
			"                                0,\n" + 
			"                                0,\n" + 
			"                                sum((shc.meij + shc.yunj) * shc.laimsl) /\n" + 
			"                                sum(shc.laimsl)),\n" + 
			"                         2),\n" + 
			"                   0) as price,\n" + 
			"                   nvl(round(decode(sum(shc.laimsl),                                    --增加这行\n" + 
			"                                0,                                                      --增加这行\n" + 
			"                                0,                                                      --增加这行\n" + 
			//"                                sum((shc.meij/1.17 + shc.yunj*0.93) * shc.laimsl) /     --增加这行\n" + 
			"                        sum((shc.meij-shc.meijs + shc.yunj-shc.yunjs) * shc.laimsl) / " +
			"                                sum(shc.laimsl)),                                       --增加这行\n" + 
			"                         2),                                                            --增加这行\n" + 
			"                   0) as price_bhs,                                                     --增加这行\n" + 
			"               round(decode(sum(shc.laimsl),\n" + 
			"                            0,\n" + 
			"                            0,\n" + 
			"                            sum(shc.rez * shc.laimsl) / sum(shc.laimsl)),\n" + 
			"                     2) as rez,\n" + 
			"               round(sum(shc.laimsl), 3) as laimsl\n" + 
			"          from shouhcfkb shc, meikxxb mk\n" + 
			"         where shc.meikxxb_id = mk.id\n" + 
			"   and shc.riq <= to_date('"+jzriq+"', 'yyyy/mm/dd')\n" + 
			"   and shc.riq >= to_date('"+riq+"', 'yyyy/mm/dd')\n" + 
			"         group by mk.mingc) a order by priceb";
		//println("入厂标煤单价" + sql)
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> getRuctrmj(String riq, String jzriq) {
		// TODO Auto-generated method stub
		riq="".equals(riq)?(new Date().toString()):riq;
		jzriq="".equals(jzriq)?(new Date().toString()):jzriq;
		String sql = "select to_char(leij.riq,'mm\".\"dd') as Ructrmjriq, nvl(dangr.meij,0) as Ructrmjprice,\n" +
				" nvl(leij.meij,0) as Ructrmjpriceb\n" + 
				"  from (select round(decode(sum(decode((shc.meij+shc.yunj), 0, 0, shc.laimsl)), 0,0,sum((shc.meij+shc.yunj) * decode((shc.meij+shc.yunj), 0, 0, shc.laimsl)) /\n" + 
				"                            sum(decode((shc.meij+shc.yunj), 0, 0, shc.laimsl))),2) as meij,shc.riq as riq\n" + 
				"          from shouhcfkb shc\n" + 
				"         where shc.riq <= to_date('"+jzriq+"', 'yyyy/mm/dd')\n" + 
				"           and shc.riq >= to_date('"+riq+"', 'yyyy/mm/dd')\n" + 
				"         group by shc.riq) dangr,\n" + 
				"       (select riq,dangrdj,round_new(\n" + 
				"        ( select sum(case when shouhcfkb.riq > shc.riq then 0 else shouhcfkb.laimsl end  *  case when shouhcfkb.riq > shc.riq then 0 else (shouhcfkb.meij+shouhcfkb.yunj) end)\n" + 
				"         / decode(sum(case when shouhcfkb.riq > shc.riq then 0 else shouhcfkb.laimsl end),0,1,sum(case when shouhcfkb.riq > shc.riq then 0 else shouhcfkb.laimsl end))\n" + 
				"         from shouhcfkb\n" + 
				"         where shouhcfkb.riq <= shc.riq\n" + 
				"         and shouhcfkb.riq >=  to_date('"+riq+"', 'yyyy/mm/dd')\n" + 
				"         ) ,2) as meij\n" + 
				"from (select  t.riq ,sum(1)/1 as dangrdj from shouhcfkb t\n" + 
				"         where t.riq <= to_date('"+jzriq+"', 'yyyy/mm/dd')\n" + 
				"           and t.riq >= to_date('"+riq+"', 'yyyy/mm/dd')\n" + 
				"         group by t.riq) shc) leij\n" + 
				" where leij.riq = dangr.riq(+)\n" + 
				" order by leij.riq";
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> getDaysofRucbmdj(String riq, String jzriq) {
		// TODO Auto-generated method stub
		riq="".equals(riq)?(new Date().toString()):riq;
		jzriq="".equals(jzriq)?(new Date().toString()):jzriq;

		String sql = 
				"select meikdw as DaysofRucbmdjriq,\n" +
				"       jiesjg as DaysofRucbmdjprice,\n" + 
				"       decode(Qnetar, 0, 0, round(jiesjg * 29.271 / Qnetar, 2)) as DaysofRucbmdjpriceb\n" + 
				"  from (SELECT c.MEIKMC AS meikdw,\n" + 
				"               DECODE(SUM(c.maoz - c.piz - c.zongkd), 0, 0,\n" + 
				"                      round_new(SUM(h.Qnet_ar * (c.maoz - c.piz - c.zongkd)) /\n" + 
				"                                SUM(decode(nvl(h.qnet_ar, 0), 0,  0,  (c.maoz - c.piz - c.zongkd))),\n" + 
				"                                2)) AS Qnetar,\n" + 
				"               decode(SUM(c.maoz - c.piz - c.zongkd), 0, 0,\n" + 
				"                      round(sum((c.maoz - c.piz - c.zongkd) * js.jiesjg) /\n" + 
				"                            sum(decode(nvl(js.jiesjg, 0), 0, 0, (c.maoz - c.piz - c.zongkd))), 2)) jiesjg\n" + 
				"          FROM rl_ys_chepb c,\n" + 
				"               rl_ys_chepb_qc q,\n" + 
				"               rl_hy_huaydb h,\n" + 
				"               (select b.chepb_id,\n" + 
				"                       round(sum(a.jiessl * a.jiesjg) / sum(a.jiessl), 2) as jiesjg,\n" + 
				"                       sum(a.jiesje) as jiesje\n" + 
				"                  from rl_js_rijsdb a, gx_jiesdb_chepb b\n" + 
				"                 where a.id = b.jiesdb_id\n" + 
				"                 group by chepb_id) js,\n" + 
				"               (select * from gx_chep_caizhbmb where zhuanhlb_id = 1) gx1,\n" + 
				"               (select * from gx_chep_caizhbmb where zhuanhlb_id = 2) gx2\n" + 
				"         WHERE c.id = q.chepb_id\n" + 
				"           and c.id = js.chepb_id(+)\n" + 
				"           and gx2.mubbm = h.huaybm(+)\n" + 
				"           and c.samcode = gx1.yuanbm(+)\n" + 
				"           and gx1.mubbm = gx2.yuanbm(+)\n" + 
				"           and to_date(substr(q.qingcsj, 0, 10),'yyyy-mm-dd') between date'"+riq+"' and  date'"+jzriq+"'\n" + 
				"           and c.diancxxb_id = 515\n" + 
				"         GROUP BY c.MEIKMC)";


		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> gridLoad(String qsriq, String jzriq) {
		qsriq="".equals(qsriq)?(new Date().toString()):qsriq;
		jzriq="".equals(jzriq)?(new Date().toString()):jzriq;

		String sql = "select rownum as id,gl.* from (SELECT decode(rc.lx,'bml','标煤量','stad','硫份','rez','热值','vdaf','挥发分')lx,\n" +
				"rc.rcdq,rc.rcdq-rc.rctq rctb,rc.rcdq-rc.rcsq rchb,rl.rldq,rl.rldq-rl.rltq rltb,rl.rldq-rl.rlsq rlhb,\n" +
				"rc.rcdq-rl.rldq czdq,(rc.rcdq-rc.rctq)-(rl.rldq-rl.rltq) cztb,  (rc.rcdq-rc.rcsq)-(rl.rldq-rl.rlsq)czhb\n" +
				"FROM\n" +
				"(SELECT sr.lx,\n" +
				"SUM(DECODE(sr.zt,'dq',sr.zhi,NULL)) AS rcdq,\n" +
				"SUM(DECODE(sr.zt,'tq',sr.zhi,NULL)) AS rctq,\n" +
				"SUM(DECODE(sr.zt,'sq',sr.zhi,NULL)) AS rcsq FROM\n" +
				"(SELECT 'dq' zt, 'rez' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(QNET_AR, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * QNET_AR) /SUM(DECODE(QNET_AR, 0, 0, F.JINGZ))),2) QNET_AR\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"   AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'dq' zt, 'stad' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(stad, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * stad) /SUM(DECODE(stad, 0, 0, F.JINGZ))),2) stad\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"   AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'dq' zt, 'vdaf' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(vdaf, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * vdaf) /SUM(DECODE(vdaf, 0, 0, F.JINGZ))),2) vdaf\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"   AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'dq' zt, 'bml' lx,\n" +
				"NVL((SELECT\n" +
				"      round(SUM(decode(F.Qnet_Ar,0,0,F.JINGZ *F.Qnet_Ar/ 29.271))) bml\n" +
				"  FROM VM_FAHB F \n" +
				" WHERE DAOHRQ BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"   AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"--tq\n" +
				"SELECT 'tq' zt, 'rez' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(QNET_AR, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * QNET_AR) /SUM(DECODE(QNET_AR, 0, 0, F.JINGZ))),2) QNET_AR\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'tq' zt, 'stad' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(stad, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * stad) /SUM(DECODE(stad, 0, 0, F.JINGZ))),2) stad\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'tq' zt, 'vdaf' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(vdaf, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * vdaf) /SUM(DECODE(vdaf, 0, 0, F.JINGZ))),2) vdaf\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'tq' zt, 'bml' lx,\n" +
				"NVL((SELECT\n" +
				"      round(SUM(decode(Qnet_Ar,0,0,F.JINGZ * Qnet_Ar /29.271))) bml\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"--sq\n" +
				"SELECT 'sq' zt, 'rez' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(QNET_AR, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * QNET_AR) /SUM(DECODE(QNET_AR, 0, 0, F.JINGZ))),2) QNET_AR\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'sq' zt, 'stad' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(stad, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * stad) /SUM(DECODE(stad, 0, 0, F.JINGZ))),2) stad\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'sq' zt, 'vdaf' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(vdaf, 0, 0, F.JINGZ)),0,0,SUM(F.JINGZ * vdaf) /SUM(DECODE(vdaf, 0, 0, F.JINGZ))),2) vdaf\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'sq' zt, 'bml' lx,\n" +
				"NVL((SELECT\n" +
				"      ROUND(SUM(decode(f.Qnet_Ar, 0, 0, F.JINGZ * f.Qnet_Ar/29.271)),0) bml\n" +
				"  FROM VM_FAHB F\n" +
				" WHERE DAOHRQ BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual) sr\n" +
				"GROUP BY sr.lx)rc,\n" +
				"--rl\n" +
				"(SELECT lsr.lx,\n" +
				"SUM(DECODE(lsr.zt,'dq',lsr.zhi,NULL)) AS rldq,\n" +
				"SUM(DECODE(lsr.zt,'tq',lsr.zhi,NULL)) AS rltq,\n" +
				"SUM(DECODE(lsr.zt,'sq',lsr.zhi,NULL)) AS rlsq FROM\n" +
				"(SELECT 'dq' zt, 'rez' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.QNET_AR, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.QNET_AR) /SUM(DECODE(Zl.QNET_AR, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) QNET_AR\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'dq' zt, 'stad' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.stad, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.stad) /SUM(DECODE(Zl.stad, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) stad\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"   AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'dq' zt, 'vdaf' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.vdaf, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.vdaf) /SUM(DECODE(Zl.vdaf, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) vdaf\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"   AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'dq' zt, 'bml' lx,\n" +
				"NVL((SELECT\n" +
				"round(SUM(decode(Zl.QNET_AR,0,0,(hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)*Zl.QNET_AR/ 29.271))) bml\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
				"   AND to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"--tq\n" +
				"SELECT 'tq' zt, 'rez' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.QNET_AR, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.QNET_AR) /SUM(DECODE(Zl.QNET_AR, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) QNET_AR\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'tq' zt, 'stad' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.stad, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.stad) /SUM(DECODE(Zl.stad, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) stad\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'tq' zt, 'vdaf' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.vdaf, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.vdaf) /SUM(DECODE(Zl.vdaf, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) vdaf\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'tq' zt, 'bml' lx,\n" +
				"NVL((SELECT\n" +
				"round(SUM(decode(Zl.QNET_AR,0,0,(hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)*Zl.QNET_AR/ 29.271))) bml\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -12),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"--sq\n" +
				"SELECT 'sq' zt, 'rez' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.QNET_AR, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.QNET_AR) /SUM(DECODE(Zl.QNET_AR, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) QNET_AR\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'sq' zt, 'stad' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.stad, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.stad) /SUM(DECODE(Zl.stad, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) stad\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'sq' zt, 'vdaf' lx,\n" +
				"NVL((SELECT\n" +
				"round(DECODE(SUM(DECODE(Zl.vdaf, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy))),0,0,SUM((hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy) * Zl.vdaf) /SUM(DECODE(Zl.vdaf, 0, 0, (hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)))),2) vdaf\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual\n" +
				"UNION\n" +
				"SELECT 'sq' zt, 'bml' lx,\n" +
				"NVL((SELECT\n" +
				"round(SUM(decode(Zl.QNET_AR,0,0,(hy.fadhy+hy.gongrhy+hy.qity+hy.feiscy)*Zl.QNET_AR/ 29.271))) bml\n" +
				"FROM VM_MEIHYB hy,rulmzlb zl WHERE hy.rulmzlb_id=zl.id\n" +
				"AND hy.rulrq BETWEEN to_char(ADD_MONTHS(to_date('"+qsriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')\n" +
				"   AND to_char(ADD_MONTHS(to_date('"+jzriq+"', 'yyyy/mm/dd'), -1),'yyyy-mm-dd')),0)zhi\n" +
				"FROM dual) lsr\n" +
				"GROUP BY lsr.lx) rl\n" +
				"WHERE rl.lx=rc.lx\n" +
				"ORDER BY rc.lx) gl";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> chartLoad(String qsriq, String jzriq) {
		// TODO Auto-generated method stub
		qsriq="".equals(qsriq)?(new Date().toString()):qsriq;
		jzriq="".equals(jzriq)?(new Date().toString()):jzriq;
		String sql= "select to_char(to_date(riq.riq,'yyyy-mm-dd'),'MM.dd') as riq,\n" +
					"       nvl(rl.meil,0) rulml,\n" +
					"       nvl(rl.rez,0) as rulrz,\n" +
					"       nvl(rc.meil,0) rucml,\n" +
					"       nvl(rc.rez,0) as rucrz\n" +
					"  from (select hy.rulrq riq,\n" +
					"               sum(hy.fadhy + hy.gongrhy + hy.qity + hy.feiscy) as meil,\n" +
					"               round(decode(sum((hy.fadhy + hy.gongrhy + hy.qity + hy.feiscy)), \n" +
					"                            0,\n" +
					"                            0,\n" +
					"                            sum((hy.fadhy + hy.gongrhy + hy.qity + hy.feiscy) *\n" +
					"                                zl.qnet_ar) /\n" +
					"                     sum(hy.fadhy + hy.gongrhy + hy.qity + hy.feiscy)),\n" +
					"                     2) as rez\n" +
					"          from VM_MEIHYB hy, rulmzlb zl\n" +
					"         where hy.rulmzlb_id = zl.id\n" +
					"           and hy.rulrq <= to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
					"           and hy.rulrq > to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
					"         group by hy.rulrq) rl,\n" +
					"       (select fh.daohrq,\n" +
					"               sum(jingz) meil,\n" +
					"               round(decode(sum((fh.jingz)),\n" +
					"                            0,\n" +
					"                            0,\n" +
					"                            sum((fh.jingz) * fh.qnet_ar) / sum(fh.jingz)),\n" +
					"                     2) as rez\n" +
					"          from VM_FAHB fh \n" +
					"         where fh.daohrq <= to_char(to_date('"+jzriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd')\n" +
					"           and fh.daohrq > to_char(to_date('"+qsriq+"', 'yyyy/mm/dd'),'yyyy-mm-dd') \n" +
					"         group by fh.daohrq) rc,\n" +
					"       (select to_char(to_date('"+jzriq+"', 'yyyy/mm/dd') - ROWNUM + 1,'yyyy-mm-dd') riq\n" +
					"          from dual\n" +
					"        connect by rownum <= (to_date('"+jzriq+"', 'yyyy/mm/dd')-to_date('"+qsriq+"', 'yyyy/mm/dd'))) riq\n" +
					" where riq.riq = rc.daohrq(+) \n" +
					"   and riq.riq = rl.riq(+) order by riq.riq";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> bingtkj(String yearmonth) {
		// TODO Auto-generated method stub
		String strDate[] = yearmonth.split("-");
		String year=strDate[0];
		String month=strDate[1];
		year="".equals(year)?String.valueOf((new Date()).getYear()):year;
		month="".equals(month)?String.valueOf((new Date()).getMonth()):month;
//		if(year==null||"".equals(year)||year=="null"||month==null||"".equals(month)) {
//			SimpleDateFormat sdfy=new SimpleDateFormat("yyyy");
//			SimpleDateFormat sdfm=new SimpleDateFormat("MM");
//			year=sdfy.format(new Date());
//			month=sdfm.format(new Date());
//		}
		String sql = "select sum(hc.jingz) as shul, kj.mingc as koujmc\n" +
				"  from VM_FAHB hc, jihkjb kj\n" +
				" where hc.jihkjb_id = kj.id\n" +
				" and hc.daohrq between to_char(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),'yyyy-mm-dd') and to_char(add_months(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),1)-1,'yyyy-mm-dd') "+
				" group by kj.mingc";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> bingtgys(String yearmonth) {
		// TODO Auto-generated method stub
		String strDate[] = yearmonth.split("-");
		String year=strDate[0];
		String month=strDate[1];
		year="".equals(year)?String.valueOf((new Date()).getYear()):year;
		month="".equals(month)?String.valueOf((new Date()).getMonth()):month;
//		if(year==null||"".equals(year)||year=="null"||month==null||"".equals(month)) {
//			SimpleDateFormat sdfy=new SimpleDateFormat("yyyy");
//			SimpleDateFormat sdfm=new SimpleDateFormat("MM");
//			year=sdfy.format(new Date());
//			month=sdfm.format(new Date());
//		}
		String sql = "select sum(hc.jingz) as shul, gys.mingc as gongysmc\n" +
				"  from VM_FAHB hc, gongysb gys\n" +
				" where hc.gongysb_id = gys.id\n" +
				" and hc.daohrq between to_char(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),'yyyy-mm-dd') and to_char(add_months(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),1)-1,'yyyy-mm-dd') "+
				" group by gys.mingc";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> bingtmk(String yearmonth) {
		// TODO Auto-generated method stub
		String strDate[] = yearmonth.split("-");
		String year=strDate[0];
		String month=strDate[1];
		year="".equals(year)?String.valueOf((new Date()).getYear()):year;
		month="".equals(month)?String.valueOf((new Date()).getMonth()):month;
//		if(year==null||"".equals(year)||year=="null"||month==null||"".equals(month)) {
//			SimpleDateFormat sdfy=new SimpleDateFormat("yyyy");
//			SimpleDateFormat sdfm=new SimpleDateFormat("MM");
//			year=sdfy.format(new Date());
//			month=sdfm.format(new Date());
//		}
		String sql = "select sum(hc.jingz) as shul, mk.mingc as meikmc\n" +
				"  from VM_FAHB hc, meikxxb mk\n" +
				" where hc.meikxxb_id = mk.id\n" +
				" and hc.daohrq between to_char(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),'yyyy-mm-dd') and to_char(add_months(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),1)-1,'yyyy-mm-dd') "+
				" group by mk.mingc";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

	@Override
	public List<Map<String, Object>> bingtmz(String yearmonth) {
		// TODO Auto-generated method stub
		String strDate[] = yearmonth.split("-");
		String year=strDate[0];
		String month=strDate[1];
		year="".equals(year)?String.valueOf((new Date()).getYear()):year;
		month="".equals(month)?String.valueOf((new Date()).getMonth()):month;
//		if(year==null||"".equals(year)||year=="null"||month==null||"".equals(month)) {
//			SimpleDateFormat sdfy=new SimpleDateFormat("yyyy");
//			SimpleDateFormat sdfm=new SimpleDateFormat("MM");
//			year=sdfy.format(new Date());
//			month=sdfm.format(new Date());
//		}
		String sql = "select sum(hc.jingz) as shul, pz.mingc as pinzmc\n" +
				"  from VM_FAHB hc,pinzb pz\n" +
				" where hc.pinzb_id = pz.id\n" +
				" and hc.daohrq between to_char(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),'yyyy-mm-dd') and to_char(add_months(to_date('"+year+"-"+month+"-01','yyyy-mm-dd'),1)-1,'yyyy-mm-dd') "+
				" group by pz.mingc";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//System.out.println(sql.toString());
		list = jdbcTemplate.queryForList(sql.toString());
		return list;
	}

}
