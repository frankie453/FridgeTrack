package ECSE428.Group6.FridgeTrack.model;

import java.sql.Date;
import java.util.*;

// line 53 "model.ump"
// line 117 "model.ump"
public class UserFeedback
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserFeedback Attributes
  private String id;
  private String feedback;
  private Date date;

  //UserFeedback Associations
  private List<WarningNotification> warningNotifications;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserFeedback(String aId, String aFeedback, Date aDate, User aUser)
  {
    id = aId;
    feedback = aFeedback;
    date = aDate;
    warningNotifications = new ArrayList<WarningNotification>();
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create userFeedback due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setFeedback(String aFeedback)
  {
    boolean wasSet = false;
    feedback = aFeedback;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public String getFeedback()
  {
    return feedback;
  }

  public Date getDate()
  {
    return date;
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
  public User getUser()
  {
    return user;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWarningNotifications()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWarningNotification(WarningNotification aWarningNotification)
  {
    boolean wasAdded = false;
    if (warningNotifications.contains(aWarningNotification)) { return false; }
    warningNotifications.add(aWarningNotification);
    if (aWarningNotification.indexOfUserFeedback(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aWarningNotification.addUserFeedback(this);
      if (!wasAdded)
      {
        warningNotifications.remove(aWarningNotification);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeWarningNotification(WarningNotification aWarningNotification)
  {
    boolean wasRemoved = false;
    if (!warningNotifications.contains(aWarningNotification))
    {
      return wasRemoved;
    }

    int oldIndex = warningNotifications.indexOf(aWarningNotification);
    warningNotifications.remove(oldIndex);
    if (aWarningNotification.indexOfUserFeedback(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aWarningNotification.removeUserFeedback(this);
      if (!wasRemoved)
      {
        warningNotifications.add(oldIndex,aWarningNotification);
      }
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
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeUserFeedback(this);
    }
    user.addUserFeedback(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<WarningNotification> copyOfWarningNotifications = new ArrayList<WarningNotification>(warningNotifications);
    warningNotifications.clear();
    for(WarningNotification aWarningNotification : copyOfWarningNotifications)
    {
      aWarningNotification.removeUserFeedback(this);
    }
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeUserFeedback(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "feedback" + ":" + getFeedback()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}