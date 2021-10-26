package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "user_entity")
public abstract class UserEntity
{

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
  // INTERFACE
  //------------------------

  public void setId(int aId)
  {
	  this.id = aId;
  }

  public void setAddress(String aAddress)
  {
    address = aAddress;
  }

  public void setCity(String aCity)
  {
    city = aCity;
  }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId()
  {
    return id;
  }

  public String getAddress()
  {
    return address;
  }

  public String getCity()
  {
    return city;
  }

  @ManyToOne(optional = false)
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
  }

  @ManyToOne(optional = false)
  public Person getPerson()
  {
    return person;
  }

  @OneToOne(optional = true)
  public OnlineAccount getOnlineAccount()
  {
    return onlineAccount;
  }


  public void setLibrarySoftwareSystem(LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    librarySoftwareSystem = aLibrarySoftwareSystem;
  }

  public void setPerson(Person aPerson)
  {
    person = aPerson;
  }

  public void setOnlineAccount(OnlineAccount aOnlineAccount)
  {
    onlineAccount = aOnlineAccount;
  }
}