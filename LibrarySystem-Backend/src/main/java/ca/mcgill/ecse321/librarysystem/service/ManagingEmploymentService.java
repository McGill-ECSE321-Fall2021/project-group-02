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
	
	@Autowired
	LibrarianRepository librarianRepository;
	@Autowired
	OnlineAccountRepository onlineAccountRepository;
	@Autowired
	WeeklyScheduleRepository weeklyScheduleRepository;
	
	/**
	 * 
	 * @param oa the user's online account
	 * @param fn the suer's first name
	 * @param ln the user's last name
	 * @param ad the user's address of residence
	 * @param city the user's city of residence
	 * @return the hired librarian
	 * @author vy-khahuynh
	 */
	@Transactional
	public Librarian createLibrarianOnlineAccount(OnlineAccount oa, String fn, String ln, String ad, String city) {
		Librarian hired = new Librarian();
		hired.setOnlineAccount(oa);
		hired.setFirstName(fn);
		hired.setLastName(ln);
		hired.setAddress(ad);
		hired.setCity(city);
		hired.setBalance(0);
		
		librarianRepository.save(hired);
		onlineAccountRepository.save(oa);
		
		return hired;	
	}
	
	/**
	 * 
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
	public Librarian createLibrarian(String fn, String ln, String ad, String city, String email, String username, String pswd) {
		OnlineAccount oa = new OnlineAccount();
		oa.setEmail(email);
		oa.setUsername(username);
		oa.setPassword(pswd);
		
		Librarian hired = new Librarian();
		hired.setOnlineAccount(oa);
		hired.setFirstName(fn);
		hired.setLastName(ln);
		hired.setAddress(ad);
		hired.setCity(city);
		hired.setBalance(0);
		
		librarianRepository.save(hired);
		
		oa.setUser(hired);
		
		onlineAccountRepository.save(oa);
		
		return hired;
	}
	
	/**
	 * 
	 * @param id the id of the librarian to be fired
	 * @author vy-khahuynh
	 */
	@Transactional
	public void deleteLibrarian(int id) {
		if (librarianRepository.existsById(id)) {
			Librarian fired = librarianRepository.findLibrarianById(id);
			weeklyScheduleRepository.delete(fired.getWeeklySchedule());
			librarianRepository.delete(fired);
		}
	}
	
	/**
	 * 
	 * @param id the id of the hired librarian  
	 * @return if the hired librarian exists in the system
	 * @author vy-khahuynh
	 */
	@Transactional
	public boolean librarianIsHired(int id) {
		return librarianRepository.existsById(id);
	}
	
	/**
	 * 
	 * @param id the id of the fired librarian
	 * @return if the fired librarian does not exist in the system
	 * @author vy-khahuynh
	 */
	@Transactional
	public boolean librarianIsFired(int id) {
		return !(librarianRepository.existsById(id));
	}
	
	/**
	 * 
	 * @return list of all the librarians
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrarians(){
	    if(librarianRepository.findAll() instanceof ArrayList) {
	        return (ArrayList<Librarian>) librarianRepository.findAll();
	      }
	    else {
	      List<Librarian> librarianList = new ArrayList<Librarian>();
	      if(librarianRepository.findAll() != null) {
	        for(Librarian e: librarianRepository.findAll()) {
	        	librarianList.add(e);
	        }
	      }
	      return librarianList;
	    }
	}
	
	/**
	 * 
	 * @param firstName first name of the librarians
	 * @return list of librarians with the same first name
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByFirstName(String firstName){
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		for(Librarian l : getAllLibrarians()) {
			if(l.getFirstName().equals(firstName)) {
				allLibrarians.add(l);
			}
		}
		return allLibrarians;
	}
	
	/**
	 * 
	 * @param lastName last name of the librarians
	 * @return list of librarians with the same last name
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByLastName(String lastName){
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		for(Librarian l : getAllLibrarians()) {
			if(l.getLastName().equals(lastName)) {
				allLibrarians.add(l);
			}
		}
		return allLibrarians;
	}
	
	/**
	 * 
	 * @param fn first name of the librarians
	 * @param ln last name of the librarians
	 * @return list of librarians with same first and last name
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<Librarian> getAllLibrariansByFirstAndLastName(String fn,String ln){
		List<Librarian> allLibrarians = new ArrayList<Librarian>();
		for(Librarian l : getAllLibrarians()) {
			if(l.getLastName().equals(ln) && l.getFirstName().equals(fn)) {
				allLibrarians.add(l);
			}
		}
		return allLibrarians;
	}
	
	/**
	 * 
	 * @param id id of the desired librarian
	 * @return the librarian with that id if they exist
	 * @author vy-khahuynh
	 */
	@Transactional
	public Optional<Librarian> getLibrarianByID(int id) {
		Optional<Librarian> l = librarianRepository.findById(id);
		return l;
	}
	
	/**
	 * 
	 * @param ws the weekly schedule assigned to the librarian
	 * @param id the id of the librarian
	 * @author vy-khahuynh
	 */
	@Transactional
	public void setWeeklySchedule(WeeklySchedule ws,int id) {
		if(librarianRepository.existsById(id)) {
			Librarian cur = librarianRepository.findLibrarianById(id);
			weeklyScheduleRepository.save(ws);
			cur.setWeeklySchedule(ws);
		}
		else throw new IllegalArgumentException("This librarian does not exist!");
	}
	
	/**
	 * 
	 * @param id the id of the librarian
	 * @return the weekly schedule of the librarian with the id input
	 * @author vy-khahuynh
	 */
	@Transactional
	public WeeklySchedule getWeeklySchedule(int id) {
		if(librarianRepository.existsById(id)) {
			Librarian cur = librarianRepository.findLibrarianById(id);
			return cur.getWeeklySchedule();
		}
		else throw new IllegalArgumentException("This librarian does not exist!");
	}
	
	/**
	 * 
	 * @return list of every weekly schedule assigned to an employee
	 * @author vy-khahuynh
	 */
	@Transactional
	public List<WeeklySchedule> getAllWeeklySchedule(){
		if(weeklyScheduleRepository.findAll() instanceof ArrayList) {
			return (ArrayList<WeeklySchedule>) weeklyScheduleRepository.findAll();
		}
		else {
			List<WeeklySchedule> ws = new ArrayList<WeeklySchedule>();
			for(WeeklySchedule w : weeklyScheduleRepository.findAll()) {
				ws.add(w);
			}
			return ws;
		}
	}

}
