package com.zhiren.fuelmis.dc.controller.kucgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.kucgl.IKucglCommonService;
import com.zhiren.fuelmis.dc.service.kucgl.IKucglService;

/**
 * @author 刘志宇
 */
@Controller
@RequestMapping("kucgl")
public class KucglController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private IKucglService kucglService;
    @Autowired
    private IKucglCommonService kucglCommonService;

    @RequestMapping(value = "/getKuczz")
    public void getKuczz(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "") String dianc,
                         HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, Object>> list = kucglService.getKuczz();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Combobox combobox = new Combobox(list.get(i).get("ID"), list.get(i));
                jsonArray.add(combobox);
            }
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getFuKuczzList")
    public void getFuKuczzList(@RequestParam(defaultValue = "") String fuid,
                               HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        List<Map<String, Object>> list = kucglService.getFuKuczzList(fuid);
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Combobox combobox = new Combobox(list.get(i).get("KUCZZMC"), list.get(i).get("ID"));
                jsonArray.add(combobox);
            }
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getWeiz")
    public void getWeiz(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "") String dianc,
                        HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, Object>> list = kucglService.getWeiz();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
/*				Combobox combobox = new Combobox(list.get(i).get("ID"), list.get(i));
                jsonArray.add(combobox);*/
                jsonArray.add(list.get(i));
            }
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //------------------------------------库存物种---------------------------------------------------------
    @RequestMapping(value = "/getKucwz")
    public void getKucwz(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "") String dianc,
                         HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, Object>> list = kucglService.getKucwz();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Combobox combobox = new Combobox(list.get(i).get("ID"), list.get(i));
                jsonArray.add(combobox);
            }
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getGuanl")
    public @ResponseBody JSONArray getGuanl(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, Object>> list = kucglService.getGuanl();
       return JSONArray.fromObject(list);
    }

    @RequestMapping(value = "/getKuczzList")
    public void getKuczzList(HttpServletRequest request,
                             HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        List<Map<String, Object>> list = kucglService.getKuczzList();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Combobox combobox = new Combobox(list.get(i).get("KUCZZMC"), list
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

    //-----------------------------------会计期定义---------------------------------------------------------
    @RequestMapping(value = "/getKuaijqdy")
    public @ResponseBody JSONArray getKuaijqdy(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, Object>> list = kucglService.getKuaijqdy();
        return JSONArray.fromObject(list);
    }

    @RequestMapping(value = "/getKuaijqList")
    public void getKuaijqList(HttpServletRequest request,
                              HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        List<Map<String, Object>> list = kucglService.getKuaijqList();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Combobox combobox = new Combobox(list.get(i).get("KUAIJRQ"), list
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

    @RequestMapping(value = "/update")
    public void update(@RequestParam String kuczzUpdate, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
//		PrintWriter writer  = null;

        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.fromObject(kuczzUpdate);
        kucglService.update(map);
    }

    @RequestMapping(value = "/updateKucwz")
    public void updateKucwz(@RequestParam String kucwzUpdate, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.fromObject(kucwzUpdate);
        kucglService.updateKucwz(map);
    }


    @RequestMapping(value = "/saveKuaijqdy")
    public void saveKuaijqdy(@RequestParam String data, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = JSONArray.fromObject(data);
        kucglService.saveKuaijqdy(list);
    }

    @RequestMapping(value = "/updateWeiz")
    public void updateWeiz(@RequestParam String weizUpdate, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.fromObject(weizUpdate);
        kucglService.updateWeiz(map);
    }

    @RequestMapping(value = "/saveGuanl")
    public void saveGuanl(@RequestParam String data, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        Renyxx user=(Renyxx)session.getAttribute("user");
        List<Map<String,Object>> list=JSONArray.fromObject(data);
            kucglService.saveGuanl(list,user);
    }

    @RequestMapping(value = "/insert")
    public void insert(@RequestParam String kuczzInsert, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.fromObject(kuczzInsert);
        kucglService.insert(map);
    }

    @RequestMapping(value = "/insertKucwz")
    public void insertKucwz(@RequestParam String kucwzInsert, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.fromObject(kucwzInsert);
        kucglService.insertKucwz(map);
    }






    @RequestMapping(value = "/insertWeiz")
    public void insertWeiz(@RequestParam String weizInsert, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.fromObject(weizInsert);
        kucglService.insertWeiz(map);
    }

    @RequestMapping(value = "/kucjz")
    public void kucjz(@RequestParam String kuaijrq, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        kucglCommonService.kucjz(kuaijrq);
    }

    @RequestMapping(value = "/getKucftList")
    public void getKucftList(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String, Object>> list = kucglService.getKucftList();
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSONArray.fromObject(list).toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/saveKucftList")
    public void saveKucftList(String data,HttpServletRequest request, HttpServletResponse response) {
        List<Map<String,Object>> list=JSONArray.fromObject(data);
        kucglService.saveKucftList(list);
    }
    @RequestMapping(value = "/yuemgz")
    public void yuemgz(String data,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
    	Renyxx user = (Renyxx) session.getAttribute("user");
    	Map<String,Object> map=JSONObject.fromObject(data);
    	map.put("userid",user.getId());
    	map.put("username",user.getMingc());
        kucglService.yuemgz(map);
    }
    @RequestMapping(value = "/clear")
    public void clear(String data,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        Renyxx user = (Renyxx) session.getAttribute("user");
        Map<String,Object> map=JSONObject.fromObject(data);
        try {
            jdbcTemplate.update("update RL_KC_MONTH_TOTAL mt_out  set\n" +
                    "in_quantity=0,\n" +
                    "in_amount=0,\n" +
                    "estimate_quantity=0,\n" +
                    "estimate_amount=0,\n" +
                    "water_quantity=0,\n" +
                    "price=0,\n" +
                    "out_quantity=0,\n" +
                    "out_amount=0,\n" +
                    "ending_quantity=0,\n" +
                    "ending_amount=0,\n" +
                    "last_updated_by_userid='" + user.getId() + "',\n" +
                    "last_updated_by_username='" + user.getMingc() + "',\n" +
                    "last_update_date=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')\n" +
                    "where mt_out.period_id='" + map.get("KUAIJQ_ID") + "' and mt_out.kuczz='" + map.get("KUCZZ") + "'");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

