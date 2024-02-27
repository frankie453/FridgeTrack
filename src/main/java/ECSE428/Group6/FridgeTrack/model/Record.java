package ECSE428.Group6.FridgeTrack.model;


import java.sql.Date;
import java.util.*;

// line 32 "model.ump"
// line 101 "model.ump"
public class Record
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Record Attributes
  private int id;
  private Date enterDate;
  private int quantity;
  private Date expiryDate;

  //Record Associations
  private List<WarningNotification> warningNotifications;
  private Item item;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Record(int aId, Date aEnterDate, int aQuantity, Date aExpiryDate, Item aItem)
  {
    id = aId;
    enterDate = aEnterDate;
    quantity = aQuantity;
    expiryDate = aExpiryDate;
    warningNotifications = new ArrayList<WarningNotification>();
    boolean didAddItem = setItem(aItem);
    if (!didAddItem)
    {
      throw new RuntimeException("Unable to create record due to item. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setEnterDate(Date aEnterDate)
  {
    boolean wasSet = false;
    enterDate = aEnterDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setExpiryDate(Date aExpiryDate)
  {
    boolean wasSet = false;
    expiryDate = aExpiryDate;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public Date getEnterDate()
  {
    return enterDate;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public Date getExpiryDate()
  {
    return expiryDate;
  }
  /* Code from template association_GetMany */
  public WarningNotification getWarningNotification(int index)
  {
    WarningNotification aWarningNotification = warningNotifications.get(index);
    return aWarningNotification;
  }

  public List<WarningNotification> getWarningNotifications()
  {
    List<WarningNotification> newWarningNotifications = Collections.unmodifiableList(warningNotifications);
    return newWarningNotifications;
  }

  public int numberOfWarningNotifications()
  {
    int number = warningNotifications.size();
    return number;
  }

  public boolean hasWarningNotifications()
  {
    boolean has = warningNotifications.size() > 0;
    return has;
  }

  public int indexOfWarningNotification(WarningNotification aWarningNotification)
  {
    int index = warningNotifications.indexOf(aWarningNotification);
    return index;
  }
  /* Code from template association_GetOne */
  public Item getItem()
  {
    return item;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWarningNotifications()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public WarningNotification addWarningNotification(String aId, WarningNotification.Warning aExpiryWarningLevel)
  {
    return new WarningNotification(aId, aExpiryWarningLevel, this);
  }

  public boolean addWarningNotification(WarningNotification aWarningNotification)
  {
    boolean wasAdded = false;
    if (warningNotifications.contains(aWarningNotification)) { return false; }
    Record existingRecord = aWarningNotification.getRecord();
    boolean isNewRecord = existingRecord != null && !this.equals(existingRecord);
    if (isNewRecord)
    {
      aWarningNotification.setRecord(this);
    }
    else
    {
      warningNotifications.add(aWarningNotification);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWarningNotification(WarningNotification aWarningNotification)
  {
    boolean wasRemoved = false;
    //Unable to remove aWarningNotification, as it must always have a record
    if (!this.equals(aWarningNotification.getRecord()))
    {
      warningNotifications.remove(aWarningNotification);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWarningNotificationAt(WarningNotification aWarningNotification, int index)
  {  
    boolean wasAdded = false;
    if(addWarningNotification(aWarningNotification))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWarningNotifications()) { index = numberOfWarningNotifications() - 1; }
      warningNotifications.remove(aWarningNotification);
      warningNotifications.add(index, aWarningNotification);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWarningNotificationAt(WarningNotification aWarningNotification, int index)
  {
    boolean wasAdded = false;
    if(warningNotifications.contains(aWarningNotification))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWarningNotifications()) { index = numberOfWarningNotifications() - 1; }
      warningNotifications.remove(aWarningNotification);
      warningNotifications.add(index, aWarningNotification);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWarningNotificationAt(aWarningNotification, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setItem(Item aItem)
  {
    boolean wasSet = false;
    if (aItem == null)
    {
      return wasSet;
    }

    Item existingItem = item;
    item = aItem;
    if (existingItem != null && !existingItem.equals(aItem))
    {
      existingItem.removeRecord(this);
    }
    item.addRecord(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=warningNotifications.size(); i > 0; i--)
    {
      WarningNotification aWarningNotification = warningNotifications.get(i - 1);
      aWarningNotification.delete();
    }
    Item placeholderItem = item;
    this.item = null;
    if(placeholderItem != null)
    {
      placeholderItem.removeRecord(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "enterDate" + "=" + (getEnterDate() != null ? !getEnterDate().equals(this)  ? getEnterDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "expiryDate" + "=" + (getExpiryDate() != null ? !getExpiryDate().equals(this)  ? getExpiryDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null");
  }
}