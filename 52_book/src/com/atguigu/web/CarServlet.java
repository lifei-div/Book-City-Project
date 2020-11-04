package com.atguigu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.serviceImp.BookService;
import com.atguigu.serviceImp.BookServiceImp;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/CarServlet")
public class CarServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService = new BookServiceImp();
	
	/**
	 * 修改商品数量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的参数 商品编号，商品数量
		int id= WebUtils.parseInt(request.getParameter("id"), 0);
		int count= WebUtils.parseInt(request.getParameter("count"), 1);
		//获取Cart购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if (cart != null) {
			cart.updateCount(id, count);
			//重定向回购物车原来页面
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
	
	protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.clear();
			//重定向回购物车原来页面
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
	
	/**
	 * 删除商品项
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		//获取购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			//删除购物车商品项
			cart.deleteItem(id);
			//重定向回购物车原来页面
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
   
	protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().write("加入购物车");
//		System.out.println("商品编号"+ request.getParameter("id"));
	
	    //获取请求的参数 商品编号
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		//调用bookService.queryBookById(id):Book得到图书的信息
		Book book = bookService.queryBookById(id);
		//把图书信息，转换长CartItem商品项
		CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
		//调用Cart.addItem();添加商品项
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		
		request.getSession().setAttribute("lastName", cartItem.getName());
//		System.out.println(cart);
//		System.out.println("请求头Referer的值" + request.getHeader("Referer"));
		//重定向回原来所在的地址页面
		response.sendRedirect(request.getHeader("Referer"));
	
	
	}
	
	
	protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().write("加入购物车");
//		System.out.println("商品编号"+ request.getParameter("id"));
	
	    //获取请求的参数 商品编号
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		//调用bookService.queryBookById(id):Book得到图书的信息
		Book book = bookService.queryBookById(id);
		//把图书信息，转换长CartItem商品项
		CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
		//调用Cart.addItem();添加商品项
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		//最后一个商品添加名称
		request.getSession().setAttribute("lastName", cartItem.getName());

		//6.返回购物车总的商品数量和最后一个添加的商品项
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("totalCount", cart.getTotalCount());
		resultMap.put("lastName", cartItem.getName());
		
		Gson gson = new Gson();
		String resutlMapJsonString = gson.toJson(resultMap);
		response.getWriter().write(resutlMapJsonString);
	
	
	}
	
	

}
