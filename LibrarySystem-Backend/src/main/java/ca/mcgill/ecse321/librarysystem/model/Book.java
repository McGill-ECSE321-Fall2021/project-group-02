package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("B")
@Table(name = "BOOK")
public class Book extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Book Attributes
  private String title;
  private String author;

  //Book Associations
  private Patron patron;

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