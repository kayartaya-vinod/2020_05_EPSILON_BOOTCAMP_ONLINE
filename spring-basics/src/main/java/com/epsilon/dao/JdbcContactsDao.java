package com.epsilon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

// We can mark this class as a "spring component" using one of these annotations:
// Component, Repository (DAO), Service (Business Logic), Controller (Web Controller), RestController, Configuration
@Repository
public class JdbcContactsDao implements ContactsDao {

	// fields
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	// represents a DB Connection Pool (DBCP)
	@Qualifier("xyz") // looks for a bean with this name - "mysqlDataSource"
	@Autowired(required = false) // generally not used; just to make sure AppConfig1 works
	private DataSource dataSource;

	public JdbcContactsDao() {
		// default constructor, default choice by Spring; one of the best practices
	}

	// we can ask spring to use this constructor instead of the default one
	// this is called constructor-injection
	public JdbcContactsDao(String driverClassName, String url, String username, String password) {
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	// Spring can also invoke setters (setter based injection)
	// spring never ever uses getters
	// a setter is also known as a "writable property" or "mutator"
	// the name of the writable property is the name of the function with out "set"
	// in camel-case
	// i.e, "driverClassName"
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// a writable property called "dataSource"
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection createConnection() throws Exception {
		if (this.dataSource != null) {
			return this.dataSource.getConnection();
		}
		Class.forName(driverClassName);
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public long count() {

		try (Connection conn = this.createConnection();
				PreparedStatement stmt = conn.prepareStatement("select count(*) from contacts");
				ResultSet rs = stmt.executeQuery();) {
			rs.next();
			return rs.getLong(1);
		} catch (Exception ex) {
			throw new RuntimeException(ex); // converting checked-exception to unchecked-exception
		}
	}

}
