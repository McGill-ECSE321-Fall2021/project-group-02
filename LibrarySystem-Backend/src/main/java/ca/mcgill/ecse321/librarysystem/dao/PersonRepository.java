package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer>{
	List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);
	List<Person> findPersonByFirstName(String firstName);
	List<Person> findPersonByLastName(String lastName);

	boolean existsPersonByFirstNameAndLastName(String firstName, String lastName);
	boolean existsPersonByFirstName(String firstName);
	boolean existsPersonByLastName(String lastName);
} 
