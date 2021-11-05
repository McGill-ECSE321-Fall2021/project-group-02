package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "user_type")
@Table(name = "userEntity")
public abstract class UserEntity
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;
  
  private String address;
  private String city;
  private String firstName;
  private String lastName;
  private int balance;
  
  //User Associations
  @OneToOne(optional = true, cascade = CascadeType.ALL)
  private OnlineAccount onlineAccount;
  
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
  
  public int getBalance()
  {
    return balance;
  }

  public void setBalance(int b)
  {
    this.balance = b;
  }

  public void setAddress(String aAddress)
  {
    address = aAddress;
  }

  public void setCity(String aCity)
  {
    city = aCity;
  }

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

  public OnlineAccount getOnlineAccount()
  {
    return onlineAccount;
  }

  public void setOnlineAccount(OnlineAccount aOnlineAccount)
  {
    onlineAccount = aOnlineAccount;
  }
}