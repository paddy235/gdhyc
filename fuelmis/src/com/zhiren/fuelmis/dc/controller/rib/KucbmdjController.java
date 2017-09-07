package com.zhiren.fuelmis.dc.controller.rib;

import com.zhiren.fuelmis.dc.service.rib.IKucbmdjService;
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
@RequestMapping("/kucbmdj")
public class KucbmdjController {

    @Autowired
    private IKucbmdjService ranmzhrbbService;

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
        List<Map<String, Object>> fujxxMap=jdbcTemplate.queryForList("SELECT \n" +
                "a.riq,\n" +
                "round(a.rucbd,2) rucbd,\n" +
                "round(a.rulbd,2) rulbd,\n" +
                "round(a.kucbd,2) kucbd,\n" +
                "round(a.kucbd-a.rulbd,2) kucjrlsj,\n" +
                "round(a.rulbd-a.rucbd,2) ruljrcsj\n" +
                "FROM yuekcbmdjbhsjb a  where riq between '"+map.get("sDate")+"'and '"+map.get("eDate")+"'");
        String[][] arrData=null;
        if (fujxxMap.size() > 0) {
            String[][] array = Result.list2array(fujxxMap, new String[]{"RIQ","RUCBD","RULBD","KUCBD","KUCJRLSJ","RULJRCSJ"});

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

