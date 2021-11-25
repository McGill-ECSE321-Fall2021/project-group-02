package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.UserEntity;

public class OnlineAccountDto {
	private String username;
	private String password;
	private String email;
	private int userId;
	private String address;
	private String firstName;
	private String lastName;
	private int balance;
	private String city;
	
	public OnlineAccountDto() {
		
	}
	
	//put back user
	public OnlineAccountDto(String username, String password, String email, int userId, String address, String firstName, String lastName, int balance, String city) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.userId=userId;
		this.address=address;
		this.firstName=firstName;
		this.lastName=lastName;
		this.balance=balance;
		this.city=city;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
