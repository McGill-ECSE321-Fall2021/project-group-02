/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;

// line 40 "model.ump"
// line 162 "model.ump"
@Entity
public class Person
{
  private int id;
  
  public void setId(int aId) {
    this.id = aId;
  }
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
  // CONSTRUCTOR
  //------------------------

  public Person(String aFirstName, String aLastName, LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    firstName = aFirstName;
    lastName = aLastName;
    users = new ArrayList<User>();
    boolean didAddLibrarySoftwareSystem = setLibrarySoftwareSystem(aLibrarySoftwareSystem);
    if (!didAddLibrarySoftwareSystem)
    {
      throw new RuntimeException("Unable to create person due to librarySoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }
  @Transient
  @OneToMany
  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }
  /* Code from template association_IsNumberOfValidMethod */


  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }

    Person existingPerson = aUser.getPerson();
    boolean isNewPerson = existingPerson != null && !this.equals(existingPerson);

    if (isNewPerson)
    {
      aUser.setPerson(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a person
    if (this.equals(aUser.getPerson()))
    {
      return wasRemoved;
    }
    users.remove(aUser);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrarySoftwareSystem(LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    boolean wasSet = false;
    if (aLibrarySoftwareSystem == null)
    {
      return wasSet;
    }

    LibrarySoftwareSystem existingLibrarySoftwareSystem = librarySoftwareSystem;
    librarySoftwareSystem = aLibrarySoftwareSystem;
    if (existingLibrarySoftwareSystem != null && !existingLibrarySoftwareSystem.equals(aLibrarySoftwareSystem))
    {
      existingLibrarySoftwareSystem.removePerson(this);
    }
    librarySoftwareSystem.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=users.size(); i > 0; i--)
    {
      User aUser = users.get(i - 1);
      aUser.delete();
    }
    LibrarySoftwareSystem placeholderLibrarySoftwareSystem = librarySoftwareSystem;
    this.librarySoftwareSystem = null;
    if(placeholderLibrarySoftwareSystem != null)
    {
      placeholderLibrarySoftwareSystem.removePerson(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "librarySoftwareSystem = "+(getLibrarySoftwareSystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySoftwareSystem())):"null");
  }
}