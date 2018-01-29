package com.ohsnip.impl;

import java.util.List;

import com.ohsnip.model.Snippet;

public interface ISnippet {
	public Snippet getSnippet(int id_snippet);
	public List<Snippet> getSnippets();
	public List<Snippet> getSnippets(String search);
	public List<Snippet> searchSnippetByUser(int id);
	public Snippet createSnippet(String nome_snippet, int id_utilizador, String tags, String linguagem, String conteudo, String descricao);
	public void editSnippet(int id_snippet, String nome_snippet, String tags, String linguagem, String conteudo, String descricao);
	public void rateSnippet(int id_snippet, int classificacao, int id_util);
	public void RemoveSnippet(int id_snippet);
	
}
