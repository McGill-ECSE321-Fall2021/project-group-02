package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.Librarian;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestOnlineAccountPersistence {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private OnlineAccountRepository onlineAccountRepository;
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@AfterEach
	public void clearDatabase() {
		librarianRepository.deleteAll();
		onlineAccountRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadOnlineAccount() {
		
		String address = "TestAddress";
		String city = "TestCity";
		Librarian librarian = new Librarian();
		librarian.setAddress(address);
		librarian.setCity(city);
		
		String username = "TestUsername";
		String email = "TestEmail";
		String password = "TestPassword";
		OnlineAccount onlineAccount = new OnlineAccount();
		onlineAccount.setUsername(username);
		onlineAccount.setEmail(email);
		onlineAccount.setPassword(password);
		
		librarian.setOnlineAccount(onlineAccount);
		librarianRepository.save(librarian);
		onlineAccountRepository.save(onlineAccount);
		onlineAccount.setUser(librarian);
		onlineAccountRepository.save(onlineAccount);
		
		
		// Tests
		
		onlineAccount = null;
		
		onlineAccount = onlineAccountRepository.findOnlineAccountByUsername(username);
		assertNotNull(onlineAccount);
		assertEquals(username, onlineAccount.getUsername());
		assertEquals(email, onlineAccount.getEmail());
		assertEquals(password, onlineAccount.getPassword());
		
		onlineAccount = null;
		
		onlineAccount = onlineAccountRepository.findOnlineAccountByEmail(email);
		assertNotNull(onlineAccount);
		assertEquals(username, onlineAccount.getUsername());
		assertEquals(email, onlineAccount.getEmail());
		assertEquals(password, onlineAccount.getPassword());
		
		onlineAccount = null;
		
		onlineAccount = onlineAccountRepository.findOnlineAccountByUserEntity(librarian);
		assertNotNull(onlineAccount);
		assertEquals(username, onlineAccount.getUsername());
		assertEquals(email, onlineAccount.getEmail());
		assertEquals(password, onlineAccount.getPassword());
	}
}
