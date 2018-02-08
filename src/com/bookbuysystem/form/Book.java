package com.bookbuysystem.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BOOK_TYPE")
public class Book implements Serializable
{

	
	public Book()
	{
		System.out.println("Inside Book Type cons()");
	}
	
	public Book(Integer id, String bookType)
	{
		this.id=id;
		this.bookType=bookType;
	}
	
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="BOOKRTYPE")
	private String bookType;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString()
	{
		return "Book [id="+id+ " BookType="+bookType+"Description="+description+"]";
	}
	
}
