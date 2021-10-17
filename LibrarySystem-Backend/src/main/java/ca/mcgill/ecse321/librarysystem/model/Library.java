/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

// line 106 "model.ump"
// line 209 "model.ump"
@Entity
public class Library
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

  //Library Attributes
  private Time openingHour;
  private Time closingHour;

  //Library Associations
  private LibrarySoftwareSystem librarySoftwareSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Library(Time aOpeningHour, Time aClosingHour, LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    openingHour = aOpeningHour;
    closingHour = aClosingHour;
    if (aLibrarySoftwareSystem == null || aLibrarySoftwareSystem.getOpeningHours() != null)
    {
      throw new RuntimeException("Unable to create Library due to aLibrarySoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    librarySoftwareSystem = aLibrarySoftwareSystem;
  }

  public Library(Time aOpeningHour, Time aClosingHour)
  {
    openingHour = aOpeningHour;
    closingHour = aClosingHour;
    librarySoftwareSystem = new LibrarySoftwareSystem(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOpeningHour(Time aOpeningHour)
  {
    boolean wasSet = false;
    openingHour = aOpeningHour;
    wasSet = true;
    return wasSet;
  }

  public boolean setClosingHour(Time aClosingHour)
  {
    boolean wasSet = false;
    closingHour = aClosingHour;
    wasSet = true;
    return wasSet;
  }

  public Time getOpeningHour()
  {
    return openingHour;
  }

  public Time getClosingHour()
  {
    return closingHour;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }

  public void delete()
  {
    LibrarySoftwareSystem existingLibrarySoftwareSystem = librarySoftwareSystem;
    librarySoftwareSystem = null;
    if (existingLibrarySoftwareSystem != null)
    {
      existingLibrarySoftwareSystem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "openingHour" + "=" + (getOpeningHour() != null ? !getOpeningHour().equals(this)  ? getOpeningHour().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "closingHour" + "=" + (getClosingHour() != null ? !getClosingHour().equals(this)  ? getClosingHour().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarySoftwareSystem = "+(getLibrarySoftwareSystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySoftwareSystem())):"null");
  }
}