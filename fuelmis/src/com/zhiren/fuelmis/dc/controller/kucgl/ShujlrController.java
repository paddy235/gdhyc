package com.zhiren.fuelmis.dc.controller.kucgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.service.impl.common.SaveService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.service.common.ICommonService;
import com.zhiren.fuelmis.dc.service.kucgl.IShujlrService;
import com.zhiren.fuelmis.dc.utils.DateUtil;

/** 
 * @author 陈宝露
 */
@Controller
@RequestMapping("kucgl/shujlr")
public class ShujlrController {
	
	@Autowired
	private IShujlrService shujlrService;
	
	@Autowired
	private ICommonService commonService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@RequestMapping(value = "/getPandxx")
	public void getPandxx(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String dianc,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String sql="select\n" +
				"\t\tP.ID,\n" +
				"\t\tDIANCXXB_ID,\n" +
				"\t\tP.ZHANGMKC,\n" +
				"\t\tP.SHIPKC,\n" +
				"\t\tP.CHANGSL,\n" +
				"\t\tP.SHUIFCTZL,\n" +
				"\t\tP.YINGKD,\n" +
				"\t\tP.FUJZT,P.FUJMC  FUJMC ,P.ZHUANGT\n" +
				"\t\tfrom PAND_GDJT P \n" +
				"\t\twhere  substr(P.RIQ ,0,7)='"+riq+"'\n" +
				"\t\t\tand P.DIANCXXB_ID = "+dianc+"\n" ;
				List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
				JSONArray jsonArray=JSONArray.fromObject(list);
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/createPandxx")
	public void createPandxx(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String dianc,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("teext/html;charset=UTF-8");
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE_MONTH):riq);
		map.put("dianc", ((Diancxx)request.getSession().getAttribute("diancxx")).getId());
		JSONObject jsonArray = shujlrService.insertPandxx(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/savePandxx")
	public void savePandxx(String data,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		
		List<Map<String,Object>> list=JSONArray.fromObject(data);
		shujlrService.save(list);
	}
	
	@RequestMapping(value = "/checkCount")
	public void checkCount(@RequestParam(defaultValue="") String riq,@RequestParam(defaultValue="") String dianc,
			HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer  = null;
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("riq", "".equals(riq)?DateUtil.format(new Date(),DateUtil.DateFormatType.SIMPLE_TYPE_MONTH):riq);
		map.put("dianc", "-1".equals(dianc)?"":dianc);
		try {
			writer = response.getWriter();
			writer.write(shujlrService.getCount(map).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping(value = "/uploadFile")
	public void uploadFile(@RequestParam(value = "upFile", required = false) MultipartFile file,
			@RequestParam String id,HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String result = commonService.uploadFile(file);

		JSONArray jsonArray = new JSONArray();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("fujmc", result);
		map.put("fujzt", 1);
		
		if(result!=null){
			shujlrService.updateFuj(map);
			map.put("msg", "文件上传成功！");
			jsonArray.add(map);
		}else{
			map.put("msg", "文件上传失败！");
			jsonArray.add(map);
		}
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/submitData")
	public void submitData(@RequestParam(defaultValue="") String id,HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		response.setContentType("text/html;charset=UTF-8");
		map.put("ID", id);
		map.put("dianc", ((Diancxx)request.getSession().getAttribute("diancxx")).getId());
		//JSONArray jsonArray = shujlrService.submitData(map);
		String f = shujlrService.submitData(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
