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
public class TestPatronPersistence {
	@Autowired
	private PatronRepository patronRepository;
	@Autowired
	private OnlineAccountRepository onlineAccountRepository;
	
	@AfterEach
	public void clearDatabase() {
		patronRepository.deleteAll();
		onlineAccountRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadPatron() {
	
		OnlineAccount oa=new OnlineAccount();
		Patron pat = new Patron();
	
		oa.setEmail("pat@hotmail.com");
		oa.setUsername("patib");
		oa.setPassword("patpassword");
		
		pat.setAddress("123 Test W");
		pat.setCity("Montreal");
		
		onlineAccountRepository.save(oa);
		patronRepository.save(pat);
		oa.setUser(pat);
		pat.setOnlineAccount(oa);
		onlineAccountRepository.save(oa);
		patronRepository.save(pat);
		
		//Check online account setting patron
		oa = null;
		oa = onlineAccountRepository.findOnlineAccountByUserEntity(pat);
		assertNotNull(oa);
		assertNotNull(oa.getUser());
		
		
		// Check finding patron by ID
		int id = pat.getId();
		
		pat = patronRepository.findPatronById(id);
		assertNotNull(pat);
		assertEquals(id, pat.getId());
		assertEquals("123 Test W", pat.getAddress());
		assertEquals("Montreal", pat.getCity());
		assertEquals("pat@hotmail.com",pat.getOnlineAccount().getEmail());
	}
}
