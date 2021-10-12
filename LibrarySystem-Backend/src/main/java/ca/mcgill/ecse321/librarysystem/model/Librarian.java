/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 20 "model.ump"
// line 139 "model.ump"
public class Librarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Associations
  private WeeklySchedule weeklySchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(int aId, String aAddress, String aCity, LibrarySoftwareSystem aLibrarySoftwareSystem, Person aPerson, WeeklySchedule aWeeklySchedule)
  {
    super(aId, aAddress, aCity, aLibrarySoftwareSystem, aPerson);
    if (!setWeeklySchedule(aWeeklySchedule))
    {
      throw new RuntimeException("Unable to create Librarian due to aWeeklySchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public WeeklySchedule getWeeklySchedule()
  {
    return weeklySchedule;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setWeeklySchedule(WeeklySchedule aNewWeeklySchedule)
  {
    boolean wasSet = false;
    if (aNewWeeklySchedule != null)
    {
      weeklySchedule = aNewWeeklySchedule;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    weeklySchedule = null;
    super.delete();
  }

}