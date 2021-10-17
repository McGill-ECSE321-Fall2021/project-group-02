/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

// line 91 "model.ump"
// line 198 "model.ump"
@Entity
public class OnlineAccount
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, OnlineAccount> onlineaccountsByUsername = new HashMap<String, OnlineAccount>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OnlineAccount Attributes
  private String username;
  private String password;
  private String email;

  //OnlineAccount Associations
  private User user;
  private LibrarySoftwareSystem librarySoftwareSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OnlineAccount(String aUsername, String aEmail, String aPassword, User aUser, LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    password = aPassword;
    email = aEmail;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create onlineAccount due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLibrarySoftwareSystem = setLibrarySoftwareSystem(aLibrarySoftwareSystem);
    if (!didAddLibrarySoftwareSystem)
    {
      throw new RuntimeException("Unable to create account due to librarySoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      onlineaccountsByUsername.remove(anOldUsername);
    }
    onlineaccountsByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    password = aEmail;
    wasSet = true;
    return wasSet;
  }
  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }
  @Id
  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static OnlineAccount getWithUsername(String aUsername)
  {
    return onlineaccountsByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getEmail(){
    return email;
  }
  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  @OneToOne
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (aNewUser == null)
    {
      //Unable to setUser to null, as onlineAccount must always be associated to a user
      return wasSet;
    }
    
    OnlineAccount existingOnlineAccount = aNewUser.getOnlineAccount();
    if (existingOnlineAccount != null && !equals(existingOnlineAccount))
    {
      //Unable to setUser, the current user already has a onlineAccount, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    User anOldUser = user;
    user = aNewUser;
    user.setOnlineAccount(this);

    if (anOldUser != null)
    {
      anOldUser.setOnlineAccount(null);
    }
    wasSet = true;
    return wasSet;
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
      existingLibrarySoftwareSystem.removeAccount(this);
    }
    librarySoftwareSystem.addAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    onlineaccountsByUsername.remove(getUsername());
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.setOnlineAccount(null);
    }
    LibrarySoftwareSystem placeholderLibrarySoftwareSystem = librarySoftwareSystem;
    this.librarySoftwareSystem = null;
    if(placeholderLibrarySoftwareSystem != null)
    {
      placeholderLibrarySoftwareSystem.removeAccount(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "librarySoftwareSystem = "+(getLibrarySoftwareSystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySoftwareSystem())):"null");
  }
}