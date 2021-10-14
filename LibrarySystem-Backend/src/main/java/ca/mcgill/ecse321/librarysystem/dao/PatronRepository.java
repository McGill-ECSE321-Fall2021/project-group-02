package ca.mcgill.ecse321.librarysystem.dao;

import ca.mcgill.ecse321.librarysystem.model.Person;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Patron;

import java.util.List;

public interface PatronRepository extends CrudRepository<Patron, Integer>{
	Patron findPatronById(Integer id);
	List<Patron> findPatronByAddress(String address);
	Patron findPatronByPerson(Person person);

	boolean existsPatronById(Integer id);
	boolean existsPatronByAddress(String address);
	boolean existsPatronByPerson(Person person);
} 
