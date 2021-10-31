package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.Patron;

public class MovieDto {
	private Patron patron;
	private String title;
	private String director;
	
	public MovieDto() {
		
	}
	
	@SuppressWarnings("unchecked")
	public MovieDto(String title, String director) {
		this(title, director, null);
	}
	
	public MovieDto(String title, String director, Patron patron) {
		this.title=title;
		this.director=director;
		this.patron=patron;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}
	
	public Patron getPatron() {
		return patron;
	}
	
	public void setPatron(Patron patron) {
		this.patron=patron;
	}
}
