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
		//���post������������
        //һ��Ҫ�ڻ�ȡ�������֮ǰ����
		request.setCharacterEncoding("UTF-8");
		//����������Ӧ����
		response.setContentType("text/html; charset=UTf-8");
		
		// ��ȡ���ĸ�һ������
		String action = request.getParameter("action");
		/*
		 * if ("login".equals(action)) { // System.out.println("�����¼����"); login(request,
		 * response);
		 * 
		 * } else if ("regist".equals(action)) { // System.out.println("����ע������");
		 * regist(request, response);
		 * 
		 * }
		 */

		// ���÷����Ż�if()elseif()
		try {
			// ��ȡactionҵ������ַ�������ȡ��Ӧ��ҵ�� �����������
			Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
			System.out.println(method);
			// ����Ŀ��ҵ�� ����
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);//���쳣�׸�Filter������
		}
		// System.out.println(action);
	}

}
