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
	private boolean loggedIn;
	private int accountId;
	
	public OnlineAccountDto() {
		
	}
	
	//put back user
	public OnlineAccountDto(String username, String password, String email, int userId, String address, String firstName, String lastName, int balance, String city, boolean loggedIn, int accountId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.userId=userId;
		this.address=address;
		this.firstName=firstName;
		this.lastName=lastName;
		this.balance=balance;
		this.city=city;
		this.loggedIn=loggedIn;
		this.accountId=accountId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public boolean getLoggedIn() {
		return this.loggedIn;
	}
	
	public int getAccountId() {
		return this.accountId;
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
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn=loggedIn;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
