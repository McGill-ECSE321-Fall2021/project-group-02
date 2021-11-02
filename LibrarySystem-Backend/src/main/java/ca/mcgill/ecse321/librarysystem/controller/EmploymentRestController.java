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
	 * @return all librarians
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrarians() {
		return service.getAllLibrarians().stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param fn first name to be searched
	 * @param ln last name to be searched
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrariansByFirstAndLastName(@RequestParam(name="firstname") String fn,@RequestParam(name="lastname") String ln) {
		return service.getAllLibrariansByFirstAndLastName(fn, ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param fn first name to be searched
	 * @return list of all librarians sharing the same first name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrariansByFirstName(@RequestParam(name="firstname") String fn) {
		return service.getAllLibrariansByFirstName(fn).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @param ln last name to be searched
	 * @return list of all librarians sharing the same last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrariansByLastName(@RequestParam(name="lastname") String ln) {
		return service.getAllLibrariansByLastName(ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * 
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
	@PostMapping(value = { "/librarians", "/librarians/" })
	public LibrarianDto createLibrarian(@RequestParam(name="firstname") String firstname,@RequestParam(name="lastname") String lastname,@RequestParam(name="address") String address,
			@RequestParam(name="city") String city,@RequestParam(name="email") String email,@RequestParam(name="username") String username,
			@RequestParam(name="password") String password) throws IllegalArgumentException {
		Librarian l = service.createLibrarian(firstname, lastname, address, city, email, username, password);
		return convertToDto(l);
	}
	
	/**
	 * 
	 * @param oa online account of the librarian
	 * @param firstname first name of the librarian 
	 * @param lastname last name of the librarian
	 * @param address address of librarian
	 * @param city city of residence of the librarian
	 * @return new librarian with the inputs as attributes
	 * @throws IllegalArgumentException
	 * @author vy-khahuynh
	 */
	@PostMapping(value = { "/librarians", "/librarians/" })
	public LibrarianDto createLibrarianOnlineAccount(@RequestParam(name="onlineaccount") OnlineAccount oa,@RequestParam(name="firstname") String firstname,@RequestParam(name="lastname") String lastname,@RequestParam(name="address") String address,
			@RequestParam(name="city") String city) throws IllegalArgumentException {
		Librarian l = service.createLibrarianOnlineAccount(oa, firstname, lastname, address, city);
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
