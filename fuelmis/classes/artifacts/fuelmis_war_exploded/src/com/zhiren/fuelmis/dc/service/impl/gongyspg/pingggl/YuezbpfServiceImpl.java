package com.zhiren.fuelmis.dc.service.impl.gongyspg.pingggl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zhiren.fuelmis.dc.entity.gongyspg.pingggl.Calculator;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.gongyspg.pingggl.IYuezbpfService;
import com.zhiren.fuelmis.dc.utils.Sequence;

//import com.zhiren.fuelmis.dc.dao.gongyspg.pingggl.RizbpfDao;
@Service
public class YuezbpfServiceImpl implements IYuezbpfService {

//	@Autowired
//	private RizbpfDao rizbpfDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getYuezbpf(Map<String, Object> map) {
		String sDate = map.get("sDate").toString();
		String eDate= map.get("eDate").toString();
		
		String gysCondition ="";
		
		if (!map.get("gongysid").toString().equals("-1")) {
            gysCondition=" and g.id= " + map.get("gongysid");
		}
		
		String sql="SELECT ht.id,jf.id AS jifb_id, g.mingc  gongysmc,g.id gongysid,ht.kaisrq,ht.jiesrq,ht.hetl,\n" +
                "             (  SELECT sum (c.maoz-c.piz-c.koud)\n" +
                "                FROM rl_ys_chepb c\n" +
                "                  INNER JOIN rl_ys_chepb_qc q ON c.id=q.chepb_id\n" +
                "                WHERE substr(q.qingcsj,0,10) BETWEEN ht.KAISRQ AND ht.JIESRQ\n" +
                "                      AND gongysb_id=g.id) AS laiml,jf.hetljf,\n" +
                "             jf.jif   AS rijf,  jf.zongf  , jf.shenhsj fabsj\n" +
                "FROM rl_gys_hetb ht,rl_gys_yuegmjfb jf,gongysb g\n" +
                "WHERE ht.id=jf.hetb_id(+)\n" +
                "      AND ht.gongysb_id=g.id\n" +
                "      AND substr(ht.jiesrq,0,10) BETWEEN '"+sDate+"' AND '"+eDate+"'\n" +gysCondition+
                "ORDER BY ht.kaisrq";

