package com.zhiren.fuelmis.dc.controller.kucgl.rukgl;



import com.zhiren.fuelmis.dc.service.kucgl.rukgl.IChukdlrService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @author 刘志宇
 */
@Controller
@RequestMapping("kucgl/rukgl/churkdlr")
public class ChurkdlrController {

    @Autowired
    private IChukdlrService chukdlrService;

    @RequestMapping(value = "/getChurkd")
    public void getKuczz(@RequestParam(defaultValue = "") String search,
                         HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map=JSONObject.fromObject(search);
        List<Map<String, Object>> list = chukdlrService.getChurkd(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSONArray.fromObject(list).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveChurkd")
    public void getFuKuczzList(@RequestParam(defaultValue = "") String data,
                               HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Map<String,Object>> list=JSONArray.fromObject(data);
        chukdlrService.saveChurkd(list);
    }
    @RequestMapping(value = "/delChurkd")
    public void delChurkd(@RequestParam(defaultValue = "") String data,HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        List<Object> ids=JSONArray.fromObject(data);
        chukdlrService.delChurkd(ids);
    }
    @RequestMapping(value = "/getChukdbh")
    public void getChukdbh(@RequestParam(defaultValue = "") String search,HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map=JSONObject.fromObject(search);
        String chukdbh=chukdlrService.getChukdbh(map);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(chukdbh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

