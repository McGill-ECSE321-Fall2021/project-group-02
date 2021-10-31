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
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;
import ca.mcgill.ecse321.librarysystem.model.Library;
import ca.mcgill.ecse321.librarysystem.model.LibrarySoftwareSystem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestDailySchedulePersistence {
	
	@Autowired
	EntityManager entityManager;
	
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
	public void testPersistAndLoadDailySchedule() {
//		Date startDate = java.sql.Date.valueOf(LocalDate.of(2021, 10, 18));
//		Date endDate = java.sql.Date.valueOf(LocalDate.of(2021, 10, 25));
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
//		
//		WeeklySchedule schedule = new WeeklySchedule();
//		schedule.setEndDate(endDate);
//		schedule.setStartDate(startDate);
//		List<DailySchedule> l = new ArrayList<DailySchedule>();
//		
//		
//		
//		DailySchedule dSchedule = new DailySchedule();
//		dSchedule.setDay(WeekDay.Monday);
//		dSchedule.setStartTime(startTime);
//		dSchedule.setEndTime(endTime);
//		
//		
//		l.add(dSchedule);
//		schedule.setDay(l);
//		
//		dailyScheduleRepository.save(dSchedule);
//		weeklyScheduleRepository.save(schedule);
//		//dSchedule.setWeeklySchedule(schedule);
//		
//		dSchedule = null;
//		
//		dSchedule = dailyScheduleRepository.findDailyScheduleByDayAndWeeklySchedule(WeekDay.Monday, schedule);
//		assertNotNull(dSchedule);
//		assertEquals(startTime, dSchedule.getStartTime());
//		assertEquals(endTime, dSchedule.getEndTime());
//		assertEquals(WeekDay.Monday, dSchedule.getDay());
	}

}
