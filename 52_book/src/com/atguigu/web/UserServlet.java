package com.atguigu.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.pojo.User;
import com.atguigu.serviceImp.UserService;
import com.atguigu.serviceImp.UserServiceImp;
import com.atguigu.test.UserServletTest;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImp();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Ajax�첽��֤�û���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ�û���
		String username = request.getParameter("username");
		//����userService.existsUsername()
		boolean existsUsername = userService.existsUsername(username);
		//�ѷ��صĽ����װ��ΪMap����
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("existsUsername", existsUsername);
		//ת����json
		Gson gson = new Gson();
		String usernameJsonString = gson.toJson(resultMap);
		
		response.getWriter().write(usernameJsonString);
	}

	/**
	 * �����¼����
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// 1.��ȡ�������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2.����userService.login()��¼����ҵ��
		User loginUser = userService.login(new User(username, password, null));
		// ���null��¼ʧ��
		if (loginUser == null) {
			// �Ѵ������Ϣ���ͻ��Եı���Ϣ�����浽Request����
			request.setAttribute("msg", "�û������������");
			request.setAttribute("username", username);
			// ʧ�ܣ����ص�¼ҳ��
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		} else {
			//�����û���¼֮�����Ϣ
			request.getSession().setAttribute("user", loginUser);
			// ��¼ �ɹ� ����ת��login_success.jspҳ��
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);

		}
	}

	/**
	 * ����ע�Ṧ��
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡSession�е���֤��
		String token = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// ɾ��Session�е���֤��
		request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		// 1 �� �� ȡ �� �� �� �� ��
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String code = request.getParameter("code");

		
		
		 //request.getParameterMap() ��ȡǰ�˱����ȫ������
		 User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
		
		// 2 �� �� �� �� ֤ �� �� �� �� ȷ===д �� , Ҫ �� �� ֤ �� Ϊ :abcde
		if (token != null && token.equalsIgnoreCase(code)) {
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
			// ������Ϣ�����浽Request����
			request.setAttribute("msg", "��֤�����");
			request.setAttribute("username", username);
			request.setAttribute("email", email);

			System.out.println("��֤��[" + code + "]����");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.����session�е��û���¼��Ϣ����������session��
		request.getSession().invalidate();
		//2.�ض�����ҳ�����ߵ�¼ҳ�棩
		response.sendRedirect(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	

}
