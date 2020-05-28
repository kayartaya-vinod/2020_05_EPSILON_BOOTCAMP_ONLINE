package com.epsilon.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.epsilon.dao.ContactsDao;
import com.epsilon.dao.JdbcContactsDao;

@Configuration
@PropertySource({ "classpath:jdbc-info.properties" })
public class AppConfig2 {

	// injecting propery key/value pairs to member variables
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public ContactsDao dao(DataSource ds) { // dependency injection by spring
		JdbcContactsDao jdbcDao = new JdbcContactsDao();
		// jdbcDao.setDataSource(dataSource()); // manual wiring via setter injection
		jdbcDao.setDataSource(ds); // manual wiring via setter injection
		return jdbcDao;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		// DB info
		bds.setUrl(this.url);
		bds.setDriverClassName(this.driverClassName);
		bds.setUsername(this.username);
		bds.setPassword(this.password);

		// Pool info
		bds.setInitialSize(10);
		bds.setMaxTotal(100);
		bds.setMaxIdle(20);
		bds.setMinIdle(5);
		bds.setMaxWaitMillis(15000);

		return bds;
	}

}
