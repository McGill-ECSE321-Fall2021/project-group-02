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
import ca.mcgill.ecse321.librarysystem.model.Person;
import ca.mcgill.ecse321.librarysystem.model.User;
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
	private PersonRepository personRepository;
	@Autowired
	private UserRepository userRepository;
	
	@AfterEach
	public void clearDatabase() {
		onlineAccountRepository.deleteAll();
		personRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadOnlineAccount() {
		
		String firstName = "TestFirstName";
		String lastName = "TestLastName";
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		personRepository.save(person);
		
		int id = 1;
		String address = "TestAddress";
		String city = "TestCity";
//		User user = new User();
//		user.setId(id);
//		user.setAddress(address);
//		user.setCity(city);
//		userRepository.save(user);
		
		String username = "TestUsername";
		String email = "TestEmail";
		String password = "TestPassword";
		OnlineAccount onlineAccount = new OnlineAccount();
		onlineAccount.setUsername(username);
		onlineAccount.setEmail(email);
		onlineAccount.setPassword(password);
//		onlineAccount.setUser(user);
		onlineAccountRepository.save(onlineAccount);
		
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
		
//		user = null;
//		
//		user = userRepository.findUserById(id);
//		assertNotNull(user);
//		assertEquals(id, user.getId());
//		assertEquals(address, user.getAddress());
//		assertEquals(city, user.getCity());
		
		onlineAccount = null;
		
//		onlineAccount = onlineAccountRepository.findOnlineAccountByUser(user);
		assertNotNull(onlineAccount);
		assertEquals(username, onlineAccount.getUsername());
		assertEquals(email, onlineAccount.getEmail());
		assertEquals(password, onlineAccount.getPassword());
	}
}
