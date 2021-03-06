package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.dao.UserEntityRepository;
import ca.mcgill.ecse321.librarysystem.dto.PatronDto;
import ca.mcgill.ecse321.librarysystem.model.Patron;
import ca.mcgill.ecse321.librarysystem.service.PatronService;

@CrossOrigin(origins = "*")
@RestController
public class PatronRestController {
	@Autowired
	PatronService patronService;
	
	@Autowired
	UserEntityRepository userRepository;
	
	@Autowired
	PatronRepository patronRepository;
	
	/**
	 * Gets a list of all the patrons in the library software system
	 *
	 * @return List of all Patron DTOs
	 * 
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/patrons", "/patrons" })
	public List<PatronDto> getAllPatrons() {
		return patronService.getAllPatrons().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Converts Patron object to PatronDto object
	 *
	 * @param p The Patron object to be converted
	 *
	 * @return The PatronDto object of the patron
	 * 
	 * @author Niilo Vuokila
	 */
	
	private PatronDto convertToDto(Patron p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Patron!");
		}
		PatronDto patronDto = new PatronDto(p.getId(),p.getAddress(),p.getCity(),p.getFirstName(),p.getLastName(),p.getBalance());
		return patronDto;
	}
}
