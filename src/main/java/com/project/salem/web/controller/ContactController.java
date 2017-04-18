package com.project.salem.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

	@RequestMapping("/contact")
	public ModelAndView contact(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return new ModelAndView("403");
		}
		
		ModelAndView mav = new ModelAndView("contact");
		String pageTitle = "Contact Page";
		
		mav.addObject("pageTitle", pageTitle);	
		return mav;
	}
	
}
