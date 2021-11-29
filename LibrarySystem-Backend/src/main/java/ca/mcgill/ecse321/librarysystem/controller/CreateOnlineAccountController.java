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

	@PostMapping(value = { "/logIn/{username}/{password}", "/logIn/{username}/{password}/" })
	public OnlineAccountDto logIn(@PathVariable(name = "username") String username,
			@PathVariable(name = "password") String password) throws IllegalArgumentException {
		OnlineAccount account = service.logIn(username, password, true);
		return convertToDto(account);
	}

	/**
	 * Gets a list of all the books in the library software system
	 * 
	 * @return
	 * 
	 * @author Sami
	 */
	@GetMapping(value = { "/onlineAccountLoggedIn", "/onlineAccountLoggedIn/" })
	public OnlineAccountDto getLoggedInAccount() {
		return convertToDto(service.getloggedInAccount());
	}

	/**
	 * Gets the type of the logged in user
	 * @return
	 * 
	 * @author Vy-Kha
	 */
	@GetMapping(value = { "/onlineAccountLoggedInUser", "/onlineAccountLoggedInUser/" })
	public String getLoggedInAccountUser() {
		return service.getloggedInAccountUser().replace("class ca.mcgill.ecse321.librarysystem.model.", "");
	}
	
	/**
	 * Gets the id of the logged in user
	 * @return
	 * 
	 * @author Vy-Kha
	 */
	@GetMapping(value = { "/onlineAccountLoggedInID", "/onlineAccountLoggedInID/" })
	public int getLoggedInAccountID() {
		return service.getloggedInAccountID();
	}
	
	@PostMapping(value = {"/onlineAccountExisting/{id}/{username}/{password}/{email}", "/onlineAccountExisting/{id}/{username}/{password}/{email}/"})
	public OnlineAccountDto createOnlineAccountExistingUser(@PathVariable(name = "id") int id, @PathVariable(name = "username") String username, 
			@PathVariable(name = "password") String password, @PathVariable(name = "email") String email) throws IllegalArgumentException {
		OnlineAccount account = service.createOnlineAccountExistingUser(id, username, password, email, false);
		return convertToDto(account);
	}

	@DeleteMapping(value = { "/deleteOnlineAccountUsername", "/deleteOnlineAccountUsername/" })
	public void deleteOnlineAccountUsername(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) throws IllegalArgumentException {
		service.deleteOnlineAccountUsername(username, password);
	}

	@DeleteMapping(value = { "/deleteOnlineAccountEmail", "/deleteOnlineAccountEmail/" })
	public void deleteOnlineAccountEmail(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) throws IllegalArgumentException {
		service.deleteOnlineAccountUsername(email, password);
	}

	@PutMapping(value = { "/changePassword", "/changePassword/" })
	public OnlineAccountDto changePassword(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "newPassword") String newPassword) throws IllegalArgumentException {
		OnlineAccount account = service.changePassword(id, password, newPassword);
		return convertToDto(account);
	}

	@PutMapping(value = { "/changeEmail", "/changeEmail/" })
	public OnlineAccountDto changeEmail(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "newEmail") String newEmail) throws IllegalArgumentException {
		OnlineAccount account = service.changeEmail(id, password, newEmail);
		return convertToDto(account);
	}

	@PutMapping(value = { "/changeUsername", "/changeUsername/" })
	public OnlineAccountDto changeUsername(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "newUsername") String newUsername) throws IllegalArgumentException {
		OnlineAccount account = service.changeUsername(id, password, newUsername);
		return convertToDto(account);
	}

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
