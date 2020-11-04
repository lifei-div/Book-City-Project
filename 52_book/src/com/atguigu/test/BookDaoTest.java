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
		bookDao.addBook(new Book(null,"我为什么这么优秀", "小李飞刀", new BigDecimal(99.99), 99, 999, null));
	}

	@Test
	public void testDeleteBook() {
		bookDao.deleteBookById(21);
	}

	@Test
	public void testUpdateBook() {
		bookDao.updateBook(new Book(21,"是什么这么优秀", "小李飞刀", new BigDecimal(199.99), 991, 999, null));
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
	 * 查询当前数据
	 */
	@Test
	public void queryForPageItems() {
		for (Book book : bookDao.queryForPageItems(8, 4)) {
			System.out.println(book);
		}
	}
	
	/**
	 * 查询当前价格区间页总数据
	 */
	@Test
	public void queryForPageTotalCountByPrice() {
		System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
	}
	/**
	 * 查询当前价格区间当前页数据
	 */
	@Test
	public void queryForPageItemsByPrice() {
		for (Book book : bookDao.queryForPageItemsByPrice(1, 4,10,50)) {
			System.out.println(book);
		}
	}
	
}
