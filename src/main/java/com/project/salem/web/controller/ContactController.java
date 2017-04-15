package com.project.salem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

	@RequestMapping("/contact")
	public ModelAndView contact() throws Exception {
		ModelAndView mav = new ModelAndView("contact");
		String pageTitle = "Contact Page";
		
		
		
		
		
		mav.addObject("pageTitle", pageTitle);	
		return mav;
	}
	
}
