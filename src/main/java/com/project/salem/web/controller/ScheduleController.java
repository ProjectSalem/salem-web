package com.project.salem.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	public ModelAndView schedule(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return new ModelAndView("403");
		}
		
		ModelAndView mav = new ModelAndView("schedule");
		String pageTitle = "Schedule";
		ArrayList<DailySchedule> weeklySchedule = scheduleRepository.getAllSchedule();
		for(DailySchedule ds : weeklySchedule) ds.sort();
		
		mav.addObject("weeklySchedule", weeklySchedule);
		mav.addObject("pageTitle", pageTitle);	
		return mav;
	}
	
	@RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
	public ModelAndView addSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String id = request.getParameter("id");
			String day = request.getParameter("day");
			String time = request.getParameter("time");
			
			if(time.split(" ")[0].split(":")[0].length() < 2) time = "0" + time;
			
			if (id.isEmpty() || id.equals(null)) {
				scheduleRepository.addNewSchedule(Integer.valueOf(day), time);
			} else {
				int schId = Integer.valueOf(id.split("_")[1]);
				scheduleRepository.editSchedule(schId, Integer.valueOf(day), time);
			}
			
			ServletInitializer.restartScheduler();
			
		}
		return schedule(request);	
	}
	
	@RequestMapping(value = "/deleteSchedule", method = RequestMethod.POST)
	public ModelAndView deleteSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String id = request.getParameter("id");
			id = id.split("_")[1];
			
			scheduleRepository.deleteSchedule(Integer.valueOf(id));
			
			ServletInitializer.restartScheduler();
			
		}
		return schedule(request);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
