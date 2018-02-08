package com.bookbuysystem.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="REGISTER")
public class Register implements Serializable
{
	
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	@NotEmpty(message="Email Should be in right formate")
	@Email()
	@Column(name="EMAIL")
	private String email;
	@NotEmpty(message="Your password must between 6 and 15 characters")
	@Size(min=6,max=15)
	@Column(name="PASSWORD")
	private String pass;
	@NotEmpty(message="Your password must be same not different")
	@Size(min=6,max=15)
	@Column(name="RPASS")
	private String rpass;
	
	
	public Register() 
	{
		System.out.println("Inside Register Cons()");
	}
	public Register(Integer id, String email,String pass,String rpass)
	{
		this.id=id;
		this.email=email;
		this.pass=pass;
		this.rpass=rpass;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRpass() 
	{
		return rpass;
	}
	public void setRpass(String rpass) 
	{
		this.rpass = rpass;
	}
	
	public String toString()
	{
		return "Register [id="+id+"email="+email+"pass="+pass+"rpass="+rpass+"]";
	}

}
