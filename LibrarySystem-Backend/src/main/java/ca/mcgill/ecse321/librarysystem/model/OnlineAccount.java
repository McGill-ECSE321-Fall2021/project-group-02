package ca.mcgill.ecse321.librarysystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "onlineAccount")
public class OnlineAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OnlineAccount Attributes
  @Column(unique = true)
  private String username;
  @Id
  @Column(name = "account_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String password;
  private String email;
  private boolean loggedIn;

  //OnlineAccount Associations
  @OneToOne
  private UserEntity userEntity;

  
  
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
  
  public void setLoggedIn(boolean loggedIn) {
	  this.loggedIn = loggedIn;
  }

  public String getUsername()
  {
    return username;
  }

  public boolean getLoggedIn() {
	  return loggedIn;
  }
  
  public String getEmail(){
    return email;
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id) {
	  this.id = id;
  }

  public UserEntity getUser()
  {
    return userEntity;
  }
  
  public void setUser(UserEntity aNewUser)
  {
    userEntity = aNewUser;
  }
}