/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

// line 56 "model.ump"
// line 173 "model.ump"
@Entity
public class Book extends Item
{
  private int bookId;
  
  public void setBookId(int aId) {
    this.bookId = aId;
  }
  @Id
  public int getBookId() {
    return this.bookId;
  }
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Book Attributes
  private String title;
  private String author;

  //Book Associations
  private Patron patron;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Book(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem, String aTitle, String aAuthor, Patron aPatron)
  {
    super(aIsArchived, aIsBorrowed, aIsDamaged, aId, aLibrarySoftwareSystem);
    title = aTitle;
    author = aAuthor;
    boolean didAddPatron = setPatron(aPatron);
    if (!didAddPatron)
    {
      throw new RuntimeException("Unable to create borrowedBook due to patron. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthor(String aAuthor)
  {
    boolean wasSet = false;
    author = aAuthor;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getAuthor()
  {
    return author;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = true)
  public Patron getPatron()
  {
    return patron;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setPatron(Patron aPatron)
  {
    boolean wasSet = false;
    //Must provide patron to borrowedBook
    if (aPatron == null)
    {
      return wasSet;
    }

    //patron already at maximum (5)
    if (aPatron.numberOfBorrowedBooks() >= Patron.maximumNumberOfBorrowedBooks())
    {
      return wasSet;
    }
    
    Patron existingPatron = patron;
    patron = aPatron;
    if (existingPatron != null && !existingPatron.equals(aPatron))
    {
      boolean didRemove = existingPatron.removeBorrowedBook(this);
      if (!didRemove)
      {
        patron = existingPatron;
        return wasSet;
      }
    }
    patron.addBorrowedBook(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Patron placeholderPatron = patron;
    this.patron = null;
    if(placeholderPatron != null)
    {
      placeholderPatron.removeBorrowedBook(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "author" + ":" + getAuthor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "patron = "+(getPatron()!=null?Integer.toHexString(System.identityHashCode(getPatron())):"null");
  }
}