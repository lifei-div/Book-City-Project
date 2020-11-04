package com.atguigu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.User;
import com.atguigu.serviceImp.UserService;
import com.atguigu.serviceImp.UserServiceImp;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImp();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 �� �� ȡ �� �� �� �� ��
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String code = request.getParameter("code");

		// 2 �� �� �� �� ֤ �� �� �� �� ȷ===д �� , Ҫ �� �� ֤ �� Ϊ :abcde
		if ("abcde".equalsIgnoreCase(code)) {
			// ��ȷ
			// 3.����û����Ƿ����
			if (userService.existsUsername(username)) {
				// ������
				request.setAttribute("msg", "�û����Ѵ���");
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				System.out.println("�û���[" + username + "]�Ѵ���");
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
				
			} else {
				// ����
				// ����userService���浽���ݿ���
				userService.registUser(new User(username, password, email));
				// ��ת����¼�ɹ�ҳ�� register_success
				request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
			}
		} else {// ����ȷ
			//������Ϣ�����浽Request����
			request.setAttribute("msg", "��֤�����");
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			
			System.out.println("��֤��[" + code + "]����");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			;
		}

	}

}
