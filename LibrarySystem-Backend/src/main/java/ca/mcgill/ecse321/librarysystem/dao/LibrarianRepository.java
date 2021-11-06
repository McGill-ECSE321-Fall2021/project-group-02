package ca.mcgill.ecse321.librarysystem.dao;

import ca.mcgill.ecse321.librarysystem.model.Librarian;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibrarianRepository extends CrudRepository<Librarian, Integer>{
	Librarian findLibrarianById(Integer id);
	List<Librarian> findLibrarianByAddress(String address);
	Librarian findLibrarianByOnlineAccount(OnlineAccount onlineaccount);

	boolean existsLibrarianById(Integer id);
	boolean existsLibrarianByAddress(String address);
	boolean existsLibrarianByOnlineAccount(OnlineAccount onlineaccount);
}
