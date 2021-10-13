package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Journal;

public interface JournalRepository extends CrudRepository<Journal, String>{
	Journal findJournalByName(String name);
	List<Journal> findJournalByDate(Date date);
	
}
