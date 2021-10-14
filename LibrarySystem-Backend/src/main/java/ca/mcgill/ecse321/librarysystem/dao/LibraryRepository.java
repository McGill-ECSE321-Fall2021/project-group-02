package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Library;

public interface LibraryRepository extends CrudRepository<Library, String>{
	Library findLibraryByName(String name);
	List<Library> findLibraryByOpeningHour(Time time);
	List<Library> findLibraryByClosingHour(Time time);
	List<Library> findLibraryByLocation(String location);

	boolean existsLibraryByName(String name);
	boolean existsLibraryByOpeningHour(Time time);
	boolean existsLibraryByClosingHour(Time time);
	boolean existsLibraryByLocation(String location);
}
