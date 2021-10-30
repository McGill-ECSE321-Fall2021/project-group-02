package ca.mcgill.ecse321.librarysystem.dto;

public class MovieDto {
	private String name;
	private String director;
	
	public MovieDto() {
		
	}
	
	public MovieDto(String name, String director) {
		this.name = name;
		this.director = director;
	}
	
	public String getName() {
		return name;
	}
	
	public String getdirector() {
		return director;
	}
}
