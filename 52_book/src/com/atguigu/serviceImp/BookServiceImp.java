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
	 * ��ҳ
	 */
	@Override
	public Page<Book> page(int pageNo, int pageSize) {
		Page<Book> page = new Page<Book>();

	
		// ����ÿҳ��ʾ������
		page.setPageSize(pageSize);
		// ���ܼ�¼��
		Integer pageTotalCount = bookDao.queryForPageTotalCount();
		// �����ܼ�¼��
		page.setPageTotalCount(pageTotalCount);
		// ����ҳ��
		Integer pageTotal = pageTotalCount / pageSize;
		if (pageTotalCount % pageSize > 0) {
			pageTotal++;
		}

		

		// ���õ�ǰҳ��
		page.setPageNo(pageNo);
		// ������ҳ��
		page.setPageTotal(pageTotal);
		// ��ǰҳ����
		int begin = (page.getPageNo() - 1) * pageSize;// ��ǰҳ����ʼ����
		List<Book> items = bookDao.queryForPageItems(begin, pageSize);
		// ���õ�ǰҳ����
		page.setItems(items);
		return page;
	}

	/**
	 * ��ȡ�۸����
	 */
	@Override
	public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
		Page<Book> page = new Page<Book>();

		
		// ����ÿҳ��ʾ������
		page.setPageSize(pageSize);
		// ���ܼ�¼��
		Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
		// �����ܼ�¼��
		page.setPageTotalCount(pageTotalCount);
		// ����ҳ��
		Integer pageTotal = pageTotalCount / pageSize;
		if (pageTotalCount % pageSize > 0) {
			pageTotal++;
		}

		

		// ���õ�ǰҳ��
		page.setPageNo(pageNo);
		// ������ҳ��
		page.setPageTotal(pageTotal);
		// ��ǰҳ����
		int begin = (page.getPageNo() - 1) * pageSize;// ��ǰҳ����ʼ����
		List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
		// ���õ�ǰҳ����
		page.setItems(items);
		return page;
	}

}
