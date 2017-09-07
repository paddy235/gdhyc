package com.zhiren.fuelmis.dc.controller.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.entity.xitgl.Diancxx;
import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;
import com.zhiren.fuelmis.dc.service.xitgl.IDiancxxService;
import com.zhiren.fuelmis.dc.service.xitgl.IRenyxxService;
import com.zhiren.fuelmis.dc.utils.addVersionToVm.AddJsAndCssVersionToVm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 陈宝露
 */
@Controller
public class LoginController {

	@Autowired
	private IRenyxxService renyxxService;
	
	@Autowired
	private IDiancxxService diancxxService;
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 登录系统
	 * @param userName		
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logon")
	public String logon(@RequestParam("username") String userName, @RequestParam("password") String password, HttpServletRequest request, HttpSession session) {
//		String realPath= request.getSession().getServletContext().getRealPath("/");
//		AddJsAndCssVersionToVm.execute(realPath);
		Renyxx renyxx = renyxxService.getRenyxx(userName,password);
		if(renyxx!=null){
			session.removeAttribute("errorMsg");
			session.setAttribute("user", renyxx);
			Diancxx diancxx  = diancxxService.getOne();
			session.setAttribute("diancxx", diancxx);
			//session.setMaxInactiveInterval(5);
			return "main/index";
		} else {
			session.setAttribute("errorMsg", "用户名/密码错误！");
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletResponse response, HttpSession session) {
		session.removeAttribute("user");
		return "/login";
	}
	
	@RequestMapping(value = "/sessionTimeout")
	public String sessionTimeout(HttpServletResponse response, HttpSession session) {
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		if (renyxx == null) {
			 try {
				response.getWriter().print("sessionTimeout");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			
		}
		return null;
	}
}
