package ca.mcgill.ecse321.librarysystem.model;

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