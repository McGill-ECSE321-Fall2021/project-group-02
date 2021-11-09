package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.Patron;

public class AlbumDto {
	private String title;
	private String artist;
	
	public AlbumDto() {
		
	}
	
	public AlbumDto(String title, String artist) {
		this.title=title;
		this.artist=artist;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}

}
