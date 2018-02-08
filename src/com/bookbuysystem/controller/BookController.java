package com.bookbuysystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookbuysystem.form.AddBook;
import com.bookbuysystem.form.Book;
import com.bookbuysystem.form.BookDetails;
import com.bookbuysystem.form.Login;
import com.bookbuysystem.form.Register;
import com.bookbuysystem.service.BookService;
import com.google.gson.Gson;


@Controller
public class BookController 
{
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/home")
	public ModelAndView listBooks(@ModelAttribute("bookDetails") BookDetails bookDetails) 
		{
		
		ModelAndView modelAndView = new ModelAndView("book");
		List<Book> list = bookService.BookTypes();                    // All list of Book
		modelAndView.addObject("bookTypesList",list);
		modelAndView.addObject("bookList", bookService.listBooks());  // All list of BookDetails
		return modelAndView;
	
		}

	@RequestMapping("/update")
	public ModelAndView listBookss(@ModelAttribute("bookDetails") BookDetails bookDetails) 
		{
		
		ModelAndView modelAndView = new ModelAndView("update");
		List<Book> list = bookService.BookTypes();                    // All list of Book
		modelAndView.addObject("bookTypesList",list);
		modelAndView.addObject("bookList", bookService.listBooks());  // All list of BookDetails
		return modelAndView;
	
		}



	@RequestMapping("/bookdetails")
	public ModelAndView addBook(AddBook bookdetails) 
	{ 
		ModelAndView modelAndView1 = new ModelAndView("bookdetails");
		List<Book> books=bookService.BookTypes();  
		modelAndView1.addObject("books", books);
		modelAndView1.addObject("bookdetails", new AddBook());

		return modelAndView1;
	}
	
	
	
	@RequestMapping("/save")
	public String saveDetails(@ModelAttribute("bookdetails") AddBook bookdetails,BindingResult result)
	{

		
		bookService.saveDetails(bookdetails);
		return "redirect:/home";
	}
	
	@RequestMapping("/Register")
	public ModelAndView register1(Register register)
	{
		ModelAndView reg=new ModelAndView("Register");
		reg.addObject("register", new Register());
		return reg;
	}
	@RequestMapping("/register")
	public ModelAndView register(@Valid@ModelAttribute("register") Register reg, BindingResult result)
	{
		if(result.hasErrors())
		{
		return new ModelAndView("Register", "key", "Registeration Failed");	
		}

		if(reg!=null)
		{	
		 bookService.register(reg);
		}
		return new ModelAndView("Success","key","Registeration Succssfull");
	}
	
	@RequestMapping("/login")
	public String login(Map<String,Object> model)
	{
		Login log=new Login();
		model.put("loginForm", log);	
		return "Login";
		
	}
	
	@RequestMapping("/loginForm")
	public ModelAndView doLogin(@Valid@ModelAttribute("loginForm") Login log , BindingResult result, Register register, HttpServletRequest request )
	{
		
		
		
		Register dto2= bookService.getRegister(log);
		
		if(result.hasErrors())
		{
			return new ModelAndView("Login","key","<b>Error</b>");
		}
		
		
		if(dto2!=null)
			{
			HttpSession session =request.getSession(true);
			session.setAttribute("DTO2", dto2);
				return new ModelAndView("LoginSucces","key","Succesfully logined");
			
			}
					
		
		
		return new ModelAndView("Login","key","<b>U Dont remember your password..?? What else do in life..? </b>");

	}
	
	
	
	
	@RequestMapping("/Logout")
	public ModelAndView Logout(HttpServletRequest request)
	{
		HttpSession session=request.getSession(false);
		session.invalidate();
		return  new ModelAndView("Login","key","logged out succesfully");
	}

	
	

	@RequestMapping("/decreaseNoOfCopies")
	public void buyBook(HttpServletRequest request,HttpServletResponse response) throws IOException
		{
		Integer id=Integer.parseInt(request.getParameter("autoId"));
		Integer Nofcopies=bookService.addOneBook(id);
	
		response.getWriter().write(new Gson().toJson(Nofcopies));    // it will return the object
		}


	@RequestMapping("/increaseNoOfCopies")
	public void saveOneBook(HttpServletRequest request, HttpServletResponse response) throws IOException
			{
			Integer id=Integer.parseInt(request.getParameter("autoId"));
			Integer temp=Integer.parseInt(request.getParameter("temp"));
			Integer Nofcopies=bookService.saveOneBook(id);
		
			response.getWriter().write(new Gson().toJson(Nofcopies));    // it will return the object 
			}
		
		
	@RequestMapping("/search")
	public String searchTerms(@ModelAttribute("bookDetails") BookDetails bookDetails, Map<String, Object> map)
		{

		 map.put("bookTypesList",bookService.BookTypes());
		 List<BookDetails> listOfBook = bookService.serach(bookDetails);
		 map.put("bookList", listOfBook);	
	     return "book";
		}
	
	@RequestMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable("bookId") Integer bookId)
	{
		
		bookService.removeBook(bookId);
		return "redirect:/home";
	}
		

}