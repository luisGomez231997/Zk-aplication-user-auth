package com.aplication.model;

public interface AuthenticationService {

	public boolean login(String mail, String password);

	/** logout current user **/
	public void logout();

	public UserCredential getUserCredential();

}
