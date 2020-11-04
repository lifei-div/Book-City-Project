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
		//����BaseDao�׳����쳣
		try {
			chain.doFilter(request, response);
			JdbcUtils.commitAndClose();//�ύ����
		} catch (Exception e) {
			JdbcUtils.rollbackAndClose();//�ع�����
			e.printStackTrace();
			throw new RuntimeException(e);//���쳣�׸�Tomact����չʾ�Ѻô���ҳ�� 
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
