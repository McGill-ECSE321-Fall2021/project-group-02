package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

@Table(name="person")
@Entity
public class Person
{
  private int id;
  
  public void setId(int aId) {
    this.id = aId;
  }
  @GeneratedValue(strategy = GenerationType.AUTO)
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
  private List<UserEntity> userEntities;
//  private LibrarySoftwareSystem librarySoftwareSystem;

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
  public List<UserEntity> getUserEntity()
  {
	return this.userEntities;
  }
  
  public void setUserEntity(List<UserEntity> u) {
	  this.userEntities = u;
  }

//  @ManyToOne(optional = false)
//  public LibrarySoftwareSystem getLibrarySoftwareSystem()
//  {
//    return librarySoftwareSystem;
//  }
//
//
//  public void setLibrarySoftwareSystem(LibrarySoftwareSystem aLibrarySoftwareSystem)
//  {
//    librarySoftwareSystem = aLibrarySoftwareSystem;
//  }
}