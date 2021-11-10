package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.OnlineAccountDto;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.service.CreateOnlineAccountService;

@CrossOrigin(origins = "*")
@RestController
public class CreateOnlineAccountController {
	@Autowired CreateOnlineAccountService service;
	
	@PostMapping(value = {"/onlineAccountNew/{firstName}/{lastName}/{address}/{city}/{username}/{password}/{email}", "/onlineAccountNew/{firstName}/{lastName}/{address}/{city}/{username}/{password}/{email}/"})
	public OnlineAccountDto createOnlineAccountNewUser(@PathVariable(name = "firstName") String firstName, 
			@PathVariable(name = "lastName") String lastName, @PathVariable(name = "address") String address, 
			@PathVariable(name = "city") String city, @PathVariable(name = "username") String username, 
			@PathVariable(name = "password") String password, @PathVariable(name = "email") String email) throws IllegalArgumentException {
		OnlineAccount account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		return convertToDto(account);
	}
	
	@PostMapping(value = {"/onlineAccountExisting/{id}/{username}/{password}/{email}", "/onlineAccountExisting/{id}/{username}/{password}/{email}/"})
	public OnlineAccountDto createOnlineAccountExistingUser(@PathVariable(name = "id") int id, @PathVariable(name = "username") String username, 
			@PathVariable(name = "password") String password, @PathVariable(name = "email") String email) throws IllegalArgumentException {
		OnlineAccount account = service.createOnlineAccountExistingUser(id, username, password, email);
		return convertToDto(account);
	}
	
	@PostMapping(value = {"/onlineAccountExisting/{username}/{password}", "/onlineAccountExisting/{username}/{password}/"})
	public void deleteOnlineAccountUsername(@PathVariable (name = "username") String username, @PathVariable (name = "password") String password) throws IllegalArgumentException {
		service.deleteOnlineAccountUsername(username, password);
	}
	
	@PostMapping(value = {"/onlineAccountExisting/{email}/{password}", "/onlineAccountExisting/{email}/{password}/"})
	public void deleteOnlineAccountEmail(@PathVariable (name = "email") String email, @PathVariable (name = "password") String password) throws IllegalArgumentException {
		service.deleteOnlineAccountUsername(email, password);
	}
	
	@PostMapping(value = {"/onlineAccountExisting/{username}/{password}/{newPassword}", "/onlineAccountExisting/{username}/{password}/{newPassword}/"})
	public OnlineAccountDto changePassword(@PathVariable(name = "username") String username, @PathVariable(name = "password") String password, 
			@PathVariable(name = "newPassword") String newPassword) throws IllegalArgumentException {
		OnlineAccount account = service.changePassword(username, password, newPassword);
		return convertToDto(account);
	}
	
	@PostMapping(value = {"/onlineAccountExisting/{username}/{password}/{newEmail}", "/onlineAccountExisting/{username}/{password}/{newEmail}/"})
	public OnlineAccountDto changeEmail(@PathVariable(name = "username") String username, @PathVariable(name = "password") String password, 
			@PathVariable(name = "newEmail") String newEmail) throws IllegalArgumentException {
		OnlineAccount account = service.changePassword(username, password, newEmail);
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
