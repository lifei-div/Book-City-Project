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
	 * Ajax异步验证用户名
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户名
		String username = request.getParameter("username");
		//调用userService.existsUsername()
		boolean existsUsername = userService.existsUsername(username);
		//把返回的结果封装成为Map对象
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("existsUsername", existsUsername);
		//转换成json
		Gson gson = new Gson();
		String usernameJsonString = gson.toJson(resultMap);
		
		response.getWriter().write(usernameJsonString);
	}

	/**
	 * 处理登录功能
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// 1.获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2.调用userService.login()登录处理业务
		User loginUser = userService.login(new User(username, password, null));
		// 如果null登录失败
		if (loginUser == null) {
			// 把错误的信息，和回显的表单信息，保存到Request域中
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("username", username);
			// 失败，跳回登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		} else {
			//保存用户登录之后的信息
			request.getSession().setAttribute("user", loginUser);
			// 登录 成功 。跳转到login_success.jsp页面
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);

		}
	}

	/**
	 * 处理注册功能
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取Session中的验证码
		String token = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 删除Session中的验证码
		request.getSession().removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		// 1 、 获 取 请 求 的 参 数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String code = request.getParameter("code");

		
		
		 //request.getParameterMap() 获取前端表格中全部参数
		 User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
		
		// 2 、 检 查 验 证 码 是 否 正 确===写 死 , 要 求 验 证 码 为 :abcde
		if (token != null && token.equalsIgnoreCase(code)) {
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
			// 回显信息，保存到Request域中
			request.setAttribute("msg", "验证码错误");
			request.setAttribute("username", username);
			request.setAttribute("email", email);

			System.out.println("验证码[" + code + "]错误");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.销毁session中的用户登录信息（或者销毁session）
		request.getSession().invalidate();
		//2.重定向到首页（或者登录页面）
		response.sendRedirect(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	

}
