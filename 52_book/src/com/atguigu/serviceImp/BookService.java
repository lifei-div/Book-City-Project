package com.atguigu.serviceImp;

import java.util.List;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

public interface BookService {

	public void addBook(Book book);
	
	public void deleteBookById(Integer id);
	
	public void updateBook(Book book);
	
	public Book queryBookById(Integer id);
	
	public List<Book> queryBooks();

	public Page<Book> page(int pageNo, int pageSize);

	public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
