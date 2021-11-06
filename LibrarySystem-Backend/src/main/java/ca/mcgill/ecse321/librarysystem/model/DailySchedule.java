package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class DailySchedule
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  public void setId(int id) {
	  this.id = id;
  }
  
  public int getId() {
    return this.id;
  }
  
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DailySchedule Attributes
  private WeekDay day;
  private Time startTime;
  private Time endTime;
  
//----------------------------------------------GETTERS
  
  public WeekDay getDay() {
	  return this.day;
  }
  
  public Time getStartTime() {
	  return this.startTime;
  }
  
  public Time getEndTime() {
	  return this.endTime;
  }
    
//----------------------------------------------SETTERS
  
  public void setDay(WeekDay d) {
	  this.day = d;
  }
  
  public void setStartTime(Time t) {
	  this.startTime = t;
  }
  
  public void setEndTime(Time t) {
	  this.endTime = t;
  }
  
}