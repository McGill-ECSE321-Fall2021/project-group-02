package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("P")
@Table(name = "patron")
public class Patron extends UserEntity
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Patron Associations
  @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
  private List<Album> borrowedAlbums;
  @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
  private List<Book> borrowedBooks;
  @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
  private List<Movie> borrowedMovies;

  //------------------------
  // INTERFACE
  //------------------------

  public List<Album> getBorrowedAlbums()
  {
    return this.borrowedAlbums;
  }
  
  public List<Book> getBorrowedBooks()
  {
    return this.borrowedBooks;
  }
  
  public List<Movie> getBorrowedMovies()
  {
    return this.borrowedMovies;
  }
  
  public void setBorrowedAlbums(List<Album> a) {
	  this.borrowedAlbums = a;
  }
  
  public void setBorrowedBooks(List<Book> b) {
	  this.borrowedBooks = b;
  }
  
  public void setBorrowedMovies(List<Movie> m) {
	  this.borrowedMovies = m;
  }
  
}