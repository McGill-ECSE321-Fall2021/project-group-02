package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.UserEntity;

public interface OnlineAccountRepository extends CrudRepository<OnlineAccount, Integer>{
	OnlineAccount findOnlineAccountByUsername(String username);
    OnlineAccount findOnlineAccountByUser(UserEntity userEntity);
    OnlineAccount findOnlineAccountByEmail(String email);

    boolean existsOnlineAccountByUsername(String username);
    boolean existsOnlineAccountByUser(UserEntity userEntity);
    boolean existsOnlineAccountByEmail(String email);
} 
