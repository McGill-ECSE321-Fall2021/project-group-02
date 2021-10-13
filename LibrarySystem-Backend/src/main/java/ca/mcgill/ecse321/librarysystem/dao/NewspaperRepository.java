package ca.mcgill.ecse321.librarysystem.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Newspaper;

public interface NewspaperRepository extends CrudRepository<Newspaper, Integer>{
	Newspaper findNewspaperByName(String name);
} 