package com.ohsnip.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import com.ohsnip.model.User;

import io.jsonwebtoken.impl.crypto.MacProvider;

public class UserManager implements IUser{
	
	static List<User> users = new ArrayList<User>();
	static Key key;
	
	static UserManager UM = null;
	
	public static UserManager getInstance() {
		if (UM == null) {
			UM = new UserManager();
			key = MacProvider.generateKey();
			
			User u1 = new User(1, "ruigomes", "wow", "ruigomespt43@gmail.com", "ola sou o rui", 1);
			User u2 = new User(2, "M123", "ya", "gato123@gmail.com", "yo", 0);
			
			users.add(u1);
			users.add(u2);
		}
		return UM;
	}
	
	public Key getKey() {
		return key;
	}

	@Override
	public User getUser(int id_utilizador) {
		// TODO Auto-generated method stub
		User user = new User(0, "default", "default", "default");
		for (int i = 0; i <= users.size(); i++) {
			if (id_utilizador == users.get(i).getId()) {
				user.setId(users.get(i).getId());
				user.setNome_utilizador(users.get(i).getNome_utilizador());
				user.setEmail(users.get(i).getEmail());
				user.setDescricao(users.get(i).getDescricao());
				return user;
			} 
		}
		return user;
	}
	
	@Override
	public User logIn(String username, String password) {
		for (int i = 0; i < users.size(); i++) {
			if (username.toLowerCase().equals(users.get(i).getNome_utilizador().toLowerCase())) {
				if (password.equals(users.get(i).getPassword())) {
					User logUser = new User(users.get(i).getId(), users.get(i).getNome_utilizador(), users.get(i).getNivel_acesso());
					return logUser;
				}
			}
		}
		return null;
	}
	
	public User signUp(String username, String password, String email) {
		int newId = 0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() >= newId) {
				newId = users.get(i).getId();
			}
		}
		newId++;
		User newUser = new User(newId, "0", "0", "0", "Add your description here", 0);
		for (int i = 0; i < users.size(); i++) {
			if (!username.toLowerCase().equals(users.get(i).getNome_utilizador().toLowerCase()) && !email.toLowerCase().equals(users.get(i).getEmail().toLowerCase())) {	
				newUser.setNome_utilizador(username);
				newUser.setPassword(password);
				newUser.setEmail(username);
				users.add(newUser);	
				break;
			} 
		}
		return newUser;
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public void editUser(int id_utilizador, String nome_utilizador, String email, String descricao,
			boolean nivel_acesso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(int id_utilizador) {
		// TODO Auto-generated method stub
		
	}

}
