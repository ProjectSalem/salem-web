package com.project.salem.web;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
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
	private static Scheduler scheduler;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		createAndStartJobs();
		return application.sources(SalemApplication.class);
	}
	
	
	public static void createAndStartJobs(){
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
					
					DayOfWeek today = LocalDate.now().getDayOfWeek();
					SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					String nowTimeStr = format.format(System.currentTimeMillis());
					
					LocalDate next = LocalDate.now().with(TemporalAdjusters.next(ds.getDayOfTheWeek()));
							
					if(today == ds.getDayOfTheWeek()){
						if(format.parse(nowTimeStr).compareTo(format.parse(s.getTime())) < 0){
							next = LocalDate.now();
						}
					}
					
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
			
		} catch (SchedulerException | ParseException e) {
			e.printStackTrace();
		}
		
	}

	public static Scheduler getScheduler() {
		return scheduler;
	}


	public static void restartScheduler() throws SchedulerException {
		scheduler.clear();
		scheduler.shutdown();
		createAndStartJobs();
	}
	
}













