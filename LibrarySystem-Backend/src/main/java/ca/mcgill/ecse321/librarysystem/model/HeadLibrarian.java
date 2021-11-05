package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import java.sql.Date;
import javax.persistence.*;

@Entity
@DiscriminatorValue("H")
@Table(name = "head_librarian")
public class HeadLibrarian extends UserEntity
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
  @OneToOne
  private WeeklySchedule weeklySchedule;

  //------------------------
  // INTERFACE
  //------------------------

  public WeeklySchedule getWeeklySchedule()
  {
    return weeklySchedule;
  }

  public void setWeeklySchedule(WeeklySchedule aNewWeeklySchedule)
  {
    weeklySchedule = aNewWeeklySchedule;
  }
}