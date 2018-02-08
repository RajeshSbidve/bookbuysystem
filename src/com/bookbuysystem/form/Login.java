package com.bookbuysystem.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Login implements Serializable
{
	
	
	public Login() 
	{
	System.out.println("Inside login()");	
	}
	
	public Login(String email,String password)
	{
		this.email=email;
		this.password=password;
	}
	
	@NotEmpty(message="Email should be in right formate")
	@Email
	private String email;
	@NotNull
	@Size(min=6,max=15,message="Password Size should be Greater then 6...!!! ")
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString()
	{
		return "Login [email="+email+"password="+password+"]";
	}


}
