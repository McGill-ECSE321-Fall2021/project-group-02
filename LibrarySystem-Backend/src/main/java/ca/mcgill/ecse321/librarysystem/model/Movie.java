package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("M")
@Table(name = "movie")
public class Movie extends Item
{
	
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Movie Attributes
  private String title;
  private String director;

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

}