package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findUserByID(Integer iD);
	List<User> findUserByFirstAndLastName(String firstName, String lastName);
	List<User> findUserByFirstName(String firstName);
	List<User> findUserByLastName(String lastName);
	List<User> findUserByRole(String role);

	boolean existsUserByID(Integer iD);
	boolean existsUserByFirstAndLastName(String firstName, String lastName);
	boolean existsUserByFirstName(String firstName);
	boolean existsUserByLastName(String lastName);
	boolean existsUserByRole(String role);
} 
