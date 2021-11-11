package ca.mcgill.ecse321.librarysystem.dto;

import java.util.Date;


public class JournalDto {
	private String name;
	private Date date;
	
	public JournalDto() {
		
	}

	
	public JournalDto(String name, Date date) {
		this.name=name;
		this.date=date;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}
	
}
