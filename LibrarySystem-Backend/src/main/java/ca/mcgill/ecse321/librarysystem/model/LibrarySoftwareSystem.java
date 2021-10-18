/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
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

// line 9 "model.ump"
// line 128 "model.ump"
@Entity
public class LibrarySoftwareSystem
{
  
  private int id;
  
  public void setId(int aId) {
    this.id = aId;
  }
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return this.id;
  }
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LibrarySoftwareSystem Associations
  private List<Person> persons;
  private List<Item> items;
  private Library openingHours;
  private List<OnlineAccount> accounts;
  private List<User> users;
  private List<WeeklySchedule> weeklySchedules;
  private List<DailySchedule> dailySchedules;

//----------------------------------------------GETTERS
  
  @OneToMany(cascade=CascadeType.ALL)
  public List<Person> getPerson()
  {
    return this.persons;
  }
  
  @OneToMany(cascade=CascadeType.ALL)
  public List<Item> getItem()
  {
    return this.items;
  }

  @OneToOne
  public Library getOpeningHours()
  {
    return this.openingHours;
  }

  @OneToMany(cascade=CascadeType.ALL)
  public List<OnlineAccount> getAccount()
  {
    return this.accounts;
  }

  @OneToMany(cascade=CascadeType.ALL)
  public List<User> getUser()
  {
    return this.users;
  }

  @OneToMany(cascade=CascadeType.ALL)
  public List<WeeklySchedule> getWeeklySchedule()
  {
    return this.weeklySchedules;
  }

  @OneToMany(cascade=CascadeType.ALL)
  public List<DailySchedule> getDailySchedule()
  {
    return this.dailySchedules;
  }
  
  //----------------------------------------------SETTERS
  
  
  public void setPerson(List<Person> p)
  {
    this.persons = p;
  }
  
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

  public void setUser(List<User> u)
  {
    this.users = u;
  }

  public void setWeeklySchedule(List<WeeklySchedule> w)
  {
    this.weeklySchedules = w;
  }

  public void setDailySchedule(List<DailySchedule> d)
  {
    this.dailySchedules = d;
  }


}