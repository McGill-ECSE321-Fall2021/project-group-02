package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("P")
@Table(name = "PATRON")
public class Patron extends User
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Patron Associations
  private List<Album> borrowedAlbums;
  private List<Book> borrowedBooks;
  private List<Movie> borrowedMovies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Patron(int aId, String aAddress, String aCity, LibrarySoftwareSystem aLibrarySoftwareSystem, Person aPerson)
  {
    super(aId, aAddress, aCity, aLibrarySoftwareSystem, aPerson);
    borrowedAlbums = new ArrayList<Album>();
    borrowedBooks = new ArrayList<Book>();
    borrowedMovies = new ArrayList<Movie>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  
  public Album getBorrowedAlbum(int index)
  {
    Album aBorrowedAlbum = borrowedAlbums.get(index);
    return aBorrowedAlbum;
  }
  @Transient
  @OneToMany
  public List<Album> getBorrowedAlbums()
  {
    List<Album> newBorrowedAlbums = Collections.unmodifiableList(borrowedAlbums);
    return newBorrowedAlbums;
  }
  
  public int numberOfBorrowedAlbums()
  {
    int number = borrowedAlbums.size();
    return number;
  }

  public boolean hasBorrowedAlbums()
  {
    boolean has = borrowedAlbums.size() > 0;
    return has;
  }

  public int indexOfBorrowedAlbum(Album aBorrowedAlbum)
  {
    int index = borrowedAlbums.indexOf(aBorrowedAlbum);
    return index;
  }
  /* Code from template association_GetMany */

  public Book getBorrowedBook(int index)
  {
    Book aBorrowedBook = borrowedBooks.get(index);
    return aBorrowedBook;
  }
  @Transient
  @OneToMany
  public List<Book> getBorrowedBooks()
  {
    List<Book> newBorrowedBooks = Collections.unmodifiableList(borrowedBooks);
    return newBorrowedBooks;
  }

  public int numberOfBorrowedBooks()
  {
    int number = borrowedBooks.size();
    return number;
  }

  public boolean hasBorrowedBooks()
  {
    boolean has = borrowedBooks.size() > 0;
    return has;
  }

  public int indexOfBorrowedBook(Book aBorrowedBook)
  {
    int index = borrowedBooks.indexOf(aBorrowedBook);
    return index;
  }
  /* Code from template association_GetMany */

  public Movie getBorrowedMovy(int index)
  {
    Movie aBorrowedMovy = borrowedMovies.get(index);
    return aBorrowedMovy;
  }
  @Transient
  @OneToMany
  public List<Movie> getBorrowedMovies()
  {
    List<Movie> newBorrowedMovies = Collections.unmodifiableList(borrowedMovies);
    return newBorrowedMovies;
  }

  public int numberOfBorrowedMovies()
  {
    int number = borrowedMovies.size();
    return number;
  }

  public boolean hasBorrowedMovies()
  {
    boolean has = borrowedMovies.size() > 0;
    return has;
  }

  public int indexOfBorrowedMovy(Movie aBorrowedMovy)
  {
    int index = borrowedMovies.indexOf(aBorrowedMovy);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBorrowedAlbums()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfBorrowedAlbums()
  {
    return 5;
  }
  /* Code from template association_AddOptionalNToOne */
  public Album addBorrowedAlbum(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem, String aTitle, String aArtist)
  {
    if (numberOfBorrowedAlbums() >= maximumNumberOfBorrowedAlbums())
    {
      return null;
    }
    else
    {
      return new Album(aIsArchived, aIsBorrowed, aIsDamaged, aId, aLibrarySoftwareSystem, aTitle, aArtist, this);
    }
  }

  public boolean addBorrowedAlbum(Album aBorrowedAlbum)
  {
    boolean wasAdded = false;
    if (borrowedAlbums.contains(aBorrowedAlbum)) { return false; }
    if (numberOfBorrowedAlbums() >= maximumNumberOfBorrowedAlbums())
    {
      return wasAdded;
    }

    Patron existingPatron = aBorrowedAlbum.getPatron();
    boolean isNewPatron = existingPatron != null && !this.equals(existingPatron);
    if (isNewPatron)
    {
      aBorrowedAlbum.setPatron(this);
    }
    else
    {
      borrowedAlbums.add(aBorrowedAlbum);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBorrowedAlbum(Album aBorrowedAlbum)
  {
    boolean wasRemoved = false;
    //Unable to remove aBorrowedAlbum, as it must always have a patron
    if (!this.equals(aBorrowedAlbum.getPatron()))
    {
      borrowedAlbums.remove(aBorrowedAlbum);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBorrowedAlbumAt(Album aBorrowedAlbum, int index)
  {  
    boolean wasAdded = false;
    if(addBorrowedAlbum(aBorrowedAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBorrowedAlbums()) { index = numberOfBorrowedAlbums() - 1; }
      borrowedAlbums.remove(aBorrowedAlbum);
      borrowedAlbums.add(index, aBorrowedAlbum);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBorrowedAlbumAt(Album aBorrowedAlbum, int index)
  {
    boolean wasAdded = false;
    if(borrowedAlbums.contains(aBorrowedAlbum))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBorrowedAlbums()) { index = numberOfBorrowedAlbums() - 1; }
      borrowedAlbums.remove(aBorrowedAlbum);
      borrowedAlbums.add(index, aBorrowedAlbum);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBorrowedAlbumAt(aBorrowedAlbum, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBorrowedBooks()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfBorrowedBooks()
  {
    return 5;
  }
  /* Code from template association_AddOptionalNToOne */
  public Book addBorrowedBook(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem, String aTitle, String aAuthor)
  {
    if (numberOfBorrowedBooks() >= maximumNumberOfBorrowedBooks())
    {
      return null;
    }
    else
    {
      return new Book(aIsArchived, aIsBorrowed, aIsDamaged, aId, aLibrarySoftwareSystem, aTitle, aAuthor, this);
    }
  }

  public boolean addBorrowedBook(Book aBorrowedBook)
  {
    boolean wasAdded = false;
    if (borrowedBooks.contains(aBorrowedBook)) { return false; }
    if (numberOfBorrowedBooks() >= maximumNumberOfBorrowedBooks())
    {
      return wasAdded;
    }

    Patron existingPatron = aBorrowedBook.getPatron();
    boolean isNewPatron = existingPatron != null && !this.equals(existingPatron);
    if (isNewPatron)
    {
      aBorrowedBook.setPatron(this);
    }
    else
    {
      borrowedBooks.add(aBorrowedBook);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBorrowedBook(Book aBorrowedBook)
  {
    boolean wasRemoved = false;
    //Unable to remove aBorrowedBook, as it must always have a patron
    if (!this.equals(aBorrowedBook.getPatron()))
    {
      borrowedBooks.remove(aBorrowedBook);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBorrowedBookAt(Book aBorrowedBook, int index)
  {  
    boolean wasAdded = false;
    if(addBorrowedBook(aBorrowedBook))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBorrowedBooks()) { index = numberOfBorrowedBooks() - 1; }
      borrowedBooks.remove(aBorrowedBook);
      borrowedBooks.add(index, aBorrowedBook);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBorrowedBookAt(Book aBorrowedBook, int index)
  {
    boolean wasAdded = false;
    if(borrowedBooks.contains(aBorrowedBook))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBorrowedBooks()) { index = numberOfBorrowedBooks() - 1; }
      borrowedBooks.remove(aBorrowedBook);
      borrowedBooks.add(index, aBorrowedBook);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBorrowedBookAt(aBorrowedBook, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBorrowedMovies()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfBorrowedMovies()
  {
    return 5;
  }
  /* Code from template association_AddOptionalNToOne */
  public Movie addBorrowedMovy(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem, String aTitle, String aDirector)
  {
    if (numberOfBorrowedMovies() >= maximumNumberOfBorrowedMovies())
    {
      return null;
    }
    else
    {
      return new Movie(aIsArchived, aIsBorrowed, aIsDamaged, aId, aLibrarySoftwareSystem, aTitle, aDirector, this);
    }
  }

  public boolean addBorrowedMovy(Movie aBorrowedMovy)
  {
    boolean wasAdded = false;
    if (borrowedMovies.contains(aBorrowedMovy)) { return false; }
    if (numberOfBorrowedMovies() >= maximumNumberOfBorrowedMovies())
    {
      return wasAdded;
    }

    Patron existingPatron = aBorrowedMovy.getPatron();
    boolean isNewPatron = existingPatron != null && !this.equals(existingPatron);
    if (isNewPatron)
    {
      aBorrowedMovy.setPatron(this);
    }
    else
    {
      borrowedMovies.add(aBorrowedMovy);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBorrowedMovy(Movie aBorrowedMovy)
  {
    boolean wasRemoved = false;
    //Unable to remove aBorrowedMovy, as it must always have a patron
    if (!this.equals(aBorrowedMovy.getPatron()))
    {
      borrowedMovies.remove(aBorrowedMovy);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBorrowedMovyAt(Movie aBorrowedMovy, int index)
  {  
    boolean wasAdded = false;
    if(addBorrowedMovy(aBorrowedMovy))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBorrowedMovies()) { index = numberOfBorrowedMovies() - 1; }
      borrowedMovies.remove(aBorrowedMovy);
      borrowedMovies.add(index, aBorrowedMovy);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBorrowedMovyAt(Movie aBorrowedMovy, int index)
  {
    boolean wasAdded = false;
    if(borrowedMovies.contains(aBorrowedMovy))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBorrowedMovies()) { index = numberOfBorrowedMovies() - 1; }
      borrowedMovies.remove(aBorrowedMovy);
      borrowedMovies.add(index, aBorrowedMovy);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBorrowedMovyAt(aBorrowedMovy, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=borrowedAlbums.size(); i > 0; i--)
    {
      Album aBorrowedAlbum = borrowedAlbums.get(i - 1);
      aBorrowedAlbum.delete();
    }
    for(int i=borrowedBooks.size(); i > 0; i--)
    {
      Book aBorrowedBook = borrowedBooks.get(i - 1);
      aBorrowedBook.delete();
    }
    for(int i=borrowedMovies.size(); i > 0; i--)
    {
      Movie aBorrowedMovy = borrowedMovies.get(i - 1);
      aBorrowedMovy.delete();
    }
    super.delete();
  }

}