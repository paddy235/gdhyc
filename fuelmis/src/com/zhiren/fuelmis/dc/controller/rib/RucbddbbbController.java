package com.zhiren.fuelmis.dc.controller.rib;

import com.zhiren.fuelmis.dc.service.rib.IRucbddbbbService;
import com.zhiren.fuelmis.dc.utils.formular.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/rucbddb")
public class RucbddbbbController {

    @Autowired
    private IRucbddbbbService ranmzhrbbService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/ribcx")
    @ResponseBody
    public JSONArray getYuedcaigjh(HttpServletRequest request, HttpServletResponse response, String condition) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Map<String, Object> map = JSONObject.fromObject(condition);
            JSONArray tablehtml =  ranmzhrbbService.getShouhcDetail(map);
            //MakeHtml.html=tablehtml;
            return tablehtml;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @RequestMapping(value = "/getMeiHy")
    public @ResponseBody
    String[][]
    getMeiHy(@RequestParam(defaultValue = "") String condition, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map=JSONObject.fromObject(condition);
        List<Map<String, Object>> fujxxMap=jdbcTemplate.queryForList("select a.riq,\n" +
                "round(a.benywcz,2) benywcz,\n" +
                "round(a.duibdwbywcz,2) duibdwbywcz,\n" +
                "round(a.jitxfdbmbz,0) jitxfdbmbz,\n" +
                "round(nvl(a.benywcz,0)-nvl(a.duibdwbywcz,0),2) duibwcz,\n" +
                "round(nvl(a.duibwcz,0)-nvl(a.jitxfdbmbz,0),2) wanczymbzzc\n" +
                "from yuebddbwcsjb a where riq between '"+map.get("sDate")+"'and '"+map.get("eDate")+"'");

        String[][] arrData=null;
        if (fujxxMap.size() > 0) {
            String[][] array = Result.list2array(fujxxMap, new String[]{"RIQ","BENYWCZ","DUIBDWBYWCZ","JITXFDBMBZ","DUIBWCZ","WANCZYMBZZC"});

            arrData=new String[array[0].length][array.length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    arrData[j][i]=array[i][j];
                }
            }
        }
        return arrData;

    }
    @RequestMapping(value = "/getRucbddb")
    public @ResponseBody List<Map<String, Object>>
    getRucbddb(@RequestParam(defaultValue = "") String riq, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> map=JSONObject.fromObject(riq);
        //List<Map<String, Object>> meiHy = xianggwhService.getMeiHy(map);
        List<Map<String, Object>> fujxxMap=jdbcTemplate.queryForList("select ID,RIQ,DIANCXXB_ID,DUIBBMDJ,QUYPM from YUEDZHZBTJB where riq between '"+map.get("sDate")+"'and '"+map.get("eDate")+"'");
        return fujxxMap;
    }
}