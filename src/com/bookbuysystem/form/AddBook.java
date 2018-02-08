package com.bookbuysystem.form;

import java.util.ArrayList;
import java.util.List;

public class AddBook 
{

	private List<BookDetails> bookdetails = new ArrayList<BookDetails>();
	
	public AddBook()
	{
		System.out.println("Inside AddBook");
	}

	public List<BookDetails> getBookdetails() {
		return bookdetails;
	}

	public void setBookdetails(List<BookDetails> bookdetails) {
		this.bookdetails = bookdetails;
	}

	


	
}
