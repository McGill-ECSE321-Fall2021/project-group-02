package ca.mcgill.ecse321.librarysystem.dto;


public class MovieDto {
	private String title;
	private String director;
	private int id;
	
	public MovieDto() {
		
	}
	
	public MovieDto(String title, String director, int id) {
		this.title=title;
		this.director=director;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}
	
}
