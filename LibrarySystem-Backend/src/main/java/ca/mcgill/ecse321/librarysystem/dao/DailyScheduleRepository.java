package ca.mcgill.ecse321.librarysystem.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.DailySchedule;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;
import ca.mcgill.ecse321.librarysystem.model.WeeklySchedule;

public interface DailyScheduleRepository extends CrudRepository<DailySchedule, Integer>{

}
