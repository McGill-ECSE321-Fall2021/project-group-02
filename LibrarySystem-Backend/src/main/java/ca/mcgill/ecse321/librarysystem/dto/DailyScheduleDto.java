package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Time;

import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;

public class DailyScheduleDto {
	
	private WeekDay day;
	private Time startTime;
	private Time endTime;
	private int id;
	
	public DailyScheduleDto() {
		
	}
	
	public DailyScheduleDto(WeekDay day, Time startTime, Time endTime, int id) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Time getStartTime() {
		return this.startTime;
	}
	
	public Time getEndTime() {
		return this.endTime;
	}
	
	public WeekDay getDay() {
		return this.day;
	}

}
