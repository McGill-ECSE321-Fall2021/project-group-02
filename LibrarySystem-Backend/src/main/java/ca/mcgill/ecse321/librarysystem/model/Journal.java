package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("J")
@Table(name = "journal")
public class Journal extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Journal Attributes
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