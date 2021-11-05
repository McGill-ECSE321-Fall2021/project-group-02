package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import java.sql.Time;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class LibrarySoftwareSystem
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) 
  private int id;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibrarySoftwareSystem Associations
  @OneToMany(cascade=CascadeType.ALL)
  private List<Item> items;
  @OneToOne(cascade=CascadeType.ALL)
  private Library openingHours;
  @OneToMany(cascade=CascadeType.ALL)
  private List<OnlineAccount> accounts;
  @OneToMany(cascade=CascadeType.ALL)
  private List<UserEntity> userEntities;
  @OneToMany(cascade=CascadeType.ALL)
  private List<WeeklySchedule> weeklySchedules;

//----------------------------------------------GETTERS
  
  public int getId() {
    return this.id;
  }
  
  public List<Item> getItem()
  {
    return this.items;
  }

  public Library getOpeningHours()
  {
    return this.openingHours;
  }

  public List<OnlineAccount> getAccount()
  {
    return this.accounts;
  }

  public List<UserEntity> getUserEntity()
  {
    return this.userEntities;
  }

  public List<WeeklySchedule> getWeeklySchedule()
  {
    return this.weeklySchedules;
  }
  
  //----------------------------------------------SETTERS
  
  public void setItem(List<Item> i)
  {
    this.items = i;
  }

  public void setOpeningHours(Library o)
  {
    this.openingHours = o;
  }

  public void setAccount(List<OnlineAccount> a)
  {
    this.accounts = a;
  }

  public void setUserEntity(List<UserEntity> u)
  {
    this.userEntities = u;
  }

  public void setWeeklySchedule(List<WeeklySchedule> w)
  {
    this.weeklySchedules = w;
  }

}