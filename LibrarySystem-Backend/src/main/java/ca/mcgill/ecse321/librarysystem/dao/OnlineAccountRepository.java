package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
public interface OnlineAccountRepository extends CrudRepository<OnlineAccount, Integer>{
	
} 
