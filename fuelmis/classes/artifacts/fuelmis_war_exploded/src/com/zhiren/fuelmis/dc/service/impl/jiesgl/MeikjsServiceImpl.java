package com.zhiren.fuelmis.dc.service.impl.jiesgl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhiren.fuelmis.dc.dao.kucgl.rukgl.ZidrkDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhiren.fuelmis.dc.dao.jiesgl.MeikjsDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.report.Report;
import com.zhiren.fuelmis.dc.report.Table;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import com.zhiren.fuelmis.dc.service.jiesgl.IMeikjsService;
import com.zhiren.fuelmis.dc.utils.CnUpperCaser;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.NumberToCN;
import com.zhiren.fuelmis.dc.utils.Sequence;
import com.zhiren.fuelmis.dc.utils.StaticApplication;
import com.zhiren.fuelmis.dc.utils.DateUtil.DateFormatType;

/**
 * @author 陈宝露
 */
@Service
public class MeikjsServiceImpl implements IMeikjsService {

    @Autowired
    private MeikjsDao meikjsDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ZidrkDao zidrkDao;
    private static Logger logger = LogManager
            .getLogger(MeikjsServiceImpl.class);

    @Override
    public Map<String, Object> getjiesd(String Jiesbh) {
        Map<String, Object> jiesd = meikjsDao.getjiesd(Jiesbh);
        String MEIKDJDX = NumberToCN.number2CNMontrayUnit(BigDecimal.valueOf(Double.parseDouble(jiesd.get("JIAKHJ").toString())));
        jiesd.put("MEIKDJDX", MEIKDJDX);
        String YUNZFHJDX = NumberToCN.number2CNMontrayUnit(BigDecimal.valueOf(Double.parseDouble(jiesd.get("YUNZFHJ").toString())));
        jiesd.put("YUNZFHJDX", YUNZFHJDX);
        String ZONGHJDX = NumberToCN.number2CNMontrayUnit(BigDecimal.valueOf(Double.parseDouble(jiesd.get("ZONGHJ").toString())));
        jiesd.put("ZONGHJDX", ZONGHJDX); 
        String sql=" select * from RL_JS_JIESZBSJB where JIESDBH='"+Jiesbh+"'";
        List<Map<String,Object>> zhibList=jdbcTemplate.queryForList(sql);
        jiesd.put("zhibList",zhibList);
        return jiesd;
    }

    @Override
    public List<Map<String, Object>> getJieszbsjbByJiesbh(String jiesbh) {
        List list = meikjsDao.getJieszbsjbByJiesbh(jiesbh);
        return list;
    }



    @Override
    public void deleteJiesdByJiesdbh(String jiesbh) {
        // TODO Auto-generated method stub
        meikjsDao.deleteJiesdByJiesdbh(jiesbh);
    }



    @Override
    public List<Map<String, Object>> getJiesdbh() {
        return meikjsDao.getJiesdbh();
    }


    @Override
    @Transactional
    public void saveJiesd_hyc(Map<String, Object> m) {
        int caozlx=0;
        String sql="select * from rl_js_yuejsdb where jiesbh='"+m.get("JIESBH")+"'";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        if(list.size()==0){
            caozlx=0;
        }else{
            caozlx=1;
        }
        meikjsDao.saveJiesd_hyc(m,caozlx);
        //保存
    }

    @Override
    public JSONArray getJiestz(Map<String, Object> map) {
        List<Map<String, Object>> list = meikjsDao.getJiestz(map);
        String[][] arrData = new String[list.size()][26];
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> rt = list.get(i);
            arrData[i][0] = rt.get("RUCRQ") == null ? "" : rt.get("RUCRQ").toString();
            arrData[i][1] = rt.get("GONGYS") == null ? "" : rt.get("GONGYS").toString();
            arrData[i][2] = rt.get("MEIZ") == null ? "" : rt.get("MEIZ").toString();
            arrData[i][3] = rt.get("JIESRQ") == null ? "" : rt.get("JIESRQ")
                    .toString();
            arrData[i][4] = rt.get("BIANM") == null ? "" : rt.get("BIANM")
                    .toString();
            arrData[i][5] = rt.get("PIAOZ") == null ? "" : rt.get("PIAOZ")
                    .toString();
            arrData[i][6] = rt.get("JINGZ") == null ? "" : rt.get("JINGZ")
                    .toString();
            arrData[i][7] = rt.get("JIESSL") == null ? "" : rt.get("JIESSL")
                    .toString();
            arrData[i][8] = rt.get("JIESRL") == null ? "" : rt.get("JIESRL")
                    .toString();
            arrData[i][9] = rt.get("HANSDJ") == null ? "" : rt.get("HANSDJ")
                    .toString();
            arrData[i][10] = rt.get("BUHSMK") == null ? "" : rt.get("BUHSMK")
                    .toString();
            arrData[i][11] = rt.get("SHUIK") == null ? "" : rt.get("SHUIK")
                    .toString();
            arrData[i][12] = rt.get("HANSMK") == null ? "" : rt.get("HANSMK")
                    .toString();
            arrData[i][13] = rt.get("YUNF") == null ? "" : rt.get("YUNF")
                    .toString();
            arrData[i][14] = rt.get("ZAF") == null ? "" : rt.get("ZAF")
                    .toString();
            arrData[i][15] = rt.get("BUHSYF") == null ? "" : rt.get("BUHSYF")
                    .toString();
            arrData[i][16] = rt.get("YUNFSK") == null ? "" : rt.get("YUNFSK")
                    .toString();
            arrData[i][17] = rt.get("HANSYF") == null ? "" : rt.get("HANSYF")
                    .toString();
            arrData[i][18] = rt.get("ZONGJE") == null ? "" : rt.get("ZONGJE")
                    .toString();
            arrData[i][19] = rt.get("BIAOMDJ") == null ? "" : rt.get("BIAOMDJ")
                    .toString();
            arrData[i][20] = rt.get("BUHSBMDJ") == null ? "" : rt.get("BUHSBMDJ").toString();
            arrData[i][21] = rt.get("RELZJE") == null ? "" : rt.get("RELZJE")
                    .toString();
            arrData[i][22] = rt.get("LIUFZJE") == null ? "" : rt.get("LIUFZJE")
                    .toString();
            arrData[i][23] = rt.get("SHUIFZJE") == null ? "" : rt.get(
                    "SHUIFZJE").toString();
            arrData[i][24] = rt.get("HUIFZJE") == null ? "" : rt.get("HUIFZJE")
                    .toString();
            arrData[i][25] = rt.get("HUIFFZJE") == null ? "" : rt.get(
                    "HUIFFZJE").toString();
        }
        Report rt = new Report();
        String ArrHeader[][] = new String[1][26];
        ArrHeader[0] = new String[]{"入厂日期", "供应商", "煤种", "结算日期", "结算编号",
                "票重<br>(吨)", "净重<br>(吨)", "结算量<br>(吨)",
                "结算热量<br>(kcal/kg)", "单价<br>（含税）", "价款金额<br>(元)",
                "价款税款<br>(元)", "价税合计<br>(元)", "运费<br>(元)", "杂费<br>(元)",
                "不含税运费<br>(元)", "运费税款<br>(元)", "运杂费合计<br>(元)", "总金额<br>(元)",
                "标煤单价(含税)", "标煤单价(不含税)", "热量折价金额<br>(元)", "硫分折价金额<br>(元)",
                "水分折价金额<br>(元)", "灰分折价金额<br>(元)", "挥发份折价金额<br>(元)"};

