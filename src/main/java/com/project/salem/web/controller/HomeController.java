package com.project.salem.web.controller;
 
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class HomeController {
	
//	SerialTest serialTest;
	
	@RequestMapping("/")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ModelAndView mav = null;
		
		if(username.equals("1234") && password.equals("1234")){
			response.sendRedirect("home");		
		}
		else {
			mav = new ModelAndView("index");
		}
		
		return mav;
	}
	
	@RequestMapping("/home")
	public ModelAndView index() throws Exception {
		ModelAndView mav = new ModelAndView("home");
		String pageTitle = "Salem Home";
		String hostName = InetAddress.getLocalHost().getHostAddress() + ":9091";
		
		mav.addObject("hostName", hostName);
		mav.addObject("pageTitle", pageTitle);	
		return mav;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/servo", method = RequestMethod.GET)
	public void servo() {

		try {
			 Process process = new ProcessBuilder(new String[] {"/bin/bash", "-c", "sudo python servo.py"})
                     .redirectErrorStream(true)
                     .directory(new File("/home/pi"))
                     .start();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
}