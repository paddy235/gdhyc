package com.zhiren.fuelmis.dc.controller.yansgl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.yansgl.IZhilhjblService;
import com.zhiren.fuelmis.dc.utils.formular.Result;


/**
 * @author 陈宝露
 */
@Controller
@RequestMapping("/yansgl")
public class ZhilhjblController {
	
	@Resource
	private IZhilhjblService zhilhjblService;
	
	@RequestMapping(value = "/getZhilhjbl")
	public void getZhilhjbl(HttpServletRequest request,HttpServletResponse response,@RequestParam String condition) {
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> map=JSONObject.fromObject(condition);
		PrintWriter writer = null;
		List<Map<String, Object>> list =zhilhjblService.getZhilhjbl(map);
		String[][] data = Result.list2array(list, new String[]{"ID","HUAYSJ","HUAYY","LURY","BEIZ","CAOZSJ","CAOZRY","AAR","AD","VDAF","MT","STAD","AAD","MAD","QBAD","HAD","VAD","FCAD","STD","QGRAD","HDAF","QGRAD_DAF","SDAF","VAR","T1","T2","T3","T4","HAR","QGRD","STAR","QNET_AR","ZHUANGT","DIANC_ID","HUAYBM","DANJLX","YEWHJ","CAOZLX","CAOZRIP"});
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(data).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