        int ArrWidth[] = new int[]{100, 90, 90, 100, 170, 65, 65, 60, 60, 60, 85, 85, 85, 85, 85, 85, 85, 85, 80, 58, 58, 80, 80, 75, 75, 85};
        rt.setBody(new Table(arrData, 1, 0, 0, 26));
        rt.body.setHeaderData(ArrHeader);
        rt.body.setPageRows(15);
        int pageCount = rt.getPages();
        rt.body.ShowZero = false;
        rt.body.setColAlign(1, Table.ALIGN_CENTER);
        rt.body.setColAlign(2, Table.ALIGN_CENTER);
        rt.body.setColAlign(3, Table.ALIGN_CENTER);
        rt.body.setColAlign(4, Table.ALIGN_CENTER);
        rt.body.setColAlign(5, Table.ALIGN_CENTER);
        for (int i = 6; i <= 26; i++) {
            rt.body.setColAlign(i, Table.ALIGN_RIGHT);
        }
        rt.setTitle("结算统计台帐", ArrWidth);
        rt.body.setWidth(ArrWidth);
        rt.body.mergeFixedRowCol();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
        resultMap.put("pageCount", pageCount);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(resultMap);
        return jsonArray;
    }

    public JSONObject getJiesList(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();

        int ches_sum = 0;
        int maxListSize = 0;
        double piaoz_sum = 0;
        double yingk_sum = 0;
        double yuns_sum = 0;
        double jingz_sum = 0;

        double qnetar_count = 0;
        double std_count = 0;
        double mt_count = 0;

        // 查询所有可以结算的入库单
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> lst = jdbcTemplate
                    .queryForList("SELECT   DISTINCT  c.rukdbh\n" +
                            "  FROM RL_KC_CHURKDB c ,GX_CHURKDB_YUNSDJ GX\n" +
                            " WHERE YEWLX = 1\n" +
                            "   AND ZHUANGT = 1\n" +
                            "   AND C.RUKDBH =GX.RUKDBH\n" +
                            "   AND C.HANGH = GX.HANGH\n" +
                            "   AND GX.YUANDJLX = 1\n" +
                            "   AND GX.YUANDJ_ID NOT IN ( SELECT GX_JIESDB_CHEPB.CHEPB_ID   FROM GX_JIESDB_CHEPB )");
            if (lst != null && lst.size() > 0) {
                Object[][] objs = new Object[lst.size() + 1][];
                maxListSize = lst.size();
                for (int i = 0; i < lst.size(); i++) {
                    try {
                        objs[i] = new Object[15];
                        // 遍历入库单，查询每个入库单对应的chepb_id和caigddb_id
                        Map<String, Object> rukd = lst.get(i);
                        List<Map<String, Object>> list = jdbcTemplate
                                .queryForList("select * from  GX_CHURKDB_YUNSDJ  where RUKDBH = '" + rukd.get("RUKDBH") + "'");
                        logger.info("=================================入庫單編號："
                                + rukd.get("RUKDBH"));
                        String chepb_id = "";
                        String caigddb_id = "";
                        if (list != null && list.size() > 0) {
                            for (int j = 0; j < list.size(); j++) {
                                // 遍历每个入库单对应的chepb_id和caigddb_id，查询对应的验收明细和采购订单信息
                                if (list.get(j).get("YUANDJLX").toString()
                                        .equals("1")) { // 验收明细、质量
                                    chepb_id += list.get(j).get("YUANDJ_ID")
                                            .toString()
                                            + ",";
                                }
                                if (list.get(j).get("YUANDJLX").toString()
                                        .equals("2")) { // 采购订单
                                    caigddb_id = list.get(j).get("YUANDJ_ID")
                                            .toString();
                                }
                            }

                            logger.info("====================車皮表ID：" + chepb_id);
                            logger.info("====================採購訂單表ID："
                                    + caigddb_id);

                            // 判断是否已结算
                            int count = jdbcTemplate
                                    .queryForInt("select count(1) from GX_JIESDB_CHEPB where CHEPB_ID in("
                                            + chepb_id.substring(0,
                                            chepb_id.length() - 1)
                                            + ")");
                            if (count == 0) {
                                String chepb_sql = "select GONGYSB_ID,MEIKXXB_ID,ZHONGCSJ,QINGCSJ,FAZ_ID,sum(CHES) CHES,sum(PIAOZ) PIAOZ,sum(YINGK) YINGK,sum(YUNS) YUNS,sum(JINGZ) JINGZ,SAMCODE from ("
                                        + "select G.MINGC GONGYSB_ID,M.MINGC MEIKXXB_ID,"
                                        + "substr(QC.ZHONGCSJ,0,10) ZHONGCSJ,substr(QC.QINGCSJ,0,10) QINGCSJ,"
                                        + "Z.MINGC FAZ_ID,count(1) CHES,C.PIAOZ,0 YINGK,0 YUNS,sum(C.MAOZ-C.PIZ-c.zongkd) JINGZ,"
                                        + "C.SAMCODE from RL_YS_CHEPB C,RL_YS_CHEPB_QC QC,GONGYSB G,MEIKXXB M,CHEZXXB Z"
                                        + " where C.ID = QC.CHEPB_ID and C.GONGYSB_ID = G.ID and C.MEIKXXB_ID = M.ID"
                                        + " and C.FAZ_ID = Z.ID and C.ID in ("
                                        + chepb_id.substring(0,
                                        chepb_id.length() - 1)
                                        + ") and QC.ZHONGCSJ >= '"
                                        + map.get("sDate")
                                        + "' and QC.QINGCSJ <= '"
                                        + map.get("eDate")
                                        + "' "
                                        + "group by G.MINGC,M.MINGC,ZHONGCSJ,QINGCSJ,Z.MINGC,C.PIAOZ,C.SAMCODE"
                                        + " ) group by GONGYSB_ID,MEIKXXB_ID,zhongcsj,qingcsj,FAZ_ID,SAMCODE";
                                Map<String, Object> chepb = jdbcTemplate
                                        .queryForMap(chepb_sql);
                                objs[i][0] = rukd.get("RUKDBH");
                                objs[i][1] = chepb.get("GONGYSB_ID");
                                logger.info("===============================供應商名稱："
                                        + chepb.get("GONGYSB_ID"));
                                objs[i][2] = chepb.get("MEIKXXB_ID");
                                objs[i][3] = chepb.get("ZHONGCSJ");
                                objs[i][4] = chepb.get("QINGCSJ");
                                objs[i][5] = chepb.get("FAZ_ID");
                                objs[i][6] = chepb.get("CHES");
                                ches_sum += Integer.parseInt(chepb.get("CHES")
                                        .toString());
                                objs[i][7] = chepb.get("PIAOZ");
                                piaoz_sum += Double.parseDouble(chepb.get(
                                        "PIAOZ").toString());
                                objs[i][8] = chepb.get("YINGK");
                                objs[i][9] = chepb.get("YUNS");
                                objs[i][10] = chepb.get("JINGZ");
                                jingz_sum += Double.parseDouble(chepb.get(
                                        "JINGZ").toString());

                                logger.info("========================SAMCODE:"
                                        + chepb.get("SAMCODE"));

                                Map<String, Object> zhilb = jdbcTemplate
                                        .queryForMap("select QNET_AR,STD,MT from RL_HY_HUAYDB  where HUAYBM = (select MUBBM from GX_CHEP_CAIZHBMB where YUANBM = "
                                                + "(select MUBBM from GX_CHEP_CAIZHBMB where YUANBM = '"
                                                + chepb.get("SAMCODE") + "'))");

                                objs[i][12] = zhilb.get("QNET_AR");
                                qnetar_count += Double.parseDouble(zhilb.get(
                                        "QNET_AR").toString())
                                        * Double.parseDouble(chepb.get("JINGZ")
                                        .toString());
                                objs[i][13] = zhilb.get("STD");
                                std_count += Double.parseDouble(zhilb
                                        .get("STD").toString())
                                        * Double.parseDouble(chepb.get("JINGZ")
                                        .toString());
                                objs[i][14] = zhilb.get("MT");
                                mt_count += Double.parseDouble(zhilb.get("MT")
                                        .toString())
                                        * Double.parseDouble(chepb.get("JINGZ")
                                        .toString());
                                String hetSQL = "select HETBH from RL_HT_HETB where ID = ("
                                        + "select HETB_ID from RL_HT_CAIGDDB where ID = "
                                        + caigddb_id + ")";
                                logger.info("=========================hetSQL:"
                                        + hetSQL);
                                String hetbh = jdbcTemplate.queryForObject(
                                        hetSQL, String.class);

                                logger.info("============================合同編號："
                                        + hetbh);

                                objs[i][11] = hetbh;
                            }
                        }

                        logger.info("*************************************************");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                double qnetar_avg = 0;
                double std_avg = 0;
                double mt_avg = 0;

                if (jingz_sum > 0) {
                    qnetar_avg = qnetar_count / jingz_sum;
                    std_avg = std_count / jingz_sum;
                    mt_avg = mt_count / jingz_sum;
                }

                Object[] obj = new Object[15];
                obj[0] = "";
                obj[1] = "合计";
                obj[2] = "";
                obj[3] = "";
                obj[4] = "";
                obj[5] = "";
                obj[6] = ches_sum;
                obj[7] = piaoz_sum;
                obj[8] = yingk_sum;
                obj[9] = yuns_sum;
                obj[10] = jingz_sum;
                obj[11] = "";
                obj[12] = qnetar_avg;
                obj[13] = std_avg;
                obj[14] = mt_avg;
                objs[maxListSize] = obj;

                int ids = 0;

                for (int index = 0; index < objs.length; index++) {
                    if (!"".equals(objs[index][0]) && objs[index][0] != null) {
                        ids++;
                    }
                }

                Object[][] rtnObjs = new Object[ids][];

                int id_index = 0;

                for (int index = 0; index < objs.length; index++) {
                    if (!"".equals(objs[index][0]) && objs[index][0] != null) {
                        rtnObjs[id_index] = objs[index];
                        id_index++;
                    }
                }

                jsonMap.put("data", rtnObjs);
            } else {
                jsonMap.put("data", "");
            }
        } catch (Exception e) {
            jsonMap.put("data", "");
            e.printStackTrace();
        }

        jsonObject = JSONObject.fromObject(jsonMap);

        return jsonObject;
    }

    public SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    public SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    @Transactional
    public JSONArray jies(Map<String, Object> map) {
        JSONArray jsonArray = new JSONArray();
        String newId = Sequence.nextId();
        Diancxx diancxx = (Diancxx) map.get("diancxx");
        // 生成结算编号
        String jiesbh = "GD-JS-" + diancxx.getPiny() + "-"
                + sdf.format(new Date()) + "-";
        int xuh = jdbcTemplate.queryForObject(
                "SELECT count(1)+1 FROM RL_JS_RIJSDB", Integer.class);
        if (xuh < 10) {
            jiesbh += "0" + xuh;
        } else {
            jiesbh += xuh;
        }
        logger.info("====================日结算开始====================");
        try {
            // 查询采购订单
            String caigddb_id = jdbcTemplate
                    .queryForObject(
                            "select distinct yuandj_id from GX_CHURKDB_YUNSDJ  where rukdbh = '"
                                    + map.get("id") + "' and yuandjlx = 2",
                            String.class);
            logger.info("**********采购订单ID：" + caigddb_id + "**********");
            // 查询合同
            Map<String, Object> hetb = jdbcTemplate
                    .queryForMap("select * from RL_HT_HETB where ID = ("
                            + "select HETB_ID from RL_HT_CAIGDDB where ID = "
                            + caigddb_id + ")");
            double hetjg = Double.parseDouble(hetb.get("JIAG").toString());
            logger.info("**********合同价格：" + hetjg + "**********");
            // 查询车皮信息
            String chepb_sql = "select GONGYSB_ID,FAZ_ID,ZHONGCSJ,QINGCSJ,PINZB_ID,sum(PIAOZ) PIAOZ,sum(CHES) CHES,sum(JINGZ) JINGZ,SAMCODE,JIHKJB_ID,DIANCXXB_ID,MEIKXXB_ID from ("
                    + "select C.GONGYSB_ID,C.FAZ_ID,substr(Q.ZHONGCSJ,0,10) ZHONGCSJ,substr(Q.QINGCSJ,0,10) QINGCSJ,C.PINZB_ID,sum(C.PIAOZ) PIAOZ,count(1) CHES,sum(C.MAOZ-C.PIZ-c.zongkd) JINGZ,C.SAMCODE,C.JIHKJB_ID,C.DIANCXXB_ID,C.MEIKXXB_ID from RL_YS_CHEPB C ,RL_YS_CHEPB_QC Q \n"
                    + "where C.ID in (select YUANDJ_ID from GX_CHURKDB_YUNSDJ  where  RUKDBH = '" + map.get("id") + "' and YUANDJLX = 1 ) \n"
                    + " and C.ID = Q.CHEPB_ID group by C.GONGYSB_ID,C.FAZ_ID,ZHONGCSJ,QINGCSJ,C.PINZB_ID,C.SAMCODE,C.JIHKJB_ID,C.DIANCXXB_ID,C.MEIKXXB_ID ) group by GONGYSB_ID,FAZ_ID,ZHONGCSJ,QINGCSJ,PINZB_ID,SAMCODE,JIHKJB_ID,DIANCXXB_ID,MEIKXXB_ID";
            Map<String, Object> maps = jdbcTemplate.queryForMap(chepb_sql);
            String gongysb_id = maps.get("GONGYSB_ID").toString();
            String faz_id = maps.get("FAZ_ID").toString();
            String zhongcsj = maps.get("ZHONGCSJ").toString();
            String qingcsj = maps.get("QINGCSJ").toString();
            String pinzb_id = maps.get("PINZB_ID").toString();
            String piaoz = maps.get("PIAOZ").toString();
            String ches = maps.get("CHES").toString();
            double jingz = Double.parseDouble(maps.get("JINGZ").toString());
            String jihkjb_id = maps.get("JIHKJB_ID").toString();
            String diancxxb_id = maps.get("DIANCXXB_ID").toString();
            String meikxxb_id = maps.get("MEIKXXB_ID").toString();
            // 查询车号
            List<Map<String, Object>> lst_cheph = jdbcTemplate
                    .queryForList("select C.CHEPH from RL_YS_CHEPB C where C.ID in (select YUANDJ_ID from GX_CHURKDB_YUNSDJ  where  RUKDBH = '"
                            + map.get("id") + "'  and YUANDJLX = 1) ");
            String daibch = "";
            if (lst_cheph != null) {
                if (lst_cheph.size() == 1) {
                    daibch = lst_cheph.get(0).get("CHEPH").toString();
                } else {
                    daibch += lst_cheph.get(0).get("CHEPH").toString();
                    daibch += "-";
                    daibch += lst_cheph.get(lst_cheph.size() - 1).get("CHEPH")
                            .toString();
                }
            }
            // 查询车皮表ID
            List<Map<String, Object>> lst_chepbId = jdbcTemplate
                    .queryForList("select C.ID from RL_YS_CHEPB C where C.ID in (select YUANDJ_ID from GX_CHURKDB_YUNSDJ  where  RUKDBH = '"
                            + map.get("id") + "'  and YUANDJLX = 1)");
            // 查询质量
            Map<String, Object> zhilb = jdbcTemplate
                    .queryForMap("select QNET_AR,star STD,MT from RL_HY_HUAYDB  where HUAYBM = (select MUBBM from GX_CHEP_CAIZHBMB where YUANBM = "
                            + "(select MUBBM from GX_CHEP_CAIZHBMB where YUANBM = '"
                            + maps.get("SAMCODE") + "'))");
            String jiesrz = zhilb.get("QNET_AR").toString();
            logger.info("**********结算热值：" + jiesrz + "**********");
            String jieslf = zhilb.get("STD").toString();
            logger.info("**********结算硫分：" + jieslf + "**********");
            /*
             * 计算热值增扣和硫分增扣
			 */
            String className = jdbcTemplate
                    .queryForObject(
                            "select URL from RL_HT_PRICE_COMPONENT where ID =(select PRICE_COMPONENT_ID from RL_HT_PRICE_SCHEME  where po_sub_id = "
                                    + caigddb_id
                                    + " and PRICE_ITEM_ID = (select ID from RL_HT_PRICE_ITEM where lower(CODE) = 'qnet_ar'))",
                            String.class);
            Icalculator calculator = (Icalculator) StaticApplication
                    .getBean(className);
            HashMap<String, Object> mapParas = new HashMap<String, Object>();
            mapParas.put("qnet_ar", jiesrz);
            PriceBean priceBean = calculator.computePrice(caigddb_id, mapParas);
            double rezzk = Double.parseDouble(df.format(priceBean.getPriceChange()));
            logger.info("**********热值增扣：" + rezzk + "**********");

            String className2 = jdbcTemplate
                    .queryForObject(
                            "select URL from RL_HT_PRICE_COMPONENT where ID =(select PRICE_COMPONENT_ID from RL_HT_PRICE_SCHEME  where po_sub_id = "
                                    + caigddb_id
                                    + " and PRICE_ITEM_ID = (select ID from RL_HT_PRICE_ITEM where lower(CODE) = 'star'))",
                            String.class);
            Icalculator calculator2 = (Icalculator) StaticApplication
                    .getBean(className2);
            HashMap<String, Object> mapParas2 = new HashMap<String, Object>();
            mapParas2.put("star", jieslf);
            PriceBean priceBean2 = calculator2.computePrice(caigddb_id,
                    mapParas2);
            double liufzk = Double.parseDouble(df.format(priceBean2.getPriceChange()));
            logger.info("**********硫分增扣：" + liufzk + "**********");

            double jiesje = Double.parseDouble(df.format(hetjg + rezzk + liufzk)) * Math.round(jingz);
            // 插入日结算表RL_JS_RIJSDB
            jdbcTemplate.update("insert into RL_JS_RIJSDB(ID,JIESBH,GONGYSB_ID,MEIKXXB_ID,FAZ_ID,PINZB_ID,ZHONGCSJ,QINGCSJ,"
                    + "PIAOZ,CHES,DAIBCH,JIESRZ,JIESLF,JIESSL,REZZK,LIUFZK,JIESJG,JIESJE,CAOZRQ,JIHKJB_ID,JINGZ,HETJG,DIANCXXB_ID,CHANGRLJBR,CHANGRLJSRQ,GONGS,GONGS1,hetbh) values("
                    + newId
                    + ",'"
                    + jiesbh
                    + "',"
                    + gongysb_id
                    + ","
                    + meikxxb_id
                    + ","
                    + faz_id
                    + ","
                    + pinzb_id
                    + ",'"
                    + zhongcsj
                    + "','"
                    + qingcsj
                    + "',"
                    + piaoz
                    + ","
                    + ches
                    + ",'"
                    + daibch
                    + "',"
                    + jiesrz
                    + ","
                    + jieslf
                    + ","
                    + jingz
                    + ","
                    + rezzk
                    + ","
                    + liufzk
                    + ","
                    + (hetjg + rezzk + liufzk)
                    + ","
                    + jiesje
                    + ",'"
                    + DateUtil.format(new Date(),
                    DateFormatType.SIMPLE_TYPE)
                    + "',"
                    + jihkjb_id
                    + ","
                    + jingz
                    + ","
                    + hetjg
                    + ","
                    + diancxxb_id
                    + ",'"
                    + ((Renyxx) map.get("renyxx")).getMingc()
                    + "','"
                    + DateUtil.format(new Date()) + "','" + priceBean.getErrMsg() + "','"
                    + priceBean2.getErrMsg() + "','" + hetb.get("HETBH").toString() + "')");
            // 插入结算单车皮关系表GX_JIESDB_CHEPB
            for (int i = 0; i < lst_chepbId.size(); i++) {
                jdbcTemplate
                        .update("insert into GX_JIESDB_CHEPB(ID,JIESDB_ID,CHEPB_ID) values ("
                                + Sequence.nextId()
                                + ","
                                + newId
                                + ","
                                + lst_chepbId.get(i).get("ID") + ")");
            }
            jsonArray.add(jiesbh);
            logger.info("====================日结算结束====================");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            jsonArray.add("");
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * 月结算
     */
    @Override
    public JSONObject getYuejsdList(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> list = meikjsDao.getYuejsdList(map);
            if (list != null && list.size() > 0) {
                Object[][] objs = new Object[list.size()][];
                for (int i = 0; i < list.size(); i++) {
                    objs[i] = new Object[10];

                    Map<String, Object> maps = list.get(i);
                    objs[i][0] = maps.get("CAOZRQ");
                    objs[i][1] = maps.get("JIESBH");
                    objs[i][2] = maps.get("GONGYSB_ID");
                    objs[i][3] = maps.get("PINZ");
                    objs[i][4] = maps.get("CHES");
                    objs[i][5] = maps.get("JIESSL");
                    objs[i][6] = maps.get("JIESRZ");
                    objs[i][7] = maps.get("JIESLF");
                    objs[i][8] = maps.get("JIESJG");
                    objs[i][9] = maps.get("JIESJE");
                }
                jsonMap.put("data", objs);
            } else {
                jsonMap.put("data", "");
            }
        } catch (Exception e) {
            jsonMap.put("data", "");
            e.printStackTrace();
        }

        jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }

    /**
     * 结算
     */
    @Override
    public JSONObject getjsdList(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> list = meikjsDao.getjsdList(map);
            if (list != null && list.size() > 0) {
                jsonMap.put("data", list);
            } else {
                jsonMap.put("data", "");
            }
        } catch (Exception e) {
            jsonMap.put("data", "");
            e.printStackTrace();
        }

        jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }

    @Override
    @Transactional
    public JSONArray create(Map<String, Object> map) {
        JSONArray jsonArray = new JSONArray();
        Diancxx diancxx = (Diancxx) JSONObject.toBean(JSONObject.fromObject(map.get("diancxx")), Diancxx.class);
        Renyxx renyxx = (Renyxx) JSONObject.toBean(JSONObject.fromObject(map.get("renyxx")), Renyxx.class);
        try {
            List<Map<String, Object>> list = meikjsDao.getYuejs(map);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> yuejsd = list.get(i);
                    String newId = Sequence.nextId();
                    yuejsd.put("ID", newId);

                    // 生成结算编号---------------------------
                    String jiesbh = "GD-JS-"
                            + diancxx.getPiny()
                            + "-"
                            + DateUtil.format(new Date(), DateFormatType.SIMPLE_TYPE_MONTH).replace("-", "") + "-";
                    int xuh = 0;
                    List<Map<String, Object>> xuhList = jdbcTemplate.queryForList(
                            "select nvl(max(substr(jiesbh,19,20)),0)+1 xuh from RL_JS_YUEJSDB where substr(jiesbh,0,18)='" + jiesbh + "'");
                    if (xuhList.size() != 0) {
                        xuh = Integer.parseInt(xuhList.get(0).get("XUH").toString());
                    }
                    if (xuh < 10) {
                        jiesbh += "0" + xuh;
                    } else {
                        jiesbh += xuh;
                    }
                    //-----------------------------------------------
                    yuejsd.put("JIESBH", jiesbh);
                    Double jiessl = Double.parseDouble(yuejsd.get("JIESSL").toString());
                    Double jiesjg = Double.parseDouble(yuejsd.get("JIESJG").toString());
                    Double hetjg = Double.parseDouble(yuejsd.get("HETJG").toString());
//                    Double rezzk=Double.parseDouble(yuejsd.get("REZZK").toString());
                    Double liufzk = -Double.parseDouble(yuejsd.get("LIUFZK").toString());
                    yuejsd.put("LIUFZK", liufzk);
                    Double rezzk = hetjg - jiesjg - liufzk;
                    yuejsd.put("REZZK", rezzk);
                    yuejsd.put("MEIKJE", jiessl * jiesjg / 1.17);
                    yuejsd.put("SHUIK", jiessl * jiesjg / 1.17 * 0.17);
                    yuejsd.put("CHANGRLJBR", renyxx.getQuanc());
                    meikjsDao.insertYuejsd(yuejsd);
                    String[] rijsd_id = yuejsd.get("RIJSD_ID").toString().split(",");
                    for (int j = 0; j < rijsd_id.length; j++) {
                        jdbcTemplate.update("insert into GX_RIJSD_YUEJSD(YUEJSDB_ID,RIJSDB_ID) values(" + newId + "," + rijsd_id[j] + ")");
                    }
                }
            }
            jsonArray.add("success");
        } catch (Exception e) {
            jsonArray.add("");
            e.printStackTrace();
        }
        return jsonArray;
    }

    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat df2 = new DecimalFormat("0");
    private DecimalFormat df3 = new DecimalFormat("0.0");

    @Override
    public JSONArray getRijstz(Map<String, Object> map) {
        String subStr = " and qingcsj between '" + map.get("sDate") + "' and '"
                + map.get("eDate") + "' ";
        if (!"".equals(map.get("dianc").toString())) {
            subStr += " and meikxxb_id = " + map.get("dianc");
        }
        if (!"".equals(map.get("gongys").toString())) {
            subStr += " and pinzb_id = " + map.get("gongys");
        }

        List<String[][]> rtnList = new ArrayList<String[][]>();
        String sql="SELECT DISTINCT MEIKXXB_ID FROM RL_JS_RIJSDB WHERE 1=1" + subStr;
        List<Map<String, Object>> list = jdbcTemplate .queryForList(sql);
        try {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> rt = list.get(i);
                sql="SELECT JS.ID,M.MINGC MEIKMC,JS.QINGCSJ,P.MINGC PINZMC,JS.JINGZ,JS.JIESJG,JS.JIESJE FROM RL_JS_RIJSDB JS,MEIKXXB M,PINZB P "
                        + "WHERE JS.MEIKXXB_ID = M.ID AND JS.PINZB_ID = P.ID AND JS.MEIKXXB_ID = "
                        + rt.get("MEIKXXB_ID") + subStr + " ORDER BY JS.QINGCSJ ASC";
                List<Map<String, Object>> jiesdList = jdbcTemplate.queryForList(sql);
                String[][] arrData = new String[jiesdList.size() + 1][12];

                double jingz = 0;
                double jiesjg = 0;
                String meikmc = "";
                double qnet_ar = 0;
                double mt = 0;
                double aar = 0;
                double var = 0;
                double star = 0;
                for (int j = 0; j < jiesdList.size(); j++) {
                    Map<String, Object> jiesd = jiesdList.get(j);
                    sql="select round(h.qnet_ar*1000/4.1816,0) qnet_ar,h.mt,h.aar,ROUND(h.VAD*(100-h.MT)/(100-h.MAD),2) VAR,ROUND(h.STAD*(100-h.MT)/(100-h.MAD),2) STAR, "
                            + "G.YUANBM from rl_hy_huaydb h,GX_CHEP_CAIZHBMB g where huaybm = (select mubbm from GX_CHEP_CAIZHBMB where yuanbm = ("
                            + "select mubbm from GX_CHEP_CAIZHBMB where yuanbm = (select distinct samcode from rl_ys_chepb where id in(select chepb_id from GX_JIESDB_CHEPB where jiesdb_id = "
                            + jiesd.get("ID")
                            + "))))and H.HUAYBM = G.MUBBM";
                    Map<String, Object> zhil = jdbcTemplate.queryForMap(sql);

                    arrData[j][0] = jiesd.get("MEIKMC").toString();
                    meikmc = jiesd.get("MEIKMC").toString();
                    arrData[j][1] = jiesd.get("QINGCSJ").toString();
                    arrData[j][2] = jiesd.get("PINZMC").toString();
                    arrData[j][3] = zhil.get("YUANBM").toString();
                    arrData[j][4] = jiesd.get("JINGZ").toString();
                    jingz += Double.parseDouble(jiesd.get("JINGZ").toString());
                    arrData[j][5] = zhil.get("QNET_AR").toString();
                    qnet_ar += Double.parseDouble(jiesd.get("JINGZ").toString())
                            * Double.parseDouble(zhil.get("QNET_AR").toString());
                    arrData[j][6] = zhil.get("MT").toString();
                    mt += Double.parseDouble(jiesd.get("JINGZ").toString())
                            * Double.parseDouble(zhil.get("MT").toString());
                    arrData[j][7] = zhil.get("AAR").toString();
                    aar += Double.parseDouble(jiesd.get("JINGZ").toString())
                            * Double.parseDouble(zhil.get("AAR").toString());
                    arrData[j][8] = zhil.get("VAR").toString();
                    var += Double.parseDouble(jiesd.get("JINGZ").toString())
                            * Double.parseDouble(zhil.get("VAR").toString());
                    arrData[j][9] = zhil.get("STAR").toString();
                    star += Double.parseDouble(jiesd.get("JINGZ").toString())
                            * Double.parseDouble(zhil.get("STAR").toString());
                    arrData[j][10] = df.format(jiesd.get("JIESJG"));
                    jiesjg += Double.parseDouble(jiesd.get("JINGZ").toString())
                            * Double.parseDouble(jiesd.get("JIESJG").toString());
                    arrData[j][11] = df.format(jiesd.get("JIESJE"));
                }
                if (jiesdList.size() > 0) {
                    arrData[jiesdList.size()][0] = meikmc;
                    arrData[jiesdList.size()][1] = "小计";
                    arrData[jiesdList.size()][2] = "";
                    arrData[jiesdList.size()][3] = "";
                    arrData[jiesdList.size()][4] = df.format(jingz);
                    arrData[jiesdList.size()][5] = df2.format(qnet_ar / jingz);
                    arrData[jiesdList.size()][6] = df3.format(mt / jingz);
                    arrData[jiesdList.size()][7] = df.format(aar / jingz);
                    arrData[jiesdList.size()][8] = df.format(var / jingz);
                    arrData[jiesdList.size()][9] = df.format(star / jingz);
                    arrData[jiesdList.size()][10] = df.format(jiesjg / jingz);
                    arrData[jiesdList.size()][11] = df.format(jiesjg / jingz
                            * jingz);

                    rtnList.add(arrData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count = 0;
        for (int x = 0; x < rtnList.size(); x++) {
            String[][] strs = rtnList.get(x);
            count += strs.length;
        }

        int index = 0;
        String[][] arrDatas = new String[count][12];
        for (int x = 0; x < rtnList.size(); x++) {
            String[][] strs = rtnList.get(x);
            for (int k = 0; k < strs.length; k++) {
                arrDatas[index] = strs[k];
                index++;
            }
        }

        Report rt = new Report();
        String ArrHeader[][] = new String[3][12];
        ArrHeader[0] = new String[]{"矿别", "入厂煤日期", "品种", "来样编号",
                "入厂煤量<br>(吨)", "收到基低位热量<br>(kcal/kg)", "收到基水分", "收到基灰",
                "收到基挥发分", "收到基硫", "煤价", "金额"};
        ArrHeader[1] = new String[]{"矿别", "入厂煤日期", "品种", "来样编号",
                "入厂煤量<br>(吨)", "Qnet,ar", "(%)", "(%)", "(%)", "(%)", "煤价",
                "金额"};
        ArrHeader[2] = new String[]{"矿别", "入厂煤日期", "品种", "来样编号",
                "入厂煤量<br>(吨)", "", "Mt", "Aar", "Var", "Star", "煤价", "金额"};

        int ArrWidth[] = new int[]{150, 100, 100, 100, 70, 100, 65, 60, 80,
                60, 65, 100};
        rt.setBody(new Table(arrDatas, 3, 0, 0, 12));
        rt.body.setHeaderData(ArrHeader);
        rt.body.setPageRows(15);
        int pageCount = rt.getPages();
        rt.body.ShowZero = false;
        rt.body.setColAlign(1, Table.ALIGN_CENTER);
        rt.body.setColAlign(2, Table.ALIGN_CENTER);
        rt.body.setColAlign(3, Table.ALIGN_CENTER);
        rt.body.setColAlign(4, Table.ALIGN_CENTER);
        for (int i = 5; i <= 12; i++) {
            rt.body.setColAlign(i, Table.ALIGN_RIGHT);
        }
        rt.setTitle("日结算统计台帐", ArrWidth);
        rt.body.setWidth(ArrWidth);
        rt.body.mergeFixedRowCol();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("html", rt.getAllPagesHtml(ArrWidth));
        resultMap.put("pageCount", pageCount);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(resultMap);
        return jsonArray;
    }

    @Override
    public JSONArray getHetong(Map<String, Object> map) {

        List<Map<String, Object>> list = meikjsDao.getHetong(map);
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            Combobox combobox = new Combobox("请选择", "-1");
            jsonArray.add(combobox);
            for (int i = 0; i < list.size(); i++) {
                combobox = new Combobox(list.get(i).get("HETBH").toString(), list
                        .get(i).get("HETBH").toString());
                jsonArray.add(combobox);
            }
        }
        return jsonArray;
    }

    @Override
    public List<Map<String, Object>> getGongysList(Map<String, Object> map) {
        String sql=" SELECT DISTINCT C.GONGYSB_ID,\n" +
                "        C.GONGYSMC mingc\n" +
                "   FROM RL_YS_CHEPB C,\n" +
                "        (SELECT *\n" +
                "           FROM RL_YS_CHEPB_QC\n" +
                "          WHERE to_char(Trunc(TO_DATE(zhongcsj, 'yyyy-mm-dd hh24:mi:ss')),\n" +
                "                        'yyyy-mm-dd') >= '" + map.get("sDate") + "'\n" +
                "            AND to_char(Trunc(TO_DATE(zhongcsj, 'yyyy-mm-dd hh24:mi:ss')),\n" +
                "                        'yyyy-mm-dd') <= '" + map.get("eDate") + "') QC,\n" +
                "        RL_HY_HUAYDB HY,\n" +
                "        VM_CAIZHBM HYBM,\n" +
                "        VM_KUCMX KC\n" +
                "  WHERE C.ID = QC.CHEPB_ID\n" +
                "    AND C.SAMCODE = HYBM.CAIYBM\n" +
                "    AND HYBM.HUAYBM = HY.HUAYBM\n" +
                "    AND C.ID = KC.CHEPB_ID\n" +
                "    AND kc.RUKDBH IS NOT NULL\n" +
                "    AND kc.RL_JS_YUEJSDB_ID IS NULL\n" ;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        Map<String, Object> map2 = new HashMap<String, Object>();
//        map2.put("GONGYSB_ID", -1);
//        map2.put("MINGC", "全部");
//        list.add(map2);
        return list;
    }

    @Override
    public List<Map<String, Object>> getPinzList(Map<String, Object> map) {
        List<Map<String, Object>> list = meikjsDao.getPinzList(map);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("PINZB_ID", -1);
        map2.put("MINGC", "全部");
        list.add(map2);
        return list;
    }

    @Override
    public List<Map<String, Object>> getHetbh(Map<String, Object> map) {
        List<Map<String, Object>> list = meikjsDao.getHetbhList(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> getZafjsList(Map<String, Object> map) {
        return meikjsDao.getZafjsList(map);
    }

    @Override
    public List<Map<String, Object>> getYewlxList() {
        return meikjsDao.getYewlxList();
    }

    @Override
    public List<Map<String, Object>> getZafjswhList(Map<String, Object> m) {
        return meikjsDao.getZafjswhList(m);
    }

    @Override
    public void saveZafjswhList(Map<String, Object> map, List<Map<String, Object>> list) {
        for (Map<String, Object> m : list) {
            if (m.get("ID") != null) {
                meikjsDao.updateZafjs(m);
            } else {
                m.put("ID", Sequence.nextId());
                meikjsDao.insertZafjs(m);
            }
        }
    }

    @Override
    public void zafJies(Map<String, Object> map) {
        map.put("zafjsb_id", Sequence.nextId());
        meikjsDao.zafJies(map);
        meikjsDao.insertGXChurkdbZafjsb(map);
    }

    @Override
    public void deleteZafjs(Map<String, Object> map) {
        meikjsDao.deleteZafjs(map);
        meikjsDao.deteteGXChurkdbZafjsb(map);
    }

    @Override
    @Transactional
    public void updateDatas(Map<String, Object> jiesdMap, List<Map<String, Object>> zengkmxList) {
        zidrkDao.updateData(jiesdMap, "rl_js_yuejsdb");
        for (Map<String, Object> zengkmx : zengkmxList) {
            zidrkDao.updateData(zengkmx, "RL_JS_JIESZBSJB");
        }
    }


}