package com.ohsnip.model;

public class User {
	private int id_utilizador;
	private String nome_utilizador;
	private String password;
	private String email;
	private String descricao;
	private int nivel_acesso;
	
	public User(int id, String nome_utilizador, String password, String email, String descricao, int nivel_acesso) {
		super();
		this.id_utilizador = id;
		this.nome_utilizador = nome_utilizador;
		this.password = password;
		this.email = email;
		this.descricao = descricao;
		this.nivel_acesso = nivel_acesso;
	}
	
	public User(int id, String nome_utilizador, int nivel_acesso) {
		super();
		this.id_utilizador = id;
		this.nome_utilizador = nome_utilizador;
		this.nivel_acesso = nivel_acesso;
	}
	
	public User(int id, String nome_utilizador, String email, String descricao) {
		super();
		this.id_utilizador = id;
		this.nome_utilizador = nome_utilizador;
		this.email = email;
		this.descricao = descricao;
	}
	
	
	
	public int getId() {
		return id_utilizador;
	}
	public void setId(int id) {
		this.id_utilizador = id;
	}
	public String getNome_utilizador() {
		return nome_utilizador;
	}
	public void setNome_utilizador(String nome_utilizador) {
		this.nome_utilizador = nome_utilizador;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNivel_acesso() {
		return nivel_acesso;
	}
	public void setNivel_acesso(int nivel_acesso) {
		this.nivel_acesso = nivel_acesso;
	}
	
	
}
