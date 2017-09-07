package com.zhiren.fuelmis.dc.service.impl.gongyspg.pingggl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.gongyspg.pingggl.IRizbpfService;
import com.zhiren.fuelmis.dc.utils.Sequence;

//import com.zhiren.fuelmis.dc.dao.gongyspg.pingggl.RizbpfDao;


import bsh.EvalError;
import bsh.Interpreter;

@Service
public class RizbpfServiceImpl implements IRizbpfService {

	// @Autowired
	// private RizbpfDao rizbpfDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getRizbpf(Map<String, Object> map) {
		// 单位条件判断
		// StringBuffer danwCondition = new StringBuffer();
		// if (getGongysValue().getId() == visit.getDiancxxb_id()) {
		// danwCondition.append("");
		// } else {
		// danwCondition.append(" and gys.id=" + getGongysValue().getId());
		// }
		List<Map<String, Object>> l = jdbcTemplate.queryForList("select id,zhibmc,zhibdm,zhibdw,beiz,leib from RL_GYS_ZHIBDYB order by id");// 得到指标定义表中启用状态的指标。

		String sql = "select jihlm.jhid,jihlm.jfid,jihlm.gysid,jihlm.mingc,jihlm.riq,";
		String where = " where 1=1 ";
		String sqlView = "";
		int flag_c = 1;
		for (Map<String, Object> m : l) {
			sqlView += " \n (select rjfmx.id,nvl(rigmjfb_id,0) rigmjfb_id,zbdy.zhibdm,nvl(jif,0) jif,rjfmx.beiz,rjfmx.leix from rl_gys_Rigmjfmxb rjfmx,rl_gys_zhibdyb zbdy where rjfmx.zhibdm(+)=zbdy.zhibdm and zbdy.zhibdm='"
					+ m.get("ZHIBDM") + "') jf" + flag_c + ", ";
			where += " and jf" + flag_c + ".rigmjfb_id(+)=jihlm.jfid \n";
			if (m.get("LEIB").toString().equals("1")) {
				sql += "getgongfhyz('" + m.get("ZHIBDM") + "',jihlm.riq,jihlm.gysid) as gongfzb" + flag_c
						+ ",getChangfhyz('" + m.get("ZHIBDM") + "',jihlm.riq,jihlm.gysid) as changfzb" + flag_c + ",jf"
						+ flag_c + ".jif as jif" + flag_c + ",";
			} else if (m.get("LEIB").toString().equals("2")) {
				sql += "jihlm.jihml as gongfzb" + flag_c + ",jihlm.shijml as changfzb" + flag_c + ",jf" + flag_c
						+ ".jif as jif" + flag_c + ",";
			}
			flag_c++;
		}

