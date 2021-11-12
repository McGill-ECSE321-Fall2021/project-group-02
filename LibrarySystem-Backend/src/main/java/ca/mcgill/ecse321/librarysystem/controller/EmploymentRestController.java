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

import ca.mcgill.ecse321.librarysystem.service.ManagingEmploymentService;
import ca.mcgill.ecse321.librarysystem.dto.LibrarianDto;
import ca.mcgill.ecse321.librarysystem.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.Librarian;

@CrossOrigin(origins = "*")
@RestController
public class EmploymentRestController {
	@Autowired
	ManagingEmploymentService service;
	
	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians/{userID}/{firstname}/{lastname}", "/librarians/{userID}/{firstname}/{lastname}/" })
	public List<LibrarianDto> getAllLibrariansByFirstAndLastName(@PathVariable(name="userID")int id,@PathVariable (name="firstname") String fn,@PathVariable(name="lastname") String ln) throws IllegalArgumentException{
		return service.getAllLibrariansByFirstAndLastName(id, fn, ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	

	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians/{userID}", "/librarians/{userID}/" })
	public List<LibrarianDto> getAllLibrarians(@PathVariable(name="userID") int id) {
		return service.getAllLibrarians(id).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @param fn first name to be searched
	 * @return list of all librarians sharing the same first name
	 * 
	 */
	@GetMapping(value = { "/librarians/{userID}{firstname}", "/librarians/{userID}{firstname}/" })
	public List<LibrarianDto> getAllLibrariansByFirstName(@PathVariable(name="userID")int id,@PathVariable (name="firstname") String fn) throws IllegalArgumentException{
		return service.getAllLibrariansByFirstName(id, fn).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @param ln last name to be searched
	 * @return list of all librarians sharing the same last name
	 */
	@GetMapping(value = { "/librarians/{userID}/{lastname}", "/librarians/{userID}/{lastname}/" })
	public List<LibrarianDto> getAllLibrariansByLastName(@PathVariable(name="userID")int id,@PathVariable (name="lastname") String ln) throws IllegalArgumentException{
		return service.getAllLibrariansByLastName(0, ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param hlid id of headlibrarian
	 * @param libid id of librarian to be searched
	 * @return librarian dto object
	 */
	@GetMapping(value= {"/librarians/{userID}/{LibID}" , "/librarians/{userID}/{LibID}/"})
	public LibrarianDto getLibrarianByID(@PathVariable(name="userID") int hlid,@PathVariable(name="LibID") int libid) throws IllegalArgumentException{
		Librarian l = service.getLibrarianByID(hlid, libid);
		return convertToDto(l);
	}
	
	/**
	 * @author vy-khahuynh
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
	 */
	@PostMapping(value = { "/createLibrarian/{userID}/{firstname}/{lastname}/{address}/{city}", "/createLibrarian/{userID}/{firstname}/{lastname}/{address}/{city}/" })
	public LibrarianDto createLibrarian(@PathVariable(name="userID")int userID,@PathVariable(name="firstname") String firstname,
			@PathVariable(name="lastname") String lastname,@PathVariable(name="address") String address,
			@PathVariable(name="city") String city) throws IllegalArgumentException {
		Librarian l = service.createLibrarian(userID, firstname, lastname, address, city);
		return convertToDto(l);
	}
	
	/**
	 * @author vy-khahuynh
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param city
	 * @param email
	 * @param username
	 * @param password
	 * @return
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createHeadLibrarian/{firstname}/{lastname}/{address}/{city}", "/createHeadLibrarian/{firstname}/{lastname}/{address}/{city}/" })
	public HeadLibrarianDto createHeadLibrarian(@PathVariable(name="firstname") String firstname,
			@PathVariable(name="lastname") String lastname,@PathVariable(name="address") String address,
			@PathVariable(name="city") String city) throws IllegalArgumentException {
		HeadLibrarian hl = service.createHeadLibrarian(firstname, lastname, address, city);
		return convertToDto(hl);
	}
	
	/**
	 * @author vy-khahuynh
	 * @param hlid head librarian id attempting to delete the librarian
	 * @param libid id of librarian to be deleted
	 */
	@DeleteMapping(value = {"/deleteLibrarian/{userID}","/deleteLibrarian/{userID}/"})
	public void deleteLibrarian(@PathVariable(name="userID") int hlid,@RequestParam(name="LibID") int libid) throws IllegalArgumentException {
		service.deleteLibrarian(hlid, libid);
	}
	
	/**
	 * @author vy-khahuynh
	 * @param l librarian object to be converted to librarianDto
	 * @return librarianDto object of librarian l
	 */
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		LibrarianDto lDto = new LibrarianDto(l.getOnlineAccount(),l.getFirstName(),l.getLastName(),l.getAddress(),l.getCity(),l.getBalance(),l.getWeeklySchedule(),l.getId());
		return lDto;	
	}
	
	/**
	 * @author vy-khahuynh
	 * @param l librarian object to be converted to librarianDto
	 * @return librarianDto object of librarian l
	 */
	private HeadLibrarianDto convertToDto(HeadLibrarian hl) {
		if (hl == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		HeadLibrarianDto hlDto = new HeadLibrarianDto(hl.getOnlineAccount(),hl.getFirstName(),hl.getLastName(),hl.getAddress(),hl.getCity(),hl.getBalance(),hl.getWeeklySchedule(),hl.getId());
		return hlDto;	
	}
}
