package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.atguigu.utils.JdbcUtils;

public class TransactionFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//捕获BaseDao抛出的异常
		try {
			chain.doFilter(request, response);
			JdbcUtils.commitAndClose();//提交事务
		} catch (Exception e) {
			JdbcUtils.rollbackAndClose();//回滚事务
			e.printStackTrace();
			throw new RuntimeException(e);//把异常抛给Tomact管理展示友好错误页面 
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
