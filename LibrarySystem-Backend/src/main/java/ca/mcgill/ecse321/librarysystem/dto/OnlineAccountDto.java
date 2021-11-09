package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.UserEntity;

public class OnlineAccountDto {
	private String username;
	private String password;
	private String email;
	private UserEntity user;
	
	public OnlineAccountDto() {
		
	}
	
	public OnlineAccountDto(String username, String password, String email, UserEntity user) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.user = user;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
