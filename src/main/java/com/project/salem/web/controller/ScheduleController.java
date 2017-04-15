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
import com.project.salem.web.ServletInitializer;

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
	public void addSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String day = request.getParameter("day");
		String time = request.getParameter("time");
		
		String[] b1 = time.split(" ");
		String[] b2 = b1[0].split(":");
		if(b1[1].equals("PM")) b2[0] = String.valueOf(Integer.valueOf((b2[0]) + 12) % 24);
		time = new String(b2[0] + ":" + b2[1] + ":" + b2[2]);
		
		scheduleRepository.addNewSchedule(Integer.valueOf(day), time);
		
		ServletInitializer.restartScheduler();
		
		response.sendRedirect("schedule");		
	}
	
}
