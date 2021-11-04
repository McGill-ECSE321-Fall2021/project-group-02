package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.UserEntity;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.Library;
import ca.mcgill.ecse321.librarysystem.model.LibrarySoftwareSystem;

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
		onlineAccountRepository.deleteAll();
		librarianRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadOnlineAccount() {
		
		String firstName = "TestFirstName";
		String lastName = "TestLastName";	
		
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
		onlineAccount.setUser(librarian);
		
		librarianRepository.save(librarian);
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
