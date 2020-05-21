package com.epsilon.training.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.epsilon.training.entity.Contact;

public class ContactManager {
	
	private Connection createConnection() throws Exception{
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:tcp://localhost/~/web-training";
		String user = "vinod";
		String password = "secret";
		
		return DriverManager.getConnection(url, user, password);
	}
	
	public List<Contact> getAllContacts() {
		List<Contact> list = new ArrayList<Contact>();
		try(
			Connection conn = this.createConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from contacts");
			ResultSet rs = stmt.executeQuery();
		) {
			while(rs.next()) {
				// convert current record into a contact object, and add to list
				Contact c = new Contact();
				c.setName(rs.getString("name"));
				c.setCity(rs.getString("city"));
				c.setGender(rs.getString("gender"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));
				list.add(c);
			}
		}
		catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		return list;
	}

}
