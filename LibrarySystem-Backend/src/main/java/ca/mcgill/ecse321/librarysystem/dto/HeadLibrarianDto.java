package ca.mcgill.ecse321.librarysystem.dto;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

public class HeadLibrarianDto {
	private OnlineAccount onlineAccount;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private WeeklySchedule weeklySchedule;
	private int balance;
	private int id;
	
	public HeadLibrarianDto() {	
	}
	
	public HeadLibrarianDto(OnlineAccount onlineAccount,String firstName,String lastName,String address,String city,int balance,WeeklySchedule weeklySchedule, int id) {
		this.onlineAccount=onlineAccount;
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.balance=balance;
		this.weeklySchedule=weeklySchedule;
		this.id = id;
	}

	public OnlineAccount getOnlineAccount() {
		return onlineAccount;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public WeeklySchedule getWeeklySchedule() {
		return weeklySchedule;
	}
	
	public int getID() {
		return this.id;
	}
}