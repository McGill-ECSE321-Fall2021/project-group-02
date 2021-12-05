package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.model.*;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;
import ca.mcgill.ecse321.librarysystem.dao.*;

@Service
public class ManagingEmploymentService {
	
	public String error;
	
	@Autowired
	LibrarianRepository librarianRepository;
	@Autowired
	HeadLibrarianRepository headLibrarianRepository;
	@Autowired
	OnlineAccountRepository onlineAccountRepository;
	@Autowired
	WeeklyScheduleRepository weeklyScheduleRepository;
	@Autowired
	DailyScheduleRepository dailyScheduleRepository;
	
	/**
	 * @author vy-khahuynh
	 * @param h id of the user
	 * @param oa the user's online account
	 * @param fn the user's first name
	 * @param ln the user's last name
	 * @param ad the user's address of residence
	 * @param city the user's city of residence
	 * @return the hired librarian
	 */
	@Transactional
	public Librarian createLibrarian(int h,String fn, String ln, String ad, String city) throws IllegalArgumentException{
		error = "";
		if(!(h > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(!verifyStringLength(fn)) error += "First name cannot be empty or too long.";
		if(!verifyStringLength(ln)) error += "Last name cannot be empty or too long.";
		if(!verifyStringLength(ad)) error += "Address cannot be empty or too long.";
		if(!verifyStringLength(city)) error += "City cannot be empty or too long.";
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
			
			Librarian hired = new Librarian();
			hired.setFirstName(fn);
			hired.setLastName(ln);
			hired.setAddress(ad);
			hired.setCity(city);
			hired.setBalance(0);
		
			librarianRepository.save(hired);
		
			return hired;
		}
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @author vy-khahuynh
	 * @param h current head librarian id
	 * @param fn user's first name
	 * @param ln user's last name
	 * @param ad user's address of residence
	 * @param city user's city
	 * @return new head librarian
	 * @throws IllegalArgumentException
	 */
	@Transactional
	public HeadLibrarian createHeadLibrarian() throws IllegalArgumentException{
			int found=0;
			HeadLibrarian newHL = new HeadLibrarian();
			for(HeadLibrarian hl : headLibrarianRepository.findAll()) {
				if(hl.getId()==newHL.getId()) {
					newHL=hl;
					found=1;
				}
			}
			
			if(found==0) {
				newHL.setFirstName("Robert");
				newHL.setLastName("Maxwell");
				newHL.setAddress("2770 Ran Avenue");
				newHL.setCity("Montreal");
				newHL.setBalance(0);
				headLibrarianRepository.save(newHL);
			}
			
			
			
			for(OnlineAccount oa : onlineAccountRepository.findAll()) {
				if(oa.getUsername().equals("robert1")) {
					return newHL;
				}
			}
			
			
			OnlineAccount headAccount=new OnlineAccount();
			headAccount.setEmail("robert@gmail.com");
			headAccount.setLoggedIn(false);
			headAccount.setPassword("fabulous");
			headAccount.setUsername("robert1");
			headAccount.setUser(newHL);
			onlineAccountRepository.save(headAccount);
			
			return newHL;
	}
	/**
	 * @author vy-khahuynh
	 * @param h id of the user
	 * @param id the id of the librarian to be fired
	 */
	@Transactional
	public void deleteLibrarian(int h, int id) throws IllegalArgumentException{
		if(!(h > -1 && id > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		
		
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
			if (librarianRepository.existsLibrarianById(id)) {
				Librarian fired = librarianRepository.findLibrarianById(id);
				if (fired.getWeeklySchedule() != null) {
					weeklyScheduleRepository.delete(fired.getWeeklySchedule());
				}
				librarianRepository.delete(fired);
			}
			else throw new IllegalArgumentException("This librarian does not exist!");
		}
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
//	/**
//	 * @author vy-khahuynh
//	 * @param h id of the user
//	 * @param id the id of the hired librarian  
//	 * @return if the hired librarian exists in the system
//	 */
//	@Transactional
//	public boolean librarianIsHired(int h,int id) throws IllegalArgumentException{
//		if(!(h > -1 && id > -1)) {
//			throw new IllegalArgumentException("IDs have to be positive.");
//		}
//		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
//			return librarianRepository.existsLibrarianById(id);
//		}
//		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
//	}
//	
//	/**
//	 * @author vy-khahuynh
//	 * @param h id of the user
//	 * @param id the id of the fired librarian
//	 * @return if the fired librarian does not exist in the system
//	 */
//	@Transactional
//	public boolean librarianIsFired(int h,int id) throws IllegalArgumentException {
//		if(!(h > -1 && id > -1)) {
//			throw new IllegalArgumentException("IDs have to be positive.");
//		}
//		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
//			return !(librarianRepository.existsLibrarianById(id));
//		}
//		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
//	}
	
	/**
	 * @author vy-khahuynh
	 * @param  id of the user
	 * @return list of all the librarians
	 */
	@Transactional
	public List<Librarian> getAllLibrarians()throws IllegalArgumentException{
	      return toList(librarianRepository.findAll());
	}
	
	/**
	 * @author vy-khahuynh
	 * @param h id of the user
	 * @param firstName first name of the librarians
	 * @return list of librarians with the same first name
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByFirstName(String firstName)throws IllegalArgumentException{
		error = "";
		if(!verifyStringLength(firstName)) error += "First name cannot be empty or too long.";
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		for(Librarian l : librarianRepository.findAll()) {
			if(l.getFirstName().equals(firstName)) {
				allLibrarians.add(l);
			}
		}			
		if(allLibrarians.size() == 0) {
			throw new IllegalArgumentException("No librarians with such first name.");
		}
		else return allLibrarians;
	}
	
	/**
	 * @author vy-khahuynh
	 * @param h id of the user
	 * @param lastName last name of the librarians
	 * @return list of librarians with the same last name
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByLastName(String lastName)throws IllegalArgumentException{
		error="";
		if(!verifyStringLength(lastName)) error += "Last name cannot be empty or too long.";
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		for(Librarian l : librarianRepository.findAll()) {
			if(l.getLastName().equals(lastName)) {
				allLibrarians.add(l);
			}
		}
			if(allLibrarians.size() == 0) {
				throw new IllegalArgumentException("No librarians with such last name.");
			}
			else return allLibrarians;
		}
	
	/**
	 * @author vy-khahuynh
	 * @param h id of the user
	 * @param fn first name of the librarians
	 * @param ln last name of the librarians
	 * @return list of librarians with same first and last name
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByFirstAndLastName(String fn,String ln)throws IllegalArgumentException{
		error="";
		if(!verifyStringLength(fn) || !verifyStringLength(ln)) {
			error += "First & last name cannot be empty or too long.";
		}
		
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		for(Librarian l : librarianRepository.findAll()) {
			if(l.getLastName().equals(ln) && l.getFirstName().equals(fn)) {
				allLibrarians.add(l);
			}
		}
		if(allLibrarians.size() == 0) {
			throw new IllegalArgumentException("No librarians with such first and last name.");
		}
		else return allLibrarians;
	}
	
	public List<Librarian> searchLibrarian(String name) throws IllegalArgumentException{
		error="";
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		
		if(!verifyStringLength(name) || !verifyStringLength(name)) {
			error += "First & last name cannot be empty or too long.";
		}
		
		if(name.isBlank()) {
			for(Librarian l : librarianRepository.findAll()) {
				allLibrarians.add(l);
			}
			return allLibrarians; 
		}
		
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		for(Librarian l : librarianRepository.findAll()) {
			String fullname = l.getFirstName() + " " + l.getLastName();
			if((fullname.toLowerCase()).contains(name.toLowerCase())) {
				allLibrarians.add(l);
			}
		}
		if(allLibrarians.size() == 0) {
			throw new IllegalArgumentException("No librarians with such first and last name.");
		}
		else return allLibrarians;
	}
	
	/**
	 * @author vy-khahuynh
	 * @param h id of the user
	 * @param id id of the desired librarian
	 * @return the librarian with that id if they exist	
	 */
	@Transactional
	public Librarian getLibrarianByID(int id) throws IllegalArgumentException{
			Librarian l = librarianRepository.findLibrarianById(id);
			if (l == null) {
				throw new IllegalArgumentException("No librarians with ID: " + id);
			}
			else return l;
	}
	
	/**
	 * @author vy-khahuynh
	 * @param h id of the user
	 * @param id id of the desired librarian
	 * @return the librarian with that id if they exist	
	 */
	@Transactional
	public List<Librarian> sortLibrarian(String mode) throws IllegalArgumentException{
		List<Librarian> libs = new ArrayList<Librarian>();
		List<Librarian> initial = new ArrayList<Librarian>();
		List<String> fn = new ArrayList<String>();
		List<String> ln = new ArrayList<String>();
		List<Integer> id = new ArrayList<Integer>();
		
		for(Librarian l : getAllLibrarians()) {
			fn.add(l.getFirstName());
			ln.add(l.getLastName());
			id.add(l.getId());
			initial.add(l);
		}
		
		switch(mode) {
		case "fAZ":  
			Collections.sort(fn);
			for(String s : fn) {
				for(Librarian l : initial) {
					if(l.getFirstName().equals(s) && id.contains(l.getId())) {
						libs.add(l);
						id.remove(Integer.valueOf(l.getId()));
					}
				}
			}
			return libs;
		case "fZA":
			Collections.sort(fn,Collections.reverseOrder());
			
			for(String s : fn) {
				for(Librarian l : initial) {
					if(l.getFirstName().equals(s) && id.contains(l.getId())) {
						libs.add(l);
						id.remove(Integer.valueOf(l.getId()));
					}
				}
			}
			return libs;
		case "lAZ":
			Collections.sort(ln);
			for(String s : ln) {
				for(Librarian l : initial) {
					if(l.getLastName().equals(s) && id.contains(l.getId())) {
						libs.add(l);
						id.remove(Integer.valueOf(l.getId()));
					}
				}
			}
			return libs;
		case "lZA":
			Collections.sort(ln,Collections.reverseOrder());
			
			for(String s : ln) {
				for(Librarian l : initial) {
					if(l.getLastName().equals(s) && id.contains(l.getId())) {
						libs.add(l);
						id.remove(Integer.valueOf(l.getId()));
					}
				}
			}
			return libs;
		case "12":
			Collections.sort(id);
			for(Integer i : id) {
				for(Librarian l : initial) {
					if(l.getId() == i) {
						libs.add(l);
					}
				}
			}
			return libs;
		case "21":
			Collections.sort(id,Collections.reverseOrder());
			
			for(Integer i : id) {
				for(Librarian l : initial) {
					if(l.getId() == i) {
						libs.add(l);
					}
				}
			}
			return libs;
			
		default: return initial;
		}
	}
	
	
	//  NOT PART OF USE CASE, KEEP JUST IN CASE
//	/**
//	 * 
//	 * @param h id of the user
//	 * @param ws the weekly schedule assigned to the librarian
//	 * @param id the id of the librarian
//	 * @author vy-khahuynh
//	 */
//	@Transactional
//	public void setWeeklySchedule(int h,WeeklySchedule ws,int id)throws IllegalArgumentException {
//		if(!(h > -1)) {
//			throw new IllegalArgumentException("IDs have to be positive.");
//		}
//		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
//		if(librarianRepository.existsById(id)) {
//			Librarian cur = librarianRepository.findLibrarianById(id);
//			weeklyScheduleRepository.save(ws);
//			cur.setWeeklySchedule(ws);
//		}
//		else throw new IllegalArgumentException("This librarian does not exist!");
//		}
//		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
//	}
//	
//	/**
//	 * 
//	 * @param h id of the user
//	 * @param id the id of the librarian
//	 * @return the weekly schedule of the librarian with the id input
//	 * @author vy-khahuynh
//	 */
//	@Transactional
//	public WeeklySchedule getWeeklySchedule(int h,int id)throws IllegalArgumentException {
//		if(!(h > -1)) {
//			throw new IllegalArgumentException("IDs have to be positive.");
//		}
//		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
//		if(librarianRepository.existsById(id)) {
//			Librarian cur = librarianRepository.findLibrarianById(id);
//			return cur.getWeeklySchedule();
//		}
//		else throw new IllegalArgumentException("This librarian does not exist!");
//	}
//	else throw new IllegalArgumentException("Must be a head librarian to proceed.");
//	}
//	
//	/**
//	 * 
//	 * @param h id of the user
//	 * @return list of every weekly schedule assigned to an employee
//	 * @author vy-khahuynh
//	 */
//	@Transactional
//	public List<WeeklySchedule> getAllWeeklySchedule(int h)throws IllegalArgumentException{
//		if(!(h > -1)) {
//			throw new IllegalArgumentException("IDs have to be positive.");
//		}
//		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
//		if(weeklyScheduleRepository.findAll() instanceof ArrayList) {
//			return (ArrayList<WeeklySchedule>) weeklyScheduleRepository.findAll();
//		}
//		else {
//			List<WeeklySchedule> ws = new ArrayList<WeeklySchedule>();
//			for(WeeklySchedule w : weeklyScheduleRepository.findAll()) {
//				ws.add(w);
//			}
//			return ws;
//		}
//	}
//		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
//	}
	
	@Transactional
	public List<WeeklySchedule> getWeeklySchedulesByID(int id)throws IllegalArgumentException {
		List<WeeklySchedule> newList = new ArrayList<WeeklySchedule>();
		
		if(librarianRepository.existsById(id)) {
			Librarian cur = librarianRepository.findLibrarianById(id);
			newList.add(cur.getWeeklySchedule());
			return newList;
		}
		else throw new IllegalArgumentException("This librarian does not exist!");
	
	}
	
	@Transactional
	public List<DailySchedule> getLibrarianDailySchedules(int id) throws IllegalArgumentException {
		if(librarianRepository.existsById(id)) {
			Librarian cur = librarianRepository.findLibrarianById(id);
			WeeklySchedule ws = cur.getWeeklySchedule();;
			return ws.getDailySchedules();
		}
		else throw new IllegalArgumentException("This librarian does not exist!");
	}
	
	@Transactional
	public DailySchedule createDailySchedule(int hid, int id, WeekDay day, Time startTime, Time endTime) {
		if(!(id > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		
		if(headLibrarianRepository.existsHeadLibrarianById(hid)) {
		if(librarianRepository.existsLibrarianById(id)) {
			DailySchedule ds = new DailySchedule();
			ds.setEndTime(endTime);
			ds.setStartTime(startTime);
			ds.setDay(day);
			
			Librarian l = librarianRepository.findLibrarianById(id);
			WeeklySchedule ws = l.getWeeklySchedule();
			List<DailySchedule> dailyschedule = ws.getDailySchedules();
			
			
			dailyschedule.add(ds);
			ws.setDay(dailyschedule);
			l.setWeeklySchedule(ws);
			
			dailyScheduleRepository.save(ds);
			return ds;
		} else {
			throw new IllegalArgumentException("Librarian does not exist.");
		}
		}
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	@Transactional
	public List<DailySchedule> getDailyScheduleById(int id) {

		List<DailySchedule> dailySchedule = new ArrayList<DailySchedule>();
		if(librarianRepository.existsById(id)) {
			Librarian l = librarianRepository.findLibrarianById(id);
			WeeklySchedule ws = l.getWeeklySchedule();
			dailySchedule.addAll(ws.getDailySchedules());
		}
		return dailySchedule;
	}
	
	@Transactional
	public WeeklySchedule createWeeklySchedule(int hID, Date startDate, Date endDate, int librarianID) throws IllegalArgumentException {
		if(!(hID > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(headLibrarianRepository.existsHeadLibrarianById(hID)) {
			if(librarianRepository.existsLibrarianById(librarianID)) {
				Librarian l = librarianRepository.findLibrarianById(librarianID);
				WeeklySchedule ws = new WeeklySchedule();
				ws.setStartDate(startDate);
				ws.setEndDate(endDate);
				weeklyScheduleRepository.save(ws);
				l.setWeeklySchedule(ws);
				librarianRepository.save(l);
				return ws;
			}
			else {
				throw new IllegalArgumentException("Librarian does not exist.");
			}
		}
		else {
			throw new IllegalArgumentException("Must be a head librarian to proceed.");
		}
		
	}
	
	/**
	 * @author stevencho
	 * @param s String to be analyzed
	 * @return if the string is valid
	 */
	private boolean verifyStringLength(String s) {
		if (s==null || s.trim().length()==0 || s.length() > 40) {
			return false;
		}
		return true;
	}
	
	/**
	 * @author ItemService
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	
}
