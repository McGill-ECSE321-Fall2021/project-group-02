package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findUserByID(Integer iD);
	List<User> findUserByFirstAndLastName(String firstName, String lastName);
	List<User> findUserByFirstName(String firstName);
	List<User> findUserByLastName(String lastName);
	List<User> findUserByCity(String city);
	List<User> findUserByAddress(String address);

	boolean existsUserByID(Integer iD);
	boolean existsUserByFirstAndLastName(String firstName, String lastName);
	boolean existsUserByFirstName(String firstName);
	boolean existsUserByLastName(String lastName);
	boolean existsUserByCity(String city);
	boolean existsUserByAddress(String address);
} 
