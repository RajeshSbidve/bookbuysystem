package com.bookbuysystem.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.CreateKeySecondPass;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookbuysystem.form.AddBook;
import com.bookbuysystem.form.Book;
import com.bookbuysystem.form.BookDetails;
import com.bookbuysystem.form.Login;
import com.bookbuysystem.form.Register;

@Repository
public class BookDaoImpl implements BookDao
{

	public BookDaoImpl()
	{
		System.out.println("Inside BookDao cons()");
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveDetails(AddBook details) 
	{
		for(BookDetails books:details.getBookdetails())
		{
			if((books.getBookId()!="")&&(books.getBookName()!="")&&(books.getBookType()!=null))
			{
			sessionFactory.getCurrentSession().save(books);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void register(Register register)
	{
		List<Register> list=sessionFactory.getCurrentSession().createQuery("from Register").list();
		for(Register reg:list)
		{
			if(!(reg.getEmail().contains(register.getEmail())))
			{
		
			sessionFactory.getCurrentSession().save(register);	
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	public Register getRegister(Login log)
	{
		String hql="from Register where email=:emailr and pass=:passs";
		Session session =sessionFactory.openSession();
		Query query=session.createQuery(hql);
		query.setParameter("emailr", log.getEmail());
		query.setParameter("passs", log.getPassword());
		
		Register register=(Register)query.uniqueResult();
		return register;
		
	}
		

	
	@SuppressWarnings("unchecked")
	public List<Register> listRegister()
	{
		List<Register> list=sessionFactory.getCurrentSession().createQuery("from Register").list();
		for(Register reg:list)
			System.out.println(reg.toString());
		
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BookDetails> listBooks()
	{
		
		List<BookDetails> list = sessionFactory.getCurrentSession().createQuery("from BookDetails").list();
		for(BookDetails book:list)
		System.out.println(book.toString());
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> BookTypes() 
	{
		
		List<Book> list = sessionFactory.getCurrentSession().createQuery("from Book").list();
		for(Book book:list)
		System.out.println(book.toString());
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public Integer addOneBook(Integer bookId)
	{
		
		BookDetails book= new BookDetails();
		book = (BookDetails)sessionFactory.getCurrentSession().get(BookDetails.class, bookId);
		Integer copies=book.getNoOfCopies();
		book.setNoOfCopies(copies-1);
		sessionFactory.getCurrentSession().update(book);
		return book.getNoOfCopies();
	}
	
	@SuppressWarnings("unchecked")
	public Integer saveOneBook(Integer bookId)
	{
		
		BookDetails book= new BookDetails();
		//book.setId(bookId);
		book = (BookDetails)sessionFactory.getCurrentSession().get(BookDetails.class, bookId);
		Integer copies=book.getNoOfCopies();
		book.setNoOfCopies(copies+1);
		sessionFactory.getCurrentSession().update(book);
		return copies+1;	
	}
	
	@SuppressWarnings("unchecked")
	public List<BookDetails>  serach(BookDetails bookDetails) 
		{
	
		 Integer Booktypes=bookDetails.getBookType().getId();
		 String BookId=bookDetails.getBookId();
		 String name=bookDetails.getBookName();
		 
		 Criteria criteria= sessionFactory.getCurrentSession().createCriteria(BookDetails.class);
		 Criterion criteria1=Restrictions.eq("bookType.id", bookDetails.getBookType().getId());
		 Criterion criteria2=Restrictions.eq("bookId", BookId);
		 Criterion criteria3=Restrictions.eq("bookName", name);
		
		 LogicalExpression logicEx1=null;
		 
		 
		 
		 if(Booktypes !=null && BookId !="" && name !="")
		 	{ 
			
			 logicEx1=Restrictions.and(criteria1, criteria2);
			 List<BookDetails> l=criteria.add(Restrictions.and(logicEx1, criteria3)).list();
			 
			 System.out.println(l.toString());
			 return l;
		 	}
		 
		 if(Booktypes !=null && BookId !="" && name =="")
		  	{
			 return criteria.add(Restrictions.and(criteria1, criteria2)).list();
		  	}
		 
		 if(Booktypes !=null && name != "" && BookId =="")
		 	{
			 return criteria.add(Restrictions.and(criteria1, criteria3)).list();
		 	}
		 
		 if(BookId !="" && name !="" && Booktypes ==null)
		 	{
			 return criteria.add(Restrictions.and(criteria2, criteria3)).list();
		 	}
		 
		 if(Booktypes !=null && BookId =="" && name =="" )
		 	{
			 return criteria.add(Restrictions.eq("bookType.id", Booktypes)).list();
		 	}
		 
		 if(BookId !=null && name =="" && Booktypes ==null )
		 	{ 
			 return criteria.add(Restrictions.eq("bookId", BookId)).list();
		 	}
		 
		 if(name != null && Booktypes ==null && BookId =="" )
		  	{ 
			 return criteria.add(Restrictions.eq("bookName", name)).list();
		  	}
		
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	public void removeBook(Integer id) {
		
		BookDetails book = (BookDetails)sessionFactory.getCurrentSession().load(BookDetails.class, id);
		
		if(null != book) {
			sessionFactory.getCurrentSession().delete(book);
			sessionFactory.getCurrentSession().flush();
		}
	}

}