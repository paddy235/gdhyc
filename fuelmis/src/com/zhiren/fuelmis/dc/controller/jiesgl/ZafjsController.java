package com.zhiren.fuelmis.dc.controller.jiesgl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.jiesgl.IZafjsService;

import net.sf.json.JSONArray;

/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("jiesgl/zafjs")
public class ZafjsController {

    @Autowired
    private IZafjsService zafjsService;

    @RequestMapping(value = "/getZafjs")
    public @ResponseBody JSONArray getJiestz(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
       List<Map<String,Object>> list= zafjsService.getZafjs();
       return JSONArray.fromObject(list);
    }
    @RequestMapping(value = "/saveZafjs")
    public void saveZafjs(String data,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        Diancxx diancxx = (Diancxx) request.getSession().getAttribute("diancxx");
        Renyxx user = (Renyxx) session.getAttribute("user");
        List<Map<String,Object>> list=JSONArray.fromObject(data);
       zafjsService.saveZafjs(list,user,diancxx);
    }
    @RequestMapping(value = "/delZafjs")
    public void delZafjs(String id,HttpServletRequest request, HttpServletResponse response) {
       zafjsService.delZafjs(id);
    }
    
}
