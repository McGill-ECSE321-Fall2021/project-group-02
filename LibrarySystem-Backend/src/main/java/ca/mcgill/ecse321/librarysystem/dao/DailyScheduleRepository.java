package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarysystem.model.DailySchedule;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

@Repository
public interface DailyScheduleRepository extends CrudRepository<DailySchedule, Integer>{
	DailySchedule findDailyScheduleByDayAndWeeklySchedule(WeekDay day, WeeklySchedule weeklySchedule);
	List<DailySchedule> findDailyScheduleByStartTimeAndWeeklySchedule(Time startTime, WeeklySchedule weeklySchedule);
	List<DailySchedule> findDailyScheduleByEndTimeAndWeeklySchedule (Time endTime, WeeklySchedule weeklySchedule);

	boolean existsDailyScheduleByDayAndWeeklySchedule(WeekDay day, WeeklySchedule weeklySchedule);
	boolean existsDailyScheduleByStartTimeAndWeeklySchedule(Time startTime, WeeklySchedule weeklySchedule);
	boolean existsDailyScheduleByEndTimeAndWeeklySchedule (Time endTime, WeeklySchedule weeklySchedule);
}
