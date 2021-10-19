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

  //DailySchedule Attributes
  private WeekDay day;
  private Time startTime;
  private Time endTime;

  //DailySchedule Associations
  private LibrarySoftwareSystem librarySoftwareSystem;
  private WeeklySchedule weeklySchedule;
  
//----------------------------------------------GETTERS
  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem() {
	  return this.librarySoftwareSystem;
  }
  
  @ManyToOne(optional = false)
  public WeeklySchedule getWeeklySchedule() {
	  return this.weeklySchedule;
  }
  
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
  
  public void setLibrarySoftwareSystem(LibrarySoftwareSystem l) {
	  this.librarySoftwareSystem = l;
  }
  
  public void setWeeklySchedule(WeeklySchedule w) {
	  this.weeklySchedule = w;
  }
  
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