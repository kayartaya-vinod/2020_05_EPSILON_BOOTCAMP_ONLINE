package com.epsilon.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.epsilon.entity.Contact;

@EnableTransactionManagement
//@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = { "com.epsilon.dao", "com.epsilon.aop" })
@PropertySource({ "classpath:jdbc-info.properties" })
public class AppConfig5 {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public PlatformTransactionManager txMgr(SessionFactory sessionFactory) { // dependency injection
		return new HibernateTransactionManager(sessionFactory); // manual wiring via constructor injection
	}

	@Bean
	public HibernateTemplate ht(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(ds);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");

		factoryBean.setHibernateProperties(props);
		factoryBean.setAnnotatedClasses(Contact.class);

		return factoryBean;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
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
