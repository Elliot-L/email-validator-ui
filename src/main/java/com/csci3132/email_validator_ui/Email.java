package com.csci3132.email_validator_ui;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
public class Email implements Serializable{
	@Id
	private String address;
	private String pass;
	
	public Email(){
		
	}
	
	public void setAddress(String s){
	address = s;
	}
	
	public String getAddress(){
		return address;
	}
	public String getPass(){
		
		return pass;
	}
	
	public Email(String address, String pass){
		
		this.address = address;
		this.pass = pass;
	}
	

	public void setPass(String p){
		this.pass = p;
	}
	
	public String toString(){
		
		return address + " from database";
	}
}
