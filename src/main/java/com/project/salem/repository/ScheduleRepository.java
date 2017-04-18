package com.project.salem.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.util.ArrayList;

import com.project.salem.model.DailySchedule;
import com.project.salem.model.Schedule;

public class ScheduleRepository extends GenericRepository {
	
	public ArrayList<DailySchedule> getAllSchedule() {
		ArrayList<DailySchedule> weeklySchedule = new ArrayList<>();
		for(int i=0; i<7; i++) weeklySchedule.add(new DailySchedule(DayOfWeek.of(i+1)));
		
		PreparedStatement st;
		try {
			Connection conn = getConnection();
			st = conn.prepareStatement("select * from schedule order by id");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				int day = rs.getInt(2);
				String time = rs.getString(3);
				
				weeklySchedule.get(day-1).add(new Schedule(id, time));
			}
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return weeklySchedule;
	}
	
	public void addNewSchedule(int day, String time){
		
		PreparedStatement st;
		try {
			Connection conn = getConnection();								
			st = conn.prepareStatement("insert into schedule set day_of_week = ? , time = ?");
			st.setString(1, String.valueOf(day));
			st.setString(2, time);
			
			st.executeUpdate();
			System.out.println("query:\t" + st.toString());
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}
