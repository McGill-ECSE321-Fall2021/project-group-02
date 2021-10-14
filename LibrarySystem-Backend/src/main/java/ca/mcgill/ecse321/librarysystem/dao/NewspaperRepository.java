package ca.mcgill.ecse321.librarysystem.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Newspaper;

public interface NewspaperRepository extends CrudRepository<Newspaper, Integer>{
	List<Newspaper> findNewspaperByHeadline(String headline);
	List<Newspaper> findNewspaperByCompanyAndDate(String company, Date date);
	List<Newspaper> findNewspaperByCompany(String company);
	List<Newspaper> findNewspaperByDate(Date date);

	List<Newspaper> findNewspaperByIsArchived(boolean isArchived);
	List<Newspaper> findNewspaperByIsDamaged(boolean isDamaged);

	boolean existsNewspaperByHeadline(String headline);
	boolean existsNewspaperByCompanyAndDate(String company, Date date);
	boolean existsNewspaperByCompany(String company);
	boolean existsNewspaperByDate(Date date);
} 