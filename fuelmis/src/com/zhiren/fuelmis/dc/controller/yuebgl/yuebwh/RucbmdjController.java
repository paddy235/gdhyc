package com.zhiren.fuelmis.dc.controller.yuebgl.yuebwh;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zhiren.fuelmis.dc.dao.yuebgl.yuebwh.RucbmdjDao;
import com.zhiren.fuelmis.dc.service.yuebgl.yuebwh.IRucbmdjService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("yuebgl/yuebwh/rucbmdj")
public class RucbmdjController {

    @Resource
    private IRucbmdjService rucbmdjService;
    @Resource
    private RucbmdjDao rucbmdjDao;

    @RequestMapping(value = "/getAll")
    public void getAll(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="257") String dianc,
                       HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String,Object>();
        String[] date = riq.split("-");
        if("".equals(riq)){
            SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM");
            Date today = new Date();
            riq = formate.format(today);
        }

        map.put("riq", riq);
        map.put("dianc", "-1".equals(dianc)?"":dianc);
        List checkData = rucbmdjDao.getCheckData(map);
        List all_list = new ArrayList();
        if(checkData.size()>0){//如果数据存在，以后则从业务表中查询
            all_list = rucbmdjDao.getFirstData(map);
        }else{//如果数据不存在，则从yueslb中查询
            all_list = rucbmdjDao.getSecondData(map);
        }
        JSONArray ja = JSONArray.fromObject(all_list);
        JSONObject jo = new JSONObject();
        jo.put("data", ja);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("getRucbmdjList")
    public void getRucbmdjList(String search,HttpServletResponse response) throws IOException {
        Map<String,Object> map= JSON.parseObject(search);
        List<Map<String,Object>> list=rucbmdjDao.getRucbmdjList(map);
        response.getWriter().write(JSON.toJSONString(list));
    }
    @RequestMapping("createData")
    public void createData(String search,HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map=JSON.parseObject(search);
        rucbmdjService.createData(map);
    }
    @RequestMapping("delData")
    public void delData(String search,HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map=JSON.parseObject(search);
        rucbmdjService.delData(map);
    }
    @RequestMapping(value = "saveData")
    public void saveData(@RequestParam(defaultValue="") String rucbmdjList, HttpServletRequest request,HttpServletResponse response) throws Exception {
//			rucbmdjList=new String(rucbmdjList.getBytes("iso-8859-1"),"UTF-8");
        List<ConcurrentMap> list = JSON.parseArray(rucbmdjList,ConcurrentMap.class);
        rucbmdjService.saveData(list);

    }

}
