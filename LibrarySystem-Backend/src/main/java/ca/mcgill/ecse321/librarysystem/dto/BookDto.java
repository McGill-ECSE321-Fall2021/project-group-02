package ca.mcgill.ecse321.librarysystem.dto;

public class BookDto {
	private String name;
	private String author;
	
	public BookDto() {
		
	}
	
	public BookDto(String name, String author) {
		this.name = name;
		this.author = author;
	}
	
	public String getName() {
		return name;
	}
	
	public String getauthor() {
		return author;
	}
}
