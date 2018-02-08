	package com.bookbuysystem.service;
	
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
	
import com.bookbuysystem.form.AddBook;
	import com.bookbuysystem.form.Book;
import com.bookbuysystem.form.BookDetails;
import com.bookbuysystem.form.Login;
import com.bookbuysystem.form.Register;
import com.bookbuysystem.dao.BookDao;
	
	@Service
	public class BookServiceImpl implements BookService
	{
	
	public BookServiceImpl()
	{
		System.out.println("Inside BookServiceimpl cons()");
	}
	
	
	@Autowired
	private BookDao bookDao;
	
	@Transactional
	public void saveDetails(AddBook details)
	{
		bookDao.saveDetails(details);
	}	
	
	@Transactional
	public List<BookDetails> listBooks()
	{
		System.out.println("inside list books in BookServiceImpl");
		return bookDao.listBooks();
	}
	
	@Transactional
	public List<Book> BookTypes() 
	{
		return bookDao.BookTypes();
	}
	
	
	@Transactional
	public Integer addOneBook(Integer bookId)
	{
		return bookDao.addOneBook(bookId);
	}
	
	@Transactional
	public Integer saveOneBook(Integer bookId)
	{
		return bookDao.saveOneBook(bookId);
	}
	
	@Transactional
	public List<BookDetails>  serach(BookDetails bookDetails)
	{
		return bookDao.serach(bookDetails);
	}

	@Transactional
	public void removeBook(Integer id) {
		bookDao.removeBook(id);
	}
	
	@Transactional
	public void register(Register register)
	{
		bookDao.register(register);
		
	}
	@Transactional
	public Register getRegister(Login log) 
	{
	
		return bookDao.getRegister(log);
	}

	public List<Register> listRegister() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	}
