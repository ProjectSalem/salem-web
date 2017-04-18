package com.project.salem.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.salem.repository.IndexRepository;

@Controller
public class IndexController {

	private IndexRepository indexRepository = new IndexRepository();
	
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView mav = null;
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			mav = new ModelAndView("index");
		}
		else {
			mav = HomeController.index(request);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ModelAndView mav = null;
		
		if (session == null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(indexRepository.authenticateUser(username, password)){
				session = request.getSession(true);
				mav = HomeController.index(request);
			} else {
				mav = new ModelAndView("404");
			}
		}
		else {
			mav = HomeController.index(request);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return new ModelAndView("index");
	}
}
