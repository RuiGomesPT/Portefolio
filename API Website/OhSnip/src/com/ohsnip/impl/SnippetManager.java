package com.ohsnip.impl;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import com.ohsnip.model.Snippet;

public class SnippetManager implements ISnippet{

		static List<Snippet> snippets = new ArrayList<Snippet>();
		static Key key;
		
		static SnippetManager SM = null;
		
		public static SnippetManager getInstance() {
			if (SM == null) {
				SM = new SnippetManager();
				
				Snippet s1 = new Snippet(1, "Copy to Clipboard in Javascript", 1, 301, 5, 1, "1;", "javascript; exemplo", "Javascript", "\r\n" + 
						"function myFunction() {\r\n" + 
						"  var copyText = document.getElementById(\"placeholder\");\r\n" + 
						"  copyText.select();\r\n" + 
						"  document.execCommand(\"Copy\");\r\n" + 
						"  alert(\"Copied the text: \" + copyText.value);\r\n" + 
						"}\r\n" + 
						"", "Copy to Clipboard example");
				
				snippets.add(s1);
			}
			return SM;
		}

		@Override
		public Snippet getSnippet(int id_snippet) {
			for (int i = 0; i < snippets.size(); i++) {
				if (snippets.get(i).getId_snippet() == id_snippet) {
					snippets.get(i).setVisualizacoes(snippets.get(i).getVisualizacoes() + 1);
					return snippets.get(i);
				}
			}
			return null;
		}

		@Override
		public List<Snippet> getSnippets() {
			return snippets;
		}

		@Override
		public List<Snippet> getSnippets(String search) {
			List<Snippet> searchSnip = new ArrayList<Snippet>();
			String searchS = search.toLowerCase();
			for (int i = 0; i < snippets.size(); i++) {
				String searchP = snippets.get(i).getTags().toLowerCase();
				String[] parts = searchP.split("; ");
				for (int x = 0; x < parts.length; x++) {
					if (searchS.contains(parts[x])) {
						if (!searchSnip.contains(snippets.get(i))) {
							searchSnip.add(snippets.get(i));	
						}	
					}
				}
			}
			
			return searchSnip;
		}

		@Override
		public Snippet createSnippet(String nome_snippet, int id_utilizador, String tags, String linguagem, String conteudo, String descricao) {
			int newId = 0;
			for (int i = 0; i< snippets.size(); i++) {
				newId = snippets.get(i).getId_snippet() + 1;
			}
			Snippet newSnip = new Snippet(newId, nome_snippet, id_utilizador, 0, 0, 0, "", tags, linguagem, conteudo, descricao);
			snippets.add(newSnip);
			return newSnip;
		}
		
		@Override
		public List<Snippet> searchSnippetByUser(int id) {
			List<Snippet> userSnip = new ArrayList<Snippet>();
			for (int i = 0; i < snippets.size(); i++) {
				if (snippets.get(i).getId_utilizador() == id) {
					userSnip.add(snippets.get(i));
				}
			}
			
			return userSnip;
		}

		@Override
		public void RemoveSnippet(int id_snippet) {
			for (int i = 0; i < snippets.size(); i++) {
				if (snippets.get(i).getId_snippet() == id_snippet) {
					
					snippets.remove(i);
				}
			}
		}

		@Override
		public void editSnippet(int id_snippet, String nome_snippet, String tags, String linguagem, String conteudo, String descricao) {
			// TODO Auto-generated method stub
			for (int i = 0; i < snippets.size(); i++) {
				if (snippets.get(i).getId_snippet() == id_snippet) {
					snippets.get(i).setNome_snippet(nome_snippet);
					snippets.get(i).setTags(tags);
					snippets.get(i).setLinguagem(linguagem);
					snippets.get(i).setConteudo(conteudo);
					snippets.get(i).setDescricao(descricao);
				}
			}
		}

		@Override
		public void rateSnippet(int id_snippet, int classificacao, int id_util) {
			for (int i = 0; i < snippets.size(); i++) {
				if (snippets.get(i).getId_snippet() == id_snippet) {
					int score = snippets.get(i).getClassificacao() + classificacao;
					snippets.get(i).setClassificacao(score);
					
					snippets.get(i).setReviewNum(snippets.get(i).getReviewNum() + 1);
					
					String s = snippets.get(i).getIdRev();
					s = s.concat(id_util + ";");
					snippets.get(i).setIdRev(s);
				}
			}
			
		}

		public Key getKey() {
			return key;
		}
}
