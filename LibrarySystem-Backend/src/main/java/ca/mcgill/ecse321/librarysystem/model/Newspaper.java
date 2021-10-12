/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 77 "model.ump"
// line 188 "model.ump"
public class Newspaper extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Newspaper Attributes
  private String name;
  private String date;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Newspaper(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem, String aName, String aDate)
  {
    super(aIsArchived, aIsBorrowed, aIsDamaged, aId, aLibrarySoftwareSystem);
    name = aName;
    date = aDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDate()
  {
    return date;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "date" + ":" + getDate()+ "]";
  }
}