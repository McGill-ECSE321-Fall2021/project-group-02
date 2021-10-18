package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.librarysystem.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestUserPersistence {
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private PersonRepository personRepository;
	@Autowired
	private LibraryRepository libraryRepository;
	@Autowired
	private OnlineAccountRepository onlineAccountRepository;

@AfterEach
public void clearDatabase() {
	userRepository.deleteAll();
	personRepository.deleteAll();
	libraryRepository.deleteAll();
	onlineAccountRepository.deleteAll();
}
@Test
public void testPersistenceAndLoadUser() {
	Library l = new Library ();
	l.setClosingHour(java.sql.Time.valueOf(LocalTime.of(17, 00)));
	l.setOpeningHour(java.sql.Time.valueOf(LocalTime.of(8, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
	ls.setOpeningHours(l);
	l.setLibrarySoftwareSystem(ls);
	User u = new User();
	Person p = new Person();
	OnlineAccount oa=new OnlineAccount();
	p.setFirstName("ufn");
	p.setLastName("uln");
	p.setLibrarySoftwareSystem(ls);
	oa.setEmail("u@hotmail.com");
	oa.setUsername("u");
	oa.setPassword( "upassword");
	oa.setUser(u);
	oa.setLibrarySoftwareSystem(ls);
	u.setLibrarySoftwareSystem(ls);
	u.setAddress("123 Test Blvd");
	u.setCity("Montreal");
	u.setId(10);
	u.setOnlineAccount(oa);
	u.setPerson(p);
	personRepository.save(p);
	libraryRepository.save(l);
	onlineAccountRepository.save(oa);
	userRepository.save(u);
	
	u = null;
	u = userRepository.findUserById(10);
	assertNotNull(u);
	assertEquals(10, u.getId());
	assertEquals("ufn",u.getPerson().getFirstName());
	assertEquals("123 Test Blvd", u.getAddress());
	assertEquals("Montreal", u.getCity());
	assertEquals("u@hotmail.com",u.getOnlineAccount().getEmail());
}
}
