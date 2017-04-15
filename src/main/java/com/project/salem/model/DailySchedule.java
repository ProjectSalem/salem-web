package com.project.salem.model;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class DailySchedule {

	private DayOfWeek dayOfTheWeek;
	private ArrayList<Schedule> list;
	
	public DailySchedule(DayOfWeek dayOfTheWeek) {
		super();
		this.dayOfTheWeek = dayOfTheWeek;
		this.list = new ArrayList<Schedule>();
	}

	public DayOfWeek getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(DayOfWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public ArrayList<Schedule> getList() {
		return list;
	}

	public void add(Schedule s){
		this.list.add(s);
	}
	
}
