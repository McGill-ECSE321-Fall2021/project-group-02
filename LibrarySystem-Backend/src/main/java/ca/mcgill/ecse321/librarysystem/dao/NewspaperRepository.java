package ca.mcgill.ecse321.librarysystem.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Newspaper;

public interface NewspaperRepository extends CrudRepository<Newspaper, Integer>{
	Newspaper findNewspaperByNameAndDate(String name, Date date);
	List<Newspaper> findNewspaperByName(String name);
	List<Newspaper> findNewspaperByDate(Date date);

	List<Newspaper> findNewspaperByIsArchived(boolean isArchived);
	List<Newspaper> findNewspaperByIsDamaged(boolean isDamaged);

	boolean existsNewspaperByNameAndDate(String name, Date date);
	boolean existsNewspaperByName(String name);
	boolean existsNewspaperByDate(Date date);
} 