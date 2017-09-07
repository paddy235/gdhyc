package com.zhiren.fuelmis.dc.controller.gongyspg.kaohzb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.gongyspg.kaohzb.Kaohzb;
import com.zhiren.fuelmis.dc.service.gongyspg.kaohzb.KaohzbService;

/**
 *
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongyspg/kaohzb")
public class KaohzbController {

    @Autowired
    private KaohzbService kaohzbService;


    /**
     * 新增考核指标
     * @param info
     * @param request
     * @param response
     */
    @RequestMapping(value = "/addKaohzb")
    public void addKaohzb(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer  = null;
        JSONObject json = JSONObject.fromObject(info);
        //获取电厂信息
        //Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
        @SuppressWarnings("static-access")
        Kaohzb kaohzb = (Kaohzb) json.toBean(json, Kaohzb.class);
//		if(diancxx.getId()!=null){
//			Kaohzb.setDiancxxb_id(diancxx.getId());
//		}else{
        kaohzb.setDiancxxb_id(910l);
//		}
        JSONArray jsonArray = kaohzbService.addKaohzb(kaohzb);;
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateKaohzb")
    public void updateKaohzb(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer  = null;
        JSONObject json = JSONObject.fromObject(info);
        //获取电厂信息
        @SuppressWarnings("static-access")
        Kaohzb kaohzb = (Kaohzb) json.toBean(json, Kaohzb.class);
        JSONArray jsonArray = kaohzbService.updateKaohzb(kaohzb);;
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    /**
     * 获取全部考核指标
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getKaohzbList")
    public void getKaohzbList(HttpServletRequest request , HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject json = kaohzbService.getKaohzbList(null);
        PrintWriter writer  = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delKaohzb")
    public void delKaohzb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONArray json = kaohzbService.delKaohzb(map);
        PrintWriter writer  = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取编辑数据
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping(value = "/editKaohzb")
    public void editKaohzb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONObject json = kaohzbService.getKaohzbById(map);
        PrintWriter writer  = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
