package com.aplication.model;

import java.util.List;

public interface UserService {
	
	public List <CatUsuario> findAll();
	public List <CatUsuario> search(String keyword);
	public void editUser(CatUsuario usuario);
	public void deleteUser(int id);
	public void createUser(CatUsuario usuario);
	public CatUsuario getUser(String email, String pass);
}
