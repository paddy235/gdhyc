package com.zhiren.fuelmis.dc.controller.jiesgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.dao.jiesgl.MeikjsDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.hetgl.PriceBean;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.hetgl.Icalculator;
import com.zhiren.fuelmis.dc.service.jiesgl.IMeikjsService;
import com.zhiren.fuelmis.dc.utils.*;
import com.zhiren.fuelmis.dc.utils.formular.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author cocoa
 */
@Controller
@RequestMapping("jiesgl/meikjs")
public class MeikjsController {

    @Autowired
    private IMeikjsService meikjsService;
    @Autowired
    private MeikjsDao meikjsDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

    /**
     * 结算
     */
    @RequestMapping(value = "/getjsdList")
    public void getjsdList(@RequestParam String condition, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map =JSON.parseObject(condition);
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(meikjsService.getjsdList(map).toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/hycDeleteJiesdByJiesdbh")
    public void hycDeleteJiesdByJiesdbh(String jiesdbh, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer;
        String jiesdid = meikjsDao.getJiesdidByJiesdbh(jiesdbh);
        meikjsService.deleteJiesdByJiesdbh(jiesdbh);
        meikjsDao.deleteJieszbsjbByJiesdbh(jiesdbh);
        meikjsDao.udpateChepbByJiesdid(jiesdid);
        meikjsDao.udpateChurkdbByJiesdid(jiesdid);

        try {
            writer = response.getWriter();
            writer.write("删除成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getJiesdlist")
    public @ResponseBody Map<String,Object> getJiesdlist(String jiesbh, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Map<String, Object> jiesd = meikjsService.getjiesd(jiesbh);
            return jiesd;
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
    }

    /**
     * 结算单编号
     */
    @RequestMapping(value = "/getJiesdbh")
    public void getJiesdbh(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer;
        List<Map<String, Object>> list = meikjsService.getJiesdbh();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            Combobox combobox = new Combobox("请选择", -1);
            jsonArray.add(combobox);
            for (int i = 0; i < list.size(); i++) {
                combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("MINGC"));
                jsonArray.add(combobox);
            }
        }
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/hycSaveJiesd")
    public void hycSaveJiesd(String data, String rukdbh, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Map<String, Object> m = JSON.parseObject(data);
            Diancxx diancxx = (Diancxx) request.getSession().getAttribute("diancxx");
            Renyxx renyxx = (Renyxx) request.getSession().getAttribute("user");
            if (null == m.get("DIANCMC") || "".equals(m.get("DIANCMC").toString())) {
                m.put("DIANCMC", diancxx.getMingc());
            }
            m.put("CHANGRLJBR", renyxx.getQuanc());
            m.remove("ZONGHJDX");
            m.remove("MEIKDJDX");
            m.remove("YUNZFHJDX");
            m.remove("HETZBZDJ");
            meikjsService.saveJiesd_hyc(m);
            //保存成功后更新CHEP表和churkdb表
            if(null!=rukdbh){
            	rukdbh = rukdbh.replace("[", "").replace("]", "").replace("\"", "'");
            }
            String jiesdid = m.get("ID").toString();
            Map<String,Object> maps = new HashMap<String,Object>();
            maps.put("jiesdid", jiesdid);
            maps.put("addition", rukdbh);
            if(null!=rukdbh){
            	meikjsDao.udpateChurkdb(maps);
                meikjsDao.udpateChepb(maps);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/getJiesd")
    public @ResponseBody Map<String,Object> getJiesd(@RequestParam(defaultValue = "") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String sql = " SELECT * from RL_JS_yuejsdb where id=" + id;
            Map<String, Object> jiesd = jdbcTemplate.queryForMap(sql);
            sql = "select * from RL_JS_JIESZBSJB where JIESDBH='" + jiesd.get("JIESBH") + "'";
            List<Map<String, Object>> zhibList = jdbcTemplate.queryForList(sql);
            jiesd.put("zhibList", zhibList);
            return jiesd;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/getJiestz")
    public void getJiestz(@RequestParam(defaultValue = "") String search, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer;
        Map<String, Object> map = JSON.parseObject(search);
        try {
            writer = response.getWriter();
            writer.write(meikjsService.getJiestz(map).toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getRijstz")
    public void getRijstz(@RequestParam(defaultValue = "") String sDate, @RequestParam(defaultValue = "") String eDate,
                          @RequestParam(defaultValue = "") String gongys, @RequestParam(defaultValue = "") String dianc,
                          HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sDate", "".equals(sDate) ? DateUtil.getDayOfMonth(null) : sDate);
        map.put("eDate", "".equals(eDate) ? DateUtil.format(new Date(), DateUtil.DateFormatType.SIMPLE_TYPE) : eDate);
        map.put("gongys", "-1".equals(gongys) ? "" : gongys);
        map.put("dianc", "-1".equals(dianc) ? "" : dianc);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(meikjsService.getRijstz(map).toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getJiesList")
    public void getJiesList(@RequestParam(defaultValue = "") String sDate, @RequestParam(defaultValue = "") String eDate,
                            //@RequestParam(defaultValue="") String gongys,@RequestParam(defaultValue="") String caigdd,
                            //@RequestParam(defaultValue="") String hetbh,
                            HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sDate", "".equals(sDate) ? DateUtil.getDayOfMonth(null) : sDate);
        map.put("eDate", "".equals(eDate) ? DateUtil.format(new Date(), DateUtil.DateFormatType.SIMPLE_TYPE) : eDate);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(meikjsService.getJiesList(map).toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/jies")
    public @ResponseBody Map<String, Object> jies(String hetj, String rukdbhs, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Map<String, Object> map = JSON.parseObject(hetj);
            Map<String, Object> jiesdMap = new HashMap<String, Object>();
            rukdbhs = rukdbhs.replace("[", "").replace("]", "").replace("\"", "'");
            //查询验收信息
            String sql = " SELECT\n" +
                    "            substr(min(qc.qingcsj), 0, 10)   fahksrq,\n" +
                    "            substr(max(qc.qingcsj), 0, 10)   FAHJZRQ,\n" +
                    "            substr(min(qc.qingcsj), 0, 10)   qingcsj,\n" +
                    "            substr(min(qc.ZHONGCSJ), 0, 10)  zhongcsj,\n" +
                    "            substr(max(qc.qingcsj), 0, 10)   YANSJZRQ,\n" +
                    "            min(CHEPH)||'至'||max(CHEPH)     daibch,\n" +
                    "            max(faz)                         fazmc,\n" +
                    "            max(FAZ_ID)                      faz_id\n" +
                    "        FROM VM_KUCMX kc, RL_YS_CHEPB chep, RL_YS_CHEPB_QC qc\n" +
                    "        WHERE kc.CHEPB_ID = chep.id\n" +
                    "              AND qc.chepb_id = chep.id\n" +
                    "              AND kc.RUKDBH IN (" + rukdbhs + ")";
            jiesdMap.putAll(jdbcTemplate.queryForMap(sql));
            Diancxx diancxx = (Diancxx) request.getSession().getAttribute("diancxx");
            Renyxx renyxx = (Renyxx) request.getSession().getAttribute("user");
            String id = Sequence.nextId();
            jiesdMap.put("ID", id);
            map.put("diancxx", diancxx);
            map.put("renyxx", renyxx);
            String caigddbSubId = map.get("CAIGDDSUBID").toString();
            //-----------------------------生成结算单编号---------------------------------------------------------------------
            String Pre_JIESBH = "GD-JS-" + diancxx.getPiny() + "-" + sdf.format(new Date()) + "-";
            String JIESBH = jdbcTemplate.queryForObject(
                    " select '" + Pre_JIESBH + "'||replace(nvl(to_char(substr(max(jiesbh),19,2)+1,'00'),'01'),' ','') as jiesbh  \n" +
                            "from Rl_Js_Yuejsdb where jiesbh like '" + Pre_JIESBH + "%'", String.class);
            //--------------------------------------------------------------------------------------------------------------
            //查询化验单
            String sql_huay =  "SELECT c.MEIKXXB_ID, C.MEIKMC, C.GONGYSMC, SUM(C.JINGZ) as JINGZ, SUM(C.MAOZ) as MAOZ, SUM(C.PIz) as PIZ,\n" +
          				  "Round_new(sum(hy.ad*c.jingz)/sum(c.jingz),2) as AD ,\n" + 
          				  "Round_new(sum(hy.ad*c.jingz)/sum(c.jingz),2) as STAR ,\n" + 
          				  "Round_new(sum(Round_new(QNET_AR/0.0041816, 0)*c.jingz)/sum(c.jingz),0) as QNET_AR_DK ,\n" + 
          				  "Round_new(sum(hy.mad*c.jingz)/sum(c.jingz),2) as Mad ,\n" + 
          				  "Round_new(sum(hy.MT*c.jingz)/sum(c.jingz),2)as MT ,\n" + 
          				  "Round_new(sum(hy.Std*c.jingz)/sum(c.jingz),2) as STD\n" + 
          				  "  FROM RL_YS_CHEPB    C,\n" + 
          				  "       RL_YS_CHEPB_QC QC,\n" + 
          				  "       RL_HY_HUAYDB   HY,\n" + 
          				  "       VM_CAIZHBM     HYBM,\n" + 
          				  "       VM_KUCMX       KC\n" + 
          				  " WHERE C.ID = QC.CHEPB_ID\n" + 
          				  "   AND C.SAMCODE = HYBM.CAIYBM(+)\n" + 
          				  "   AND HYBM.HUAYBM = HY.HUAYBM(+)\n" + 
          				  "   AND C.ID = KC.CHEPB_ID(+)\n" + 
          				  "   AND KC.RUKDBH in\n" + 
          				  "       ("+rukdbhs+")\n" + 
          				  "       group by c.MEIKXXB_ID,C.MEIKMC, C.GONGYSMC";

            Map<String, Object> huaydMap = jdbcTemplate.queryForMap(sql_huay);
            map.putAll(huaydMap);
            //查询类名
            sql = "SELECT DISTINCT classname, e.id scheme_id, upper(t.code) AS CODE, t.xuh \n" + 
            	"  FROM RL_HT_PRICE_SCHEME e\n" + 
            	" INNER JOIN RL_HT_PRICE_COMPONENT m\n" + 
            	"    ON e.PRICE_COMPONENT_ID = m.id\n" + 
            	" INNER JOIN rl_ht_price_item t\n" + 
            	"    on e.PRICE_ITEM_ID = t.id\n" + 
            	" WHERE PO_SUB_ID ="+ caigddbSubId +"\n" + 
            	"    order by  xuh ";
            List<Map<String, Object>> schemeList = jdbcTemplate.queryForList(sql);
            double zengkjg = 0;
            List<Map<String, Object>> zengkmxList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> scheme : schemeList) {
            	map.put("HETKHZB", scheme.get("code").toString());
                Icalculator calculator = (Icalculator) StaticApplication.getBean(scheme.get("CLASSNAME").toString());
                PriceBean priceBean = calculator.computePrice(scheme.get("SCHEME_ID").toString(), map);
                //存储结算增扣明细
                Map<String, Object> zengkmx = new HashMap<String, Object>();
                zengkmx.put("ID", Sequence.nextId());
                zengkmx.put("JIESDBH", JIESBH);
                zengkmx.put("LEIB", priceBean.getType());
                zengkmx.put("ZHEJBZ", priceBean.getPrice());
                DecimalFormat    df   = new DecimalFormat("######0.00"); 
                zengkmx.put("ZHEJJE", df.format(priceBean.getPrice() * Double.parseDouble(map.get("JINGZ").toString())));
                zengkmx.put("HETBZ", priceBean.getHetjz());//合同标准
                zengkmx.put("YINGK", df.format(priceBean.getChaz()));//盈亏/相差数量
                zengkmx.put("JIES", priceBean.getZhibValue());//结算标准
                zengkmx.put("CHANGF", priceBean.getZhibValue());//厂方
                zengkmx.put("ZHUANGT", 1);
                zengkjg += priceBean.getPrice();
                zengkmxList.add(zengkmx);
            }
            //查询合同
            sql = "SELECT DISTINCT h.HETBH,h.JIAG hetjg,h.GONGF gongysb_id,h.PINZ_ID pinzb_id,h.diancxxb_id,g.kaihyh,g.zhangh,\n" +
                    "g.quanc gongysmc,g.quanc shoukdw,d.QUANC diancmc \n " +
                    "FROM RL_HT_HETB h\n" +
                    "  INNER JOIN RL_HT_CAIGDDB c ON h.id=c.HETB_ID\n" +
                    "  INNER JOIN RL_HT_CAIGDDB_SUB s ON s.CAIGDDB_ID=c.id \n" +
                    "  INNER JOIN gongysb g ON h.GONGF=g.id \n" +
                    "  INNER JOIN diancxxb d ON h.DIANCXXB_ID=d.id \n" +
                    "WHERE s.id=" + caigddbSubId;
            Map<String, Object> hetMap = jdbcTemplate.queryForMap(sql);
            jiesdMap.putAll(hetMap);
            //double hetjg = Double.parseDouble(hetMap.get("HETJG").toString());
            double jiesjg = zengkjg;
            jiesdMap.put("HETZBZDJ", jiesjg);
            jiesdMap.put("JIESSL", map.get("JINGZ"));
            jiesdMap.put("JINGZ", map.get("JINGZ"));
            jiesdMap.put("GUOHSL", map.get("JINGZ"));
            jiesdMap.put("PIAOZ", map.get("JINGZ"));
            jiesdMap.put("JIESSLCY", CustomMaths.sub(map.get("SANFSL"), map.get("JINGZ")));
            jiesdMap.put("JIESLF", map.get("STD"));
            jiesdMap.put("JIESRZ", map.get("QNETAR"));
            jiesdMap.put("MEIKMC", map.get("MEIKMC"));
            jiesdMap.put("MEIKXXB_ID", map.get("MEIKXXB_ID"));
            jiesdMap.put("PINZMC", "煤");
            jiesdMap.put("CHES", map.get("CHES"));
            jiesdMap.put("JIESJG", CustomMaths.round(jiesjg, 2));
            jiesdMap.put("HETJG", Double.parseDouble(hetMap.get("HETJG").toString()) );
            jiesdMap.put("BUHSDJ", CustomMaths.round(jiesjg * (1 - 0.17), 2));
            jiesdMap.put("SHUIL", 0.17);
            jiesdMap.put("BUKK",0);
            double jiesje = CustomMaths.mul(jiesjg, jiesdMap.get("JIESSL"));
            jiesdMap.put("JIESJE", jiesje);
            jiesdMap.put("MEIKHJ", jiesje);
            jiesdMap.put("MEIKJE", CustomMaths.round(jiesje * 1.17, 2));
            jiesdMap.put("JIAKHJ",CustomMaths.round(jiesje * 1.17, 2));
            jiesdMap.put("SHUIK", CustomMaths.round(jiesje * 0.17, 2));
            jiesdMap.put("JIESBH", JIESBH);
            jiesdMap.put("CAOZRQ", DateUtil.getCurrentTime());
            jiesdMap.put("zhibList", zengkmxList);
            return jiesdMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 供应商
     */
    @RequestMapping(value = "/getGongysList")
    public void getGongysList(@RequestParam(defaultValue = "") String condition,
                              HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(condition);
        PrintWriter writer;
        List<Map<String, Object>> list = meikjsService.getGongysList(map);
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            Combobox combobox = new Combobox("请选择", -1);
            jsonArray.add(combobox);
            for (int i = 0; i < list.size(); i++) {
                combobox = new Combobox(list.get(i).get("MINGC"), list
                        .get(i).get("GONGYSB_ID"));
                jsonArray.add(combobox);
            }
        }
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/getMeikxxList")
    public void getMeikxxList(@RequestParam(defaultValue = "") String condition,
                              HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(condition);
        PrintWriter writer;
        String sql=" SELECT DISTINCT C.MEIKXXB_ID id,\n" +
                "        C.MEIKMC mingc\n" +
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
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        try {
            writer = response.getWriter();
            writer.write(JSONArray.fromObject(list).toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    /**
     * 品种
     */
    @RequestMapping(value = "/getPinzList")
    public void getPinzList(@RequestParam(defaultValue = "") String condition,
                            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(condition);
        PrintWriter writer;
        List<Map<String, Object>> list = meikjsService.getPinzList(map);
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            Combobox combobox = new Combobox("请选择", -1);
            jsonArray.add(combobox);
            for (int i = 0; i < list.size(); i++) {
                combobox = new Combobox(list.get(i).get("MINGC"), list
                        .get(i).get("GONGYSB_ID"));
                jsonArray.add(combobox);
            }
        }
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    /**
     * 合同号
     */
    @RequestMapping(value = "/gethetbhList")
    public void getHetbh(@RequestParam(defaultValue = "") String condition, HttpServletRequest request,
                         HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(condition);
        PrintWriter writer;
        List<Map<String, Object>> list = meikjsService.getHetbh(map);
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            Combobox combobox = new Combobox("请选择", -1);
            jsonArray.add(combobox);
            for (int i = 0; i < list.size(); i++) {
                combobox = new Combobox(list.get(i).get("MINGC"), list
                        .get(i).get("ID"));
                jsonArray.add(combobox);
            }
        }
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    /**
     * 月结算
     */
    @RequestMapping(value = "/getYuejsdList")
    public void getYuejsdList(@RequestParam String condition, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(condition);
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(meikjsService.getYuejsdList(map).toString());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/create")
    public void create(@RequestParam(defaultValue = "") String condition,
                       HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(condition);
        Diancxx diancxx = (Diancxx) request.getSession().getAttribute("diancxx");
        Renyxx renyxx = (Renyxx) request.getSession().getAttribute("user");
        map.put("diancxx", diancxx);
        map.put("renyxx", renyxx);
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(meikjsService.create(map).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取合同号
    @RequestMapping(value = "/getHetong")
    public void getHetong(@RequestParam(defaultValue = "") String search, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(search);
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(meikjsService.getHetong(map).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取合同号
    @RequestMapping(value = "/getZafjsList")
    public void getZafjsList(@RequestParam(defaultValue = "") String search, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(search);
        List<Map<String, Object>> l = meikjsService.getZafjsList(map);
        String[][] a = Result.list2array(l, new String[]{"ID", "RUKDH", "MEIKDW", "PINZ", "DAOHRQ", "CHES", "SANFSL", "MAOZ", "PIZ", "JINGZ"});
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(JSONArray.fromObject(a).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getYewlxList")
    public void getYewlxList(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, Object>> l = meikjsService.getYewlxList();
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(JSONArray.fromObject(l).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getZafjswhList")
    public void getZafjswhList(String search, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> m = JSON.parseObject(search);
        List<Map<String, Object>> l = meikjsService.getZafjswhList(m);
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.write(JSONArray.fromObject(l).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveZafjswhList")
    public void saveZafjswhList(String search, String data, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> m = JSON.parseObject(search);
        List<Map<String, Object>> list = JSONArray.fromObject(data);
        meikjsService.saveZafjswhList(m, list);
    }

    @RequestMapping(value = "/zafJies")
    public void zafJies(String data, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(data);
        meikjsService.zafJies(map);
    }

    @RequestMapping(value = "/deleteZafjs")
    public void deleteZafjs(String data, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = JSON.parseObject(data);
        meikjsService.deleteZafjs(map);
    }

}
