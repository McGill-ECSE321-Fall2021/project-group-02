package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("N")
@Table(name = "newspaper")
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