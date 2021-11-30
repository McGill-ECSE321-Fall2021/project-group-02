package ca.mcgill.ecse321.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.model.Newspaper;
import ca.mcgill.ecse321.librarysystem.model.Patron;

@Service
public class PatronService {
	
	@Autowired 
	PatronRepository patronRepository;
	
	/**
	 * Gets a list of all of the patrons
	 * @return List of all patrons
	 * 
	 * @author Niilo
	 */
	@Transactional
	public List<Patron> getAllPatrons(){
		return toList(patronRepository.findAll());
	}
	
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
