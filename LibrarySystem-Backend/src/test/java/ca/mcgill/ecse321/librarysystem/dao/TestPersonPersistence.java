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
public class TestPersonPersistence {
	
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private LibraryRepository libraryRepository;
	
@AfterEach
public void clearDatabase() {
	personRepository.deleteAll();
	libraryRepository.deleteAll();
}
@Test
public void testPersistAndLoadPerson() {
	Library l = new Library ();
	l.setClosingHour(java.sql.Time.valueOf(LocalTime.of(17, 00)));
	l.setOpeningHour(java.sql.Time.valueOf(LocalTime.of(8, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
	ls.setOpeningHours(l);
//	l.setLibrarySoftwareSystem(ls);
	Person p = new Person();
	String firstName = "testFirstName";
	String lastName = "testLastName";
	p.setLibrarySoftwareSystem(ls);
	p.setFirstName(firstName);
	p.setLastName(lastName);
	libraryRepository.save(l);
	personRepository.save(p);
	
	Person testP = (personRepository.findPersonByFirstName(firstName)).get(0);
	assertNotNull(testP);
	assertEquals("testFirstName",testP.getFirstName());
	assertEquals("testLastName",testP.getLastName());
}
}
