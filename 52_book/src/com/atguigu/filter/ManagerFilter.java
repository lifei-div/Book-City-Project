package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ManagerFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
       HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	   Object user = httpServletRequest.getSession().getAttribute("user");
	   if (user == null) {
		httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
	}else {
	   chain.doFilter(request, response);
	}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
