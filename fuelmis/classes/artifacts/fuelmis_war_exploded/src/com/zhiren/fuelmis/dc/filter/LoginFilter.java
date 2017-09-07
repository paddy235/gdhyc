package com.zhiren.fuelmis.dc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

/**
 * 登陆拦截器
 * 
 * @author 陈宝露
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		
		String contextPath = request.getContextPath();

		if (renyxx == null) {
			response.sendRedirect(contextPath + "/login");
			return;
		}
		if (renyxx.getMingc() != null && renyxx.getMim() != null) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
