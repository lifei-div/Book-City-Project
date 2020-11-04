package com.atguigu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.serviceImp.OrderService;
import com.atguigu.serviceImp.OrderServlceImp;
import com.atguigu.utils.JdbcUtils;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     private OrderService orderService = new OrderServlceImp();
    /**
     * ��������
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡCart���ﳵ����
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//��ȡUserId
		User loginUser = (User) request.getSession().getAttribute("user");
	    
	    if (loginUser == null) {
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		    return;
	    }
	    Integer userId = loginUser.getId();
	   //����OrderService.create(Cart,UserId)���ɶ���
    	String orderId  = orderService.createOrder(cart, userId);
	    
    	//�Ѷ����ű���session����
	    request.getSession().setAttribute("orderId", orderId);
	    
	   // request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
	    //�����ض���
	    response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
	}

}