		sql += "\n jihlm.tsjf tsjf,jihlm.jif zongdf,fabsj ";
		sqlView += " \n ( select gys.id gysid,rjh.id jhid,nvl(rjf.id,0) jfid,gys.mingc mingc,rjh.riq,rjh.jihml,nvl(fh.laiml,0) shijml, tsjf.jif tsjf, rjf.jif jif ,rjf.shenhsj as fabsj from rl_gys_rigmjhb rjh,rl_gys_rigmjfb rjf,(select rigmjfb_id, sum(jif) as jif from rl_gys_rigmjfmxb where zhibdm = 'TSJF' group by rigmjfb_id, zhibdm) tsjf,gongysb gys,rl_gys_fahb fh where rjh.riq=rjf.riq(+) and rjh.gongysb_id=rjf.gongysb_id(+) and rjf.id = tsjf.rigmjfb_id(+) and rjh.gongysb_id=gys.id and rjh.gongysb_id=fh.gongysb_id(+) and rjh.riq=fh.daohrq(+) "
				// + danwCondition
				+ " and rjh.riq between '" + map.get("sDate") + "'	and '" + map.get("eDate") + "' ) jihlm ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql + " from " + sqlView + where + " order by jihlm.riq desc,jihlm.mingc");
		return list;
	}

	// 月评分是完成，如果月评分计算完成，日评分不应再发生变化
	private boolean MonthPinfIsCompute(long gongysb_id, String riq) {
		boolean blnMonthPinfIsCompute = true;
		List<Map<String, Object>> l = jdbcTemplate.queryForList("select jf.lursj ,ht.gongysb_id,ht.kaisrq,ht.jiesrq\n"
				+ "from rl_gys_yuegmjfb jf,rl_gys_hetb ht\n" + "where jf.hetb_id=ht.id\n"
				+ "       and substr(ht.kaisrq,0,10)<='" + riq + "'\n" + "and substr(ht.jiesrq,0,10)>='" + riq
				+ "'\n" + "       and ht.gongysb_id=" + gongysb_id);

		if (l.size() == 0) {
			blnMonthPinfIsCompute = false;
		}
		return blnMonthPinfIsCompute;
	}

	public String jis(List<Map<String, Object>> list,Renyxx renyxx) {
		/*
		 * 1,往rigmjfb中添加数据； 2,往rigmjfmxb中添加数据； 3,修改rigmjfb中的分值；
		 */
		String strErrorMsg = "";
		StringBuffer sql = new StringBuffer("begin \n ");
		for (Map<String, Object> m : list) {
			long gongysb_id = Long.parseLong(m.get("GYSID").toString());// 供应商
			String riq = m.get("RIQ").toString();
			if (MonthPinfIsCompute(gongysb_id, riq)) {
				strErrorMsg = strErrorMsg + "供应商:" + m.get("MINGC") + " " + riq + "<br>";
			} else {
				long rigmjfb_id = Long.parseLong(Sequence.nextId());

				// 检查当前是否有日供煤积分（因为可能存在特殊加分情况）
				Map<String, Object> mid = jdbcTemplate.queryForMap("select id from rl_gys_RIGMJFB where substr(riq,0,10) = '" + riq
						+ "' and gongysb_id = " + gongysb_id);
				if (mid.get("ID")!=null) {
					rigmjfb_id = Long.parseLong(mid.get("ID").toString());
				} else {
					// 日积分表，没有数据的情况创建该表
					sql.append("insert into rl_gys_rigmjfb(id,riq,gongysb_id,jif,rigmjhb_id)values(" + rigmjfb_id + ",'"
							+ riq + "'," + gongysb_id + ",0,(select id from rl_gys_rigmjhb where gongysb_id="
							+ gongysb_id + " and substr(riq,0,10)='" + riq + "',));\n");
				}
				// 此处将数量指标直接写在sql语句中。
				List<Map<String, Object>> l = jdbcTemplate.queryForList(
						"select pffa.gongysb_id,pfxz.zhibdm,pfxz.leix,pfxz.zhibgs,\n DECODE(zhibdm,'TZL',getTiaozl('C','"
								+ riq + "'," + gongysb_id + "),decode(getchangfhyz(zhibdm,'" + riq + "'," + gongysb_id
								+ "),null," + m.get("CHANGFZB1") + ",0," + m.get("CHANGFZB1") + ",getchangfhyz(zhibdm,'"
								+ riq + "'," + gongysb_id + "))) as changfzb,\n DECODE(zhibdm,'TZL',getTiaozl('K','"
								+ riq + "'," + gongysb_id + "),decode(getgongfhyz(zhibdm,'" + riq + "'," + gongysb_id
								+ "),null," + m.get("GONGFZB1") + ",0," + m.get("GONGFZB1") + ",getgongfhyz(zhibdm,'"
								+ riq + "'," + gongysb_id + "))) as kuangfzb,\n getZhibkhbz(zhibdm,'" + riq + "',"
								+ gongysb_id + ") as kaohbz,pfxz.Zhibfz as biaozf \n"
								+ "from rl_gys_pingffab pffa,rl_gys_pingffaxzb pfxz,\n(select pingffab_id from rl_gys_rigmjhb where substr(riq,0,10)= '"
								+ riq + "' and gongysb_id=" + gongysb_id + ") rjh "
								+ "\nwhere pffa.id=pfxz.pingffab_id(+) and pffa.id=rjh.pingffab_id "
								+ "and pffa.zhuangt=1\n" + "and pfxz.leix=0\n" + "and substr(pffa.qisrq,0,10)<='" + riq
								+ "' and substr(pffa.jiezrq,0,10)>='" + riq + "'");
				for (Map<String, Object> map : l) {
					String zhibdm = map.get("ZHIBDM").toString();
					int leix = Integer.parseInt(map.get("LEIX").toString());
					String zhibgs = map.get("ZHIBGS").toString();
					double changfzb = Double.parseDouble(map.get("CHANGFZB").toString());
					double kuangfzb = Double.parseDouble(map.get("KUANGFZB").toString());
					double zhibfz = 0;
					String strKaohbz = (Integer.parseInt(map.get("KAOHBZ").toString()) == 1 ? "是" : "否");
					double dblBiaozf = Double.parseDouble(map.get("BIAOZF").toString());
					Interpreter bsh = new Interpreter();
					try {
						if (strKaohbz.equals("是")) {
							bsh.set("CF", changfzb);
							bsh.set("KF", kuangfzb);
							bsh.set("指标考核标识", strKaohbz);
							bsh.set("指标标准分", dblBiaozf);
							bsh.eval(zhibgs);
							zhibfz = Double.parseDouble(bsh.get("result").toString());
						} else {
							// 如果指标免考，这给标准分
							zhibfz = dblBiaozf;
						}
					} catch (EvalError e) {
						e.printStackTrace();
					}
					// 清空rigmjfmxb原来的历史数据
					sql.append("delete from rl_gys_rigmjfmxb where rigmjfb_id = " + rigmjfb_id + " and zhibdm = '"
							+ zhibdm + "';\n");
					// 往rigmjfmxb中插入数据。
					sql.append("insert into rl_gys_rigmjfmxb(id,rigmjfb_id,zhibdm,jif,leix)values(" + Sequence.nextId()
							+ "," + rigmjfb_id + ",'" + zhibdm + "'," + zhibfz + "," + leix + ");\n");
				} // 遍历结束l
					// 更新rigmjfb中的分值
				sql.append(
						"update rl_gys_rigmjfb rjf set jif =(select sum(nvl(jif,0)) from rl_gys_rigmjfmxb where rigmjfb_id=rjf.id and leix<>1) where id="
								+ rigmjfb_id + "; \n");
			}
		}
		sql.append("\n end;");
		int flag = 0;
		if (sql.toString().length() > 15) {
			flag = jdbcTemplate.update(sql.toString());
		}
		String msg=null;
		if (flag > 0) {
			msg = "计算完成！！";
		}
		if (strErrorMsg.length() > 0) {
			msg = strErrorMsg + "计算失败！请在取消相应的月评分后，再进行此操作!";
		}
		return msg;
	}

	/*@Override
	public void fab(List<Map<String, Object>> list) {
		String sGongysb_id="0";
		StringBuffer sError=new StringBuffer();
		String riq="";
		StringBuffer sbsql=new StringBuffer("");
		for (Map<String,Object> m : list) {
			sGongysb_id= m.get("GYSID").toString();// 供应商
			riq = m.get("RIQ").toString();
			
			//SmsManager sm=new SmsManager();
			//发布评分信息后，更新发布信息
//			if (sm.FabRigmpfMessage(visit.getRenymc(), riq, sGongysb_id, cn)){
				sbsql.setLength(0);
				sbsql.append(" update RIGMJFB set shenhsj=sysdate,shenhr='").append(visit.getRenymc()).append("'" );
				sbsql.append(" where gongysb_id=").append(sGongysb_id);
				sbsql.append(" and riq=date'").append(riq).append("'");
				cn.getUpdate(sbsql.toString());
//			}else{
//				sError.append("供应商:").append(rs.getString("mingc")).append("日期:").append(riq).append("<br>");
//			}
		}
		if (sError.length()>0){
			setMsg(sError.append(" 发布失败！请检查联系人电话号码或是否已评分!").toString());
		}
	}
*/
	@Override
	public void fabCancel(List<String> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fab(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		
	}
}
