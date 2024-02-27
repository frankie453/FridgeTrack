package ECSE428.Group6.FridgeTrack.model;

import java.util.*;

// line 27 "model.ump"
// line 96 "model.ump"
public class ItemList
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ItemList Attributes
  private List<Item> list;

  //ItemList Associations
  private List<Item> items;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ItemList()
  {
    list = new ArrayList<Item>();
    items = new ArrayList<Item>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetMany */
  public boolean addList(Item aList)
  {
    boolean wasAdded = false;
    wasAdded = list.add(aList);
    return wasAdded;
  }

  public boolean removeList(Item aList)
  {
    boolean wasRemoved = false;
    wasRemoved = list.remove(aList);
    return wasRemoved;
  }
  /* Code from template attribute_GetMany */
  public Item getList(int index)
  {
    Item aList = list.get(index);
    return aList;
  }

  public Item[] getList()
  {
    Item[] newList = list.toArray(new Item[list.size()]);
    return newList;
  }

  public int numberOfList()
  {
    int number = list.size();
    return number;
  }

  public boolean hasList()
  {
    boolean has = list.size() > 0;
    return has;
  }

  public int indexOfList(Item aList)
  {
    int index = list.indexOf(aList);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem(String aName, Item.Unit aUnit, Recipe aRecipe, ItemCategory aItemCategory, Fridge aFridge)
  {
    return new Item(aName, aUnit, aRecipe, this, aItemCategory, aFridge);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    ItemList existingItemList = aItem.getItemList();
    boolean isNewItemList = existingItemList != null && !this.equals(existingItemList);
    if (isNewItemList)
    {
      aItem.setItemList(this);
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
    //Unable to remove aItem, as it must always have a itemList
    if (!this.equals(aItem.getItemList()))
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

  public void delete()
  {
    for(int i=items.size(); i > 0; i--)
    {
      Item aItem = items.get(i - 1);
      aItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]";
  }
}