package com.atguigu.test;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.atguigu.daoImp.BookDao;
import com.atguigu.daoImp.BookDaoImp;
import com.atguigu.pojo.Book;
import com.atguigu.serviceImp.BookService;

public class BookDaoTest {

	private BookDao bookDao = new BookDaoImp();
	@Test
	public void testAddBook() {
		bookDao.addBook(new Book(null,"��Ϊʲô��ô����", "С��ɵ�", new BigDecimal(99.99), 99, 999, null));
	}

	@Test
	public void testDeleteBook() {
		bookDao.deleteBookById(21);
	}

	@Test
	public void testUpdateBook() {
		bookDao.updateBook(new Book(21,"��ʲô��ô����", "С��ɵ�", new BigDecimal(199.99), 991, 999, null));
	}

	@Test
	public void testQueryBookById() {
		System.out.println(bookDao.queryBookById(21));
	}

	@Test
	public void testQueryBooks() {
		for (Book book : bookDao.queryBooks()) {
			System.out.println(book);
		}
	}

	
	@Test
	public void queryForPageTotalCount() {
		System.out.println(bookDao.queryForPageTotalCount());
	}
	/**
	 * ��ѯ��ǰ����
	 */
	@Test
	public void queryForPageItems() {
		for (Book book : bookDao.queryForPageItems(8, 4)) {
			System.out.println(book);
		}
	}
	
	/**
	 * ��ѯ��ǰ�۸�����ҳ������
	 */
	@Test
	public void queryForPageTotalCountByPrice() {
		System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
	}
	/**
	 * ��ѯ��ǰ�۸����䵱ǰҳ����
	 */
	@Test
	public void queryForPageItemsByPrice() {
		for (Book book : bookDao.queryForPageItemsByPrice(1, 4,10,50)) {
			System.out.println(book);
		}
	}
	
}
