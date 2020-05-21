package com.epsilon.training.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/print-table", "/table"})
public class PrintTableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		// read user inputs
		String strNum = req.getParameter("num");
		String strLimit = req.getParameter("limit");
		
		if(strNum==null || strLimit==null) {
			resp.sendRedirect("./"); // redirects the client to the homepage
			return;
		}
		
//		try {
			int num = Integer.parseInt(strNum);
			int limit = Integer.parseInt(strLimit);
			
			out.println("<h1>Multiplication Table for " + num + "</h1>");
			out.println("<hr>");
			for(int i=1; i<=limit; i++) {
				out.printf("%d X %d = %d<br>", num, i, num*i);
			}
//		} catch (NumberFormatException e) {
//			out.println("<h1>There was an error while processing your request</h1>");
//			out.println("<hr>");
//			out.println("<div>This happened because, you must have given non numerical values for inputs</div>");
//		}
		
		out.println("<div><a href='./'>Home</a></div>");
		out.close();
		
		
	}

	
	
}
