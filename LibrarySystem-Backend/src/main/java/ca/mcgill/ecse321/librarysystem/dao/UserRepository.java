package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findUserByName(String name);
} 
