/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;

// line 45 "model.ump"
// line 111 "model.ump"
public class WarningNotification
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Warning { High, Medium, Low, None }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WarningNotification Attributes
  private String id;
  private Warning expiaryWarningLevel;

  //WarningNotification Associations
  private Record record;
  private List<UserFeedback> userFeedbacks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WarningNotification(String aId, Warning aExpiaryWarningLevel, Record aRecord)
  {
    id = aId;
    expiaryWarningLevel = aExpiaryWarningLevel;
    boolean didAddRecord = setRecord(aRecord);
    if (!didAddRecord)
    {
      throw new RuntimeException("Unable to create warningNotification due to record. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    userFeedbacks = new ArrayList<UserFeedback>();
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

  public boolean setExpiaryWarningLevel(Warning aExpiaryWarningLevel)
  {
    boolean wasSet = false;
    expiaryWarningLevel = aExpiaryWarningLevel;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public Warning getExpiaryWarningLevel()
  {
    return expiaryWarningLevel;
  }
  /* Code from template association_GetOne */
  public Record getRecord()
  {
    return record;
  }
  /* Code from template association_GetMany */
  public UserFeedback getUserFeedback(int index)
  {
    UserFeedback aUserFeedback = userFeedbacks.get(index);
    return aUserFeedback;
  }

  public List<UserFeedback> getUserFeedbacks()
  {
    List<UserFeedback> newUserFeedbacks = Collections.unmodifiableList(userFeedbacks);
    return newUserFeedbacks;
  }

  public int numberOfUserFeedbacks()
  {
    int number = userFeedbacks.size();
    return number;
  }

  public boolean hasUserFeedbacks()
  {
    boolean has = userFeedbacks.size() > 0;
    return has;
  }

  public int indexOfUserFeedback(UserFeedback aUserFeedback)
  {
    int index = userFeedbacks.indexOf(aUserFeedback);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRecord(Record aRecord)
  {
    boolean wasSet = false;
    if (aRecord == null)
    {
      return wasSet;
    }

    Record existingRecord = record;
    record = aRecord;
    if (existingRecord != null && !existingRecord.equals(aRecord))
    {
      existingRecord.removeWarningNotification(this);
    }
    record.addWarningNotification(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserFeedbacks()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addUserFeedback(UserFeedback aUserFeedback)
  {
    boolean wasAdded = false;
    if (userFeedbacks.contains(aUserFeedback)) { return false; }
    userFeedbacks.add(aUserFeedback);
    if (aUserFeedback.indexOfWarningNotification(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUserFeedback.addWarningNotification(this);
      if (!wasAdded)
      {
        userFeedbacks.remove(aUserFeedback);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeUserFeedback(UserFeedback aUserFeedback)
  {
    boolean wasRemoved = false;
    if (!userFeedbacks.contains(aUserFeedback))
    {
      return wasRemoved;
    }

    int oldIndex = userFeedbacks.indexOf(aUserFeedback);
    userFeedbacks.remove(oldIndex);
    if (aUserFeedback.indexOfWarningNotification(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUserFeedback.removeWarningNotification(this);
      if (!wasRemoved)
      {
        userFeedbacks.add(oldIndex,aUserFeedback);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserFeedbackAt(UserFeedback aUserFeedback, int index)
  {  
    boolean wasAdded = false;
    if(addUserFeedback(aUserFeedback))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserFeedbacks()) { index = numberOfUserFeedbacks() - 1; }
      userFeedbacks.remove(aUserFeedback);
      userFeedbacks.add(index, aUserFeedback);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserFeedbackAt(UserFeedback aUserFeedback, int index)
  {
    boolean wasAdded = false;
    if(userFeedbacks.contains(aUserFeedback))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserFeedbacks()) { index = numberOfUserFeedbacks() - 1; }
      userFeedbacks.remove(aUserFeedback);
      userFeedbacks.add(index, aUserFeedback);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserFeedbackAt(aUserFeedback, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Record placeholderRecord = record;
    this.record = null;
    if(placeholderRecord != null)
    {
      placeholderRecord.removeWarningNotification(this);
    }
    ArrayList<UserFeedback> copyOfUserFeedbacks = new ArrayList<UserFeedback>(userFeedbacks);
    userFeedbacks.clear();
    for(UserFeedback aUserFeedback : copyOfUserFeedbacks)
    {
      aUserFeedback.removeWarningNotification(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "expiaryWarningLevel" + "=" + (getExpiaryWarningLevel() != null ? !getExpiaryWarningLevel().equals(this)  ? getExpiaryWarningLevel().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "record = "+(getRecord()!=null?Integer.toHexString(System.identityHashCode(getRecord())):"null");
  }
}