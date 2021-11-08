package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.DailySchedule;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;

public interface DailyScheduleRepository extends CrudRepository<DailySchedule, Integer>{

	DailySchedule findDailyScheduleById(int id);
	List<DailySchedule> findDailyScheduleByDay(WeekDay w);
	
	boolean existsDailyScheduleById(int id);
}
