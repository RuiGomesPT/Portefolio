package com.ohsnip.api;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ohsnip.impl.SnippetManager;
import com.ohsnip.model.Snippet;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Path("/snippets")
public class Resource {


		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Snippet> getSnippets() {
			SnippetManager SM = SnippetManager.getInstance();
			return SM.getSnippets();	
		}
		
		@Path("/{search}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Snippet> getSearch(@PathParam("search") String search) {
			SnippetManager SM = SnippetManager.getInstance();		
			if (search!=null) {
				return SM.getSnippets(search);
			} else {
				return SM.getSnippets();
			}
					
		}
		
		
		
		
		@Path("/users/{userSnip}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Snippet> getSnippetByUser(@PathParam("userSnip") int id) {
			SnippetManager SM = SnippetManager.getInstance();		
			return SM.searchSnippetByUser(id);
					
		}
		
		@Path("/snippet/{snippetID}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Snippet getSnippet(@PathParam("snippetID") int id) {
			SnippetManager SM = SnippetManager.getInstance();	
			return SM.getSnippet(id);	
		}
		
		@Path("/snippet/{snippetID}")
		@DELETE	
		public Response removeSnippet(
				@PathParam("snippetID") int id) {
			
			SnippetManager sm = SnippetManager.getInstance();	
			sm.RemoveSnippet(id);
			
			return Response.ok().entity("Snippet removed!").build();
					
		}
		
		@Path("/edit")
		@PUT
		public Response updateSnippet(
				@FormParam("id_snippet") int id_snippet,
				@FormParam("nome_snippet") String nome_snippet,
				@FormParam("tags") String  tags,
				@FormParam("linguagem") String linguagem,
				@FormParam("conteudo") String  conteudo,
				@FormParam("descricao") String descricao,
				@FormParam("token") String token) { 
			
			SnippetManager sm = SnippetManager.getInstance();
			try {
				
				//String login = (String) Jwts.parser().setSigningKey(sm.getKey()).parseClaimsJws(token).getBody().get("userID");
				//Jwts.parser().setSigningKey(sm.getKey()).parseClaimsJws(token);
				sm.editSnippet(id_snippet, nome_snippet, tags, linguagem, conteudo, descricao);
				
				return Response.ok().entity("Snippet updated").build();
			} catch (SignatureException e){
				return Response.ok().entity("Not allowed").build();
			}

		}
				
		
		@Path("/new")
		@POST
		@Consumes("application/x-www-form-urlencoded")
		public void createSnippet(
				@FormParam("nome") String nome,
				@FormParam("utilizador") int utilizador,
				@FormParam("descricao") String  descricao,
				@FormParam("linguagem") String linguagem,
				@FormParam("conteudo") String  conteudo,
				@FormParam("tags") String tags) {
			
			SnippetManager SM = SnippetManager.getInstance();	
			SM.createSnippet(nome, utilizador, descricao, linguagem, conteudo, tags);	
		}
		
		@Path("/rate")
		@PUT
		public void rateSnippet(
				@FormParam("id_snippet") int id_snippet,
				@FormParam("id_user") int id_user,
				@FormParam("rate") int rate) { 
			
			System.out.println("1");
			SnippetManager sm = SnippetManager.getInstance();

			sm.rateSnippet(id_snippet, rate, id_user);
				


		}
		   
}

