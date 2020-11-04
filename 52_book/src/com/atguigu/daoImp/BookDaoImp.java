package com.atguigu.daoImp;

import java.util.List;

import com.atguigu.pojo.Book;

public class BookDaoImp extends BaseDao implements BookDao{

	@Override
	public int addBook(Book book) {
		String sql = "insert into t_book(name,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
		
		return update(sql, book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPaht());
	}

	@Override
	public int deleteBookById(Integer id) {
		String sql = "delete from t_book where id = ?";
		return update(sql, id);
	}

	@Override
	public int updateBook(Book book) {
	    String sql = "update t_book set name = ?,author=?,price=?,sales=?,stock=?,img_path=? where id = ?";
	    
		return update(sql, book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPaht(),book.getId());
	}

	@Override
	public Book queryBookById(Integer id) {
		String sql = "select * from t_book where id = ?";
		return queryForOne(Book.class, sql, id);
	}

	@Override
	public List<Book> queryBooks() {
		String sql = "select * from t_book ";
		return queryForList(Book.class, sql);
	}

	/**
	 * 求总的page行数
	 */
	@Override
	public Integer queryForPageTotalCount() {
	   	String sql = "select count(*) from t_book";
	    Number count = (Number)queryForSingValue(sql);
	    return count.intValue();
	}

	/**
	 * 查询当前页数据
	 */
 	@Override
	public List<Book> queryForPageItems(int begin, int pageSize) {
		String sql = "select * from t_book limit ?,?";
		return queryForList(Book.class, sql, begin,pageSize);
	}

 	/**
 	 * 价格区间内的总行数
 	 */
	@Override
	public Integer queryForPageTotalCountByPrice(int min, int max) {
	  	String sql = "select count(*) from t_book where price between  ? and ?";
	    Number count = (Number)queryForSingValue(sql,min,max);
	    return count.intValue();
	}

	/**
	 * 查询当前价格内当前页数据
	 */
	@Override
	public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
		String sql = "select * from t_book where price between  ? and ? order by price  limit ?,?";
		return queryForList(Book.class, sql, min,max,begin,pageSize);
	}

}
