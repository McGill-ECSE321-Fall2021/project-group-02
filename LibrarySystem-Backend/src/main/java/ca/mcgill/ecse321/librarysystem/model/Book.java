package ca.mcgill.ecse321.librarysystem.model;


import javax.persistence.*;

@Entity
@DiscriminatorValue("B")
@Table(name = "book")
public class Book extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Book Attributes
  private String title;
  private String author;

  //------------------------
  // INTERFACE
  //------------------------
  
  public void setTitle(String aTitle)
  {
    title = aTitle;
  }

  public void setAuthor(String aAuthor)
  {
    author = aAuthor;
  }

  public String getTitle()
  {
    return title;
  }

  public String getAuthor()
  {
    return author;
  }
}