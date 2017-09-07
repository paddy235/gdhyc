package com.zhiren.fuelmis.dc.controller.jih;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiren.fuelmis.dc.service.jih.IYudjhcxService;

/** 
 * @author 摧枯拉朽cococa
 */
@Controller
@RequestMapping("/yuedjh")
public class YuedjhController {
	
	@Autowired
	private IYudjhcxService yudjhcxService;
	
	@RequestMapping(value = "/yuedjhcx")
	public void getYuecgjh(HttpServletRequest request , HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String riq = request.getParameter("riq");
		String diancid = request.getParameter("diancid");
		if(null == diancid || ""==diancid){
			diancid = "-1";
		}
		String tablehtml = yudjhcxService.getTabelData(diancid, riq);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(tablehtml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