		List<Map<String, Object>> l = jdbcTemplate.queryForList(sql);
		return l;
	}
	@Override
	public String computeScore(List<Map<String,Object>> list,Renyxx lurry){
		String strMsg="";
		for (Map<String, Object> map : list) {
			if (map.get("FABSJ").toString().equals("null")||"".equals(map.get("FABSJ").toString())){
				strMsg=computeHetScore (map,lurry);
				strMsg=strMsg+map.get("GONGYSMC")+"有效期:"+map.get("KAISRQ")+"至"+map.get("JIESRQ")+"<br>";
			}else{
				strMsg=strMsg+map.get("GONGYSMC")+"有效期:"+map.get("KAISRQ")+"至"+map.get("JIESRQ")+"已发布,计算失败！"+"<br>";
			}	
		}
		return strMsg;
	}
	//计算一个合同的评分，保存评分信息
	private String computeHetScore(Map<String, Object> map,Renyxx lurry){
			double dblRipjf =0;
			double dblHetlpf=0;
			String sHetID=map.get("ID").toString();
			String sYuegmjfb_id=map.get("JIFB_ID").toString();
			String sGongsybID="";
			boolean blnHasPinfs=true;
			String msg="";
			//日评分平均
			StringBuffer sbsql = new StringBuffer();
			sbsql.append("select round_new(avg(JIF),2) as jif,sum(1) as tians \n");
			sbsql.append("from (SELECT GYS.ID, JF.RIQ, GYS.QUANC, SUM(JFMX.JIF) AS JIF \n");
			sbsql.append("        FROM rl_gys_RIGMJFB JF, rl_gys_RIGMJFMXB JFMX, GONGYSB GYS, rl_gys_rigmjhb jh, \n");
			sbsql.append("             (select id, kaisrq,jiesrq,gongysb_id from rl_gys_hetb \n");
			sbsql.append("              where id=").append(sHetID).append(") ht \n");
			sbsql.append("        WHERE JF.ID = JFMX.RIGMJFB_ID \n");
			sbsql.append("            AND JF.GONGYSB_ID = GYS.Id and jh.gongysb_id=jf.gongysb_id and jh.riq=jf.riq\n");
			sbsql.append("            AND GYS.ID = ht.gongysb_id \n");
			sbsql.append("            and substr(jf.riq,0,10)>=ht.kaisrq \n");
			sbsql.append("            and substr(jf.riq,0,10)<=ht.jiesrq \n");
			sbsql.append("        GROUP BY GYS.ID, GYS.QUANC, JF.RIQ) \n");
		
			Map<String,Object> m=jdbcTemplate.queryForMap(sbsql.toString());
			dblRipjf=Double.parseDouble(m.get("JIF").toString());
			//评分所需信息
			sbsql.setLength(0);
			sbsql.append("select ht.gongysb_id, ht.id, ht.hetl,fa.zhibgs,fa.zhibfz, \n");
			sbsql.append("   (select nvl(sum(laiml),0) \n");
			sbsql.append("    from rl_gys_fahb fh  \n");
			sbsql.append("    where fh.gongysb_id=ht.gongysb_id  \n");
			sbsql.append("        and fh.daohrq>=ht.kaisrq  \n");
			sbsql.append("        and fh.daohrq<=ht.jiesrq) as laiml \n");
			sbsql.append("from rl_gys_hetb ht,(select * from rl_gys_PINGFFAXZB where ZHIBDM=");
			sbsql.append("(select decode((select beiz from xitxxb where mingc='合同评分标题条件' and leib='月指标评分' and zhuangt=1)" +
							",null,'YHTL',(select beiz from xitxxb where mingc='合同评分标题条件' and leib='月指标评分' and zhuangt=1)) from dual)");
			sbsql.append(") fa \n");
			sbsql.append("where ht.id=").append(sHetID).append(" \n");
			sbsql.append("and ht.pingffab_id=fa.pingffab_id(+) \n");
			
			m=jdbcTemplate.queryForMap(sbsql.toString());
	
			//计算评分
			Calculator cl=new Calculator();
			if ("".equals(m.get("ZHIBGS"))){
				blnHasPinfs=false;
			}else{
				cl.setCf(Double.parseDouble(m.get("LAIML").toString()));
				cl.setKf(Double.parseDouble(m.get("HETL").toString()));
				cl.setSetBz(Double.parseDouble(m.get("ZHIBFZ").toString()));
				cl.setFormula(m.get("ZHIBGS").toString());	
				sGongsybID=m.get("GONGYSB_ID").toString();
			}
			if (blnHasPinfs){//没有合同评分公式
				if (cl.Compute()){
					dblHetlpf=cl.getResult();
				}
				//删除上次的评分信息
				if (!sYuegmjfb_id.equals("")){
					jdbcTemplate.update("delete from rl_gys_yuegmjfb where id="+sYuegmjfb_id);
				}
				
				sbsql.setLength(0);
				sbsql.append("INSERT INTO rl_gys_YUEGMJFB\n");
				sbsql.append( "  (ID, RIQ, GONGYSB_ID, JIF, LURSJ, LURRY, HETLJF,Zongf,Hetb_id)\n");
				sbsql.append( "VALUES ("+ Sequence.nextId() + ",");
				sbsql.append(" sysdate, " + sGongsybID + ", " + dblRipjf+ ",");
				sbsql.append( "sysdate, '" + lurry.getMingc() + "', "+ dblHetlpf +",");
				sbsql.append(dblRipjf+dblHetlpf+","+sHetID+")");
				jdbcTemplate.update(sbsql.toString());
				msg="计算成功！";
			}else{
				msg="没有找到月合同数量的的指标计算公式，计算失败!";
			}
			return msg;
		}
	@Override
	public void fab(List<String> list){
		StringBuffer sbIDs =new StringBuffer();
		for (String id : list) {
			if  (sbIDs.length()>0){
				sbIDs.append(",");
			}
			sbIDs.append(id);
		}
		if (sbIDs.length()>0){
			jdbcTemplate.update("update rl_gys_yuegmjfb set shenhsj=sysdate where id in("+sbIDs.toString()+")");
		} 
	}
	@Override
	public void fabCancel(List<Object> list){
		StringBuffer sbIDs =new StringBuffer();
		for (Object id : list) {
			if  (sbIDs.length()>0){
				sbIDs.append(",");
			}
			sbIDs.append(id);
		}
		if (sbIDs.length()>0){
			jdbcTemplate.update(" delete from  rl_gys_yuegmjfb  where id in("+sbIDs.toString()+")");
		} 
		
	}
}
