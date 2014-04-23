package com.noword.bean;

import javax.servlet.Filter;   
import javax.servlet.FilterConfig;   
import javax.servlet.ServletException;   
import javax.servlet.ServletRequest;   
import javax.servlet.ServletResponse;   
import javax.servlet.FilterChain;   
import java.io.IOException;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpSession;   
import javax.servlet.http.HttpServletResponse;   
public class MyFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,   
			FilterChain chain) throws IOException, ServletException {
			// TODO Auto-generated method stub
			HttpServletRequest req = (HttpServletRequest) request;   
			HttpServletResponse res = (HttpServletResponse) response;   
			HttpSession session = req.getSession(true);  
			String user = (String)session.getAttribute("user");
			if (user == null || "".equals(user)) {   
		     //跳转到登陆页面   
				 res.sendRedirect("welcomePage.xhtml");    
			}else {   
		      //已经登陆,继续此次请求
				chain.doFilter(request,response);   
			}   
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
			
	}
	
}
