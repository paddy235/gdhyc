package com.zhiren.fuelmis.dc.controller.rib;

import com.zhiren.fuelmis.dc.service.rib.IFenkcgrcbmdjService;
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
@RequestMapping("/fenkcgrcbmdj")
public class FenkcgrcbmdjController {

    @Autowired
    private IFenkcgrcbmdjService ranmzhrbbService;
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

        List<Map<String, Object>> fujxxMap=jdbcTemplate.queryForList("SELECT\n" +
                " b.riq,\n" +
                "  '阳光采购' yanggcg,\n" +
                " '公路' gongl,\n" +
                " f.mingc,\n" +
                " e.quanc,\n" +
                "\n" +
                " sum(a.jingz) jingz,\n" +
                " round_new(decode(sum(c.qnet_ar), 0, 0,sum(c.qnet_ar * a.jingz) / sum(a.jingz)),2) rez,\n" +
                " round_new(decode(sum(c.std), 0, 0, sum(c.std * a.jingz) / sum(a.jingz)), 2) liuf,\n" +
                " round(decode(sum(d.biaomdj), 0, 0, sum(d.biaomdj * a.jingz) / sum(a.jingz)),\n" + " 2) biaomdj\n" +
                " \n" +
                "  FROM yuetjkjb b\n" +
                " INNER JOIN yueslb a\n" +
                "    ON a.yuetjkjb_id = b.id\n" +
                "   AND a.fenx = '本月'\n" +
                " INNER JOIN yuezlb c\n" +
                "    ON c.yuetjkjb_id = b.id\n" +
                "   AND c.fenx = '本月'\n" +
                " INNER JOIN yuejsbmdj d\n" +
                "    ON d.yuetjkjb_id = b.id\n" +
                "   AND d.fenx = '本月'\n" +
                " INNER JOIN gongysb e\n" +
                "    ON e.id = b.gongysb_id\n" +
                " INNER JOIN pinzb f\n" +
                "    ON f.id = b.pinzb_id where riq ='"+map.get("sDate")+"'\n" +
                " GROUP BY b.riq, e.quanc, f.mingc");

        String[][] arrData=null;
        if (fujxxMap.size() > 0) {
            String[][] array = Result.list2array(fujxxMap, new String[]{"RIQ","YANGGCG","GONGL","MINGC","QUANC","JINGZ","LIUF","REZ","BIAOMDJ"});

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