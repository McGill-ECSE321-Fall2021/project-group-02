package ca.mcgill.ecse321.librarysystem.dao;

import ca.mcgill.ecse321.librarysystem.model.Person;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findUserById(Integer id);
	List<User> findUserByPerson(Person person);
	List<User> findUserByCity(String city);
	List<User> findUserByAddress(String address);

	boolean existsUserById(Integer id);
	boolean existsUserByPerson(Person person);
	boolean existsUserByCity(String city);
	boolean existsUserByAddress(String address);
} 
