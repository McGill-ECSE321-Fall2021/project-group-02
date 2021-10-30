package ca.mcgill.ecse321.librarysystem.dto;

public class AlbumDto {
	private String name;
	private String artist;
	
	public AlbumDto() {
		
	}
	
	public AlbumDto(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}
	
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
}
