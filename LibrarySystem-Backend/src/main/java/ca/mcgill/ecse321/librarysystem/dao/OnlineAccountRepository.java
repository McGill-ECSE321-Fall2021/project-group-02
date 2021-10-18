package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.User;

@Repository
public interface OnlineAccountRepository extends CrudRepository<OnlineAccount, Integer>{
	OnlineAccount findOnlineAccountByUsername(String username);
    OnlineAccount findOnlineAccountByUser(User user);
    OnlineAccount findOnlineAccountByEmail(String email);

    boolean existsOnlineAccountByUsername(String username);
    boolean existsOnlineAccountByUser(User user);
    boolean existsOnlineAccountByEmail(String email);
} 
