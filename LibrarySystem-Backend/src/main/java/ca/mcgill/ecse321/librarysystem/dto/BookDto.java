package ca.mcgill.ecse321.librarysystem.dto;


public class BookDto {
	
	private String title;
	private String author;
	private int id;
	
	public BookDto() {
		
	}
	
	public BookDto(String title, String author, int id) {
		this.title=title;
		this.author=author;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
}
