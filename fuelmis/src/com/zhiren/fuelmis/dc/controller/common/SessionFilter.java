package com.zhiren.fuelmis.dc.controller.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) resp;
//        HttpSession session = request.getSession();
//        // 获得用户请求的URI
//        String path = request.getRequestURI();
//        if(path.contains("login")||path.contains("logon")||path.contains("logout")){
//        	chain.doFilter(request, response);
//        	return;
//        }else{
//        	Renyxx user = (Renyxx) session.getAttribute("user");  
//            if(user == null) { 
//                response.sendRedirect("http://localhost:8080/fuelmis/dc/logout");
////                PrintWriter out = response.getWriter();
////				out.println("<html>");
////				out.println("<script type=\"text/javascript\">");
////				out.println("top.window.location.href = 'logout';");
////				out.println("</script>");
////				out.println("</html>");
//
//            } else {  
                chain.doFilter(request, response);  
//            } 
//        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
