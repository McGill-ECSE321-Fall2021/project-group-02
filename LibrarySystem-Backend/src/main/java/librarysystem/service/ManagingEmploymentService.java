package librarysystem.service;

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
	 */
	@Transactional
	public Librarian hireLibrarianOnlineAccount(OnlineAccount oa, String fn, String ln, String ad, String city) {
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
	 */
	@Transactional
	public Librarian hireLibrarian(String fn, String ln, String ad, String city, String email, String username, String pswd) {
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
	 */
	@Transactional
	public void fireLibrarian(int id) {
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
	 */
	@Transactional
	public boolean librarianIsHired(int id) {
		return librarianRepository.existsById(id);
	}
	
	/**
	 * 
	 * @param id the id of the fired librarian
	 * @return if the fired librarian does not exist in the system
	 */
	@Transactional
	public boolean librarianIsFired(int id) {
		return !(librarianRepository.existsById(id));
	}
	
	/**
	 * 
	 * @param ws the weekly schedule assigned to the librarian
	 * @param id the id of the librarian
	 */
	@Transactional
	public void setWeeklySchedule(WeeklySchedule ws,int id) {
		if(librarianRepository.existsById(id)) {
			Librarian cur = librarianRepository.findLibrarianById(id);
			weeklyScheduleRepository.save(ws);
			cur.setWeeklySchedule(ws);
		}
		else throw new IllegalArgumentException("This librarian does not exist");
	}
	
	/**
	 * 
	 * @param id the id of the librarian
	 * @return the weekly schedule of the librarian with the id input
	 */
	@Transactional
	public WeeklySchedule getWeeklySchedule(int id) {
		if(librarianRepository.existsById(id)) {
			Librarian cur = librarianRepository.findLibrarianById(id);
			return cur.getWeeklySchedule();
		}
		else throw new IllegalArgumentException("This librarian does not exist");
	}

}
