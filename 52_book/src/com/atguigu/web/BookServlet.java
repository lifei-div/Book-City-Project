package com.atguigu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.serviceImp.BookService;
import com.atguigu.serviceImp.BookServiceImp;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/manager/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	private BookService bookService = new BookServiceImp();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
		pageNo+=1;
		//1.��ȡ����Ĳ���==��װ��Book����(ǰ��ҳ���name="ֵ"��Ҫ��Bean�������ֵһ��)
		Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
		//2.����BookService.addBook()����ͼƬ
		bookService.addBook(book);
		//3.��ת��ͼ���б�ҳ�� /manager/bookServlet?action=list
		//request.getRequestDispatcher("/manager/BookServlet?action=list").forward(request, response);
	      //����ת�� /:��ʾ��������    �ض���/:��ʾ���˿ں�  ���еü��Ϲ�����
		response.sendRedirect(request.getContextPath()+"/manager/BookServlet?action=page&pageNo="+ pageNo);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����Ĳ���id��ͼ����
		int id = WebUtils.parseInt(request.getParameter("id"),0);
		//2.����bookService.delereBookById(),ɾ������
		bookService.deleteBookById(id);
		//3.�ض����ͼ���б����ҳ��  /book/manager/bookServlet?action=list
		response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=page");
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����Ĳ���==��װ��Book����
		Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
		//2.����BookService.updateBook(book);�޸�ͼ��
		bookService.updateBook(book);
		//3.�ض���ͼ��ҳ��  /������/manager/BookServler?action=list
		response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=page&pageNo="+request.getParameter("pageNo"));

	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.ͨ��BookService��ѯȫ��ͼ��
		List<Book> books = bookService.queryBooks();
		//2.��ȫ��ͼ�鱣�浽Request����
		request.setAttribute("books", books);
		//3.��Request������������ת����/pages/manager/book_manager.jspҳ��
	    request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����Ĳ���ͼ����
    	int id = WebUtils.parseInt(request.getParameter("id"), 0);
    	//2.����bookService.queryBookByid��ѯͼ��
    	Book book = bookService.queryBookById(id);
    	//3.���浽ͼ�鵽request����
    	request.setAttribute("book", book);
    	//4.����ת���� pages/manager/book_edit.jspҳ��
    	request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
    
    /**
     * �����ҳ����
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ����Ĳ��� pageNo ��pageSize
    	int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
    	int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    	//2.����BookSvice.page(PageNO,pageSize):Page����
        Page<Book> page =  bookService.page(pageNo,pageSize);
    	
        page.setUrl("manager/BookServlet?action=page");
        
        //3.����Page����Request����
        request.setAttribute("page", page);
    	//4.����ת����pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

}


