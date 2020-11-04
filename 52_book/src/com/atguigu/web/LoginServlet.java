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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserService userService = new UserServiceImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ�������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.����userService.login()��¼����ҵ��
		User loginUser = userService.login(new User(username, password, null));
		//���null��¼ʧ��
		if (loginUser == null) {
			//�Ѵ������Ϣ���ͻ��Եı���Ϣ�����浽Request����
			request.setAttribute("msg", "�û������������");
			request.setAttribute("username", username);
			//ʧ�ܣ����ص�¼ҳ��
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//��¼ �ɹ� ����ת��login_success.jspҳ��
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
			
		}
	}

}
