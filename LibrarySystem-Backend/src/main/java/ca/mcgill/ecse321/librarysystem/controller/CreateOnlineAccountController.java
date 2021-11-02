package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.OnlineAccountDto;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.service.CreateOnlineAccountService;

@CrossOrigin(origins = "*")
@RestController
public class CreateOnlineAccountController {
	@Autowired CreateOnlineAccountService service;
	
	@PostMapping(value = {"/onlineAccount", "/onlineAccount"})
	public OnlineAccountDto createOnlineAccountNewUser(@RequestParam(name = "firstName") String firstName, 
			@RequestParam(name = "lastName") String lastName, @RequestParam(name = "address") String address, 
			@RequestParam(name = "city") String city, @RequestParam(name = "username") String username, 
			@RequestParam(name = "password") String password, @RequestParam(name = "email") String email) throws IllegalArgumentException {
		OnlineAccount account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		return convertToDto(account);
	}
	
	@PostMapping(value = {"/onlineAccount", "/onlineAccount"})
	public OnlineAccountDto createOnlineAccountExistingUser(@RequestParam(name = "firstName") int id, @RequestParam(name = "username") String username, 
			@RequestParam(name = "password") String password, @RequestParam(name = "email") String email) throws IllegalArgumentException {
		OnlineAccount account = service.createOnlineAccountExistingUser(id, username, password, email);
		return convertToDto(account);
	}
	
	private OnlineAccountDto convertToDto(OnlineAccount acc) {
		if (acc==null) {
			throw new IllegalArgumentException("There is no such account!");
		}
		OnlineAccountDto accountDto = new OnlineAccountDto(acc.getUsername(), acc.getPassword(), acc.getEmail(), acc.getUser());
		return accountDto;
	}
}
