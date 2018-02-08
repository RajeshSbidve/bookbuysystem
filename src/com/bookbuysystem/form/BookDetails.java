	package com.bookbuysystem.form;
	
	import java.io.Serializable;
	
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.OneToOne;
	import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
	
	
	@SuppressWarnings("serial")
	@Entity
	@Table(name="BOOK_DETAILS")
	public class BookDetails implements Serializable 
	{
	@Id
	@Column(name="ID")
	@GeneratedValue(generator = "system-increment") 
	@GenericGenerator(name="system-increment",strategy="increment")
	
	private Integer id;
	@Column(name="BOOKID")
	@NotNull(message="BookID Not to be Empty")
	private String bookId;
	@Column(name="BOOKNAME")
	private String bookName;
	@Column(name="PRICE")
	private String price;
	@Column(name="COMMENTS")
	private String comment;
	@Column(name="NOOFCOPIES")
	private Integer noOfCopies;
	
	@OneToOne
	@JoinColumn(name="BOOK_TYPE")
	private Book bookType;
	
	
	public BookDetails()
	{
		System.out.println("Inside BookDetails Cons()");
	}
	
	
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getBookId()
	{
		return bookId;
	}
	public void setBookId(String bookId) 
	{
		this.bookId = bookId;
	}
	public String getBookName()
	{
		return bookName;
	}
	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}
	public String getPrice() 
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
	public Book getBookType() 
	{
		return bookType;
	}
	public void setBookType(Book bookType)
	{
		this.bookType = bookType;
	}
	public Integer getNoOfCopies()
	{
	
		return noOfCopies;
	}
	public void setNoOfCopies(Integer noOfCopies)
	{
		this.noOfCopies = noOfCopies;
	}
	
	@Override
	public String toString()
	{
		return "BookDetails[id= "+id+"bookId="+bookId+"bookName="+bookName+"price="+price+"comment="+comment+"noOFCopies="+noOfCopies+"BookType="+bookType+"]";
	}
	
	}
