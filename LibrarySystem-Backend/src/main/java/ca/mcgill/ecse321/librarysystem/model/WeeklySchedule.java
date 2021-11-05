package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Date;
import java.util.*;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

@Entity
public class WeeklySchedule
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeeklySchedule Attributes
  private Date startDate;
  private Date endDate;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  //WeeklySchedule Associations
  @OneToMany
  private List<DailySchedule> days;

  //------------------------
  // INTERFACE
  //------------------------
  
  public void setId(int aId) {
    this.id = aId;
  }

  public int getId() {
    return this.id;
  }
  
  public void setStartDate(Date aStartDate)
  {
    startDate = aStartDate;
  }

  public void setEndDate(Date aEndDate)
  {
    endDate = aEndDate;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public List<DailySchedule> getDailySchedules()
  {
    return this.days;
  }

  public void setDay(List<DailySchedule> d) 
  {
	  this.days = d;
  }
}