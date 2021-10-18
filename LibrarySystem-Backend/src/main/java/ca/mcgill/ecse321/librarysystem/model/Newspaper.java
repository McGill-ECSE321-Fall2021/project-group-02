/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

// line 77 "model.ump"
// line 188 "model.ump"
@Entity
@DiscriminatorValue("N")
@Table(name = "NEWSPAPER")
public class Newspaper extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Newspaper Attributes
  private String name;
  private Date date;

  //------------------------
  // INTERFACE
  //------------------------

  public void setName(String aName)
  {
    name = aName;
  }

  public void setDate(Date aDate)
  {
    date = aDate;
  }

  public String getName()
  {
    return name;
  }

  public Date getDate()
  {
    return date;
  }
}