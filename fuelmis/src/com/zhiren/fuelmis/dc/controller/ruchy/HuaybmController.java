package com.zhiren.fuelmis.dc.controller.ruchy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiren.fuelmis.dc.service.ruchy.IHuaybmService;


/** 
 * @author 刘志宇
 */
@Controller
@RequestMapping("/ruchy/huaysh")
public class HuaybmController {
	
	@Autowired
	private IHuaybmService huaybmService;
	
	@RequestMapping(value = "/startScan")
	public void startScan(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		huaybmService.startSerial();
	}
	@RequestMapping(value = "/getPorts")
	public void getPorts(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		List<String> portList = huaybmService.getPortList();
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONArray.fromObject(portList).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/overScan")
	public void getBarcode(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		BufferedImage image = huaybmService.convert();
	//	Image.saveToPNG(image, "bar.png");  
		
		try {
			ImageIO.write(image , "PNG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getHuaybmBar")
	public void getHuaybmBar(HttpServletRequest request,@RequestParam(defaultValue = "") String parameter,
			HttpServletResponse response, HttpSession session) {
		Map<String, Object> p = JSONObject.fromObject(parameter);
		BufferedImage image = huaybmService.getHuaybmBar(
				Double.parseDouble(p.get("height").toString()),
		Double.parseDouble(p.get("dimension").toString()),
		Boolean.parseBoolean(p.get("showText").toString()));
		
	//	Image.saveToPNG(image, "bar.png");  
		
		try {
			ImageIO.write(image , "PNG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}
