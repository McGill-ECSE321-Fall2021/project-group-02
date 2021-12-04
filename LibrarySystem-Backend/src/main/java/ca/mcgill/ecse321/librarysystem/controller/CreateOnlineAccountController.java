package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.BookDto;
import ca.mcgill.ecse321.librarysystem.dto.OnlineAccountDto;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.service.CreateOnlineAccountService;

@CrossOrigin(origins = "*")
@RestController
public class CreateOnlineAccountController {
	@Autowired
	CreateOnlineAccountService service;
	
	/**
	* Creates an online account for a new user
	*
	* @param firstName The first name of the new user
	* @param lastName The last name of the new user
	* @param address The address of the new user
	* @param city The city of the new user
	* @param username The username of the new user
	* @param password The password of the new user
	* @param email The email of the new user
	*
	* @return The online account of the new user
	*
	* @author Sami Ait Ouahmane
	*/

	@PostMapping(value = { "/onlineAccountNew/{firstName}/{lastName}/{address}/{city}/{username}/{password}/{email}",
			"/onlineAccountNew/{firstName}/{lastName}/{address}/{city}/{username}/{password}/{email}/" })
	public OnlineAccountDto createOnlineAccountNewUser(@PathVariable(name = "firstName") String firstName,
			@PathVariable(name = "lastName") String lastName, @PathVariable(name = "address") String address,
			@PathVariable(name = "city") String city, @PathVariable(name = "username") String username,
			@PathVariable(name = "password") String password, @PathVariable(name = "email") String email)
			throws IllegalArgumentException {
		OnlineAccount account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password,
				email, false);
		return convertToDto(account);
	}
	
	/**
	* Logs in the user with the username and password
	*
	* @param username The username that the patron uses to log in
	* @param password The password that the patron uses to log in
	*
	* @return The online account of the user logging in
	* 
	* @author Sami Ait Ouahmane
	*/

	@PostMapping(value = { "/logIn/{username}/{password}", "/logIn/{username}/{password}/" })
	public OnlineAccountDto logIn(@PathVariable(name = "username") String username,
			@PathVariable(name = "password") String password) throws IllegalArgumentException {
		OnlineAccount account = service.logIn(username, password, true);
		return convertToDto(account);
	}
	
	/**
	* Signs out the currently logged in user
	*
	* @author Sami Ait Ouahmane
	*/

	@PostMapping(value = { "/signOut", "/signOut/" })
	public void signOut() throws IllegalArgumentException {
		service.signOutAccount();
	}

	/**
	 * Gets the online account of the current logged in user
	 * 
	 * @return The online account of the logged in user
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@GetMapping(value = { "/onlineAccountLoggedIn", "/onlineAccountLoggedIn/" })
	public OnlineAccountDto getLoggedInAccount() {
		return convertToDto(service.getloggedInAccount());
	}
	
	/**
	 * Gets the type of the logged in user
	 * 
	 * @return The type of the logged in user
	 * 
	 * @author Vy-Kha Huyhn
	 */
	
	@GetMapping(value = { "/onlineAccountLoggedInUser", "/onlineAccountLoggedInUser/" })
	public String getLoggedInAccountUser() {
		return service.getloggedInAccountUser().replace("class ca.mcgill.ecse321.librarysystem.model.", "");
	}

	/**
	 * Gets the ID of the logged in user
	 * 
	 * @return The ID of the logged in user
	 * 
	 * @author Vy-Kha Huyhn
	 */
	
	@GetMapping(value = { "/onlineAccountLoggedInID", "/onlineAccountLoggedInID/" })
	public int getLoggedInAccountID() {
		return service.getloggedInAccountID();
	}
	
	/**
	* Creates an online account for an existing user
	*
	* @param id The ID that was generated for the existing user
	* @param username The username that the existing user created
	* @param password The password that the existing user created
	* @param email The email of the existing user
	*
	* @return The online account of the existing user
	*
	* @author Vy-Kha Huynh
	*/

	@PostMapping(value = { "/onlineAccountExisting/{id}/{username}/{password}/{email}",
			"/onlineAccountExisting/{id}/{username}/{password}/{email}/" })
	public OnlineAccountDto createOnlineAccountExistingUser(@PathVariable(name = "id") int id,
			@PathVariable(name = "username") String username,
			@PathVariable(name = "password") String password, @PathVariable(name = "email") String email)
			throws IllegalArgumentException {
		OnlineAccount account = service.createOnlineAccountExistingUser(id, username, password, email, false);
		return convertToDto(account);
	}
	
	/**
	* Deletes the online account given a username
	*
	* @param username The username associated with the online account
	* @param password The password associated with the online account
	*
	* @author Hyunbum Cho
	*/

	@DeleteMapping(value = { "/deleteOnlineAccountUsername", "/deleteOnlineAccountUsername/" })
	public void deleteOnlineAccountUsername(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) throws IllegalArgumentException {
		service.deleteOnlineAccountUsername(username, password);
	}
	
	/**
	* Deletes the online account given an email
	*
	* @param email The email associated with the online account
	* @param password The password associated with the online account
	*
	* @author Hyunbum Cho
	*/

	@DeleteMapping(value = { "/deleteOnlineAccountEmail", "/deleteOnlineAccountEmail/" })
	public void deleteOnlineAccountEmail(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) throws IllegalArgumentException {
		service.deleteOnlineAccountUsername(email, password);
	}
	
	/**
	* Deletes the online account given an ID
	*
	* @param id The ID associated with the online account
	* @param password The password associated with the online account
	*
	* @author Hyunbum Cho
	*/

	@DeleteMapping(value = { "/deleteOnlineAccount", "/deleteOnlineAccount/" })
	public void deleteOnlineAccount(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password) throws IllegalArgumentException {
		service.deleteOnlineAccount(id, password);
	}
	
	/**
	* Changes the password for a given online account
	*
	* @param id The ID associated with the online account
	* @param password The old password associated with the online account
	* @param newPassword The new password to be associated with the online account
	*
	* @return The online account with the new password
	*
	* @author Hyunbum Cho
	*/

	@PutMapping(value = { "/changePassword", "/changePassword/" })
	public OnlineAccountDto changePassword(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "newPassword") String newPassword) throws IllegalArgumentException {
		OnlineAccount account = service.changePassword(id, password, newPassword);
		return convertToDto(account);
	}
	
	/**
	* Changes the email for a given online account
	*
	* @param id The ID associated with the online account
	* @param password The password associated with the online account
	* @param newEmail The new email to be associated with the online account
	*
	* @return The online account with the new email
	*
	* @author Hyunbum Cho
	*/

	@PutMapping(value = { "/changeEmail", "/changeEmail/" })
	public OnlineAccountDto changeEmail(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "newEmail") String newEmail) throws IllegalArgumentException {
		OnlineAccount account = service.changeEmail(id, password, newEmail);
		return convertToDto(account);
	}
	
	/**
	* Changes the username for a given online account
	*
	* @param id The ID associated with the online account
	* @param password The password associated with the online account
	* @param newUsername The new username to be associated with the online account
	*
	* @return The online account with the new username
	*
	* @author Hyunbum Cho
	*/

	@PutMapping(value = { "/changeUsername", "/changeUsername/" })
	public OnlineAccountDto changeUsername(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "newUsername") String newUsername) throws IllegalArgumentException {
		OnlineAccount account = service.changeUsername(id, password, newUsername);
		return convertToDto(account);
	}
	
	/**
	* Converts the OnlineAccount object to OnlineAccountDto object
	*
	* @param acc The OnlineAccount object to be converted
	*
	* @return The OnlineAccountDto object of the online account
	*
	* @author Hyunbum Cho
	*/

	private OnlineAccountDto convertToDto(OnlineAccount acc) {
		if (acc == null) {
			throw new IllegalArgumentException("There is no such account!");
		}
		OnlineAccountDto accountDto = new OnlineAccountDto(acc.getUsername(), acc.getPassword(), acc.getEmail(),
				acc.getUser().getId(), acc.getUser().getAddress(), acc.getUser().getFirstName(), acc.getUser().getLastName(),
				acc.getUser().getBalance(), acc.getUser().getCity(), acc.getLoggedIn(), acc.getId());
		return accountDto;
	}
}
