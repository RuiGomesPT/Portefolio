package com.ohsnip.impl;

import java.util.List;

import com.ohsnip.model.User;

public interface IUser {
	public User getUser(int id_utilizador);
	public User logIn(String username, String password);
	public User signUp(String username, String password, String email);
	public List<User> getUsers();
	public void editUser(int id_utilizador, String nome_utilizador, String email, String descricao, boolean nivel_acesso);
	public void removeUser(int id_utilizador);
	
	
}
