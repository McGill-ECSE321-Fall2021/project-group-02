package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Person;
public interface PersonRepository extends CrudRepository<Person, Integer>{
	Person findPersonByName(String name);
} 
