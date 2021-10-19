package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "ITEM_TYPE")
@Table(name = "item1")
public abstract class Item
{

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
  // INTERFACE
  //------------------------

  public void setIsArchived(boolean aIsArchived)
  {
    isArchived = aIsArchived;
  }

  public void setIsBorrowed(boolean aIsBorrowed)
  {
    isBorrowed = aIsBorrowed;
  }

  public void setIsDamaged(boolean aIsDamaged)
  {
    isDamaged = aIsDamaged;
  }

  public void setId(int aId)
  {
	 this.id = aId;
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
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId()
  {
    return id;
  }

//  @ManyToOne(optional = false)
//  public LibrarySoftwareSystem getLibrarySoftwareSystem()
//  {
//    return librarySoftwareSystem;
//  }
//
//  public void setLibrarySoftwareSystem(LibrarySoftwareSystem aLibrarySoftwareSystem)
//  {
//	this.librarySoftwareSystem = aLibrarySoftwareSystem;
//  }

}