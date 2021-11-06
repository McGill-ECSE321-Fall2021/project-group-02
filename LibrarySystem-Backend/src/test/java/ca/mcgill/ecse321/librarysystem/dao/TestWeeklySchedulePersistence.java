package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.DailySchedule;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.model.Library;
import ca.mcgill.ecse321.librarysystem.model.LibrarySoftwareSystem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestWeeklySchedulePersistence {
	
	@Autowired
	EntityManager entitiyManager;

	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
	@Autowired
	private DailyScheduleRepository dailyScheduleRepository;
	
	@AfterEach
	public void clearDatabase() {
		weeklyScheduleRepository.deleteAll();
		dailyScheduleRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadWeeklySchedule() {
		Date startDate = java.sql.Date.valueOf(LocalDate.of(2021, 10, 18));
		Date endDate = java.sql.Date.valueOf(LocalDate.of(2021, 10, 25));
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		
		WeeklySchedule schedule = new WeeklySchedule();
		schedule.setEndDate(endDate);
		schedule.setStartDate(startDate);
		List<DailySchedule> ls = new ArrayList<DailySchedule>();
		DailySchedule d = new DailySchedule();
		d.setEndTime(endTime);
		d.setStartTime(startTime);
		d.setDay(WeekDay.Monday);
		
		ls.add(d);
		schedule.setDay(ls);
		
		dailyScheduleRepository.save(d);
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
