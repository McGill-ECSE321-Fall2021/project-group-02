package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

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
	@Autowired
	private DailyScheduleRepository dailyScheduleRepository;
@AfterEach
public void clearDatabase() {
	librarianRepository.deleteAll();
	onlineAccountRepository.deleteAll();
	weeklyScheduleRepository.deleteAll();
	dailyScheduleRepository.deleteAll();
}

@Test
public void testPersistAndLoadLibrarian() {
	DailySchedule ds = new DailySchedule();
	OnlineAccount oa=new OnlineAccount();
	Librarian lib = new Librarian();
	WeeklySchedule ws=new WeeklySchedule();
	oa.setEmail("lib@hotmail.com");
	oa.setUsername("lib");
	oa.setPassword( "libpassword");
	
	ws.setStartDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)));
	ws.setEndDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)));
	ArrayList<DailySchedule> l = new ArrayList<DailySchedule>();
	l.add(ds);
	ws.setDay(l);
	
	lib.setAddress("123 Test St");
	lib.setCity("Montreal");
	
	//Save objects to repository
	librarianRepository.save(lib);
	oa.setUser(lib);
	onlineAccountRepository.save(oa);
	dailyScheduleRepository.save(ds);
	weeklyScheduleRepository.save(ws);
	
	//Set associations
	lib.setOnlineAccount(oa);
	lib.setWeeklySchedule(ws);
	
	librarianRepository.save(lib);
	
	
	int id = lib.getId();
	oa.setUser(lib);
	
	// Check findById
	lib = null;
	lib = librarianRepository.findLibrarianById(id);
	assertNotNull(lib);
	assertEquals(id, lib.getId());
	assertEquals("123 Test St", lib.getAddress());
	assertEquals("Montreal", lib.getCity());
	assertEquals("lib@hotmail.com",lib.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),lib.getWeeklySchedule().getStartDate());
	
	// Check findByOnlineAccount
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
