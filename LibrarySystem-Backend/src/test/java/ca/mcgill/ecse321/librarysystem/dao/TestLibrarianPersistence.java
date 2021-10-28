package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
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
public class TestLibrarianPersistence {
	@Autowired
	private LibrarianRepository librarianRepository;
	@Autowired 
	private LibraryRepository libraryRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private OnlineAccountRepository onlineAccountRepository;
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
@AfterEach
public void clearDatabase() {
	librarianRepository.deleteAll();
	libraryRepository.deleteAll();
	personRepository.deleteAll();
	onlineAccountRepository.deleteAll();
	weeklyScheduleRepository.deleteAll();
}
@Test
public void testPersistAndLoadLibrarian() {
	Library l = new Library ();
	l.setClosingHour(java.sql.Time.valueOf(LocalTime.of(17, 00)));
	l.setOpeningHour(java.sql.Time.valueOf(LocalTime.of(8, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
	Person p = new Person();
	OnlineAccount oa=new OnlineAccount();
	Librarian lib = new Librarian();
	WeeklySchedule ws=new WeeklySchedule();
	p.setFirstName("lfn");
	p.setLastName("lln");
	oa.setEmail("lib@hotmail.com");
	oa.setUsername("lib");
	oa.setPassword( "libpassword");
	
		ws.setStartDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)));
		ws.setEndDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)));
		lib.setOnlineAccount(oa);
		lib.setAddress("123 Test St");
		lib.setCity("Montreal");
		lib.setPerson(p);
		lib.setWeeklySchedule(ws);
		personRepository.save(p);
		onlineAccountRepository.save(oa);
		libraryRepository.save(l);
		weeklyScheduleRepository.save(ws);
		librarianRepository.save(lib);
		int id = lib.getId();
		oa.setUser(lib);
		
		lib = null;
		lib = librarianRepository.findLibrarianById(id);
		assertNotNull(lib);
		assertEquals(id, lib.getId());
		assertEquals("lfn",lib.getPerson().getFirstName());
		assertEquals("123 Test St", lib.getAddress());
		assertEquals("Montreal", lib.getCity());
		assertEquals("lib@hotmail.com",lib.getOnlineAccount().getEmail());
		assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),lib.getWeeklySchedule().getStartDate());
		
		lib = null;
		lib = librarianRepository.findLibrarianByPerson(p);
		assertNotNull(lib);
		assertEquals(id, lib.getId());
		assertEquals("lfn",lib.getPerson().getFirstName());
		assertEquals("123 Test St", lib.getAddress());
		assertEquals("Montreal", lib.getCity());
		assertEquals("lib@hotmail.com",lib.getOnlineAccount().getEmail());
		assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),lib.getWeeklySchedule().getStartDate());
	}
}
