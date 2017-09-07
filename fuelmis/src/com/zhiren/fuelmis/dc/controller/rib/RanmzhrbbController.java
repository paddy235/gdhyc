package com.zhiren.fuelmis.dc.controller.rib;


import com.zhiren.fuelmis.dc.service.rib.IRanmzhrbbService;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/ranm")
public class RanmzhrbbController {

    @Autowired
    private IRanmzhrbbService IRanmzhrbbService;

    @RequestMapping(value = "/ribcx")
    public
    @ResponseBody
    String getYuedcaigjh(HttpServletRequest request, HttpServletResponse response, String condition) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Map<String, Object> map = JSONObject.fromObject(condition);
            String tablehtml = IRanmzhrbbService.getShouhc(map) + "<div>&nbsp;</div>" + IRanmzhrbbService.getShouhcDetail(map);
            //MakeHtml.html=tablehtml;
            return tablehtml;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}