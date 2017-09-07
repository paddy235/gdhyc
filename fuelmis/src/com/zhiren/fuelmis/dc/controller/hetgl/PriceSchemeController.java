package com.zhiren.fuelmis.dc.controller.hetgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.hetgl.PriceSchemeService;
import com.zhiren.fuelmis.dc.utils.Constant;

/**
 * 质量范围计价组件Controller
 * @author ZY
 *
 */
@Controller
@RequestMapping("hetgl/pricescheme")
public class PriceSchemeController {
    @Autowired
    private PriceSchemeService priceSchemeService;
    /**
     * 更新计价组件
     * @param info
     * @param subinfo
     * @param delinfo
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping(value = "/updatePriceScheme")
    public void updatePriceScheme(@RequestParam String info, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        try{
            Renyxx user=(Renyxx) session.getAttribute("user");
            JSONArray priceScheme = JSONArray.fromObject(info);
            priceSchemeService.updatePriceScheme(priceScheme,user);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
    @RequestMapping(value = "/delPriceScheme")
    public void delPriceScheme(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id", id);
        JSONObject json = priceSchemeService.delPriceScheme(map);
        PrintWriter writer  = null;
        try {
            writer = response.getWriter();
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
