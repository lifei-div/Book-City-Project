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
		//1.获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.调用userService.login()登录处理业务
		User loginUser = userService.login(new User(username, password, null));
		//如果null登录失败
		if (loginUser == null) {
			//把错误的信息，和回显的表单信息，保存到Request域中
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("username", username);
			//失败，跳回登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			//登录 成功 。跳转到login_success.jsp页面
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
			
		}
	}

}
