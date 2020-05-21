package com.epsilon.training.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// to import any class automatically, use CTRL+SHIFT+O


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<h1>Hello, World!</h1>");
		out.println("<hr>");
		out.println("<p>A message from Vinod</p>");
		
//		Cookie c1 = new Cookie("greet", "JSP is really cool");
//		c1.setMaxAge(60*60*24*365);
//		resp.addCookie(c1);
//		
		
		Cookie c1 = new Cookie("greet", "JSP_is_really_cool");
		c1.setMaxAge(60*60*24);
		resp.addCookie(c1);
		
		
		out.close();
	}

	
}
