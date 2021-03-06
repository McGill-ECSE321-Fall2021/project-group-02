package ca.mcgill.ecse321.librarysystem.model;

import java.sql.Time;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="library")
@Entity
public class Library
{  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
	  this.id = id;
  }
  
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Library Attributes
  private Time openingHour;
  private Time closingHour;

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

}