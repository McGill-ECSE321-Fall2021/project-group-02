package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.Patron;

public class BookDto {
	
	private Patron patron;
	private String title;
	private String author;
	
	public BookDto() {
		
	}
	
	@SuppressWarnings("unchecked")
	public BookDto(String title, String author) {
		this(title, author, null);
	}
	
	public BookDto(String title, String author, Patron patron) {
		this.title=title;
		this.author=author;
		this.patron=patron;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public Patron getPatron() {
		return patron;
	}
	
	public void setPatron(Patron patron) {
		this.patron=patron;
	}
}
