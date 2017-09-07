package com.zhiren.fuelmis.dc.controller.jiesgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.scheduler.ZhilrkJob;
import com.zhiren.fuelmis.dc.scheduler.ShulrkJob;
import com.zhiren.fuelmis.dc.utils.formular.Result;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.jiesgl.IRijsscService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("jiesgl/rijssc")
public class RijsscController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private IRijsscService rijsscService;
    @Autowired
    private ShulrkJob shulrkJob;
    @Autowired
    private ZhilrkJob zhilrkJob;

    @RequestMapping(value = "/getRijscx")
    public
    @ResponseBody
    JSONArray getRijscx(HttpServletRequest request, HttpServletResponse response, String search) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Map<String, Object> map = JSONObject.fromObject(search);
            List<Map<String, Object>> list = rijsscService.getRijscx(map);
            String[][] array = Result.list2array(list, new String[]{
                    "CAIYBM", "GONGYSMC", "PINZ", "CHES", "DAOHRQ", "SANFSL", "JINGZ", "CAIYBM", "HUAYBM",
                    "HUAYZT", "QNET_AR", "RUKDBH", "RK_JINE", "BIANH", "KAISSJ", "JIESSJ", "JIESBH", "RJS_JIESSL", "RJS_JIESRZ", "RJS_JIESLF",
                    "RJS_HANSJE", "RJS_BUHSJE", "RJS_HANSDJ", "RJS_REZZK", "RJS_LIUFZK", "RJS_GONGS", "RJS_HETBH", "YUEJSDBM", "JIESDJ_YJS"});
            return JSONArray.fromObject(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/delRijsd")
    public void delRijsd(HttpServletRequest request, HttpServletResponse response, String list) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            List<Map<String, Object>> jiesxxList = JSONArray.fromObject(list);
            rijsscService.delRijsd(jiesxxList);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

  /*  @RequestMapping(value = "/delRuksc")
    public void delRuksc(HttpServletRequest request, HttpServletResponse response, String id) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Object> list = JSONArray.fromObject(id);
            rijsscService.delRuksc(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @RequestMapping(value = "/del")
    public void del(HttpServletRequest request, HttpServletResponse response, String id) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Object> list = JSONArray.fromObject(id);
            rijsscService.del(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/ruk")
    public void ruk(HttpServletRequest request, HttpServletResponse response, String id) {
        try {
            List<Object> list = JSONArray.fromObject(id);
            shulrkJob.ruk(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/rijs")
    public void rijs(HttpServletRequest request, HttpServletResponse response, String id) {
        try {
            List<Object> list = JSONArray.fromObject(id);
            String sc = "-1";
            for (Object o : list) {
                sc += ",'" + o + "'";
            }
            List<Map<String, Object>> rukdbhs = jdbcTemplate.queryForList(
                    "select DISTINCT rukdbh from GX_CHURKDB_YUNSDJ g inner join RL_YS_CHEPB c on g.YUANDJ_ID=c.id\n" +
                            "where g.yuandjlx=1 and c.samcode in (" + sc + ")");
            zhilrkJob.rijs(rukdbhs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
