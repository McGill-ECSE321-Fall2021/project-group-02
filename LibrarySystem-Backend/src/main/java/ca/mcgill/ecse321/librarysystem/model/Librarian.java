package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import java.sql.Date;
import javax.persistence.*;


@Entity
@DiscriminatorValue("L")
@Table(name = "LIBRARIAN")
public class Librarian extends User
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