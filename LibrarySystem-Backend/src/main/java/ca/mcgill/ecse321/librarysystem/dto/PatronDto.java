package ca.mcgill.ecse321.librarysystem.dto;

public class PatronDto {
	private int id;
	private String address;
	private String city;
	
	public PatronDto() {
		
	}
	
	public PatronDto(int id, String address, String city) {
		this.id = id;
		this.address = address;
		this.city = city;
	}
	
	public int getId() {
		return id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
}
