package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

@Entity
public class Person
{
  private int id;
  
  public void setId(int aId) {
    this.id = aId;
  }
  @GeneratedValue
  @Id
  public int getId() {
    return this.id;
  }
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String firstName;
  private String lastName;

  //Person Associations
  private List<User> users;
  private LibrarySoftwareSystem librarySoftwareSystem;

  //------------------------
  // INTERFACE
  //------------------------

  public void setFirstName(String aFirstName)
  {
    firstName = aFirstName;
  }

  public void setLastName(String aLastName)
  {
	this.lastName = aLastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  @OneToMany
  public List<User> getUser()
  {
	return this.users;
  }
  
  public void setUser(List<User> u) {
	  this.users = u;
  }

  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }


  public void setLibrarySoftwareSystem(LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    librarySoftwareSystem = aLibrarySoftwareSystem;
  }
}