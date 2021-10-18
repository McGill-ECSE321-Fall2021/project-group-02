package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("A")
@Table(name = "ALBUM")
public class Album extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  
  //Album Attributes
  private String title;
  private String artist;

  //Album Associations
  private Patron patron;

  public void setTitle(String aTitle)
  {
    title = aTitle;
  }

  public void setArtist(String aArtist)
  {
    artist = aArtist;
  }

  public String getTitle()
  {
    return title;
  }

  public String getArtist()
  {
    return artist;
  }

  @ManyToOne(optional = true)
  public Patron getPatron()
  {
    return patron;
  }

  public void setPatron(Patron aPatron)
  { 
    patron = aPatron;
  }

}