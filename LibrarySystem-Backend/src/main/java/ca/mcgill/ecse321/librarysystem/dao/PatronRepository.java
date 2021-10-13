package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Patron;
public interface PatronRepository extends CrudRepository<Patron, Integer>{
	Patron findPatronByName(String name);
} 
