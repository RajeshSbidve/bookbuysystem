package com.bookbuysystem.service;

import java.util.List;

import com.bookbuysystem.form.AddBook;
import com.bookbuysystem.form.Book;
import com.bookbuysystem.form.BookDetails;
import com.bookbuysystem.form.Login;
import com.bookbuysystem.form.Register;

public interface BookService 
{

	
	
	public List<BookDetails> listBooks();

	public List<Book> BookTypes();
	
	public Integer addOneBook(Integer bookId);
	
	public Integer saveOneBook(Integer bookId);
	
	public List<BookDetails>  serach(BookDetails bookDetails);
	
	public void removeBook(Integer id);
	public void saveDetails(AddBook details) ;
	public void register(Register register);
	public List<Register> listRegister();

	public Register getRegister(Login log);
}
