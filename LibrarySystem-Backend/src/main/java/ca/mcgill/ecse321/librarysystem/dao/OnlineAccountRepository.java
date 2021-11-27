package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.UserEntity;

public interface OnlineAccountRepository extends CrudRepository<OnlineAccount, Integer>{
	OnlineAccount findOnlineAccountByUsername(String username);
    OnlineAccount findOnlineAccountByUserEntity(UserEntity userEntity);
    OnlineAccount findOnlineAccountByEmail(String email);
    OnlineAccount findOnlineAccountById(int id);
    OnlineAccount findOnlineAccountByLoggedInTrue();

    boolean existsOnlineAccountByUsername(String username);
    boolean existsOnlineAccountByUserEntity(UserEntity userEntity);
    boolean existsOnlineAccountByEmail(String email);
    boolean existsOnlineAccountById(int id);
} 
