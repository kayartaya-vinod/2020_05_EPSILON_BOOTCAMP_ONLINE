package com.epsilon.training.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TodayTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			out.println(new Date().toString());
			// out.close(); // never do this!!!
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}

	
}
