package com.zhiren.fuelmis.dc.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhiren.fuelmis.dc.entity.xitgl.Renyxx;

/**
 * 登陆拦截器
 * 
 * @author 陈宝露/刘志宇
 */
public class LoginInterceptor implements HandlerInterceptor {

	@SuppressWarnings("unused")
	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
	}

	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView view)
			throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String requestUri = request.getRequestURI();// /fuelmis/dc/login/
		
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				return true;
			}
		}
//
		HttpSession session = request.getSession();
		Renyxx renyxx = (Renyxx) session.getAttribute("user");
		if (renyxx == null) {
			 response.getWriter().print("sessionTimeout");
            return false;	 
		}
//		if(requestUri.endsWith("baseSet/getBanz")){
//			System.out.println("---------ok--------");
//			response.sendRedirect("/fuelmis/dc/login/");
//		}
		return true;
	}
}