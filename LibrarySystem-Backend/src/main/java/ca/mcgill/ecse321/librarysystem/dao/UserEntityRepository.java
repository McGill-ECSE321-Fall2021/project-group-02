package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.UserEntity;

import java.util.List;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer>{
	UserEntity findUserEntityById(Integer id);
	List<UserEntity> findUserEntityByCity(String city);
	List<UserEntity> findUserEntityByAddress(String address);

	boolean existsUserEntityById(Integer id);
	boolean existsUserEntityByCity(String city);
	boolean existsUserEntityByAddress(String address);
} 
