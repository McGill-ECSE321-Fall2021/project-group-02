package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("M")
@Table(name = "MOVIE")
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
  // INTERFACE
  //------------------------

  public void setTitle(String aTitle)
  {
    title = aTitle;
  }

  public void setDirector(String aDirector)
  {
    director = aDirector;
  }
  
  public String getTitle()
  {
    return title;
  }

  public String getDirector()
  {
    return director;
  }

  @ManyToOne(optional = true)
  public Patron getPatron()
  {
    return patron;
  }

  public void setPatron(Patron aPatron)
  {
	this.patron = aPatron;
  }
}