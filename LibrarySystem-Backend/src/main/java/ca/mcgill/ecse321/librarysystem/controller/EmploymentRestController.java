package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.service.ManagingEmploymentService;
import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;

@CrossOrigin(origins = "*")
@RestController
public class EmploymentRestController {
	@Autowired
	ManagingEmploymentService service;
	
	@Autowired
	LibrarianRepository librarians;
	
	
	/**
	 * 
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians/{id}/{firstname}/{lastname}", "/librarians/{id}/{firstname}/{lastname}/" })
	public List<LibrarianDto> getAllLibrariansByFirstAndLastName(@PathVariable(name="userID")int id,@PathVariable(name="firstname") String fn,@PathVariable(name="lastname") String ln) {
		return service.getAllLibrariansByFirstAndLastName(id, fn, ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	

	/**
	 * 
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians/{id}", "/librarians/{id}/" })
	public List<LibrarianDto> getAllLibrarians(@PathVariable(name="userID") int id) {
		return service.getAllLibrarians(id).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param id id of the user
	 * @param fn first name to be searched
	 * @return list of all librarians sharing the same first name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians/{id}/{firstname}", "//librarians/{id}/{firstname}/" })
	public List<LibrarianDto> getAllLibrariansByFirstName(@PathVariable(name="userID")int id,@PathVariable(name="firstname") String fn) {
		return service.getAllLibrariansByFirstName(id, fn).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param id id of the user
	 * @param ln last name to be searched
	 * @return list of all librarians sharing the same last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians/{id}/{lastname}", "/librarians/{id}/{lastname}/" })
	public List<LibrarianDto> getAllLibrariansByLastName(@PathVariable(name="lastname") String ln) {
		return service.getAllLibrariansByLastName(0, ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	
	/**
	 * 
	 * @param id id of the user
	 * @param firstname first name of the librarian 
	 * @param lastname last name of the librarian
	 * @param address address of librarian
	 * @param city city of residence of the librarian
	 * @param email email to be associated with the online account
	 * @param username username to be associated with the online account
	 * @param password password o be associated with the online account
	 * @return new librarian with the inputs as attributes
	 * @throws IllegalArgumentException
	 * @author vy-khahuynh
	 */
	@PostMapping(value = { "/librarians/{id}/{firstname}/{lastname}/{address}/{city}/{email}/{username}/{password}", "/librarians/{id}/{firstname}/{lastname}/{address}/{city}/{email}/{username}/{password}/" })
	public LibrarianDto createLibrarian(@PathVariable(name="userID")int id,@PathVariable(name="firstname") String firstname,
			@PathVariable(name="lastname") String lastname,@PathVariable(name="address") String address,
			@PathVariable(name="city") String city,@PathVariable(name="email") String email,
			@PathVariable(name="username") String username,
			@PathVariable(name="password") String password) throws IllegalArgumentException {
		Librarian l = service.createLibrarian(id, firstname, lastname, address, city, email, username, password);
		return convertToDto(l);
	}
	
	/**
	 * 
	 * @param id id of the user
	 * @param oa online account of the librarian
	 * @param firstname first name of the librarian 
	 * @param lastname last name of the librarian
	 * @param address address of librarian
	 * @param city city of residence of the librarian
	 * @return new librarian with the inputs as attributes
	 * @throws IllegalArgumentException
	 * @author vy-khahuynh
	 */
	@PostMapping(value = { "/librarians/{id}/{onlineaccount}/{firstname}/{lastname}/{address}/{city}", "/{id}/librarians/{onlineaccount}/{firstname}/{lastname}/{address}/{city}/" })
	public LibrarianDto createLibrarianOnlineAccount(@PathVariable(name="userID")int id,@PathVariable(name="onlineaccount") OnlineAccount oa,@PathVariable(name="firstname") String firstname,
			@PathVariable(name="lastname") String lastname,@PathVariable(name="address") String address,
			@PathVariable(name="city") String city) throws IllegalArgumentException {
		Librarian l = service.createLibrarianOnlineAccount(id, oa, firstname, lastname, address, city);
		return convertToDto(l);
	}
	
	/**
	 * 
	 * @param l librarian object to be converted to librarianDto
	 * @return librarianDto object of librarian l
	 */
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		LibrarianDto lDto = new LibrarianDto(l.getOnlineAccount(),l.getFirstName(),l.getLastName(),l.getAddress(),l.getCity(),l.getBalance(),l.getWeeklySchedule());
		return lDto;	
	}
}
