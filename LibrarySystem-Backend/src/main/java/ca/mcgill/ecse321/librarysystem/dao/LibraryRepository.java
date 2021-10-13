package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Library;

public interface LibraryRepository extends CrudRepository<Library, String>{
	Library findLibraryByOpeningHour(Time time);
	Library findLibraryByClosingHour(Time time);
}
