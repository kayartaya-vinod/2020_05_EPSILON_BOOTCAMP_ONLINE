package com.epsilon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaRepositories
@SpringBootApplication
@RestController
public class Main {
	
	// this bean is responsible for scanning all REST controllers, and making the dynamic documentation
	// of all REST endpoints and corresponding operations.
	
	// This will expose a REST endpoint at http://localhost:8080/v2/api-docs
	// And the UI is available at http://localhost:8080/swagger-ui.html
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				//.apis(RequestHandlerSelectors.basePackage("com.epsilon"))
				.build();
	}
	
	@GetMapping(path = "/api/hello/{name}", produces = "text/plain")
	public String hello(@PathVariable String name) {
		return String.format("Hello, %s!!", name);
	}
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
