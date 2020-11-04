package com.atguigu.web;

import java.io.IOException;

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
 * Servlet implementation class ClientBookServlet
 */
@WebServlet("/Client/BookServlet")
public class ClientBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookServiceImp();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientBookServlet() {
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
     * 处理分页功能
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	System.out.println("经过前台的ClientBookServlet ");
    	//1.获取请求的参数 pageNo 和pageSize
    	int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
    	int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    	//2.调用BookSvice.page(PageNO,pageSize):Page对象
        Page<Book> page =  bookService.page(pageNo,pageSize);
        
        //设置地址
        page.setUrl("Client/BookServlet?action=page");

        
    	//3.保存Page对象到Request域中
        request.setAttribute("page", page);
    	//4.请求转发到pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
	}
    
    
    
    /**
     * 处理分页功能,价格分区
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	System.out.println("经过前台的ClientBookServlet ");
    	//1.获取请求的参数 pageNo 和pageSize
    	int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
    	int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    	int min = WebUtils.parseInt(request.getParameter("min"),0);
    	int max = WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);
    	//2.调用BookSvice.page(PageNO,pageSize):Page对象
        Page<Book> page =  bookService.pageByPrice(pageNo,pageSize,min,max);
        
        StringBuilder sb = new StringBuilder("Client/BookServlet?action=pageByPrice");
       //如果有最小价格的参数，追加 到分页条参数中
        if (request.getParameter("min") != null) {
			sb.append("&min=").append(request.getParameter("min"));
		}
        
        //如果有最大价格的参数，追加 到分页条参数中
        if (request.getParameter("max") != null) {
			sb.append("&max=").append(request.getParameter("max"));
		}
        
        
        //设置地址
        page.setUrl(sb.toString());

        
    	//3.保存Page对象到Request域中
        request.setAttribute("page", page);
    	//4.请求转发到pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
	}

}
