/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

// line 98 "model.ump"
// line 204 "model.ump"
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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DailySchedule(WeekDay aDay, Time aStartTime, Time aEndTime, LibrarySoftwareSystem aLibrarySoftwareSystem, WeeklySchedule aWeeklySchedule)
  {
    day = aDay;
    startTime = aStartTime;
    endTime = aEndTime;
    boolean didAddLibrarySoftwareSystem = setLibrarySoftwareSystem(aLibrarySoftwareSystem);
    if (!didAddLibrarySoftwareSystem)
    {
      throw new RuntimeException("Unable to create dailySchedule due to librarySoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddWeeklySchedule = setWeeklySchedule(aWeeklySchedule);
    if (!didAddWeeklySchedule)
    {
      throw new RuntimeException("Unable to create day due to weeklySchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDay(WeekDay aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public WeekDay getDay()
  {
    return day;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = true)
  public WeeklySchedule getWeeklySchedule()
  {
    return weeklySchedule;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrarySoftwareSystem(LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    boolean wasSet = false;
    if (aLibrarySoftwareSystem == null)
    {
      return wasSet;
    }

    LibrarySoftwareSystem existingLibrarySoftwareSystem = librarySoftwareSystem;
    librarySoftwareSystem = aLibrarySoftwareSystem;
    if (existingLibrarySoftwareSystem != null && !existingLibrarySoftwareSystem.equals(aLibrarySoftwareSystem))
    {
      existingLibrarySoftwareSystem.removeDailySchedule(this);
    }
    librarySoftwareSystem.addDailySchedule(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    boolean wasSet = false;
    //Must provide weeklySchedule to day
    if (aWeeklySchedule == null)
    {
      return wasSet;
    }
    
    WeeklySchedule existingWeeklySchedule = weeklySchedule;
    weeklySchedule = aWeeklySchedule;
    weeklySchedule.addDay(this);
    wasSet = true;
    return wasSet;
  }

}