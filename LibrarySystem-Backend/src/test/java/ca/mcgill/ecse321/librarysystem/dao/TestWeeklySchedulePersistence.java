package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.model.Library;
import ca.mcgill.ecse321.librarysystem.model.LibrarySoftwareSystem;

public class TestWeeklySchedulePersistence {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
	
	@AfterEach
	public void clearDatabase() {
		weeklyScheduleRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadWeeklySchedule() {
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2021, 10, 18));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2021, 10, 25));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		Library library = new Library(startTime, endTime);
		
		LibrarySoftwareSystem ls = new LibrarySoftwareSystem(library);
		
		WeeklySchedule schedule = new WeeklySchedule(null, null, ls);
		schedule.setEndDate(endDate);
		schedule.setStartDate(startDate);
		weeklyScheduleRepository.save(schedule);
		
		schedule = null;
		
		schedule = weeklyScheduleRepository.findWeeklyScheduleByEndDate(endDate);
		assertNotNull(schedule);
		assertEquals(startDate, schedule.getStartDate());
		
		schedule = null;
		
		schedule = weeklyScheduleRepository.findWeeklyScheduleByStartDate(startDate);
		assertNotNull(schedule);
		assertEquals(endDate, schedule.getEndDate());
	}
}
