package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

import java.util.Date;
import java.util.List;

public interface WeeklyScheduleRepository extends CrudRepository<WeeklySchedule, Integer>{
	WeeklySchedule findWeeklyScheduleByStartDate(Date startDate);
    WeeklySchedule findWeeklyScheduleByEndDate(Date endDate);
    boolean existsWeeklyScheduleByStartDate(Date startDate);
    boolean existsWeeklyScheduleByEndDate(Date endDate);
} 
