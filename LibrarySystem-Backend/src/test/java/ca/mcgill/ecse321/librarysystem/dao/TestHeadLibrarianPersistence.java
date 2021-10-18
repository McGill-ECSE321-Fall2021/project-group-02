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
public class TestHeadLibrarianPersistence {
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
@AfterEach
public void clearDatabase() {
	headLibrarianRepository.deleteAll();
}
@Test
public void testPersistAndLoadHeadLibrarian() {
	Library l = new Library ();
	l.setClosingHour(java.sql.Time.valueOf(LocalTime.of(17, 00)));
	l.setOpeningHour(java.sql.Time.valueOf(LocalTime.of(8, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
	ls.setOpeningHours(l);
	Person p = new Person();
	OnlineAccount oa=new OnlineAccount();
	HeadLibrarian hl = new HeadLibrarian();
	WeeklySchedule ws=new WeeklySchedule();
	p.setFirstName("hlfn");
	p.setLastName("hlln");
	p.setLibrarySoftwareSystem(ls);
	oa.setEmail("hlib@hotmail.com");
	oa.setUsername("hlib");
	oa.setPassword("hlibpassword");
	oa.setUser(hl);
	oa.setLibrarySoftwareSystem(ls);
	ws.setStartDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)));
	ws.setEndDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)));
	ws.setLibrarySoftwareSystem(ls);
	hl.setOnlineAccount(oa);
	hl.setId(2);
	hl.setAddress("123 Test Blvd");
	hl.setCity("Montreal");
	hl.setPerson(p);
	hl.setWeeklySchedule(ws);
	hl.setLibrarySoftwareSystem(ls);
	headLibrarianRepository.save(hl);
	
	hl = null;
	hl = headLibrarianRepository.findHeadLibrarianById(2);
	assertNotNull(hl);
	assertEquals(2, hl.getId());
	assertEquals("hlfn",hl.getPerson().getFirstName());
	assertEquals("123 Test Blvd", hl.getAddress());
	assertEquals("Montreal", hl.getCity());
	assertEquals("hlib@hotmail.com",hl.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),hl.getWeeklySchedule().getStartDate());
	
	hl = null;
	hl = headLibrarianRepository.findHeadLibrarianByPerson(p);
	assertNotNull(hl);
	assertEquals(2, hl.getId());
	assertEquals("hlfn",hl.getPerson().getFirstName());
	assertEquals("123 Test Blvd", hl.getAddress());
	assertEquals("Montreal", hl.getCity());
	assertEquals("hlib@hotmail.com",hl.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),hl.getWeeklySchedule().getStartDate());
}
}
