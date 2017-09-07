package com.zhiren.fuelmis.dc.controller.hetgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.dao.hetgl.CaigddbDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.hetgl.Caigddb;
import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.hetgl.CaigddbService;
import com.zhiren.fuelmis.dc.service.hetgl.HetbeanService;
import com.zhiren.fuelmis.dc.service.hetgl.PriceComponetService;
import com.zhiren.fuelmis.dc.service.hetgl.PriceQalityRangeService;
import com.zhiren.fuelmis.dc.service.hetgl.PriceSchemeService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("hetgl/caigddb")
public class CaigddbController {
    @Autowired
    private CaigddbDao caigddbDao;

    @Autowired
    private CaigddbService caigddbService;

    @Autowired
    private HetbeanService hetbeanService;

    @Autowired
    private PriceComponetService priceComponetService;

    @Autowired
    private PriceQalityRangeService priceQalityRangeService;


    @RequestMapping(value = "/getJiagtype")
    public void getJiagtype(HttpServletRequest request,
                            HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        List<Map<String, Object>> list = caigddbDao.getjiagtype();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Combobox combobox = new Combobox(list.get(i).get("MINGC"), list
                        .get(i).get("ID"));
                jsonArray.add(combobox);
            }
        }
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 新增合同发货订单
     *
     * @param info
     * @param request
     * @param response
     */
    @RequestMapping(value = "/addCaigddb")
    public void addCaigddb(@RequestParam String info, @RequestParam String ranlcginfo, @RequestParam String caigyunfsinfo,
                           HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        //判断info里的caigddlx字段的值等于“燃煤采购”，保存ranlcginfo里的数据，如果是“运费订单”，保存caigyunfsinfo里的数据
        JSONObject json = JSONObject.fromObject(info);
//		//获取电厂信息q
        Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
//		@SuppressWarnings("static-access")
        Caigddb caigddb = (Caigddb) json.toBean(json, Caigddb.class);
        Renyxx renyxx = (Renyxx) session.getAttribute("user");
        caigddb.setCaozry(renyxx.getQuanc());
        Long diancxxb_id = diancxx.getId();

        caigddb.setCaozsj(DateUtil.format(new Date()));
        JSONArray subs = new JSONArray();
        if ("运费订单".equals(caigddb.getCaigddlx())) {
            subs = JSONArray.fromObject(caigyunfsinfo);
        } else {
            subs = JSONArray.fromObject(ranlcginfo);
        }
        JSONArray jsonArray = caigddbService.addCaigddb(caigddb, subs, diancxxb_id);

        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updates")
    public void updateCaigddb(@RequestParam String info, @RequestParam String ranlcginfo, @RequestParam String caigyunfsinfo,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        JSONObject json = JSONObject.fromObject(info);
        System.out.println(json);
        //获取电厂信息
        //@SuppressWarnings("static-access")
        Caigddb caigddb = (Caigddb) json.toBean(json, Caigddb.class);
        JSONArray subs = new JSONArray();
        if ("运费订单".equals(caigddb.getCaigddlx())) {
            subs = JSONArray.fromObject(caigyunfsinfo);
        } else {
            subs = JSONArray.fromObject(ranlcginfo);
        }
        JSONArray jsonArray = caigddbService.updateCaigddb(caigddb, subs);
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getBianh")
    public void updateCaigddb(@RequestParam String dingdrq, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        //获取电厂信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dingdrq", dingdrq);
        JSONObject json = caigddbService.getBianh(map);
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取全部合同发货订单
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCaigddbList")
    public void getCaigddbList(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject json = caigddbService.getCaigddbList(null);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取全部合同发货订单
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/searchCaigddbList")
    public void searchCaigddbList(@RequestParam String diancid, @RequestParam String strdate, @RequestParam String enddate,
                                  @RequestParam String gongys, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == strdate || ("").equals(strdate)) {
            strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        if (null == diancid || "" == diancid) {
            diancid = "910";
        }
        if (!gongys.equals("-1")) {
            map.put("gongys", gongys);
        }
        map.put("diancid", diancid);
        map.put("strdate", strdate);
        map.put("enddate", enddate);
        JSONObject json = caigddbService.getCaigddbList(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取全部调度查询
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCaigddbinfoList")
    public void getCaigddbinfoList(@RequestParam String diancid, @RequestParam String strdate, @RequestParam String enddate, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        if (null == strdate || ("").equals(strdate)) {
            strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        if (null == diancid || "" == diancid) {
            diancid = "910";
        }

        JSONObject json = caigddbService.getCaigddbinfoList(strdate, enddate, diancid);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取全部调度查询
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCaigddbinfoList1")
    public void getCaigddbinfoList1(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String strdate = request.getParameter("strdate");
        String enddate = request.getParameter("enddate");
        String diancid = request.getParameter("diancid");
        if (null == strdate || ("").equals(strdate)) {
            strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        if (null == diancid || "" == diancid) {
            diancid = "910";
        }

        JSONObject json = caigddbService.getCaigddbinfoList(strdate, enddate, diancid);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delCaigddb")
    public void delCaigddb(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONArray json = caigddbService.delCaigddb(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * mjy 燃料订单
     *
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delCaigddbsub")
    public void delCaigddbsub(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONArray json = caigddbService.delCaigddbsub(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * mjy 运费订单
     *
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delCaigddbyfsub")
    public void delCaigddbyfsub(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONArray json = caigddbService.delCaigddbyfsub(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/beforedelCaigddb")
    public void beforedelCaigddb(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONObject json = caigddbService.beforedelCaigddb(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取编辑数据
     *
     * @param id
     * @param request
     * @param response MJY
     */
    @RequestMapping(value = "/editCaigddb")
    public void editCaigddb(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONObject json = caigddbService.getCaigddbById(map);

        JSONObject result = new JSONObject();
        result.put("caigddb", json);

        JSONObject gongys = hetbeanService.getGongysById(json.getString("gongys_id"));
        json.put("gongysmc", (gongys.toString().equals("null") ? "" : gongys.getString("mingc")));

        JSONObject dianc = hetbeanService.getDiancxxById(json.getString("diancxxb_id"));
        json.put("diancmc", dianc.getString("mingc"));

        JSONObject jihkj = caigddbService.getjihkjById(json.getString("kouj_id"));
        json.put("jihkjmc", jihkj.getString("mingc"));

        JSONArray subs = new JSONArray();
        if ("运费订单".equals(json.getString("caigddlx"))) {
            subs = caigddbService.getCaigddbyfsubById(map);
            result.put("caigyunfsinfo", subs);
        } else {
            subs = caigddbService.getCaigddbsubById(map);
            result.put("ranlcginfo", subs);
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取计价方式以及考核指标
     *
     * @param id
     * @param request
     * @param response MJY
     */
    @RequestMapping(value = "/getJijfsList")
    public @ResponseBody List<Map<String,Object>> getJijfsList(@RequestParam String caigddb_sub_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        try{
            List<Map<String,Object>> jijfsList = caigddbService.getJijfsByCaigddbSubId(caigddb_sub_id);
            return jijfsList;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }




}
