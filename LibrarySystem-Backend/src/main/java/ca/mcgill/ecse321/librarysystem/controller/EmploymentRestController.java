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
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librariansfnln", "/librariansfnln/" })
	public List<LibrarianDto> getAllLibrariansByFirstAndLastName(@RequestParam (name="firstname") String fn,@RequestParam	 (name="lastname") String ln) throws IllegalArgumentException{
		return service.getAllLibrariansByFirstAndLastName(fn, ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	

	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarianssort", "/librarianssort/" })
	public List<LibrarianDto> getAllLibrariansByFirstAndLastName(@RequestParam (name="mode") String mode) throws IllegalArgumentException{
		return service.sortLibrarian(mode).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarianSearch", "/librarianSearch/" })
	public List<LibrarianDto> searchLibrarian(@RequestParam (name="librarianName") String name) throws IllegalArgumentException{
		return service.searchLibrarian(name).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	


	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @return list of all librarians sharing the same first and last name
	 * @author vy-khahuynh
	 */
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<LibrarianDto> getAllLibrarians() {
		return service.getAllLibrarians().stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @param fn first name to be searched
	 * @return list of all librarians sharing the same first name
	 * 
	 */
	@GetMapping(value = { "/librariansfn", "/librariansfn/" })
	public List<LibrarianDto> getAllLibrariansByFirstName(@RequestParam (name="firstname") String fn) throws IllegalArgumentException{
		return service.getAllLibrariansByFirstName(fn).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param id id of the user
	 * @param ln last name to be searched
	 * @return list of all librarians sharing the same last name
	 */
	@GetMapping(value = { "/librariansln", "/librariansln/" })
	public List<LibrarianDto> getAllLibrariansByLastName(@RequestParam (name="lastname") String ln) throws IllegalArgumentException{
		return service.getAllLibrariansByLastName(ln).stream().map(l -> convertToDto(l)).collect(Collectors.toList());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param hlid id of headlibrarian
	 * @param libid id of librarian to be searched
	 * @return librarian dto object
	 */
	@GetMapping(value= {"/librariansid" , "/librariansid/"})
	public LibrarianDto getLibrarianByID(@RequestParam(name="LibID") int libid) throws IllegalArgumentException{
		Librarian l = service.getLibrarianByID(libid);
		return convertToDto(l);
	}
	
	@GetMapping(value= {"/schedules", "/schedules/" })
	public WeeklyScheduleDto getWeeklyScheduleByID(@RequestParam(name="LibID") int libid) throws IllegalArgumentException {
		WeeklySchedule ws = service.getWeeklyScheduleByID(libid);
		return convertToDto(ws);
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
	
	@PostMapping(value = { "/createDailySchedule/{userID}/{librarianID}/{weekday}/{startTime}/{endTime}", "/createDailySchedule/{userID}/{librarianID}/{weekday}/{startTime}/{endTime}/" })
	public DailyScheduleDto createDailySchedule(@PathVariable(name="userID") int userID, @PathVariable(name="librarianID")int librarianID, @PathVariable(name="weekday") WeekDay day, 
			@PathVariable(name="startTime") Time startTime, @PathVariable(name="endTime") Time endTime) throws IllegalArgumentException {
				DailySchedule ds = service.createDailySchedule(userID, librarianID, day, startTime, endTime);
				return convertToDto(ds);
			}
	
	
	
	@PostMapping(value = { "/createWeeklySchedule/{userID}/{startDate}/{endDate}/{librarianID}", "/createWeeklySchedule/{userID}/{startDate}/{endDate}/{librarianID}/" })
	public WeeklyScheduleDto createWeeklySchedule(@PathVariable(name="userID") int userID, @PathVariable(name="startDate") Date startDate, 
			@PathVariable(name="endDate") Date endDate, @PathVariable(name="librarianID") int librarianID) {
				WeeklySchedule ws = service.createWeeklySchedule(userID, startDate, endDate, librarianID);
				return convertToDto(ws);
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
	
	private DailyScheduleDto convertToDto(DailySchedule ds) {
		if (ds == null) {
			throw new IllegalArgumentException("Daily Schedule does not exist.");
		}
		DailyScheduleDto dsDto = new DailyScheduleDto(ds.getDay(), ds.getStartTime(), ds.getEndTime(), ds.getId());
		return dsDto;	
	}
	
	private WeeklyScheduleDto convertToDto(WeeklySchedule ws) {
		if (ws == null) {
			throw new IllegalArgumentException("Daily Schedule does not exist.");
		}
		WeeklyScheduleDto wsDto = new WeeklyScheduleDto(ws.getStartDate(), ws.getEndDate(), ws.getDailySchedules(), ws.getId());
		return wsDto;	
	}
}
