package com.zhiren.fuelmis.dc.controller.yansgl;
import com.alibaba.fastjson.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
/**
 * @author rain
 */
@Controller
@RequestMapping("/yuebgl/yuebwh")
public class YuebsjpzController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getYuebsjpzList")
    public
    @ResponseBody
    List<Map<String, Object>> getYuebsjpzList(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try{
            String sql = " SELECT * FROM yuebsjpzb order by YUEBJQ desc";
            return jdbcTemplate.queryForList(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    @RequestMapping(value = "/saveYuebsjpz")
    public void saveYuebsjpz(HttpServletRequest request, HttpServletResponse response, String data) {
        response.setContentType("text/html;charset=UTF-8");
        try{
            String sql;
            List<Map> list= JSONArray.parseArray(data,Map.class);
            Object id;
            for (Map map:list) {
                id=map.get("ID");
                if(null==id){
                    sql="insert into YUEBSJPZB (id,yuebjq,yuebksrq,yuebjzrq)values" +
                            "((select nvl(max(id),0)+1 from yuebsjpzb),'"+map.get("YUEBJQ")+"','"+map.get("YUEBKSRQ")+"','"+map.get("YUEBJZRQ")+"')";

                }else {
                    sql="update yuebsjpzb set yuebjq='"+map.get("YUEBJQ")+"',yuebksrq='"+map.get("YUEBKSRQ")+"',yuebjzrq='"+map.get("YUEBJZRQ")+"' where id =  "+id;
                }
                jdbcTemplate.update(sql);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    @RequestMapping(value = "/deleteYuebsjpz")
    public void deleteYUebsjpz(HttpServletRequest request, HttpServletResponse response, String id) {
        try{
            String sql="delete from YUEBSJPZB where id="+id;
            jdbcTemplate.update(sql);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
