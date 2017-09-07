package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.cnfy.IYuszhcxService;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/yuszhsearch")
public class YuszhcxController {
	@Autowired
	private IYuszhcxService yuszhcxService;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getzonghcxdata")
	public void getzonghcxdata(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String diancid = request.getParameter("diancid");
		String nianf = request.getParameter("nianf");
		String zafid = request.getParameter("zafid");
		Map map = new HashMap();
		map.put("diancid",diancid );
		map.put("nianf",nianf );
		map.put("zafid", zafid);
		JSONObject jsonArray = yuszhcxService.getYuszhcxdata(map);
		PrintWriter writer  = null;
		try {
			writer = response.getWriter();
			writer.write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
