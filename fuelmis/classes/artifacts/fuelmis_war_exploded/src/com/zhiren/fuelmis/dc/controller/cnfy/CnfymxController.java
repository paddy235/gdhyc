package com.zhiren.fuelmis.dc.controller.cnfy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.cnfy.ICnfymxService;

/** 
 * @author 摧枯拉朽COCOA
 */
@Controller
@RequestMapping("/cnfymx")
public class CnfymxController {
	
	@Autowired
	private ICnfymxService cnfymxService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/cnfymxcx")
	public void getCnfymxTable(HttpServletRequest request , HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq")+"-01";
		String nianf = riq.substring(0, 4);
		Map<String, Object> map = new HashMap();
		map.put("riq", riq);
		map.put("nianf",nianf );
		String tablehtml = cnfymxService.getTabelData(map);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(tablehtml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
