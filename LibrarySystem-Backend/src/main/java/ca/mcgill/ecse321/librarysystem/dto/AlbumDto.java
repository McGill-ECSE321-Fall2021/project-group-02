package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.Patron;

public class AlbumDto {
	private Patron patron;
	private String title;
	private String artist;
	
	public AlbumDto() {
		
	}
	
	@SuppressWarnings("unchecked")
	public AlbumDto(String title, String author) {
		this(title, author, null);
	}
	
	public AlbumDto(String title, String artist, Patron patron) {
		this.title=title;
		this.artist=artist;
		this.patron=patron;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public Patron getPatron() {
		return patron;
	}
	
	public void setPatron(Patron patron) {
		this.patron=patron;
	}
}
