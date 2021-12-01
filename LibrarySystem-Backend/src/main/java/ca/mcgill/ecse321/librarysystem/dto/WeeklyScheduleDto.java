package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse321.librarysystem.model.DailySchedule;

public class WeeklyScheduleDto {
	
	private Date startDate;
	private Date endDate;
	private List<DailySchedule> day;
	private int id;
	
	public WeeklyScheduleDto() {
		
	}

	public WeeklyScheduleDto(Date startDate, Date endDate, List<DailySchedule> day, int id) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.day = day;
		this.id = id;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public List<DailySchedule> getDailySchedule() {
		return this.day;
	}
	
	public int getId() {
		return this.id;
	}
}
