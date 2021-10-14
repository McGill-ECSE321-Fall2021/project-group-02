package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Patron;
public interface PatronRepository extends CrudRepository<Patron, Integer>{
	Patron findPatronByID(Integer iD);
	List<Patron> findPatronByAddress(String address);
	List<Patron> findPatronByFirstAndLastName(String firstName, String lastName);
	List<Patron> findPatronByFirstName(String firstName);
	List<Patron> findPatronByLastName(String lastName);

	boolean existsPatronByID(Integer iD);
	boolean existsPatronByAddress(String address);
	boolean existsPatronByFirstAndLastName(String firstName, String lastName);
	boolean existsPatronByFirstName(String firstName);
	boolean existsPatronByLastName(String lastName);
} 
