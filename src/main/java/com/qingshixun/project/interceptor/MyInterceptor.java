package com.qingshixun.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * springmvc 的 Interceptor 要实现 HandlerInterceptor 接口
 */
public class MyInterceptor implements HandlerInterceptor {

	/**
	 * Controller 执行后且视图返回后调用这个方法，可以得到执行 Controller 时的异常信息，可以做日志记录、资源清理等
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyInterceptor afterCompletion " + handler);
	}

	/**
	 * Controller 执行后但是未返回视图前调用这个方法，可以在返回前对模型数据进行加工处理
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {


	}

	/**
	 * 在 Controller 执行前执行这个方法，返回 true 表示继续执行，返回 false 表示终止执行，可以加入等量校验、权限拦截等
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object user = session.getAttribute("userInfo");
		// 获取当前访问的URL地址
		StringBuffer requestURL = request.getRequestURL();
		// 排除登陆界面login.jsp和登陆处理doLogin action以及images目录
		// 判断有无user对象
		System.out.println("拦截地址："+requestURL);
		if (requestURL.indexOf("loginUser")>0 || requestURL.indexOf("validateLogin")>0 || requestURL.indexOf("images") > 0 || requestURL.indexOf("css") > 0 ||  requestURL.indexOf("plugins") > 0  ||user != null) {
			// 如果有，表示已经登录，可以访问
			return true;
		} else {
			// 没有user对象，表示没有登录，不能继续访问，跳转到登录界面
			response.sendRedirect(request.getContextPath()+"/user/loginUser");
			return false;
		}
		/* System.out.println("MyInterceptor postHandle " + handler); */
		
		
	}

}
