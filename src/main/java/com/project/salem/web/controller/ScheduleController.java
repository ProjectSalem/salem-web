package com.project.salem.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.salem.model.DailySchedule;
import com.project.salem.repository.ScheduleRepository;

@Controller
public class ScheduleController {

	private ScheduleRepository scheduleRepository = new ScheduleRepository();
	
	@RequestMapping("/schedule")
	public ModelAndView schedule() throws Exception {
		ModelAndView mav = new ModelAndView("schedule");
		String pageTitle = "Schedule";
		ArrayList<DailySchedule> weeklySchedule = scheduleRepository.getAllSchedule();
		
		mav.addObject("weeklySchedule", weeklySchedule);
		mav.addObject("pageTitle", pageTitle);	
		return mav;
	}
	
	@RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
	public ModelAndView addSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("schedule");
		String pageTitle = "Schedule";
		ArrayList<DailySchedule> weeklySchedule = scheduleRepository.getAllSchedule();
		
		String day = request.getParameter("day");
		String time = request.getParameter("time");
		
		System.out.println(day + " ---"  + time);
		
		mav.addObject("weeklySchedule", weeklySchedule);
		mav.addObject("pageTitle", pageTitle);	
		return mav;
	}
	
}
