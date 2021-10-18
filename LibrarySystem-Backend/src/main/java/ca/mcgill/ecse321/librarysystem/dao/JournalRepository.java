package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Journal;

public interface JournalRepository extends CrudRepository<Journal, Integer>{
	Journal findJournalByNameAndDate(String name, Date date);
	Journal findJournalById(int id);
	List<Journal> findJournalByName(String name);
	List<Journal> findJournalByDate(Date date);

	List<Journal> findJournalByIsArchived(boolean isArchived);
	List<Journal> findJournalByIsBorrowed(boolean isBorrowed);
	List<Journal> findJournalByIsDamaged(boolean isDamaged);
	
	boolean existsJournalByNameAndDate(String name, Date date);
	boolean existsJournalByName(String name);
	boolean existsJournalByDate(Date date);
}
