package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

public interface WeeklyScheduleRepository extends CrudRepository<WeeklySchedule, Integer>{
	WeeklySchedule findWeeklyScheduleByStartDate(Date startDate);
    WeeklySchedule findWeeklyScheduleByEndDate(Date endDate);

    boolean existsWeeklyScheduleByStartDate(Date startDate);
    boolean existsWeeklyScheduleByEndDate(Date endDate);
} 
