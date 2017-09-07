package com.zhiren.fuelmis.dc.controller.gongyspg.hetb;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.zhiren.fuelmis.dc.utils.formular.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.dao.gongyspg.hetbdao.HetbDao;
import com.zhiren.fuelmis.dc.entity.gongyspg.hetb.Hetb;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.gongyspg.hetb.HetbService;
import com.zhiren.fuelmis.dc.utils.DateUtil;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author ZY
 *
 */
@Controller
@RequestMapping("gongyspg/hetb")
public class HetbController {

	@Autowired
	private HetbService hetbService;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private HetbDao hetbDao;
	/**
	 * 新增
	 * @param info
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addHetb")
	public void addHetb(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> het = JSONObject.fromObject(info);
		//获取电厂信息
		//Diancxx diancxx = (Diancxx) session.getAttribute("diancxx");
		Renyxx user = (Renyxx) session.getAttribute("user");
		//session暂时有问题
		het.put("LURRY",user.getId());
//		hetb.setLurry(2571517772l);
		het.put("LURSJ",DateUtil.format(new Date()));
		hetbService.saveHetb(het);
	}
	
	@RequestMapping(value = "/updateHetb")
	public void updateHetb(@RequestParam String info,HttpServletRequest request , HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");
		String star = request.getParameter("star");
		String qnet_ar = request.getParameter("qnet_ar");
		PrintWriter writer  = null;
		JSONObject json = JSONObject.fromObject(info);
		//获取电厂信息
		@SuppressWarnings("static-access")
		Hetb hetb = (Hetb) json.toBean(json, Hetb.class);
		JSONArray jsonArray = hetbService.updateHetb(hetb,star,qnet_ar);
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @RequestMapping("getZhibList")
    public @ResponseBody List<Map<String,Object>> getZhibList(){
	    String sql ="select * from rl_gys_zhibdyb";
	    return jdbcTemplate.queryForList(sql);
    }

	@RequestMapping(value = "/getHetbList")
	public @ResponseBody JSONArray getHetbList(String search,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
        Map map=JSONObject.fromObject(search);
        Object date=map.get("date");
        Object gongysb_id=map.get("gongysb_id");
        String sql="SELECT h.id,g.MINGC gongysmc,h.KAISRQ,h.JIESRQ,h.HETL,z.qnet_ar,z.STAR,z.VDAF,z.MT,p.MINGC pingffa,h.BEIZ,r.quanc  LURRY,h.LURSJ\n " +
                "FROM rl_gys_hetb h left JOIN\n" +
                "(SELECT hetb_id,\n" +
                "sum(nvl(CASE WHEN zhibdm='QNET_AR' THEN zhibz  END ,0)) qnet_ar,  \n" +
                "sum(nvl(CASE WHEN zhibdm='STAR' THEN nvl(zhibz,0)  END,0)) STAR,  \n" +
                "sum(nvl(CASE WHEN zhibdm='VDAF' THEN nvl(zhibz,0)  END,0)) VDAF, \n" +
                "sum(nvl(CASE WHEN zhibdm='MT' THEN nvl(zhibz,0)  END,0)) MT\n" +
                "FROM rl_gys_hetzlb\n" +
                "group by hetb_id) z ON h.id=z.hetb_id \n" +
                "inner join gongysb g on g.id=h.GONGYSB_ID\n" +
                "inner join rl_gys_pingffab p on p.id=h.pingffab_id\n" +
                "inner join RENYXXB r on r.id=h.lurry\n" +
                "where '"+date+"' between substr(h.kaisrq,0,7) and substr(h.jiesrq,0,7)\n";
        if(!gongysb_id.toString().equals("-1")){
            sql+="and h.gongysb_id="+gongysb_id;
        }
        List list=jdbcTemplate.queryForList(sql);
        Object[][] array=Result.list2array(list);
        return JSONArray.fromObject(array);
	}
//	/**
//	 * 获取全部
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/searchHetbList")
//	public void searchHetbList(@RequestParam String gongysbid,@RequestParam String strdate,@RequestParam String enddate, HttpServletRequest request , HttpServletResponse response) {
//		response.setContentType("text/html;charset=UTF-8");
//		Map<String,Object> map = new HashMap<String, Object>();
//		if(null == strdate || ("").equals(strdate)){
//			strdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
//		}
//		map.put("gongysbid", gongysbid);
//		map.put("strdate", strdate);
//		map.put("enddate", enddate);
//		
//		
//		JSONObject json = hetbService.getHetbList(map);
//		PrintWriter writer  = null;
//		try {
//			writer = response.getWriter();
//			writer.write(json.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}	
	
	
	
	@RequestMapping(value = "/delHetb")
	public void delHetb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		JSONArray json = hetbService.delHetb(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取编辑数据
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/editHetb")
	public @ResponseBody Map editHetb(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
			response.setContentType("text/html;charset=UTF-8");
        String sql="select * from RL_GYS_HETB where id="+id;
        Map map=jdbcTemplate.queryForMap(sql);
        sql="SELECT * from RL_GYS_HETZLB where HETB_ID="+id;
        List list =jdbcTemplate.queryForList(sql);
        map.put("zhilList",list);
        return map;
	}
    @RequestMapping("delZhib")
    public void  delZhib(@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
       String sql="delete from rl_gys_hetzlb where id="+id;
       jdbcTemplate.update(sql);
    }
	
}
