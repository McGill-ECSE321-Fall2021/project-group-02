/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Date;
import java.util.*;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

// line 112 "model.ump"
// line 214 "model.ump"
@Entity
public class WeeklySchedule
{
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

  //WeeklySchedule Attributes
  private Date startDate;
  private Date endDate;

  //WeeklySchedule Associations
  private List<DailySchedule> days;
  private LibrarySoftwareSystem librarySoftwareSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeeklySchedule(Date aStartDate, Date aEndDate, LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    days = new ArrayList<DailySchedule>();
    boolean didAddLibrarySoftwareSystem = setLibrarySoftwareSystem(aLibrarySoftwareSystem);
    if (!didAddLibrarySoftwareSystem)
    {
      throw new RuntimeException("Unable to create weeklySchedule due to librarySoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  
  public void addDay(DailySchedule a)
  {
	  days.add(a);
  }

  @OneToMany
  @Transient
  public List<DailySchedule> getDays()
  {
    List<DailySchedule> newDays = Collections.unmodifiableList(days);
    return newDays;
  }

  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }

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
      existingLibrarySoftwareSystem.removeWeeklySchedule(this);
    }
    librarySoftwareSystem.addWeeklySchedule(this);
    wasSet = true;
    return wasSet;
  }
}