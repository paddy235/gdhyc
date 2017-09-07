//chh 2008-03-17 修改查询语句与表格格式
package com.zhiren.fuelmis.dc.controller.zonghzs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("zonghzs/wenjtz")
public class WenjAllController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getWenjList")
    public
    @ResponseBody
    List<Map<String, Object>> getWenjList(String riq, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String sql = "SELECT\n" +
                    "  rownum,\n" +
                    "  s.*\n" +
                    "FROM (SELECT\n" +
                    "        w.id                                                                                                         AS id,\n" +
                    "w.biaot,\n" +
                    "        w.reny                                                                                                          reny,\n" +
                    "        to_char(min(f.shij),\n" +
                    "                'yy-mm-dd hh:mi:ss')                                                                                    shij,\n" +
                    "        to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(to_char(f.shij, 'yyyy-mm-dd'),\n" +
                    "                                                                        'yyyy-mm-dd')                                AS tians,\n" +
                    "        x.zhi                                                                                                        AS tiansbj,\n" +
                    "        to_char(min(f.youxq),\n" +
                    "                'YYYY-MM-DD')                                                                                           youxsj,\n" +
                    "        getJiesdws(w.id, f.shij)                                                                                        jiesdws\n" +
                    "      FROM fabwjb f, wenjb w, xitxxb x\n" +
                    "      WHERE f.wenjb_id = w.id\n" +
                    "            -- AND f.diancxxb_id = 515\n" +
                    "            AND x.mingc = '新闻天数设置'\n " +
                    "      AND f.shij >= to_date('" + riq + "','yyyy-mm') \n " +
                    "      GROUP BY w.id, w.reny, w.biaot, f.shij, x.zhi \n " +
                    "      ORDER BY shij DESC) s";
            List<Map<String, Object>> rs = jdbcTemplate.queryForList(sql);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    //------------------------------------------------弹出层---------------------------------------------------------------
    @RequestMapping(value = "/getWenjmx")
    public
    @ResponseBody
    Map<String, Object> getWenjmx(String id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String sql = " select id,biaot,neir,reny,to_char(shij,'yyyy-mm-dd') shij from wenjb where id=" + id;
            Map<String, Object> wenjmx = jdbcTemplate.queryForMap(sql);
            sql = " select yuanmc, url from fujb where wenjb_id=" + id;
            List<Map<String, Object>> fujList = jdbcTemplate.queryForList(sql);
            wenjmx.put("fujList", fujList);
            return wenjmx;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
