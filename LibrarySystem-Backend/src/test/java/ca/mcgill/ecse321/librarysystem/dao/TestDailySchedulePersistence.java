package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.DailySchedule;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestDailySchedulePersistence {
	
	@Autowired
	EntityManager entityManager;

	@Autowired
	private DailyScheduleRepository dailyScheduleRepository;
	
	@AfterEach
	public void clearDatabase() {
		dailyScheduleRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadDailySchedule() {
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		
		List<DailySchedule> l = new ArrayList<DailySchedule>();

		DailySchedule dSchedule = new DailySchedule();
		dSchedule.setDay(WeekDay.Monday);
		dSchedule.setStartTime(startTime);
		dSchedule.setEndTime(endTime);
		
		l.add(dSchedule);
		
		dailyScheduleRepository.save(dSchedule);
		int id = dSchedule.getId();
		
		dSchedule = null;
		
		dSchedule = dailyScheduleRepository.findDailyScheduleById(id);
		assertNotNull(dSchedule);
		assertEquals(id, dSchedule.getId());
	}

}
