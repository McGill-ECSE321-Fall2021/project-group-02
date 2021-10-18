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
@AfterEach
public void clearDatabse() {
	patronRepository.deleteAll();
}
@Test
public void testPersistAndLoadPatron() {
	Library l = new Library ();
	l.setClosingHour(java.sql.Time.valueOf(LocalTime.of(17, 00)));
	l.setOpeningHour(java.sql.Time.valueOf(LocalTime.of(8, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
	ls.setOpeningHours(l);
	User u = new User();
	Person p = new Person();
	OnlineAccount oa=new OnlineAccount();
	Patron pat = new Patron();
	WeeklySchedule ws=new WeeklySchedule();
	p.setFirstName("patfn");
	p.setLastName("patln");
	p.setLibrarySoftwareSystem(ls);
	oa.setEmail("pat@hotmail.com");
	oa.setUsername("patib");
	oa.setPassword("patpassword");
	oa.setUser(pat);
	oa.setLibrarySoftwareSystem(ls);
	pat.setAddress("123 Test W");
	pat.setCity("Montreal");
	pat.setId(13);
	pat.setOnlineAccount(oa);
	pat.setPerson(p);
	patronRepository.save(pat);
	
	pat = patronRepository.findPatronById(13);
	assertNotNull(pat);
	assertEquals(13, pat.getId());
	assertEquals("patfn",pat.getPerson().getFirstName());
	assertEquals("123 Test W", pat.getAddress());
	assertEquals("Montreal", pat.getCity());
	assertEquals("pat@hotmail.com",pat.getOnlineAccount().getEmail());
	
	pat = patronRepository.findPatronByPerson(p);
	assertNotNull(pat);
	assertEquals(13, pat.getId());
	assertEquals("patfn",pat.getPerson().getFirstName());
	assertEquals("123 Test W", pat.getAddress());
	assertEquals("Montreal", pat.getCity());
	assertEquals("pat@hotmail.com",pat.getOnlineAccount().getEmail());
}
}
