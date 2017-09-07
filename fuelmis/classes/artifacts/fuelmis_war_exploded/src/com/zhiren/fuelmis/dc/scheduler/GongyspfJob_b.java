package com.zhiren.fuelmis.dc.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhiren.fuelmis.dc.utils.Sequence;

import bsh.EvalError;
import bsh.Interpreter;


/**
 * @author 陈宝露
 */
/*@Component("gongyspfJob")*/
public class GongyspfJob_b { 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplateGddl;
	
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static String FormatDate(Date date) {
		String StrDate;
		try {
			StrDate = DATE_FORMAT.format(date);
		} catch (NullPointerException NPE) {
			StrDate = "1900-01-01";
			System.out.println("格式化日期为空！");
		} catch (Exception E) {
			StrDate = "1900-01-01";
			System.out.println("未知异常！");
		}
		return StrDate;
	}
	public void execute() {
		String diancxxb_id = "515";
		String riq = this.FormatDate(new Date());
//		String sql_tians ="select zhi from xitxxb where mingc='供应商接收时间调整' and leib = '供应商接口用'";
//
//		int tians = jdbcTemplate.queryForInt(sql_tians);//接收数据时天数调整
		int tians=3;
		//判断在此节点是否存在存合同有效期结束的合同
		String hetb_check = "select  h.id ,h.gongysb_id,h.pingffab_id ,h.kaisrq, \n"
				+ "		h.jiesrq \n"
				+ " from rl_gys_hetb h "; //where to_date(jiesrq,'yyyy-mm-dd')= DATE'"+ riq + "'-"+tians ;
		List lists = jdbcTemplate.queryForList(hetb_check);
		for(int j =0;j<lists.size();j++) {
			String hetb_id =((Map)lists.get(j)).get("id").toString();
			String gongysb_id = ((Map)lists.get(j)).get("gongysb_id").toString();
			String pingffab_id =((Map)lists.get(j)).get("pingffab_id").toString();
			String kaisrq =((Map)lists.get(j)).get("kaisrq").toString();
			String jiesrq =((Map)lists.get(j)).get("jiesrq").toString();
			boolean flag =  this.Jis(hetb_id,gongysb_id, pingffab_id,kaisrq,jiesrq,diancxxb_id );
			if (flag) {
				try {
					this.Fab(hetb_id,diancxxb_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void Fab(String hetb_id,String diancxxb_id) throws Exception  {
		StringBuffer sbIDs =new StringBuffer();
//		StringBuffer sb = new StringBuffer();
		String sql="select ht.id as hID,\n" +
				"        jf.id as jifb_id,\n" +
				"        gy.id as gongysb_id,\n" +
				"        ht.kaisrq as kaisrq,\n" +
				"        ht.jiesrq as jiesrq,\n" +
				"        ht.hetl as hetl,\n" +
				"       (select nvl(sum(c.JINGZ), 0)\n" +
				"          from VM_CHEPMX c\n" +
				"         where c.gongysb_id = ht.gongysb_id\n" +
				"           and substr(c.QINGCSJ,0,10) >= ht.kaisrq\n" +
				"           and substr(c.QINGCSJ,0,10) <= ht.jiesrq) as laiml,\n" +
				"        jf.hetljf hetljf,\n" +
				"        jf.jif as rijf,\n" +
				"        nvl(jf.hetzqtsjf,0) as hetzqtsjf,\n" +
				"        jf.zongf as zongf\n" +
				"  from rl_gys_hetb ht, rl_gys_yuegmjfb jf, gongysb gy\n" +
				" where ht.id = jf.hetb_id(+)\n" +
				"   and ht.gongysb_id = gy.id\n" +
				"   and ht.id = "+hetb_id;
//		StringBuffer fb = new StringBuffer("select 'hid='||ht.id as hID,");
//		fb.append("'jifb_id='||jf.id as jifb_id,");
//		fb.append("'gongysb_id='|| gy.mingc as gongysb_id,");
//		fb.append("'kaisrq='|| ht.kaisrq as kaisrq,");
//		fb.append("'jiesrq='|| ht.jiesrq as jiesrq,");
//		fb.append("'hetl='|| ht.hetl as hetl,");
//		fb.append("'laimsl='|| (select nvl(sum(laimsl),0)");
//		fb.append("from fahb fh where fh.gongysb_id=ht.gongysb_id and to_char(fh.daohrq,'yyyy-mm-dd')>=ht.kaisrq and  to_char(fh.daohrq,'yyyy-mm-dd')<=ht.jiesrq) as laiml,");
//		fb.append("'hetljf='|| jf.hetljf  hetljf ,");
//		fb.append("'rijf='|| jf.jif as rijf ,");
//		fb.append("'hetzqtsjf='||jf.hetzqtsjf as hetzqtsjf,");
//		fb.append("'zongf='|| jf.zongf as zongf");
//		fb.append(" from rl_gys_hetb ht,rl_gys_yuegmjfb jf,gongysb gy where ht.id=jf.hetb_id(+)and ht.gongysb_id=gy.id and ht.id=").append(hetb_id);

		List list  = jdbcTemplate.queryForList(sql);
		for(int k=0;k<list.size();k++){
			String jifb_id = ((Map)list.get(k)).get("JIFB_ID").toString();
			String gongysb_id = ((Map)list.get(k)).get("GONGYSB_ID").toString();
			String kaisrq = ((Map)list.get(k)).get("KAISRQ").toString();
			String jiesrq = ((Map)list.get(k)).get("JIESRQ").toString();
			String hetl = ((Map)list.get(k)).get("HETL").toString();
			String laiml =((Map)list.get(k)).get("LAIML").toString();
			String hetljf = ((Map)list.get(k)).get("HETLJF").toString();
			String rljf = ((Map)list.get(k)).get("RIJF").toString();
			String hetzqtsjf = ((Map)list.get(k)).get("HETZQTSJF").toString();
			String zongf = ((Map)list.get(k)).get("ZONGF").toString();
			jdbcTemplateGddl.update("delete from yuepfb  where jifb_id= " + jifb_id);
			sql = "insert into yuepfb(diancxxb_id,jifb_id,gongysb_id,kaisrq,jiesrq,hetl,laiml,hetljf,rljf,hetzqtsjf,zongf) "
					+ "values("
					+ diancxxb_id
					+ ",'"
					+ jifb_id
					+ "','"
					+ gongysb_id
					+ "',"
					+"to_date('"+kaisrq+"','yyyy-mm-dd')"
					+ ","
					+"to_date('"+jiesrq+"','yyyy-mm-dd')"
					+ ","
					+ hetl
					+ ","
					+ laiml
					+ ","
					+ hetljf
					+ ","
					+ rljf
					+ ","
					+ hetzqtsjf
					+ ","
					+ zongf
					+ ")";
			int result  = jdbcTemplateGddl.update(sql);
			if(result==-1){
				System.out.println("发布失败！");
			}else{
				System.out.println("发布成功！");
			}
		}
		if (sbIDs.length() > 0) {
			String sqls = " update rl_gys_yuegmjfb set shenhsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where id in("+ sbIDs.toString() + ")";
			jdbcTemplate.update(sqls);
		}
	}

	// 新建ID
	public String getNewID(String diancxxb_id) {
		int id = jdbcTemplate.queryForInt("select xl_xul_id.nextval id from dual");
		return diancxxb_id + id;
	}

	public boolean Jis(String hetb_id,String gongysb_id,String  pingffab_id,String hetksrq,String hetjsrq,String diancxxb_id ){
		//根据合同有效期，和供应商把来煤信息按照指标项进行加权
		boolean fabzt = false;
		StringBuffer sql = new StringBuffer("begin \n ");
		String pingffa = "select pffa.gongysb_id, z.zhibdm, pfxz.leix, pfxz.zhibgs,\n" +
				" GETLAIMXXMX(z.zhibdm,"+gongysb_id+",'"+hetksrq+"','"+hetjsrq+"') as changfzb,\n" +
				" GETHETBZ(z.zhibdm,"+hetb_id+") as kuangfzb,\n" +
				" pfxz.Zhibfz as biaozf\n" +
				"from  rl_gys_pingffab pffa,rl_gys_pingffaxzb pfxz,rl_gys_zhibdyb z\n" +
				"where pffa.id = pfxz.pingffab_id(+)\n" +
				"and pfxz.zhibdm=z.id" +
				"  and pffa.id = "+pingffab_id+"\n" +
				"  and pffa.zhuangt = 1\n" +
				"  and pfxz.leix > 0 " ;
		List list= (List) jdbcTemplate.queryForList(pingffa);
		String yuegmpf_id = this.getNewID(diancxxb_id);
		for(int i =0;i<list.size();i++){
			Map v = (Map) list.get(i);
			String zhibdm =  ((Map)list.get(i)).get("zhibdm").toString();
			String zhibgs =  ((Map)list.get(i)).get("zhibgs").toString();
			String changfzb =  ((Map)list.get(i)).get("changfzb").toString();
			String kuangfzb =  ((Map)list.get(i)).get("kuangfzb").toString();
			String zhibfz = "0";
			String strKaohbz = "是";
			String dblBiaozf = ((Map)list.get(i)).get("biaozf").toString();
			Interpreter bsh = new Interpreter();
			try {
				bsh.set("CF", Double.parseDouble(changfzb));
				bsh.set("KF", Double.parseDouble(kuangfzb));
				bsh.set("指标考核标识", strKaohbz);
				bsh.set("指标标准分", Double.parseDouble(dblBiaozf));
				bsh.eval(zhibgs);
				zhibfz = bsh.get("result").toString();

			} catch (EvalError e) {
				e.printStackTrace();
			}
			if (zhibdm.equals("SL")) {
				sql.append("insert into rl_gys_YUEGMJFB (ID, RIQ, GONGYSB_ID, JIF, LURSJ, LURRY, SHENHSJ, SHENHRY, HETB_ID, ZONGF, HETLJF) ");
				sql.append("values ("+yuegmpf_id+",sysdate,"+gongysb_id+","+zhibfz+",sysdate,'自动评分',null,null, "+hetb_id+", "+zhibfz+", "+zhibfz+"); \n");
			}else{
				sql.append("insert into rl_gys_yuegmjfmxb (ID, YUEGMJFB_ID, JIF, RIQ, BEIZ, ZHIBDM)");
				sql.append("values ("+Sequence.nextId()+", "+yuegmpf_id+","+zhibfz+",sysdate, null, '"+zhibdm+"'); \n ");
			}

		}
		sql.append(" update rl_gys_YUEGMJFB set JIF = (select sum(JIF) jif from rl_gys_YUEGMJFMXB where YUEGMJFB_ID = "+yuegmpf_id+"  ) ;\n");
		sql.append(" update rl_gys_YUEGMJFB set ZONGF = round(JIF + HETLJF ,2 ) where id= "+yuegmpf_id+"   ;\n");
		sql.append(" end; ");

		int flag = 0;
		if (sql.toString().length() > 15) {
			flag = jdbcTemplate.update(sql.toString()) ;
			if (flag > 0) {
				fabzt = true;
			}
			System.out.println("flag"+flag);
		}
		if (flag > 0) {
			System.out.println("计算完成！！");
		}

		return fabzt;

	}

}
