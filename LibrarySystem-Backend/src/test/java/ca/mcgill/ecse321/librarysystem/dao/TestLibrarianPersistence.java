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
	private OnlineAccountRepository onlineAccountRepository;
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
@AfterEach
public void clearDatabase() {
	onlineAccountRepository.deleteAll();
	librarianRepository.deleteAll();
	weeklyScheduleRepository.deleteAll();
}
@Test
public void testPersistAndLoadLibrarian() {
	OnlineAccount oa=new OnlineAccount();
	Librarian lib = new Librarian();
	WeeklySchedule ws=new WeeklySchedule();
	oa.setEmail("lib@hotmail.com");
	oa.setUsername("lib");
	oa.setPassword( "libpassword");
	
		ws.setStartDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)));
		ws.setEndDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)));
		lib.setOnlineAccount(oa);
		lib.setAddress("123 Test St");
		lib.setCity("Montreal");
		lib.setWeeklySchedule(ws);
		onlineAccountRepository.save(oa);
		weeklyScheduleRepository.save(ws);
		librarianRepository.save(lib);
		int id = lib.getId();
		oa.setUser(lib);
		
		lib = null;
		lib = librarianRepository.findLibrarianById(id);
		assertNotNull(lib);
		assertEquals(id, lib.getId());
		assertEquals("123 Test St", lib.getAddress());
		assertEquals("Montreal", lib.getCity());
		assertEquals("lib@hotmail.com",lib.getOnlineAccount().getEmail());
		assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),lib.getWeeklySchedule().getStartDate());
		
		
		lib = null;
		lib = librarianRepository.findLibrarianByOnlineAccount(oa);
		assertNotNull(lib);
		assertEquals(id, lib.getId());
		assertEquals("123 Test St", lib.getAddress());
		assertEquals("Montreal", lib.getCity());
		assertEquals("lib@hotmail.com",lib.getOnlineAccount().getEmail());
		assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),lib.getWeeklySchedule().getStartDate());
	}
}
