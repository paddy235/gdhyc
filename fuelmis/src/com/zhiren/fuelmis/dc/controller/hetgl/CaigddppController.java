package com.zhiren.fuelmis.dc.controller.hetgl;

import com.zhiren.fuelmis.dc.dao.hetgl.CaigddppDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.utils.Sequence;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhiyu on 2016/12/30.
 */

@Controller
@RequestMapping("hetgl/caigddpp")
public class CaigddppController {
    @Autowired
    private CaigddppDao caigddppDao;
    /**
     * 获取采购订单匹配信息
     * @param request
     * @param response
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/getCaigdppList")
    public void getCaigdppList(HttpServletRequest request , HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        String diancid = request.getParameter("diancid");
        String meikid = request.getParameter("meikid");
        String pinzid = request.getParameter("pinzid");
        Map map = new HashMap();
        map.put("diancid", diancid);
        map.put("meikid", meikid);
        map.put("pinzid", pinzid);
        System.out.println(map.toString());
        List<Map<String,Object>> list = caigddppDao.getCaigddppList(map);
        JSONArray JO = JSONArray.fromObject(list);
        System.out.println(JO.toString());
//		JSONObject json =  new JSONObject();
//		json.put("datalist", JO);
        PrintWriter writer  = null;
        try {
            writer = response.getWriter();
            writer.write(JO.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/getCaigddxxcombox")
    public void getCaigddxxcombox(HttpServletRequest request,
                                  HttpServletResponse response, HttpSession session) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        List list = caigddppDao.getCaigddxxcombox();
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Combobox combobox = new Combobox(((Map)list.get(i)).get("MINGC"),((Map)list.get(i)).get("ID"));
                jsonArray.add(combobox);
            }
        }
        try {
            writer = response.getWriter();
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/saveCaigddpp")
    public void saveCaigddpp(HttpServletRequest request , HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String info = request.getParameter("info");
        JSONArray ja = JSONArray.fromObject(info);
        for(int i=0;i<ja.size();i++){
            Map map =   (Map) ja.get(i);
            if(null == map.get("ID")||"".equals(map.get("ID"))){
                Long id = Long.parseLong(Sequence.nextId());
                map.put("ID", id);
                caigddppDao.addcaigddpp(map);
            }else{
                caigddppDao.updatecaigddpp(map);
            }
        }
    }
    @RequestMapping(value = "/deleteCaigddpp")
    public void deleteCaigddpp(HttpServletRequest request , HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        caigddppDao.deleteCaigddppById(id);
    }
}
