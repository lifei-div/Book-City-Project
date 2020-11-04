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
		// 1 、 获 取 请 求 的 参 数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String code = request.getParameter("code");

		// 2 、 检 查 验 证 码 是 否 正 确===写 死 , 要 求 验 证 码 为 :abcde
		if ("abcde".equalsIgnoreCase(code)) {
			// 正确
			// 3.检查用户名是否可用
			if (userService.existsUsername(username)) {
				// 不可用
				request.setAttribute("msg", "用户名已存在");
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				System.out.println("用户名[" + username + "]已存在");
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
				
			} else {
				// 可用
				// 调用userService保存到数据库中
				userService.registUser(new User(username, password, email));
				// 跳转到登录成功页面 register_success
				request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
			}
		} else {// 不正确
			//回显信息，保存到Request域中
			request.setAttribute("msg", "验证码错误");
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			
			System.out.println("验证码[" + code + "]错误");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			;
		}

	}

}
