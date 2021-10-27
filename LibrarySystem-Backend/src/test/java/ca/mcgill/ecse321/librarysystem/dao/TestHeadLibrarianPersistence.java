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
	headLibrarianRepository.deleteAll();
	libraryRepository.deleteAll();
	personRepository.deleteAll();
	onlineAccountRepository.deleteAll();
	weeklyScheduleRepository.deleteAll();
}
@Test
public void testPersistAndLoadHeadLibrarian() {
	Library l = new Library ();
	l.setClosingHour(java.sql.Time.valueOf(LocalTime.of(17, 00)));
	l.setOpeningHour(java.sql.Time.valueOf(LocalTime.of(8, 00)));
	libraryRepository.save(l);
	Person p = new Person();
	OnlineAccount oa=new OnlineAccount();
	HeadLibrarian hl = new HeadLibrarian();
	WeeklySchedule ws=new WeeklySchedule();
	p.setFirstName("hlfn");
	p.setLastName("hlln");
	oa.setEmail("hlib@hotmail.com");
	oa.setUsername("hlib");
	oa.setPassword("hlibpassword");
	//oa.setUser(hl);
	ws.setStartDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)));
	ws.setEndDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)));
	hl.setOnlineAccount(oa);
	hl.setAddress("123 Test Blvd");
	hl.setCity("Montreal");
	hl.setPerson(p);
	hl.setWeeklySchedule(ws);
	personRepository.save(p);
	weeklyScheduleRepository.save(ws);
	
	onlineAccountRepository.save(oa);
	headLibrarianRepository.save(hl);
	
	
	
	int id = hl.getId();
	
	hl = null;
	hl = headLibrarianRepository.findHeadLibrarianById(2);
	assertNotNull(hl);
	assertEquals(id, hl.getId());
	assertEquals("hlfn",hl.getPerson().getFirstName());
	assertEquals("123 Test Blvd", hl.getAddress());
	assertEquals("Montreal", hl.getCity());
	assertEquals("hlib@hotmail.com",hl.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),hl.getWeeklySchedule().getStartDate());
	
	hl = null;
	hl = headLibrarianRepository.findHeadLibrarianByPerson(p);
	assertNotNull(hl);
	assertEquals(id, hl.getId());
	assertEquals("hlfn",hl.getPerson().getFirstName());
	assertEquals("123 Test Blvd", hl.getAddress());
	assertEquals("Montreal", hl.getCity());
	assertEquals("hlib@hotmail.com",hl.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),hl.getWeeklySchedule().getStartDate());
}
}
