/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;

// line 63 "model.ump"
// line 178 "model.ump"
public class Movie extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Movie Attributes
  private String title;
  private String director;

  //Movie Associations
  private Patron patron;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Movie(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem, String aTitle, String aDirector, Patron aPatron)
  {
    super(aIsArchived, aIsBorrowed, aIsDamaged, aId, aLibrarySoftwareSystem);
    title = aTitle;
    director = aDirector;
    boolean didAddPatron = setPatron(aPatron);
    if (!didAddPatron)
    {
      throw new RuntimeException("Unable to create borrowedMovy due to patron. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDirector(String aDirector)
  {
    boolean wasSet = false;
    director = aDirector;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDirector()
  {
    return director;
  }
  /* Code from template association_GetOne */
  public Patron getPatron()
  {
    return patron;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setPatron(Patron aPatron)
  {
    boolean wasSet = false;
    //Must provide patron to borrowedMovy
    if (aPatron == null)
    {
      return wasSet;
    }

    //patron already at maximum (5)
    if (aPatron.numberOfBorrowedMovies() >= Patron.maximumNumberOfBorrowedMovies())
    {
      return wasSet;
    }
    
    Patron existingPatron = patron;
    patron = aPatron;
    if (existingPatron != null && !existingPatron.equals(aPatron))
    {
      boolean didRemove = existingPatron.removeBorrowedMovy(this);
      if (!didRemove)
      {
        patron = existingPatron;
        return wasSet;
      }
    }
    patron.addBorrowedMovy(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Patron placeholderPatron = patron;
    this.patron = null;
    if(placeholderPatron != null)
    {
      placeholderPatron.removeBorrowedMovy(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "director" + ":" + getDirector()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "patron = "+(getPatron()!=null?Integer.toHexString(System.identityHashCode(getPatron())):"null");
  }
}