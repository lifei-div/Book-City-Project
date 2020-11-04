package com.atguigu.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决post请求中文乱码
        //一定要在获取请求参数之前调用
		request.setCharacterEncoding("UTF-8");
		//解决浏览器响应乱码
		response.setContentType("text/html; charset=UTf-8");
		
		// 获取是哪个一个请求
		String action = request.getParameter("action");
		/*
		 * if ("login".equals(action)) { // System.out.println("处理登录需求"); login(request,
		 * response);
		 * 
		 * } else if ("regist".equals(action)) { // System.out.println("处理注册需求");
		 * regist(request, response);
		 * 
		 * }
		 */

		// 利用反射优化if()elseif()
		try {
			// 获取action业务鉴别字符串，获取响应的业务 方法反射对象
			Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
			System.out.println(method);
			// 调用目标业务 方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);//把异常抛给Filter过滤器
		}
		// System.out.println(action);
	}

}
