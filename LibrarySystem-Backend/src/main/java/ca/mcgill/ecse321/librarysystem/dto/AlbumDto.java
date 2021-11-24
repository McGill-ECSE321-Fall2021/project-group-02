package ca.mcgill.ecse321.librarysystem.dto;


public class AlbumDto {
	private String title;
	private String artist;
	private int id;
	
	public AlbumDto() {
		
	}
	
	public AlbumDto(String title, String artist, int id) {
		this.title=title;
		this.artist=artist;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}

}
