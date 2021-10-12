/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;

// line 70 "model.ump"
// line 183 "model.ump"
public class Album extends Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Album Attributes
  private String title;
  private String artist;

  //Album Associations
  private Patron patron;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Album(boolean aIsArchived, boolean aIsBorrowed, boolean aIsDamaged, int aId, LibrarySoftwareSystem aLibrarySoftwareSystem, String aTitle, String aArtist, Patron aPatron)
  {
    super(aIsArchived, aIsBorrowed, aIsDamaged, aId, aLibrarySoftwareSystem);
    title = aTitle;
    artist = aArtist;
    boolean didAddPatron = setPatron(aPatron);
    if (!didAddPatron)
    {
      throw new RuntimeException("Unable to create borrowedAlbum due to patron. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setArtist(String aArtist)
  {
    boolean wasSet = false;
    artist = aArtist;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getArtist()
  {
    return artist;
  }
  /* Code from template association_GetOne */
  public Patron getPatron()
  {
    return patron;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setPatron(Patron aPatron)
  {
    boolean wasSet = false;
    //Must provide patron to borrowedAlbum
    if (aPatron == null)
    {
      return wasSet;
    }

    //patron already at maximum (5)
    if (aPatron.numberOfBorrowedAlbums() >= Patron.maximumNumberOfBorrowedAlbums())
    {
      return wasSet;
    }
    
    Patron existingPatron = patron;
    patron = aPatron;
    if (existingPatron != null && !existingPatron.equals(aPatron))
    {
      boolean didRemove = existingPatron.removeBorrowedAlbum(this);
      if (!didRemove)
      {
        patron = existingPatron;
        return wasSet;
      }
    }
    patron.addBorrowedAlbum(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Patron placeholderPatron = patron;
    this.patron = null;
    if(placeholderPatron != null)
    {
      placeholderPatron.removeBorrowedAlbum(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "artist" + ":" + getArtist()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "patron = "+(getPatron()!=null?Integer.toHexString(System.identityHashCode(getPatron())):"null");
  }
}