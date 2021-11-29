package ca.mcgill.ecse321.librarysystem.dto;


public class MovieDto {
	private String title;
	private String director;
	private boolean isAvailable;
	private int id;
	
	public MovieDto() {
		
	}
	
	public MovieDto(String title, String director, boolean isBorrowed, int id) {
		this.title=title;
		this.director=director;
		this.id = id;
		this.isAvailable = !isBorrowed;
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
	
	public boolean getIsAvailable() {
		return this.isAvailable;
	}
	
}
