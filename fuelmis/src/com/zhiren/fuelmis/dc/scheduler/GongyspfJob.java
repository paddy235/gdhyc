package com.zhiren.fuelmis.dc.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import bsh.EvalError;
import bsh.Interpreter;


/**
 * @author 陈宝露
 */
@Component("gongyspfJob")
public class GongyspfJob {
	@Autowired
	private JdbcTemplate jdbcTemplate;//红雁池燃料库的jdbcTemplate
	private static Logger logger = LogManager.getLogger(GongyspfJob.class);
	@Autowired
	private JdbcTemplate jdbcTemplateGddl;//国电电力燃料库的jdbcTemplate
	@Autowired
	private JdbcTemplate jdbcTemplateGongys;
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
/*	@Scheduled(cron="0 0 23 * * ?")*/
	@SuppressWarnings({ "rawtypes", "unused", "static-access" })
	public void execute() {
		String diancxxb_id = "515";
		String riq = this.FormatDate(new Date());
//		String sql_tians ="select zhi from xitxxb where mingc='供应商接收时间调整' and leib = '供应商接口用'";
//
//		int tians = jdbcTemplate.queryForInt(sql_tians);//接收数据时天数调整
		int tians=3;
		String hetb_check = "select distinct  h.id ,h.gongysb_id,h.pingffab_id ,h.kaisrq, \n"
				+ "		h.jiesrq \n"
				+ " from rl_gys_hetb h where id not in (SELECT Y.HETB_ID FROM RL_GYS_YUEGMJFB Y) \n"
				+ "  and to_date(jiesrq,'yyyy-mm-dd')= DATE'"+ riq + "'-"+tians 
				;
		List lists = jdbcTemplate.queryForList(hetb_check);
		for(int j =0;j<lists.size();j++) {
			String hetb_id =((Map)lists.get(j)).get("id").toString();
			String gongysb_id = ((Map)lists.get(j)).get("gongysb_id").toString();
			String pingffab_id =((Map)lists.get(j)).get("pingffab_id").toString();
			String kaisrq =((Map)lists.get(j)).get("kaisrq").toString();
			String jiesrq =((Map)lists.get(j)).get("jiesrq").toString();
			String condition="and h.id="+hetb_id;
			Renyxx r=new Renyxx();
			r.setQuanc("系统");
            boolean flag=false;
            try {
                 flag =  this.jis(condition,r);
            } catch (Exception e) {
                e.printStackTrace();
            }
			if (flag) {
				try {
					this.Fab(hetb_id,diancxxb_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
//    public void compute(Object hetb_id,Renyxx lurry){
//	    this.jis(""){
//
//        }
//    }

	// 新建ID
	public String getNewID(String diancxxb_id) {
		int id = jdbcTemplate.queryForInt("select xl_xul_id.nextval id from dual");
		return diancxxb_id + id;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public boolean jis(String condition,Renyxx lurry) throws Exception{
	    String sql="select \n" +
                "  h.gongysb_id,h.kaisrq,h.jiesrq,\n" +
                "  b.leib leix,x.zhibfz,\n" +
                "  x.zhibgs,\n" +
                "  h.kf ,\n" +
                "  b.zhibdm,h.id hetb_id\n" +
                "  from (\n" +
                "  select ht.id,ht.pingffab_id, ht.gongysb_id,ht.kaisrq,ht.jiesrq ,ht.hetl kf ,'SL' zhibdm\n" +
                "  from rl_gys_hetb ht \n" +
                "  union all\n" +
                "  select ht.id,ht.pingffab_id,ht.gongysb_id,ht.kaisrq,ht.jiesrq ,z.zhibz kuangfzb ,to_char(z.zhibdm)\n" +
                "  from rl_gys_hetb ht \n" +
                "  left join rl_gys_hetzlb z on z.hetb_id=ht.id \n" +
                "  ) h\n" +
                "  inner join rl_gys_pingffab p on h.pingffab_id=p.id\n" +
                "  inner join rl_gys_pingffaxzb x on p.id=x.pingffab_id\n" +
                "  inner join rl_gys_zhibdyb b on b.id=x.zhibdm and h.zhibdm=b.zhibdm\n"+condition;
	    List kfList=jdbcTemplate.queryForList(sql);
        String yuegmpf_id=this.getNewID("515");
        String jifsql="begin\n";
        for (Object kf:kfList) {
            Object gongysb_id=((Map)kf).get("gongysb_id");
            Object kaisrq=((Map)kf).get("kaisrq");
            Object jiesrq=((Map)kf).get("jiesrq");
            Object zhibdm=((Map)kf).get("zhibdm");
            Object zhibfz=((Map)kf).get("zhibfz");
            Object KF=((Map)kf).get("kf");
            Object zhibgs=((Map)kf).get("zhibgs");
            Object hetb_id=((Map)kf).get("hetb_id");
            sql="SELECT c.diancxxb_id,c.gongysb_id," ;
                    if(zhibdm.equals("SL"))
                    sql+="sum (c.maoz-c.piz-c.koud) cf \n" ;
                    else sql+="  round_new(decode(sum (c.maoz-c.piz-c.koud),0,0,sum ((c.maoz-c.piz-c.koud)*h."+zhibdm+")/sum (c.maoz-c.piz-c.koud)) ,2) cf\n" ;
                    sql+=
                    "  FROM rl_ys_chepb c \n" +
                    "  INNER JOIN rl_ys_chepb_qc q ON c.id=q.chepb_id\n" +
                    "  INNER JOIN (SELECT * FROM gx_chep_caizhbmb WHERE zhuanhlb_id=1) gx1 ON gx1.yuanbm=c.samcode\n" +
                    "  INNER JOIN (SELECT * FROM gx_chep_caizhbmb WHERE zhuanhlb_id=2) gx2 ON gx1.mubbm=gx2.yuanbm\n" +
                    "  INNER JOIN rl_hy_huaydb h  ON h.huaybm= gx2.mubbm \n" +
                    "  WHERE substr(q.qingcsj,0,10) BETWEEN '"+kaisrq+"' AND '"+jiesrq+"'\n" +
                    "  AND gongysb_id="+gongysb_id+"\n" +
                    "  GROUP BY c.diancxxb_id,gongysb_id";
            List cfList=jdbcTemplate.queryForList(sql);


            for (Object cf:cfList) {
                Object CF=((Map)cf).get("cf");
                Interpreter bsh = new Interpreter();
                try {
                    bsh.set("CF", Double.parseDouble(CF.toString()));
                    bsh.set("KF", Double.parseDouble(KF.toString()));
                    bsh.set("指标考核标识", zhibdm);
                    bsh.set("指标标准分", Double.parseDouble(zhibfz.toString()));
                    bsh.eval(zhibgs.toString());
                    zhibfz = bsh.get("result").toString();
                }catch (Exception e){
                    e.printStackTrace();
                }
                String diancxxb_id=((Map)cf).get("diancxxb_id").toString();

                if (zhibdm.equals("SL") || zhibdm.equals("YHTL")) {
                    jifsql+=" insert into rl_gys_YUEGMJFB (ID, RIQ, GONGYSB_ID, JIF, LURSJ, LURRY, SHENHSJ, SHENHRY, HETB_ID, ZONGF, HETLJF, HETZQTSJF,CF_SL,KF_SL, diancxxb_id) ";
                    jifsql+="values (" + yuegmpf_id + ",to_char(sysdate,'yyyy-mm-dd')," + gongysb_id + "," + zhibfz + ",to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),'"+lurry.getQuanc()+"',null,null, " +
                            hetb_id + ", " + zhibfz + ", " + zhibfz + ", null," + CF + "," + KF + "," + diancxxb_id + "); \n";
                } else {
                    jifsql+="insert into rl_gys_yuegmjfmxb (ID, YUEGMJFB_ID, JIF, RIQ, BEIZ, ZHIBDM,CF,KF)";
                    jifsql+="values (getnewid(" + diancxxb_id + "), " + yuegmpf_id + "," + zhibfz + ",to_char(sysdate,'yyyy-mm-dd'), null, '" + zhibdm + "'," + CF + "," + KF + "); \n ";
                }
            }
        }
        jifsql+=" update rl_gys_YUEGMJFB set JIF = (select sum(JIF) jif from rl_gys_YUEGMJFMXB where YUEGMJFB_ID = "+yuegmpf_id+"  ) ;\n";
        jifsql+=" update rl_gys_YUEGMJFB set ZONGF = round(JIF + HETLJF ,2 ) where id= "+yuegmpf_id+"   ;\n";
        jifsql+=" end; ";
        jdbcTemplate.update(jifsql);
		return true;
	}
	
	
	@SuppressWarnings("rawtypes")
	public void Fab(String hetb_id,String diancxxb_id) throws Exception  {
		
		String zb_sql = "select  y.id,--number\n" +
				"       riq,--to_char(riq,'YYYY-MM-DD HH24:MI:SS' ) as riq, --time\n" + 
				"       g.mingc as jianc , --varchar2\n" + 
				"       y.jif as SLjif,  --number\n" + 
				"       y.lursj,--to_char(y.lursj,'YYYY-MM-DD HH24:MI:SS' ) as lursj, --time\n" + 
				"       h.kaisrq,--to_char(h.kaisrq,'yyyy-mm-dd') as kaisrq, -- date\n" + 
				"       h.jiesrq,--to_char(h.jiesrq,'yyyy-mm-dd') as jiesrq, -- date\n" + 
				"       h.id as hetb_id,\n" + 
				"       zongf, ----number\n" + 
				"       y.hetljf, --number\n" + 
				"       y.cf_sl, --number\n" + 
				"       y.kf_sl, --number\n" + 
				"       "+diancxxb_id+" as diancxxb_id --number\n" + 
				"  from rl_gys_YUEGMJFB y, gongysb g,rl_gys_hetb h\n" + 
				" where y.gongysb_id = g.id\n" + 
				"   and y.hetb_id = h.id \n" + 
				"   and h.id =  " + hetb_id ;
		List rs = jdbcTemplate.queryForList(zb_sql);
		StringBuffer sb = new StringBuffer("begin \n");
		for(int i =0;i<rs.size();i++){
			sb.append(" delete from  YUEGMJFB_NEW where id = ").append(((Map)rs.get(i)).get("id").toString()).append(" ;\n");
			sb.append("insert into YUEGMJFB_NEW (id,riq,gongysbMC,SLjif,lursj,kaisrq,jiesrq,hetb_id,zongf,hetljf,cf_sl,kf_sl,diancxxb_id ) values(");
			sb.append(((Map)rs.get(i)).get("id").toString()).append(",");
			sb.append("to_date('").append(((Map)rs.get(i)).get("riq").toString()).append("','YYYY-MM-DD HH24:MI:SS'),");
			sb.append("'").append(((Map)rs.get(i)).get("jianc").toString()).append("',");
			sb.append("'").append(((Map)rs.get(i)).get("SLjif").toString()).append("',");
			sb.append("to_date('").append(((Map)rs.get(i)).get("lursj").toString()).append("','YYYY-MM-DD HH24:MI:SS'),");
			sb.append("to_date('").append(((Map)rs.get(i)).get("kaisrq").toString()).append("','yyyy-mm-dd'),");
			sb.append("to_date('").append(((Map)rs.get(i)).get("jiesrq").toString()).append("','yyyy-mm-dd'),");			
			sb.append("'").append(((Map)rs.get(i)).get("hetb_id").toString()).append("',");
			sb.append("'").append(((Map)rs.get(i)).get("zongf").toString()).append("',");
			sb.append("'").append(((Map)rs.get(i)).get("hetljf").toString()).append("',");
			sb.append("'").append(((Map)rs.get(i)).get("cf_sl").toString()).append("',");
			sb.append("'").append(((Map)rs.get(i)).get("kf_sl").toString()).append("',");
			sb.append("'").append(((Map)rs.get(i)).get("diancxxb_id").toString()).append("'"); 			
			sb.append(" ); \n");
			String zicx = "select mx.id as id, --number\n" +
						"      mx.yuegmjfb_id,  --number\n" + 
						"      mx.zhibdm, --varvhar\n" + 
						"      mx.cf, --number\n" + 
						"      mx.kf,--number\n" + 
						"      mx.jif --number\n" + 
						" from rl_gys_yuegmjfmxb mx\n" + 
						" where mx.yuegmjfb_id= '"+((Map)rs.get(i)).get("id").toString()+"'";
			List rs_zcx = jdbcTemplate.queryForList(zicx);
			for(int j =0;j<rs_zcx.size();j++){
				sb.append(" delete from  YUEGMJFMXB_NEW where id = ").append(((Map)rs_zcx.get(j)).get("id").toString()).append(" ;\n");
				sb.append("insert into YUEGMJFMXB_NEW (ID, YUEGMJFB_ID, ZHIBDM, CF, KF, JIF) values ( ");
				sb.append("'").append(((Map)rs_zcx.get(j)).get("id").toString()).append("',");
				sb.append("'").append(((Map)rs_zcx.get(j)).get("yuegmjfb_id").toString()).append("',");
				sb.append("'").append(((Map)rs_zcx.get(j)).get("zhibdm").toString()).append("',");
				sb.append("'").append(((Map)rs_zcx.get(j)).get("cf").toString()).append("',");
				sb.append("'").append(((Map)rs_zcx.get(j)).get("kf").toString()).append("',");
				sb.append("'").append(((Map)rs_zcx.get(j)).get("jif").toString()).append("'");				
				sb.append(");\n");
			}
			
		}
		sb.append(" end ;");
		int result = jdbcTemplateGddl.update(sb.toString());
		int resultG=jdbcTemplateGongys.update(sb.toString());
		System.out.println("result:"+result);
		if(result==-1){
			logger.info("发布失败！");
		}else{
			logger.info("发布成功！");
		} 
		if(resultG==-1){
			logger.info("供应商发布失败！");
		}else{
			logger.info("供应商发布成功！");
		} 
	}

    public String computeScore(List<Map<String, Object>> list, Renyxx lurry) {
        String strMsg="";
        for (Map<String, Object> map : list) {
            if (map.get("FABSJ").toString().equals("null")||"".equals(map.get("FABSJ").toString())){
                String condition="and h.id="+map.get("ID");
                try {
                    this.jis(condition,lurry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                strMsg=strMsg+map.get("GONGYSMC")+"有效期:"+map.get("KAISRQ")+"至"+map.get("JIESRQ")+"<br>";
            }else{
                strMsg=strMsg+map.get("GONGYSMC")+"有效期:"+map.get("KAISRQ")+"至"+map.get("JIESRQ")+"已发布,计算失败！"+"<br>";
            }
        }
        return strMsg;
    }
}