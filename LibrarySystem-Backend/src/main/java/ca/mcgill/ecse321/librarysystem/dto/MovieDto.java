package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.Patron;

public class MovieDto {
	private String title;
	private String director;
	
	public MovieDto() {
		
	}
	
	public MovieDto(String title, String director) {
		this.title=title;
		this.director=director;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}
	
}
