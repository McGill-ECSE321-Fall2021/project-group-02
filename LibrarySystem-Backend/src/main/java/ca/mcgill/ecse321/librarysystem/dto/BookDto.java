package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.Patron;

public class BookDto {
	
	private String title;
	private String author;
	
	public BookDto() {
		
	}
	
	public BookDto(String title, String author) {
		this.title=title;
		this.author=author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
}
