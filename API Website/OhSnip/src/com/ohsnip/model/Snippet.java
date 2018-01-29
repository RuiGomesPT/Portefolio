package com.ohsnip.model;

public class Snippet {
	private int id_snippet;
	private String nome_snippet;
	private int id_utilizador;
	private int visualizacoes;
	private int classificacao;
	private int reviewNum;
	private String idRev;
	private String tags;
	private String linguagem;
	private String conteudo;
	private String descricao;
	
	
	public Snippet(int id_snippet, String nome_snippet, int id_utilizador, int visualizacoes, int classificacao, int reviewNum, String idRev,
			String tags, String linguagem, String conteudo, String descricao) {
		super();
		this.id_snippet = id_snippet;
		this.nome_snippet = nome_snippet;
		this.id_utilizador = id_utilizador;
		this.visualizacoes = visualizacoes;
		this.classificacao = classificacao;
		this.reviewNum = reviewNum;
		this.idRev = idRev;
		this.tags = tags;
		this.linguagem = linguagem;
		this.conteudo = conteudo;
		this.descricao = descricao;
	}
	

	public Snippet(String nome_snippet, int id_utilizador, String tags, String linguagem, String conteudo, String descricao) {
		super();
		this.nome_snippet = nome_snippet;
		this.id_utilizador = id_utilizador;
		this.tags = tags;
		this.linguagem = linguagem;
		this.conteudo = conteudo;
		this.descricao = descricao;
	}


	public int getId_snippet() {
		return id_snippet;
	}
	public void setId_snippet(int id_snippet) {
		this.id_snippet = id_snippet;
	}
	public String getNome_snippet() {
		return nome_snippet;
	}
	public void setNome_snippet(String nome_snippet) {
		this.nome_snippet = nome_snippet;
	}
	public int getId_utilizador() {
		return id_utilizador;
	}
	public void setId_utilizador(int id_utilizador) {
		this.id_utilizador = id_utilizador;
	}
	public int getVisualizacoes() {
		return visualizacoes;
	}
	public void setVisualizacoes(int visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
	public int getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getLinguagem() {
		return linguagem;
	}
	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getReviewNum() {
		return reviewNum;
	}


	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}


	public String getIdRev() {
		return idRev;
	}


	public void setIdRev(String idRev) {
		this.idRev = idRev;
	}
	
	
}
