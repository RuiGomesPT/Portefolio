package com.ohsnip.model;

public class Authdata {
	private String hash;
	private int id_user;
	private int user_type;
	
	
	
	public Authdata(String hash, int id_user, int user_type) {
		super();
		this.hash = hash;
		this.id_user = id_user;
		this.user_type = user_type;
	}
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
	
}
