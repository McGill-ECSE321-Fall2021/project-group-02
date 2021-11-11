package ca.mcgill.ecse321.librarysystem.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
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
	private OnlineAccountRepository onlineAccountRepository;
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
@AfterEach
public void clearDatabase() {
	headLibrarianRepository.deleteAll();
	onlineAccountRepository.deleteAll();
	weeklyScheduleRepository.deleteAll();
}
@Test
public void testPersistAndLoadHeadLibrarian() {
	OnlineAccount oa=new OnlineAccount();
	HeadLibrarian hl = new HeadLibrarian();
	WeeklySchedule ws=new WeeklySchedule();
	oa.setEmail("hlib@hotmail.com");
	oa.setUsername("hlib");
	oa.setPassword("hlibpassword");
	

	ws.setStartDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)));
	ws.setEndDate(java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)));
	hl.setOnlineAccount(oa);
	hl.setAddress("123 Test Blvd");
	hl.setCity("Montreal");
	hl.setWeeklySchedule(ws);
	weeklyScheduleRepository.save(ws);
	headLibrarianRepository.save(hl);
	onlineAccountRepository.save(oa);
	oa.setUser(hl);
	onlineAccountRepository.save(oa);
	int idOA = oa.getId();
	int id = hl.getId();
	
	hl = null;
	hl = headLibrarianRepository.findHeadLibrarianById(id);
	assertNotNull(hl);
	assertEquals(id, hl.getId());
	assertEquals("123 Test Blvd", hl.getAddress());
	assertEquals("Montreal", hl.getCity());
	assertEquals("hlib@hotmail.com",hl.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),hl.getWeeklySchedule().getStartDate());
	
	oa = null;
	oa = onlineAccountRepository.findOnlineAccountById(idOA);
	assertNotNull(oa);
	assertEquals(idOA, oa.getId());
}
}
