package com.project.salem.web;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.project.salem.model.DailySchedule;
import com.project.salem.model.Schedule;
import com.project.salem.repository.ScheduleRepository;
import com.project.salem.web.controller.MyJob;

public class ServletInitializer extends SpringBootServletInitializer {

	private static ScheduleRepository scheduleRepository = new ScheduleRepository();
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		createAndStartJobs();
		return application.sources(SalemApplication.class);
	}
	
	
	public static void createAndStartJobs(){
		Scheduler scheduler;
		ArrayList<DailySchedule> weeklySchedule = scheduleRepository.getAllSchedule();
		
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		
			for(DailySchedule ds : weeklySchedule){
				for(Schedule s : ds.getList()){
					String[] time = s.getTime().split(":");
					int hour = Integer.valueOf(time[0]);
					int minute = Integer.valueOf(time[1]);
					int second = Integer.valueOf(time[2]);
					
					LocalDate next = LocalDate.now().with(TemporalAdjusters.next(ds.getDayOfTheWeek()));
					
					int day = next.getDayOfMonth();
					int month = next.getMonthValue();
					
					String name = hour + "-" + minute + "-" + second + "-" + day + "-" + month;
					String group = ds.getDayOfTheWeek().toString() + "_Group";
					
					JobDetail job = newJob(MyJob.class)
							.withIdentity(name, group)
							.build();
					
					Trigger trigger = newTrigger() 
					     .withIdentity(name, group)
					     .withSchedule(calendarIntervalSchedule()
					             .withIntervalInWeeks(1))
					     .startAt(dateOf(hour, minute, second, day, month))
					     .build();
					
					scheduler.scheduleJob(job, trigger);
					System.out.println(
						"JOB: [" + job.getKey().getName() + ", " + job.getKey().getGroup() 
						+ "] succesfully started with TRIGGER: [" + 
						trigger.getKey().getName() + ", " + trigger.getKey().getGroup() + "]" 
					);
				}
			}
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}

}













