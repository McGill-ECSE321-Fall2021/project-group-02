/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Date;
import java.util.*;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
  /* Code from template association_GetMany */
  @OneToMany
  public DailySchedule getDay(int index)
  {
    DailySchedule aDay = days.get(index);
    return aDay;
  }

  public List<DailySchedule> getDays()
  {
    List<DailySchedule> newDays = Collections.unmodifiableList(days);
    return newDays;
  }

  public int numberOfDays()
  {
    int number = days.size();
    return number;
  }

  public boolean hasDays()
  {
    boolean has = days.size() > 0;
    return has;
  }

  public int indexOfDay(DailySchedule aDay)
  {
    int index = days.indexOf(aDay);
    return index;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfDaysValid()
  {
    boolean isValid = numberOfDays() >= minimumNumberOfDays() && numberOfDays() <= maximumNumberOfDays();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfDays()
  {
    return 7;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDays()
  {
    return 7;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfDays()
  {
    return 7;
  }
  /* Code from template association_AddMNToOnlyOne */
  public DailySchedule addDay(DailySchedule.WeekDay aDay, Time aStartTime, Time aEndTime, LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    if (numberOfDays() >= maximumNumberOfDays())
    {
      return null;
    }
    else
    {
      return new DailySchedule(aDay, aStartTime, aEndTime, aLibrarySoftwareSystem, this);
    }
  }

  public boolean addDay(DailySchedule aDay)
  {
    boolean wasAdded = false;
    if (days.contains(aDay)) { return false; }
    if (numberOfDays() >= maximumNumberOfDays())
    {
      return wasAdded;
    }

    WeeklySchedule existingWeeklySchedule = aDay.getWeeklySchedule();
    boolean isNewWeeklySchedule = existingWeeklySchedule != null && !this.equals(existingWeeklySchedule);

    if (isNewWeeklySchedule && existingWeeklySchedule.numberOfDays() <= minimumNumberOfDays())
    {
      return wasAdded;
    }

    if (isNewWeeklySchedule)
    {
      aDay.setWeeklySchedule(this);
    }
    else
    {
      days.add(aDay);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDay(DailySchedule aDay)
  {
    boolean wasRemoved = false;
    //Unable to remove aDay, as it must always have a weeklySchedule
    if (this.equals(aDay.getWeeklySchedule()))
    {
      return wasRemoved;
    }

    //weeklySchedule already at minimum (7)
    if (numberOfDays() <= minimumNumberOfDays())
    {
      return wasRemoved;
    }
    days.remove(aDay);
    wasRemoved = true;
    return wasRemoved;
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
      existingLibrarySoftwareSystem.removeWeeklySchedule(this);
    }
    librarySoftwareSystem.addWeeklySchedule(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=days.size(); i > 0; i--)
    {
      DailySchedule aDay = days.get(i - 1);
      aDay.delete();
    }
    LibrarySoftwareSystem placeholderLibrarySoftwareSystem = librarySoftwareSystem;
    this.librarySoftwareSystem = null;
    if(placeholderLibrarySoftwareSystem != null)
    {
      placeholderLibrarySoftwareSystem.removeWeeklySchedule(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarySoftwareSystem = "+(getLibrarySoftwareSystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySoftwareSystem())):"null");
  }
}