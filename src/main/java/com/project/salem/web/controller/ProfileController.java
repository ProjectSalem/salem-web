package com.project.salem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

	@RequestMapping("/profile")
	public ModelAndView profile() throws Exception {
		ModelAndView mav = new ModelAndView("profile");
		String pageTitle = "Profile";
		
		
		
		
		
		mav.addObject("pageTitle", pageTitle);	
		return mav;
	}
	
}
