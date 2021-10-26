/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import java.sql.Date;
import javax.persistence.*;

// line 26 "model.ump"
// line 145 "model.ump"
@Entity
@DiscriminatorValue("H")
@Table(name = "head_librarian")
public class HeadLibrarian extends UserEntity
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HeadLibrarian Associations
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