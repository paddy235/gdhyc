package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.dao.cnfy.ZafjswhDao;
import com.zhiren.fuelmis.dc.entity.common.Combobox;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.cnfy.IZafjswhService;
import com.zhiren.fuelmis.dc.utils.JsonUtil;
import com.zhiren.fuelmis.dc.utils.NumberToCN;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/zafjswh")
public class ZafjswhController {
	
	@Autowired
	private IZafjswhService zafjswhService ;
	
	@Autowired
	private ZafjswhDao zafjswhDao;
	
	@RequestMapping(value = "/getZafjsdata")
	public void getZafjsByNianf(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String nianf = request.getParameter("nianf").substring(0,7);
		System.out.println("I want to fuck you:"+nianf);
		JSONObject jsonArray = zafjswhService.getZafjsdata(nianf);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getzaffybxdbidandrenyxx")
	public void getzaffybxdbIdAndRenyxx(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String zaffybxdid = zafjswhDao.getZaffybxdId()+"";
		Renyxx user = (Renyxx) request.getSession().getAttribute("user");
		Map map = new HashMap();
		map.put("zaffybxdid",zaffybxdid );
		map.put("xingm", user.getQuanc());
		map.put("department", user.getBum());
		JSONObject json = JSONObject.fromObject(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/gethetBianh")
	public void gethetBianh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		List<HashMap<String, Object>> list = zafjswhDao.getHetBianh();
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox();
			combobox.setName("请选择");
			combobox.setValue(-1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("BIANH"), list.get(i).get("ID"));
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
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/gethetzaf")
	public void getHetzaf(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String hetid = request.getParameter("hetid");
		List<HashMap<String, Object>> list = zafjswhDao.getHetZaf(hetid);
		JSONArray jsonArray = new JSONArray();
		if (list != null) {
			Combobox combobox = new Combobox();
			combobox.setName("请选择");
			combobox.setValue(-1);
			jsonArray.add(combobox);
			for (int i = 0; i < list.size(); i++) {
				combobox = new Combobox(list.get(i).get("MINGC"), list.get(i).get("ID"));
				jsonArray.add(combobox);
			}
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getdanj")
	public void getdanj(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String hetid = request.getParameter("hetid");
		String feiyxmid = request.getParameter("feiyxmid");
		Map map = new HashMap();
		map.put("hetid", hetid);
		map.put("feiyxmid",feiyxmid );
		String danj = zafjswhDao.getdanj(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(danj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/saveZafdjb")
	public void saveZafDjb(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		//mybatis将参数以实体类作为参数，这样插入的时候将id返回给实体类的id属性，通过实体类获取到插入记录的id值.
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		List zaf = zafjswhDao.getZafyById(map.get("FEIYXM").toString());
		String zaf_mingc = ((Map)zaf.get(0)).get("MINGC").toString();
		String zaf_danw = ((Map)zaf.get(0)).get("DANW").toString();
		String hetbianh = zafjswhDao.getHetBianhById(map.get("HETBIANH").toString());
		String beiz = "杂费项目："+zaf_mingc+"，合同编号："+hetbianh+"，费用期间："+map.get("KAISSJ").toString()+
				"至"+map.get("JIESSJ").toString()+"，单价："+map.get("DANJ").toString()+zaf_danw+
				"，数量："+map.get("SHUL").toString()+"，金额："+map.get("JINE").toString()+"元。";
		map.put("beiz", beiz);
		int ret = zafjswhService.saveZafDjb(map);
		String reback;
		if(ret>0){
			reback="保存成功！！！";
		}else{
			reback="保存失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/updateZafdjb")
	public void updateZafdjb(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		//mybatis将参数以实体类作为参数，这样插入的时候将id返回给实体类的id属性，通过实体类获取到插入记录的id值.
		Map map = JsonUtil.parseJSON2Map(request.getParameter("info"));
		List zaf = zafjswhDao.getZafyById(map.get("FEIYXM").toString());
		String zaf_mingc = ((Map)zaf.get(0)).get("MINGC").toString();
		String zaf_danw = ((Map)zaf.get(0)).get("DANW").toString();
		String hetbianh = zafjswhDao.getHetBianhById(map.get("HETBIANH").toString());
		String beiz = "杂费项目："+zaf_mingc+"，合同编号："+hetbianh+"，费用期间："+map.get("KAISSJ").toString()+
				"至"+map.get("JIESSJ").toString()+"，单价："+map.get("DANJ").toString()+zaf_danw+
				"，数量："+map.get("SHUL").toString()+"，金额："+map.get("JINE").toString()+"元。";
		map.put("beiz", beiz);
		int ret = zafjswhService.updateZafDjb(map);
		String reback;
		if(ret>0){
			reback="修改成功！！！";
		}else{
			reback="修改失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/savezaffybxd")
	public void saveZaffybxd(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		//mybatis将参数以实体类作为参数，这样插入的时候将id返回给实体类的id属性，通过实体类获取到插入记录的id值.
		String id = request.getParameter("zaffybxdid");
		String nianf = request.getParameter("nianf");
		long reyxxb_id = ((Renyxx)session.getAttribute("user")).getId();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		Random r=new Random();
		String next =String.format("%04d", r.nextInt(9999)) ;
		String bianh = date + next;
		double zongje = zafjswhDao.getZongjeById(id);
		Map map = new HashMap();
		map.put("id", id);
		map.put("reyxxb_id", reyxxb_id);
		map.put("bianh", bianh);
		map.put("zongje", zongje);
		map.put("riq", nianf);
		int ret = zafjswhService.saveZaffybxd(map);
		String reback;
		if(ret>0){
			reback="保存成功！！！";
		}else{
			reback="保存失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/updatezaffybxd")
	public void updatezaffybxd(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		//mybatis将参数以实体类作为参数，这样插入的时候将id返回给实体类的id属性，通过实体类获取到插入记录的id值.
		String id = request.getParameter("zaffybxdid");
		String nianf = request.getParameter("nianf");
		long reyxxb_id = ((Renyxx)session.getAttribute("user")).getId();
		double zongje = zafjswhDao.getZongjeById(id);
		Map map = new HashMap();
		map.put("id", id);
		map.put("reyxxb_id", reyxxb_id);
		map.put("zongje", zongje);
		map.put("riq", nianf);
		int ret = zafjswhService.updateZaffybxd(map);
		String reback;
		if(ret>0){
			reback="更新成功！！！";
		}else{
			reback="更新失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getbxddata")
	public void getBxddata(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String zaffybxd_id = request.getParameter("zaffybxd_id");
		List<HashMap<String, Object>> list = zafjswhDao.getBxddata(zaffybxd_id);
		String[][] arr = new String[6][12];
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map = list.get(i);
			arr[i][0] =  map.get("BEIZ").toString();
			long jine = Long.parseLong(map.get("JINE").toString());
			long fen = jine%10;
			long jiao = ((long)(jine/10))%10;
			long yuan = ((long)(jine/100))%10;
			long shi = ((long)(jine/1000))%10;
			long bai = ((long)(jine/10000))%10;
			long qian = ((long)(jine/100000))%10;
			long wang = ((long)(jine/1000000))%10;
			long shiw = ((long)(jine/10000000))%10;
			long baiw = ((long)(jine/100000000))%10;
			long qianw = ((long)(jine/1000000000))%10;
			arr[i][1] = qianw+"";
			arr[i][2] = baiw+"";
			arr[i][3] = shiw+"";
			arr[i][4] = wang+"";
			arr[i][5] = qian+"";
			arr[i][6] = bai+"";
			arr[i][7] = shi+"";
			arr[i][8] = yuan+"";
			arr[i][9] = jiao+"";
			arr[i][10] = fen+"";
			arr[i][11] = map.get("ID").toString();//存放rl_cnfy_zafhtfydjb表的id，供修改时调用
		}
		//总金额
		BigDecimal zongjine = zafjswhDao.getZongJine(zaffybxd_id);
		String zongjine_dax = NumberToCN.number2CNMontrayUnit(zongjine);
		arr[5][0] = zongjine_dax;
		double zje_db =Double.parseDouble(zongjine.toString());
		long zje_long = (long)zje_db*100;
		long fen_z = zje_long%10;
		long jiao_z = ((long)(zje_long/10))%10;
		long yuan_z = ((long)(zje_long/100))%10;
		long shi_z = ((long)(zje_long/1000))%10;
		long baiz_z = ((long)(zje_long/10000))%10;
		long qian_z = ((long)(zje_long/100000))%10;
		long wang_z = ((long)(zje_long/1000000))%10;
		long shiw_z = ((long)(zje_long/10000000))%10;
		long baiw_z = ((long)(zje_long/100000000))%10;
		long qianw_z = ((long)(zje_long/1000000000))%10;
		arr[5][10] = fen_z+"";
		arr[5][9] = jiao_z+"";
		arr[5][8] = yuan_z+"";
		arr[5][7] = shi_z+"";
		arr[5][6] = baiz_z+"";
		arr[5][5] = qian_z+"";
		arr[5][4] = wang_z+"";
		arr[5][3] = shiw_z+"";
		arr[5][2] = baiw_z+"";
		arr[5][1] = qianw_z+"";
		JSONArray jsonArray = JSONArray.fromObject(arr);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getUserAndDept")
	public void getUserAndDept(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("zaffybxd_id");
		List userlist = zafjswhDao.getUserAndDeptById(id);
		JSONObject json = JSONObject.fromObject((Map)userlist.get(0));
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getzafhtfydjbById")
	public void getzafhtfydjbById(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		List djblist = zafjswhDao.getZafhtfydjbById(id);
		JSONObject json = JSONObject.fromObject((Map)djblist.get(0));
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/delZafjswh")
	public void delZafjswhById(HttpServletRequest request , HttpServletResponse response , HttpSession session){
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		int ret = zafjswhService.delZaffybxdById(id);
		String reback;
		if(ret>0){
			int ret1 = zafjswhService.delZaffydjbById(id);
			if(ret1>0){
				reback="删除成功！！！";
			}else{
				reback="删除失败！！！";
			}
		}else{
			reback="删除失败！！！";
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(reback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
