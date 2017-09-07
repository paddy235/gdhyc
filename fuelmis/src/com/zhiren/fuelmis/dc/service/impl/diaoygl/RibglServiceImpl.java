package com.zhiren.fuelmis.dc.service.impl.diaoygl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.diaoygl.RibglDao;
import com.zhiren.fuelmis.dc.service.diaoygl.IRibglService;

import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.Sequence;

/**
 * @author 陈宝露
 */

@Service
public class RibglServiceImpl implements IRibglService {

    @Autowired
    private RibglDao ribglDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LogManager.getLogger(RibglServiceImpl.class);

    @Override
    public JSONArray getAll(Map<String, Object> map) {
        // TODO Auto-generated method stub addMeikxx
        Map<String, Object> maps = null;
        String riq = map.get("riq").toString();
        String lriq = DateUtil.changeDate(riq, -1);
        String eriq = DateUtil.getLastDayOfMonth(DateUtil.StringToDate(riq, "yyyy-MM-dd"));
        Map<String, Object> m = jdbcTemplate.queryForMap("Select nvl(kuc,0) as kuc  From SHOUHCRBB where substr(riq,0,10)=to_date('" + lriq + "','yyyy-MM-dd')");
        Double lkuc = Double.parseDouble(m.get("KUC").toString());
        try {
           // if (riq.equals(eriq)) {
                maps = jdbcTemplate
                        .queryForMap("select id, diancxxb_id, riq, dangrgm, haoyqkdr,kuc, diancscsj, beiz,dangrfdl,tiaozl, shangbkc,diancscwjm,fadl,quemtjts,quemtjrl,jingz,biaoz,yuns,yingd,kuid,fady,gongry,qity,cuns,shuifctz,panyk,diaoc,feiscy,changwml,bukdml,kedkc,decode(getXitxxbZhi(515,'日报是否锁定','调运',1),0,0,zhuangt) zhuangt,gongrl,shiftj from SHOUHCRBB "
                                + "where RIQ = to_date('"
                                + map.get("riq")
                                + "','yyyy-MM-dd') and DIANCXXB_ID = "
                                + map.get("diancxxb_id"));
           /* } else {
                maps = jdbcTemplate
                        .queryForMap("select id, diancxxb_id, riq, dangrgm, haoyqkdr,kuc, diancscsj, beiz,dangrfdl, shangbkc,diancscwjm,fadl,quemtjts,quemtjrl,jingz,biaoz,yuns,yingd,kuid,fady,gongry,qity,diaoc,feiscy,changwml,bukdml,kedkc,decode(getXitxxbZhi(515,'日报是否锁定','调运',1),0,0,zhuangt) zhuangt,gongrl,shiftj from SHOUHCRBB "
                                + "where RIQ = to_date('"
                                + map.get("riq")
                                + "','yyyy-MM-dd') and DIANCXXB_ID = "
                                + map.get("diancxxb_id"));
            }*/
            maps.put("LKUC", lkuc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        if (maps != null) {
            jsonArray.add(maps);
        }
        return jsonArray;
    }

    @Transactional
    public JSONArray createData(Map<String, Object> map) {

        JSONArray jsonArray = new JSONArray();
        // 判断验收数据是否已审核
        double maoz = 0;
        double piz = 0;
        double piaoz = 0;
        double jingz = 0;
        double yuns = 0;
        double yingd = 0;
        double kuid = 0;
        double kuc = 0;
        double kedkc = 0;
        double zongkd=0;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		
		/*String yanz_sql  = "select *\n" +
				"  from rl_ys_chepb c, rl_ys_chepb_sp sp ,rl_ys_chepb_qc qc\n" + 
				" where c.id = qc.chepb_id\n" + 
				"   and c.id = sp.chepb_id(+)\n" + 
				"   and sp.chepb_id is null\n" + 
				"   and substr(qc.qingcsj, 0, 10) = '"+map.get("riq")+"'\n" + 
				"   and diancxxb_id = "+map.get("diancxxb_id") ;

		list = jdbcTemplate.queryForList(yanz_sql );
		if (list.size() > 0) {
			jsonArray.add(-1);
			return jsonArray;
		}*/


        try {
            list = jdbcTemplate
                    .queryForList("select nvl(sum(maoz),0) MAOZ, nvl(sum(piz),0) PIZ, nvl(sum(PIAOZ),0) as PIAOZ,nvl(sum(zongkd),0) zongkd  from rl_ys_chepb where id in("
                            + "select chepb_id from rl_ys_chepb_qc where substr(qingcsj,0,10) = '"
                            + map.get("riq") + "') and diancxxb_id="
                            + map.get("diancxxb_id"));
        } catch (Exception e) {
            logger.info("===============查询的日期没有数据=====================");
            e.printStackTrace();
        }
        if (list.size() > 0) {
            Map<String, Object> maps = list.get(0);
            maoz = Double.parseDouble(maps.get("MAOZ").toString());
            piz = Double.parseDouble(maps.get("PIZ").toString());
            piaoz = Double.parseDouble(maps.get("PIAOZ").toString());
            zongkd=Double.parseDouble(maps.get("ZONGKD").toString());
        }

        // 生成日报
        jingz = maoz - piz-zongkd;
        if (maoz - piz >= piaoz) {
            yingd = maoz - piz - piaoz;
            kuid = yuns = 0;
        }
        //計算庫存
        Map<String, Object> zuorkcml = null;
        double kuc_yestoday = 0;
        try {

            zuorkcml = jdbcTemplate.queryForMap("select kuc from shouhcrbb where riq = to_date('" + map.get("riq") + "','yyyy-mm-dd')-1");
            if (zuorkcml != null) {
                kuc_yestoday = Double.parseDouble(zuorkcml.get("kuc").toString());
            }

        } catch (Exception e) {

        }

        Map<String, Object> meihy = null;
        double fady = 0;
        double gongry = 0;
        double qity = 0;
        double feiscy = 0;
        double haoyzl = 0;
        try {
            meihy = jdbcTemplate.queryForMap("select sum(fadhy) fadhy,sum(gongrhy) gongrhy,sum(qity) qity,sum(feiscy) feiscy from rl_rul_meihyb where rulrq = '" + map.get("riq") + "'");
            if (meihy != null) {
                fady = Double.parseDouble(meihy.get("FADHY").toString());
                gongry = Double.parseDouble(meihy.get("GONGRHY").toString());
                qity = Double.parseDouble(meihy.get("QITY").toString());
                feiscy = Double.parseDouble(meihy.get("FEISCY").toString());
            }
        } catch (Exception e) {

        }
        haoyzl = fady + gongry + qity + feiscy;
        kuc = kuc_yestoday + jingz - fady - gongry - qity - feiscy;
        kedkc = kuc;

        try {
            jdbcTemplate.update("delete from shouhcrbb where riq = to_date('"
                    + map.get("riq") + "','yyyy-MM-dd') and diancxxb_id = "
                    + map.get("diancxxb_id"));
            jdbcTemplate
                    .update("insert into shouhcrbb(ID,DIANCXXB_ID,RIQ,DANGRGM,HAOYQKDR,KUC,DANGRFDL,TIAOZL,SHANGBKC,FADL,"
                            + "QUEMTJTS,QUEMTJRL,JINGZ,BIAOZ,YUNS,YINGD,KUID,FADY,GONGRY,QITY,CUNS,SHUIFCTZ,PANYK,DIAOC,FEISCY,CHANGWML,"
                            + "BUKDML,KEDKC,zhuangt,GONGRL,SHIFTJ) values ("
                            + Sequence.nextId()
                            + ","
                            + map.get("diancxxb_id")
                            + ",to_date('"
                            + map.get("riq")
                            + "','yyyy-MM-dd')," +
                            jingz + ",\n" +
                            haoyzl + ","
                            + kuc
                            + ",0,0,0,0,0,0,"
                            + jingz
                            + ","
                            + piaoz
                            + ","
                            + yuns
                            + ","
                            + yingd
                            + ","
                            + kuid
                            + "," + fady + "," + gongry + "," + qity + ",0,0,0," + feiscy + ",0,0,0,"
                            + kedkc
                            + ",0,0,0)");

            jsonArray.add(1);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }

        return jsonArray;
    }


    @Override
    public JSONArray createData2(Map<String, Object> map) {
        //生成估收
        JSONArray jsonArray = new JSONArray();

        jdbcTemplate.update("delete from shouhcfkb where riq = to_date('"
                + map.get("riq") + "','yyyy-MM-dd') and diancxxb_id = "
                + map.get("diancxxb_id"));
        List<Map<String, Object>> lst = jdbcTemplate.queryForList("select c.gongysb_id,c.meikxxb_id,c.pinzb_id,"
                + "c.jihkjb_id,c.yunsfsb_id,round(sum(c.piaoz),0) piaoz,count(1) ches,sum(c.maoz - c.piz-zongkd) laimsl "
                + "from rl_ys_chepb c "
                + "left join  rl_ys_chepb_qc q  on c.id = q.chepb_id "
                + "left join (select yuanbm,mubbm from gx_chep_caizhbmb where zhuanhlb_id = 1) gx1  on c.samcode = gx1.yuanbm "
                + " left join (select yuanbm,mubbm from gx_chep_caizhbmb where zhuanhlb_id = 2) gx2  on gx1.mubbm = gx2.yuanbm "
                + "left join rl_hy_huaydb h on gx2.mubbm = h.huaybm  "
                + "where c.id = q.chepb_id  and substr(q.qingcsj, 0, 10) = '" + map.get("riq") + "'  "
                //	+ "and h.zhuangt=2 "
                + "group by c.gongysb_id,c.meikxxb_id, c.pinzb_id,  c.jihkjb_id, c.yunsfsb_id ");
        if (lst != null && lst.size() > 0) {
            for (int i = 0; i < lst.size(); i++) {
                Map<String, Object> maps = lst.get(i);
                String gongysb_id = maps.get("gongysb_id").toString();
                String meikxxb_id = maps.get("meikxxb_id").toString();
                String pinzb_id = maps.get("pinzb_id").toString();
                String jihkjb_id = maps.get("jihkjb_id").toString();
                String yunsfsb_id = maps.get("yunsfsb_id").toString();
                //查询热值
                double rez = 0;
                try {
                    //根据samcode查询化验编码
                    String sql = "select decode(sum(jingz), 0, 0,\n" +
                            "              round_new(sum(jingz * qnet_ar) / sum(jingz), 2)) qnet_ar\n" +
                            "  from (SELECT (c.maoz - c.piz - c.zongkd) jingz, (nvl(h.qnet_ar, 0)) qnet_ar\n" +
                            "          FROM rl_ys_chepb c\n" +
                            "          join RL_YS_CHEPB_QC QC on c.id = QC.chepb_id\n" +
                            "          left join (select * from gx_chep_caizhbmb where zhuanhlb_id = 1) gx1 on c.samcode = gx1.yuanbm\n" +
                            "          left join (select * from gx_chep_caizhbmb where zhuanhlb_id = 2) gx2 on gx1.mubbm = gx2.yuanbm\n" +
                            "          left join rl_hy_huaydb h on gx2.mubbm = h.huaybm\n" +
                            "         where gongysb_id = '" + gongysb_id + "'\n" +
                            "           and meikxxb_id = '" + meikxxb_id + "'\n" +
                            "           and pinzb_id = '" + pinzb_id + "'\n" +
                            "           and jihkjb_id = '" + jihkjb_id + "'\n" +
                            "           and yunsfsb_id = '" + yunsfsb_id + "'\n" +
                            "           and substr(qingcsj, 0, 10) = '" + map.get("riq") + "')";
                    rez = jdbcTemplate.queryForObject(sql, Double.class);
                } catch (Exception e) {
                }
                //查询含税煤价
                double meij = 0;
                try {
                    String meijSQL = "select round(sum(jiesjg*jiessl)/sum(jiessl),2) from rl_js_rijsdb where gongysb_id ="
                            + maps.get("gongysb_id") + " and pinzb_id = " + maps.get("pinzb_id") + " and substr(QINGCSJ,0,10) = '" + map.get("riq") + "'";
                    meij = jdbcTemplate.queryForObject(meijSQL, Double.class);
                } catch (Exception e) {
                }
                jdbcTemplate.update(
                        "insert into shouhcfkb(id,diancxxb_id,rez,laimsl,riq,meikxxb_id,gongysb_id,pinzb_id,jihkjb_id,yunsfsb_id,ches,piaoz,meij,meijs) "
                                + "values(" + Sequence.nextId() + "," + map.get("diancxxb_id") + "," + rez + "," + maps.get("LAIMSL") + ",to_date('"
                                + map.get("riq") + "','yyyy-MM-dd'),'" + maps.get("MEIKXXB_ID") + "','" + maps.get("GONGYSB_ID") + "','" + maps.get("PINZB_ID")
                                + "','" + maps.get("JIHKJB_ID") + "','" + maps.get("YUNSFSB_ID") + "'," + maps.get("CHES") + "," + maps.get("PIAOZ") + "," + meij + ",round(" + meij + "/1.17*0.17,2))");
            }
            jsonArray.add(1);
        }
        jsonArray.add(0);
        return jsonArray;
    }

    @Override
    @Transactional
    public JSONArray save(String strList) {
        // TODO Auto-generated method stub

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArr = JSONArray.fromObject(strList);
        JSONObject jsonObject = jsonArr.getJSONObject(0);
        try {
            jdbcTemplate.update("update shouhcrbb set fady = "
                    + jsonObject.get("FADY") + ",gongry = "
                    + jsonObject.get("GONGRY") + ",qity = "
                    + jsonObject.get("QITY") + ",CUNS = "
                    + jsonObject.get("CUNS") + ",TIAOZL = "
                    + jsonObject.get("TIAOZL") + ",SHUIFCTZ = "
                    + jsonObject.get("SHUIFCTZ") + ",PANYK = "
                    + jsonObject.get("PANYK") + ",feiscy = "
                    + jsonObject.get("FEISCY") + ",changwml = "
                    + jsonObject.get("CHANGWML") + ",bukdml = "
                    + jsonObject.get("BUKDML") + ",shiftj = "
                    + jsonObject.get("SHIFTJ") +" ,jingz = " 
                    + jsonObject.get("JINGZ")+" ,dangrgm = " 
                    + jsonObject.get("JINGZ") +" ,haoyqkdr = " 
                    + jsonObject.get("FADY")  
                    + " where id = "
                    + jsonObject.get("ID"));
            jdbcTemplate.update("update shouhcrbb set kuc = (" + jsonObject.get("KUCX")  + ") ,kedkc = " + jsonObject.get("KUCX")  + " ,haoyqkdr = (fady +gongry+ qity+ cuns +shuifctz +panyk +diaoc)   where id = "
                    + jsonObject.get("ID"));
            jsonArray.add(1);
        } catch (Exception e) {
            jsonArray.add(-1);
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public JSONArray getAll2(Map<String, Object> map) {
    	
        List<Map<String, Object>> maps = null;
        try {
            maps = jdbcTemplate
                    .queryForList("select decode(getXitxxbZhi(515,'日报是否锁定','调运',1),0,0,c.zhuangt) zhuangt, c.id,g.mingc gongysb_id,m.mingc meikxxb_id, \n"
                            + "	p.mingc pinzb_id,j.mingc jihkjb_id,y.mingc yunsfsb_id,c.piaoz,c.ches, \n"
                            + "	c.laimsl,c.rez,c.meij,c.meijs,c.yunj,c.yunjs, \n"
                            + "  decode(rez,0,0,round((c.meij+c.yunj)*29.271/rez,2) ) hansbmdj, "
                            + "  decode(rez,0,0,round((c.meij/1.17+c.yunj/1.11) *29.271/rez,2) ) buhsbmdjfrom "
                            + " from shouhcfkb c,gongysb g,meikxxb m,pinzb p,jihkjb j,yunsfsb y \n"
                            + "where c.gongysb_id = g.id and c.meikxxb_id = m.id and c.pinzb_id = p.id \n"
                            + "	and c.jihkjb_id = j.id  and c.yunsfsb_id = y.id and c.riq = to_date('" + map.get("riq") + "','yyyy-MM-dd') \n"
                            + " 	and diancxxb_id = " + map.get("diancxxb_id") + "\n"
                            + "union  \n"
                            + "select 0 zhuangt, null id,'合计','','','','',sum(c.piaoz) piaoz,sum(c.ches),sum(laimsl) laimsl, \n "
                            + "	decode(sum(laimsl),0,0,round(sum(c.rez * c.laimsl) / sum(c.laimsl), 2)) rez,\n"
                            + "	decode(sum(laimsl),0,0,round(sum(c.meij * c.laimsl) / sum(c.laimsl), 2)) meij,\n"
                            + "	decode(sum(laimsl),0,0,round(sum(c.meij * c.laimsl) / sum(c.laimsl) / 1.17 * 0.17, 2)) meijs,\n"
                            + "	decode(sum(laimsl),0,0,round(sum(c.yunj*c.laimsl) / sum(c.laimsl), 2)) yunj,\n"
                            + "	decode(sum(laimsl),0,0,round(sum(c.yunjs*c.laimsl) / sum(c.laimsl), 2)) yunjs,\n"
                            + "	decode (sum(c.rez * c.laimsl),0,0,round(sum(c.meijs) * 29.271 / (sum(c.rez * c.laimsl) / sum(c.laimsl)), 2))hansbmdj, "
                            + "  decode (sum(c.rez * c.laimsl),0,0,round(sum(c.meijs) * 29.271 / (sum(c.rez * c.laimsl) / sum(c.laimsl)) / 1.17,  2) ) buhsbmdj "
                            + "from shouhcfkb c,gongysb g,meikxxb m,pinzb p,jihkjb j,yunsfsb y "
                            + "where c.gongysb_id = g.id and c.meikxxb_id = m.id and c.pinzb_id = p.id "
                            + "	and c.jihkjb_id = j.id  and c.yunsfsb_id = y.id and c.riq = to_date('" + map.get("riq") + "','yyyy-MM-dd')"
                            + " 	and diancxxb_id = " + map.get("diancxxb_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        if (maps != null && maps.size() > 0) {
            for (int i = 0; i < maps.size(); i++) {
                if (maps.get(i).get("REZ") != null && Double.parseDouble(maps.get(i).get("REZ").toString()) != 0) {
                    double hansbmdj = (Double.parseDouble(maps.get(i).get("MEIJ").toString()) + Double.parseDouble(maps.get(i).get("YUNJ").toString())) * 29.271 / Double.parseDouble(maps.get(i).get("REZ").toString());
                    double buhsbmdj = (
                            Double.parseDouble(maps.get(i).get("MEIJ").toString())
                                    +
                                    Double.parseDouble(maps.get(i).get("YUNJ").toString())
                                    -
                                    (Double.parseDouble(maps.get(i).get("YUNJS").toString()))
                                    -
                                    ((Double.parseDouble(maps.get(i).get("MEIJS").toString())))) * 29.271 / Double.parseDouble(maps.get(i).get("REZ").toString());

                    Map<String, Object> mm = maps.get(i);
                    mm.remove("HANSBMDJ");
                    mm.put("HANSBMDJ", String.format("%.2f", hansbmdj));
                    mm.remove("BUHSBMDJ");
                    mm.put("BUHSBMDJ", String.format("%.2f", buhsbmdj));

                    jsonArray.add(mm);
                } else {
                    jsonArray.add(maps.get(i));
                }
            }
        }
        return jsonArray;
    }

    @Override
    public JSONArray save2(String strList) {
        // TODO Auto-generated method stub
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArr = JSONArray.fromObject(strList);
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObject = jsonArr.getJSONObject(i);

            try {
                jdbcTemplate.update("update shouhcfkb set meij = "
                        + jsonObject.get("MEIJ") + ",meijs = round("
                        + jsonObject.get("MEIJ") + "/1.17*0.17,2),yunj = "
                        + jsonObject.get("YUNJ") + ",yunjs = round(" + jsonObject.get("YUNJS") + "/1.11*0.11,2) where id = "
                        + jsonObject.get("ID"));
                jsonArray.add(1);
            } catch (Exception e) {
                jsonArray.add(-1);
                TransactionAspectSupport.currentTransactionStatus()
                        .setRollbackOnly();
                e.printStackTrace();
            }
        }
        return jsonArray;
    }


    @Override
    public JSONArray getRibtbAll(String riq) {
        List<Map<String, Object>> list = ribglDao.getRibtbShangc(riq);
        //修改状态
        ribglDao.updateZhuangt(riq);
        JSONArray result = JSONArray.fromObject(list);
        return result;
    }

    @Override
    public JSONArray getRibgsAll(String riq) {
        List<Map<String, Object>> list = ribglDao.getRibgsShangc(riq);
//        ribglDao.updateRibgsZhuangt(riq);
        JSONArray result = JSONArray.fromObject(list);
        return result;
    }


    @Override
    public void RibtbShangc(JSONArray json) {
        for (int i = 0; i < json.size(); i++) {
            JSONObject jsonObject = json.getJSONObject(i);
            try {
                jdbcTemplate.update("insert into shouhcrbb@db (ID,DIANCXXB_ID,RIQ,DANGRGM,HAOYQKDR,KUC,DANGRFDL,TIAOZL,FADL,JINGZ,BIAOZ,YUNS,YINGD,KUID,FADY,GONGRY,QITY,CUNS,SHUIFCTZ,PANYK,DIAOC,FEISCY,CHANGWML,BUKDML,KEDKC,GONGRL,SHIFTJ)VALUES("
                        + jsonObject.get("ID") + ","
                        + jsonObject.get("DIANCXXB_ID") + ","
                        + "date'" + jsonObject.get("RIQ") + "',"
                        + jsonObject.get("DANGRGM") + ","
                        + jsonObject.get("HAOYQKDR") + ","
                        + jsonObject.get("KUC") + ","
                        + jsonObject.get("DANGRFDL") + ","
                        + jsonObject.get("TIAOZL") + ","
                        + jsonObject.get("FADL") + ","
                        + jsonObject.get("JINGZ") + ","
                        + jsonObject.get("BIAOZ") + ","
                        + jsonObject.get("YUNS") + ","
                        + jsonObject.get("YINGD") + ","
                        + jsonObject.get("KUID") + ","
                        + jsonObject.get("FADY") + ","
                        + jsonObject.get("GONGRY") + ","
                        + jsonObject.get("QITY") + ","
                        + jsonObject.get("CUNS") + ","
                        + jsonObject.get("SHUIFCTZ") + ","
                        + jsonObject.get("PANYK") + ","
                        + jsonObject.get("DIAOC") + ","
                        + jsonObject.get("FEISCY") + ","
                        + jsonObject.get("CHANGWML") + ","
                        + jsonObject.get("BUKDML") + ","
                        + jsonObject.get("KEDKC") + ","
                        + jsonObject.get("GONGRL") + ","
                        + jsonObject.get("GONGRL") + ")");
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus()
                        .setRollbackOnly();
                e.printStackTrace();
            }
        }
    }


    @Override@Transactional
    public void RibgsShangc(Map<String, Object> map) {
        ribglDao.updateRibgsZhuangt(map);
    }


}