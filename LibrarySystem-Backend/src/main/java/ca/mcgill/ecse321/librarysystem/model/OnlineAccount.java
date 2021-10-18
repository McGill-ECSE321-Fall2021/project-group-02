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
  // INTERFACE
  //------------------------

  public void setUsername(String aUsername)
  {
    username = aUsername;
  }

  public void setEmail(String aEmail)
  {
    email = aEmail;
  }
  public void setPassword(String aPassword)
  {
    password = aPassword;
  }
  @Id
  public String getUsername()
  {
    return username;
  }

  public String getEmail(){
    return email;
  }
  public String getPassword()
  {
    return password;
  }

  @OneToOne
  public User getUser()
  {
    return user;
  }

  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }

  public void setUser(User aNewUser)
  {
    user = aNewUser;
  }

  public void setLibrarySoftwareSystem(LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    librarySoftwareSystem = aLibrarySoftwareSystem;
  }

}