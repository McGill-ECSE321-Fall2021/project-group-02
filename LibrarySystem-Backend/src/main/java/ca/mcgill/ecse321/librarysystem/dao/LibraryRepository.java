package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem.model.Library;


public interface LibraryRepository extends CrudRepository<Library, Integer>{
	Library findLibraryByOpeningHour(Time time);
	Library findLibraryByClosingHour(Time time);

	boolean existsLibraryByOpeningHour(Time time);
	boolean existsLibraryByClosingHour(Time time);
}
