package com.zhiren.fuelmis.dc.controller.kucgl.chukgl;


import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.IKucglService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author rain
 */
@Controller
@RequestMapping("kucgl/chuk")
public class ChukwhController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private IKucglService kucglService;

    @RequestMapping(value = "/getChurkd")
    public void getKuczz(@RequestParam(defaultValue = "") String search,
                         HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map=JSONObject.fromObject(search);
        List<Map<String, Object>> list = kucglService.getChurkd(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSONArray.fromObject(list).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveChurkd")
    public void saveChurkd(@RequestParam(defaultValue = "") String data, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String,Object>> list=JSONArray.fromObject(data);
        Renyxx user = (Renyxx) session.getAttribute("user");
        kucglService.saveChurkd(list,user);
    }
    @RequestMapping(value = "/delChurkd")
    public void delChurkd(@RequestParam(defaultValue = "") String data,HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Object> ids=JSONArray.fromObject(data);
        kucglService.delChurkd(ids);
    }
    /*@RequestMapping(value = "/getChukdbhAndHangh")
    public @ResponseBody JSONObject getChukdbhAndHangh(@RequestParam(defaultValue = "") String search,HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map=JSONObject.fromObject(search);
        Object yewlx=map.get("YEWLX");
        Object riq=map.get("riq");
//        String chukdbh=kucglService.getChukdbh(map);
        String sql="SELECT\n" +
                "  decode((select leixdm from GY_DM_CHURKYWLXDMB where id=7), '1', 'RKD-', 'CKD-') || RUKDBH RUKDBH,\n" +
                "  HANGH\n" +
                "FROM (\n" +
                "  SELECT\n" +
                "    nvl(substr(max(rukdbh), length(max(rukdbh)) - 10, 11), replace('2017-10-18', '-', '') || '000') + 1 RUKDBH,\n" +
                "    nvl(max(hangh), 0) + 1                                                                              HANGH\n" +
                "  FROM rl_kc_churkdb\n" +
                "  WHERE substr(rukdbh, 5, 8) = replace('2016-10-18', '-', '')\n" +
                ")";
        Map<String,Object>=jdbcTemplate.queryForMap(sql);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(chukdbh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    @RequestMapping(value = "/getYewlxList")
    public @ResponseBody JSONArray getYewlxList(HttpServletRequest request, HttpServletResponse response) {
        try {
            String sql="select zhi from xitxxb where mingc='出入库单业务类型是否可修改' and leib='库存管理'";
            Map<String, Object> zhi = jdbcTemplate.queryForMap(sql);
            List<Map<String, Object>> list = jdbcTemplate.queryForList("select id,leixmc from GY_DM_CHURKYWLXDMB where id in (" + zhi.get("zhi") + ")");
            return JSONArray.fromObject(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

