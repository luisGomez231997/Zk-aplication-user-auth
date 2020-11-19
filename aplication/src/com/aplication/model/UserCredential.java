package com.aplication.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserCredential  implements Serializable {
private static final long serialVersionUID = 1L;
	
	String mail;
	String pass;
	UserService user;
	
	public UserService getUser() {
		return user;
	}

	public void setUser(UserService user) {
		this.user = user;
	}

	Set<String> roles = new HashSet<String>();

	public UserCredential(String mail, String pass) {
		this.mail = mail;
		this.pass = pass;
	}

	public UserCredential() {
		this.mail = "anonymous";
		this.pass = "Anonymous";
		roles.add("anonymous");
	}

	public boolean isAnonymous() {
		return hasRole("anonymous") || "anonymous".equals(mail);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String name) {
		this.pass = pass;
	}
	
	public boolean hasRole(String role){
		return roles.contains(role);
	}
	
	public void addRole(String role){
		roles.add(role);
	}

}
