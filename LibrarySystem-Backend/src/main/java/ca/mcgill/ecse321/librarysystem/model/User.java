/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

// line 2 "model.ump"
// line 123 "model.ump"
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "USER_TYPE")
public class User
{
  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, User> usersById = new HashMap<Integer, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private int id;
  private String address;
  private String city;

  //User Associations
  private LibrarySoftwareSystem librarySoftwareSystem;
  private Person person;
  private OnlineAccount onlineAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(int aId, String aAddress, String aCity, LibrarySoftwareSystem aLibrarySoftwareSystem, Person aPerson)
  {
    address = aAddress;
    city = aCity;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrarySoftwareSystem = setLibrarySoftwareSystem(aLibrarySoftwareSystem);
    if (!didAddLibrarySoftwareSystem)
    {
      throw new RuntimeException("Unable to create user due to librarySoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddPerson = setPerson(aPerson);
    if (!didAddPerson)
    {
      throw new RuntimeException("Unable to create user due to person. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      usersById.remove(anOldId);
    }
    usersById.put(aId, this);
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }
  @Id
  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithId(int aId)
  {
    return usersById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }

  public String getAddress()
  {
    return address;
  }

  public String getCity()
  {
    return city;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }
  /* Code from template association_GetOne */
  @ManyToOne
  public Person getPerson()
  {
    return person;
  }
  /* Code from template association_GetOne */
  @OneToOne(optional = true)
  public OnlineAccount getOnlineAccount()
  {
    return onlineAccount;
  }

  public boolean hasOnlineAccount()
  {
    boolean has = onlineAccount != null;
    return has;
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
      existingLibrarySoftwareSystem.removeUser(this);
    }
    librarySoftwareSystem.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setPerson(Person aPerson)
  {
    boolean wasSet = false;
    //Must provide person to user
    if (aPerson == null)
    {
      return wasSet;
    }

    Person existingPerson = person;
    person = aPerson;
    if (existingPerson != null && !existingPerson.equals(aPerson))
    {
      boolean didRemove = existingPerson.removeUser(this);
      if (!didRemove)
      {
        person = existingPerson;
        return wasSet;
      }
    }
    person.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setOnlineAccount(OnlineAccount aNewOnlineAccount)
  {
    boolean wasSet = false;
    if (onlineAccount != null && !onlineAccount.equals(aNewOnlineAccount) && equals(onlineAccount.getUser()))
    {
      //Unable to setOnlineAccount, as existing onlineAccount would become an orphan
      return wasSet;
    }

    onlineAccount = aNewOnlineAccount;
    User anOldUser = aNewOnlineAccount != null ? aNewOnlineAccount.getUser() : null;

    if (!this.equals(anOldUser))
    {
      if (anOldUser != null)
      {
        anOldUser.onlineAccount = null;
      }
      if (onlineAccount != null)
      {
        onlineAccount.setUser(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersById.remove(getId());
    LibrarySoftwareSystem placeholderLibrarySoftwareSystem = librarySoftwareSystem;
    this.librarySoftwareSystem = null;
    if(placeholderLibrarySoftwareSystem != null)
    {
      placeholderLibrarySoftwareSystem.removeUser(this);
    }
    Person placeholderPerson = person;
    this.person = null;
    if(placeholderPerson != null)
    {
      placeholderPerson.removeUser(this);
    }
    OnlineAccount existingOnlineAccount = onlineAccount;
    onlineAccount = null;
    if (existingOnlineAccount != null)
    {
      existingOnlineAccount.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "address" + ":" + getAddress()+ "," +
            "city" + ":" + getCity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "librarySoftwareSystem = "+(getLibrarySoftwareSystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySoftwareSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "onlineAccount = "+(getOnlineAccount()!=null?Integer.toHexString(System.identityHashCode(getOnlineAccount())):"null");
  }
}