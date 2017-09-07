package com.zhiren.fuelmis.dc.controller.xitgl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.utils.IPUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.xitgl.IRenyxxService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import com.zhiren.fuelmis.dc.utils.MD5Util;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;

/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/renyxx")
public class RenyxxController {
    @Autowired
    private IRenyxxService renyxxService;

    @RequestMapping(value = "/getAll")
    public void getAll(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject jsonObject = renyxxService.getAll(null);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addRenyxx")
    public void addRenyxx(@RequestParam String info,
                          HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        @SuppressWarnings("unchecked")
        Map<String, Object> map = JSONObject.fromObject(info);
        map.put("id", System.currentTimeMillis());
        String mim = MD5Util.generate(map.get("mim").toString());
        map.remove("mim");
        map.put("mim", mim);
        String xingb = map.get("xingb").toString();
        map.remove("xingb");
        map.put("xingb", xingb.equals("1") ? "男" : "女");
        map.put("diancxxb_id",
                ((Diancxx) request.getSession().getAttribute("diancxx"))
                        .getId());
        JSONArray jsonArray = renyxxService.insertRenyxx(map);
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getOne")
    public void getOne(@RequestParam String id, HttpServletRequest request,
                       HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONArray jsonArray = renyxxService.getOne(map);
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateRenyxx")
    public void updateRenyxx(@RequestParam String info,
                             HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = JSONObject.fromObject(info);
            String xingb = map.get("xingb").toString();
            map.remove("xingb");
            map.put("xingb", xingb.equals("1") ? "男" : "女");
            String ip = IPUtil.getIpAddress();
            String mac = IPUtil.getMACAddress();
            Renyxx renyxx = (Renyxx) session.getAttribute("user");
            map.put("last_editer", renyxx.getMingc());
            map.put("last_editer_ip", ip);
            map.put("last_editer_mac", mac);
            map.put("last_edit_date", DateUtil.formatTime(new Date()));
            JSONArray jsonArray = renyxxService.updateRenyxx(map);

            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "/delRenyxx")
    public void delRenyxx(@RequestParam String id, HttpServletRequest request,
                          HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONArray jsonArray = renyxxService.deleteRenyxx(map);
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/resetPassword")
    public void resetPassword(@RequestParam String id,
                              HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
//		map.put("mim",
//				MD5Util.generate(PropertiesUtil.getValue("default_password")));
        map.put("mim", PropertiesUtil.getValue("default_password"));
        JSONArray jsonArray = renyxxService.updateRenyxx(map);
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/shezQuanx")
    public void shezQuanx(@RequestParam Long id, @RequestParam String[] ziy,
                          HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        JSONArray jsonArray = renyxxService.updateQuanx(id, ziy);
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getQuanx")
    public void getQuanx(@RequestParam Long id, HttpServletRequest request,
                         HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;

        JSONArray jsonArray = renyxxService.getQuanx(id);
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
