package com.ohsnip.routes;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registo extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	public void user() {
	}	

	   @Override
	   protected void doGet(HttpServletRequest request,
	           HttpServletResponse response) throws ServletException, IOException {
		   
		   RequestDispatcher despachar = request.getRequestDispatcher("register.html");
		   despachar.forward(request, response);
	   }

	   
	   @Override
	   protected void doPost(HttpServletRequest request,
	           HttpServletResponse response) throws ServletException, IOException {
	       this.doGet(request, response);
	   }
}
