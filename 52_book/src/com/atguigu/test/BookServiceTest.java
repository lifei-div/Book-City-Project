package com.atguigu.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.pojo.Book;
import com.atguigu.serviceImp.BookService;
import com.atguigu.serviceImp.BookServiceImp;

public class BookServiceTest {

	private BookService bookService = new BookServiceImp();
	@Test
	public void testAddBook() {
		bookService.addBook(new Book(null, "小李飞刀", "李飞", new BigDecimal(100), 100, 99, null));
	}

	@Test
	public void testDeleteBookById() {
		bookService.deleteBookById(22);
	}

	@Test
	public void testUpdateBook() {
		bookService.updateBook(new Book(22, "小李飞刀,剑下亡魂", "李飞", new BigDecimal(100), 100, 99, null));
	}

	@Test
	public void testQueryBookById() {
		System.out.println(bookService.queryBookById(22));
	}

	@Test
	public void testQueryBooks() {
		
		for (Book book: bookService.queryBooks()) {
			System.out.println(book);
		}
	}
 
	@Test
    public void page() {
    	System.out.println(bookService.page(1, 4));
    }
	
	@Test
    public void pageByPrice() {
    	System.out.println(bookService.pageByPrice(1, 4,10,50));
    }
}
