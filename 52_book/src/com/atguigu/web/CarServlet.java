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
	 * �޸���Ʒ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ����Ĳ��� ��Ʒ��ţ���Ʒ����
		int id= WebUtils.parseInt(request.getParameter("id"), 0);
		int count= WebUtils.parseInt(request.getParameter("count"), 1);
		//��ȡCart���ﳵ����
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if (cart != null) {
			cart.updateCount(id, count);
			//�ض���ع��ﳵԭ��ҳ��
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
	
	protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���ﳵ����
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.clear();
			//�ض���ع��ﳵԭ��ҳ��
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
	
	/**
	 * ɾ����Ʒ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		//��ȡ���ﳵ����
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			//ɾ�����ﳵ��Ʒ��
			cart.deleteItem(id);
			//�ض���ع��ﳵԭ��ҳ��
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
   
	protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().write("���빺�ﳵ");
//		System.out.println("��Ʒ���"+ request.getParameter("id"));
	
	    //��ȡ����Ĳ��� ��Ʒ���
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		//����bookService.queryBookById(id):Book�õ�ͼ�����Ϣ
		Book book = bookService.queryBookById(id);
		//��ͼ����Ϣ��ת����CartItem��Ʒ��
		CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
		//����Cart.addItem();�����Ʒ��
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		
		request.getSession().setAttribute("lastName", cartItem.getName());
//		System.out.println(cart);
//		System.out.println("����ͷReferer��ֵ" + request.getHeader("Referer"));
		//�ض����ԭ�����ڵĵ�ַҳ��
		response.sendRedirect(request.getHeader("Referer"));
	
	
	}
	
	
	protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().write("���빺�ﳵ");
//		System.out.println("��Ʒ���"+ request.getParameter("id"));
	
	    //��ȡ����Ĳ��� ��Ʒ���
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		//����bookService.queryBookById(id):Book�õ�ͼ�����Ϣ
		Book book = bookService.queryBookById(id);
		//��ͼ����Ϣ��ת����CartItem��Ʒ��
		CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
		//����Cart.addItem();�����Ʒ��
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		//���һ����Ʒ�������
		request.getSession().setAttribute("lastName", cartItem.getName());

		//6.���ع��ﳵ�ܵ���Ʒ���������һ����ӵ���Ʒ��
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("totalCount", cart.getTotalCount());
		resultMap.put("lastName", cartItem.getName());
		
		Gson gson = new Gson();
		String resutlMapJsonString = gson.toJson(resultMap);
		response.getWriter().write(resutlMapJsonString);
	
	
	}
	
	

}
