package ECSE428.Group6.FridgeTrack;

import java.util.*;
import java.sql.Date;

// line 60 "model.ump"
// line 122 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private int id;
  private String userName;
  private String password;
  private String email;

  //User Associations
  private List<UserFeedback> userFeedbacks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(int aId, String aUserName, String aPassword, String aEmail)
  {
    id = aId;
    userName = aUserName;
    password = aPassword;
    email = aEmail;
    userFeedbacks = new ArrayList<UserFeedback>();
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

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserFeedbacks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public UserFeedback addUserFeedback(String aId, String aFeedback, Date aDate)
  {
    return new UserFeedback(aId, aFeedback, aDate, this);
  }

  public boolean addUserFeedback(UserFeedback aUserFeedback)
  {
    boolean wasAdded = false;
    if (userFeedbacks.contains(aUserFeedback)) { return false; }
    User existingUser = aUserFeedback.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aUserFeedback.setUser(this);
    }
    else
    {
      userFeedbacks.add(aUserFeedback);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserFeedback(UserFeedback aUserFeedback)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserFeedback, as it must always have a user
    if (!this.equals(aUserFeedback.getUser()))
    {
      userFeedbacks.remove(aUserFeedback);
      wasRemoved = true;
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
    for(int i=userFeedbacks.size(); i > 0; i--)
    {
      UserFeedback aUserFeedback = userFeedbacks.get(i - 1);
      aUserFeedback.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "userName" + ":" + getUserName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "email" + ":" + getEmail()+ "]";
  }
}