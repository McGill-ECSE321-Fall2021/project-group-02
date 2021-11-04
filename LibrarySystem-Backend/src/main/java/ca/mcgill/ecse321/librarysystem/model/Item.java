package ca.mcgill.ecse321.librarysystem.model;

import java.util.*;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "item_type")
@Table(name = "item")
public abstract class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private boolean isArchived;
  private boolean isBorrowed;
  private boolean isDamaged;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;


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

}