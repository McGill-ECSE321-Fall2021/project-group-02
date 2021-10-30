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
  public List<Album> getBorrowedAlbum()
  {
    return this.borrowedAlbums;
  }
  
  @OneToMany
  public List<Book> getBorrowedBook()
  {
    return this.borrowedBooks;
  }
  
  @OneToMany
  public List<Movie> getBorrowedMovie()
  {
    return this.borrowedMovies;
  }
  

  public void setBorrowedAlbum(List<Album> a) {
	  this.borrowedAlbums = a;
  }
  
  public void setBorrowedBook(List<Book> b) {
	  this.borrowedBooks = b;
  }
  
  public void setBorrowedMovie(List<Movie> m) {
	  this.borrowedMovies = m;
  }
  
}