package ca.mcgill.ecse321.librarysystem.dto;

import java.util.Date;

public class NewspaperDto {
	private String name;
	private Date date;
	private int id;
	
	public NewspaperDto() {
		
	}

	
	public NewspaperDto(String name, Date date, int id) {
		this.name=name;
		this.date=date;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}
}
