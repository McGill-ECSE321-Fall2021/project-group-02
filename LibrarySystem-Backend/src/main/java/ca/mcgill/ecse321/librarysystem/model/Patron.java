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
  private List<Album> borrowedAlbums;
  private List<Book> borrowedBooks;
  private List<Movie> borrowedMovies;

  //------------------------
  // INTERFACE
  //------------------------

  @OneToMany
  public List<Album> getBorrowedAlbums()
  {
    return this.borrowedAlbums;
  }
  
  @OneToMany
  public List<Book> getBorrowedBooks()
  {
    return this.borrowedBooks;
  }
  
  @OneToMany
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