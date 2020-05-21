package com.epsilon.training.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epsilon.training.entity.Contact;
import com.epsilon.training.model.ContactManager;

@WebServlet("/view-all-contacts")
public class GetAllContactsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// A controller has these following tasks/responsibilities:
		
		// #1: Read inputs from the request (if any)
		
		// #2: Use appropriate model (service/dao) to get data from the DB
		ContactManager mgr = new ContactManager();
		List<Contact> list = mgr.getAllContacts();
		
		// #3: Store the model data in a scope (preferably requestScope)
		req.setAttribute("contacts", list);
		
		// #4: Forward to a JSP (view)
		req.getRequestDispatcher("/show-contacts.jsp").forward(req, resp);
	}

	
	
}
