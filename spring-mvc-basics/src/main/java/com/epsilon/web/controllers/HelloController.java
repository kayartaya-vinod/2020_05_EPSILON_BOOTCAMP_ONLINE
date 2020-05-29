package com.epsilon.web.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // qualified for component scan; HandlerMapping scans for all request handler methods 
public class HelloController {
	
	@RequestMapping("/hello")
	public ModelAndView sayHello() {
		String modelData = "Hello, Spring MVC!";
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", modelData);
		mav.setViewName("hello");
		
		// if 'x' is a logical view name
		// then /WEB-INF/views/x.jsp is the physical file name
		
		System.out.println("Got a client request at " + new Date().toString());
		return mav;
	}

}
