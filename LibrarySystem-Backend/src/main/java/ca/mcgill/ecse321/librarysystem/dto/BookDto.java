package ca.mcgill.ecse321.librarysystem.dto;


public class BookDto {
	
	private String title;
	private String author;
	private boolean isAvailable;
	private int id;
	
	public BookDto() {
		
	}
	
	public BookDto(String title, String author, boolean isBorrowed, int id) {
		this.title=title;
		this.author=author;
		this.id=id;
		this.isAvailable = !isBorrowed;
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
	
	public boolean getIsAvailable() {
		return this.isAvailable;
	}
}
