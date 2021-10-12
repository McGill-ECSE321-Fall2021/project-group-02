/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 9 "model.ump"
// line 128 "model.ump"
public class LibrarySoftwareSystem
{

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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LibrarySoftwareSystem(Library aOpeningHours)
  {
    persons = new ArrayList<Person>();
    items = new ArrayList<Item>();
    if (aOpeningHours == null || aOpeningHours.getLibrarySoftwareSystem() != null)
    {
      throw new RuntimeException("Unable to create LibrarySoftwareSystem due to aOpeningHours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    openingHours = aOpeningHours;
    accounts = new ArrayList<OnlineAccount>();
    users = new ArrayList<User>();
    weeklySchedules = new ArrayList<WeeklySchedule>();
    dailySchedules = new ArrayList<DailySchedule>();
  }

  public LibrarySoftwareSystem(Time aOpeningHourForOpeningHours, Time aClosingHourForOpeningHours)
  {
    persons = new ArrayList<Person>();
    items = new ArrayList<Item>();
    openingHours = new Library(aOpeningHourForOpeningHours, aClosingHourForOpeningHours, this);
    accounts = new ArrayList<OnlineAccount>();
    users = new ArrayList<User>();
    weeklySchedules = new ArrayList<WeeklySchedule>();
    dailySchedules = new ArrayList<DailySchedule>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetOne */
  public Library getOpeningHours()
  {
    return openingHours;
  }
  /* Code from template association_GetMany */
  public OnlineAccount getAccount(int index)
  {
    OnlineAccount aAccount = accounts.get(index);
    return aAccount;
  }

  public List<OnlineAccount> getAccounts()
  {
    List<OnlineAccount> newAccounts = Collections.unmodifiableList(accounts);
    return newAccounts;
  }

  public int numberOfAccounts()
  {
    int number = accounts.size();
    return number;
  }

  public boolean hasAccounts()
  {
    boolean has = accounts.size() > 0;
    return has;
  }

  public int indexOfAccount(OnlineAccount aAccount)
  {
    int index = accounts.indexOf(aAccount);
    return index;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

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
  /* Code from template association_GetMany */
  public WeeklySchedule getWeeklySchedule(int index)
  {
    WeeklySchedule aWeeklySchedule = weeklySchedules.get(index);
    return aWeeklySchedule;
  }

  public List<WeeklySchedule> getWeeklySchedules()
  {
    List<WeeklySchedule> newWeeklySchedules = Collections.unmodifiableList(weeklySchedules);
    return newWeeklySchedules;
  }

  public int numberOfWeeklySchedules()
  {
    int number = weeklySchedules.size();
    return number;
  }

  public boolean hasWeeklySchedules()
  {
    boolean has = weeklySchedules.size() > 0;
    return has;
  }

  public int indexOfWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    int index = weeklySchedules.indexOf(aWeeklySchedule);
    return index;
  }
  /* Code from template association_GetMany */
  public DailySchedule getDailySchedule(int index)
  {
    DailySchedule aDailySchedule = dailySchedules.get(index);
    return aDailySchedule;
  }

  public List<DailySchedule> getDailySchedules()
  {
    List<DailySchedule> newDailySchedules = Collections.unmodifiableList(dailySchedules);
    return newDailySchedules;
  }

  public int numberOfDailySchedules()
  {
    int number = dailySchedules.size();
    return number;
  }

  public boolean hasDailySchedules()
  {
    boolean has = dailySchedules.size() > 0;
    return has;
  }

  public int indexOfDailySchedule(DailySchedule aDailySchedule)
  {
    int index = dailySchedules.indexOf(aDailySchedule);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Person addPerson(String aFirstName, String aLastName)
  {
    return new Person(aFirstName, aLastName, this);
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    LibrarySoftwareSystem existingLibrarySoftwareSystem = aPerson.getLibrarySoftwareSystem();
    boolean isNewLibrarySoftwareSystem = existingLibrarySoftwareSystem != null && !this.equals(existingLibrarySoftwareSystem);
    if (isNewLibrarySoftwareSystem)
    {
      aPerson.setLibrarySoftwareSystem(this);
    }
    else
    {
      persons.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a librarySoftwareSystem
    if (!this.equals(aPerson.getLibrarySoftwareSystem()))
    {
      persons.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId)
  {
    return new Item(aIsArchived, aIsBorrowed, aIsDamaged, aId, this);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    LibrarySoftwareSystem existingLibrarySoftwareSystem = aItem.getLibrarySoftwareSystem();
    boolean isNewLibrarySoftwareSystem = existingLibrarySoftwareSystem != null && !this.equals(existingLibrarySoftwareSystem);
    if (isNewLibrarySoftwareSystem)
    {
      aItem.setLibrarySoftwareSystem(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a librarySoftwareSystem
    if (!this.equals(aItem.getLibrarySoftwareSystem()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OnlineAccount addAccount(String aUsername, String aPassword, User aUser)
  {
    return new OnlineAccount(aUsername, aPassword, aUser, this);
  }

  public boolean addAccount(OnlineAccount aAccount)
  {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) { return false; }
    LibrarySoftwareSystem existingLibrarySoftwareSystem = aAccount.getLibrarySoftwareSystem();
    boolean isNewLibrarySoftwareSystem = existingLibrarySoftwareSystem != null && !this.equals(existingLibrarySoftwareSystem);
    if (isNewLibrarySoftwareSystem)
    {
      aAccount.setLibrarySoftwareSystem(this);
    }
    else
    {
      accounts.add(aAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAccount(OnlineAccount aAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aAccount, as it must always have a librarySoftwareSystem
    if (!this.equals(aAccount.getLibrarySoftwareSystem()))
    {
      accounts.remove(aAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAccountAt(OnlineAccount aAccount, int index)
  {  
    boolean wasAdded = false;
    if(addAccount(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAccountAt(OnlineAccount aAccount, int index)
  {
    boolean wasAdded = false;
    if(accounts.contains(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAccountAt(aAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(int aId, String aAddress, String aCity, Person aPerson)
  {
    return new User(aId, aAddress, aCity, this, aPerson);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    LibrarySoftwareSystem existingLibrarySoftwareSystem = aUser.getLibrarySoftwareSystem();
    boolean isNewLibrarySoftwareSystem = existingLibrarySoftwareSystem != null && !this.equals(existingLibrarySoftwareSystem);
    if (isNewLibrarySoftwareSystem)
    {
      aUser.setLibrarySoftwareSystem(this);
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
    //Unable to remove aUser, as it must always have a librarySoftwareSystem
    if (!this.equals(aUser.getLibrarySoftwareSystem()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeklySchedules()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public WeeklySchedule addWeeklySchedule(Date aStartDate, Date aEndDate)
  {
    return new WeeklySchedule(aStartDate, aEndDate, this);
  }

  public boolean addWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    boolean wasAdded = false;
    if (weeklySchedules.contains(aWeeklySchedule)) { return false; }
    LibrarySoftwareSystem existingLibrarySoftwareSystem = aWeeklySchedule.getLibrarySoftwareSystem();
    boolean isNewLibrarySoftwareSystem = existingLibrarySoftwareSystem != null && !this.equals(existingLibrarySoftwareSystem);
    if (isNewLibrarySoftwareSystem)
    {
      aWeeklySchedule.setLibrarySoftwareSystem(this);
    }
    else
    {
      weeklySchedules.add(aWeeklySchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeeklySchedule(WeeklySchedule aWeeklySchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeeklySchedule, as it must always have a librarySoftwareSystem
    if (!this.equals(aWeeklySchedule.getLibrarySoftwareSystem()))
    {
      weeklySchedules.remove(aWeeklySchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeeklyScheduleAt(WeeklySchedule aWeeklySchedule, int index)
  {  
    boolean wasAdded = false;
    if(addWeeklySchedule(aWeeklySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeklySchedules()) { index = numberOfWeeklySchedules() - 1; }
      weeklySchedules.remove(aWeeklySchedule);
      weeklySchedules.add(index, aWeeklySchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeeklyScheduleAt(WeeklySchedule aWeeklySchedule, int index)
  {
    boolean wasAdded = false;
    if(weeklySchedules.contains(aWeeklySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeklySchedules()) { index = numberOfWeeklySchedules() - 1; }
      weeklySchedules.remove(aWeeklySchedule);
      weeklySchedules.add(index, aWeeklySchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeeklyScheduleAt(aWeeklySchedule, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDailySchedules()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DailySchedule addDailySchedule(DailySchedule.WeekDay aDay, Time aStartTime, Time aEndTime, WeeklySchedule aWeeklySchedule)
  {
    return new DailySchedule(aDay, aStartTime, aEndTime, this, aWeeklySchedule);
  }

  public boolean addDailySchedule(DailySchedule aDailySchedule)
  {
    boolean wasAdded = false;
    if (dailySchedules.contains(aDailySchedule)) { return false; }
    LibrarySoftwareSystem existingLibrarySoftwareSystem = aDailySchedule.getLibrarySoftwareSystem();
    boolean isNewLibrarySoftwareSystem = existingLibrarySoftwareSystem != null && !this.equals(existingLibrarySoftwareSystem);
    if (isNewLibrarySoftwareSystem)
    {
      aDailySchedule.setLibrarySoftwareSystem(this);
    }
    else
    {
      dailySchedules.add(aDailySchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDailySchedule(DailySchedule aDailySchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aDailySchedule, as it must always have a librarySoftwareSystem
    if (!this.equals(aDailySchedule.getLibrarySoftwareSystem()))
    {
      dailySchedules.remove(aDailySchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDailyScheduleAt(DailySchedule aDailySchedule, int index)
  {  
    boolean wasAdded = false;
    if(addDailySchedule(aDailySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDailySchedules()) { index = numberOfDailySchedules() - 1; }
      dailySchedules.remove(aDailySchedule);
      dailySchedules.add(index, aDailySchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDailyScheduleAt(DailySchedule aDailySchedule, int index)
  {
    boolean wasAdded = false;
    if(dailySchedules.contains(aDailySchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDailySchedules()) { index = numberOfDailySchedules() - 1; }
      dailySchedules.remove(aDailySchedule);
      dailySchedules.add(index, aDailySchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDailyScheduleAt(aDailySchedule, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (persons.size() > 0)
    {
      Person aPerson = persons.get(persons.size() - 1);
      aPerson.delete();
      persons.remove(aPerson);
    }
    
    while (items.size() > 0)
    {
      Item aItem = items.get(items.size() - 1);
      aItem.delete();
      items.remove(aItem);
    }
    
    Library existingOpeningHours = openingHours;
    openingHours = null;
    if (existingOpeningHours != null)
    {
      existingOpeningHours.delete();
    }
    while (accounts.size() > 0)
    {
      OnlineAccount aAccount = accounts.get(accounts.size() - 1);
      aAccount.delete();
      accounts.remove(aAccount);
    }
    
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (weeklySchedules.size() > 0)
    {
      WeeklySchedule aWeeklySchedule = weeklySchedules.get(weeklySchedules.size() - 1);
      aWeeklySchedule.delete();
      weeklySchedules.remove(aWeeklySchedule);
    }
    
    while (dailySchedules.size() > 0)
    {
      DailySchedule aDailySchedule = dailySchedules.get(dailySchedules.size() - 1);
      aDailySchedule.delete();
      dailySchedules.remove(aDailySchedule);
    }
    
  }

}