package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IHaochjService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("yuebgl/yuebwh/haochj")
public class HaochjController {

    @Autowired
    private IHaochjService haochjService;

    @RequestMapping(value = "/getAll")
    public void getAll(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "515") String dianc,
                       HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("riq", "".equals(riq) ? DateUtil.getDayOfMonth(null) : riq + "-01");
        map.put("dianc", "-1".equals(dianc) ? "" : dianc);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(haochjService.getAll(map).toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/check")
    public void check(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "515") String dianc,
                      HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("riq", "".equals(riq) ? DateUtil.getDayOfMonth(null) : riq + "-01");
        map.put("dianc", "-1".equals(dianc) ? "" : dianc);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(haochjService.check(map).toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/createData")
    public void createData(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "515") String dianc,
                           HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("riq", "".equals(riq) ? DateUtil.getDayOfMonth(null) : riq + "-01");
        map.put("dianc", "-1".equals(dianc) ? "" : dianc);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(haochjService.createData(map).toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveData")
    public void saveData(@RequestParam(defaultValue = "") String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map> list = JSON.parseArray(data, Map.class);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
            haochjService.saveData(list);
            writer = response.getWriter();
            writer.write("更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @RequestMapping(value = "/delData")
    public void delData(@RequestParam(defaultValue = "") String riq, @RequestParam(defaultValue = "515") String dianc,
                        HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("riq", "".equals(riq) ? DateUtil.getDayOfMonth(null) : riq + "-01");
        map.put("dianc", "-1".equals(dianc) ? "" : dianc);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(haochjService.delData(map).toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
