/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;

// line 48 "model.ump"
// line 168 "model.ump"
public class Item
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Item> itemsById = new HashMap<Integer, Item>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private boolean isArchived;
  private boolean isBorrowed;
  private boolean isDamaged;
  private int id;

  //Item Associations
  private LibrarySoftwareSystem librarySoftwareSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem)
  {
    isArchived = aIsArchived;
    isBorrowed = aIsBorrowed;
    isDamaged = aIsDamaged;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddLibrarySoftwareSystem = setLibrarySoftwareSystem(aLibrarySoftwareSystem);
    if (!didAddLibrarySoftwareSystem)
    {
      throw new RuntimeException("Unable to create item due to librarySoftwareSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsArchived(boolean aIsArchived)
  {
    boolean wasSet = false;
    isArchived = aIsArchived;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsBorrowed(boolean aIsBorrowed)
  {
    boolean wasSet = false;
    isBorrowed = aIsBorrowed;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsDamaged(boolean aIsDamaged)
  {
    boolean wasSet = false;
    isDamaged = aIsDamaged;
    wasSet = true;
    return wasSet;
  }

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
      itemsById.remove(anOldId);
    }
    itemsById.put(aId, this);
    return wasSet;
  }

  public boolean getIsArchived()
  {
    return isArchived;
  }

  public boolean getIsBorrowed()
  {
    return isBorrowed;
  }

  public boolean getIsDamaged()
  {
    return isDamaged;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static Item getWithId(int aId)
  {
    return itemsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }
  /* Code from template association_GetOne */
  public LibrarySoftwareSystem getLibrarySoftwareSystem()
  {
    return librarySoftwareSystem;
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
      existingLibrarySoftwareSystem.removeItem(this);
    }
    librarySoftwareSystem.addItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    itemsById.remove(getId());
    LibrarySoftwareSystem placeholderLibrarySoftwareSystem = librarySoftwareSystem;
    this.librarySoftwareSystem = null;
    if(placeholderLibrarySoftwareSystem != null)
    {
      placeholderLibrarySoftwareSystem.removeItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "isArchived" + ":" + getIsArchived()+ "," +
            "isBorrowed" + ":" + getIsBorrowed()+ "," +
            "isDamaged" + ":" + getIsDamaged()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "librarySoftwareSystem = "+(getLibrarySoftwareSystem()!=null?Integer.toHexString(System.identityHashCode(getLibrarySoftwareSystem())):"null");
  }
}