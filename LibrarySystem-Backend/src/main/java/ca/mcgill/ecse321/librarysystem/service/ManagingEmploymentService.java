package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.model.*;
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
	
	/**
	 * 
	 * @param h id of the user
	 * @param oa the user's online account
	 * @param fn the user's first name
	 * @param ln the user's last name
	 * @param ad the user's address of residence
	 * @param city the user's city of residence
	 * @return the hired librarian
	 * @author vy-khahuynh
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
	 * @param h id of the user
	 * @param fn the user's first name
	 * @param ln the user's last name
	 * @param ad the user's address of residence
	 * @param city the suer's city of residence
	 * @param email the user's email address
	 * @param username the user's username
	 * @param pswd the user's password
	 * @return the hired librarian
	 * @author vy-khahuynh
	 */
	@Transactional
	public HeadLibrarian createHeadLibrarian(int h,String fn, String ln, String ad, String city) throws IllegalArgumentException{
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
			HeadLibrarian newHL = new HeadLibrarian();
			newHL.setFirstName(fn);
			newHL.setLastName(ln);
			newHL.setAddress(ad);
			newHL.setCity(city);
			newHL.setBalance(0);
			
			headLibrarianRepository.deleteById(h);
			headLibrarianRepository.save(newHL);
			
			return newHL;
		}
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @param h id of the user
	 * @param id the id of the librarian to be fired
	 * @author vy-khahuynh
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
	
	/**
	 * 
	 * @param h id of the user
	 * @param id the id of the hired librarian  
	 * @return if the hired librarian exists in the system
	 * @author vy-khahuynh
	 */
	@Transactional
	public boolean librarianIsHired(int h,int id) throws IllegalArgumentException{
		if(!(h > -1 && id > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
			return librarianRepository.existsLibrarianById(id);
		}
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @param h id of the user
	 * @param id the id of the fired librarian
	 * @return if the fired librarian does not exist in the system
	 * @author vy-khahuynh
	 */
	@Transactional
	public boolean librarianIsFired(int h,int id) throws IllegalArgumentException {
		if(!(h > -1 && id > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
			return !(librarianRepository.existsLibrarianById(id));
		}
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @param  id of the user
	 * @return list of all the librarians
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrarians(int h)throws IllegalArgumentException{
		if(!(h > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
	      return toList(librarianRepository.findAll());
	    }
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @param h id of the user
	 * @param firstName first name of the librarians
	 * @return list of librarians with the same first name
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByFirstName(int h,String firstName)throws IllegalArgumentException{
		error = "";
		if(!(h > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(!verifyStringLength(firstName)) error += "First name cannot be empty or too long.";
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
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
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @param h id of the user
	 * @param lastName last name of the librarians
	 * @return list of librarians with the same last name
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByLastName(int h,String lastName)throws IllegalArgumentException{
		error="";
		if(!(h > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(!verifyStringLength(lastName)) error += "Last name cannot be empty or too long.";
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
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
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @param h id of the user
	 * @param fn first name of the librarians
	 * @param ln last name of the librarians
	 * @return list of librarians with same first and last name
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByFirstAndLastName(int h,String fn,String ln)throws IllegalArgumentException{
		error="";
		if(!(h > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		
		if(!verifyStringLength(fn) || !verifyStringLength(ln)) {
			error += "First & last name cannot be empty or too long.";
		}
		
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
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
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
	}
	
	/**
	 * 
	 * @param h id of the user
	 * @param id id of the desired librarian
	 * @return the librarian with that id if they exist
	 * @author vy-khahuynh
	 */
	@Transactional
	public Librarian getLibrarianByID(int h,int id) throws IllegalArgumentException{
		if(!(h > -1 && id > -1)) {
			throw new IllegalArgumentException("IDs have to be positive.");
		}
		if(headLibrarianRepository.existsHeadLibrarianById(h)) {
			Librarian l = librarianRepository.findLibrarianById(id);
			if (l == null) {
				throw new IllegalArgumentException("No librarians with ID: " + id);
			}
			else return l;
	}
		else throw new IllegalArgumentException("Must be a head librarian to proceed.");
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
	
	private boolean verifyStringLength(String s) {
		if (s==null || s.trim().length()==0 || s.length() > 40) {
			return false;
		}
		return true;
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
