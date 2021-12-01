package ca.mcgill.ecse321.librarysystem.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("A")
@Table(name = "album")
public class Album extends Item
{
	

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  
  //Album Attributes
  private String title;
  private String artist;

  //------------------------
  // INTERFACE
  //------------------------
  
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

}