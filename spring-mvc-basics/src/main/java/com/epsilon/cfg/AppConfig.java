package com.epsilon.cfg;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
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
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.epsilon.entity.Contact;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.epsilon.dao", "com.epsilon.web"})
@PropertySource({ "classpath:jdbc-info.properties" })
public class AppConfig implements WebApplicationInitializer, WebMvcConfigurer {

	
	// this is required for any request that is not resolved by DispatcherServiet with
	// the help of handler-mapping, to be passed on to Tomcat to handle the same.
	// For example, css, javascript, images etc
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
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
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/views/");
		vr.setSuffix(".jsp");
		return vr;
	}
	

	// this function is an event listener for the Application Server's startup event
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// create a spring container, which can load all beans from this configuration file (AppConfig)
		AnnotationConfigWebApplicationContext ctx;
		ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		
		// create a DispatcherServlet instance, supplying the reference of the spring container
		
		Dynamic ds = servletContext.addServlet("ds", new DispatcherServlet(ctx));
		
		// make DispatcherServlet as the controller for all user requests '/'
		ds.addMapping("/");
		
		// load the servlet and it's mapping before any other servlets
		ds.setLoadOnStartup(1);
	}

}
