package ca.mcgill.ecse321.librarysystem.dto;

import java.util.Date;

public class NewspaperDto {
	private String name;
	private Date date;
	
	public NewspaperDto() {
		
	}

	
	public NewspaperDto(String name, Date date) {
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
