package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import java.sql.Date;
import javax.persistence.*;


@Entity
@DiscriminatorValue("L")
@Table(name = "librarian")
public class Librarian extends UserEntity
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Associations
  private WeeklySchedule weeklySchedule;

  //------------------------
  // INTERFACE
  //------------------------

  @OneToOne
  public WeeklySchedule getWeeklySchedule()
  {
    return weeklySchedule;
  }

  public void setWeeklySchedule(WeeklySchedule aNewWeeklySchedule)
  {
	weeklySchedule = aNewWeeklySchedule;
  }
}