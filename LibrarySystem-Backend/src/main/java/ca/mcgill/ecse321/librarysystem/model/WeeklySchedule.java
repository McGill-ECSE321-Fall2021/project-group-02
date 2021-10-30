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
  private int id;
  
  public void setId(int aId) {
    this.id = aId;
  }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return this.id;
  }
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeeklySchedule Attributes
  private Date startDate;
  private Date endDate;

  //WeeklySchedule Associations
  private List<DailySchedule> days;

  //------------------------
  // INTERFACE
  //------------------------

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

  @OneToMany
  public List<DailySchedule> getDay()
  {
    return this.days;
  }

  public void setDay(List<DailySchedule> d) 
  {
	  this.days = d;
  }
}