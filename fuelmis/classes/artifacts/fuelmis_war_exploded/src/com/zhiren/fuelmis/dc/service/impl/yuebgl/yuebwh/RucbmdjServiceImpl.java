package com.zhiren.fuelmis.dc.service.impl.yuebgl.yuebwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.zhiren.fuelmis.dc.utils.math.Math;
import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.RucbmdjDao;
import com.zhiren.fuelmis.dc.service.impl.yuebgl.YuebService;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRucbmdjService;
import com.zhiren.fuelmis.dc.utils.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.springframework.transaction.annotation.Transactional;

/**
 * @author 陈宝露
 */
@Service
public class RucbmdjServiceImpl extends YuebService implements IRucbmdjService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RucbmdjDao rucbmdjDao;

    @Override
    @Transactional
    public void createData(Map<String, Object> cd) {
        List<Map<String, Object>> list = rucbmdjDao.getSecondData(cd);
        for (Map<String, Object> map : list) {
            map.put("CAOZLX", 0);
            map.put("ID", Sequence.nextId());
            this.saveData(map, "YUEJSBMDJ", "yuetjkjb_id,fenx");
            this.saveData(map, "YUEJSBMDJYF", "yuetjkjb_id,fenx");
        }
    }

    @Override
    @Transactional
    public void saveData(List<ConcurrentMap> list) throws Exception {
        for (int i = 2; i < list.size(); i += 2) {
            Map<String, Object> beny = list.get(i);
            Map<String, Object> byleij = list.get(i+1);
            Map<String, Object> leij = rucbmdjDao.getLastMonthLeij(beny.get("YUETJKJB_ID").toString());
            leij.put("ID", byleij.get("ID"));
            leij.put("YUETJKJB_ID", byleij.get("YUETJKJB_ID"));
            leij.put("FENX", "累计");
            Object leijjsl = leij.get("JIESL");
            for (Map.Entry<String, Object> entry : beny.entrySet()) {
                String key = entry.getKey();//本月
                Object value = entry.getValue();
                //计算本月
                double daoczhj = Math.getGongsjg(beny, "MEIJ+YUNJ +DAOZZF+ZAF+QIT+KUANGQYF");
                beny.put("DAOCZHJ", daoczhj);
                double biaomdj = 0;
                double buhsbmdj = 0;
                if (!beny.get("QNET_AR").toString().equals("0")) {
                    biaomdj = Math.getGongsjg(beny, "DAOCZHJ*29.271/QNET_AR");
                    buhsbmdj = Math.getGongsjg(beny, "(DAOCZHJ-MEIJS-YUNJS-ZAFS)*29.271/QNET_AR");
                }
                beny.put("BIAOMDJ", biaomdj);
                beny.put("BUHSBMDJ", buhsbmdj);
                //计算累计
                if (key.equals("JIESL")) {
                    leij.put(key, Math.add(leij.get(key), value));
                }
                if (key.equals("MEIJ") || key.equals("MEIJS") || key.equals("KUANGQYF") || key.equals("YUNJ") ||
                        key.equals("YUNJS") || key.equals("DAOZZF") || key.equals("ZAF") || key.equals("ZAFS") ||
                        key.equals("QIT") || key.equals("QNET_AR") || key.equals("BIAOMDJ") || key.equals("BUHSBMDJ")) {
                    Map<String, Object> vMap = new HashMap<String, Object>();
                    vMap.put("LEIJ", leij.get(key));
                    vMap.put("LEIJJSL", leijjsl);
                    vMap.put("BENY", beny.get(key));
                    vMap.put("BENYJSL", value);
                    vMap.put("JIESL", leij.get("JIESL"));
                    if (vMap.get("JIESL").toString().equals("0")) {
                        leij.put(key, 0);
                    } else {
                        leij.put(key, Math.getGongsjg(vMap, "(LEIJ*LEIJJSL+BENY*BENYJSL)/JIESL"));
                    }

                }
            }
            this.saveData(beny, "YUEJSBMDJ", "yuetjkjb_id,fenx");
            this.saveData(beny, "YUEJSBMDJYF", "yuetjkjb_id,fenx");
            this.saveData(leij, "YUEJSBMDJ", "yuetjkjb_id,fenx");
            this.saveData(leij, "YUEJSBMDJYF", "yuetjkjb_id,fenx");
        }
    }

    @Deprecated
    public String saveData(Map<String, Object> map) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#");
        java.text.DecimalFormat df2 = new java.text.DecimalFormat("#.00");
        String riq = map.get("riq").toString();
        String[] dates = riq.split("-");
        String year = dates[0];
        String month = dates[1];
        String ret = "";
        StringBuffer sql = new StringBuffer();
        JSONArray ja = JSONArray.fromObject(map.get("rucbmdjList").toString());
        for (int i = 0; i < ja.size(); i++) {
            JSONObject rs = (JSONObject) ja.get(i);
            Integer jiesl = 0;
            double meij = 0;
            double meijs = 0;
            double yunj = 0;
            double yunjs = 0;
            double daozzf = 0;
            double zaf = 0;
            double zafs = 0;
            double qit = 0;
            double kuangqyf = 0;
            double qnet_ar = 0;
            double biaomdj = 0;
            double buhsbmdj = 0;
            double _qnet_ar = rs.getDouble("QNET_AR");
            double _biaomdj = 0;
            double _buhsbmdj = 0;
            if (_qnet_ar > 0) {
                //_biaomdj = rs.getDouble("BIAOMDJ");
                //_buhsbmdj = rs.getDouble("BUHSBMDJ");
                double daoczhj = Double.parseDouble(rs.get("MEIJ").toString()) +
                        Double.parseDouble(rs.get("YUNJ").toString()) +
                        Double.parseDouble(rs.get("DAOZZF").toString())
                        + Double.parseDouble(rs.get("ZAF").toString()) +
                        Double.parseDouble(rs.get("QIT").toString()) +
                        Double.parseDouble(rs.get("KUANGQYF").toString());
                if (null != rs.get("QNET_AR") && rs.get("QNET_AR") != "0") {
                    _biaomdj = daoczhj * 29.271 / (Double.parseDouble(rs.get("QNET_AR").toString()));
                    _buhsbmdj = (daoczhj - Double.parseDouble(rs.get("MEIJS").toString())
                            - Double.parseDouble(rs.get("YUNJS").toString())
                            - Double.parseDouble(rs.get("ZAFS").toString())) * 29.271 / Double.parseDouble(rs.get("QNET_AR").toString());
                }
            }
            if ("-1".equals(rs.getString("ID"))) {
                continue;
            }
            if ("".equals(rs.getString("ID")) || "0".equals(rs.getString("ID"))) {
                sql.append("insert into yuejsbmdj(id, fenx, yuetjkjb_id, jiesl, meij, meijs, kuangqyf, " +
                        "                yunj, yunjs, daozzf, zaf, zafs,qit, qnet_ar, biaomdj, buhsbmdj)\n" +
                        "values(\n" +
                        "getnewid(" + map.get("dianc").toString() + "),\n" +
                        "'" + rs.getString("FENX") + "',\n" +
                        rs.getString("YUETJKJB_ID") + ",\n" +
                        df.format(rs.getDouble("JIESL")) + ",\n" +
                        rs.getDouble("MEIJ") + ",\n" +
                        rs.getDouble("MEIJS") + ",\n" +
                        rs.getDouble("KUANGQYF") + ",\n" +
                        rs.getDouble("YUNJ") + ",\n" +
                        rs.getDouble("YUNJS") + ",\n" +
                        rs.getDouble("DAOZZF") + ",\n" +
                        rs.getDouble("ZAF") + ",\n" +
                        rs.getDouble("ZAFS") + ",\n" +
                        rs.getDouble("QIT") + ",\n" +
                        _qnet_ar + ",\n" +
                        df2.format(_biaomdj) + ",\n" +
                        _buhsbmdj + "\n" +
                        ");\n");
                if (!"累计".equals(rs.getString("FENX"))) {
                    sql.append("INSERT INTO YUEJSBMDJYF (ID, FENX, YUETJKJB_ID, HUOCYJ, HUOCYJS, QICYJ, QICYJS)\n" +
                            "(SELECT ID, FENX, YUETJKJB_ID, " + rs.getDouble("HUOCYJ") + " HUOCYJ, " + rs.getDouble("HUOCYJS") + " HUOCYJS,\n" +
                            " " + rs.getDouble("QICYJ") + " QICYJ, " + rs.getDouble("QICYJS") + " QICYJS \n" +
                            "FROM YUEJSBMDJ WHERE FENX = '" + rs.getString("FENX") + "' AND YUETJKJB_ID = " + rs.getString("YUETJKJB_ID") + ");\n");
                }
            } else {
                jiesl = Integer.parseInt(df.format(rs.getDouble("JIESL")));
                meij = rs.getDouble("MEIJ");
                meijs = rs.getDouble("MEIJS");
                yunj = rs.getDouble("YUNJ");
                yunjs = rs.getDouble("YUNJS");
                daozzf = rs.getDouble("DAOZZF");
                zaf = rs.getDouble("ZAF");
                zafs = rs.getDouble("ZAFS");
                qit = rs.getDouble("QIT");
                kuangqyf = rs.getDouble("KUANGQYF");
                qnet_ar = _qnet_ar;
                biaomdj = _biaomdj;
                buhsbmdj = _buhsbmdj;
                sql.append("update yuejsbmdj set " +
                        "jiesl = " + jiesl +
                        ",meij = " + meij +
                        ",meijs = " + meijs +
                        ",yunj = " + yunj +
                        ",yunjs = " + yunjs +
                        ",daozzf = " + daozzf +
                        ",zaf = " + zaf +
                        ",zafs = " + zafs +
                        ",qit = " + qit +
                        ",qnet_ar = " + qnet_ar +
                        ",biaomdj = " + df2.format(biaomdj) +
                        ",buhsbmdj = " + buhsbmdj +
                        ",kuangqyf = " + kuangqyf +
                        " where yuetjkjb_id =" + rs.getString("YUETJKJB_ID") + " and fenx = '" + rs.getString("FENX") + "';\n");
                if (!"累计".equals(rs.getString("FENX"))) {
                    sql.append("update YUEJSBMDJYF set " +
                            "HUOCYJ = " + rs.getDouble("HUOCYJ") +
                            ",HUOCYJS = " + rs.getDouble("HUOCYJS") +
                            ",QICYJ = " + rs.getDouble("QICYJ") +
                            ",QICYJS = " + rs.getDouble("QICYJS") +
                            " where yuetjkjb_id =" + rs.getString("YUETJKJB_ID") + " and fenx = '" + rs.getString("FENX") + "';\n");
                }
            }

        }

        if (!"".equals(sql.toString()) && sql != null) {
            String sql_value = sql.toString().substring(0, sql.toString().length() - 2);
            String[] sqls = sql_value.toString().split(";");
            int[] flag = jdbcTemplate.batchUpdate(sqls);

            if (flag.length > 0) {
                sql.setLength(0);
                for (int j = 0; j < ja.size(); j++) {
                    JSONObject rs = (JSONObject) ja.get(j);
                    if ("累计".equals(rs.getString("FENX"))) {
                        //-------------------计算累计-------------------------------------------------------
                        String sq = "select\n" +
                                "round_new(sum(jiesl),2) as jiesl,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(meij*jiesl)/sum(jiesl)),2) meij,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(meijs*jiesl)/sum(jiesl)),2) meijs,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(yunj*jiesl)/sum(jiesl)),2) yunj,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(yunjs*jiesl)/sum(jiesl)),2) yunjs,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(daozzf*jiesl)/sum(jiesl)),2) daozzf,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(zaf*jiesl)/sum(jiesl)),2) zaf,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(zafs*jiesl)/sum(jiesl)),2) zafs,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(qit*jiesl)/sum(jiesl)),2) qit,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(qnet_ar*jiesl)/sum(jiesl)),2) qnet_ar,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(biaomdj*jiesl)/sum(jiesl)),2) biaomdj,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(buhsbmdj*jiesl)/sum(jiesl)),2) buhsbmdj,\n" +
                                "round_new(decode(sum(jiesl),0,0,sum(kuangqyf*jiesl)/sum(jiesl)),2) kuangqyf\n" +
                                "  from yuejsbmdj y,yuetjkjb yt,(select gongysb_id,jihkjb_iD,pinzb_id,yunsfsb_id from yuetjkjb where id= " + rs.getString("YUETJKJB_ID") + ")yt2\n" +
                                " where y.yuetjkjb_id=yt.id\n" +
                                "	and yt.gongysb_id=yt2.gongysb_id\n" +
                                "	and yt.jihkjb_id=yt2.jihkjb_id\n" +
                                "	and yt.pinzb_id=yt2.pinzb_id\n" +
                                "	and yt.yunsfsb_id=yt2.yunsfsb_id\n" +
                                "   and substr(yt.riq,0,7) between '" + year + "-01'\n" +
                                "   and '" + year + "-" + month + "'\n" +
                                "   and y.fenx='本月'\n" +
                                "   and yt.diancxxb_id=" + map.get("dianc");
                        List<?> list = jdbcTemplate.queryForList(sq);
                        boolean flay = true;
                        if (list.size() > 0) {
                            Map thismap = (Map) list.get(0);
                            if (null == thismap.get("jiesl") || null == thismap.get("MEIJ") || null == thismap.get("MEIJS") || null == thismap.get("YUNJ") || null == thismap.get("YUNJS") || null == thismap.get("DAOZZF") || null == thismap.get("JIESL")
                                    || null == thismap.get("ZAF") || null == thismap.get("ZAFS") || null == thismap.get("QIT") || null == thismap.get("KUANGQYF")) {
                                flay = false;
                            }
                        }
                        if (flay) {
                            Map rsl = (Map) list.get(0);
                            double daoczhj = Double.parseDouble(rsl.get("MEIJ").toString()) + Double.parseDouble(rsl.get("YUNJ").toString()) + Double.parseDouble(rsl.get("DAOZZF").toString())
                                    + Double.parseDouble(rsl.get("ZAF").toString()) + Double.parseDouble(rsl.get("QIT").toString()) + Double.parseDouble(rsl.get("KUANGQYF").toString());
                            double biaomdj = 0;
                            double buhsbmdj = 0;

                            if (null != rsl.get("QNET_AR") && rsl.get("QNET_AR") != "0") {
                                biaomdj = daoczhj * 29.271 / (Double.parseDouble(rsl.get("QNET_AR").toString()));
                                buhsbmdj = (daoczhj - Double.parseDouble(rsl.get("MEIJS").toString()) - Double.parseDouble(rsl.get("YUNJS").toString()) - Double.parseDouble(rsl.get("ZAFS").toString())) * 29.271 / Double.parseDouble(rsl.get("QNET_AR").toString());
                            }

                            sql.append("update yuejsbmdj set " +
                                    "jiesl = " + df.format(Double.parseDouble(rsl.get("JIESL").toString())) +
                                    ",meij = " + rsl.get("MEIJ").toString() +
                                    ",meijs = " + rsl.get("MEIJS").toString() +
                                    ",yunj = " + rsl.get("YUNJ").toString() +
                                    ",yunjs = " + rsl.get("YUNJS").toString() +
                                    ",daozzf = " + rsl.get("DAOZZF").toString() +
                                    ",zaf = " + rsl.get("ZAF").toString() +
                                    ",zafs = " + rsl.get("ZAFS").toString() +
                                    ",qit = " + rsl.get("QIT").toString() +
                                    ",qnet_ar = " + rsl.get("QNET_AR").toString() +
                                    ",biaomdj = round_new(" + biaomdj + ",2)" +
                                    ",buhsbmdj = round_new(" + buhsbmdj + ",2)" +
                                    ",kuangqyf = " + rsl.get("KUANGQYF").toString() +
                                    " where yuetjkjb_id =" + rs.getString("YUETJKJB_ID") + " and fenx = '" + rs.getString("FENX") + "';");
                        }
                        //----------------------------计算累计结束-------------------------------------------
                    }
                }
                if ("".equals(sql) || sql == null || sql.length() < 1) {
                    ret = "保存成功!";
                } else {
                    int flags = jdbcTemplate.update("begin\n" + sql.toString() + "\n end;");
                    if (flags != -1) {
                        ret = "保存成功!";
                    } else {
                        ret = "保存成功,累计数计算失败!";
                    }
                }
            } else {
                ret = "保存失败!";
            }

        }
        return ret;
    }

    @Override
    @Transactional
    public void delData(Map<String, Object> map) {
        this.delYuebData("YUEJSBMDJ", map);
        this.delYuebData("YUEJSBMDJYF", map);
    }

    @Override
    public JSONArray check(Map<String, Object> map) {
        // TODO Auto-generated method stub
        JSONArray jsonArray = new JSONArray();
        String sql = "select s.zhuangt zhuangt	" +
                "	from yuejsbmdj s, yuetjkjb k	" +
                "	where s.yuetjkjb_id = k.id	" +
                "	and k.diancxxb_id = " + map.get("dianc") +
                " and k.riq ='" + map.get("riq") + "'";
        return jsonArray;
    }

}
