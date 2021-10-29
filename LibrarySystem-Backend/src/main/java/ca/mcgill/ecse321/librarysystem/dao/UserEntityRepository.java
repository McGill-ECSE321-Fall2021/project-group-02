package ca.mcgill.ecse321.librarysystem.dao;

import ca.mcgill.ecse321.librarysystem.model.Person;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.UserEntity;

import java.util.List;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer>{
	UserEntity findUserEntityById(Integer id);
	List<UserEntity> findUserEntityByPerson(Person person);
	List<UserEntity> findUserEntityByCity(String city);
	List<UserEntity> findUserEntityByAddress(String address);

	boolean existsUserEntityById(Integer id);
	boolean existsUserEntityByPerson(Person person);
	boolean existsUserEntityByCity(String city);
	boolean existsUserEntityByAddress(String address);
} 
