package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
public interface OnlineAccountRepository extends CrudRepository<OnlineAccount, Integer>{
	OnlineAccount findOnlineAccountByUsername(String username);
    OnlineAccount findOnlineAccountByUserID(Integer iD);
    OnlineAccount findOnlineAccountByEmail(String email);

    boolean existsOnlineAccountByUsername(String username);
    boolean existsOnlineAccountByUserID(Integer iD);
    boolean existsOnlineAccountByEmail(String email);
} 
