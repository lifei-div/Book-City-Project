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
		//1.获取请求的参数==封装成Book对象(前端页面的name="值"，要和Bean层的属性值一样)
		Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
		//2.调用BookService.addBook()保存图片
		bookService.addBook(book);
		//3.跳转到图书列表页面 /manager/bookServlet?action=list
		//request.getRequestDispatcher("/manager/BookServlet?action=list").forward(request, response);
	      //请求转发 /:表示到工程名    重定向/:表示到端口号  所有得加上工程名
		response.sendRedirect(request.getContextPath()+"/manager/BookServlet?action=page&pageNo="+ pageNo);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求的参数id，图书编程
		int id = WebUtils.parseInt(request.getParameter("id"),0);
		//2.调用bookService.delereBookById(),删除数据
		bookService.deleteBookById(id);
		//3.重定向回图书列表管理页面  /book/manager/bookServlet?action=list
		response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=page");
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求的参数==封装成Book对象
		Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
		//2.调用BookService.updateBook(book);修改图书
		bookService.updateBook(book);
		//3.重定向图书页面  /工程名/manager/BookServler?action=list
		response.sendRedirect(request.getContextPath() + "/manager/BookServlet?action=page&pageNo="+request.getParameter("pageNo"));

	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.通过BookService查询全部图书
		List<Book> books = bookService.queryBooks();
		//2.把全部图书保存到Request域中
		request.setAttribute("books", books);
		//3.把Request域中数据请求转发到/pages/manager/book_manager.jsp页面
	    request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求的参数图书编号
    	int id = WebUtils.parseInt(request.getParameter("id"), 0);
    	//2.调用bookService.queryBookByid查询图书
    	Book book = bookService.queryBookById(id);
    	//3.保存到图书到request域中
    	request.setAttribute("book", book);
    	//4.请求转发到 pages/manager/book_edit.jsp页面
    	request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
    
    /**
     * 处理分页功能
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求的参数 pageNo 和pageSize
    	int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
    	int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    	//2.调用BookSvice.page(PageNO,pageSize):Page对象
        Page<Book> page =  bookService.page(pageNo,pageSize);
    	
        page.setUrl("manager/BookServlet?action=page");
        
        //3.保存Page对象到Request域中
        request.setAttribute("page", page);
    	//4.请求转发到pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

}


