package com.zhiren.fuelmis.dc.controller.rib;

import com.zhiren.fuelmis.dc.service.rib.ILaihclService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/laihcl")
public class LaihclController {

    @Autowired
    private ILaihclService ranmzhrbbService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/ribcx")
    public
    @ResponseBody
    JSONArray getYuedcaigjh(HttpServletRequest request, HttpServletResponse response, String condition) {
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
        List<Map<String, Object>> fujxxMap=jdbcTemplate.queryForList("select d.riq, biaomdj ,c.kuc,c.shouml, (c.fady+c.gongry) haoml from \n" +
                "(select b.riq,round(decode(sum(a.jiesl),0,0,sum(a.meij*a.jiesl)/sum(a.jiesl)),2) biaomdj from yuejsbmdj a  left join \n" +
                "yuetjkjb b on b.id=a.yuetjkjb_id\n" +
                "where fenx='本月'  group by b.riq\n" +
                "order by riq desc\n" +
                ") d\n" +
                "\n" +
                "left join YUESHCHJB c on c.riq=d.riq \n" +
                "where fenx='本月' and d.riq between '"+map.get("sDate")+"'and '"+map.get("eDate")+"'");

        String[][] arrData=null;
        if (fujxxMap.size() > 0) {
            String[][] array = Result.list2array(fujxxMap, new String[]{"RIQ","KUC","SHOUML","HAOML","BIAOMDJ"});

            arrData=new String[array[0].length][array.length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    arrData[j][i]=array[i][j];
                }
            }
        }
        return arrData;
    }
}