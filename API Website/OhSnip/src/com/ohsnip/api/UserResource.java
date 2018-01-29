package com.ohsnip.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.ohsnip.impl.UserManager;
import com.ohsnip.model.Authdata;
import com.ohsnip.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/users")
public class UserResource {
	
	
	@Path("/user/{userID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userID") int user) {
		UserManager UM = UserManager.getInstance();	
		System.out.println(UM.getUser(user));
		return UM.getUser(user);

	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Authdata logIn(
			@FormParam("username") String username,
			@FormParam("password") String password) {
		
		
		UserManager um = UserManager.getInstance();
					
		User user = um.logIn(username, password);
		
		if (user != null) {
			Map<String,Object> userHash = new HashMap<String,Object>();
			userHash.put("userID", user.getId());
			userHash.put("permission", user.getNivel_acesso());
			
			System.out.println(userHash);
			
			String newToken = Jwts.builder()				  
					  .setClaims(userHash)
					  .setIssuer("OhSnip")			
					  .signWith(SignatureAlgorithm.HS512, um.getKey())				  
					  .compact();
			
			System.out.println(newToken);
			Authdata auth = new Authdata(newToken, user.getId(), user.getNivel_acesso());
			return auth;
		} else {
			return null;
		}
	}
	
	@Path("/register")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Authdata signUp(
			@FormParam("username") String username,
			@FormParam("email") String email,
			@FormParam("password") String password) {
		
		UserManager um = UserManager.getInstance();
		User user = um.signUp(username, password, email);
		
		if (user != null) {
			Map<String,Object> userHash = new HashMap<String,Object>();
			userHash.put("userID", user.getId());
			userHash.put("permission", user.getNivel_acesso());
			
			String newToken = Jwts.builder()				  
					  .setClaims(userHash)
					  .setIssuer("OhSnip")			
					  .signWith(SignatureAlgorithm.HS512, um.getKey())				  
					  .compact();
			
			Authdata auth = new Authdata(newToken, user.getId(), user.getNivel_acesso());
			return auth;
		} else {
			return null;
		}
	}
}
