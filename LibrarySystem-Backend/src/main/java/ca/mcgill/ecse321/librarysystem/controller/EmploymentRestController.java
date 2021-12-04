package ca.mcgill.ecse321.librarysystem.controller;

import java.sql.Date;
import java.sql.Time;
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
import ca.mcgill.ecse321.librarysystem.dto.WeeklyScheduleDto;
import ca.mcgill.ecse321.librarysystem.dto.DailyScheduleDto;
import ca.mcgill.ecse321.librarysystem.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@CrossOrigin(origins = "*")
@RestController
public class EmploymentRestController {
	@Autowired
	ManagingEmploymentService service;
	
	/**
	 * Gets all librarians sharing the same first and last name
	 * @param firstname The first name of the librarian
	 * @param lastname The last name of the librarian
	 * 
	 * @return list of all librarians sharing the same first and last name
	 * 
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librariansfnln", "/librariansfnln/" })
	public List<LibrarianDto> getAllLibrariansByFirstAndLastName(@RequestParam (name="firstname") String fn,@RequestParam	 (name="lastname") String ln) throws IllegalArgumentException{
		return service.getAllLibrariansByFirstAndLastName(fn, ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * Gets all librarians in a sorted manner depending on the mode
	 * @param mode The mode used to sort the list of librarians
	 * 
	 * @return sorted list of all librarians depending on the mode
	 * 
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librariansSort", "/librariansSort/" })
	public List<LibrarianDto> getAllLibrariansByFirstAndLastName(@RequestParam (name="mode") String mode) throws IllegalArgumentException{
		return service.sortLibrarian(mode).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * Gets all librarians whose names match the search query 
	 * @param librarianName The name of the librarians that are we are searching for
	 * 
	 * @return list of all librarians matching the search query
	 * 
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarianSearch", "/librarianSearch/" })
	public List<LibrarianDto> searchLibrarian(@RequestParam (name="librarianName") String name) throws IllegalArgumentException{
		return service.searchLibrarian(name).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	

	/**
	 * Gets all librarians in the system
	 * 
	 * @return list of all librarians 
	 * 
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrarians() throws IllegalArgumentException{
		return service.getAllLibrarians().stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * Gets all librarians whose first names match the search query
	 * @param firstName The first name of the librarians to be searched
	 * 
	 * @return list of all librarians sharing the same first name
	 *
	 * @author Vy-Kha Huynh
	 */
	@GetMapping(value = { "/librariansfn", "/librariansfn/" })
	public List<LibrarianDto> getAllLibrariansByFirstName(@RequestParam (name="firstname") String fn) throws IllegalArgumentException{
		return service.getAllLibrariansByFirstName(fn).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * Gets all librarians whose last names match the search query
	 * @param lastname The last name of the librarians to be searched
	 *
	 * @return list of all librarians sharing the same last name
	 *
	 * @author Vy-Kha Huynh
	 */
	@GetMapping(value = { "/librariansln", "/librariansln/" })
	public List<LibrarianDto> getAllLibrariansByLastName(@RequestParam (name="lastname") String ln) throws IllegalArgumentException{
		return service.getAllLibrariansByLastName(ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * Gets the particular librarian associated with the ID
	 * @param libid The ID of the librarian to be searched
	 *
	 * @return Librarian that is being searched for
	 * 
	 * @author Vy-Kha Huynh
	 */
	@GetMapping(value= {"/librariansid" , "/librariansid/"})
	public LibrarianDto getLibrarianByID(@RequestParam(name="LibID") int libid) throws IllegalArgumentException{
		Librarian l = service.getLibrarianByID(libid);
		return convertToDto(l);
	}
	
	/**
	 * Gets the weekly schedule of the librarian associated with the ID
	 * @param libid The ID of the librarian to be searched
	 *
	 * @return List of librarian's weekly schedules
	 * 
	 * @author John Park
	 */
	
	@GetMapping(value= {"/schedulesweekly", "/schedulesweekly/" })
	public List<WeeklyScheduleDto> getAllWeeklySchedulesByID(@RequestParam(name="LibID") int libid) throws IllegalArgumentException {
		return service.getWeeklySchedulesByID(libid).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * Gets the daily schedules of the librarian associated with the ID
	 * @param libid The ID of the librarian to be searched
	 *
	 * @return List of librarian's daily schedules
	 * 
	 * @author John Park
	 */
	
	@GetMapping(value= {"/schedulesdaily" , "/schedulesdaily/"})
	public List<DailyScheduleDto> getDailySchedulesByID(@RequestParam(name="LibID")int libid) throws IllegalArgumentException{
		return service.getLibrarianDailySchedules(libid).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * Creates a librarian
	 * @param userID The ID of the user
	 * @param firstname The first name of the librarian 
	 * @param lastname The last name of the librarian
	 * @param address The address of librarian
	 * @param city The city of residence of the librarian
	 * @param email The email to be associated with the online account
	 * @param username The username to be associated with the online account
	 * @param password The password o be associated with the online account
	 *
	 * @return new librarian with the inputs as attributes
	 *
	 * @throws IllegalArgumentException
	 *
	 * @author Vy-Kha Huynh
	 */
	@PostMapping(value = { "/createLibrarian/{userID}/{firstname}/{lastname}/{address}/{city}", "/createLibrarian/{userID}/{firstname}/{lastname}/{address}/{city}/" })
	public LibrarianDto createLibrarian(@PathVariable(name="userID")int userID,@PathVariable(name="firstname") String firstname,
			@PathVariable(name="lastname") String lastname,@PathVariable(name="address") String address,
			@PathVariable(name="city") String city) throws IllegalArgumentException {
		Librarian l = service.createLibrarian(userID, firstname, lastname, address, city);
		return convertToDto(l);
	}
	
	/**
	 * Creates a daily schedule for the librarian
	 * @param userID The ID of the user
	 * @param librarianID The ID of the librarian 
	 * @param weekday The corresponding day of the daily schedule
	 * @param startTime The start time of the daily schedule
	 * @param endTime The end time of the daily schedule
	 *
	 * @return New daily schedule with the inputs as attributes
	 *
	 * @throws IllegalArgumentException
	 *
	 * @author John Park
	 */
	
	@PostMapping(value = { "/createDailySchedule/{userID}/{librarianID}/{weekday}/{startTime}/{endTime}", "/createDailySchedule/{userID}/{librarianID}/{weekday}/{startTime}/{endTime}/" })
	public DailyScheduleDto createDailySchedule(@PathVariable(name="userID") int userID, @PathVariable(name="librarianID")int librarianID, @PathVariable(name="weekday") WeekDay day, 
			@PathVariable(name="startTime") Time startTime, @PathVariable(name="endTime") Time endTime) throws IllegalArgumentException {
				DailySchedule ds = service.createDailySchedule(userID, librarianID, day, startTime, endTime);
				return convertToDto(ds);
			}
	
	/**
	 * Creates a weekly schedule for the librarian
	 * @param userID The ID of the user
	 * @param startDate The start date of the weekly schedule
	 * @param endDate The end date of the weekly scheudle
	 * @param librarianID The ID of the librarian 
	 *
	 * @return New weekly schedule with the inputs as attributes
	 *
	 * @throws IllegalArgumentException
	 *
	 * @author John Park
	 */
	
	@PostMapping(value = { "/createWeeklySchedule/{userID}/{startDate}/{endDate}/{librarianID}", "/createWeeklySchedule/{userID}/{startDate}/{endDate}/{librarianID}/" })
	public WeeklyScheduleDto createWeeklySchedule(@PathVariable(name="userID") int userID, @PathVariable(name="startDate") Date startDate, 
			@PathVariable(name="endDate") Date endDate, @PathVariable(name="librarianID") int librarianID) {
				WeeklySchedule ws = service.createWeeklySchedule(userID, startDate, endDate, librarianID);
				return convertToDto(ws);
	}
	
	/**
	 * Creates the head librarian with predefined attributes for the system
	 * 
	 * @return The head librarian of the library
	 *
	 * @throws IllegalArgumentException
	 *
	 * @author Vy-Kha Huynh
	 */
	
	@PostMapping(value = { "/createHeadLibrarian", "/createHeadLibrarian/" })
	public HeadLibrarianDto createHeadLibrarian() throws IllegalArgumentException {
		HeadLibrarian hl = service.createHeadLibrarian();
		return convertToDto(hl);
	}
	
	
	
	/**
	 * Deletes a librarian
	 * @param hlid The ID of the head librarian attempting to delete the librarian
	 * @param libid The ID of librarian to be deleted
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @author Vy-Kha Huynh
	 */
	
	@DeleteMapping(value = {"/deleteLibrarian/{userID}","/deleteLibrarian/{userID}/"})
	public void deleteLibrarian(@PathVariable(name="userID") int hlid,@RequestParam(name="LibID") int libid) throws IllegalArgumentException {
		service.deleteLibrarian(hlid, libid);
	}
	
	/**
	 * Converts Librarian object to LibrarianDto object
	 * @param l Librarian object to be converted to librarianDto
	 * 
	 * @return librarianDto object of librarian l
	 *
	 * @author Vy-Kha Huynh
	 */
	
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		LibrarianDto lDto = new LibrarianDto(l.getOnlineAccount(),l.getFirstName(),l.getLastName(),l.getAddress(),l.getCity(),l.getBalance(),l.getWeeklySchedule(),l.getId());
		return lDto;	
	}
	
	/**
	 * Converts HeadLibrarian object to HeadLibrarianDto object
	 * @param hl HeadLibrarian object to be converted to HeadLibrarianDto
	 * 
	 * @return HeadLibrarianDto object of HeadLibrarian hl
	 *
	 * @author Vy-Kha Huynh
	 */
	
	private HeadLibrarianDto convertToDto(HeadLibrarian hl) {
		if (hl == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
		}
		HeadLibrarianDto hlDto = new HeadLibrarianDto(hl.getOnlineAccount(),hl.getFirstName(),hl.getLastName(),hl.getAddress(),hl.getCity(),hl.getBalance(),hl.getWeeklySchedule(),hl.getId());
		return hlDto;	
	}
	
	/**
	 * Converts DailySchedule object to DailyScheduleDto object
	 * @param ds DailySchedule object to be converted to DailyScheduleDto object
	 * 
	 * @return DailyScheduleDto object of DailySchedule ds
	 *
	 * @author John Park
	 */
	
	private DailyScheduleDto convertToDto(DailySchedule ds) {
		if (ds == null) {
			throw new IllegalArgumentException("Daily Schedule does not exist.");
		}
		DailyScheduleDto dsDto = new DailyScheduleDto(ds.getDay(), ds.getStartTime(), ds.getEndTime(), ds.getId());
		return dsDto;	
	}
	
	/**
	 * Converts WeeklySchedule object to WeeklyScheduleDto object
	 * @param ws WeeklySchedule object to be converted to WeeklyScheduleDto object
	 * 
	 * @return WeeklyScheduleDto object of WeeklySchedule ds
	 *
	 * @author John Park
	 */
	
	private WeeklyScheduleDto convertToDto(WeeklySchedule ws) {
		if (ws == null) {
			throw new IllegalArgumentException("Daily Schedule does not exist.");
		}
		WeeklyScheduleDto wsDto = new WeeklyScheduleDto(ws.getStartDate(), ws.getEndDate(), ws.getDailySchedules(), ws.getId());
		return wsDto;	
	}
}
