package com.atguigu.serviceImp;

import java.util.List;

import com.atguigu.daoImp.BookDao;
import com.atguigu.daoImp.BookDaoImp;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

public class BookServiceImp implements BookService {

	private BookDao bookDao = new BookDaoImp();

	@Override
	public void addBook(Book book) {
		bookDao.addBook(book);

	}

	@Override
	public void deleteBookById(Integer id) {
		bookDao.deleteBookById(id);

	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);

	}

	@Override
	public Book queryBookById(Integer id) {
		// TODO Auto-generated method stub
		return bookDao.queryBookById(id);
	}

	@Override
	public List<Book> queryBooks() {
		// TODO Auto-generated method stub
		return bookDao.queryBooks();
	}

	/**
	 * 分页
	 */
	@Override
	public Page<Book> page(int pageNo, int pageSize) {
		Page<Book> page = new Page<Book>();

	
		// 设置每页显示的数据
		page.setPageSize(pageSize);
		// 求总记录数
		Integer pageTotalCount = bookDao.queryForPageTotalCount();
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 求总页码
		Integer pageTotal = pageTotalCount / pageSize;
		if (pageTotalCount % pageSize > 0) {
			pageTotal++;
		}

		

		// 设置当前页码
		page.setPageNo(pageNo);
		// 设置总页码
		page.setPageTotal(pageTotal);
		// 求当前页数据
		int begin = (page.getPageNo() - 1) * pageSize;// 当前页数开始索引
		List<Book> items = bookDao.queryForPageItems(begin, pageSize);
		// 设置当前页数据
		page.setItems(items);
		return page;
	}

	/**
	 * 获取价格分区
	 */
	@Override
	public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
		Page<Book> page = new Page<Book>();

		
		// 设置每页显示的数据
		page.setPageSize(pageSize);
		// 求总记录数
		Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 求总页码
		Integer pageTotal = pageTotalCount / pageSize;
		if (pageTotalCount % pageSize > 0) {
			pageTotal++;
		}

		

		// 设置当前页码
		page.setPageNo(pageNo);
		// 设置总页码
		page.setPageTotal(pageTotal);
		// 求当前页数据
		int begin = (page.getPageNo() - 1) * pageSize;// 当前页数开始索引
		List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
		// 设置当前页数据
		page.setItems(items);
		return page;
	}

}
