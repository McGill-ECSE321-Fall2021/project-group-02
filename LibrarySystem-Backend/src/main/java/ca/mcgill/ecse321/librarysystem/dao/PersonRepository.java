package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Person;
public interface PersonRepository extends CrudRepository<Person, Integer>{
	List<Person> findPersonByFirstAndLastName(String firstName, String lastName);
	List<Person> findPersonByFirstName(String firstName);
	List<Person> findPersonByLastName(String lastName);

	boolean existsPersonByFirstAndLastName(String firstName, String lastName);
	boolean existsPersonByFirstName(String firstName);
	boolean existsPersonByLastName(String lastName);
} 
