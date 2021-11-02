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
public class HeadLibrarianRestController {
	@Autowired
	ManagingEmploymentService service;
	
	@Autowired
	LibrarianRepository librarians;

	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrarians() {
		return service.getAllLibrarians().stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/librarians", "/librarians" })
	public LibrarianDto createLibrarian(@RequestParam(name="firstname") String firstname,@RequestParam(name="lastname") String lastname,@RequestParam(name="address") String address,
			@RequestParam(name="city") String city,@RequestParam(name="email") String email,@RequestParam(name="username") String username,
			@RequestParam(name="password") String password) throws IllegalArgumentException {
		Librarian l = service.createLibrarian(firstname, lastname, address, city, email, username, password);
		return convertToDto(l);
	}
	
	@PostMapping(value = { "/librarians", "/librarians" })
	public LibrarianDto createLibrarianOnlineAccount(@RequestParam(name="onlineaccount") OnlineAccount oa,@RequestParam(name="firstname") String firstname,@RequestParam(name="lastname") String lastname,@RequestParam(name="address") String address,
			@RequestParam(name="city") String city) throws IllegalArgumentException {
		Librarian l = service.createLibrarianOnlineAccount(oa, firstname, lastname, address, city);
		return convertToDto(l);
	}
	
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		LibrarianDto lDto = new LibrarianDto(l.getOnlineAccount(),l.getFirstName(),l.getLastName(),l.getAddress(),l.getCity(),l.getBalance(),l.getWeeklySchedule());
		return lDto;	
	}
}
