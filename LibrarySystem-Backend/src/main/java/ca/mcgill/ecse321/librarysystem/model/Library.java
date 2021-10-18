/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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
  // INTERFACE
  //------------------------

  public void setOpeningHour(Time aOpeningHour)
  {
    openingHour = aOpeningHour;
  }

  public void setClosingHour(Time aClosingHour)
  {
    closingHour = aClosingHour;
  }

  public Time getOpeningHour()
  {
    return openingHour;
  }

  public Time getClosingHour()
  {
    return closingHour;
  }

  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }
  public void setLibrarySoftwareSystem(LibrarySoftwareSystem a)
  {
	this.librarySoftwareSystem = a;
  }

}