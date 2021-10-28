package ca.mcgill.ecse321.librarysystem.dto;

public class OnlineAccountDto {
	private String username;
	private String password;
	private String email;
	
	public OnlineAccountDto() {
		
	}
	
	public OnlineAccountDto(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
}
