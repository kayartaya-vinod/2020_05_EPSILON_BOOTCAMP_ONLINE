package com.epsilon.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:jdbc-info.properties" })
@ComponentScan(basePackages = {"com.epsilon.dao"})
public class AppConfig4 {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean(name = {"xyz", "dataSource1"})
	public DataSource h2DataSource() {
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
	
	@Bean
	public DataSource mySqlDataSource() {
		BasicDataSource bds = new BasicDataSource();
		// DB info
		bds.setUrl("jdbc:mysql://localhost/epsilon_training");
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bds.setUsername("root");
		bds.setPassword("Welcome#123");

		// Pool info
		bds.setInitialSize(10);
		bds.setMaxTotal(100);
		bds.setMaxIdle(20);
		bds.setMinIdle(5);
		bds.setMaxWaitMillis(15000);

		return bds;
	}

}
