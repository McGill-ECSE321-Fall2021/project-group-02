package ca.mcgill.ecse321.librarysystem.dto;

public class PatronDto {
	private int id;
	private String address;
	private String city;
	private String firstName;
	private String lastName;
	private int balance;
	
	public PatronDto() {
		
	}
	
	public PatronDto(int id, String address, String city, String firstName, String lastName, int balance) {
		this.id = id;
		this.address = address;
		this.city = city;
		this.firstName=firstName;
		this.lastName=lastName;
		this.balance=balance;
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
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public int getBalance() {
		return balance;
	}
}
