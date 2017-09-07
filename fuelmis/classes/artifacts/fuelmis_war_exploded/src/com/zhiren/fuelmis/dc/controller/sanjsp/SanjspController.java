package com.zhiren.fuelmis.dc.controller.sanjsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.utils.PropertiesUtil;

/**
 * 
 * @author 刘志宇
 * 
 */
@Controller
@RequestMapping("sanjsp")
public class SanjspController {

	@RequestMapping(value = "/url")
	public void getUrl(@RequestParam(defaultValue = "") String riq,
			@RequestParam(defaultValue = "") String dianc,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		response.setContentType("text/html;charset=UTF-8");

		
		PrintWriter writer = null;
		try {
			String sanjspUrl = PropertiesUtil.getValue("sanjsp_url");
			session.setAttribute("sanjspUrl", sanjspUrl);
			writer = response.getWriter();
			writer.write(sanjspUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
