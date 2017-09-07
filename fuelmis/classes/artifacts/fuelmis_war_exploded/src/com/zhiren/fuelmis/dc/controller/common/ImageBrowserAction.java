package com.zhiren.fuelmis.dc.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;


import com.google.gson.Gson;

import com.zhiren.fuelmis.dc.utils.FileBrowserUtil ;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/specificationGroup")
public class ImageBrowserAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @RequestMapping(value = "/browser")
	public void browser(HttpServletRequest request, HttpServletResponse response,String path){
		try {
			String jsons=FileBrowserUtil.getFiles(request.getSession().getServletContext().getRealPath("/uploadFolder"+path),request.getContextPath(), request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			System.out.println(jsons);
			out.write(jsons);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @RequestMapping(value = "/uploadImage")
	public void uploadImage(HttpServletRequest request, HttpServletResponse response,String path,String type,File image,String imageFileName){
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String, String> message=new HashMap<String, String>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateDir=simpleDateFormat.format(new Date());
			path=request.getSession().getServletContext().getRealPath("/uploadFolder")+"/"+dateDir;
			File fileDir=new File(path);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			FileUtils.copyFile(image, new File(path+File.separator+imageFileName));
			message.put("type", "success");
			String url= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
			map.put("url",url+"/uploadFolder/"+dateDir+File.separator+imageFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			message.put("type", "error");
			e.printStackTrace();
		}
		map.put("message", message);
		try {
			String jsons=new Gson().toJson(map);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(jsons);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
